%{ 
Réalisation d un modem de fréquence selon la recommandation V21 de 
           l'Union Internationale des Télécommunications (UIT)
---------------------------------------------------------------------------
Ecole : INP-ENSEEIHT
Auteurs : Ayoub BOUCHAMA & Oussama ELGUERRAOUI
Promotion : 2022/2023
Groupe : F
Partie : Définition de la fonction dirac
---------------------------------------------------------------------------
%}

function y = dirac1(x)
 
   y = zeros(size(x));
   y(x==0) = 1 ; 
end
