import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Question 1: Version Lock
 */
public class laCorde {
	private ReentrantLock l= new ReentrantLock();
	private Condition Libere=l.newCondition();
	private boolean occupe=false;

	public void acceder(int position) throws InterruptedException{
		l.lock();
		try{
				while(occupe) {
					Libere.await();
				}
			
				occupe=true;
		}finally {
			l.unlock();
		}
	}
	
	public void lacher(int position) {
		l.lock();
		try{
			occupe=false;
			Libere.signalAll();
		}finally {
			l.unlock();
		}
	}
}
/*
 * Version plus simple
 */
/*
	public void acceder(int position){
		l.lock();
	}
	
	public void lacher(int position){
		l.unlock();
	}


*/
