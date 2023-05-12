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
phi = [0, (40*pi)/180, (100*pi)/180]; % phase en rad

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
    for k = 1:3

        rAvecErreur = r*exp(1i*phi(k));

        % Filtre de réception
        hr = h;

        % Filtrage
        z = filter(hr,1, rAvecErreur);

        % Echantillonnage
        zm = z(Ns:Ns:end);

        % La phase estimée
        phiEstime = 1/2*angle(sum(zm.^2));

        % Correction de la phase
        zc = zm*exp(-1i*phiEstime);

        % Partie réelle du signal échantillonné
        zmReal = real(zm);
        zcReal = real(zc);

        % Décision
        SymbolesEstimesOrig = zmReal/Ns;
        SymbolesEstimesOrig = sign(SymbolesEstimesOrig);
        SymbolesEstimesCor = zcReal/Ns;
        SymbolesEstimesCor = sign(SymbolesEstimesCor);

        % Demapping
        BitsEstimesOrig = SymbolesEstimesOrig > 0;
        BitsEstimesCor = SymbolesEstimesCor > 0;

        % Taux d'erreur binaire
        TEB_ESTIME_ORIGINAL(j,k) = sum(BitsEstimesOrig ~= bits)/N;
        TEB_ESTIME_CORRIGE(j,k) = sum(BitsEstimesCor ~= bits)/N;
    end
end


figure;
semilogy(0:6, TEB_ESTIME_ORIGINAL(:,1));
semilogy(0:6, TEB_ESTIME_ORIGINAL(:,2));
semilogy(0:6, TEB_ESTIME_ORIGINAL(:,3));
hold on;
semilogy(0:6, TEB_ESTIME_CORRIGE(:,1));
semilogy(0:6, TEB_ESTIME_CORRIGE(:,2));
semilogy(0:6, TEB_ESTIME_CORRIGE(:,3));
legend('TEB original phi = 0', 'TEB original phi = 40', 'TEB original phi = 100', 'TEB corrigé phi = 0', 'TEB corrigé phi = 40', 'TEB corrigé phi = 100');
xlabel('Eb/N0 (dB)');
ylabel('TEB');
title('Comparaison entre le TEB original et le TEB corrigé');
grid on;