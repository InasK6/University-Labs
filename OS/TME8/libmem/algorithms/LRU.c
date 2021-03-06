#include "LRU.h"
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>


//Variable globale incrémenté à chaque accès à n'importe quel case
int compteur=0;

int	initLRU(Swapper*);
void	referenceLRU(Swapper*,unsigned int frame);
unsigned int chooseLRU(Swapper*);
void	finalizeLRU(Swapper*);

int initLRUSwapper(Swapper*swap,unsigned int frames){
	initSwapper(swap,frames,initLRU,referenceLRU,chooseLRU,finalizeLRU);
}

int	initLRU(Swapper*swap){
	/* Allouer un tableau avec un entier pour chaque case, il est mis à jour à chaque utilisation de la case avec la valeur courante de compteur */
	swap->private_data = calloc(swap->frame_nb,sizeof(int));
	int * use = (int*)swap->private_data;
	int i;
	/* Initialisation du tableau */
	for( i=0 ; i<swap->frame_nb ; i++ )
		use[i] = 0;
	return 0;
}

/*
appelée à chaque accès à une case d'indice frame
 */
void	referenceLRU(Swapper*swap,unsigned int frame){
	int i;
	int * use = (int*)swap->private_data;
	/* A chaque acces à la case frame augmente son compteur d'utilisation */
	compteur++;
	use[frame]=compteur;
}

unsigned int chooseLRU(Swapper*swap){
	int i, frame = 0;
	int * use = swap->private_data;
	/* Choisir la case (contenant une page)  ayant la variable associée la plus petite */
	
	for ( i=0 ; i<swap->frame_nb ; i++ ){
		if( swap->frame[i] == -1 ){
			frame = i;
			break;
		}
		// récupération de la valeur minimum
		if( use[i] < use[frame] )
			frame = i;
	}

	//use[frame] = 0;

	DEBUG(
		fprintf(stderr,"LRU uses: ");
		printUse(swap);
		fprintf(stderr,"\n\n");
	);
	
	return frame;
}

void	finalizeLRU(Swapper*swap){
	free(swap->private_data);
}
/*
3. Comparaison LRU/ FIFO:
pour le fichier bench donné, on a le même taux de défaut de pages 4/6 pour les deux stratégies
pour le fichiers bench2 qui correspond à la séquence étudiée en TD, la stratégie LRU est meilleure
LRU= 10/15 < FIFO= 12/15
 */
