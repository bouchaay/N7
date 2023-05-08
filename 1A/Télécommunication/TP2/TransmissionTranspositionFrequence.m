close all;
clc;
clear;

% Les paramètres

Fe = 24000; % Fréquence d'échantillonnage
Rb = 3000; % Débit binaire
Tb = 1/Rb; % Période d'échantillonnage
Te = 1/Fe; % Période d'échantillonnage
N = 10000; % Nombre de bits
Rs = Rb; % Fréquence de symbole
Ts = 1/Rs; % Période de symbole
Ns = Fe/Rs; % Nombre d'échantillons par symbole
k = 2; % Nombre de bits par symbole
M = 2^k; % Ordre de modulation
alpha = 0.35; % Roll-off
fp = 2000; % Fréquence porteuse
L = 100; % Span
retard = (L*Ns)/2; % Retard introsuit par le filtre

%% Modulation QPSK

% Génération des bits
bits = randi([0 1],1,N);

% Mapping des bits
ak = 2*bits(1:2:end)-1;
bk = 2*bits(2:2:end)-1;
dk = ak + 1i*bk;

% Suréchantillonnage
symbolesSurEchantionnesI = kron(ak, [1 zeros(1,Ns-1)]);
symbolesSurEchantionnesQ = kron(bk, [1 zeros(1,Ns-1)]);

% Filtre de mise en forme
h = rcosdesign(alpha,L , Ns, 'sqrt');

% Filtrage
I = filter(h,1,[symbolesSurEchantionnesI zeros(1,retard)]);
Q = filter(h,1,[symbolesSurEchantionnesQ zeros(1,retard)]);

% Gestion du retard
I = I(retard + 1 : end);
Q = Q(retard + 1 : end);

% Les densités spectrales de puissance de I(t) et Q(t)
DSPI = pwelch(I, [], [], 4096, Fe, 'centered');
DSPQ = pwelch(Q, [], [], 4096, Fe, 'centered');

%% Transposition de fréquence

% Signal transmis sur fréquence porteuse
Tx = 0:Te:(length(I)-1)*Te;
x = I.*cos(2*pi*fp*Tx) - Q.*sin(2*pi*fp*Tx);
DSPx = (1/length(x))*abs(fft(x,2^nextpow2(length(x)))).^2;

% Retour en base
T = 0: Te: (length(x)-1)*Te;
xsin = x.*sin(2*pi*fp*T);
xcos = x.*cos(2*pi*fp*T);
y = xcos - 1i*xsin;

% Filtre de réception
hr = h;

% Filtrage avec gestion du retard
z = filter(hr,1, [y zeros(1, retard)]);
z = z(retard+1:end);

% Echantillonnage
z_ech = z(1:Ns:end);

% Demapping
bitsEstimes = zeros(1, N);
bitsEstimes(1:2:end) = (real(z_ech) > 0);
bitsEstimes(2:2:end) = (imag(z_ech) > 0);

% Taux d'erreur binaire
TEB = sum(bitsEstimes ~= bits) / N;

%% Les Tracés

% Tracé du signal généré sur voie en phase I(t)
T = 0:Te:(length(I)-1)*Te;
figure;
plot(T, I)
axis([0 0.1 -0.6 0.6]);
xlabel('Temps (s)')
ylabel('Amplitude')
title('Tracé du signal généré sur voie en phase')
grid on;

% Tracé du siganl généré sur voie en quadrature Q(t)
figure;
plot(T, Q)
axis([0 0.1 -0.6 0.6]);
xlabel('Temps (s)')
ylabel('Amplitude')
title('Tracé du signal généré sur voie en quadrature')
grid on;

% Tracé du signal transmis sur fréquence porteuse x(t)
T = Te: Te: Te*length(x);
figure;
plot(T, x)
title('Tracé du signal transmis sur fréquence porteuse')
axis([0 0.1 -0.8 0.8]);
grid on;

% Tracé de la densité spectrale de puissance du signal généré sur voie en phase I(t)
F = linspace(-Fe/2, Fe/2, length(DSPI));
figure;
semilogy(F, DSPI);
xlabel('Fréquence (Hz)')
ylabel('DSP')
title('Densité spectrale de puissance du signal généré sur la voie en phase')
grid on;

% Tracé de la densité spectrale de puissance du signal généré sur voie en quadrature Q(t)
figure;
semilogy(F, DSPQ);
xlabel('Fréquence (Hz)')
ylabel('DSP')
title('Densité spectrale de puissance du signal généré sur la voie en quadrature')
grid on;

% Trace de la densité spectrale de puissance du signal transmis sur fréquence porteuse x(t)
figure;
semilogy(linspace(-Fe/2, Fe/2, length(DSPx)), fftshift(DSPx));
xlabel('Fréquence (Hz)')
ylabel('DSP')
title('Densité spectrale de puissance du signal transmis sur fréquence porteuse')
grid on;

%% Canal de transmission

for j = 1:7

    % Puissance du signal
    Px = mean(x.^2);

    % Eb/N0
    EbN0 = 10^(j-1/10);

    % Bruit
    sigma = sqrt(Ns*Px/(2*k*EbN0)); % k = log2(M)

    % Bruit blanc gaussien
    bruit = sigma*randn(1, length(x));

    % Signal reçu
    r = x + bruit;

    % Retour en base
    T = 0:Te:(length(r)-1)*Te;
    rsin = r.*sin(2*pi*fp*T);
    rcos = r.*cos(2*pi*fp*T);
    y = rcos - 1i*rsin;

    % Filtre de réception
    hr = h;

    % Filtrage avec gestion du retard
    z = filter(hr,1, [y zeros(1, retard)]);
    z = z(retard+1:end);

    % Echantillonnage
    z_ech = z(1:Ns:end);

    % Demapping
    bitsEstimes = zeros(1, N);
    bitsEstimes(1:2:end) = (real(z_ech) > 0);
    bitsEstimes(2:2:end) = (imag(z_ech) > 0);

    % Taux d'erreur binaire
    TEB_ESTIME(j) = sum(bitsEstimes ~= bits) / N;
    TEB_THEORIQUE(j) = qfunc(sqrt(2*EbN0));

end

% Tracé du TEB Estimé en fonction de Eb/N0
figure;
semilogy(0:6, TEB_ESTIME, 'r');
grid on;
title('TEB Estimé en fonction de Eb/N0 ');
xlabel('SNR (dB)');
ylabel('TEB');
grid on;

% Tracé du TEB et TEB théorique en fonction de Eb/N0
figure;
semilogy(0:6, TEB_ESTIME, 'r');
hold on;
semilogy(0:6, TEB_THEORIQUE, 'b');
grid on;
title('TEB éstimé avec bruit, TEB théorique');
xlabel('SNR (dB)');
ylabel('TEB');
legend('TEB éstimé', 'TEB théorique');
grid on;
