#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <sys/time.h>

void lance_commande(char * commande){
  struct timeval  t1;
  struct timeval  t2;
  gettimeofday(&t1,NULL);
  int i=system (commande);
  gettimeofday(&t2,NULL);
  if(i==-1){
    printf("Error, bad command\n");
  }
  else{
    printf("temps écoulé: %ld \n",( t2.tv_sec-t1.tv_sec));
  }
}
int main(int argc, char ** argv){
  if(argc<2){
    printf("Errooor, choose a command!!\n");
  }
  else{
    int i=0;
    for(i=1; i<argc; i++){
      char * com=argv[i];
      lance_commande(com);
    }
  }
  return 0; 
}
