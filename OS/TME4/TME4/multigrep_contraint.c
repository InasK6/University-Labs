#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int min(int a, int b){
  if(a<b){
    return a;
  }
  else
    return b;
}
int max(int a, int b){
  if(a>b){
    return a;
  }
  else
    return b;
}
//TME4
//Partie 2
int main(int argc , char ** argv){

  // autant de processus fils que de fichiers
  // argv[0] reprèsente l'executable
  // argv[1] représente la chaine de caractère
  // pour i>1 argc[i] représente un nom de fichier
  if(argc<3){
    printf("few arguments, try <executable>  <string>  <files names>\n");
  }
  int MAXFILS=1;
  int NB_FILES= argc-2;
  int FILES_ANALYSED=0;

  int i=0;
  char * CHAINE=argv[1];
  while (i<min(NB_FILES, MAXFILS) ){
    if(fork()==0){
      execlp("grep", "grep", CHAINE, argv[i+2], NULL);
    }
    i++;
  }
  FILES_ANALYSED=min(NB_FILES, MAXFILS);
  //attente de la fin des processus
  int j=0;
  for(j=0; j<max(MAXFILS,NB_FILES)  ; j++){
    wait(NULL);
    if(NB_FILES-FILES_ANALYSED>0){
      // j'incrémente avant car je sais que le fichier va être analysé, mais j'aurais pu le faire après aussi 
      FILES_ANALYSED++;
      if(fork()==0){
	execlp("grep", "grep", CHAINE, argv[FILES_ANALYSED+1], NULL);
      }
    }
  }
  
  
   printf("End\n");

  return 0;
  
}
/*
Test:
./multi okey fichier.txt fichier2.txt fichier3.txt
okey
okey okey
okey fichier2
okey okey 2
okey 3
okey okey 3
End


 */
