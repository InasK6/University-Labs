#include "libmem.h"
#include "Random.h"
#include "LFU.h"
#include "FIFO.h"
#include "LRU.h"

int main(int argc,char*argv[]){
	unsigned int frame_nb;
	Swapper	s;	

	scanf("%i",&frame_nb);

	/* Initialisation du Swapper de la stratégie LFU */
	//initRandomSwapper(&s,frame_nb);
	
	//initLFUSwapper(&s, frame_nb);

	initFIFOSwapper(&s, frame_nb);

	//initLRUSwapper(&s, frame_nb);
	
	if(swapSimulation(&s,stdin)<0){
		printf("Error during swap simulation !!!\n");
		return -1;
	}

	return 0;
}
