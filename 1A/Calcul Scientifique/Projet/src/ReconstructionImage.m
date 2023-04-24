%%  Application de la SVD : compression d'images

clear all
close all

% Lecture de l'image
I = imread('BD_Asterix_0.png');
I = rgb2gray(I);
I = double(I);

[q, p] = size(I)

% Décomposition par SVD
fprintf('Décomposition en valeurs singulières\n')
tic
[U, S, V] = svd(I);
toc

l = min(p,q);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% On choisit de ne considérer que 200 vecteurs
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% vecteur pour stocker la différence entre l'image et l'image reconstuite
inter = 1:40:(200+40);
inter(end) = 200;
differenceSVD = zeros(size(inter,2), 1);

% images reconstruites en utilisant de 1 à 200 vecteurs (avec un pas de 40)
ti = 0;
td = 0;
for k = inter

    % Calcul de l'image de rang k
    Im_k = U(:, 1:k)*S(1:k, 1:k)*V(:, 1:k)';

    % Affichage de l'image reconstruite
    ti = ti+1;
    figure(ti)
    colormap('gray')
    imagesc(Im_k)
    
    % Calcul de la différence entre les 2 images
    td = td + 1;
    differenceSVD(td) = sqrt(sum(sum((I-Im_k).^2)));
    pause
end

% Figure des différences entre image réelle et image reconstruite
ti = ti+1;
figure(ti)
hold on 
plot(inter, differenceSVD, 'rx')
ylabel('RMSE')
xlabel('rank k')
pause


% Plugger les différentes méthodes : eig, puissance itérée et les 4 versions de la "subspace iteration method" 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% QUELQUES VALEURS PAR DÉFAUT DE PARAMÈTRES, 
% VALEURS QUE VOUS POUVEZ/DEVEZ FAIRE ÉVOLUER
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% tolérance
eps = 1e-8;
% nombre d'itérations max pour atteindre la convergence
maxit = 1000;

% taille de l'espace de recherche (m)
search_space = 1000;

% pourcentage que l'on se fixe
percentage = 0.995;

% p pour les versions 2 et 3 (attention p déjà utilisé comme taille)
puiss = 1;



%%%%%%%%%%%%%
% À COMPLÉTER
%%%%%%%%%%%%%

%%
%calcul de la matrice m
if size(I,1) >= size(I,2)
    M = I*I';
else
    M = I'*I;
end

% calcul des couples propres
%%
%[ V1, D, n_ev, it, itv, flag ] = subspace_iter_v3(M, search_space, percentage, puiss, eps, maxit);
[ V1, D, n_ev, it, itv, flag ] = subspace_iter_v2(M, search_space, percentage, puiss, eps, maxit);
%[ V1, D, n_ev, it, itv, flag ] = subspace_iter_v1( M, search_space, percentage, eps, maxit );
%[ V1, D, itv, flag ] = subspace_iter_v0( M, search_space, eps, maxit );
%[ V1, D, n_ev, itv, flag ] = power_v12( M, search_space, percentage, eps, maxit );
%[ V1, D, n_ev, itv, flag ] = power_v11( M, search_space, percentage, eps, maxit );
%[W, V, flag, q, qv] = eigen_2023(imat, n, v, m, eps, maxit, percentage, p, genere)

%%
% calcul des valeurs singulières
%%
S1 = sqrt(D);
%%
% calcul de l'autre ensemble de vecteurs
%%
U1 = I*V1*inv(S1);

% vecteur pour stocker la différence entre l'image et l'image reconstuite
inter1 = 1:floor(n_ev/6):n_ev;
inter1(end) = 6*floor(n_ev/6);
differenceSVD1 = zeros(size(inter1,2), 1);

% images reconstruites en utilisant de 1 à 200 vecteurs (avec un pas de 40)
ti1= 0;
td1 = 0;
%%
% calcul des meilleures approximations de rang faible
%%

for k = inter1

     % Calcul de l'image de rang k

    Im_k1 = U1(:, 1:k)*S1(1:k, 1:k)*V1(:, 1:k)';

    % Affichage de l'image reconstruite
    ti1 = ti1+1;
    figure(ti1)
    colormap('gray')
    imagesc(Im_k1)
    
    % Calcul de la différence entre les 2 images
    td1 = td1 + 1;
    differenceSVD1(td1) = sqrt(sum(sum((I-Im_k1).^2)));
    pause
end

% Figure des différences entre image réelle et image reconstruite
ti1 = ti1+1;
figure(ti1)
hold on 
plot(inter1, differenceSVD1, 'rx')
ylabel('RMSE')
xlabel('rank k')
pause


     
