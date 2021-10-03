
public class Consommateur implements Runnable{
	private static int CPT=0;
	private int id;
	private Object mutex=new Object();
	
	private int nb_retrait;
	private Buffer b;
	
	public Consommateur(Buffer b) {
		synchronized(mutex) {
			id=++CPT;
		}
		this.b=b;
		nb_retrait=b.getNbElem();
	}
	
	//Le nombre de retraits correspond à la taille de Sstock
	public void run() {
		
		for(int i=0; i<nb_retrait; i++) {
			b.retrait();
		}
	}
	/*
	 * Possibilité de retrait dans une case vide si le consommateur
	 */
}
