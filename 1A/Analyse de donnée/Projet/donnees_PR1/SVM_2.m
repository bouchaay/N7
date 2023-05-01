% SVM pour formulation lineaire (formulation duale)
function [X_VS,w,c,code_retour] = SVM_2(X,Y)

    % Variables
    eps = 1e-6;
    n = length(X);
    f = -ones(n,1);
    lb = zeros(n,1);

    % Resolution du probleme d'optimisation
    H = (Y.*X)*(Y.*X)';
    Aeq = Y';
    [alpha,~,code_retour] = quadprog(H,f,[],[],Aeq,0,lb,[]);

    % Calcul de w et c
    w = X'*(alpha.*Y);
    X_VS = X(alpha > eps,:);
    Y_VS = Y(alpha > eps,:);
    c = mean(X_VS*w - Y_VS);
end