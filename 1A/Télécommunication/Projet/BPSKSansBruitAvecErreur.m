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
N = 10000; % Nombre de bits à transmettre
k = 1; % Nombre de bits par symbole
M = 2^k; % Nombre de symboles
phi = [(40*pi)/180, (100*pi)/180, (180*pi)/180];

% Génération des bits
bits = randi([0 1], 1, N);

% Mapping des bits
ak = 2*bits - 1;

% Sur-echantillonnage
symbolesSurEchantionnes = kron(ak, [1 zeros(1,Ns-1)]);

% Filtre de mise en forme
h = ones(1, Ns);

% Enveloppe Complexe
xe = filter(h, 1,symbolesSurEchantionnes);

for j = 1:3
    % Introduction de l'erreur de phase
    xeAvecErreur = xe*exp(1i*phi(j));

    % Filtre de récéption
    hr = h;

    % Filtrage avec gestion du retard
    z = filter(h, 1, xeAvecErreur);

    % Echantillonnage
    z_ech = z(Ns:Ns:end);

    % Constellation
    scatterplot(z_ech);

    % Partie réelle du signal échantillonné
    z_ech_real = real(z_ech);

    % Décision
    SymbolesEstimes = z_ech_real/Ns;
    SymbolesEstimes = sign(SymbolesEstimes);

    % Demapping
    BitsEstimes = SymbolesEstimes > 0;

    % Taux d'erreur binaire
    TEB(j) = sum(BitsEstimes ~= bits)/N;
    
end

figure;
semilogy((180/pi)*phi, TEB);
title('TEB éstimé pour différent phase');
xlabel('Phase');
ylabel('TEB');
grid on;