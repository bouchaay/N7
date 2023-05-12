close all;
clc;
clear;

% Les paramètres

Fe = 24000; % Fréquence d'échantillonnage
Rb = 6000; % Débit binaire
Tb = 1/Rb; % Période d'échantillonnage
Te = 1/Fe; % Période d'échantillonnage
Rs = 1/Tb; % Fréquence de symbole
Ts = 1/Rs; % Période de symbole
Ns = Ts/Te; % Nombre d'échantillons par symbole
N = 1000000; % Nombre de bits à transmettre
k = 1; % Nombre de bits par symbole
M = 2^k; % Nombre de symboles
phi = (40*pi)/180; % phase en rad

% Génération des bits
bits = randi([0 1], 1, N);

% Mapping des bits
ak = 2*bits - 1;

% Sur-echantillonnage
symbolesSurEchantionnes = kron(ak, [1 zeros(1,Ns-1)]);

% Filtre de mise en forme
h = ones(1, Ns);

% Enveloppe Complexe
xe = filter(h, 1, symbolesSurEchantionnes);

%% Canal de transmission

for j = 1:7

    % Puissance du signal
    Pxe = mean(xe.^2);

    % Eb/N0
    EbN0 = 10^((j-1)/10);

    % Bruit
    sigma = sqrt(Ns*Pxe/(2*k*EbN0)); % k = log2(M)

    % Bruit blanc gaussien
    bruit = sigma*randn(1, length(xe)) + 1i*sigma*randn(1, length(xe));

    % Signal reçu
    r = xe + bruit;

    % Introduction de l'erreur phase
    rAvecErreur = r*exp(1i*phi);

    % Filtre de réception
    hr = h;

    % Filtrage
    z = filter(hr,1, rAvecErreur);

    % Echantillonnage
    z_ech = z(Ns:Ns:end);

    % Constellation
    scatterplot(z_ech)

    % Partie réelle du signal échantillonné
    z_ech_real = real(z_ech);

    % Décision
    SymbolesEstimes = z_ech_real/Ns;
    SymbolesEstimes = sign(SymbolesEstimes);

    % Demapping
    BitsEstimes = SymbolesEstimes > 0;

    % Taux d'erreur binaire
    TEB_ESTIME(j) = sum(BitsEstimes ~= bits)/N;

    % Taux d'erreur Binaire théorique
    TEB_THEORIQUE(j) = qfunc(sqrt(2*EbN0)*cos(phi));
end

figure;
semilogy(0:6, TEB_ESTIME, 'r');
hold on;
semilogy(0:6, TEB_THEORIQUE, 'b');
semilogy(0:6, TEB_ESTIME, '*');
legend('TEB estimé', 'TEB théorique');
xlabel('Eb/N0 (dB)');
ylabel('TEB');
title('Comparaison entre le TEB estimé et le TEB théorique');
grid on;
