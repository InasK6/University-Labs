import java.util.concurrent.Semaphore;

public class cond {
	private Semaphore condition=new Semaphore(0);
	private MonitorAvecSemaphores m;
	//private Semaphore signal=new Semaphore(0);
	private int nbAttente=0;
	
	public cond(MonitorAvecSemaphores m) {
		this.m=m;
	}
	
	/*
	 * Se met en attente et lache le verrou, ne le reprend pas tant qu'il n'y a pas de signal
	 */
	
	public void Waitt() throws InterruptedException {
		
		nbAttente++;	// On incrémente d'abord avant de lacher le verrou, pour éviter que le signal ne demarre
						// avant la synchronisation
		m.UnLock();
		condition.acquire();
		m.Lock();
	}
	/*
	 * Reveille une Thread en attente, donc diminue le nombre de Thread endormi
	 */
	public void Signal() {
		if(nbAttente>0) {
			condition.release();
			nbAttente--;
		}
		
	}
}
