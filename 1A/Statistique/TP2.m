% TP2 de Statistiques :
% Nom : BOUCHAMA
% Prénom : Ayoub
% Groupe : 1SN-F

function varargout = fonctions_TP2_stat(nom_fonction,varargin)

    switch nom_fonction
        case 'tirages_aleatoires_uniformes'
            [varargout{1},varargout{2}] = tirages_aleatoires_uniformes(varargin{:});
        case 'estimation_Dyx_MV'
            [varargout{1},varargout{2}] = estimation_Dyx_MV(varargin{:});
        case 'estimation_Dyx_MC'
            [varargout{1},varargout{2}] = estimation_Dyx_MC(varargin{:});
        case 'estimation_Dyx_MV_2droites'
            [varargout{1},varargout{2},varargout{3},varargout{4}] = estimation_Dyx_MV_2droites(varargin{:});
        case 'probabilites_classe'
            [varargout{1},varargout{2}] = probabilites_classe(varargin{:});
        case 'classification_points'
            [varargout{1},varargout{2},varargout{3},varargout{4}] = classification_points(varargin{:});
        case 'estimation_Dyx_MCP'
            [varargout{1},varargout{2}] = estimation_Dyx_MCP(varargin{:});
        case 'iteration_estimation_Dyx_EM'
            [varargout{1},varargout{2},varargout{3},varargout{4},varargout{5},varargout{6},varargout{7},varargout{8}] = ...
            iteration_estimation_Dyx_EM(varargin{:});
    end

end

% Fonction centrage_des_donnees (exercice_1.m) ----------------------------
function [x_G, y_G, x_donnees_bruitees_centrees, y_donnees_bruitees_centrees] = ...
                centrage_des_donnees(x_donnees_bruitees,y_donnees_bruitees)
    
    %Calcul de la moyenne de x_donnees_bruitees
    x_G = mean(x_donnees_bruitees);

    %Calcul de la moyenne de y_donnees_bruitees
    y_G = mean(y_donnees_bruitees);

    %Centrer les données de x en soustractant la moyenne
    x_donnees_bruitees_centrees = x_donnees_bruitees - x_G;

    %Centrer les données de y en soustractant la moyenne
    y_donnees_bruitees_centrees = y_donnees_bruitees - y_G;
 
end

% Fonction tirages_aleatoires_uniformes (exercice_1.m) ------------------------
function [tirages_angles,tirages_G] = tirages_aleatoires_uniformes(n_tirages,taille)

    % Tirages aléatoires d'angles pour les vecteurs normaux (entre -pi/2 et pi/2)
    tirages_angles = pi*rand(1,n_tirages)-pi/2;

    % Tirages aléatoires de points pour se trouver sur la droite (sur [-taille,taille])
    tirages_G = 2*taille*rand(2,n_tirages)-taille*ones(2,n_tirages); 


end

% Fonction estimation_Dyx_MV (exercice_1.m) -------------------------------
function [a_Dyx,b_Dyx] = ...
           estimation_Dyx_MV(x_donnees_bruitees,y_donnees_bruitees,tirages_psi)

    % Appel de la fonction de centrage des données pour calculer les coordonnées x_G, y_G et les données bruitees centrées x_donnees_bruitees_centrees, y_donnees_bruitees_centrees
    [x_G,y_G,x_donnees_bruitees_centrees,y_donnees_bruitees_centrees] = centrage_des_donnees(x_donnees_bruitees,y_donnees_bruitees);

    % La longueur de tirages_psi
    n_tirages = length(tirages_psi);

    % Calculer les tangentes des angles tirages_psi et les stocker dans la variable tanpsi
    tan_psi = tan(tirages_psi);

    % Définir la variable A
    A = ones(n_tirages,1)*y_donnees_bruitees_centrees - (tan_psi')*x_donnees_bruitees_centrees;

    % Trouver l'indice de la valeur minimale de la somme des carrés de A
    [~,indice] = min(sum(A.^2,2));

    % Utiliser l'indice pour définir a_Dyx comme étant le coefficient tangentiel de la régression linéaire
    a_Dyx = tan_psi(indice);

    % Utiliser x_G et a_Dyx pour définir b_Dyx comme étant l'ordonnée à l'origine de la régression linéaire
    b_Dyx = y_G - a_Dyx*x_G;

end

% Fonction estimation_Dyx_MC (exercice_1.m) -------------------------------
function [a_Dyx,b_Dyx] = ...
                   estimation_Dyx_MC(x_donnees_bruitees,y_donnees_bruitees)

    % Construire la matrice A
    A = [x_donnees_bruitees',ones(length(x_donnees_bruitees),1)];

    % Stocker y_donnees_bruitees' dans la variable B
    B = y_donnees_bruitees';

    % Résoudre pour X en utilisant la formule de la régression linéaire (A' * A) * X = A' * B
    X = (A'*A)\(A'*B); 

    % Définir a_Dyx comme étant le premier élément de X
    a_Dyx = X(1); 

    % Définir b_Dyx comme étant le deuxième élément de X
    b_Dyx = X(2);

end

% Fonction estimation_Dyx_MV_2droites (exercice_2.m) -----------------------------------
function [a_Dyx_1,b_Dyx_1,a_Dyx_2,b_Dyx_2] = ... 
         estimation_Dyx_MV_2droites(x_donnees_bruitees,y_donnees_bruitees,sigma, ...
                                    tirages_G_1,tirages_psi_1,tirages_G_2,tirages_psi_2)    

    % Initialisation des variables n et n_tirages
    m = length(x_donnees_bruitees);
    n_tirages = length(tirages_G_1);

    % Calcul des tangentes des angles tirages_psi_1 et tirages_psi_2
    tan_psi_1 = tan(tirages_psi_1);
    tan_psi_2 = tan(tirages_psi_2);

    % Centrage des données y_donnees_bruitees en utilisant les coordonnées tirages_G_1(2,:) et tirages_G_2(2,:)
    y_donnees_bruitees_centrees_1 = ones(n_tirages,1)*y_donnees_bruitees - tirages_G_1(2,:)'*ones(1,m);
    y_donnees_bruitees_centrees_2 = ones(n_tirages,1)*y_donnees_bruitees - tirages_G_2(2,:)'*ones(1,m);

    % Centrage des données x_donnees_bruitees en utilisant les coordonnées tirages_G_1(1,:) et tirages_G_2(1,:)
    x_donnees_bruitees_centrees_1 = ones(n_tirages,1)*x_donnees_bruitees - tirages_G_1(1,:)'*ones(1,m);
    x_donnees_bruitees_centrees_2 = ones(n_tirages,1)*x_donnees_bruitees - tirages_G_2(1,:)'*ones(1,m);

    % Calcul des résidus pour les données centrées en utilisant les tangentes tanpsi_1 et tanpsi_2
    res_1 = y_donnees_bruitees_centrees_1 - (tan_psi_1'*ones(1,m)).*x_donnees_bruitees_centrees_1;
    res_2 = y_donnees_bruitees_centrees_2 - (tan_psi_2'*ones(1,m)).*x_donnees_bruitees_centrees_2;

    %Calcul de la variable D en utilisant les résidus_1 et résidus_2
    A = log(exp(-res_1.^2/(2*sigma^2)) + exp(-res_2.^2/(2*sigma^2)));
    %calcul de l'indice pour la variable D
    [~,indice] = max(sum(A,2));

    %Calcul des variables a_Dyx_1 et b_Dyx_1 en utilisant les données tirages_G_1
    a_Dyx_1 = tan_psi_1(indice);
    b_Dyx_1 = tirages_G_1(2,indice) - a_Dyx_1*tirages_G_1(1,indice);

    %Calcul des variables a_Dyx_2 et b_Dyx_2 en utilisant les données tirages_G_2
    a_Dyx_2 = tan_psi_2(indice);
    b_Dyx_2 = tirages_G_2(2,indice) - a_Dyx_2*tirages_G_2(1,indice);

end

% Fonction probabilites_classe (exercice_3.m) ------------------------------------------
function [probas_classe_1,probas_classe_2] = probabilites_classe(x_donnees_bruitees,y_donnees_bruitees,sigma,...
                                                                 a_1,b_1,proportion_1,a_2,b_2,proportion_2)
    
    % Calcul des résidus pour les données bruitees en utilisant les coefficients a_1 et b_1
    res_1 = y_donnees_bruitees - a_1*x_donnees_bruitees - b_1;

    % Calcul des résidus pour les données bruitees en utilisant les coefficients a_2 et b_2
    res_2 = y_donnees_bruitees - a_2*x_donnees_bruitees - b_2;

    %Calcul de la probabilité de classe 1 en utilisant la proportion_1 et les résidus calculés précédemment
    probas_classe_1 = proportion_1*exp(-res_1.^2/(2*sigma^2));

    %Calcul de la probabilité de classe 2 en utilisant la proportion_2 et les résidus calculés précédemment
    probas_classe_2 = proportion_2*exp(-res_2.^2/(2*sigma^2));

end

% Fonction classification_points (exercice_3.m) ----------------------------
function [x_classe_1,y_classe_1,x_classe_2,y_classe_2] = classification_points ...
              (x_donnees_bruitees,y_donnees_bruitees,probas_classe_1,probas_classe_2)

    % Sélection des données appartenant à la classe 1 en utilisant les probabilités calculées précédemment
    x_classe_1 = x_donnees_bruitees(probas_classe_1 > probas_classe_2);
    y_classe_1 = y_donnees_bruitees(probas_classe_1 > probas_classe_2);

    % Sélection des données appartenant à la classe 2 en utilisant les probabilités calculées précédemment
    x_classe_2 = x_donnees_bruitees(probas_classe_1 <= probas_classe_2);
    y_classe_2 = y_donnees_bruitees(probas_classe_1 <= probas_classe_2);

end

% Fonction estimation_Dyx_MCP (exercice_4.m) -------------------------------
function [a_Dyx,b_Dyx] = estimation_Dyx_MCP(x_donnees_bruitees,y_donnees_bruitees,probas_classe)

    % Construction de la matrice A en utilisant les données x_donnees_bruitees et probas_classe
    A = [x_donnees_bruitees'.*probas_classe' , probas_classe'];

    % Construction de la matrice B en utilisant les données y_donnees_bruitees et probas_classe
    B = y_donnees_bruitees'.*probas_classe';

    % Résolution du système linéaire A*X = B pour trouver les coefficients a_Dyx et b_Dyx
    X = A\B;
    a_Dyx = X(1);
    b_Dyx = X(2);
   
end

% Fonction iteration_estimation_Dyx_EM (exercice_4.m) ---------------------
function [probas_classe_1,proportion_1,a_1,b_1,probas_classe_2,proportion_2,a_2,b_2] =...
         iteration_estimation_Dyx_EM(x_donnees_bruitees,y_donnees_bruitees,sigma,...
         proportion_1,a_1,b_1,proportion_2,a_2,b_2)

    % Initialisation de la variable n
    n = length(x_donnees_bruitees);

    % Calcul des probabilités de chaque classe en utilisant les fonctions probabilites_classe
    [probas_classe_1,probas_classe_2] = probabilites_classe(x_donnees_bruitees,y_donnees_bruitees,sigma,a_1,b_1,proportion_1,a_2,b_2,proportion_2);

    % Calcul de la proportion de chaque classe en utilisant les probabilités calculées précédemment
    proportion_1 = sum(probas_classe_1)/n;
    proportion_2 = sum(probas_classe_2)/n;

    % Estimation des coefficients a_1 et b_1 en utilisant la fonction estimation_Dyx_MCP et les données de la classe 1
    [a_1,b_1] = estimation_Dyx_MCP(x_donnees_bruitees,y_donnees_bruitees,probas_classe_1);

    % Estimation des coefficients a_2 et b_2 en utilisant la fonction estimation_Dyx_MCP et les données de la classe 2
    [a_2,b_2] = estimation_Dyx_MCP(x_donnees_bruitees,y_donnees_bruitees,probas_classe_2);

end
