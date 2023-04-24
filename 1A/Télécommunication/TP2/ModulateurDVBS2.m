close all;
clc;
clear;

% Les paramètres

Fe = 24000; % Fréquence d'échantillonnage
Rb = 3000; % Débit binaire
Tb = 1/Rb; % Période d'échantillonnage
Te = 1/Fe; % Période d'échantillonnage
N = 333333; % Nombre de bits
Rs = 1/Tb; % Fréquence de symbole
Ts = 1/Rs; % Période de symbole
Ns = Ts/Te; % Nombre d'échantillons par symbole
k = 3; % Nombre de bits par symbole
M = 2^k; % Ordre de modulation
alpha = 0.2; % Roll-off
fp = 2000; % Fréquence porteuse

%% Modulation 8-PSK

% Génération des bits
bits = randi([0 1],1,N);

% Mapping des bits
bits_res = reshape(bits, k, N/k);
l = bi2de(bits_res', 'left-msb')';
symboles = pskmod(l, M, pi/M);
scatterplot(symboles);

% Suréchantillonnage
symbolesSurEchantillones = kron(symboles, [1 zeros(1,Ns-1)]);

% Filtrage
L = 100;
h = rcosdesign(alpha, L, Ns, 'sqrt');

% Gestion du retard
ordre = L*Ns;
decalage = floor((ordre)/2);
xe_filtre = filter(h, 1, [symbolesSurEchantillones zeros(1, decalage)]);
xe = xe_filtre(decalage+1:end);

% Signal généré sur la voie en phase
I = real(xe);

% Signal généré sur la voie quadrature
Q = imag(xe);

%% Transposition de fréquence
Tx = Te:Te:length(xe)*Te;
x_intermediaire = xe.*exp(1i*2*pi*Tx.*fp);
x = real(x_intermediaire);

% Retour en base
T = Te: Te: Te*length(x);
xsin = x.*sin(2*pi*fp*T);
xcos = x.*cos(2*pi*fp*T);
y = xcos - 1i*xsin;

for j = 1:7
    % Puissance du signal
    Px = mean(x.^2);

    % Eb/N0
    EbN0 = 10^(j/10);

    % Calcul de la puissance du bruit
    sigma2 = (Ns*Px)./(2*log2(M)*EbN0);

    % Bruit gaussien
    bruit = sqrt(sigma2)*randn(1, length(x));

    % Signal bruité
    r = x + bruit;

    % retour en bande base
    T = Te: Te: Te*length(x);
    rsin = r.*sin(2*pi*fp*T);
    rcos = r.*cos(2*pi*fp*T);
    yr = rcos - 1i*rsin;

    % Filtre de réciption
    L = 100;
    hr = rcosdesign(alpha, L, Ns, 'sqrt');

    % Gestion du retard
    ordre = L*Ns;
    decalage = floor(ordre/2);
    z = filter(hr,1, [yr zeros(1, decalage)]);
    z_dec = z(decalage + 1: end);

    % Echantillonage 
    z_echan = z_dec(1:Ns:end);
    scatterplot(z_echan);

    % Demapping
    bits_estime_inter = pskdemod(z_echan, M, pi/M);
    bits_estime = int2bit((bits_estime_inter'),3)';

    % Taux d'erreur binaire
    TEB_avecbruit(j) = sum(bits_estime ~= bits)/N;

    % Taux d'erreur theorique
    TEB_theorique(j) = (2/log2(M))*qfunc(sqrt(2*log2(M)*EbN0)*sin(pi/8));
end

% Tracé du TEB Estimé en fonction de Eb/N0
figure;
semilogy(0:6, TEB_avecbruit, 'r');
grid on;
title('TEB Estimé en fonction de Eb/N0 ');
xlabel('SNR (dB)');
ylabel('TEB');

% Tracé du TEB et du TEB théorique
figure;
semilogy(0:6, TEB_avecbruit, 'r');
hold on;
semilogy(0:6, TEB_theorique, 'b');
grid on;
title('TEB éstimé avec bruit et TEB théorique');
xlabel('SNR (dB)');
ylabel('TEB');
legend('TEB éstimé', 'TEB théorique');


