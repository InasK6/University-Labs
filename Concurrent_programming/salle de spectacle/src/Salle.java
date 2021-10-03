
public class Salle {
	private final int nbRangs;
	private final int nbPlacesParRang;
	private boolean[][] placesLibres;
	
	public Salle(int i, int j) {
		nbRangs=i;
		nbPlacesParRang=j;
		placesLibres=new boolean[i][j];
		for(int k=0; k<i; k++) {
			for(int l=0; l<j; l++) {
				placesLibres[k][l]=true;
			}
		}
	}
	
	
	public boolean capaciteOK(int n){
		int nb=0;
		for (int i=0; i<nbRangs; i++) {
			for(int j=0; j<nbPlacesParRang;j++) {
				if(placesLibres[i][j]==true) {
					nb++;
				}
			}
		}
		if(n<=nb) {
			System.out.println("Capacité OK!!! Il reste "+nb+" places ");
			
		}
		else {
			System.out.println("Caparcité limite atteinte! réservation refusée");
		}
		return(n<=nb);
	}
	
	//output: -1 si pas de places contigues, j première du bloc de n places libres au rang i
	public int nContigueAuRangI(int n, int i) {
		boolean existe=true;
		for(int j=0; j<nbPlacesParRang;j++) {
			if(j+n>nbPlacesParRang) {
				return -1;
			}
			existe=true;
			for(int k=j; k<j+n;k++) {
				existe&=placesLibres[i][k];
			}
			if(existe) {
				System.out.println("Places contigues trouvées! au rang "+i+ " à partir de la place"+ j);
				return j;
			}
		}
		
		return -1;
	}
	
	public boolean reserverContigues(int n,Groupe g) {
	
		//on cherche le premier rang ou il y a n places contigues libres
		for(int i=0; i<nbRangs; i++) {
			int j=nContigueAuRangI(n,i);
			if(j!=-1) {
				for(int k=j;k<j+n;k++) {
					placesLibres[i][k]=false;
					g.addPlace(new Place(i,k));
				
				}
				System.out.println("Places contigues réservées");
				return true;
			}
		}
		
		return false;
	}
	/*
	 * Réponse à la question 2: selon moi, il faut garantir l'exclusion mutuelle pour la méthode reserver(iny n)
	 * car si on fait un synchronized pour les méthodes intermédiaires on peut rencontrer un conflit
	 * exemple: si un groupe trouve des places avec la méthode capaciteOK puis lache le verrou, un autre groupe reprend le verrou et a aussi true pour capacitéOK,
	 * puis réserve les places, il y aura un problème pour le premier groupe car il avait trouvé des places mais elles ont été reservé entre temps
	 * si on met un synchronized à la méthode réserver, on garantit qu'il n'y aura pas de commutation entre les méthodes
	 * je choisis de faire en sorte qu'un groupe ne puisse pas réserver avant qu'un autre qui a commencé avant lui n'ait fini
	 */
	synchronized public boolean reserver(int n, Groupe g) {
		if(!capaciteOK(n)) {
			return false;
		}
		else {
			if(reserverContigues(n,g)) {
				return true;
			}
			int nb=0;
			
			for(int i=0; i<nbRangs; i++) {
				for(int j=0; j<nbPlacesParRang;j++) {
					if(placesLibres[i][j]==true) {
						placesLibres[i][j]=false;
						g.addPlace(new Place(i,j));
						nb++;
					}
					if(nb==n) {
						System.out.println("Réservation de places non contigues!");
						return true;
					}
				}
			}
			
		}
		
		System.out.println("Problème!! n'est pas supposé faire ça!");
		return false;
	}
	/*
	 * entrée: g :le groupe qui veut annuler la réservation
	 * 		nb: le nombre de places qu'il veut annuler
	 */
	public boolean annulerRes(Groupe g, int nb) {
		int longueur=g.getPlaces().size();
		if(longueur<nb) {
			System.out.println("Vous voulez annuler plus de places que ce que vous avez reservé!");
			return false;
		}
		for (int i=0; i<nb; i++) {
			Place p=g.getPlaces().get(i);
			placesLibres[p.getRang()][p.getPlaceDansRang()]=true;
			System.out.println("Reservation de "+ p+" Annulée");
		}
		for(int i=0; i<nb;i++) {
			g.removePlace(0);
		}
		return true;
	}
	/*
	 *  On n'est pas obligé de mettre de synchronized sur cette méthode
	 *  Il peut y avoir plusieurs annulations de réservations au même temps
	 *  cela ne causera pas de conflit car chaque groupe cible les cases qu'il a déja reservé
	 *  il faut seulement s'assurer que les annulation s'effectuent bien après les reservations
	 */
	
}
