% -------------------------------------------------------------------------
%          Fonction qui prend en paramètre une phase et retourne le
%            taux d'erreur binaire de la chaine original et corrigé
% -------------------------------------------------------------------------
%                                Ayoub Bouchama
%                              Oussama ElGuerraoui
%                                  'Groupe F' 
% -------------------------------------------------------------------------

% phi : l'erreur de phase à introduire dans la chaine de transmission.
% TEB_ESTIME_ORIGINAL : le taux d'erreur binaire de la chaine codé.
% TEB_ESTIME_CORRIGE : le taux d'erreur binaire de la chaine corrigé.
function [TEB_ESTIME_ORIGINAL, TEB_ESTIME_CORRIGE] = Correction(phi)

    % Les paramètres

    Fe = 24000; % Fréquence d'échantillonnage
    Rb = 6000; % Débit binaire
    Tb = 1/Rb; % Période d'échantillonnage
    Te = 1/Fe; % Période d'échantillonnage
    Rs = 1/Tb; % Fréquence de symbole
    Ts = 1/Rs; % Période de symbole
    Ns = Ts/Te; % Nombre d'échantillons par symbole
    N = 1000000; % Nombre de bits à transmettre
    k = 1; % Nombre de bits par symbole
    Echelle = 0:6; % Eb/N0 en dB

    % Génération des bits
    bits = randi([0 1], 1, N);

    % Mapping des bits
    ak = 2*bits - 1;

    % Sur-echantillonnage
    symbolesSurEchantionnes = kron(ak, [1 zeros(1,Ns-1)]);

    % Filtre de mise en forme
    h = ones(1, Ns);

    % Enveloppe Complexe
    xe = filter(h, 1, symbolesSurEchantionnes);

    %% Canal de transmission

    % Initialiser les vecteurs de TEB
    TEB_ESTIME_ORIGINAL = zeros(1, length(Echelle));
    TEB_ESTIME_CORRIGE = zeros(1, length(Echelle));

    % Boucle pour introduire le bruit
    for j = 1:7

        % Puissance du signal
        Pxe = mean(xe.^2);

        % Eb/N0
        EbN0 = 10^((j-1)/10);

        % Bruit
        sigma = sqrt(Ns*Pxe/(2*k*EbN0)); % k = log2(M)

        % Bruit blanc gaussien
        bruit = sigma*randn(1, length(xe)) + 1i*sigma*randn(1, length(xe));

        % Signal reçu
        r = xe + bruit;

        % Introduction de l'erreur phase
        rAvecErreur = r*exp(1i*phi);

        % Filtre de réception
        hr = h;

        % Filtrage
        z = filter(hr,1, rAvecErreur);

        % Echantillonnage
        zm = z(Ns:Ns:end);

        % La phase estimée
        phiEstime = 1/2*angle(sum(zm.^2));

        % Correction de la phase
        zc = zm.*exp(-1i*phiEstime);

        % Partie réelle du signal échantillonné
        zmReal = real(zm);
        zcReal = real(zc);

        % Décision
        SymbolesEstimesOrig = sign(zmReal);
        SymbolesEstimesCor = sign(zcReal);

        % Demapping
        BitsEstimesOrig = SymbolesEstimesOrig > 0;
        BitsEstimesCor = SymbolesEstimesCor > 0;

        % Taux d'erreur binaire estimé
        TEB_ESTIME_ORIGINAL(j) = sum(BitsEstimesOrig ~= bits)/N;

        % Taux d'erreur binaire corrigé
        TEB_ESTIME_CORRIGE(j) = sum(BitsEstimesCor ~= bits)/N;

    end
    
end 