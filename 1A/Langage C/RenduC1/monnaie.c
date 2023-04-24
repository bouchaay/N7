// Auteur : Ayoub BOUCHAMA

#include <stdlib.h> 
#include <stdio.h>
#include <assert.h>
#include <stdbool.h>
#define CAPACITE 5
// Definition du type monnaie
struct t_monnaie{
    float valeur;
    char devise;
};
typedef struct t_monnaie t_monnaie; 

/**
 * \brief Initialiser une monnaie 
 * \param[]
 * \pre 
 * // valeur>0.0;
 * /post 
 * /(*monnaie).valeur==valeur && (*monnaie).devise==devise
 */ 
void initialiser(t_monnaie* monnaie, float valeur, char devise) {
    assert(valeur>=0.0);
    (*monnaie).valeur=valeur;
    (*monnaie).devise=devise;
}


/**
 * \brief Ajouter une monnaie m2 à une monnaie m1 
 * \param[]
 * / pre
  \m1.devise==m2.devise
 * / post
   \m2.valeur=m2'old.valeur+m1.valeur **/
bool ajouter(t_monnaie m1, t_monnaie* m2) {
    if (m1.devise==(*m2).devise){
        (*m2).valeur+=m1.valeur;
        return 1;
    }
    return 0;
}


/**
 * \brief Tester Initialiser 
 * \param[]
 * // pre
 * valeur >0.0;
 **/ 
void tester_initialiser() {
    t_monnaie* monnaie=NULL;
    float valeur=75.2;
    char devise='$';
    assert(valeur>=0.0);
    initialiser(monnaie,valeur,devise);
    assert((*monnaie).valeur=75.2);
    assert((*monnaie).devise='$');
}

/**
 * \brief Tester Ajouter 
 * \param[]
* \param[]
 * / pre
  \m1.devise==m2.devise
 * / post
   \m2.valeur=m2'old.valeur+m1.valeur **/
void tester_ajouter() {
    t_monnaie m1;
    t_monnaie* m2=NULL;
    initialiser(&m1,42.3,'$');
    initialiser(m2,23.1,'$');
    ajouter(m1,m2);
    assert((*m2).valeur=42.3+23.1);
}



int main(void) {
    // Déclarer un tableau de 5 monnaies
    t_monnaie porte_monnaie[CAPACITE];
    
    //Initialiser les monnaies
    float valeur;
    char devise;
    for (int i=1;i<=5;i++){
        printf("Saisissez la valeur de votre monnaie numéro %d : ", i);
        scanf(" %f", &valeur); 
        printf("\n");
        printf("Saisissez la devise de cette monnaie : ");
        scanf(" %c", &devise);
        printf("\n");
        initialiser(&porte_monnaie[i-1],valeur,devise);
    }
        
    // Afficher la somme de toutes les monnaies qui sont dans une devise entrée par l'utilisateur.
    printf("Saisissez la devise des monnaies que vous voulez sommer : ");
    scanf(" %c", &devise);
    printf("\n");
    t_monnaie somme;
    initialiser(&somme,0.0,devise);
    for (int i=1;i<=5;i++){
        if (porte_monnaie[i-1].devise==devise){
            ajouter(porte_monnaie[i-1],&somme);
        }
    }
    printf("La somme de ces monnaies est : %1.2f\n", somme.valeur);
    return EXIT_SUCCESS;
}
