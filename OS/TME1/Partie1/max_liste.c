#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>


//Question 1
int main(int argc, char ** argv){

  // on récupère la liste dans argv[1]
  if( argc<2){
    printf("il manque des arguments !! liste vide");
    
  }

  else{
    int i=0;
    int max=atoi(argv[1]);
    for( i=2; i<argc; i++){
      if(atoi(argv[i])>max){
	max=atoi(argv[i]);
      }
    }

    printf(" le maximum est:%d  ",max);
  }
  return 0; 
}

  /*
    commandes d'execution: gcc -o max_liste.c
   */
