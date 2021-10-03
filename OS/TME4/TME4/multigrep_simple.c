#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>


//TME4
//Partie 1
int main(int argc , char ** argv){

  // autant de processus fils que de fichiers
  // argv[0] reprèsente l'executable
  // argv[1] représente la chaine de caractère
  // pour i>1 argc[i] représente un nom de fichier
  if(argc<3){
    printf("few arguments, try <executable>  <string>  <files names>\n");
  }
  int NB_FILES= argc-2;

  int i=0;
  char * CHAINE=argv[1];
  while (i<NB_FILES ){
    if(fork()==0){
      execlp("grep", "grep", CHAINE, argv[i+2], NULL);
    }
    i++;
  }

  int j=0;
  for(j=0; j<NB_FILES; j++){
    wait(NULL);
  }
  
   printf("End\n");

  return 0;
  
}
/*
Test réussi en cherchant okey dans deux fichiers que j'ai crée: fichier.txt et fichier2.txt
affichage:
okey
okey okey
okey fichier2
okey okey 2
End

Remarque: On  a bien l'affichage de END après l'éxecution des deux premiers fils

 */
