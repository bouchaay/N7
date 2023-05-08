close all;
clc;
clear;

% Les paramètres

Fe = 6000; % Fréquence d'échantillonnage
Rb = 3000; % Débit binaire
Tb = 1/Rb; % Période d'échantillonnage
Te = 1/Fe; % Période d'échantillonnage
Rs = 1/Tb; % Fréquence de symbole
Ts = 1/Rs; % Période de symbole
Ns = Ts/Te; % Nombre d'échantillons par symbole
k1 = 3; % Nombre de bits par symbole pour le 8-PSK
M1 = 2^k1; % Ordre de modulation pour le 8-PSK
k2 = 2; % Nombre de bits par symbole pour le QPSK
M2 = 2^k2; % Ordre de modulation pour le QPSK
alpha1 = 0.2; % Roll-off 8-PSK
alpha2 = 0.35; % Roll-off QPSK
fp = 2000; % Fréquence porteuse
L = 100; % Nombre de coefficients du filtre de mise en forme
retard = (L*Ns)/2; % Retard introduit par le filtre

%% Modulateur QPSK

% Génération des bits
N = 200000;
bits = randi([0 1],1,N);

% Mapping des bits
partieReelle = 2*bits(1:2:end)-1;
partieImaginaire = 2*bits(2:2:end)-1;
symbolesQPSK = partieReelle + 1i*partieImaginaire;

% Suréchantillonnage
symbolesSurEchantillones = kron(symbolesQPSK, [1 zeros(1,Ns-1)]);

% Filtre de mise en forme
h = rcosdesign(alpha2, L, Ns, 'sqrt');

% Gestion du retard
xeQPSK = filter(h, 1, [symbolesSurEchantillones zeros(1, retard)]);
xeQPSK = xeQPSK(retard+1:end);

% Signal généré sur la voie en phase
I_QPSK = real(xeQPSK);

% Signal généré sur la voie en quadrature
Q_QPSK = imag(xeQPSK);


% Canal passe bas equivalent
for j = 1:7
    % Puissance du signal
    Px = mean(abs(xeQPSK).^2);

    % Eb/N0
    EbN0 = 10^((j-1)/10);

    % Calcul de la puissance du bruit
    sigma = sqrt((Ns*Px)./(2*log2(M2)*EbN0));

    % Bruit gaussien
    bruit = sigma*randn(1, length(xeQPSK)) + 1i*sigma*randn(1, length(xeQPSK));

    % Signal bruité
    rQPSK = xeQPSK + bruit;

    % Filtre de réception
    hr = h;

    % Gestion du retard
    z = filter(hr, 1, [rQPSK zeros(1, retard)]);
    z = z(retard+1:end);

    % Echantillonage 
    z_ech = z(1:Ns:end);

    % Demapping du signal modulé sur fréquence porteuse
    bits_estime = zeros(1,N);
    bits_estime(1:2:end) = (real(z_ech) > 0);
    bits_estime(2:2:end) = (imag(z_ech) > 0);

    % Taux d'erreur binaire
    TEB_QPSK(j) = sum(bits_estime ~= bits)/N;

    % Taux d'erreur theorique
    TEB_THEORIQUE_QPSK(j) = qfunc(sqrt(2*EbN0));
end

%% Modulateur 8-PSK

% Génération des bits
N = 3000000;
bits = randi([0 1], 1, N);

% Mapping des bits
bitsReshapped = reshape(bits, k1, N/k1);
l = bi2de(bitsReshapped', 'left-msb')';
dk = pskmod(l, M1, pi/M1);

% Suréchantillonnage
symbolesSurEchantillones = kron(dk, [1 zeros(1,Ns-1)]);

% Filtre de mise en forme
h = rcosdesign(alpha1, L, Ns, 'sqrt');

% Gestion du retard
xe8PSK = filter(h, 1, [symbolesSurEchantillones zeros(1, retard)]);
xe8PSK = xe8PSK(retard+1:end);

% Signal généré sur la voie en phase
I_8PSK = real(xe8PSK);

% Signal généré sur la voie quadrature
Q_8PSK = imag(xe8PSK);

%% Canal de propagation

for j = 1:7
    % Puissance du signal
    Px = mean(abs(xe8PSK).^2);

    % Eb/N0
    EbN0 = 10^((j-1)/10);

    % Calcul de la puissance du bruit
    sigma = sqrt((Ns*Px)./(2*log2(M1)*EbN0));

    % Bruit gaussien
    bruit = sigma*randn(1, length(xe8PSK)) + 1i*sigma*randn(1, length(xe8PSK));

    % Signal bruité
    r8PSK = xe8PSK + bruit;

    % Filtre de réciption
    hr = h;

    % Gestion du retard
    z = filter(hr,1, [r8PSK zeros(1, retard)]);
    z = z(retard + 1: end);

    % Echantillonage 
    z_ech = z(1:Ns:end);
    scatterplot(z_ech);

    % Demapping
    bitsDemappe = pskdemod(z_ech, M1, pi/M1);
    bitsEstimes = int2bit((bitsDemappe'),3)';

    % Taux d'erreur binaire
    TEB_8PSK(j) = sum(bitsEstimes ~= bits)/N;

    % Taux d'erreur theorique
    TEB_THEORIQUE_8PSK(j) = (2/log2(M1))*qfunc(sqrt(2*log2(M1)*EbN0)*sin(pi/8));
end

%% Les tracés superposés des densités spectrales de puissance des deux chaines

% Densité spectrale de puissace du signal généré sur la voie en phase
DSP_phase_QPSK = pwelch(I_QPSK, [], [], [], Fe, 'twosided');
DSP_phase_8PSK = pwelch(I_8PSK, [], [], [], Fe, 'twosided');
F_phase_QPSK = linspace(-Fe/2, Fe/2, length(DSP_phase_QPSK));
F_phase_8PSK = linspace(-Fe/2, Fe/2, length(DSP_phase_8PSK));
figure;
semilogy(F_phase_QPSK, DSP_phase_QPSK)
hold on;
semilogy(F_phase_8PSK, DSP_phase_8PSK)
title('Densité spectrales de puissance des signaux I (partie réeel de xe')
xlabel('Signal I (QPSK), Signal I (8-PSK)')
grid on;

% Densité spectrale de puissace du signal généré sur la voie en phase
DSP_quadrature_QPSK = pwelch(Q_QPSK, [], [], 4096, Fe, 'centered');
DSP_quadrature_8PSK = pwelch(Q_8PSK, [], [], 4096, Fe, 'centered');
F_quadrature_QPSK = linspace(-Fe/2, Fe/2, length(DSP_quadrature_QPSK));
F_quadrature_8PSK = linspace(-Fe/2, Fe/2, length(DSP_quadrature_8PSK));
figure;
semilogy(F_quadrature_QPSK, DSP_quadrature_QPSK)
hold on;
semilogy(F_quadrature_8PSK, DSP_quadrature_8PSK)
title('Densité spectrales de puissance des signaux I (partie réeel de xe')
xlabel('Signal I (QPSK), Signal I (8-PSK)')
grid on;

% Les taux d'erreur binaire QPSK et 8-PSK
figure;
semilogy(0:6, TEB_8PSK, 'r');
hold on;
semilogy(0:6, TEB_THEORIQUE_8PSK, 'b');
semilogy(0:6, TEB_QPSK, 'y');
semilogy(0:6, TEB_THEORIQUE_QPSK, 'c');
grid on;
title('TEB éstimé et théorique 8-PSK et TEB estimé et théorique QPSK');
xlabel('SNR (dB)');
ylabel('TEB');
legend('TEB éstimé 8-PSK', 'TEB théorique 8-PSK', 'TEB éstimé QPSK', 'TEB théorique QPSK');
grid on;
