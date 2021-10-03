#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>

#include <sys/types.h>
#include <unistd.h>

#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/times.h>

int main(){
  //Attente de la saisie d'une commande sans argument
  while(1){
  int taille=10;
  char executable[taille];
  
  int MAXARG=10;
   printf("Tapez votre commande\n");
   if(fgets(executable, taille, stdin)==NULL){
     perror("fgets!! Error \n");
   }
   //On retire d'abord le "\n" du retour à la ligne
   // executable[strlen(executable)-1]='\0';
    
  if(strncmp( executable,"quit",4)==0){
    
     exit(0);
  }
   executable[strlen(executable)-1]='\0';
   //on garde dans une variable si on a un & ou pas
   int attendre=1;
   if(executable[strlen(executable)-1]=='&'){
       attendre =0;
       executable[strlen(executable)-1]='\0';
       executable[strlen(executable)-1]='\0';
       
    }
  //Lancement d'un processus fils executant la commande
  pid_t p=fork();
  if(p==-1){
    perror("Fork Error\n");
    
  }
  if( p==0){
    // char * strToken=strtok(executable, separators);
    //Le dernier argument doit être NULL
    char * arg[MAXARG];
    char * separator=" ";
    int i=0;
    char * strToken=strtok(executable,separator );
    
     while(strToken!=NULL){
       arg[i]=strToken;
      i++;
      strToken=strtok(NULL,separator );
      }
    
    arg[i]=NULL;
    
    execvp(arg[0],arg);
    perror("Erroor");
    exit(1);
  }

  
  if(attendre){
    wait(NULL);
    
  }


}
  return 0;
}
