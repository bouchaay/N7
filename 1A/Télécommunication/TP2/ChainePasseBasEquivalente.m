close all;
clc;
clear;

% Les paramètres

Fe = 24000; % Fréquence d'échantillonnage
Rb = 3000; % Débit binaire
Tb = 1/Rb; % Période d'échantillonnage
Te = 1/Fe; % Période d'échantillonnage
N = 1000000; % Nombre de bits
Rs = 1/Tb; % Fréquence de symbole
Ts = 1/Rs; % Période de symbole
Ns = Ts/Te; % Nombre d'échantillons par symbole
k = 2; % Nombre de bits par symbole
M = 2^k; % Ordre de modulation
alpha = 0.35; % Roll-off
fp = 2000; % Fréquence porteuse

%% Modulation QPSK

% Génération des bits
bits = randi([0 1],1,N);

% Mapping des bits
partieReelle = 2*bits(1:2:end)-1;
partieImaginaire = 2*bits(2:2:end)-1;
symboles = partieReelle + 1i*partieImaginaire;

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

% Tracé du signal généré sur la voie en phase
T_phase = 0:Te:(length(I)-1)*Te;
figure;
plot(T_phase, I)
axis([0 0.1 -0.6 0.6]);
title('Tracé du signal généré sur voie phase')
grid on;

% Densité spectrale de puissace du signal généré sur la voie en phase
DSP_phase = pwelch(I, [], [], [], Fe, 'twosided');
F_phase = linspace(-Fe/2, Fe/2, length(DSP_phase));
figure;
semilogy(F_phase, DSP_phase)
title('Densité spectrale de puissance du signal généré sur la voie en phase')
grid on;

% Signal généré sur la voie en quadrature
Q = imag(xe);

% Signal généré sur voie en quadrature
T_quadrature = 0:Te:(length(Q)-1)*Te;
figure;
plot(T_quadrature, Q)
axis([0 0.1 -0.6 0.6]);
title('Tracé du signal généré sur voie quadrature')
grid on;

% Densité spectrale de puissance sur voie en quadrature
DSP_quadrature = pwelch(Q, [], [], [], Fe, 'twosided');
F_quadrature = linspace(-Fe/2, Fe/2, length(DSP_quadrature));
figure;
semilogy(F_quadrature, DSP_quadrature)
title('Densité spectrale de puissance du signal généré sur la voie en quadrature')
grid on;

%% Transposition de fréquence
Tx = Te:Te:length(xe)*Te;
x_intermediaire = xe.*exp(1i*2*pi*Tx.*fp);
x = real(x_intermediaire);
DSPx = pwelch(x, [], [], [], Fe, 'twosided');
Fx = linspace(-Fe/2, Fe/2, length(DSPx));
figure;
semilogy(Fx, DSPx);
title('Densité spectrale de puissance du signal transmis sur frequence porteuse')
grid on;

% Retour en base
T = Te: Te: Te*length(x);
xsin = x.*sin(2*pi*fp*T);
xcos = x.*cos(2*pi*fp*T);
y = xcos - 1i*xsin;

% Filtre de réception
L = 100;
hr = rcosdesign(alpha, L, Ns, 'sqrt');

% Filtrage avec gestion du retard
ordre = Ns*L;
decalage = floor((ordre)/2);
z = filter(hr,1, [y zeros(1, decalage)]);
z_dec = z(decalage + 1: end);

% Echantillonnage
z_echan = z_dec(1:Ns:end);

% Demapping
bits_estime = zeros(1,N);
z_ech_real = real(z_echan);
z_ech_imag = imag(z_echan);
bits_estime(1:2:end) = (z_ech_real > 0);
bits_estime(2:2:end) = (z_ech_imag > 0);
TEB = sum(bits_estime ~= bits)/N;

%% Canal de transmission

for j = 1:7
    % Puissance du signal
    Px = mean(x.^2);
    Pxe = mean(abs(xe).^2);

    % Eb/N0
    EbN0 = 10^((j-1)/10);

    % Calcul de la puissance du bruit
    sigma2 = (Ns*Px)./(2*log2(M)*EbN0);
    sigmaxe = (Ns*Pxe)./(2*log2(M)*EbN0);


    % Bruit gaussien
    bruit = sqrt(sigma2)*randn(1, length(x));

    % Bruit Gaussien complexe
    bruitComplexe = sqrt(sigmaxe)*randn(1, length(xe))+ 1i*sqrt(sigmaxe)*randn(1, length(xe));

    % Signal bruité
    r = x + bruit;
    
    % Signal avec bruit complexe
    r2 = xe + bruitComplexe;

    % retour en bande base
    T = Te: Te: Te*length(x);
    xsin = r.*sin(2*pi*fp*T);
    xcos = r.*cos(2*pi*fp*T);
    y = xcos - 1i*xsin;

    % Filtre de réception
    L = 100;
    hr = rcosdesign(alpha, L, Ns, 'sqrt');

    % Gestion du retard
    ordre = L*Ns;
    decalage = floor(ordre/2);
    
    z = filter(hr,1, [y zeros(1, decalage)]);
    z_dec = z(decalage +1: end);

    z_complexe = filter(hr,1, [r2 zeros(1, decalage)]);
    z_dec_complexe = z_complexe(decalage +1: end);

    % Echantillonage 
    z_echan = z_dec(1:Ns:end);
    z_echan_complexe = z_dec_complexe(1:Ns:end);

    % Demapping du signal modulé sur fréquence porteuse
    bits_estime = zeros(1,N);
    z_ech_real = real(z_echan);
    z_ech_imag = imag(z_echan);
    bits_estime(1:2:end) = (z_ech_real > 0);
    bits_estime(2:2:end) = (z_ech_imag > 0);

    % Demapping du signal passe-bas equivalent
    bits_estime_complexe = zeros(1,N);
    z_ech_real_comp = real(z_echan_complexe);
    z_ech_imag_comp = imag(z_echan_complexe);
    %bits_estime_complexe(1:2:end) = (z_ech_real_comp > 0);
    %bits_estime_complexe(2:2:end) = (z_ech_imag_comp > 0);
    for p = 2: 2 : N
        if (z_ech_real_comp(p/2) >= 0 && z_ech_imag_comp(p/2) >= 0)
            %symboles_estime= 1 + 1i;
            bits_estime_complexe(p-1) = 1;
            bits_estime_complexe(p) = 1;
            
        elseif (z_ech_real_comp(p/2) >= 0 && z_ech_imag_comp(p/2) <= 0)
            %symboles_estime= 1 - 1i;
            bits_estime_complexe(p-1) = 1;
            bits_estime_complexe(p) = 0;
            
        elseif (z_ech_real_comp(p/2) <= 0 && z_ech_imag_comp(p/2) >= 0)
            %symboles_estime= -1 + 1i;
            bits_estime_complexe(p-1) = 0;
            bits_estime_complexe(p) = 1;
            
        elseif (z_ech_real_comp(p/2) <= 0 && z_ech_imag_comp(p/2) <= 0)
            %symboles_estime= -1 - 1i;
            bits_estime_complexe(p-1) = 0;
            bits_estime_complexe(p) = 0;
        end
    end

    % Taux d'erreur binaire
    TEB_avecbruit(j) = sum(bits_estime ~= bits)/N;

    % Taux d'erreur theorique
    TEB_theorique(j) = qfunc(sqrt(2*EbN0));

    % Taux d'erreur binaire du passe-bas equivalent
    TEB_avecbruitcomplexe(j) = sum(bits_estime_complexe ~= bits)/N;

end

% Tracé du TEB, TEB théorique et TEB du filtre passe-bas équivalent
figure;
semilogy(0:6, TEB_avecbruit, 'r');
hold on;
semilogy(0:6, TEB_theorique, 'b');
semilogy(0:6, TEB_avecbruitcomplexe, 'g');
grid on;
title('TEB éstimé avec bruit, TEB théorique et TEB passe-bas equivalent');
xlabel('SNR (dB)');
ylabel('TEB');
legend('TEB éstimé', 'TEB théorique', 'TEB passe-bas');
grid on;
