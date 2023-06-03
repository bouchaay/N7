function [X_VS,w,c,code_retour] = SVM_1(X,Y)
    H = eye(3);
    H(3,3) = 0;
    A = [-X.*Y Y];
    b = - ones(length(Y),1);
    f = zeros(3,1);
    [w_tilde,~,code_retour ] = quadprog(H,f,A,b);
    w = [w_tilde(1);w_tilde(2)];
    c = w_tilde(3);
    X_VS = X((abs(A*w_tilde - b) < 10e-6), :);

end

