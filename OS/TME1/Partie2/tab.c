#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <time.h>

/*
 Question 1
 */
void InitTab(int *tab, int size){
  int i;
  srand(time(NULL));
  for(i=0; i<size; i++){
    tab[i]= (int)(rand())%9; 
  }

}

/*
 Question 2
 */

void PrintTab(int *tab, int size){
  int i;
  printf(" Tableau: \n");
  printf("[ ");
  for(i=0; i<size; i++){
    printf("%d , ", tab[i]);
    
  }
  printf(" ]\n ");
}

/*
Question 5
 */

int SumTab(int *tab, int size){
  int i;
  int sum=0; 
  for( i=0; i< size; i++){
    sum+=tab[i];
  }
  return sum; 
}

/*
Question 6
 */

int MinSumTab(int *min, int *tab, int size){
  int i;
  // Je fais l'hypothèse que le tableau a au minimum un élément
  int m=tab[0];
  for(i=1; i<size; i++){
    if(tab[i]<m){
      m =tab[i];
    }
  }
  (*min)=m;
  return SumTab(tab, size);
}
