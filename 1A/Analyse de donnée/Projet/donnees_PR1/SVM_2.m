function [X_VS,w,c,code_retour] = SVM_2(X,Y)
    n = length(Y);
    H = Y .* (X * X') .* Y';
    A = - eye(n);
    b = zeros(n,1);
    f = - ones(n,1);
    beq = 0;
    Aeq = Y';
    [alpha,~,code_retour ] = quadprog(H,f,A,b,Aeq,beq);


    indices_support = find(alpha > 1e-6);
    X_VS = X(indices_support,:);
    Y_VS = Y(indices_support);
    Alpha_VS = alpha(indices_support);
    w = X_VS' * (Alpha_VS .* Y_VS);



    % Calcul du biais c
    c = mean(X_VS * w - Y_VS);


end

