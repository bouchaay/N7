clear all;
close all;
format long;

% Figures des spectres des 4 types de matrices

figure;

load A_200_1.mat
subplot(2, 2, 1);
D = sort(D, 'descend');
plot(D, 'r');
ylabel("Valeurs propres");
title("Matrice Type-1");
legend("Type-1")
grid on;

load A_200_2.mat
subplot(2, 2, 2);
D = sort(D, 'descend');
plot(D, 'b');
ylabel("Valeurs propres");
title("Matrice Type-2");
legend("Type-2")
grid on;

load A_200_3.mat
subplot(2, 2, 3);
D = sort(D, 'descend');
plot(D, 'g');
ylabel("Valeurs propres");
title("Matrice Type-3");
legend("Type-3")
grid on;

load A_200_4.mat
subplot(2, 2, 4);
D = sort(D, 'descend');
plot(D, 'y');
ylabel("Valeurs propres");
title("Matrice Type-4");
legend("Type-4")
grid on;