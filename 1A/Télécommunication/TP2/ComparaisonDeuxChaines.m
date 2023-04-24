close all;
clc;
clear;

% Les paramètres

Fe = 24000; % Fréquence d'échantillonnage
Rb = 3000; % Débit binaire
Tb = 1/Rb; % Période d'échantillonnage
Te = 1/Fe; % Période d'échantillonnage
Rs = 1/Tb; % Fréquence de symbole
Ts = 1/Rs; % Période de symbole
Ns = Ts/Te; % Nombre d'échantillons par symbole
k1 = 3; % Nombre de bits par symbole pour le 8-PSK
M1 = 2^k1; % Ordre de modulation pour le 8-PSK
k2 = 2; % Nombre de bits par symbole pour le 8-PSK
M2 = 2^k2; % Ordre de modulation pour le 8-PSK
alpha1 = 0.2; % Roll-off 8-PSK
alpha2 = 0.35; % Roll-off QPSK
fp = 2000; % Fréquence porteuse

%% Modulateur QPSK

% Génération des bits
N = 2000000;
bits = randi([0 1],1,N);

% Mapping des bits
partieReelle = 2*bits(1:2:end)-1;
partieImaginaire = 2*bits(2:2:end)-1;
symbolesQPSK = partieReelle + 1i*partieImaginaire;

% Suréchantillonnage
symbolesSurEchantillones = kron(symbolesQPSK, [1 zeros(1,Ns-1)]);

% Filtrage
L = 100;
h = rcosdesign(alpha2, L, Ns, 'sqrt');

% Gestion du retard
ordre = L*Ns;
decalage = floor((ordre)/2);
xe_filtre = filter(h, 1, [symbolesSurEchantillones zeros(1, decalage)]);
xeQPSK = xe_filtre(decalage+1:end);

% Signal généré sur la voie en phase
I_QPSK = real(xeQPSK);

% Signal généré sur la voie en quadrature
Q_QPSK = imag(xeQPSK);

% Transposition de frequence
Tx = Te:Te:length(xeQPSK)*Te;
x_intermediaire = xeQPSK.*exp(1i*2*pi*Tx.*fp);
xQPSK = real(x_intermediaire);

% Canal de transmission
for j = 1:7
    % Puissance du signal
    Px = mean(xQPSK.^2);

    % Eb/N0
    EbN0 = 10^(j/10);

    % Calcul de la puissance du bruit
    sigma2 = (Ns*Px)./(2*log2(M2)*EbN0);

    % Bruit gaussien
    bruit = sqrt(sigma2)*randn(1, length(xQPSK));

    % Signal bruité
    rQPSK = xQPSK + bruit;

    % retour en bande base
    T = Te: Te: Te*length(xQPSK);
    xsin = rQPSK.*sin(2*pi*fp*T);
    xcos = rQPSK.*cos(2*pi*fp*T);
    yQPSK = xcos - 1i*xsin;

    % Filtre de réception
    L = 100;
    hr = rcosdesign(alpha2, L, Ns, 'sqrt');

    % Gestion du retard
    ordre = L*Ns;
    decalage = floor(ordre/2);
    z = filter(hr,1, [yQPSK zeros(1, decalage)]);
    z_dec = z(decalage+1:end);

    % Echantillonage 
    z_echan = z_dec(1:Ns:end);

    % Demapping du signal modulé sur fréquence porteuse
    bits_estime = zeros(1,N);
    z_ech_real = real(z_echan);
    z_ech_imag = imag(z_echan);
    bits_estime(1:2:end) = (z_ech_real > 0);
    bits_estime(2:2:end) = (z_ech_imag > 0);

    % Taux d'erreur binaire
    TEB_QPSK(j) = sum(bits_estime ~= bits)/N;

    % Taux d'erreur theorique
    TEB_Theorique_QPSK(j) = qfunc(sqrt(2*EbN0));
end

%% Modulateur 8-PSK

% Génération des bits
N = 3000000;
bits = randi([0 1],1,N);

% Mapping des bits
bits_res = reshape(bits, k1, N/k1);
l = bi2de(bits_res', 'left-msb')';
symboles = pskmod(l, M1, pi/M1);

% Suréchantillonnage
symbolesSurEchantillones = kron(symboles, [1 zeros(1,Ns-1)]);

% Filtrage
L = 100;
h = rcosdesign(alpha1, L, Ns, 'sqrt');

% Gestion du retard
ordre = L*Ns;
decalage = floor((ordre)/2);
xe_filtre = filter(h, 1, [symbolesSurEchantillones zeros(1, decalage)]);
xe_8PSK = xe_filtre(decalage+1:end);

% Signal généré sur la voie en phase
I_8PSK = real(xe_8PSK);

% Signal généré sur la voie quadrature
Q_8PSK = imag(xe_8PSK);

% Transposition de fréquence
Tx = Te:Te:length(xe_8PSK)*Te;
x_intermediaire = xe_8PSK.*exp(1i*2*pi*Tx.*fp);
x_8PSK = real(x_intermediaire);

% Retour en base
T = Te: Te: Te*length(x_8PSK);
xsin = x_8PSK.*sin(2*pi*fp*T);
xcos = x_8PSK.*cos(2*pi*fp*T);
y = xcos - 1i*xsin;

for j = 1:7
    % Puissance du signal
    Px = mean(x_8PSK.^2);

    % Eb/N0
    EbN0 = 10^(j/10);

    % Calcul de la puissance du bruit
    sigma2 = (Ns*Px)./(2*log2(M1)*EbN0);

    % Bruit gaussien
    bruit = sqrt(sigma2)*randn(1, length(x_8PSK));

    % Signal bruité
    r_8PSK = x_8PSK + bruit;

    % retour en bande base
    T = Te: Te: Te*length(x_8PSK);
    rsin = r_8PSK.*sin(2*pi*fp*T);
    rcos = r_8PSK.*cos(2*pi*fp*T);
    yr = rcos - 1i*rsin;

    % Filtre de réciption
    L = 100;
    hr = rcosdesign(alpha1, L, Ns, 'sqrt');

    % Gestion du retard
    ordre = L*Ns;
    decalage = floor(ordre/2);
    z = filter(hr,1, [yr zeros(1, decalage)]);
    z_dec = z(decalage + 1: end);

    % Echantillonage 
    z_echan = z_dec(1:Ns:end);
    %scatterplot(z_echan);

    % Demapping
    bits_estime_inter = pskdemod(z_echan, M1, pi/M1);
    bits_estime = int2bit((bits_estime_inter'),3)';

    % Taux d'erreur binaire
    TEB_8PSK(j) = sum(bits_estime ~= bits)/N;

    % Taux d'erreur theorique
    TEB_Theorique_8PSK(j) = (2/log2(M1))*qfunc(sqrt(2*log2(M1)*EbN0)*sin(pi/8));
end

%% Les tracés superposés des densités spectrales de puissance des deux chaines

% Densité spectrale de puissace du signal généré sur la voie en phase
DSP_phase_QPSK = pwelch(I_QPSK, [], [], [], Fe, 'twosided');
DSP_phase_8PSK = pwelch(I_8PSK, [], [], [], Fe, 'twosided');
F_phase_QPSK = linspace(-Fe/2, Fe/2, length(DSP_phase_QPSK));
figure;
semilogy(F_phase_QPSK, DSP_phase_QPSK)
hold on;
semilogy(F_phase_QPSK, DSP_phase_8PSK)
title('Densité spectrales de puissance des signaux I (partie réeel de xe')
xlabel('Signal I (QPSK), Signal I (8-PSK)')
grid on;

% Densité spectrale de puissace du signal généré sur la voie en phase
DSP_quadrature_QPSK = pwelch(Q_QPSK, [], [], [], Fe, 'twosided');
DSP_quadrature_8PSK = pwelch(Q_8PSK, [], [], [], Fe, 'twosided');
F_quadrature_QPSK = linspace(-Fe/2, Fe/2, length(DSP_quadrature_QPSK));
figure;
semilogy(F_quadrature_QPSK, DSP_quadrature_QPSK)
hold on;
semilogy(F_quadrature_QPSK, DSP_quadrature_8PSK)
title('Densité spectrales de puissance des signaux I (partie réeel de xe')
xlabel('Signal I (QPSK), Signal I (8-PSK)')
grid on;

% Les taux d'erreur binaire QPSK et 8-PSK
figure;
semilogy(0:6, TEB_8PSK, 'r');
hold on;
semilogy(0:6, TEB_Theorique_8PSK, 'b');
semilogy(0:6, TEB_QPSK, 'y');
semilogy(0:6, TEB_Theorique_QPSK, 'c');
grid on;
title('TEB éstimé et théorique 8-PSK et TEB estimé et théorique QPSK');
xlabel('SNR (dB)');
ylabel('TEB');
legend('TEB éstimé 8-PSK', 'TEB théorique 8-PSK', 'TEB éstimé QPSK', 'TEB théorique QPSK');
grid on;
