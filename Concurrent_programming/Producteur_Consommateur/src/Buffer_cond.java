import java.util.concurrent.locks.ReentrantLock;

public class Buffer_cond {
	private int taille_actuelle=0;
	private int[] tab;
	
	private int depot=0;
	private int retrait=0;
	
	private Object NBE=new Object();
	private ReentrantLock mutex[];
	
	public Buffer_cond(int taille) {
		tab=new int[taille];
		//un verrou pour chaque case
		mutex=new ReentrantLock[taille];
		for(int i=0; i<taille; i++) {
			mutex[i]=new ReentrantLock();
		}
	}
	
	public String toString() {
		String s=" ";
		for(int i=0; i<taille_actuelle; i++) {
			s=s+tab[i]+" ";
		}
		return s;
	}
	
	public synchronized void depot(int a) throws InterruptedException {
		while(taille_actuelle>=tab.length) {
			System.out.println("Dépot de "+a+" échoué, Buffer Plein!!");
			wait();
		}
			mutex[depot].lock();
			tab[depot]=a;
			System.out.println("Dépot de "+a+" réussi");
			depot=(depot+1)%tab.length;
			mutex[depot].unlock();
			synchronized(NBE) {
				taille_actuelle++;
			}
			notifyAll();
			
		

	}
	


	public synchronized int retrait() throws InterruptedException {
		while(taille_actuelle==0) {
			System.out.println("Retrait impossible! Buffer Vide");
			wait();
		}
		mutex[retrait].lock();
		int r=tab[retrait];
		retrait=(retrait+1)%tab.length;
		mutex[retrait].lock();
		
		synchronized(NBE) {
			taille_actuelle--;
		}
		
		notifyAll();
		return r;
		
	}
	
	// accesseur pour connaître le nombre d'éléments actuels
	public int getNbElem() {
		return taille_actuelle;
	}
}
