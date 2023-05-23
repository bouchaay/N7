% -------------------------------------------------------------------------
%           Chaine de transmission avec bruit et erreur de phase
% -------------------------------------------------------------------------
%                                Ayoub Bouchama
%                              Oussama ElGuerraoui
%                                  'Groupe F' 
% -------------------------------------------------------------------------

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
phi = (40*pi)/180; % phase en rad 40°
phi100 = (100*pi)/180; % phase en rad 100°
Echelle = 0:6; % Eb/N0 en dB

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

% Initialisation des vecteurs
TEB_ESTIME = zeros(1, length(Echelle)); % TEB estimé pour une phase 40°
TEB_THEORIQUE = zeros(1, length(Echelle)); % TEB théorique pour une phase 40°
TEB_PHASE_NULLE = zeros(1, length(Echelle)); % TEB théorique pour une phase 0°
TEB_ESTIME_100 = zeros(1, length(Echelle)); % TEB estimé pour une phase 100°
TEB_THEORIQUE_100 = zeros(1, length(Echelle)); % TEB théorique pour une phase 100°

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
    rAvecErreur = r*exp(1i*phi); % phase 40°
    rAvecErreur100 = r*exp(1i*phi100); % phase 100°
    rSansErreur = r; % phase 0°

    % Filtre de réception
    hr = h;

    % Filtrage
    z = filter(hr,1, rAvecErreur); % phase 40°
    z100 = filter(hr,1, rAvecErreur100); % phase 100°
    zSansErreur = filter(hr,1, rSansErreur); % phase 0°

    % Echantillonnage
    z_ech = z(Ns:Ns:end); % phase 40°
    z_ech100 = z100(Ns:Ns:end); % phase 100°
    z_ech_SansErreur = zSansErreur(Ns:Ns:end); % phase 0°

    % Partie réelle du signal échantillonné
    z_ech_real = real(z_ech); % phase 40°
    z_ech_real100 = real(z_ech100); % phase 100°
    z_ech_real_SansErreur = real(z_ech_SansErreur); % phase 0°

    % Décision
    SymbolesEstimes = sign(z_ech_real); % phase 40°
    SymbolesEstimes100 = sign(z_ech_real100); % phase 100°
    SymbolesEstimesSansErreur = sign(z_ech_real_SansErreur); % phase 0°

    % Demapping
    BitsEstimes = SymbolesEstimes > 0; % phase 40°
    BitsEstimes100 = SymbolesEstimes100 > 0; % phase 100°
    BitsEstimesSansErreur = SymbolesEstimesSansErreur > 0; % phase 0°

    % Taux d'erreur binaire estimé
    TEB_ESTIME(j) = sum(BitsEstimes ~= bits)/N; % phase 40°
    TEB_ESTIME_100(j) = sum(BitsEstimes100 ~= bits)/N; % phase 100°

    % Taux d'erreur Binaire théorique
    TEB_THEORIQUE(j) = qfunc(sqrt(2*EbN0)*cos(phi)); % phase 40°
    TEB_THEORIQUE_100(j) = qfunc(sqrt(2*EbN0)*cos(phi100)); % phase 100°

    % Taux d'erreur binaire pour une phase 0°
    TEB_PHASE_NULLE(j) = qfunc(sqrt(2*EbN0));
end

% Tracé du taux d'erreur binaire estimé et théoriqe pour une phase 40°
figure;
semilogy(Echelle, TEB_ESTIME, 'r');
hold on;
semilogy(Echelle, TEB_THEORIQUE, 'b');
semilogy(Echelle, TEB_ESTIME, 'r*');
legend('TEB estimé', 'TEB théorique');
xlabel('Eb/N0 (dB)');
ylabel('TEB');
title('Comparaison entre le TEB estimé et le TEB théorique');
grid on;

% Tracés des taux d'erreur binaire estimés pour une phase 0° et 40°
figure;
semilogy(Echelle, TEB_ESTIME, 'r');
hold on;
semilogy(Echelle, TEB_PHASE_NULLE, 'b');
semilogy(Echelle, TEB_ESTIME, 'r*');
semilogy(Echelle, TEB_PHASE_NULLE, 'b*');
legend('TEB phase 40°', 'TEB phase 0°');
xlabel('Eb/N0 (dB)');
ylabel('TEB');
title('Comparaison entre le TEB 0° et le TEB 40°');
grid on;

% Tracés des taux d'erreur binaire estimés pour une phase 100° et 40°
figure;
semilogy(Echelle, TEB_ESTIME, 'r');
hold on;
semilogy(Echelle, TEB_THEORIQUE, 'y');
semilogy(Echelle, TEB_ESTIME_100, 'b');
semilogy(Echelle, TEB_THEORIQUE_100, 'g');
semilogy(Echelle, TEB_ESTIME_100, 'b*');
semilogy(Echelle, TEB_ESTIME, 'r*');
legend('TEB phase 40°', 'TEB théorique phase 40°', 'TEB phase 100°', 'TEB théorique phase 100°');
xlabel('Eb/N0 (dB)');
ylabel('TEB');
title('Comparaison entre le TEB 100° et le TEB 40°');
grid on;
