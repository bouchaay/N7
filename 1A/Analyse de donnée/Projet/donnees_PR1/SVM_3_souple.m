function [X_VS,Y_VS,Alpha_VS,c,code_retour] = SVM_3_souple(X, Y,sigma, lambda)
    % Définition des contraintes du problème
    n = size(X, 1);


    % Calcul de la matrice de Gram
    G = zeros(n,n);
    for i = 1:n
        for j = 1:n
            G(i,j) = exp(-(norm(X(i,:)-X(j,:))^2)/(2*sigma^2));
        end
    end


    % Définition des contraintes du problème
    H = G .* (Y*Y');
    f = -ones(n,1);

    Aeq = Y';
    beq = 0;

    lb = zeros(n, 1);
    ub = repmat(lambda,n,1);
    
    % Résolution du problème d'optimisation quadratique
    [alpha,~,code_retour] = quadprog(H, f, [], [], Aeq, beq, lb, ub);
    beta = lambda - alpha;
    

    % Sélection des vecteurs de support
    indices_support = find(alpha > 1e-6);
    X_VS = X(indices_support,:);
    Y_VS = Y(indices_support);
    Alpha_VS = alpha(indices_support);

    % Calcul du biais c
    indices_c = find(alpha > 1e-6 & beta > 1e-6);
    X_c = X(indices_c,:);
    Y_c = Y(indices_c);
    Alpha_c = alpha(indices_c);
    c = mean(mean(G(indices_c,indices_c)*(Alpha_c.*Y_c) - Y_c));
    c = mean(mean(G(indices_support,indices_support)*(Alpha_VS.*Y_VS) - Y_VS));

end
