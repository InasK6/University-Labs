import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Renne implements Runnable{
	private  final int NB_TOTAL_RENNES;
	private int id;
	//private Santa s;
	private PereNoel s;
	private static CyclicBarrier c;
	
	
	public Renne(int i, PereNoel s, int nb) {
		id=i;
		this.s=s;
		NB_TOTAL_RENNES=nb;
		c=new CyclicBarrier(NB_TOTAL_RENNES);
	}
	
	public void tirer() throws InterruptedException, BrokenBarrierException {
		c.await();
	}
	public String toString() {
		return "Renne "+id+" ";
	}
	public void run() {
		try{
			System.out.println("Le renne "+id+" est en route");
			s.sayHello(this);
			tirer();
			System.out.println(" OH hiiiss!!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
