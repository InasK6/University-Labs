import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;


public class SalonDeCoiffure {
	/*
	 * 2) Variables qui définissent l'état de la salle d'attente
	 */
	private Client[] SalleAttente;
	private final int NbMAX;
	private int Nbcourant=0;
	
	private int id=0;
	private int ir=0;
	/*
	 * 2 Les actions qui modifient ces variables:	| Effets sur les autres composants
	 * - Entrée d'un client dans la salle d'attente | - Débloque le barbier si le salon était vide
	 * - Coiffure d'un client par le Barbier		| - permet à un autre client d'entrer dans le salon, à la fin de la 
	 * 												| coiffure permet à un autre client d'être débloqué de son attente dans
	 * 												| la salle
	 *3 doivent être implémenté dans cette classe car manipulent ses variables, les variables doivent être protégées par
	 *une section critique car elles peuvent être manipulés par plusieurs Threads au même temps
	 */
	private Lock l=new ReentrantLock();
	/*
	 * Les conditions qui peuvent bloquer l'éxecution du Barbier:
	 * - Salle d'attente vide, c'est l'entrée d'un client dans la salle qui le débloque
	 * Les conditions qui peuvent bloquer l'éxecution du Client
	 * - Coiffure occupée, il est en attente dans la salle, c'est au coiffeur donc de le débloquer
	 */
	private Condition ClientDispo=l.newCondition();
	public SalonDeCoiffure(int n) {
		NbMAX=n;
		SalleAttente=new Client[NbMAX];
	}
	public boolean entrer(Client c) {
		l.lock();
		try{
			if(Nbcourant==NbMAX) {
		
				System.out.println("Salle d'attente pleine, "+c+" s'en va");
				return false;
			}
			else {
				SalleAttente[id]=c;
				Nbcourant++;
				id=(id+1)%NbMAX;
				if(Nbcourant==1) {
					ClientDispo.signalAll();
				}
				System.out.println(c+ "Rentre dans la salle d'attente");
				return true;
			}
		}finally {
			l.unlock();
		}
	}
	public Client AccueillirClient() throws InterruptedException{
		l.lock();
		try {
			while(Nbcourant==0) {
				ClientDispo.await();
			}
			Nbcourant--;
			Client c=SalleAttente[ir];
			System.out.println(c+ "acceuilli par le coiffeur");
			ir=(ir+1)%NbMAX;
			c.Accueilli();
			return c;
			
		}finally{
			l.unlock();
		}
	}
}
