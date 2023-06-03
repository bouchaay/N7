clear;
close all;

load donnees_test;
load donnees_app;
% Donnees non filtrees :
X = X_app;
Y = Y_app;

% Plage de valeurs de sigma à explorer
lambda_values = [0.1,0.5,1,5,10,50,100];

% Initialisation des variables
accuracy_values = zeros(size(lambda_values));

% Boucle sur les différentes valeurs de sigma
for i = 1:length(lambda_values)
    lambda = lambda_values(i);

    % Exécution du code existant avec la valeur de sigma actuelle
    [X_VS,w,c,code_retour] = SVM_2_souple(X,Y, lambda);

    % Vérification de la convergence de l'optimisation
    if code_retour ~= 1
        continue; % Passer à la prochaine itération si l'optimisation n'a pas convergé
    end

    % Pourcentage de bonnes classifications des donnees de test :
    nb_donnees_test = size(X_test,1);
    nb_classif_OK = 0;
    for j = 1:nb_donnees_test
	    x_i = X_test(j,:)';
	    prediction = sign(w'*x_i-c);
	    if prediction==Y_test(j)
		    nb_classif_OK = nb_classif_OK+1;
	    end
    end
    accuracy_values(i) = double(nb_classif_OK/nb_donnees_test*100);
end

% Tracé du pourcentage de bonnes classifications en fonction de lambda
figure;
plot(lambda_values, accuracy_values, 'bo-');
grid on;
xlabel('Valeur de sigma');
ylabel('Pourcentage de bonnes classifications');
title('Pourcentage de bonnes classifications en fonction de sigma');
