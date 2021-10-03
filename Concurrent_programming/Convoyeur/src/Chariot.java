import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chariot {
	private ArrayList<AleaObject> contenu=new ArrayList<AleaObject>();
	private final int poidsMax;
	private final int nbMax;
	private int poidsCourant=0;
	
	
	// variables pour gérer la concurrence
	private boolean decharge=true;
	private boolean charge=false;
	private Lock l= new ReentrantLock();
	private Condition Decharge=l.newCondition();
	private Condition Charge=l.newCondition();
	
	public Chariot( int p, int n) {
		poidsMax=p;
		nbMax=n;
	}
	
	public String toString() {
		String s="Chariot de poids max "+poidsMax+" et de nombre max "+nbMax+" de contenu: \n";
		for( int i=0; i<contenu.size(); i++) {
			s+=contenu.get(i);
			s+="\n";
		}
		return s;
	}
	public boolean PlaceDispo(AleaObject o) {
		l.lock();
		try {
			if(contenu.size()<nbMax && poidsCourant+o.getPoids()<=poidsMax) {
				System.out.println("Chargement possible");
				charge=false;
				return true;
			}
			else {
				System.out.println("Pas de place disponible, attente de déchargement ");
				charge=true;
				decharge=false;
				Charge.signalAll();
				return false;
			}
			
		}
		finally {
			l.unlock();
		}
	}
	
	public void charger(AleaObject o) throws InterruptedException{
		l.lock();
		try{
			while(!decharge) {
				Decharge.await();
			}
			
			System.out.println("Chargement du chariot");
			contenu.add(o);
			poidsCourant+=o.getPoids();
			System.out.println("Chariot chargé avec "+o);

		}finally {
			l.unlock();
			
		}
	}
	
	public void StockVide() {
		l.lock();
		try {
			charge=true;
			Charge.signalAll();
		}finally {
			l.unlock();
		}
	}
	public void decharger() throws InterruptedException {
		l.lock();
		try {
			while(!charge) {
				Charge.await();
			}
			System.out.println("début du déchargement de la marchandise du chariot par le dechargeur");
			int i=contenu.size();
			ArrayList<AleaObject> liste=new ArrayList<AleaObject>();
			for(int j=i-1; j>=0; j--) {
				AleaObject o=contenu.remove(j);
				liste.add(o);
			}
			decharge=true;
			charge=false;
			Decharge.signalAll();
			poidsCourant=0;
			System.out.println("fin du déchargement de la marchandise"+ liste+" du chariot");
		}finally {
			l.unlock();
		}
		
	}
}
