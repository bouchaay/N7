% -------------------------------------------------------------------------
%                   Etude comparative entre les chaines de transmission
%                               Chaine 1 | Chaine 3
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
Ns1 = Ts/Te; % Nombre d'échantillons par symbole
M1 = 2; % Ordre de modulation
M3 = 4; % Ordre de modulation
Ns3 = log2(M3)*(Ts/Te);

%% Implementation de la chaine de transmission 1

% Génération de N bits aléatoires
bits = randi([0 1],1,N);

% Mapping
Symboles = [-1, 1];
Symboles1= 2*bits - 1;

% Sur-échantillonnage
signal_sur_echantillonne1 = kron(Symboles1 ,[1 zeros(1,Ns1-1)]);

% Filtre de mise en forme
hm1 = ones(1, Ns1);
x1 = filter(hm1, 1, signal_sur_echantillonne1);

% Ajout du bruit
Ps = mean(x1.^2);

for i = 0:8

    % Eb/N0 en dB
    EbN0dB = 10^(i/10);

    % Calcul de la puissance du bruit
    sigma2 = (Ns1*Ps)./(2*log2(M1)*EbN0dB);

    % Bruit gaussien
    bruit = sqrt(sigma2)*randn(1, length(x1));

    % Siganl bruité
    r = x1 + bruit;

    % Filtre de réception
    hr2 = ones(1, Ns1);
    z1 = filter(hr2, 1, r);

    % Echantillonnage
    z1_echant = z1(Ns1:Ns1:end);

    %decision 
    symboles_estimes1 = z1_echant/Ns1;
    map_estime1 = sign(symboles_estimes1);

    % demapping
    bits_estimes1 = symboles_estimes1 > 0;

    % Taux d'erreur binaire
    TEB1(i+1) = sum(bits_estimes1 ~= bits)/N;

    % Taux d'erreur symbole
    TES1(i+1) = sum(abs(map_estime1 - Symboles1))/length(Symboles1);
end

%% Implementation de la chaine de transmission 3

% Modulation

% Mapping
Symboles = [-3, -1, 1, 3];
symboles3 = (2*bit2int(bits',2) - 3)' ;

% Sur-échantillonnage

signal_sur_echantillonne3 = kron(symboles3,[1, zeros(1,Ns3-1)]);

% Filtre de mise en forme
hm1 = ones(1, Ns3);
x3 = filter(hm1, 1, signal_sur_echantillonne3);

% Ajout du bruit
Ps = mean(x3.^2);

for j = 0:8

    % Eb/N0 en dB
    EbN0dB = 10^(j/10);

    % Calcul de la puissance du bruit
    sigma2 = (Ns3*Ps)./(4*EbN0dB);

    % Bruit gaussien
    bruit = sqrt(sigma2)*randn(1, length(x3));

    % Siganl bruité
    r = x3 + bruit;

    % Filtre de réception
    hr2 = ones(1, Ns3);
    z3 = filter(hr2, 1, r);

    % Echantillonnage
    z3_echant = z3(Ns3:Ns3:end);

    % Decision
    symboles_estimes3 = zeros(1,length(z3_echant));
    for i=1:length(z3_echant)
        if ( z3_echant(i) > 2*Ns3)
            %a_m = 3;
            symboles_estimes3(i) = 3;
        elseif ( z3_echant(i) >= 0 && z3_echant(i) <2*Ns3)
            %a_m = 1;
            symboles_estimes3(i) = 1;
        elseif ( z3_echant(i) < -2*Ns3)
            %a_m = -3;
            symboles_estimes3(i) = -3;
        else
            %a_m = -1;
            symboles_estimes3(i) = -1;
        end
    end 
    
    % Demapping
    bits_detecte3 =int2bit((symboles_estimes3' + 3)/2,2)';

    % Taux d'erreur binaire
    TEB3(j+1) = sum((bits_detecte3 ~= bits))/length(bits);

    % Taux d'erreur symbole
    TES3(j+1) = sum(abs(symboles_estimes3 - symboles3))/length(symboles3);
end

% Tracé des taux binaires d'erreurs en fonction de Eb/N0 sur la même figure
figure;
semilogy(0:8, TEB1, 'r');
hold on;
semilogy(0:8, TEB3, 'b');
grid on;
legend('Chaine 1', 'Chaine 3');
xlabel('Eb/N0 (dB)');
ylabel('TEB');
title('Comparaison des TEBs des deux chaines (1|3) de transmission');

% Tracé des taux symbole d'erreurs en fonction de Eb/N0 sur la même figure
figure;
semilogy(0:8, TES1, 'r');
hold on;
semilogy(0:8, TES3, 'b');
grid on;
legend('Chaine 1', 'Chaine 3');
xlabel('Eb/N0 (dB)');
ylabel('TES');
title('Comparaison des TESs des deux chaines (1|3) de transmission');
