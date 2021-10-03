import java.util.concurrent.CyclicBarrier;

public class GroupeClients implements Runnable{
	private int id;
	private CyclicBarrier c;
	private Restaurant resto;
	private int nb_clients;
	private Client[] clients;
	private Thread[] ThClients;
	private int num_Reservation=-1;
	public GroupeClients(int i,Restaurant r, int n) {
		id=i;
		resto=r;
		nb_clients=n;
		clients=new Client[nb_clients];
		ThClients=new Thread[nb_clients];
		c=new CyclicBarrier(nb_clients);
		for(int j=0; j<nb_clients; j++) {
			clients[j]=new Client(j,this,c);
		}
	}

	public int getNbClients() {
		return nb_clients;
	}
	public synchronized boolean reserver(Client c) {
		
		if(num_Reservation==-1) {	// num_Reservation pas encore affecté
			int i=resto.reserver(this);
			if( i==-1) {
				System.out.println(c+ " n'a pas pu reserver ");
				for(int j=0; j<nb_clients; j++) {
					
					// On rajoute la condition pour ne pas arrêter le Thread courant et l'empêcher d'arrêter les suivants s'ils ont demarré
					if(ThClients[j]!=Thread.currentThread()) {
						ThClients[j].interrupt();
					}
				}
				
				return false;
			}
			else {
				
				num_Reservation=i;
				System.out.println(c+ " a eu le numéro de reservation "+num_Reservation);
			}
		}
		//System.out.println(c+ " a fini de reserver ");
		return true;
		
	}
	public String toString() {
		return "Groupe "+id;
	}
	
	public void run() {
		System.out.println(this+" avec"+nb_clients);
		for(int i=0; i<nb_clients; i++) {
			ThClients[i]=new Thread(clients[i]);
			ThClients[i].start();
		}
	}
	
}
