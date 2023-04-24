% -------------------------------------------------------------------------
%                       Troisième chaine de transmission
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
k = 2; % Nombre de bits par symbole
M = 2^k; % Ordre de modulation
Rs = 1/(log2(M)*Tb); % Fréquence de symbole
Ts = 1/Rs; % Période de symbole
Ns = Ts/Te; % Nombre d'échantillons par symbole

%% Modulation

% Génération de N bits aléatoires
bits = randi([0 1],1,N);

% Mapping
Symboles = [-3, -1, 1, 3];
symboles = (2*bit2int(bits',2) - 3)' ;

% Sur-échantillonnage

signal_sur_echantillonne = kron(symboles,[1, zeros(1,Ns-1)]);

% Filtre de mise en forme
hm1 = ones(1, Ns);
x = filter(hm1, 1, signal_sur_echantillonne);

%% Demodulation sans bruit

% Filtre de réception
hr1 = ones(1, Ns);
z1 = filter(hr1, 1, x);

% Echantillonnage
z1_echant = z1(Ns:Ns:end);

%decision
symboles_estimes1 = z1_echant/Ns;

% Demapping
bits_detecte1 =int2bit((symboles_estimes1' + 3)/2,2)';


% Taux Erreur Binaire sans bruit
TEB1 = sum(abs(bits_detecte1 - bits))/length(bits);
fprintf('TEB sans bruit est %f\n', TEB1);

% Taux d'erreur symbole sans bruit
TES1 = sum(abs(symboles_estimes1 - symboles))/length(symboles);
fprintf('TES sans bruit est %f\n', TES1);

% Diagramme de l'oeil sans bruit
%figure(2);
%Oeil_Sans_Bruit = reshape(z1(Ns+1:end), Ns, length(z1(Ns+1:end))/Ns);
%plot(Oeil_Sans_Bruit);
%xlim([1 16])
%title('Diagramme de l''oeil sans bruit de la 3ème chaine');
%grid on;

%% Demodulation avec bruit
    
% Sur-échantillonnage
signal_sur_echantillonne2 = kron(symboles,[1 zeros(1,Ns-1)]);
    
% Filtrage
hm2 = ones(1,Ns);
x2 = filter(hm2,1,signal_sur_echantillonne2);

for j = 0 : 8
    
    % Calcul de la puissance
    sigma2 = mean(x2.^2)*Ns/(4*10^(j/10));
        
    % Génération et ajout du bruit     
    b = sqrt(sigma2)*randn(1,length(x2));
    r = x2 + b ;

    % Filtre de réception
    hr = ones(1,Ns);
    z2 = filter(hr,1,r);
        
    % Démodulateur bande de base
    z_echant2 = z2(Ns:Ns:end);
        
    % decision
    symboles_estimes2 = zeros(1,length(z_echant2));
    
    for i = 1:length(z_echant2)
        if (z_echant2(i) > 2*Ns)
            %a_m = 3;
            symboles_estimes2(i) = 3;
        elseif (z_echant2(i) >= 0 && z_echant2(i) <2*Ns)
            %a_m = 1;
            symboles_estimes2(i) = 1;
        elseif (z_echant2(i) < -2*Ns)
            %a_m = -3;
            symboles_estimes2(i) = -3;
        else
            %a_m = -1;
            symboles_estimes2(i) = -1;
        end
    end
    
    % Demapping
    bits_detecte2 = int2bit((symboles_estimes2' + 3)/2,2)';
    nb_erreurs =  (bits_detecte2 ~= bits);
    
    % Taux d'erreur binaire
    TEB2(j+1) = sum(nb_erreurs)/length(bits);
        
    TEB_theorique(j+1) =qfunc(sqrt((4/5)*10.^(j/10)));

    TES2(j+1) = sum(abs(symboles_estimes2 - symboles))/length(symboles);

    TES_Theorique(j+1) = TEB_theorique(j+1)*2;

    % Diagramme de l'oeil avec bruit
    figure;
    Oeil_Avec_Bruit = reshape(z2(Ns+1:end), Ns, length(z2(Ns+1:end))/Ns);
    plot(Oeil_Avec_Bruit);
    xlim([1 16])
    title(['Diagramme de l''oeil avec bruit de la 3ème chainepour j = ', num2str(j)]);
end

% Tracé du TEB Estimé en fonction de Eb/N0
figure;
semilogy(0:8, TEB2, 'r');
grid on;
title('TEB Estimé en fonction de Eb/N0 de la 3ème chaine');
xlabel('SNR (dB)');
ylabel('TEB');

% Tracé du TEB et du TEB théorique
figure;
semilogy(0:8, TEB2, 'r');
hold on;
semilogy(0:8, TEB_theorique, 'b');
grid on;
title('TEB éstimé avec bruit et TEB théorique de la 3ème chaine');
xlabel('SNR (dB)');
ylabel('TEB');
legend('TEB éstimé', 'TEB théorique');

% Tracé du TES Estimé en fonction de Eb/N0
figure;
semilogy(0:8, TES2, 'r');
grid on;
title('TES Estimé en fonction de Eb/N0 de la 3ème chaine');
xlabel('SNR (dB)');
ylabel('TES');

% Tracé du TES et du TES théorique
figure;
semilogy(0:8, TES2, 'r');
hold on;
semilogy(0:8, TES_Theorique, 'b');
grid on;
title('TES éstimé avec bruit et TES théorique de la 3ème chaine');
xlabel('SNR (dB)');
ylabel('TES');
legend('TES éstimé', 'TES théorique');
