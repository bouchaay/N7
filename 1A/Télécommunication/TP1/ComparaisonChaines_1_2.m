% -------------------------------------------------------------------------
%                   Etude comparative entre les chaines de transmission
%                               Chaine 1 | Chaine 2
% -------------------------------------------------------------------------
%                                Ayoub Bouchama
%                              Oussama ElGuerraoui
%                                  'Groupe F' 
% -------------------------------------------------------------------------

clear;
close all;
clc;

% Les paramètres

Fe = 24000; % Fréquence d'échantillonnage
Rb = 3000; % Débit binaire
Tb = 1/Rb; % Période d'échantillonnage
Te = 1/Fe; % Période d'échantillonnage
N = 100000; % Nombre de bits
Rs = 1/Tb; % Fréquence de symbole
Ts = 1/Rs; % Période de symbole
Ns = Ts/Te; % Nombre d'échantillons par symbole
M = 2; % Ordre de modulation

%% Implementation de la chaine de transmission 1

% Génération de N bits aléatoires
bits = randi([0 1],1,N);

% Mapping
Symboles = [-1, 1];
Map = 2*bits - 1;

% Sur-échantillonnage
signal_sur_echantillonne1 = kron(Map ,[1 zeros(1,Ns-1)]);

% Filtre de mise en forme
hm1 = ones(1, Ns);
x = filter(hm1, 1, signal_sur_echantillonne1);

% Ajout du bruit
Ps = mean(x.^2);

for i = 0:8

    % Eb/N0 en dB
    EbN0dB = 10^(i/10);

    % Calcul de la puissance du bruit
    sigma2 = (Ns*Ps)./(2*log2(M)*EbN0dB);

    % Bruit gaussien
    bruit = sqrt(sigma2)*randn(1, length(x));

    % Siganl bruité
    r = x + bruit;

    % Filtre de réception
    hr2 = ones(1, Ns);
    z1 = filter(hr2, 1, r);

    % Echantillonnage
    z1_echant = z1(Ns:Ns:end);

    % Décision
    map_estimes1 = z1_echant/Ns;
    mapsign = sign(map_estimes1);

    % Demapping
    bits_ditecte1 = map_estimes1 > 0;

    % Taux d'erreur binaire
    TEB_EbN0dB_1(i+1) = sum(bits_ditecte1 ~= bits)/N;

    % Taux d'erreur symbole
    TES_EbN0dB_1(i+1) = sum(mapsign ~= Map)/N;
end

%% Implementation de la chaine de transmission 2

% Mapping
Symboles = [-1, 1];
Map = 2*bits - 1;

% Sur-échantillonnage
signal_sur_echantillonne = kron(Map ,[1 zeros(1,Ns-1)]);

% Filtre de mise en forme
hm2 = ones(1, Ns);
x = filter(hm2, 1, signal_sur_echantillonne);

% Ajout du bruit
Ps = mean(x.^2);

for i = 0:8

    % Eb/N0 en dB
    EbN0dB = 10^(i/10);

    % Calcul de la puissance du bruit
    sigma2 = (Ns*Ps)./(2*log2(M)*EbN0dB);

    % Bruit gaussien
    bruit = sqrt(sigma2)*randn(1, length(x));

    % Siganl bruité
    r = x + bruit;

    % Filtre de réception
    hr2 = ones(1, Ns/2);
    z2 = filter(hr2, 1, r);

    % Echantillonnage
    z2_echant = z2(Ns:Ns:end);

    % Décision
    map_estimes2 = z2_echant/Ns;
    mapsign2 = sign(map_estimes2);

    % Demapping
    bits_ditecte2 = map_estimes2 > 0;

    % Taux d'erreur binaire
    TEB_EbN0dB_2(i+1) = sum(bits_ditecte2 ~= bits)/N;

    % Taux d'erreur symbole
    TES_EbN0dB_2(i+1) = sum(mapsign2 ~= Map)/N;
end

% Tracé des taux binaires d'erreurs en fonction de Eb/N0 sur la même figure
figure;
semilogy(0:8, TEB_EbN0dB_1, 'r');
hold on;
semilogy(0:8, TEB_EbN0dB_2, 'b');
grid on;
legend('Chaine 1', 'Chaine 2');
xlabel('Eb/N0 (dB)');
ylabel('TEB');
title('Comparaison des deux chaines (1|2) de transmission');

% Tracé des taux symboles d'erreurs en fonction de Eb/N0 sur la même figure
figure;
semilogy(0:8, TES_EbN0dB_1, 'r');
hold on;
semilogy(0:8, TES_EbN0dB_2, 'b');
grid on;
legend('Chaine 1', 'Chaine 2');
xlabel('Eb/N0 (dB)');
ylabel('TES');
title('Comparaison des deux chaines (1|2) de transmission');



