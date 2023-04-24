%{ 
Réalisation d un modem de fréquence selon la recommandation V21 de 
           l'Union Internationale des Télécommunications (UIT)
---------------------------------------------------------------------------
Ecole : INP-ENSEEIHT
Auteurs : Ayoub BOUCHAMA & Oussama ELGUERRAOUI
Promotion : 2022/2023
Groupe : F
Partie : Fonction démoduler
---------------------------------------------------------------------------
%}

function signal_demoduler = demoduler(signal)

F0 = 1180; % Hz, fréquence de la porteuse 0
F1 = 980; % Hz, fréquence de la porteuse 1
Fe = 48000; % Hz, fréquence d'échantillonnage
Te = 1/Fe; % s, intervalle de temps entre chaque échantillon
Debit = 300; % bauds, débit du signal 
Ns = Fe/Debit; % nombre d'échantillons par bit
n_bit = length(signal)/Ns; % nombre de bits du signal 

Temps = 0:Te:(length(signal)-1)*Te; % vecteur des instants de temps

theta0 = rand*2*pi;
theta1 = rand*2*pi;

x_cos0 = reshape(signal.*cos(2*pi*F0*Temps + theta0),Ns,n_bit);
x_cos1 = reshape(signal.*cos(2*pi*F1*Temps + theta1),Ns,n_bit);
x_sin0 = reshape(signal.*sin(2*pi*F0*Temps + theta0),Ns,n_bit);
x_sin1 = reshape(signal.*sin(2*pi*F1*Temps + theta1),Ns,n_bit);

int_xcos0 = trapz(x_cos0);
int_xcos1 = trapz(x_cos1);
int_xsin0 = trapz(x_sin0);
int_xsin1 = trapz(x_sin1);

H = (int_xcos1.^2 + int_xsin1.^2 ) - (int_xcos0.^2 + int_xsin0.^2);

Bits_retrouves = H > 0;


signal_demoduler = Bits_retrouves;
end