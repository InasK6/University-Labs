
public class Client implements Runnable{
	private int id;
	private static int CPT=0;
	private Object o=new Object();
	private SalonDeCoiffure s;
	private boolean CoiffeurOccupe=true;
	private boolean CoiffureEnCours=true;
	
	public Client(SalonDeCoiffure s) {
		synchronized (o) {
			id=++CPT;
		}
		this.s=s;
	}
	public String toString() {
		return "Client "+id+" ";
	}
	public synchronized void attendreTour() throws InterruptedException {
		System.out.println(this+ "attend son tour");
		while(CoiffeurOccupe) {
			wait();
		}
	}
	
	public synchronized void Accueilli() {
		CoiffeurOccupe=false;
		notifyAll();
	}
	
	public synchronized void partir() throws InterruptedException{
		System.out.println(this+" en train d'être coiffé");
		while(CoiffureEnCours) {
			wait();
		}
		
	}
	public synchronized void finCoiffure() {
		CoiffureEnCours=false;
		notifyAll();
	}
	
	/*
	 * On n'est pas obligé de remettre les booleens à true car les fonctions ne sont appelés qu'une seule fois
	 * on aurait même pu se contenter de if et notiy au lieu de while et notifyAll
	 */
	public void run() {
		boolean b;
		try{
			b=s.entrer(this);
			if(b) {
		
				attendreTour();
				partir();
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		/*finally {
			System.out.println(" fin du run de  "+this);

		}*/
	}
}
