public class TestClientServeur {
	
	public static void main (String[] args) {
		
		int NB_CLIENTS;
		//NB_CLIENTS=Integer.parseInt(args[0]);
		NB_CLIENTS=3;
		Serveur s=new Serveur(NB_CLIENTS);
		Thread serveur=new Thread(s);
		serveur.start();
		Thread[] Clients=new Thread[NB_CLIENTS];
		for(int i=0; i<NB_CLIENTS; i++) {
			Clients[i]=new Thread(new Client(s));
			Clients[i].start();
		}
		try{for(int i=0; i<NB_CLIENTS; i++) {
			Clients[i].join();
		}
		serveur.join();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Fin du programme");
		
	}
	/*
	 * On se rend compte que la requête qui fait une boucle infinie occupe le serveur et on ne peut plus traîter d'autres requêtes! 
	 * Les modifications à apporter au système seraient de créer des threads servants qui vont traiter les requêtes envoyées au serveur
	 */
}