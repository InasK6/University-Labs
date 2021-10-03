/* Definition du tas */

#define TAILTAS 127	/* l'indice max et la taille de la plus grande donnee possible */
#define TAILMIN 2	/* le plus petit bloc utilisable */

char tas[TAILTAS+1];
int libre;		/* indice du premier bloc libre dans le tas */

void afficher_tas(void);
    /* affiche le contenu du tas */
    
void tas_init(void);
    /* initialise le contenu du tas quand celui-ci ne contient pas de donnees */

char *tas_malloc(unsigned int taille);
/*
réserve dans le tas une zone de taille octets, retourne l'adresse du début de la zone allouée, NULL en cas d'allocation impossible
 */

int tas_free(char *ptr);
/*libère la zone dont le début est designé par ptr
 */
int first_fit(int taille, int *pred);
/*
Stratégie d'allocation first fit
retourne l'adresse du début de la zone à allouer
-1 s'il n'y a pas de bloc libre de taille suffisante
 */
    
