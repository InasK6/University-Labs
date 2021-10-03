#include "FIFO.h"
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>




int numCase=0;



int initFIFO(Swapper*);
/*
Allouer un tableau avec l'ordre de chargement associé à chaque case
 */
void referenceFIFO(Swapper*, unsigned int frame);
unsigned int chooseFIFO(Swapper *);
/*
On n'en a pas besoin car on n'a pas de mémoire à désallouer à la fin 
void finalizeFIFO(Swapper*);
 */

int initFIFOSwapper(Swapper *swap, unsigned int frames){
  initSwapper(swap, frames, initFIFO,NULL, chooseFIFO,NULL);
}


int initFIFO(Swapper *swap){
  // On n'a pas besoin de private data à allouer pour cette stratégie
  printf("initialisation FIFO\n");
  return 0;
}




unsigned int chooseFIFO(Swapper *swap){
  int i;
  //s'il y a des cases libres, on les prend, sinon quand il n'y a plus de case vide, on en choisit une une case à évincer selon son ordre d'arrivée

  // On cherche une case libre
  for( i=0; i<swap->frame_nb; i++){
    if(swap->frame[i]==-1){
      return i;
    } 
  }
  // à la fin de cette boucle, toutes les cases sont pleines, on retire la première ajoutée
  int j=numCase;
  numCase=(numCase+1)%swap->frame_nb;
  return j;
				
}

