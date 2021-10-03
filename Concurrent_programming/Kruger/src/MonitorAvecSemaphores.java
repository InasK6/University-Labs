import java.util.concurrent.Semaphore;

/*
 * Question 3:
 * Implémentation de lock et unlock avec Semaphore
 * Question 4:
 * Implémentation de wait, await sur une condition
 */
public class MonitorAvecSemaphores {
	private Semaphore s1=new Semaphore(1); //Exclusion mutuelle, un signalAll
	//private Semaphore s2=new Semaphore(1); // Signal
	
	
	public void Lock() throws InterruptedException{
		s1.acquire();
		
	}
	public void UnLock() {
		s1.release();
	}

	
}
