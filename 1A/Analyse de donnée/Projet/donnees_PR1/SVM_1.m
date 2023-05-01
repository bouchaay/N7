% SVM pour formation linéaire (formulation primale)
function [X_VS,w,c,code_retour] = SVM_1(X,Y)

    % Variables
    eps = 1e-6;
    H = [1 0 0; 0 1 0; 0 0 0];
    f = [0 0 0]';
    b = -ones(length(X),1);

    % Résolution du problème d'optimisation
    A = -Y.*[X b];
    [argmin, ~, code_retour] = quadprog(H,f,A,b);

    % Calcul de w et c
    w = argmin(1:2);
    c = argmin(3);
    X_VS = X(abs(A*argmin - b) < eps,:);
end