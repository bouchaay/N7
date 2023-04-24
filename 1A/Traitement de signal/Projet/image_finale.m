%{ 
Réalisation d un modem de fréquence selon la recommandation V21 de 
           l'Union Internationale des Télécommunications (UIT)
---------------------------------------------------------------------------
Ecole : INP-ENSEEIHT
Auteurs : Ayoub BOUCHAMA & Oussama ELGUERRAOUI
Promotion : 2022/2023
Groupe : F
Partie : Reconstruction de l'image finale
---------------------------------------------------------------------------
%}

% Reconstruire l'image finale.

load fichier1.mat;
img1 = demoduler(signal);
load fichier2.mat;
img2 = demoduler(signal);
load fichier3.mat;
img3 = demoduler(signal);
load fichier4.mat;
img4 = demoduler(signal);
load fichier5.mat;
img5 = demoduler(signal);
load fichier6.mat;
img6 = demoduler(signal);

img1_rec = reconstitution_image(img1);
img2_rec = reconstitution_image(img2);
img3_rec = reconstitution_image(img3);
img4_rec = reconstitution_image(img4);
img5_rec = reconstitution_image(img5);
img6_rec = reconstitution_image(img6);

img_final = [img6_rec img1_rec img5_rec ; img2_rec img4_rec img3_rec];
image(img_final);