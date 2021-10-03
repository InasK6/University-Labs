import java.util.concurrent.Semaphore;

/*
 * Question 1: Version Semaphores
 */

public class Corde {
	
	private boolean occupe=false;
	private Semaphore c=new Semaphore(1);
	public void acceder(int position) throws InterruptedException{
		c.acquire();
	}
	
	public void lacher(int position) {
		c.release();
	}
}
