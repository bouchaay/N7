% -------------------------------------------------------------------------
%                          Etude avec canal de propagation
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

% Canal de propagation
BW1 = 8000;
BW2 = 1000;

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

%% Etude du canal de propagation (BW1)

% Filtre passe-bas
N = 30;
k = -N:N;
f_norm = BW1/Fe;
hc = 2*f_norm*sinc(2*f_norm*k);

% Tracé de la réponse impulsionnelle du filtre passe-bas
Tc = 0:Te:(length(hc)-1)*Te;
figure;
plot(Tc, hc);
title('Réponse impulsionnelle du filtre passe-bas (BW1)');
xlabel('Temps (s)');
ylabel('Amplitude');
grid on;

% Reponse impulsionnelle du global de la chaine de transmission
g1 = conv(conv(h, hc), hr);

% Tracé de la réponse impulsionnelle globale de la chaine de transmission
Tg1 = 0:Te:(length(g1)-1)*Te;
figure;
plot(Tg1, g1);
xlim([0 0.003])
title('Réponse impulsionnelle globale de la chaine de transmission (BW1)');
xlabel('Temps (s)');
ylabel('Amplitude');
grid on;

% Gestion du retard
x_padded = [x zeros(1, N)];
x_padded_inter = filter(hc, 1, x_padded);
x_padded_final = x_padded_inter(N+1 : end);
x_sans_delai = filter(hr, 1, x_padded_final);

% Diagramme de l'oeil
Oeil1 = reshape(x_sans_delai(Ns+1:end), Ns, length(x_sans_delai(Ns+1:end))/Ns);
figure;
plot(Oeil1);
title('Diagramme de l''oeil (BW1)');
xlabel('Temps (s)');
ylabel('Amplitude');
grid on;

% Tracé, sur une meme figure, de |H(f)Hr(f)| et de |Hc(f)|
p = 8;
H = fftshift(fft(h, 2^p));
Hr = fftshift(fft(hr, 2^p));
Hc = fftshift(fft(hc, 2^p));
figure;
plot(abs(H.*Hr)/max(abs(H.*Hr)));
hold on;
plot(abs(Hc)/max(abs(Hc)));
xlim([0 256])
title('Comparaison de |H(f)Hr(f)| et de |Hc(f)| (BW1)');
xlabel('Fréquence (Hz)');
ylabel('Amplitude');
legend('|H(f)Hr(f)|', '|Hc(f)|');
grid on;

% Echantillonnage
y_echantillonne3 = x_sans_delai(Ns:Ns:end);

% Decision
map_estime3 = sign(y_echantillonne3) ;

% Demapping
bits_ditecte3 = (map_estime3 > 0);

% Taux d'erreur binaire
TEB3 = sum(bits_ditecte3 ~= bits)/N;
fprintf('TEB pour BW1 = 8000 Hz est :  %f\n', TEB3);

%% Etude du canal de propagation (BW2)

% Filtre passe-bas
N = 30;
k = -N:N;
f_norm = BW2/Fe;
hc = 2*f_norm*sinc(2*f_norm*k);

% Tracé de la réponse impulsionnelle du filtre passe-bas
Tc = 0:Te:(length(hc)-1)*Te;
figure;
plot(Tc, hc);
title('Réponse impulsionnelle du filtre passe-bas (BW2)');
xlabel('Temps (s)');
ylabel('Amplitude');
grid on;

% Reponse impulsionnelle du global de la chaine de transmission
g2 = conv(conv(h, hc), hr);

% Tracé de la réponse impulsionnelle globale de la chaine de transmission
Tg2 = 0:Te:(length(g2)-1)*Te;
figure;
plot(Tg2, g2);
xlim([0 0.003])
title('Réponse impulsionnelle globale de la chaine de transmission (BW2)');
xlabel('Temps (s)');
ylabel('Amplitude');
grid on;

% Gestion du retard
x_padded = [x zeros(1, N)];
x_padded_inter = filter(hc, 1, x_padded);
x_padded_final = x_padded_inter(N+1 : end);
x_sans_delai = filter(hr, 1, x_padded_final);

% Diagramme de l'oeil
Oeil2 = reshape(x_sans_delai(Ns+1:end), Ns, length(x_sans_delai(Ns+1:end))/Ns);
figure;
plot(Oeil2);
title('Diagramme de l''oeil (BW2)');
xlabel('Temps (s)');
ylabel('Amplitude');
grid on;

% Tracé, sur une meme figure, de |H(f)Hr(f)| et de |Hc(f)|
p = 8;
H = fftshift(fft(h, 2^p));
Hr = fftshift(fft(hr, 2^p));
Hc = fftshift(fft(hc, 2^p));
figure;
plot(abs(H.*Hr)/max(abs(H.*Hr)));
axis = [0 60 -5 70];
hold on;
plot(abs(Hc)/max(abs(Hc)));
xlim([0 256])
title('Comparaison de |H(f)Hr(f)| et de |Hc(f)| (BW2)');
xlabel('Fréquence (Hz)');
ylabel('Amplitude');
legend('|H(f)Hr(f)|', '|Hc(f)|');
grid on;

% Echantillonnage
y_echantillonne4 = x_sans_delai(Ns:Ns:end);

% Decision
map_estime4 = sign(y_echantillonne4);

% Demapping
bits_ditecte4 = map_estime3 > 0;

% Taux d'erreur binaire
TEB4 = sum(bits_ditecte4 ~= bits)/N;
fprintf('TEB pour BW2 = 1000 Hz est :  %f\n', TEB4);
