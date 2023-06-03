function [X_VS, w, c, code_retour] = SVM_2_souple(X, Y, lambda)
    % Définition des contraintes du problème
    n = size(X, 1);
    H = Y .* (X * X') .* Y';
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
    w = X_VS' * (Alpha_VS .* Y_VS);

    % Calcul du biais c
    indices_c = find(alpha > 1e-6 & beta > 1e-6);
    X_c = X(indices_c,:);
    Y_c = Y(indices_c);
    c = mean(X_c * w - Y_c);

end
