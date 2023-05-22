close all;
clc;
clear;

% Les paramètres
Phi_40 = (40*pi)/180;
Phi_100 = (100*pi)/180;
Echelle = 0:6;

%% Calculer les taux d'erreur binaire

% Phase 40°
[~, TEB_CORRIGE_40] = Correction(Phi_40);
[TEB_CODE_40, TEB_CORRIGE_CODE_40] = Codage(Phi_40);

% Phase 100°
[~, TEB_CORRIGE_100] = Correction(Phi_100);
[TEB_CODE_100, TEB_CORRIGE_CODE_100] = Codage(Phi_100);

%% Afficher les résultats (Tracés)

% Tracé du TEB corrigé, codé et corrigé+codé pour la phase 40°
figure(1);
semilogy(Echelle, TEB_CORRIGE_40, 'b');
hold on;
semilogy(Echelle, TEB_CODE_40, 'r');
semilogy(Echelle, TEB_CORRIGE_CODE_40, 'g');
semilogy(Echelle, TEB_CORRIGE_40, '*');
semilogy(Echelle, TEB_CODE_40, '*');
semilogy(Echelle, TEB_CORRIGE_CODE_40, '*');
title('TEB corrigé, codé et corrigé+codé pour la phase 40°');
xlabel('SNR (dB)');
ylabel('TEB');
legend('TEB corrigé', 'TEB codé', 'TEB corrigé+codé');
grid on;

% Tracé du TEB corrigé, codé et corrigé+codé pour la phase 100°
figure(2);
semilogy(Echelle, TEB_CORRIGE_100, 'b');
hold on;
semilogy(Echelle, TEB_CODE_100, 'r');
semilogy(Echelle, TEB_CORRIGE_CODE_100, 'g');
semilogy(Echelle, TEB_CORRIGE_100, '*');
semilogy(Echelle, TEB_CODE_100, '*');
semilogy(Echelle, TEB_CORRIGE_CODE_100, '*');
title('TEB corrigé, codé et corrigé+codé pour la phase 100°');
xlabel('SNR (dB)');
ylabel('TEB');
legend('TEB corrigé', 'TEB codé', 'TEB corrigé+codé');
grid on;