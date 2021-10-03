/* Diffusion tampon 1 case */

  #include <stdio.h> 
  #include <unistd.h> 
  #include <stdlib.h> 
  #include <signal.h> 
  #include <libipc.h>


/*
Mécanismes d'exclusion mutuelle
- Sémaphore <emet>: bloque les emetteurs tant qu"il n'y a pas de case dans laquelle ils puissent écrire,
   initialisé à 1 car au début, le tampon est vide
- tableau de sémaphores <RECEP[1...NR]> pour bloquer les récepteurs en cas de case vide, et faire en sorte qu'il ne fasse qu'une seule lecture par donnée émise. En effet, comme les emetteurs et récepteurs sont cycliques, si on utilisait un seul sémaphore et qu'on l'augmentait de NR lors de l'emission d'un message, on risque de se retrouver avec un récepteur qui lit une donnée plusieurs fois et d'autres qui ne la lisent jamais. 
   Toutes les sémaphores du tableau sont initialisés à 0. 
- variable partagée "nb_recepteurs", indique le nombre de consommateurs, sert à réveiller un emetteur quand une donnée a été consommée par tous les consommateurs, elle est protégée par un sémaphore d'exclusion mutuelle <mutex>
  C'est donc le dernier consommateur de la donnée qui prévient les emetteurs de la disponibilité de la case.
 */

/************************************************************/

/* definition des parametres */ 

  #define NE          2     /*  Nombre d'emetteurs         */ 
  #define NR          5     /*  Nombre de recepteurs       */ 
  
/************************************************************/

/* definition des semaphores */ 

  #define MUTEX 0
  #define EMET 1
//tableau de sémaphore
      int RECEP[NR];   
/************************************************************/

/* definition de la memoire partagee */ 

/* Definition du format du segment de memoire partagee */
typedef struct {
	int a;
} t_segpart;

t_segpart *nb_recepteurs;

/*
je simule le tampon d'une case par une variable l'emetteur poserait la valeur du pid
 */
t_segpart *tampon;

/************************************************************/

/* variables globales */ 
    int emet_pid[NE], recep_pid[NR]; 
    
/************************************************************/

/* traitement de Ctrl-C */ 

  void handle_sigint(int sig) { 
      int i;
      for (i = 0; i < NE; i++) kill(emet_pid[i], SIGKILL); 
      for (i = 0; i < NR; i++) kill(recep_pid[i], SIGKILL); 
      det_sem(); 
      det_shm((char *)nb_recepteurs); 
  } 

/************************************************************/

/* fonction EMETTEUR */ 

	// A completer - contient les instructions executees
        // par un emetteur

/*
Comme dans mon traitement, je rajoute une variable partagée tampon, je devrai 
 */

void emettre(){
  int i;
  while(1) {
    P(EMET);
    //traitement de l'emetteur;
    tampon->a=getpid();
    printf("%d posé\n", tampon->a);
    for(i=0; i<NR; i++){
      V(RECEP[i]);
    }
    
  }
}



/************************************************************/

/* fonction RECEPTEUR */ 

	// A completer - contient les instructions executees
        // par un recepteur


void recevoir(int index){
  while(1) {
    P(RECEP[index]);
    P(MUTEX);
    nb_recepteurs->a++;
    if(nb_recepteurs->a==NR){
      V(MUTEX);
      //traitement par le recepteur
      printf("%d lu par %d\n", tampon->a, getpid());
      nb_recepteurs->a=0;
      V(EMET);
    }
    else{
      V(MUTEX);
       //traitement par le recepteur
       printf("%d lu par %d\n", tampon->a, getpid());
    }
  }
}




/************************************************************/

int main() { 
    struct sigaction action;
    /* autres variables (a completer) */
    
    int i,p;
    for(i=0; i<NR; i++){
      RECEP[i]=2+i;
    }
    setbuf(stdout, NULL);

/* Creation du segment de memoire partagee */

    if((nb_recepteurs=(t_segpart *)init_shm(sizeof(t_segpart)))==NULL){
      perror("init_sum error");
      exit(1);
    }
    nb_recepteurs->a=0;

    if((tampon=(t_segpart *)init_shm(sizeof(t_segpart)))==NULL){
      perror("init_shm error");
      exit(1);
    }
    tampon->a=-1;

/* creation des semaphores */ 

    if(creer_sem(2+NR)==-1){
      perror("creer_sem error");
      exit(2);
      
    }

/* initialisation des semaphores */ 
    init_un_sem(MUTEX,1);
    init_un_sem(EMET,1);
    for(i=0; i<NR; i++){
      init_un_sem(RECEP[i],0);
    }
    
    

/* creation des processus emetteurs */ 

	// A completer - les pid des processus crees doivent
        // etre stockes dans le tableau emet_pid
    printf("pid des emetteurs\n");
    for(i=0; i<NE; i++){
      p=fork();
      if( p<0){
	perror("fork error!!");
	exit(3);
      }
      if(p>0){
	printf("emetteur %d\n", p);
	emet_pid[i]=p;
      }
      if(p==0){
	emettre();
	exit(0);
      }
    }
    

/* creation des processus recepteurs */ 

	// A completer - les pid des processus crees doivent
        // etre stockes dans le tableau recep_pid
     for(i=0; i<NR; i++){
      p=fork();
      if( p<0){
	perror("fork error!!");
	exit(3);
      }
      if(p>0){
	recep_pid[i]=p;
      }
      if(p==0){
	recevoir(i);
	exit(0);
      }
    }
    
/* redefinition du traitement de Ctrl-C pour arreter le programme */ 

    sigemptyset(&action.sa_mask);
    action.sa_flags = 0;
    action.sa_handler = handle_sigint;
    sigaction(SIGINT, &action, 0); 
    
    pause();                    /* attente du Ctrl-C  */
   
    return EXIT_SUCCESS;
} 
