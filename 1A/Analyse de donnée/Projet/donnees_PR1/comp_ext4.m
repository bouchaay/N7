load donnees_test;
load donnees_app;
% Donnees non filtrees :
X = X_app;
Y = Y_app;

% Plage de valeurs de sigma à explorer
lambda_values = [0.1,0.5,1,5,10,50,100];
sigma_values = [0.00001:0.00002:0.0001,0.0002:0.0002:0.001,0.002:0.002:0.01];

% Initialisation des variables
accuracy_values = zeros(length(lambda_values), length(sigma_values));

% Boucle sur les différentes valeurs de sigma
for i = 1:length(lambda_values)
    for k = 1:length(sigma_values)
        lambda = lambda_values(i);
        sigma = sigma_values(k);
    
        % Exécution du code existant avec la valeur de sigma actuelle
        [X_VS,Y_VS,Alpha_VS,c,code_retour] = SVM_3_souple(X,Y,sigma, lambda);
    
        % Vérification de la convergence de l'optimisation
        if code_retour ~= 1
            continue; % Passer à la prochaine itération si l'optimisation n'a pas convergé
        end
    
        % Pourcentage de bonnes classifications des donnees de test :
        load donnees_test;
        nb_donnees_test = size(X_test,1);
        nb_classif_OK = 0;
        for j = 1:nb_donnees_test
	        x_i = X_test(j,:);
	        prediction = sign(exp(-sum((X_VS-x_i).^2,2)/(2*sigma^2))'*diag(Y_VS)*Alpha_VS-c);
	        if prediction==Y_test(j)
		        nb_classif_OK = nb_classif_OK+1;
	        end
        end
        accuracy_values(i, k) = double(nb_classif_OK/nb_donnees_test*100);
    end
end

% Tracé du pourcentage de bonnes classifications en fonction de sigma et lambda
[lambda_grid, sigma_grid] = meshgrid(lambda_values, sigma_values);
figure;
surf(lambda_grid', sigma_grid', accuracy_values);
xlabel('Valeur de lambda');
ylabel('Valeur de sigma');
zlabel('Pourcentage de bonnes classifications');
title('Pourcentage de bonnes classifications en fonction de lambda et sigma');

Q = floor(find(accuracy_values == max(accuracy_values,[],"all"))./repmat(7,1)) + 1;

indices_min = [mod(find(accuracy_values == max(accuracy_values,[],"all")), 7), Q];