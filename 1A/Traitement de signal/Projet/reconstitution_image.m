%{ 
Réalisation d un modem de fréquence selon la recommandation V21 de 
           l'Union Internationale des Télécommunications (UIT)
---------------------------------------------------------------------------
Ecole : INP-ENSEEIHT
Auteurs : Ayoub BOUCHAMA & Oussama ELGUERRAOUI
Promotion : 2022/2023
Groupe : F
Partie : Fonction de reconstitution de l'image
---------------------------------------------------------------------------
%}

function image_retrouvee = reconstitution_image(suite_binaire_reconstruite)

% Reconstruction de l'image à partir de la suite de bits retrouvée
mat_image_binaire_retrouvee = reshape(suite_binaire_reconstruite,105*100,8);
mat_image_decimal_retrouvee = bi2de(mat_image_binaire_retrouvee);
image_retrouvee = reshape(mat_image_decimal_retrouvee,105,100);