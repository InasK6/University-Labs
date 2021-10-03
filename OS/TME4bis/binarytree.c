#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>

#include <sys/types.h>
#include <unistd.h>

#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/times.h>

int main(int argc, char * argv){
  int Level=3;
  int i=0;
  // La boucle se fait sur les levels
  // Je considère que le courant est le 1er level
  for(i=1; i<Level; i++){
    pid_t p=fork();
    if(p<0){
      perror("Error fork!!");
      exit(1);
    }
    /* if(p==0){
      printf("fils1 de level %d\n", i);
      }*/
    pid_t p2;
    if(p!=0){
      p2=fork();

      
    }

    if(p!=0 && p2!=0){
      printf("pere Level %d\n", i);
      break;
    }
  }
  //chaque processus doit s'endormir pour 30s dont le porcessus courant aussi
  sleep(30);
  return 0;
}
/*
Test pour Level=4
binarytree
  ├─binarytree
  │   ├─binarytree
  │   │   ├─binarytree
  │   │   └─binarytree
  │   └─binarytree
  │       ├─binarytree
  │       └─binarytree
  └─binarytree
      ├─binarytree
      │   ├─binarytree
      │   └─binarytree
      └─binarytree
          ├─binarytree
          └─binarytree

Test pour level=6
binarytree
  ├─binarytree
  │   ├─binarytree
  │   │   ├─binarytree
  │   │   │   ├─binarytree
  │   │   │   │   ├─binarytree
  │   │   │   │   └─binarytree
  │   │   │   └─binarytree
  │   │   │       ├─binarytree
  │   │   │       └─binarytree
  │   │   └─binarytree
  │   │       ├─binarytree
  │   │       │   ├─binarytree
  │   │       │   └─binarytree
  │   │       └─binarytree
  │   │           ├─binarytree
  │   │           └─binarytree
  │   └─binarytree
  │       ├─binarytree
  │       │   ├─binarytree
  │       │   │   ├─binarytree
  │       │   │   └─binarytree
  │       │   └─binarytree
  │       │       ├─binarytree
  │       │       └─binarytree
  │       └─binarytree
  │           ├─binarytree
  │           │   ├─binarytree
  │           │   └─binarytree
  │           └─binarytree
  │               ├─binarytree
  │               └─binarytree
  └─binarytree
      ├─binarytree
      │   ├─binarytree
      │   │   ├─binarytree
      │   │   │   ├─binarytree
      │   │   │   └─binarytree
      │   │   └─binarytree
      │   │       ├─binarytree
      │   │       └─binarytree
      │   └─binarytree
      │       ├─binarytree
      │       │   ├─binarytree
      │       │   └─binarytree
      │       └─binarytree
      │           ├─binarytree
      │           └─binarytree
      └─binarytree
          ├─binarytree
          │   ├─binarytree
          │   │   ├─binarytree
          │   │   └─binarytree
          │   └─binarytree
          │       ├─binarytree
          │       └─binarytree
          └─binarytree
              ├─binarytree
              │   ├─binarytree
              │   └─binarytree
              └─binarytree
                  ├─binarytree
                  └─binarytree

test pour level=3
binarytree
  ├─binarytree
  │   ├─binarytree
  │   └─binarytree
  └─binarytree
      ├─binarytree
      └─binarytree

 */
