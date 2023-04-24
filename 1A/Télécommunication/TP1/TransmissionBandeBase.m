% -------------------------------------------------------------------------
%                       Etude des modulateurs bande de base
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
N = 10000; % Nombre de bits

% Generation de N bits aleatoires
bits = randi([0 1],1,N);

%% Premier modulateur

k1 = 1; % Nombre de bits par symbole
M1 = 2^k1; % Ordre de modulation
Rs1 = Rb/log2(M1); % Débit symbole
Ts1 = 1/Rs1; % Période symbole
Ns1 = Ts1/Te; % Nombre symbole

% Mapping
Symboles1 = [-1, 1];
Map1 = 2*bits - 1;

% Sur-échantillonnage
signal_sur_echantillonne1 = kron(Map1 ,[1 zeros(1,Ns1-1)]);

% Filtre de mise en forme
hm1 = ones(1, Ns1);

% Modulation
x1 = filter(hm1, 1, signal_sur_echantillonne1);

%% Deuxieme modulateur

k2 = 2; % Nombre de bits par symbole
M2 = 2^k2; % Ordre de modulation
Rs2 = Rb/log2(M2); % Débit symbole
Ts2 = 1/Rs2; % Période symbole
Ns2 = Ts2/Te; % Nombre symbole

% Mapping
Symboles2 = [-3, -1, 1, 3];
Map2 = (2*bit2int(bits',2) - 3)';

% Sur-échantillonnage
signal_sur_echantillonne2 = kron(Map2 ,[1 zeros(1,Ns2-1)]);

% Filtre de mise en forme
hm2 = ones(1, Ns2);

% Modulation
x2 = filter(hm2, 1, signal_sur_echantillonne2);

%% Troisieme modulateur

k3 = 1; % Nombre de bits par symbole
M3 = 2^k3; % Ordre de modulation
Rs3 = Rb/log2(M3); % Débit symbole
Ts3 = 1/Rs3; % Période symbole
Ns3 = Ts3/Te; % Nombre symbole

% Roll-Off
alpha = 0.5;

% Mapping
Symboles3 = [-1, 1];
Map3 = 2*bits - 1;

% Sur-échantillonnage
signal_sur_echantillonne3 = kron(Map3 ,[1 zeros(1,Ns3-1)]);

% Filtre de mise en forme
hm3 = rcosdesign(alpha, length(signal_sur_echantillonne1)/Ns3, Ns3, 'sqrt');

% Modulation
decalage = length(signal_sur_echantillonne3)/2;
x3_filtre = filter(hm3, 1, [signal_sur_echantillonne3, zeros(1,decalage)]);
x3 = x3_filtre(decalage+1:end);

%% Les Densités Spectrales de Puissance Estimées (DSP)

DSP1 = pwelch(x1, [], [], [], Fe, 'twosided');
DSP2 = pwelch(x2, [], [], [], Fe, 'twosided');
DSP3 = pwelch(x3, [], [], [], Fe, 'twosided');

%% Les Densités Spectrales de Puissance Théoriques (DSPT)

% Vecteur de fréquence
f = linspace(-Fe/2, Fe/2, length(DSP1));

% Fréquence limite du 3ème modulateur
F_limite_1 = (1-alpha)/(2*Ts3);
F_limite_2 = (1+alpha)/(2*Ts3);

% DSP théorique du premier modulateur
DSPTH1 = var(Symboles1)*Ts1*sinc(f*Ts1).^2;

% DSP théorique du deuxieme modulateur
DSPTH2 = var(Symboles2)*Ts2*sinc(f*Ts2).^2;

% DSP théorique du troisieme modulateur
DSP_FUNCTION = @(f) (1/Ts3)*(Ts3*(abs(f)<=F_limite_1) + (Ts3/2)*(1+cos( ((pi*Ts3)/alpha)*(abs(f) - F_limite_1) )).*(F_limite_1<=abs(f) & abs(f)<=F_limite_2));
DSPTH3 = (1/Ts3)*DSP_FUNCTION(f);

%% Les tracés des signaux modulé

% Vecteur temps
T = 0:Te:(length(x1)-1)*Te;

% Signal du premier modulateur
figure;
plot(T, x1);
title('Premier modulateur');
xlabel('Temps (s)');
ylabel('Amplitude');
axis([0 0.05 -1.5 1.5]);
grid on;

% Signal du deuxieme modulateur
figure;
plot(T, x2);
title('Deuxieme modulateur');
xlabel('Temps (s)');
ylabel('Amplitude');
axis([0 0.1 -4 4]);
grid on;

% Signal du troisieme modulateur
figure;
plot(T, x3);
title('Troisieme modulateur');
xlabel('Temps (s)');
ylabel('Amplitude');
axis([0 0.02 -1.5 1.5]);
grid on;

%% Les tracés des DSP

% Densité spectrale de puissance du premier modulateur
figure;
semilogy(f, fftshift(abs(DSP1)/max(DSP1)));
title('DSP du premier modulateur');
xlabel('Fréquence (Hz)');
ylabel('DSP');
grid on;

% Densitée spectrale de puissance du deuxième modulateur
figure
semilogy(f, fftshift(abs(DSP2)/max(DSP2)));
title('DSP du deuxieme modulateur');
xlabel('Fréquence (Hz)');
ylabel('DSP');
grid on;

% Densité spectrale de puissance du troisième modulateur
figure;
semilogy(f, fftshift(abs(DSP3)/max(DSP3)));
title('DSP du troisieme modulateur');
xlabel('Fréquence (Hz)');
ylabel('DSP');
grid on;

%% Les tracés des DSP et DSPT sur le même graphique

% Densité spectrale de puissance estimée et théorique du premier modulateur
figure;
semilogy(f, fftshift(abs(DSP1)/max(DSP1)), 'r');
hold on;
semilogy(f, DSPTH1/max(DSPTH1), 'b');
title('DSP et DSPT du premier modulateur');
xlabel('Fréquence (Hz)');
ylabel('DSP');
legend('DSP', 'DSPT');
grid on;

% Densité spectrale de puissance estimée et théorique du deuxième modulateur
figure;
semilogy(f, fftshift(abs(DSP2)/max(DSP2)), 'r');
hold on;
semilogy(f, DSPTH2/max(DSPTH2), 'b');
title('DSP et DSPT du deuxieme modulateur');
xlabel('Fréquence (Hz)');
ylabel('DSP');
legend('DSP', 'DSPT');
grid on;

% Densité spectrale de puissance estimée et théorique du troisième modulateur
figure;
semilogy(f, fftshift(abs(DSP3))/max(DSP3), 'r');
hold on;
semilogy(f, DSPTH3/max(DSPTH3), 'b');
title('DSP et DSPT du troisieme modulateur');
xlabel('Fréquence (Hz)');
ylabel('DSP');
legend('DSP', 'DSPT');
grid on;

% Les tracés des DSP Estimées sur la meme figure
figure;
semilogy(f, fftshift(abs(DSP1)/max(DSP1)), 'r');
hold on;
semilogy(f, fftshift(abs(DSP2)/max(DSP2)), 'b');
semilogy(f, fftshift(abs(DSP3)/max(DSP3)), 'g');
title('DSP des trois modulateurs');
xlabel('Fréquence (Hz)');
ylabel('DSP');
legend('Premier modulateur', 'Deuxieme modulateur', 'Troisieme modulateur');
grid on;
