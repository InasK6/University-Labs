#include <stdio.h>
#include "affiche_tas.h"
#include <ctype.h>

void afficher_tas(){
    int i, j;
    for (i = 0; i < 8; i++) {
        for (j = 0; j < 16; j++) { 
	    printf("%4d", j + 16*i);
        }
        printf("\n");
        for (j = 0; j < 16; j++) { 
	    printf("%4d", tas[j + 16*i]);
        }
        printf("\n");
        for (j = 0; j < 16; j++) { 
	    if (isalnum(tas[j + 16*i])) {
	        printf("%4c", tas[j + 16*i]);
	    } else {
		printf("    ");
	    }
        }
        printf("\n\n");
    }
    printf("---------------------------------------------------------------\n\n");
}

void tas_init(){
  libre = 0;
  tas[0] = TAILTAS;
  tas[1] = -1;
}
/*
En résonnant, je déduis quand dans pred on devrait mettre l'adresse mémoire de la zone libre précédant celle alouée, car il sera utile lors de l'allocation de changer la valeur de sa prochaine zone libre 
si aucune zone n'a été allouée, ou si notre zone allouée est la première zone libre on met dans pred je mets -1 aussi, on n'aura pas à faire de modification dans ce case de toute façon
 */
int first_fit(int taille, int *pred){
  int p=libre;
  *pred=-1;
  while(tas[p]<taille){
    *pred=p;
    p=tas[p+1];
    // si la taille est inférieure et qu'on est arrivé au dernier bloc 
    if(p==-1){
      *pred=-1;
      return -1;
    }

  }
  return p;
}

char* tas_malloc(unsigned int taille){
  int pred=-2;
  int adr=first_fit(taille,&pred);
  int next=-1;
  if(adr==-1){
    printf("Allocation impossible, pas de place disponible\n");
    return NULL;
  }
  int var=tas[adr];
  // Si la taille disponible est inférieure à celle du tas, on crée un nouveau bloc vide, sinon on remplit le bloc et on aura un bloc libre en moins, il faudra donc ensuite prendre en comple le déplacement de la valeur de libre
  if(taille<tas[adr]){
    next=adr+taille+1;
     //on retire 1 car une case est automatiquement allouée pour stocker la taille mémoire
    tas[next]=tas[adr]-taille-1;
    if(tas[next]>0){
      //si la taille du bloc est plus grande que 0, alors ce n'est pas une miette, le résultat est considéré comme un nouveau bloc vide, dans ce cas, il faut le relier au bloc précédent si ce n'est pas le premier bloc vide
      tas[next+1]= tas[adr+1];
      // si le bloc qu'on vient de remplir était le premier bloc libre
      if(libre==adr){
	libre=next;
      }
      //sinon on doit relier le bloc actuel au bloc précédent
      else{
	tas[pred+1]=next;
      }
    }

    tas[adr]=taille;
    
    
  }
  // si le bloc actuel est totalement rempli ou s'il reste seulement une miette, on l'ignore et on la saute
  if(taille==var || tas[next]==0){
    next=tas[adr+1];
    if(libre==adr){
	libre=next;
      }
      //sinon on doit relier le bloc actuel au bloc précédent
      else{
	tas[pred+1]=next;
      }
  }
  tas[adr+1]=0;

  printf("%d libre à la fin de malloc\n", libre );

  return &tas[adr+1];
}

//libère la zone dont le début est désigné par ptr
int tas_free(char *ptr){
  int i=0;
  //Si on veut libérer une zone pleine qui se situe avant la première zone libre
  if ( ptr<&tas[libre]){
    *(ptr)=libre;
    //on met à 0 pour libèrer le reste des cases
    for(i=1; i<*(ptr-1); i++){
      *(ptr+i)=0;
    }
   
    libre =ptr-tas-1;
    
    
  }
  // sinon, il faut chercher la dernière zone libre avant ptr afin de la relier à ptr
  // On simule un parcours de liste chaînée
  else{
  int var=libre;
  
  while(&tas[tas[var+1]]<ptr){
    var=tas[var+1];
   
  }

  
  // on se retrouve avec var tel que notre zone libre est située entre tas[var] et tas[var+1]
  *(ptr)=tas[var+1];
  

   for(i=1; i<*(ptr-1); i++){
      *(ptr+i)=0;
    }
  tas[var+1]=ptr-tas-1;
  }
  printf("%d libre à la fin de tas_free\n", libre);
  return -1;
}



