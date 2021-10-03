import java.util.ArrayList;

public class Groupe implements Runnable{
	public static int CPT=0;
	private final int id;
	private final int NB_Pers;
	private static final Object mutex=new Object();
	private Salle s;
	/*
	 * Question 5:
	 * On doit ajouter un ArrayList avec les places reservées 
	 * afin qu'une fois qu'on veut annuler on peut directement les retrouver
	 * Dans l'Arraylist on aura des object qui indiquent le rang et la position dans le rang de la place reservée
	 * Je crée donc un objet place avec ces caractéristiques!
	 * Les modifications à apporter à Salle sont:
	 * l'ajout de la place dans l'ArrayList dés qu'elle est reservé par le groupe
	 * ajout d'une méthode pour annuler la réservation de nb place faites par un groupe g
	 */
	private ArrayList<Place> places=new ArrayList<Place>();
	
	public Groupe(int nb, Salle s) {
		NB_Pers=nb;
		synchronized(mutex) {
			id=++CPT;
		}
		this.s=s;
	}
	 
	public void addPlace(Place p) {
		places.add(p);
	}
	
	public void removePlace(int i) {
		places.remove(i);
	}
	//getter sur la liste places 
	public ArrayList<Place> getPlaces(){
		return places;
	}

	
	// fait une demande de réservation
	public void run() {
		System.out.println("Le groupe "+id+" veut réserver");
		boolean b=s.reserver(NB_Pers, this);
		if(b) {
			System.out.println("Réservation effectuée par le groupe "+id);
		}
		else {
			System.out.println("Réservation Impossible pour le groupe "+id);
		}
	}
}
