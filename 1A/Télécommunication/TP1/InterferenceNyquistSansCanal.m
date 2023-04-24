% -------------------------------------------------------------------------
%                          Etude sans canal de propagation
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
N = 1000; % Nombre de bits
Rs = 1/Tb; % Fréquence de symbole
Ts = 1/Rs; % Période de symbole
Ns = Ts/Te; % Nombre d'échantillons par symbole
k = 1; % Nombre de bits par symbole
M = 2^k; % Ordre de modulation

% Generation de N bits aleatoires
bits = randi([0 1],1,N);

% Mapping
Symboles = [-1, 1];
Map = 2*bits - 1;

% Sur-échantillonnage
signal_sur_echantillonne = kron(Map ,[1 zeros(1,Ns-1)]);

% Filtre de mise en forme
h = ones(1, Ns);

% Modulation
x = filter(h, 1, signal_sur_echantillonne);

% Tracé du signal modulé sans canal de propagation
T = 0:Te:(length(x)-1)*Te;
figure;
plot(T, x);
axis([0 0.015 -1.5 1.5]);
title('Signal modulé sans canal de propagation');
xlabel('Temps (s)');
ylabel('Amplitude');
grid on;

% Filtre de réception
hr = ones(1, Ns);

% Réception
y = filter(hr, 1, x);

% Reponse impulsionnelle globale de la chaine de transmission
g = conv(h, hr);

% Tracé de la réponse impulsionnelle globale de la chaine de transmission
Tg = Te:Te:(length(g))*Te;
figure;
plot(Tg, g);
title('Réponse impulsionnelle globale de la chaine de transmission');
xlabel('Temps (s)');
ylabel('Amplitude');
grid on;

% Diagramme de l'oeil
Oeil = reshape(y(Ns+1:end), Ns, length(y(Ns+1:end))/Ns);
figure;
plot(Oeil);
title('Diagramme de l oeil');
xlabel('Temps (s)');
ylabel('Amplitude');
grid on;

% Echantillonnage
y_echantillonne1 = y(Ns:Ns:end);
y_echantillonne2 = y(3:Ns:end);

% Decision
map_estime1 = sign(y_echantillonne1);
map_estime2 = sign(y_echantillonne2);

% Demapping
bits_ditecte1 = map_estime1 > 0;
bits_ditecte2 = map_estime2 > 0;

% Taux d'erreur binaire
TEB1 = sum(bits_ditecte1 ~= bits)/N;
fprintf('TEB pour n0 = Ns est :  %f\n', TEB1);
TEB2 = sum(bits_ditecte2 ~= bits)/N;
fprintf('TEB pour n0 = 3 est :  %f\n', TEB2);
