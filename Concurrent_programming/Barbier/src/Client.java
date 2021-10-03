
public class Client implements Runnable{

	private SalonCoiffure s;
	private final int id;
	private static int CPT=0;
	private final Object mutex=new Object();
	
	public Client(SalonCoiffure s) {
		synchronized(mutex) {
			id=++CPT;
		}
		this.s=s;
	}
	
	public String toString() {
		String s="";
		s+=id;
		return s;
	}
	
	public void run() {
		try{
			if(s.entrerSalle(this)) {
				s.ClientAttend(this);
			}
		}
		catch(Exception e) {
			System.out.println("Problème dans le run du Client");
		}
	}
}
