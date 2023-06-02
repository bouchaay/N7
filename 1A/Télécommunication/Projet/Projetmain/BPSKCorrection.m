% -------------------------------------------------------------------------
%                 Chaine de transmission avec correction
% -------------------------------------------------------------------------
%                                Ayoub Bouchama
%                              Oussama ElGuerraoui
%                                  'Groupe F' 
% -------------------------------------------------------------------------

close all;
clc;
clear;

% Les paramètres
Phi_0 = 0;
Phi_40 = (40*pi)/180;
Phi_100 = (100*pi)/180;
Echelle = 0:6;

%% Calculer les taux d'erreur binaires

% Phase 0°
[TEB_ORIGINAL_0, ~] = Correction(Phi_0);

% Phase 40°
[TEB_ORIGINAL_40, TEB_CORRIGE_40] = Correction(Phi_40);

% Phase 100°
[TEB_ORIGINAL_100, TEB_CORRIGE_100] = Correction(Phi_100);

%% Afficher les résultats (Tracés)

% Tracé du TEB original et corrigé pour la phase 40°
figure(1);
semilogy(Echelle, TEB_ORIGINAL_40, 'b');
hold on;
semilogy(Echelle, TEB_CORRIGE_40, 'r');
semilogy(Echelle, TEB_ORIGINAL_40, 'b*');
semilogy(Echelle, TEB_CORRIGE_40, 'r*');
title('TEB original et corrigé pour la phase 40°');
xlabel('SNR (dB)');
ylabel('TEB');
legend('TEB original', 'TEB corrigé');
grid on;

% Tracé du TEB original et corrigé pour la phase 100°
figure(2);
semilogy(Echelle, TEB_ORIGINAL_100, 'b');
hold on;
semilogy(Echelle, TEB_CORRIGE_100, 'r');
semilogy(Echelle, TEB_ORIGINAL_100, 'b*');
semilogy(Echelle, TEB_CORRIGE_100, 'r*');
title('TEB original et corrigé pour la phase 100°');
xlabel('SNR (dB)');
ylabel('TEB');
legend('TEB original', 'TEB corrigé');
grid on;

% Tracé des TEB originaux et corrigés dans le même graphe
figure(3);
semilogy(Echelle, TEB_ORIGINAL_0, 'b');
hold on;
semilogy(Echelle, TEB_ORIGINAL_40, 'r');
semilogy(Echelle, TEB_CORRIGE_40, 'y');
semilogy(Echelle, TEB_ORIGINAL_100, 'g');
semilogy(Echelle, TEB_CORRIGE_100, 'm');
semilogy(Echelle, TEB_ORIGINAL_0, 'b*');
semilogy(Echelle, TEB_ORIGINAL_40, 'r*');
semilogy(Echelle, TEB_CORRIGE_40, 'y*');
semilogy(Echelle, TEB_ORIGINAL_100, 'g*');
semilogy(Echelle, TEB_CORRIGE_100, 'm*');
title('TEB original et corrigé pour les phases 0°, 40° et 100°');
xlabel('SNR (dB)');
ylabel('TEB');
legend('TEB original 0°', 'TEB original 40°', 'TEB corrigé 40°', 'TEB original 100°', 'TEB corrigé 100°');
grid on;

