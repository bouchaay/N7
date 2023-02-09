% Etude de modulateurs bande de base

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
bits = randi([0 1],1,N); % Génération de N bits aléatoires
Ts = 1/Rs; % Période de symbole
Ns = Ts/Te; % Nombre d'échantillons par symbole

%% Modulateur 1 :

% Mapping
symboles1 = 2*bits-1;

% Sur-échantillonnage
signal_sur_echantillonne1 = kron(symboles1,[1, zeros(1,Ns-1)]);

% Vecteur temps
temps1 = 0:Te:(length(signal_sur_echantillonne1)-1)*Te;

% Filtrage
h1 = ones(1,Ns);
x1 = filter(h1,1,signal_sur_echantillonne1);

figure;
plot(temps1,x1);
title('Signal modulé');
xlabel('Temps (s)');
ylabel('Amplitude');
axis([0 N*Tb -1.5 1.5]);
grid on;

% Densité spectrale de puissance
figure;
% DSP = pwelch(x,[],[],[],Fe); (Marche mal)
NFFT1 = 2^nextpow2(length(x1));
DSP1 = abs(fft(x1,NFFT1)).^2;
f = linspace(-Fe/2,Fe/2,length(DSP1));
semilogy(f,fftshift(DSP1));
title('DSP du signal modulé');
xlabel('Fréquence (Hz)');
ylabel('DSP');
grid on;

% Tracés superposes sur une mˆeme figure de la DSP estim ́ee du signal g ́en ́er ́e et de sa DSP th ́eorique.
figure;
% DSP_th1 = (1/Tb)*rectpuls(f,Tb);
DSP_th1 = (Ts*(sin(pi*f*Ts/2)).^4)./((pi*f*Ts/2).^2);
semilogy(f,fftshift(DSP_th1));
hold on;
semilogy(f,fftshift(DSP1));
title('DSP du signal modulé');
xlabel('Fréquence (Hz)');
ylabel('DSP');
legend('DSP théorique','DSP estimée');
grid on;

%% Modulateur 2 :

% Les symboles
LesSymboles = [-3 -1 3 1];

% Mapping 4-aires
symboles2 = LesSymboles*(1+bi2de(reshape(bits,2,N/2).','left-msb'));

% Sur-échantillonnage
signal_sur_echantillonne2 = kron(symboles2,[1, zeros(1,Ns-1)]);

% Vecteur temps
temps2 = 0:Te:(length(signal_sur_echantillonne2)-1)*Te;

% Filtrage
h2 = ones(1,Ns);
x2 = filter(h2,1,signal_sur_echantillonne2);

figure;
plot(temps2,x2);
title('Signal modulé');
xlabel('Temps (s)');
ylabel('Amplitude');
axis([0 N*Tb -1.5 1.5]);
grid on;

% Densité spectrale de puissance
figure;
% DSP2 = pwelch(x2,[],[],[],Fe); (Marche mal)
NFFT2 = 2^nextpow2(length(x2));
DSP2 = abs(fft(x2,NFFT2)).^2;
f = linspace(-Fe/2,Fe/2,length(DSP2));
semilogy(f,fftshift(DSP2));
title('DSP du signal modulé');
xlabel('Fréquence (Hz)');
ylabel('DSP');
grid on;

% Tracés superposes sur une mˆeme figure de la DSP estim ́ee du signal g ́en ́er ́e et de sa DSP th ́eorique.
figure;
% DSP_th2 = (1/Tb)*rectpuls(f,Tb);
DSP_th2 = (Ts*(sin(pi*f*Ts/2)).^4)./((pi*f*Ts/2).^2);
semilogy(f,fftshift(DSP_th2));
hold on;
semilogy(f,fftshift(DSP2));
title('DSP du signal modulé');
xlabel('Fréquence (Hz)');
ylabel('DSP');
legend('DSP théorique','DSP estimée');
grid on;

%% Modulateur 3 :

% Facteur de suréchantillonnage
alpha = 0.5;

% Mapping
symboles3 = 2*bits - 1;

% Sur-échantillonnage
signal_sur_echantillonne3 = kron(symboles3,[1, zeros(1,Ns-1)]);

% Vecteur temps
temps3 = 0:Te:(length(signal_sur_echantillonne3)-1)*Te;

% Filtrage
h3 = rcosdesign(alpha,8,Ns,'sqrt');
x3 = filter(h3,1,signal_sur_echantillonne3);

figure;
plot(temps3,x3);
title('Signal modulé');
xlabel('Temps (s)');
ylabel('Amplitude');
axis([0 N*Tb -1.5 1.5]);
grid on;

% Densité spectrale de puissance
figure;
% DSP3 = pwelch(x3,[],[],[],Fe); (Marche mal)
NFFT3 = 2^nextpow2(length(x3));
DSP3 = abs(fft(x3,NFFT3)).^2;
f = linspace(-Fe/2,Fe/2,length(DSP3));
semilogy(f,fftshift(DSP));
title('DSP du signal modulé');
xlabel('Fréquence (Hz)');
ylabel('DSP');
grid on;

% Traces superposes sur une meme figure de la DSP estim ́ee du signal g ́en ́er ́e et de sa DSP th ́eorique.
F_limite_1 = (1-alpha)/2*Ts;
F_limite_2 = (1+alpha)/2*Ts;
DSP_th = zeros(1,length(f));
variance_symbole_emis = 1;
DSP_th(abs(f) <= F_limite_1) = variance_symbole_emis;
DSP_th(F_limite_1 <= abs(f) & abs(f) <= F_limite_2) = variance_symbole_emis/2 * (1 + cos(pi*Ts/alpha*abs(f(F_limite_1 <= abs(f) & abs(f) <= F_limite_2))) - F_limite_1);
figure;
semilogy(f,fftshift(DSP_th));
hold on;
semilogy(f,fftshift(DSP3));
title('DSP du signal modulé');
xlabel('Fréquence (Hz)');
ylabel('DSP');
legend('DSP théorique','DSP estimée');
grid on;

% Tracé superposée des DSP des différents signaux générées pour un meme débit binaire
semilogy(f,fftshift(DSP_th1));
hold on;
semilogy(f,fftshift(DSP_th2));
semilogy(f,fftshift(DSP_th3));
title('DSP du signal modulé');
xlabel('Fréquence (Hz)');
ylabel('DSP');
legend('DSP théorique 1','DSP théorique 2','DSP théorique 3');
grid on;





