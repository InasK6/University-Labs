#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <libipc.h>
/*
Mécanismes d'exclusion mutuelle
- Sémaphore <emet>: bloque les emetteurs tant qu"il n'y a pas de case dans laquelle ils puissent écrire,
   initialisé à 1 car au début, le tampon est vide
- tableau de sémaphores <RECEP[1...NR]> pour bloquer les récepteurs en cas de case vide, et faire en sorte qu'il ne fasse qu'une seule lecture par donnée émise. En effet, comme les emetteurs et récepteurs sont cycliques, si on utilisait un seul sémaphore et qu'on l'augmentait de NR lors de l'emission d'un message, on risque de se retrouver avec un récepteur qui lit une donnée plusieurs fois et d'autres qui ne la lisent jamais. 
   Toutes les sémaphores du tableau sont initialisés à 0. 
- variable partagée "nb_recepteurs", indique le nombre de consommateurs, sert à réveiller un emetteur quand une donnée a été consommée par tous les consommateurs, elle est protégée par un sémaphore d'exclusion mutuelle <mutex>
  C'est donc le dernier consommateur de la donnée qui prévient les emetteurs de la disponibilité de la case.
 */

#define NE 2
#define NR 3

void emettre(){
  while(true) {

  }
}

void recevoir(){
  while (true){

  }
}

int main(int argc, char **argv){
  if(  creer_sem(1)==-1){
    printf("problème de création de sémaphore !!\n");
    exit(1);
  }

  det_sem();
  return 0;
}
