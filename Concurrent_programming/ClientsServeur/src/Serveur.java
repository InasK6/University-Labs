import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/*
 * TP: Serveur utilisant pool de threads
 * Question 1: Solution pour que le prise en compte de l'arrivée d'une réponse ne soit pas retardée 
 * lorsqu'un serveur est bloquée en attente d'une requête
 */
public class Serveur implements Runnable{

	//private ReponseRequete requete;
	// peut traîter une requête à la fois seulement  
	private Client c;
	private ReentrantLock l=new ReentrantLock();
	private Condition Vide=l.newCondition();
	private Condition Rempli=l.newCondition();
	private boolean occupe=false;
	private Requete r;
	

	/*
	 * TME pools
	 * Question 2:
	 */
	private final int NB_THREADS=5;
	private ExecutorService exec=Executors.newFixedThreadPool(NB_THREADS);
	ExecutorCompletionService<ReponseRequete> ECS=new ExecutorCompletionService<ReponseRequete>(exec);
	
	/*
	 * Question 3
	 */
	private int NB_CLIENTS;
	public Serveur(int n) {
		NB_CLIENTS=n;
	}
	public void soumettre(Requete r) throws InterruptedException{
		l.lock();
		try{while(occupe) {
			Vide.await();
		}
		occupe=true;
		Rempli.signalAll();
		this.r=r;
		c=r.getC();
		System.out.println("Soumission effectuée de la requete "+r);
		}finally {
		l.unlock();
		}
	}
	public void traiterRequete() {
		l.lock();
		try{occupe=false;
		Vide.signalAll();

		
		/*
		 * 
		 * A la création des servants:
		 * 
		 */
		exec.submit((new Servant(this,r)));
		
		}
		finally {
			l.unlock();}
	}
	public void attendreRequete() throws InterruptedException {
		l.lock();
		try{System.out.println("le serveur est en Attente d'une requête");
		while(!occupe) {
			Rempli.await();
		}
		}finally {
		l.unlock();}
	}

	/*
	 * Question 3: pour bien terminer mon programme, je récupère en paramètre du serveur le nombre de clients max
	 * afin de limiter le nombre d'iteration de la boucle run, une fois celle ci terminée, on fait un shutdown() du pool
	 */

	public void run() {
			try {
				for (int i=0; i<NB_CLIENTS; i++ ) {
					attendreRequete();
					traiterRequete();
					System.out.println("Here");
					Future<ReponseRequete> rep=ECS.take();
					System.out.println("Here2");
					ReponseRequete reponse=rep.get();
					System.out.println("Here3");
					r.getC().requeteServie(reponse);
				}
				exec.shutdown();
				while(!exec.isTerminated()) {
					Thread.sleep(10);
				}
				System.out.println("Toutes les réponses aux requêtes on été effectuées");
			}catch(InterruptedException e) {
				System.out.println("Serveur interrompu");
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * L'inconvénient d'utiliser un nombre fixe de Threads est le retardement des executions 
			 * car les clients se mettent des une file d'attente en attendant la fin d'exécution des réponses aux
			 *  requêtes d'autres clients
			 *  Pour y remédier il faudrait qu'on ait un nombre de Threads dans le pools assez grand
			 *  pour qu'à l'arrivée d'un nouveau client, Le Thread le plus ancien du pool finisse 
			 *  son exécution
			 */
		
	}
	
}