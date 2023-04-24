/**
 *  \author Xavier Cr�gut <nom@n7.fr>
 *  \file file.c
 *
 *  Objectif :
 *	Implantation des op�rations de la file
*/

#include <malloc.h>
#include <assert.h>

#include "file.h"


void initialiser(File *f) {
    f->tete = NULL;
    f->queue = NULL;
    assert(est_vide(*f));
}

void detruireCellule(Cellule *c) {
    if (c != NULL) {
        detruireCellule(c->suivante);
        free(c);
        c = NULL;
    }
}

void detruire(File *f) {
    if (f != NULL) {
        detruireCellule(f->tete);
        f->tete = NULL;
        f->queue = NULL;
        f = NULL;
    }
}

char tete(File f) {
    assert(! est_vide(f));
    return f.tete->valeur;
}

bool est_vide(File f) {
    return f.tete == NULL;
}

/**
 * Obtenir une nouvelle cellule allou�e dynamiquement
 * initialis�e avec la valeur et la cellule suivante pr�cis� en param�tre.
 */
static Cellule * cellule(char valeur, Cellule *suivante) {
    Cellule *c = malloc(sizeof(Cellule));
    c->valeur = valeur;
    c->suivante = suivante;
    return c;
}

// inserer dans la file
void inserer(File *f, char v) {
    assert(f != NULL);
    Cellule *c = cellule(v, NULL);
    if (est_vide(*f)) {
        f->tete = c;
    } else if (f->tete->suivante == NULL) {
        f->tete->suivante = c;
    } else {
        f->queue->suivante = c;
    }
}

void extraire(File *f, char *v) {
    assert(f != NULL);
    assert(! est_vide(*f));
    Cellule *c = f->tete;
    *v = c->valeur;
    f->tete = c->suivante;
    free(c);
}

int longueur(File f) {
    int n = 0;
    Cellule *c = f.tete;
    while (c != NULL) {
        n++;
        c = c->suivante;
    }
    return n;
}
