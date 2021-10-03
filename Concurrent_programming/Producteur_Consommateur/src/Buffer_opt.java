import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/*
 * On protège l'accés aux compteurs partagé avec un bloc d'instruction synchronisé et un verrou pour l'accés à une case
 */
public class Buffer_opt {
	private int taille_actuelle=0;
	private int[] tab;
	
	private int depot=0;
	private int retrait=0;
	
	
	private ReentrantLock mutex[];
	
	// 2 Conditions 
	private final Lock l=new ReentrantLock();
	private final Condition buff_depot=l.newCondition();
	private final Condition buff_retrait=l.newCondition();
	
	public Buffer_opt(int taille) {
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
		l.lock();
		try{
			while(taille_actuelle>=tab.length) {
		
				System.out.println("Dépot de "+a+" échoué, Buffer Plein!!");
				buff_depot.await();
			}
				mutex[depot].lock();
				tab[depot]=a;
				System.out.println("Dépot de "+a+" réussi");
				depot=(depot+1)%tab.length;
				mutex[depot].unlock();
				
					taille_actuelle++;
				
				buff_retrait.signalAll();
		}
		finally {
			l.unlock();
		}
			
		

	}
	
	// accesseur pour connaître le nombre d'éléments actuels
	public int getNbElem() {
		return taille_actuelle;
	}

	public synchronized int retrait() throws InterruptedException {
		l.lock();
		int r;
		try {
			while(taille_actuelle==0) {
				System.out.println("Retrait impossible! Buffer Vide");
				buff_retrait.await();
			}
			r=tab[retrait];
			retrait=(retrait+1)%tab.length;
			taille_actuelle--;
			buff_depot.signalAll();
		}catch(InterruptedException e){
			r=-1;
		}
		finally {
			l.unlock();
		}
		
			return r;
		
	}
}
