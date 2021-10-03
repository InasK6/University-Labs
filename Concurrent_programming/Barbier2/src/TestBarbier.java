/* question 6:
 * 		Pour gérer la terminaison du coiffeur, on peut faire une interrupt sur le Thread associé une fois que tous les Threads associés aux clients sont terminés
 * 
 */
public class TestBarbier {
	public static void main(String[] args) {
		int NB_CLIENTS=20;
		int CAPACITE_SALLE=5;
		SalonDeCoiffure s=new SalonDeCoiffure(CAPACITE_SALLE);
		Barbier c=new Barbier(s);
		Thread thc=new Thread(c);
		
		thc.start();
		
		Client[] clients=new Client[NB_CLIENTS];
		Thread[] thclients=new Thread[NB_CLIENTS];
		
		//Création des threads clients
		for(int i=0; i<NB_CLIENTS; i++) {
			clients[i]=new Client(s);
			thclients[i]=new Thread(clients[i]);
			
		}
		
		// lancements des threads clients
		for(int i=0; i<NB_CLIENTS; i++) {
			thclients[i].start();
		}
		
		//Attente de la fin des threads clients avant de terminer le programme
		try {
			for(int i=0; i<NB_CLIENTS; i++) {
					thclients[i].join();
					
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thc.interrupt();
		System.out.println("Fin");
	}
}
