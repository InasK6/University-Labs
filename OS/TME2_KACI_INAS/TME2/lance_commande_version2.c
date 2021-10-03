#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <sys/time.h>
#include <sys/times.h>
//utilisation de la fonction times
void lance_commande(char * commande){
  struct timeval  t1;
  struct timeval  t2;
  struct tms t;
  gettimeofday(&t1,NULL);
  int i=system (commande);
  gettimeofday(&t2,NULL);
  times(&t);
  if(i==-1){
    printf("Error, bad command\n");
  }
  else{
    printf("Statistique de %s :\n",commande); 
    printf("Temps total : %ld \n",( t2.tv_sec-t1.tv_sec));
    printf("Temps utilisateur : %ld\n", t.tms_utime);
    printf("Temps systeme : %ld\n", t.tms_stime);
    printf("Temps utilisateur fils : %ld\n", t.tms_cutime);
    printf("Temps systeme fils: %ld\n", t.tms_cstime);
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
      printf("fin %d\n",i);
    }
  }
  return 0; 
}
