#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>


//TME4
//Partie 4
/*
Réponse à la question:

Un zombie est un état entre la fin du processus et sa mort de telle sorte que le processus est terminé, mais attend que son père fasse un wait avant d'être détruit. S'il était détruit avant la fin du processus père, ce dernier ne serait pas en mesure de récupérer ses statistiques par exemple

 */
int main(int argc , char ** argv){


  // pour créer pendant 10s 2 processus zombie, il suffit de rajouter sleep(10) avant les deux wait des deux fils

  //Création de deux processus
   int i=0;

   for(i=0; i<2; i++){
     if(fork()==0){
       printf("processus %d crée\n", i);
       exit(0);
       perror("This shouldn't be printed!!! weird behaviour\n");
     }
   }
   
  
  sleep(10);
 
  for(i=0; i<2; i++){
    wait(NULL);
  }
  return 0;
  
}
/*
je lance mon exécutable appelé multi en arrière plan avec la commande multi &
puis avec la commande ps, j'ai l'affichage:
PID TTY          TIME CMD
  973 pts/1    00:00:00 multi
  974 pts/1    00:00:00 multi <defunct>
  975 pts/1    00:00:00 multi <defunct>
  976 pts/1    00:00:00 ps
 9451 pts/1    00:00:00 bash

les deux processus avec <defunct> représentent les processus zombie

après 10 secondes ils sont détruits: affichage après ps:
  PID TTY          TIME CMD
 1007 pts/1    00:00:00 ps
 9451 pts/1    00:00:00 bash

 */
