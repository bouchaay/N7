%{ 
Réalisation d un modem de fréquence selon la recommandation V21 de 
           l'Union Internationale des Télécommunications (UIT)
---------------------------------------------------------------------------
Ecole : INP-ENSEEIHT
Auteurs : Ayoub BOUCHAMA & Oussama ELGUERRAOUI
Promotion : 2022/2023
Groupe : F
Partie : Gestion d'une erreur de synchronisation de phase porteuse
---------------------------------------------------------------------------
%}

% Effacer toutes les variables et fermer toutes les figures
clear all;
close all;

% Définir les fréquences de la porteuse 0 et 1 en Hz
F0 = 1180;
F1 = 980;

% Définir la fréquence d'échantillonnage en Hz
Fe = 48000;

% Définir le débit du signal NRZ en bauds
Debit = 300;

% Calculer le nombre d'échantillons par bit
Ns = Fe/Debit;

% Définir le nombre de bits du signal NRZ
n_bit = 100;

% Calculer l'intervalle de temps entre chaque échantillon en secondes
Te = 1/Fe;

% Calculer l'intervalle de temps entre chaque bit en secondes
Ts = Ns*Te;

% Générer un vecteur de bits aléatoires de longueur n_bit
A = randi([0,1],1,n_bit);

% Etendre chaque bit sur Ns échantillons consécutifs
NRZ = kron(A,ones(1,Ns));

% Générer le vecteur des instants de temps
Temps = 0:Te:(length(NRZ)-1)*Te;

% Générer des phases aléatoires pour les porteuses 0 et 1
phi0 = rand*2*pi;
phi1 = rand*2*pi;
lambda0 = rand*2*pi;
lambda1 = rand*2*pi;

% Générer les porteuses 0 et 1
s0 = cos(2*pi*F0*Temps + phi0);
s1 = cos(2*pi*F1*Temps + phi1);

% Moduler le signal NRZ
x = (1-NRZ).*s0 + NRZ.*s1;

% Définir le rapport signal sur bruit en dB
SNR_DB = 50;

% Calculer la puissance du signal modulé
Px = mean(abs(x).^2);

% Calculer la puissance du bruit
Pb = Px / (10^(SNR_DB/10));

% Générer un vecteur de nombres aléatoires suivant une loi normale
n_echantillons = randn(1,length(x));

% Générer le signal du bruit gaussien
Bruit_Gaussien = sqrt(Pb)*n_echantillons;

% Ajouter le bruit au signal modulé
x_bruite = x + Bruit_Gaussien;

% Récupération des sous-matrices correspondant aux bits 0 et 1 à partir du signal reçu
x_s0 = reshape(x_bruite.*cos(2*pi*F0*Temps + lambda0),Ns,n_bit);
x_s1 = reshape(x_bruite.*cos(2*pi*F1*Temps + lambda1),Ns,n_bit);

% Calcul des intégrales des sous-matrices
int_x_s0 = trapz(x_s0);
int_x_s1 = trapz(x_s1);

% Calcul de la différence d'intégrales pour prendre la décision de bit
D_Sans_Gestion_Phase = int_x_s1 - int_x_s0;
Bits_retrouves_Sans_Gestion_Phase = D_Sans_Gestion_Phase > 0;

% Calcul de la différence entre les bits retrouvés et les bits envoyés (A)
Erreur_Sans_Gestion_Phase = xor(Bits_retrouves_Sans_Gestion_Phase,A);

% Calcul du taux d'erreur binaire
Taux_Erreur_Sans_Gestion_Phase = sum(Erreur_Sans_Gestion_Phase(:))/n_bit;
fprintf("Taux d'erreur binaire du signal sans gestion de phase : %.3f\n",Taux_Erreur_Sans_Gestion_Phase);

% Récupération des sous-matrices correspondant aux bits 0 et 1 à partir du signal reçu
x_cos0 = reshape(x_bruite.*cos(2*pi*F0*Temps + lambda0), Ns, n_bit);
x_cos1 = reshape(x_bruite.*cos(2*pi*F1*Temps + lambda1), Ns, n_bit);
x_sin0 = reshape(x_bruite.*sin(2*pi*F0*Temps + lambda0), Ns, n_bit);
x_sin1 = reshape(x_bruite.*sin(2*pi*F1*Temps + lambda1), Ns, n_bit);

% Calcul des intégrales des sous-matrices
int_xcos0 = trapz(x_cos0);
int_xcos1 = trapz(x_cos1);
int_xsin0 = trapz(x_sin0);
int_xsin1 = trapz(x_sin1);

% Calcul de la différence d'intégrales pour prendre la décision de bit
D_Gestion_Phase = (int_xcos1.^2 + int_xsin1.^2 ) - (int_xcos0.^2 + int_xsin0.^2);
Bits_Retrouves_Gestion_Phase = D_Gestion_Phase > 0;

% Calcul de la différence entre les bits retrouvés et les bits envoyés (A)
Erreur_Gestion_Phase = xor(Bits_Retrouves_Gestion_Phase, A);

% Calcul du taux d'erreur binaire
Taux_Erreur_Gestion_Phase = sum(Erreur_Gestion_Phase(:)) / n_bit;

% Affichage du taux d'erreur binaire
fprintf("Taux d'erreur binaire du signal avec gestion de phase : %.3f\n", Taux_Erreur_Gestion_Phase);