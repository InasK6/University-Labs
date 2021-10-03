import java.util.Random;

public class Babouin implements Runnable{
	//private Corde2 corde;
	protected Corde3 corde;
	private int id;
	protected Position position;
	private Random r=new Random();
	
	public Babouin(int i, Corde3 c, Position p) {
		id=i;
		corde=c;
		position=p;
	}
	
	public void traverser() throws InterruptedException{
		System.out.println("Le babouin "+id+" traverse la corde");
		Thread.sleep(100);
		System.out.println("Le babouin "+id+" a fini de traverser la corde");
	}
	
	public String toString() {
		return "Babouin "+id+ " à la position "+ position+" ";
	}
	
	public Position position() {
		return position;
	}
	
	public void Batifoler() {
		int n=100+r.nextInt(200);
		try{Thread.sleep(n);
		}catch(InterruptedException e) {
			System.out.println("interruption de sleep dans batifoler");
		}
	}
	public void run() {
		try {
			corde.acceder(position);
			System.out.println(this.toString()+ "a pris la corde ");
			Batifoler();
			System.out.println(this.toString()+" a fini de cueillir les fruits"); 
			traverser();
			System.out.println(this.toString()+ "est arrivé ");
			corde.lacher(position);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
