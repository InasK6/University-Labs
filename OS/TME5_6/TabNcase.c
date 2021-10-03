
/* Diffusion tampon N case */
/*Ressemble à producteur consommateur
 */
  #include <stdio.h> 
  #include <unistd.h> 
  #include <signal.h> 
  #include <libipc.h>
/*
- Sémaphores nécessaires
* tableau de semaphores d'accès aux cases en écriture ( une par case )
* tableau de variables partagée pour compter le nombre de lecteurs par case et une sémaphore d'exlusion mutuelle pour la manipulation de chacune
*  variables partagés id, et ir qui représentent les indices des casesà écrire/ lire et une sémaphore d'exclusion mutuelle pour chacune
* un sémaphore pour bloquer les écritures en cas de tampon plein
* un sémaphore pour bloquer les lectures en cas de tampon vide
* tableau de sémaphore ( 1 pour chaque récepteur) afin de les réveiller lors de l'écriture dans une case

- Variables nécessaires:

 */
/************************************************************/

/* definition des parametres */ 

  #define NE          2     /*  Nombre d'emetteurs         */ 
  #define NR          5     /*  Nombre de recepteurs       */ 
  #define NMAX        3     /*  Taille du tampon           */
  
/************************************************************/

/* definition des semaphores */ 
    #define MUTEXID     0     /* Exclusion mutuelle sur la variable partagée id*/
    int EMET[NMAX];        /* accès à une case du tampon, une sémaphore pour chaque case pour maximiser le parallèlisme initialisée à 1 car au début les cases sont vides !*/ 
    int RECEP[NMAX][NR];         /* NMAX semaphores par recepteur réveillé après une écriture, un sémaphore pour chaque case*/
    int mutexNB[NMAX];     /* sémaphores d'exlusion mutuelle sur les variables partagés qui comptent le nombre de lectures sur chaque case */

/************************************************************/

/* definition de la memoire partagee */ 
typedef struct {
	int a;
} t_segpart;

t_segpart *nb_recepteurs[NMAX]; /*tableau de variables partagée qui comptent chacunes le nombres de recepteurs sur chaque cases, permet de révéiller un emetteur quand une valeurs a été lue par tous les récepteurs */
t_segpart *id;             /* variable partagée sur l'indice de la case que l'emetteur modifiera, ceci augmente le parallélisme, car les emetteurs ne se retrouveront pas dans la même case, donc un ne sera pas bloqué car l'autre est en cours d'écriture dans une même case*/
/*
je simule le tampon de plusieurs cases par un tableau de variables partagées ou les emetteurs poseraint la valeur du pid
 */
t_segpart *tampon[NMAX];

/************************************************************/

/* variables globales */ 
    int emet_pid[NE], recep_pid[NR];



/************************************************************/

/* traitement de Ctrl-C */ 

  void handle_sigint(int sig) 
  { int i;
  	for (i = 0; i < NE; i++) kill(emet_pid[i], SIGKILL); 
	for (i = 0; i < NR; i++) kill(recep_pid[i], SIGKILL); 
	det_sem(); 
	det_shm((char *)id);
	for(i=0; i<NMAX; i++) det_shm((char *)nb_recepteurs[i]);
 
  } 

/************************************************************/

/* fonction EMETTEUR */ 

	// A completer - contient les instructions executees
        // par un emetteur
void emettre(){
  int i,j;
  while(1){
  P(MUTEXID);
  P(EMET[id->a]);
  i=id->a;
  id->a=(id->a+1)%NMAX;
  V(MUTEXID);
  //traitement
  tampon[i]->a=getpid();
  printf("%d posé dans la case %d\n", getpid(), i);
  for(j=0; j<NR; j++){
    V(RECEP[i][j]);
  }
  }
  
}

/************************************************************/

/* fonction RECEPTEUR */ 

	// A completer - contient les instructions executees
        // par un recepteur
void recevoir(int index){
  int i=0;
  while(1){
    // C'est une mauvaise stratégie d'utiliser une variable partagée ir pour le déplacement dans la case géré de façon commune entre les récepteurs car ils peuvent être bloqué si ir a été incrémenté par un autre recepteur et qu'ils se retrouvent dans une case qu'ils ont déja lu, ils bloquent et ne peuvent donc pas lire les autres cases, et donc il y aura des cases qui ne seront pas lues par tous les récepteurs donc, pas de possibilité d'emettre de nouvelle valeurs ---> interblocage!!!
    /* P(MUTEXIR);
    i=ir->a;
    printf("case = %d\n", i);
    ir->a=(ir->a +1)%NMAX;
    
    V(MUTEXIR);*/
    
  P(RECEP[i][index]);
    P(mutexNB[i]);
    nb_recepteurs[i]->a++;
    if(nb_recepteurs[i]->a==NR){
      V(mutexNB[i]);
      nb_recepteurs[i]->a=0;
      V(EMET[i]);
      //traitement
      printf(" %d récupéré par %d dans la case %d !!\n", tampon[i]->a, index, i);
      i=(i+1)%NMAX;
      // sleep(10);
    }
    else{
      V(mutexNB[i]);
      //traitement
      printf(" %d récupéré par %d  dans la case %d \n",tampon[i]->a, index,i);
      i=(i+1)%NMAX;
      //sleep(10);
    }
  }

}
/************************************************************/

int main() { 
    struct sigaction action;
    /* autres variables (a completer) */
    int i,j;
    pid_t p;
    setbuf(stdout, NULL);

/* Creation du segment de memoire partagee */

    for(i=0; i<NMAX; i++){
      if((nb_recepteurs[i]=(t_segpart *)init_shm(sizeof(t_segpart)))==NULL){
	perror("init_shm error");
	exit(0);
      }
      nb_recepteurs[i]->a=0;
      if((tampon[i]=(t_segpart *)init_shm(sizeof(t_segpart)))==NULL){
	perror("init_shm error");
	exit(0);
      }
      tampon[i]->a=-1;
    }
    if((id=(t_segpart *)init_shm(sizeof(t_segpart)))==NULL){
	perror("init_shm error");
	exit(0);
    }
    id->a=0;


/* creation des semaphores */ 

    for(i=0; i<NMAX; i++){
      EMET[i]=1+i;
      mutexNB[i]=1+i+NMAX;
    }


    for(i=0; i<NMAX; i++){
      for(j=0; j<NR; j++){
	RECEP[i][j]=1+NMAX+NMAX+i*NR+j;

      }
    }
    if(creer_sem(1+NMAX+NMAX+NR*NMAX)==-1){
      perror("creer_sem error");
      exit(1);
    }
/* initialisation des semaphores */ 
    init_un_sem(MUTEXID, 1);
    for(i=0; i<NMAX; i++){
      init_un_sem(EMET[i], 1);
      init_un_sem(mutexNB[i],1);
    }
    for(i=0; i<NMAX; i++){
      for(j=0; j<NR; j++){
	init_un_sem(RECEP[i][j],0);
      }
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

    pause();                     /* attente du Ctrl-C */
    return EXIT_SUCCESS;
} 
