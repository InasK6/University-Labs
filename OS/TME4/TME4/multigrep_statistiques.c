#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <sys/resource.h>


//TME4
//Partie 3
/*
Modification du fichier afin d'afficher les statistiques 
 */
int main(int argc , char ** argv){

  struct rusage r;
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
    wait3(NULL,0 , &r);
    printf("Statistiques fils %d\n", j+1);
    printf("temps utilisateur %f\n", r.ru_utime);
    printf("temps système %f\n", r.ru_stime);
    
  }
  
   printf("End\n");

  return 0;
  
}
/*
Affichage:

test 1:

./multi_stat okey fichier.txt
okey
okey okey
Statistiques fils 1
temps utilisateur 0.000000
temps système 0.000000
End

test 2:
./multi_stat okey fichier.txt fichier2.txt
okey
okey okey
Statistiques fils 1
temps utilisateur 0.000000
temps système 0.000000
okey fichier2
okey okey 2
Statistiques fils 2
temps utilisateur 0.000000
temps système 0.000000
End


Je n'arrive pas à trouver ce que je dois modifier pour avoir les bons temps utilisateur et système !=0 

 */
