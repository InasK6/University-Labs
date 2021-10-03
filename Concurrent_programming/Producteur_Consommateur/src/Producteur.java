import java.util.Random;

public class Producteur implements Runnable{
	private static int CPT=0;
	private int id;
	private Object mutex=new Object();
	
	private int nb_depot;
	private Buffer b;
	
	private Random gen=new Random();
	public Producteur(Buffer b,int nb) {
		synchronized(mutex) {
			id=++CPT;
		}
		this.b=b;
		nb_depot=nb;
	}
	
	public void run() {
		System.out.println("Dépot du producteur "+id+"de "+nb_depot+" éléments dans le buffer "+b.toString());
		for(int i=0; i<nb_depot;i++) {
			b.depot(gen.nextInt(50));
		}
	}
}
