import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/*
 * Les clients se comportent de manière indépendante
 */
public class Client implements Runnable {
	private int id;
	private GroupeClients g;
	private CyclicBarrier c;
	public Client(int i, GroupeClients g, CyclicBarrier c) {
		id=i;
		this.g=g;
		this.c=c;
	}
	
	public String toString() {
		return "Client "+id+" du groupe"+g;
	}
	public void run() {
		try {
			Random r=new Random();
			//System.out.println(this+" est sur le point de reserver");
			Thread.sleep(100+r.nextInt(200));
			boolean b=g.reserver(this);
			if(!b) {
				System.out.println("Reservation echouée pour"+g);
			}
			else {
				
				//Temps aléatoire pour se rendre au resto
				Thread.sleep(100+r.nextInt(200));
				System.out.println(this+ " est arrivé");
				if(c.getNumberWaiting()==g.getNbClients()-1) {
					System.out.println(g+" est au complet et peut passer sa commande ");
				}
				c.await();
				
			}
		}
		catch(Exception e) {
			System.out.println("Reservation echouée pour"+ this);
		}
	}
}
