% SVM à noyau gaussien
function [X_VS,Y_VS,Alpha_VS,c,code_retour] = SVM_3(X,Y,sigma)

    % Variables
    beq = 0;
    eps = 1e-6;
    n = size(X,1);
    G = zeros(n,n);
    lb = zeros(n,1);
    ub = ones(n,1);
    f = zeros(n,1);

    % Calcul de la matrice de Gram G
    for i=1:n
        for j=1:n
            G(i,j) = exp(-norm(X(i,:)-X(j,:))^2/(2*sigma^2));
        end
    end

    % Résolution du problème d'optimisation
    H = (Y*Y').*G;
    Aeq = Y';
    options = optimoptions('quadprog','MaxIterations',5000);
    [Alpha_VS,~,code_retour] = quadprog(H,f,[],[],Aeq,beq,lb,ub,[],options);

    % Calcul de c et des vecteurs de support
    X_VS = X(Alpha_VS>eps,:);
    Y_VS = Y(Alpha_VS>eps);
    Alpha_VS = Alpha_VS(Alpha_VS>eps);
    c = mean(Y_VS - G(Alpha_VS>eps,:)*(Alpha_VS.*Y_VS));
end
