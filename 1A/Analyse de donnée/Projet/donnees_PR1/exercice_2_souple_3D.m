clear;
close all;
clc;



% Parametres pour l'affichage des donnees :
taille_ecran = get(0,'ScreenSize');
L = taille_ecran(3);
H = taille_ecran(4);

load donnees_train_3caracteristiques.mat;

% Donnees non filtrees :
X = X_train;
Y = Y_train;

% Parametres d'affichage :
pas = 0.008;
marge = 0.005;
valeurs_carac_1 = min(min(X(:,1)))-marge:pas:max(max(X(:,1)))+marge;
valeurs_carac_2 = min(min(X(:,2)))-marge:pas:max(max(X(:,2)))+marge;
valeurs_carac_3 = min(min(X(:,3)))-marge:pas:max(max(X(:,3)))+marge;
%limites_affichage = [valeurs_carac_1(1) valeurs_carac_1(end) valeurs_carac_2(1) valeurs_carac_2(end)];
nom_carac_1 = 'Compacite';
nom_carac_2 = 'Contraste';
nom_carac_3 = 'Texture';

% Estimation du SVM avec noyau gaussien :
lambda = 100;
[X_VS,w,c,code_retour] = SVM_2_souple(X, Y, lambda);

% Si l'optimisation n'a pas converge :
if code_retour ~= 1
	return;
end

% Regle de decision du SVM :
nb_1 = length(valeurs_carac_1);
nb_2 = length(valeurs_carac_2);
nb_3 = length(valeurs_carac_3);
SVM_predict = zeros(nb_3,nb_2,nb_1);

K1 = [0 0 0];
K2 = [];
for i = 1:nb_1
	for j = 1:nb_2
        for k = 1:nb_3
		    x_ijk = [valeurs_carac_1(i) valeurs_carac_2(j) valeurs_carac_3(k)];
		    %SVM_predict(k,j,i) = sign(exp(-sum((X_VS-x_ijk).^2,2)/(2*sigma^2))'*diag(Y_VS)*Alpha_VS-c);
            if sign(sign(w'*x_ijk'-c)) > 0
                K1 = [K1; x_ijk];
            else
                K2 = [K2; x_ijk];
            end
        end
	end
end

% Affichage des classes predites par le SVM :
%[valeurs_carac_1_grid, valeurs_carac_2_grid,valeurs_carac_3_grid] = meshgrid(valeurs_carac_1, valeurs_carac_2,valeurs_carac_3);

% Plot the points where SVM_predict is 1 with blue
scatter3(K1(:, 1), K1(:, 2), K1(:, 3), 'b');
hold on;
% Plot the points where SVM_predict is -1 with red
scatter3(K2(:, 1), K2(:, 2), K2(:, 3), 'r','filled');
% Set labels for the axes
xlabel(nom_carac_1);
ylabel(nom_carac_2);
zlabel(nom_carac_3);
% Set the title for the plot
title('SVM Predictions');
% Show the grid
grid on;
% Show the legend
legend('SVM Predict = 1', 'SVM Predict = -1');
hold off;


% Pourcentage de bonnes classifications des donnees de test :
load donnees_test_3caracteristiques.mat;
nb_donnees_test = size(X_test,1);
nb_classif_OK = 0;
for i = 1:nb_donnees_test
	x_i = X_test(i,:);
	prediction = sign(exp(-sum((X_VS-x_i).^2,2)/(2*sigma^2))'*diag(Y_VS)*Alpha_VS-c);
	if prediction==Y_test(i)
		nb_classif_OK = nb_classif_OK+1;
	end
end
fprintf('Pourcentage de bonnes classifications des donnes de test : %.1f %%\n',double(nb_classif_OK/nb_donnees_test*100));
