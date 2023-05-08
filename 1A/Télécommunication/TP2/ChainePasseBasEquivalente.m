close all;
clc;
clear;

% Les paramètres

Fe = 24000; % Fréquence d'échantillonnage
Rb = 3000; % Débit binaire
Tb = 1/Rb; % Période d'échantillonnage
Te = 1/Fe; % Période d'échantillonnage
N = 10000; % Nombre de bits
Rs = 1/Tb; % Fréquence de symbole
Ts = 1/Rs; % Période de symbole
Ns = Ts/Te; % Nombre d'échantillons par symbole
k = 2; % Nombre de bits par symbole
M = 2^k; % Ordre de modulation
alpha = 0.35; % Roll-off
fp = 2000; % Fréquence porteuse
L = 100; % Span du filtre
retard = (L*Ns)/2; % le retard introduit par le filtre

%% Modulation QPSK

% Génération des bits
bits = randi([0 1],1,N);

% Mapping des bits
ak = 2*bits(1:2:end)-1;
bk = 2*bits(2:2:end)-1;
dk = ak + 1i*bk;

% Constellation
scatterplot(dk);

% Suréchantillonnage
symbolesSurEchantionnesI = kron(ak, [1 zeros(1,Ns-1)]);
symbolesSurEchantionnesQ = kron(bk, [1 zeros(1,Ns-1)]);

% Filtre de mise en forme
h = rcosdesign(alpha, L, Ns, 'sqrt');

% Filtrage
I = filter(h,1,[symbolesSurEchantionnesI zeros(1,retard)]);
Q = filter(h,1,[symbolesSurEchantionnesQ zeros(1,retard)]);

% Gestion du retard
I = I(retard + 1 : end);
Q = Q(retard + 1 : end);

% Enveloppe complexe
xe = I + 1i*Q;
DSP = pwelch(xe, [], [], 4096, Fe, 'centered');

%% Transposition de fréquence

% Signal transmis sur fréquence porteuse
Tx = 0:Te:(length(I)-1)*Te;
x = I.*cos(2*pi*fp*Tx) - Q.*sin(2*pi*fp*Tx);

%% Les tracés

% Tracé du signal généré sur voie en phase I(t)
T = 0:Te:(length(I)-1)*Te;
figure;
plot(T, I)
axis([0 0.1 -0.6 0.6]);
xlabel('Temps (s)')
ylabel('Amplitude')
title('Tracé du signal généré sur voie en phase')
grid on;

% Tracé du siganl généré sur voie en quadrature Q(t)
figure;
plot(T, Q)
axis([0 0.1 -0.6 0.6]);
xlabel('Temps (s)')
ylabel('Amplitude')
title('Tracé du signal généré sur voie en quadrature')
grid on;

% Tracé de la densité spectrale de puissance de xe
F = linspace(-Fe/2, Fe/2, length(DSP));
figure;
semilogy(F, DSP);
xlabel('Fréquence (Hz)')
ylabel('DSP')
title('Densité spectrale de puissance de l enveloppe complexe')
grid on;

%% Canal de transmission

for j = 1:7
    
    % Puissance du signal
    Px = mean(x.^2);
    Pxe = mean(abs(xe).^2);

    % Eb/N0
    EbN0 = 10^((j-1)/10);

    % Calcul de la puissance du bruit
    sigma = sqrt((Ns*Px)./(2*log2(M)*EbN0));
    sigmaxe = sqrt((Ns*Pxe)./(2*log2(M)*EbN0));

    % Bruit gaussien
    bruit = sigma*randn(1, length(x));

    % Bruit Gaussien complexe
    bruitComplexe = sigmaxe*randn(1, length(xe))+ 1i*sigmaxe*randn(1, length(xe));

    % Signal bruité
    r = x + bruit;
    
    % Signal avec bruit complexe
    r2 = xe + bruitComplexe;

    % retour en bande base
    T = Te: Te: Te*length(x);
    xsin = r.*sin(2*pi*fp*T);
    xcos = r.*cos(2*pi*fp*T);
    y = xcos - 1i*xsin;

    % Filtre de récéption
    hr = h;

    % Filtrage
    z = filter(hr, 1, [y zeros(1, retard)]);
    z = z(retard+1:end);

    z_complexe = filter(hr, 1, [r2 zeros(1, retard)]);
    z_complexe = z_complexe(retard+1:end);

    % Echantillonage 
    z_ech = z(1:Ns:end);
    z_ech_complexe = z_complexe(1:Ns:end);

    % Constellation
    scatterplot(z_ech_complexe);

    % Demapping du signal modulé sur fréquence porteuse
    bitsEstimesReal = zeros(1,N);
    bitsEstimesReal(1:2:end) = (real(z_ech) > 0);
    bitsEstimesReal(2:2:end) = (imag(z_ech) > 0);

    % Demapping du signal passe-bas equivalent
    bitsEstimesComplexe = zeros(1,N);
    z_ech_real_comp = real(z_ech_complexe);
    z_ech_imag_comp = imag(z_ech_complexe);
    
    for p = 2:2:N
        if (z_ech_real_comp(p/2) >= 0 && z_ech_imag_comp(p/2) >= 0)
            % dk = 1 + 1i;
            bitsEstimesComplexe(p-1) = 1;
            bitsEstimesComplexe(p) = 1;
            
        elseif (z_ech_real_comp(p/2) >= 0 && z_ech_imag_comp(p/2) <= 0)
            % dk = 1 - 1i;
            bitsEstimesComplexe(p-1) = 1;
            bitsEstimesComplexe(p) = 0;
            
        elseif (z_ech_real_comp(p/2) <= 0 && z_ech_imag_comp(p/2) >= 0)
            % dk = -1 + 1i;
            bitsEstimesComplexe(p-1) = 0;
            bitsEstimesComplexe(p) = 1;
            
        elseif (z_ech_real_comp(p/2) <= 0 && z_ech_imag_comp(p/2) <= 0)
            % dk = -1 - 1i;
            bitsEstimesComplexe(p-1) = 0;
            bitsEstimesComplexe(p) = 0;
        end
    end

    % Taux d'erreur binaire
    TEB_ESTIME(j) = sum(bitsEstimesReal ~= bits)/N;

    % Taux d'erreur theorique
    TEB_THEORIQUE(j) = qfunc(sqrt(2*EbN0));

    % Taux d'erreur binaire du passe-bas equivalent
    TEB_ESTIME_COMPLEXE(j) = sum(bitsEstimesComplexe ~= bits)/N;

end

% Tracé du TEB, TEB théorique et TEB du filtre passe-bas équivalent
figure;
semilogy(0:6, TEB_ESTIME, 'r');
hold on;
semilogy(0:6, TEB_THEORIQUE, 'b');
semilogy(0:6, TEB_ESTIME_COMPLEXE, 'g');
grid on;
title('TEB éstimé avec bruit, TEB théorique et TEB passe-bas equivalent');
xlabel('SNR (dB)');
ylabel('TEB');
legend('TEB éstimé', 'TEB théorique', 'TEB passe-bas');
grid on;
