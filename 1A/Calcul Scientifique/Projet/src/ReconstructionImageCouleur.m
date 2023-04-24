%%  Application de la SVD : compression d'images colorées

close all
clear all

% Lecture de l'image
I = imread('BD_Asterix_Colored.jpg');
I = double(I);

% Taille de l'image
[qr, pr] = size(I(:,:,1));
[qg, pg] = size(I(:,:,2));
[qb, pb] = size(I(:,:,3));

% Affichage de l'image
figure(1)
imshow(uint8(I))
title('Image originale')

% Calcul de la SVD
[Ur, Sr, Vr] = svd(I(:,:,1));
[Ug, Sg, Vg] = svd(I(:,:,2));
[Ub, Sb, Vb] = svd(I(:,:,3));

% Vecteur pour stocker la différence entre l'image originale et l'image reconstruite
inter = 1:40:240;
inter(end) = 200;
differenceSVDr = zeros(size(inter, 2), 1);
differenceSVDb = zeros(size(inter, 2), 1);
differenceSVDg = zeros(size(inter, 2), 1);

% Image reconstruite en utilisant de 1 à 200 vecteurs (avec un pas de 40)
ti = 0;
td = 0;

for k = inter

    % Calcul de l'image de rang k
    Ir = Ur(:, 1:k) * Sr(1:k, 1:k) * Vr(:, 1:k)';
    Ig = Ug(:, 1:k) * Sg(1:k, 1:k) * Vg(:, 1:k)';
    Ib = Ub(:, 1:k) * Sb(1:k, 1:k) * Vb(:, 1:k)';

    % Affichage de l'image reconstruite
    ti = ti + 1;
    figure(ti)
    imshow(uint8(cat(3, Ir, Ig, Ib)))
    title(['Image reconstruite avec ', num2str(k), ' vecteurs singuliers'])

    % Calcul de la différence entre l'image originale et l'image reconstruite
    td = td + 1;
    differenceSVDr(td) = sqrt(sum(sum((Ir - I(:,:,1)).^2)));
    differenceSVDg(td) = sqrt(sum(sum((Ig - I(:,:,2)).^2)));
    differenceSVDb(td) = sqrt(sum(sum((Ib - I(:,:,3)).^2)));
    pause
end

% Affichage de la différence entre l'image originale et l'image reconstruite
ti = ti + 1;
figure(ti)
hold on
plot(inter, differenceSVDr, 'r')
plot(inter, differenceSVDg, 'g')
plot(inter, differenceSVDb, 'b')
title('Différence entre l''image originale et l''image reconstruite')
legend('Rouge', 'Vert', 'Bleu')
hold off

%##########################################################################
% Reconstruction de l'image en utilisant les différents méthodes
%##########################################################################

% Paramètres

% tolérance
eps = 1e-8;

% nombre d'itérations max pour atteindre la convergence
maxit = 1000;

% taille de l'espace de recherche (m)
search_space = 1000;

% pourcentage que l'on se fixe
percentage = 0.9;

% p pour les versions 2 et 3 (attention p déjà utilisé comme taille)
puiss = 1;

%##########################################################################

% Calcul des matrices de compression
A = Ir(:, 1:200)' * Ir(:, 1:200);
B = Ig(:, 1:200)' * Ig(:, 1:200);
C = Ib(:, 1:200)' * Ib(:, 1:200);

% Calcul des couples propres
[ V1, D1, n_ev1, it1, itv1, flag1 ] = subspace_iter_v2(A, search_space, percentage, puiss, eps, maxit);
[ V2, D2, n_ev2, it2, itv2, flag2 ] = subspace_iter_v2(B, search_space, percentage, puiss, eps, maxit);
[ V3, D3, n_ev3, it3, itv3, flag3 ] = subspace_iter_v2(C, search_space, percentage, puiss, eps, maxit);

% Calcul des valeurs singulières
S1 = sqrt(D1);
S2 = sqrt(D2);
S3 = sqrt(D3);

% Calcul des vecteurs singuliers
U1 = Ir(:, 1:200) * V1 * inv(S1);
U2 = Ig(:, 1:200) * V2 * inv(S2);
U3 = Ib(:, 1:200) * V3 * inv(S3);

% Vecteur pour stocker la différence entre l'image originale et l'image reconstruite
inter1 = 1:floor(n_ev1/6):6*floor(n_ev1/6);
inter1(end) = 6*floor(n_ev1/6);
differenceSVDr = zeros(size(inter1, 2), 1);
differenceSVDb = zeros(size(inter1, 2), 1);
differenceSVDg = zeros(size(inter1, 2), 1);

% Image reconstruite en utilisant de 1 à 184 vecteurs (avec un pas de 40)
ti = 0;
td = 0;

for k = inter1

    % Calcul de l'image de rang k
    Ir1 = U1(:, 1:k) * S1(1:k, 1:k) * V1(:, 1:k)';
    Ig1 = U2(:, 1:k) * S2(1:k, 1:k) * V2(:, 1:k)';
    Ib1 = U3(:, 1:k) * S3(1:k, 1:k) * V3(:, 1:k)';

    % Affichage de l'image reconstruite
    ti = ti + 1;
    figure(ti)
    imshow(uint8(cat(3, Ir1, Ig1, Ib1)))
    title(['Image reconstruite avec ', num2str(k), ' vecteurs singuliers'])

    % Calcul de la différence entre l'image originale et l'image reconstruite
    td = td + 1;
    differenceSVDr(td) = sqrt(sum(sum((Ir1 - I(:,:,1)).^2)));
    differenceSVDg(td) = sqrt(sum(sum((Ig1 - I(:,:,2)).^2)));
    differenceSVDb(td) = sqrt(sum(sum((Ib1 - I(:,:,3)).^2)));
    pause
end

% Affichage de la différence entre l'image originale et l'image reconstruite
ti = ti + 1;
figure(ti)
hold on
plot(inter1, differenceSVDr, 'r')
plot(inter1, differenceSVDg, 'g')
plot(inter1, differenceSVDb, 'b')
title('Différence entre l''image originale et l''image reconstruite')
legend('Rouge', 'Vert', 'Bleu')
hold off

