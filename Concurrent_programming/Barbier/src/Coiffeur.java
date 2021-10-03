import java.util.Random;

public class Coiffeur implements Runnable{
	private final int id;
	private static int CPT=0;
	//private Client c;
	private Object o=new Object();
	private Random gen=new Random();
	private SalonCoiffure s;
	
	public Coiffeur(SalonCoiffure s) {
		synchronized(o) {
			id=++CPT;
		}
		this.s=s;
	}
	
	public void Coiffer(Client c) {
		s.Barbieroccupe();
		int tmp=50+gen.nextInt(100);
		try {
			System.out.println("Coiffeur "+id+" coiffe le Client "+c);
			Thread.sleep(tmp);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problème durant la coiffure du client "+c+" par le coiffeur "+id);
		}
	}
	
	public void run() {
		while(true) {
			// peut être rajouter un try catch après avoir implémenté la fonction
			try{
				s.BarbierDisponible();
				Client v=s.ClientDisponible();
				Coiffer(v);
				
			}
			catch(Exception e){
				System.out.println("Problème inatendu dans le run du coiffeur!!!");
			}
		}
		
	}
}
