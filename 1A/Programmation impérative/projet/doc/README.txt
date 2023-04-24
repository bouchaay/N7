Auteurs du projet :	|
=> Ayoub BOUCHAMA	|
=> Oussama ELGUERRAOUI	|
=> Achraf MERZOUGUI	|
*************************

********************************************************************************************************************************|
- Step 1 : *** Tester les modules necessaires au fonctionnement du routeur ***							|
=> Compiler puis exécuter les modules de tests : test_lc, test_cli, test_ip_adress, test_table_routage.				|
																|
- Step 2 : *** Tester le fonctionnement du routeur_ll (avec cache version liste chainée) ***					|
=> Compiler puis exécuter le module test_cache_ll.										|
=> Compiler puis lancer le module routeur_ll avec les commandes exemples suivantes :						|
	>> ./routeur_ll -r fichiers_txt/resultats.txt -p fichiers_txt/paquets.txt -t fichiers_txt/table.txt			|
	>> ./routeur_ll -c 2 -r fichiers_txt/resultats.txt -p fichiers_txt/paquets.txt -t fichiers_txt/table.txt -s		|
	>> ./routeur_ll -c 3 -p fichiers_txt/paquets.txt -t fichiers_txt/table.txt -p LRU -r fichiers_txt/resultats.txt	|
																|
- Step 3 : *** Tester le fonctionnement du routeur_la (avec cache version arbre) ***						|
=> Compiler puis exécuter le module test_cache_la.										|
=> Compiler puis lancer le module routeur_la avec les commandes exemples suivantes :						|
	>> ./routeur_la -r fichiers_txt/resultats.txt -p fichiers_txt/paquets.txt -t fichiers_txt/table.txt			|
	>> ./routeur_la -c 2 -r fichiers_txt/resultats.txt -p fichiers_txt/paquets.txt -t fichiers_txt/table.txt -s		|
	>> ./routeur_la -c 3 -p fichiers_txt/paquets.txt -t fichiers_txt/table.txt -p LRU -r fichiers_txt/resultats.txt	|
																|	
- Step 4 : Fermer les dossiers ouverts, fermer le terminal et retrouver les resultats du routage dans le fichier resultats.txt	|
*********************************************************************************************************************************	
	
		

