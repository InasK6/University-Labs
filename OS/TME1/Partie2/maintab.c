#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include "tab.h"
#include <sys/time.h>
#include <sys/resource.h>
/*
Question 7
affiche la mémoire résidente occupée par le programme
 */
void PrintMem(){
  struct rusage rus;
  getrusage(RUSAGE_SELF, &rus);
  printf("Mem: %ld\n", rus.ru_maxrss);
}


/*
Question 3
 */
int main(){

  int *tab=(int *) malloc(NMAX * sizeof(int));
  PrintMem();
  InitTab(tab, NMAX);
  PrintMem();
  // PrintTab(tab, NMAX);
  int min=100;
  printf("somme: %d\n", SumTab(tab, NMAX));
  MinSumTab(&min, tab, NMAX);
  printf(" minimum: %d\n", min);
  
}
/*
 Question 9:
 ( plusieurs tests: )  test2      test3    test4       test5
 avant InitTab: 2884
 après InitTab: 5140   4744       4736     4780        5112

  On constate que la mémoire augmente et donc on déduit qu'elle est effectivement allouée après l'initialisation du tableau. Une partie seulement de celle-ci est allouée lors du malloc par optimisation ( pour éviter de ne pas occuper trop de mémoire dans le cas ou celle demandé lors du malloc n'est pas utilisée au complet ).
 */
