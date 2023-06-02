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

% Filtre de récéption
hr = h;

% Filtrage avec gestion du retard
z = filter(h, 1, xe);

% Echantillonnage
z_ech = z(Ns:Ns:end);

% Constellation
scatterplot(z_ech);

% Décision
SymbolesEstimes = z_ech/Ns;
SymbolesEstimes = sign(SymbolesEstimes);

% Demapping
BitsEstimes = SymbolesEstimes > 0;

% Taux d'erreur binaire
TEB = sum(BitsEstimes ~= bits)/N;

