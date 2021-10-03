#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <time.h>
#include <math.h>
#include <unistd.h>
#include <sys/types.h>

int main(){
  long long i=0;
 
  for (i=0; i<5*pow(10,7) ;i++){
    getpid();
  }
    return 0; 
}
