import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 
 * @author inas
 *	question 4: Implémentation avec lock
 * deux files d'attentes car on ne veut pas que des objets différents attendent dans la même file 
 */
public class Santa implements Runnable {
	private final int NB_TOTAL_RENNES;
	private ReentrantLock l=new ReentrantLock();
	private Condition renne=l.newCondition();
	private Condition santa=l.newCondition();
	private boolean busy=false;
	private int nbRenneAttele=0;
	private Condition renneAttele= l.newCondition();
	
	public Santa(int n){
		NB_TOTAL_RENNES=n;
	}
	public void sayHello(Renne r) throws InterruptedException {
		l.lock();
		try {
			while(busy) {
				santa.await();
			}
			System.out.println(r+ "dit: Salut père Noel");
			busy=true;
			renne.signal();
			//Question 5
			renneAttele.await();
		}finally {
			l.unlock();
		}
	}
	
	public void Atteler() throws InterruptedException{
		l.lock();
		try {
			while(!busy) {
				renne.await();
			}
			System.out.println("Santa s'occupe d'un renne");
			Thread.sleep(300);
			nbRenneAttele++;
			System.out.println("Encore un renne attelé");
			busy=false;
			santa.signal();
			//Question 5, Question 6 on le retire
			renneAttele.signal();
		}finally {
			l.unlock();
		}
	}
	//Question 6
	public void pereNoelMonte() throws InterruptedException{
		l.lock();
		try{
			
			renneAttele.signalAll();
		}finally {
			l.unlock();
		}
		
	}
	public void run() {
		try {
			while(nbRenneAttele!=NB_TOTAL_RENNES ) {
				Atteler();
			}
			
			Thread.sleep(100);
			// Pour la question 6
			pereNoelMonte();
		System.out.println("Le père noél a fini d'atteler ses rennes, monte dans le traineau");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
