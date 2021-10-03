import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Question 4: implémentation avec Sémaphores
 * Hoare à N conditions = N+2 Sémaphores , N pour chaque condition , 1 pour l'exlusion mutuelle (et 1 pour le signal)
 */

public class PereNoel implements Runnable {
	private final int NB_TOTAL_RENNES;
	// Semaphore pour l'exclusion mutuelle, initialisé à pour permettre qu'il soit pris par un utilisateur
	//private Semaphore mutex=new Semaphore(1);
	private ReentrantLock l=new ReentrantLock();
	/*
	 * Semaphore pour chaque condition initialisé 
	 * penser à la condition initiale
	 * au début le santa doit attendre un renne, bloqué! donc initialisé à 0
	 * au début le renne doit attendre la réponse d'un père noel initialisé à 0
	 */
	private Semaphore renne=new Semaphore(0);
	private Semaphore santa=new Semaphore(0);
	//private boolean busy=false;
	private int nbRenneAttele=0;
	
	public PereNoel(int n){
		NB_TOTAL_RENNES=n;
	}
	public void sayHello(Renne r) throws InterruptedException {
		
			
			renne.release();
			System.out.println(r+ "a dit: Salut père Noel");
			santa.acquire();
	}
	
	public void Atteler() throws InterruptedException{
	
				renne.acquire();
			
			System.out.println("Santa s'occupe d'un renne");
			Thread.sleep(300);
			nbRenneAttele++;
			System.out.println("Encore un renne attelé");
			santa.release();
	}
	
	public void run() {
		try {
			while(nbRenneAttele!=NB_TOTAL_RENNES ) {
				Atteler();
			}
			Thread.sleep(100);
		System.out.println("Le père noél a fini d'atteler ses rennes, monte dans le traineau");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
