%{ 
Réalisation d un modem de fréquence selon la recommandation V21 de 
           l'Union Internationale des Télécommunications (UIT)
---------------------------------------------------------------------------
Ecole : INP-ENSEEIHT
Auteurs : Ayoub BOUCHAMA & Oussama ELGUERRAOUI
Promotion : 2022/2023
Groupe : F
Partie : Modem adapté aux normes V21
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

%% Démoduler le signal en utilisant la norme V21

% Calculer les intégrales du récepteur en utilisant la synchronisation idéale
f1 = @(Temps) cos((2*pi*F0).*Temps + phi0).^2 ;
int_f1 = integral(f1,0,Ts);
%= 0.0016

f2 = @(Temps)  cos((2*pi*F1).*Temps + phi1).^2 ;
int_f2 = integral(f2,0,Ts);
%= 0.0017

f3 = @(Temps) cos((2*pi*F0).*Temps + phi0).*cos((2*pi*F1).*Temps + phi1);
int_f3 = integral(f3,0,Ts);
%= -4.3447e-04

% Appliquer l'implantation du démodulateur avec bruit
x_br0 = reshape(x_bruite.*cos(2*pi*F0*Temps + phi0),Ns,n_bit);
x_br1 = reshape(x_bruite.*cos(2*pi*F1*Temps + phi1),Ns,n_bit);

int_xbr0 = trapz(x_br0);
int_xbr1 = trapz(x_br1);

D_bruit = int_xbr1 - int_xbr0;
Bits_Retrouves_Bruit = D_bruit > 0;

% Calculer le taux d'erreur avec bruit
Erreur_Bruit = xor(Bits_Retrouves_Bruit, A);
Taux_Erreur_Bruit = sum(Erreur_Bruit(:)) / n_bit;
fprintf("Taux d'erreur binaire du signal bruité : %.3f\n", Taux_Erreur_Bruit);

% Appliquer l'implantation du démodulateur sans bruit
x0 = reshape(x.*cos(2*pi*F0*Temps + phi0),Ns,n_bit);
x1 = reshape(x.*cos(2*pi*F1*Temps + phi1),Ns,n_bit);

int_x0 = trapz(x0); 
int_x1 = trapz(x1);

D_Sans_Bruit = int_xbr1 - int_xbr0;
Bits_Retrouves_Sans_Bruit = D_Sans_Bruit > 0;

Erreur_Sans_Bruit = xor(Bits_Retrouves_Sans_Bruit, A);
Taux_Erreur_Sans_Bruit = sum(Erreur_Sans_Bruit(:)) / n_bit;

fprintf("Taux d'erreur binaire du signal sans bruit : %.3f\n",Taux_Erreur_Sans_Bruit);