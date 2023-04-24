% -------------------------------------------------------------------------
%                       Deuxième chaine de transmission
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
k = 1; % Nombre de bits par symbole
M = 2^k; % Ordre de modulation

%% Modulation

% Génération de N bits aléatoires
bits = randi([0 1],1,N);

% Mapping
Symboles = [-1, 1];
Map = 2*bits - 1;

% Sur-échantillonnage
signal_sur_echantillonne = kron(Map ,[1 zeros(1,Ns-1)]);

% Filtre de mise en forme
hm1 = ones(1, Ns);
x = filter(hm1, 1, signal_sur_echantillonne);

%% Demodulation sans bruit

% Filtre de réception

hr1 = [ones(1, Ns/2) zeros(1,Ns/2)];
z1 = filter(hr1, 1, x);


% Echantillonnage
z1_echant = z1(Ns:Ns:end);

% Décision
map_estimes1 = z1_echant/(Ns/2);

% Demapping
bits_ditecte1 = map_estimes1 > 0;

% Taux d'erreur binaire
TEB1 = sum(bits_ditecte1 ~= bits)/N;
fprintf('TEB sans bruit = %f\n', TEB1);

% Taux d'erreur symbole
TES1 = sum(map_estimes1 ~= Map)/N;
fprintf('TES sans bruit = %f', TES1);

% Diagramme de l'oeil
figure(2);
Oeil_Sans_Bruit = reshape(z1(Ns+1:end), Ns, length(z1(Ns+1:end))/Ns);
plot(Oeil_Sans_Bruit);
grid on;
title('Diagramme de l''oeil sans bruit');

%% Ajout du bruit

% Puissance du signal
Ps = mean(x.^2);

for i = 0:8
    % Eb/N0 en dB
    EbN0dB = 10^(i/10);

    % Calcul de la puissance du bruit
    sigma2 = (Ns*Ps)./(2*log2(M)*EbN0dB);

    % Bruit gaussien
    bruit = sqrt(sigma2)*randn(1, length(x));

    % Signal bruité
    r = x + bruit;

    % Filtre de réception
    hr2 = hr1 ;
    z2 = filter(hr2, 1, r);

    % Echantillonnage
    z2_echant = z2(Ns:Ns:end);

    % Décision
    map_estimes2 = z2_echant/Ns;

    % Demapping
    bits_ditecte2 = map_estimes2 > 0;
    map_detectes2 = sign(map_estimes2);

    % Taux d'erreur binaire
    TEB_EbN0dB(i+1) = sum(bits_ditecte2 ~= bits)/N;

    % Taux d'erreur binaire théorique
    TEB_theorique(i+1) = qfunc(sqrt(2*EbN0dB));

    % Taux d'erreur symbole
    TES2(i+1) = sum(map_detectes2~= Map)/N;

    % Taux d'erreur symbole théorique
    TES_theorique(i+1) = TEB_theorique(i+1);

    % Diagramme de l'oeil avec bruit
    figure;
    Oeil_Avec_Bruit = reshape(z2(Ns+1:end), Ns, length(z2(Ns+1:end))/Ns);
    plot(Oeil_Avec_Bruit);
    title(['Diagramme de l''oeil avec bruit pour i = ', num2str(i)]);
    grid on;

end

% Tracé du TEB Estimé en fonction de Eb/N0
figure;
semilogy(0:8, TEB_EbN0dB, 'b')
xlabel('Eb/N0 (dB)');
ylabel('TEB');
grid on;
title('TEB estimé en fonction de Eb/N0');

% Tracé du TEB et du TEB théorique sur la même figure en fonction de Eb/N0
figure;
semilogy(0:8, TEB_EbN0dB, 'b');
hold on;
semilogy(0:8, TEB_theorique, 'r');
xlabel('Eb/N0 (dB)');
ylabel('TEB');
legend('TEB', 'TEB théorique');
grid on;
title('TEB et TEB théorique en fonction de Eb/N0');

% Tracé du TES Estimé en fonction de Eb/N0
figure;
semilogy(0:8, TES2, 'b')
xlabel('Eb/N0 (dB)');
ylabel('TES');
grid on;
title('TES estimé en fonction de Eb/N0');

% Tracé du TES et du TES théorique sur la même figure en fonction de Eb/N0
figure;
semilogy(0:8, TES2, 'b');
hold on;
semilogy(0:8, TES_theorique, 'r');
xlabel('Eb/N0 (dB)');
ylabel('TES');
legend('TES', 'TES théorique');
grid on;
title('TES et TES théorique en fonction de Eb/N0');


