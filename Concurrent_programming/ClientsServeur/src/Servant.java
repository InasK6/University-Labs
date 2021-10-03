import java.util.concurrent.Callable;

public class Servant implements Callable<ReponseRequete> {
	private Serveur s;
	private Requete r;
	private static int CPT=0;
	private int id;
	private Object o=new Object();
	
	public Servant(Serveur se, Requete re) {
		synchronized(o) {
			id=++CPT;
		}
		s=se;
		r=re;
	}
	//Il faut signaler la fin du traîtement au client afin qu'il puisse envoyer une autre requête.
	public ReponseRequete traiter() {
		System.out.println("Traitement de "+r+" par le servant "+ id);
		if(r.getType()==1) {
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			/*while(true) {
				
			}*/
		}
		ReponseRequete rep=new ReponseRequete(r.getC(), r.getId());
		//r.getC().requeteServie(rep);
		System.out.println("fin du traitement de "+r+" par le servant "+ id);
		return rep;
		
	}
	public ReponseRequete call() {
		return traiter();
	}
}
