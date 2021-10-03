import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/*
 * TD: Question 2: Le babouin s'engage dans la corde si:
 * - La corde est vide
 * - si les babouins déja engagés sont dans le même sens
 * 
 * Evaluation de la condition:
 * - on peut garder le nombre de babouins courant dans une variable
 * - on peut garder les babouins dans un ArrayBlockingQueue, 
 *   ou modifier position quand un babouin accède à la corde
 * TME: Question 2: On veut limiter le nombre de babouin sur une corde à 5
 */
public class Corde2 {
	private ReentrantLock l= new ReentrantLock();
	/*
	 * On a deux conditions d'attente, une à droit et une à gauche
	 * mais dans notre cas on peut utiliser une seule file car on ne peut pas avoir les deux qui attendent au meme temps
	 Donc le babouin s'engage si:
	 - corde vide
	 - ou si les babouins déja engagés sont dans le même sens et leur nombre<5
	 On rajoute simplement une condition dans le while: || nbcourant >=5
	 */
	private Condition Libere=l.newCondition();
	private Position P=null;
	private int nbcourant=0;
	
	public void acceder(Position position) throws InterruptedException{
		l.lock();
		try{
				while(nbcourant!=0 && (position!=P || nbcourant >=5)) {
					Libere.await();
				}
				if(nbcourant==0) {
					P=position;
				}
				nbcourant++;

		}finally {
			l.unlock();
		}
	}
	
	public void lacher(Position position) {
		l.lock();
		try{
			nbcourant--;
			if(nbcourant==0) {
				Libere.signalAll();
			}
		}finally {
			l.unlock();
		}
	}
}
