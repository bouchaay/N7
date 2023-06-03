function [X_VS,Y_VS,Alpha_VS,c,code_retour] = SVM_3(X,Y,sigma)
    n = size(X,1);
    
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
    A = - eye(n);
    b = zeros(n,1);
    Aeq = Y';
    beq = 0;
    
    % Résolution du problème d'optimisation quadratique
    [alpha,~,code_retour] = quadprog(H,f,A,b,Aeq,beq);
    
    % Sélection des vecteurs de support
    indices_support = find(alpha > 1e-6);
    X_VS = X(indices_support,:);
    Y_VS = Y(indices_support);
    Alpha_VS = alpha(indices_support);
    
    % Calcul du biais c
    c = mean(mean(G(indices_support,indices_support)*(Alpha_VS.*Y_VS) - Y_VS));
end
