% TP3 de Statistiques : fonctions a completer et rendre sur Moodle
% Nom : 
% Prenom : 
% Groupe : 

function varargout = fonctions_TP3_stat(nom_fonction,varargin)

    switch nom_fonction
        case 'estimation_F'
            [varargout{1},varargout{2},varargout{3}] = estimation_F(varargin{:});
        case 'choix_indices_points'
            [varargout{1}] = choix_indices_points(varargin{:});
        case 'RANSAC_2'
            [varargout{1},varargout{2}] = RANSAC_2(varargin{:});
        case 'G_et_R_moyen'
            [varargout{1},varargout{2},varargout{3}] = G_et_R_moyen(varargin{:});
        case 'estimation_C_et_R'
            [varargout{1},varargout{2},varargout{3}] = estimation_C_et_R(varargin{:});
        case 'RANSAC_3'
            [varargout{1},varargout{2}] = RANSAC_3(varargin{:});
    end
end

% Fonction estimation_F (exercice_1.m) ------------------------------------
function [rho_F,theta_F,ecart_moyen] = estimation_F(rho,theta)
    
    % On construit les matrices A et B
    A = [cos(theta) sin(theta)];
    B = rho;

    X = A\B; % X = (A'A)−1 A' B
    rho_F = sqrt(X(1)^2 + X(2)^2);
    theta_F = atan2(X(2), X(1));

    ecart_moyen = mean(abs(rho - rho_F*cos(theta - theta_F)));
end

% Fonction choix_indice_elements (exercice_2.m) ---------------------------
function tableau_indices_points_choisis = choix_indices_points(k_max,n,n_indices)

    % Tableau de dimension k_max * n_indices constitué de k_max tirages
    % aléatoires de n_indices
    tableau_indices_points_choisis = zeros(k_max, n_indices);
    for i = 1 : k_max
        tableau_indices_points_choisis(i, :) = randperm(n,n_indices);
    end
end

% Fonction RANSAC_2 (exercice_2.m) ----------------------------------------
function [rho_F_estime,theta_F_estime] = RANSAC_2(rho,theta,parametres,tableau_indices_2droites_choisies)
    
    n = length(rho);
    S1 = parametres(1);
    S2 = parametres(2);
    k_max = parametres(3);
    ecart = Inf;

    for i = 1 : k_max

        % On tire aléatoirement un sous-ensemble de données, considérées
        % conforme au modèle
        indices = tableau_indices_2droites_choisies(i, :);
        [rho_F_i,theta_F_i,~] = estimation_F(rho(indices),theta(indices));

        % On trouve les données conformes aux données tirées précedemment
        % suivant les paramètres d'entrée (S1)
        donnee_conforme = abs(rho - rho_F_i*cos(theta - theta_F_i)) < S1;
        proportion = sum(donnee_conforme)/n;
        
        % Si la proportion de données conformes est supérieur au seuil S2,
        % le modèle est accepté
        if proportion > S2

            % Si le modèle est accepté, il est réestimé à partir de
            % l'ensemble des données conformes
            [rho_F,theta_F,ecart_moyen] = estimation_F(rho(donnee_conforme),theta(donnee_conforme));
            if ecart_moyen < ecart

                % Le modèle retenu est celui qui minimise l'écart moyen des
                % données conformes
                ecart = ecart_moyen;
                rho_F_estime = rho_F;
                theta_F_estime = theta_F;
            end
        end
    end
end

% Fonction G_et_R_moyen (exercice_3.m, bonus, fonction du TP1) ------------
function [G, R_moyen, distances] = ...
         G_et_R_moyen(x_donnees_bruitees,y_donnees_bruitees)

    x_moyen = mean(x_donnees_bruitees); % moyenne des abscisse des points
    y_moyen = mean(y_donnees_bruitees); % moyenne des ordonnées des points
    G = [x_moyen y_moyen]; % Centre de gravité des points

    distances = sqrt((x_donnees_bruitees - x_moyen).^2 + (y_donnees_bruitees - y_moyen).^2);
    R_moyen = mean(distances); % Distance moyenne des points avec le centre de gravité
end

% Fonction estimation_C_et_R (exercice_3.m, bonus, fonction du TP1) -------
function [C_estime,R_estime,critere] = ...
         estimation_C_et_R(x_donnees_bruitees,y_donnees_bruitees,n_tests,C_tests,R_tests)

    X_c = repmat(C_tests(:, 1), 1, length(x_donnees_bruitees));
    Y_c = repmat(C_tests(:, 2), 1, length(x_donnees_bruitees));
    X = repmat(x_donnees_bruitees, n_tests, 1);
    Y = repmat(y_donnees_bruitees, n_tests, 1);

    D = sqrt((X-X_c).^2 + (Y-Y_c).^2);
    eps = (D - repmat(R_tests, 1, length(x_donnees_bruitees))).^2;
    S = sum(eps, 2);
    [~, i] = min(S);
    C_estime = C_tests(i, :);
    R_estime = R_tests(i);
    critere = mean(abs(D - R_estime));
end

% Fonction RANSAC_3 (exercice_3, bonus) -----------------------------------
function [C_estime,R_estime] = ...
         RANSAC_3(x_donnees_bruitees,y_donnees_bruitees,parametres,tableau_indices_3points_choisis)

    n = length(x_donnees_bruitees);
    S1 = parametres(1);
    S2 = parametres(2);
    k_max = parametres(3);
    ecart = Inf;

    for i = 1 : k_max

        % On tire aléatoirement un sous-ensemble de données, considérées
        % conforme au modèle
        indices = tableau_indices_3points_choisis(i, :);
        [C_i,R_i] = estimation_cercle_3points(x_donnees_bruitees(indices),y_donnees_bruitees(indices));

        % On trouve les données conformes aux données tirées précedemment
        % suivant les paramètres d'entrée (S1)
        donnee_conforme = abs(sqrt((C_i(1)-x_donnees_bruitees).^2 + (C_i(2)-y_donnees_bruitees).^2) - R_i) < S1;
        proportion = sum(donnee_conforme)/n;

        % Si la proportion de données conformes est supérieur au seuil S2,
        % le modèle est accepté
        if proportion > S2

            % Si le modèle est accepté, il est réestimé à partir de
            % l'ensemble des données conformes
            [G, R_moyen, ~] = G_et_R_moyen(x_donnees_bruitees,y_donnees_bruitees);
            [C_estime_F,R_estime_F,ecart_moyen] = estimation_C_et_R(x_donnees_bruitees(donnee_conforme),y_donnees_bruitees(donnee_conforme),length(C_i) ,C_i, R_i);
            if ecart_moyen < ecart
                
                % Le modèle retenu est celui qui minimise l'écart moyen des
                % données conformes
                ecart = ecart_moyen;
                C_estime = C_estime_F;
                R_estime = R_estime_F;
            end
        end
    end
end
