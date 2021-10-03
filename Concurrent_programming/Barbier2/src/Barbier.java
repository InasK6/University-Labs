
public class Barbier implements Runnable {

	private SalonDeCoiffure s;
	
	public Barbier(SalonDeCoiffure sa) {
		s=sa;
	}
	public void Coiffer(Client c) throws InterruptedException {
		System.out.println("Début de la coiffure de "+c+" Par le coiffeur");
		//c.CoiffureEnCours=true;
		Thread.sleep(3);
		c.finCoiffure();
		System.out.println("Fin de la coiffure de "+c+" Par le coiffeur");
		
	}
	public void run() {
		try{
			//Thread t= Thread.currentThread();
			while(!Thread.currentThread().isInterrupted()) {
				System.out.println("Iteration "+Thread.currentThread().isInterrupted());
				Client c=s.AccueillirClient();
				Coiffer(c);
			
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}

	}
}
