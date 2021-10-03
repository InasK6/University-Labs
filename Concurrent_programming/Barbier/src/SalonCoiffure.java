import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * TD: 
 * question 2: 
 * 		les variables qui définissent l'était de la salle d'attente sont
 * 			 nb_courant: le nombre de personnes dans la salle
 * 			salle_dattente: tableau des clients présents dans  le salon de coiffure
 * 		Les actions qui modifient ces variables:
 * 			- un client qui entre dans le salon
 * 			- un coiffeur qui coiffe un client
 * 		Un effet sur d'autres composantes du système:
 * 			- Un client qui rentre débloque un coiffeur si le salon était vide
 * 			- Un coiffeur qui coiffe un client le débloque car il attendait dans la salle
 * 
 * question 3:
 * 		- les actions identifiées doivent être implémentées dans la classe SalonCoiffure
 * 		- Les variables qu'elles manipulent sont des variables partagées donc doivent être dans une section critique 
 * 		 donc manipulés en exlusion mutuelle
 * 
 * question 4:
 * 		- Les conditions qui peuvent bloquer le barbier: 
 * 			Une salle d'attente vide
 * 		On crée une condition Vide
 * question 5:
 * 		- Les conditions qui peuvent bloquer le client: 
 * 			Une salle d'attente pleine
 * 			Le coiffeur est occupé
 * 
 * 		On crée une condition Plein 
 * 
 * On crée deux conditions pour que les thread ne se retrouvent pas dans le même waitset pour un salon de coiffure alors qu'ils attendent pour différentes raisons et qu'ils sont de nature différente
 * 
 */
public class SalonCoiffure {
	//private Coiffeur f;
	private int nb_max;
	private Client[] salleAttente;
	private static int nb_courant=0;
	private static int entrer=0;
	private static int sortir=0;
	// Conditions
	private Lock lock=new ReentrantLock();
	private Condition NonVide=lock.newCondition();
	private Condition CoiffeurLibre=lock.newCondition();
	private boolean occupe=false;
	public SalonCoiffure( int nb) {
		nb_max=nb;	
		salleAttente=new Client[nb_max];
	}
	
	public boolean entrerSalle(Client c) throws InterruptedException{
		lock.lock();
		try{
			if(nb_courant==nb_max) {
		
			System.out.println("Salle d'attente pleine, le client"+c+" s'en va");
		
			return false;
		}
		else {
			
			System.out.println("Le client"+ c+ " rentre dans la salle");
			nb_courant++;
			salleAttente[entrer]=c;
			entrer=(entrer+1)%nb_max;
			NonVide.signalAll();
			
			return true;
			
		}
		
		}finally {
			lock.unlock();
		}
	}
	
	public void ClientAttend(Client c) throws InterruptedException{
		lock.lock();
		try {
			System.out.println("Le client "+c+ " attend son tour");
			while(occupe)
			{
				CoiffeurLibre.await();
			}
		}
		finally {
			lock.unlock();
		}
	}
	
	public Client ClientDisponible() throws InterruptedException {
		lock.lock();
		//try{ 
		while(nb_courant==0) {
		
			NonVide.await();
		}
		
		Client c=salleAttente[sortir];
		nb_courant--;
		sortir=(sortir+1)%nb_max;
		System.out.println("Le client "+c+" Sort de la salle d'attente");
		
		lock.unlock();
		return c;
		/*}
		finally {
			lock.unlock();
		}*/
	}
	

	
	public void BarbierDisponible() throws InterruptedException{
		lock.lock();
		try{
			occupe=false;
			CoiffeurLibre.signalAll();
		}
		finally {
			lock.unlock();
		}
	}
	
	public void Barbieroccupe() {
		lock.lock();
		try {
			occupe=true;
		}
		finally {
			lock.unlock();
		}
	}
	
}
