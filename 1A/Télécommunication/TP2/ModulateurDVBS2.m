% -------------------------------------------------------------------------
%                   Implémentation du modulateur DVB-S2
% -------------------------------------------------------------------------
%                                Ayoub Bouchama
%                              Oussama ElGuerraoui
%                                  'Groupe F' 
% -------------------------------------------------------------------------

close all;
clc;
clear;

% Les paramètres

Fe = 6000; % Fréquence d'échantillonnage
Rb = 3000; % Débit binaire
Tb = 1/Rb; % Période d'échantillonnage
Te = 1/Fe; % Période d'échantillonnage
N = 3333333; % Nombre de bits
Rs = 1/Tb; % Fréquence de symbole
Ts = 1/Rs; % Période de symbole
Ns = Ts/Te; % Nombre d'échantillons par symbole
k = 3; % Nombre de bits par symbole
M = 2^k; % Ordre de modulation
alpha = 0.2; % Roll-off
fp = 2000; % Fréquence porteuse
L = 100; % Span
retard = (L*Ns)/2; % Retard introsuit par le filtre

%% Modulation 8-PSK

% Génération des bits
bits = randi([0 1],1,N);

% Mapping des bits
bits_res = reshape(bits, k, N/k);
l = bi2de(bits_res', 'left-msb')';
dk = pskmod(l, M, pi/M);
scatterplot(dk);

% Suréchantillonnage
symbolesSurEchantillones = kron(dk, [1 zeros(1,Ns-1)]);

% Filtre de mise en forme
h = rcosdesign(alpha, L, Ns, 'sqrt');

% Gestion du retard
xe = filter(h, 1, [symbolesSurEchantillones zeros(1, retard)]);
xe = xe(retard+1:end);

% Signal généré sur la voie en phase
I = real(xe);

% Signal généré sur la voie quadrature
Q = imag(xe);

%% Canal de transmission

for j = 1:7

    % Puissance du signal
    Px = mean(abs(xe).^2);

    % Eb/N0
    EbN0 = 10^((j-1)/10);

    % Calcul de sigma
    sigma = sqrt((Ns*Px)./(2*log2(M)*EbN0));

    % Bruit gaussien
    bruit = sigma*randn(1, length(xe)) + 1i*randn(1, length(xe))*sigma ;

    % Signal bruité
    r = xe + bruit;

    % Filtre de réciption
    hr = h;

    % Gestion du retard
    z = filter(hr, 1, [r zeros(1, retard)]);
    z = z(retard+1:end);

    % Echantillonage 
    z_ech = z(1:Ns:end);

    % Constellation
    scatterplot(z_ech);

    % Demapping
    bitsDemappe = pskdemod(z_ech, M, pi/M);
    bitsEstimes = int2bit((bitsDemappe'), 3)';

    % Taux d'erreur binaire
    TEB_ESTIME(j) = sum(bitsEstimes ~= bits)/N;

    % Taux d'erreur theorique
    TEB_THEORIQUE(j) = (2/log2(M))*qfunc(sqrt(2*log2(M)*EbN0)*sin(pi/8));
end

% Tracé du TEB Estimé en fonction de Eb/N0
figure;
semilogy(0:6, TEB_ESTIME, 'r');
grid on;
title('TEB Estimé en fonction de Eb/N0 ');
xlabel('SNR (dB)');
ylabel('TEB');

% Tracé du TEB et du TEB théorique
figure;
semilogy(0:6, TEB_ESTIME, 'r');
hold on;
semilogy(0:6, TEB_THEORIQUE, 'b');
grid on;
title('TEB éstimé avec bruit et TEB théorique');
xlabel('SNR (dB)');
ylabel('TEB');
legend('TEB éstimé', 'TEB théorique');
