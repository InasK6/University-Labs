public class Client implements Runnable{

		private static int  CPT=0;
		private int id;
		private Object o=new Object();
		private Serveur s;
		private boolean Rservie=false;
		
		
		public Client(Serveur s) {
			synchronized (o){
				id=++CPT;
			}
			this.s=s;
		}
		
		public String toString() {
			return "Client "+id;
		}
		
		public synchronized void requeteServie(ReponseRequete r) {
			Rservie=true;
			System.out.println("Requête"+ r+ " servie");
			notifyAll();
			
		}
		
		public synchronized void attendreReception(int i) throws InterruptedException {
			System.out.println("Le  "+ this+ " attend que le requête "+i+" soit servie");
			while(!Rservie) {
				wait();	//lache le verrou
			}
			System.out.println("Le  "+ this+ " a fini d'attendre que le requête soit servie");
		}
		public void run() {
			int type;
			try{for(int i=1; i<=5; i++) {
				// Pour pouvoir soumettre une requete, il faut s'assurer que le serveur est libre ( gérer la concurrence entre plusieurs clients dans la classe Serveur
				if(id%3!=0) {
					type=1;
				}
				else {
					type=2;
				}
				//Avant chaque soummission, mettre Rservie à false
				
				synchronized(this) {
					s.soumettre(new Requete(this,type));
					Rservie=false;
				}
				
				attendreReception(i);
				System.out.println("Execution du client"+id );
				Thread.sleep(1);
			}
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
}