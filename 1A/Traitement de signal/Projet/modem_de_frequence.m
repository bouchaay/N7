%{ 
Réalisation d un modem de fréquence selon la recommandation V21 de 
           l'Union Internationale des Télécommunications (UIT)
---------------------------------------------------------------------------
Ecole : INP-ENSEEIHT
Auteurs : Ayoub BOUCHAMA & Oussama ELGUERRAOUI
Promotion : 2022/2023
Groupe : F
Partie : Modem de fréquence
---------------------------------------------------------------------------
%}

clear all % efface toutes les variables du workspace
close all % ferme toutes les figures ouvertes

F0 = 6000; %Hz, fréquence de la porteuse 0 (1180 pour V21)
F1 = 2000; %Hz, fréquence de la porteuse 1 (980 pour V21)
Fe = 48000; % Hz, fréquence d'échantillonnage
Debit = 300; % bauds, débit du signal NRZ
Ns = Fe/Debit; % nombre d'échantillons par bit
n_bit = 100; % nombre de bits du signal NRZ
Te = 1/Fe; % s, intervalle de temps entre chaque échantillon
Ts = Ns*Te; % s, intervalle de temps entre chaque bit

%% Génération et affichage du signal NRZ

% Génération du signal NRZ
A = randi([0,1],1,n_bit); % génère un vecteur de bits aléatoires de longueur n_bit
NRZ = kron(A,ones(1,Ns)); % étend chaque bit sur Ns échantillons consécutifs
Temps_1 = 0:Te:(length(NRZ)-1)*Te; % vecteur des instants de temps
% Affichage du signal NRZ dans une échelle temporelle
figure(1)
plot(Temps_1,NRZ,'k')
grid minor
axis([0 0.35 -0.2 1.2]);
xlabel("Temps en s")
ylabel("Amplitude en V")
title('Tracé du signal NRZ')

% Calcul et affichage de la densité spectrale du signal NRZ
NRZ_T = fft(NRZ); % transformée de Fourier du signal NRZ
% DSP_NRZ = pwelch(NRZ,[],[],[],Fe,"two-sided"); % densité spectrale du signal NRZ
DSP_NRZ =(1/Ns)*abs(NRZ_T).^2;
Echelle_Freq_1 = linspace(-Fe/2, Fe/2, length(DSP_NRZ)); % vecteur des fréquences du spectre du signal NRZ
figure(2)
subplot(211)
semilogy(Echelle_Freq_1, fftshift(abs(DSP_NRZ))) % affiche la densité spectrale du signal NRZ sur une échelle logarithmique
grid minor
xlabel("Frequence en Hz")
ylabel("Amplitude en 'V'")
title('Tracé de la densite spectrale de NRZ')

% Calcul et affichage de la densité spectrale du filtre ideal NRZ

S_NRZ = (1/4)*Ts*(sinc(pi*Echelle_Freq_1*Ts)).^2 + 1/4*dirac1(Echelle_Freq_1); % densité spectrale du filtre idéal NRZ
subplot(212)
semilogy(Echelle_Freq_1, fftshift(abs(DSP_NRZ)),"r") % affiche la densité spectrale du signal NRZ sur une échelle logarithmique
grid minor
xlabel("Frequence en Hz")
ylabel("Amplitude en V")
title('Tracé de la densite spectrale ideal de NRZ')
hold on % maintient la figure 2 active pour ajouter des courbes
semilogy(Echelle_Freq_1, abs(S_NRZ),"b") % affiche la densité spectrale du filtre idéal NRZ sur une échelle logarithmique
legend("Tracé de la desnité spectrale de NRZ","Tracé de la densité spectrale du siganl NRZ idéal")
%% Modulation du signal NRZ

% Génération des porteuses
phi0 = rand*2*pi; % phase aléatoire de la porteuse 0
phi1 = rand*2*pi; % phase aléatoire de la porteuse 1
s0 = cos(2*pi*F0*Temps_1 + phi0); % porteuse 0
s1 = cos(2*pi*F1*Temps_1 + phi1); % porteuse 1

% Modulation du signal NRZ
x = (1-NRZ).*s0 + NRZ.*s1; % signal modulé

% Affichage du signal modulé dans une échelle temporelle
figure(3)
subplot(211)
plot(Temps_1,x)
grid minor
axis([0 0.1 -1.2 1.2]);
xlabel("Temps en s")
ylabel("Amplitude en V")
title('Tracé du signal x')

% Calcul et affichage de la densité spectrale du signal modulé
X = fft(x); % transformée de Fourier du signal modulé
DSP_x = (1/Ns)*abs(X).^2; % densité spectrale du signal modulé
% DSP_x = pwelch(x,[],[],[],Fe,"two-sided")
Echelle_Freq_1 = linspace(-Fe/2, Fe/2, length(DSP_x)); % vecteur des fréquences du spectre du signal modulé
subplot(212)
semilogy(Echelle_Freq_1, fftshift(abs(DSP_x))) % affiche la densité spectrale du signal modulé sur une échelle logarithmique
grid minor
xlabel("Frequence en Hz")
ylabel("Amplitude")
title('Densite spectrale de x')

%% Ajout du bruit au signal modulé
SNR_DB = 50; % rapport signal sur bruit en dB
Px = mean(abs(x).^2); % puissance du signal modulé
Pb = Px / (10^(SNR_DB/10)); % puissance du bruit
n_echantillons = randn(1,length(x)); % génère un vecteur de nombres aléatoires suivant une loi normale
Bruit_Gaussien = sqrt(Pb)*n_echantillons; % signal du bruit gaussien
x_bruite = x + Bruit_Gaussien; % nouveau signal

% Affichage du signal bruité dans une échelle temporelle
figure(4)
subplot(211)
plot(Temps_1,x_bruite)
grid minor
axis([0 0.1 -1.2 1.2]);
xlabel("Temps en s")
ylabel("Amplitude en V")
title('Tracé du signal x bruité')

% Calcul et affichage de la densité spectrale du signal bruité
X_BRUITEE = fft(x_bruite); % transformée de Fourier du signal bruité
DSP_x_bruitee = (1/Ns)*abs(X_BRUITEE).^2; % densité spectrale du signal bruité
% DSP_x_bruitee = pwelch(x_bruitee,[],[],[],Fe,"two-sided")
Echelle_Freq_1 = linspace(-Fe/2, Fe/2, length(DSP_x_bruitee)); % vecteur des fréquences du spectre du signal bruité
subplot(212)
semilogy(Echelle_Freq_1, fftshift(abs(DSP_x_bruitee))) % affiche la densité spectrale du signal bruité sur une échelle logarithmique
grid minor
xlabel("Frequence en 'Hz'")
ylabel("Amplitude en 'V'")
title('Tracé de la densité spectrale du signal x bruité')

%% Réponse impulsionnelle et en fréquence du filtre passe bas
Fc = (F0+F1)/2; % Fréquence centrale
N = 30; % Nombre de coefficients de filtrage
% N = 100
k = -N : N; % Vecteur des indices
f_normal = Fc/Fe; % Fréquence normalisée
h_filtre_PB = 2*f_normal*sinc(2*f_normal*k); % Réponse impulsionnelle du filtre passe bas

% Tracé de la réponse impulsionnelle du filtre passe bas
figure(5)
subplot(211)
plot(k, h_filtre_PB)
grid minor
xlabel('temps en s')
ylabel('Morceau de la trace impulsionnelle idéale')
title('Réponse impulsionnelle du filtre passe bas')

% Tracé de la réponse en fréquence du filtre passe bas
fft_h_filtre_PB = fftshift(fft(h_filtre_PB));
FPB = linspace(-Fe/2,Fe/2,length(fft_h_filtre_PB));
subplot(212)
semilogy(FPB, abs(fft_h_filtre_PB))
grid minor
title('Tracé de la réponse en fréquence du filtre PB')
xlabel('Fréquences en Hz ')
ylabel('Réponse en fréquence')

%% Réponse impulsionnelle et en fréquence du filtre passe haut

h_filtre_PH = dirac1(k*Te) - h_filtre_PB; % Réponse impulsionnelle du filtre passe haut

% Tracé de la réponse impulsionnelle du filtre passe haut
figure(6)
subplot(211)
plot(k, h_filtre_PH)
grid minor
title('Tracé de la réponse impulsionnelle du fitre PH')
xlabel('temps en s ')
ylabel('Réponse impulsionnelle')

% Tracé de la réponse en fréquence du filtre passe haut
fft_h_filtre_PH = fftshift(fft(h_filtre_PH));
subplot(212)
semilogy(FPB, abs(fft_h_filtre_PH))
grid on
xlabel('Fréquences en Hz ')
ylabel('Réponse en fréquence')
title('Tracé de la réponse en fréquence du filtre PH')

% Filtrage du signal x avec les filtres passe bas et passe haut
signal_filtre_PB = filter(h_filtre_PB,1,x_bruite); % Filtre passe bas
signal_filtre_PH = filter(h_filtre_PH,1,x_bruite); % Filtre passe haut
Temps_2 = 0 : Te : (length(signal_filtre_PB) - 1)*Te; % Vecteur des temps

% Tracé du signal x après filtrage passe bas
figure(7)
subplot(211)
plot(Temps_2, signal_filtre_PB)
grid minor
axis([0 0.1 -1.2 1.2]);
xlabel('Temps en s')
ylabel('Signal x bruité après filtrage')
title('Tracé du signal x bruité après filtrage passe bas')

% Tracé du signal x après filtrage passe haut
subplot(212)
plot(Temps_2, signal_filtre_PH)
grid minor
axis([0 0.1 -1.2 1.2]);
xlabel('Temps en s')
ylabel('Signal x bruité après filtrage')
title('Tracé du signal x bruité après filtrage passe haut')

% Tracé de la transformée de Fourier du signal x après filtrage passe bas et passe haut
fft_signal_filtre_PH = fft(signal_filtre_PH);
fft_signal_filtre_PB = fft(signal_filtre_PB);
Echelle_Freq_2 = 0 : Fe : (length(signal_filtre_PH)-1)*Fe;

% Tracé de la transformée de Fourier du signal x après filtrage passe bas
figure(8)
subplot(211)
semilogy(Echelle_Freq_2, abs(fft_signal_filtre_PB))
grid on
xlabel('frequence en HZ')
ylabel('Signal x après filtrage')
title('Tracé du signal x après filtrage passe bas')

% Tracé de la transformée de Fourier du signal x après filtrage passe haut
subplot(212)
semilogy(Echelle_Freq_2, abs(fft_signal_filtre_PH))
grid on
xlabel('frequence en Hz')
ylabel('Signal x bruité après filtrage')
title('Tracé du signal x bruité après filtrage passe haut')

%% Détection d'énergie

% Pour l'ordre 61.
X = reshape(signal_filtre_PB, Ns, n_bit);
Energie = sum(X.^2);
K_1 = (min(Energie) + max(Energie))/2; % seuil deteminé
bits_PB = (Energie > K_1); % bits détectés à partir du signal filtré passe-bas
D = xor(bits_PB,A);
Error_Bits = sum(D(:))/n_bit; % Calcul du taux d'erreur binaire (BER)
% Affichage des résultats
fprintf("Taux d'erreur binaire du signal filtré passe-bas à l'ordre 61 : %.3f\n", Error_Bits)

%{ 
Pour l'ordre 201 -------------------------------------------------------

*** Gérer le retard avec la technique du zero padding ***
Signal_NR = filter(h_filtre_PB, 1, [x_bruitee, zeros(1, N)]);
Signal_NR_fixe = Signal_NR(1, N+1:end);
Signal_NR_fixe_reshape = reshape(Signal_NR_fixe,Ns,n_bit);

*** Détection d'energie ***
Energie_NR = sum(Signal_NR_fixe_reshape.^2);
K_NR = (max(Energie_NR)+min(Energie_NR))/2;
bits_PB_NR = Energie_NR > K_NR;
D_NR = xor(bits_PB_NR,A);
Error_Bits_NR = sum(D_NR(:))/n_bit;
fprintf("Taux d'erreur binaire du signal filtré passe-bas à l'ordre 201 : %.3f\n", Error_Bits_NR)
%}







