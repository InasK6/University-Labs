import java.util.concurrent.Semaphore;

/*
 * Question 6:
 * S'il y a des babouins en attente, on inverse le sens. Sinon laisse la corde vide 
 * Il faut compter le nombre des babouins qui attendent dans les deux sens
 * 
 */
public class Corde3 {

	private int[] CPT= new int[2];	// nb babouins en attente sur chaque porte
									// On utilise deux compteurs car quand on en a un seul, il est aussi incrémenté
									// par les babouins bloqués et donc empêche le compteur de passer à 0 pour changer
	        						// de sen, tout le monde est bloqué
	
	private int sens=0;		// On rajoute une variable sens pour obliger l'alternation des passages
									// pour ne pas avoir un même coté qui reprend à chaque fois
	private Semaphore mutex=new Semaphore(1); 
	private Semaphore[] Sem=new Semaphore[2];
	// On a une deuxième condition d'attente, on rajoute deux nouveaux semaphores, un pour chaque file d'attente
	private Semaphore[] Sem2=new Semaphore[2];
	private int[] CPT2= new int[2];
	
	public Corde3() {
		for(int i=0; i<2;i++) {
			Sem[i]=new Semaphore(1);
			Sem2[i]=new Semaphore(0);
		}
	}
	
	
	public void acceder(Position position) throws InterruptedException{
		mutex.acquire();
		if (CPT[0]==0 && CPT[1]==0) {
			Sem[(position.index()+1)%2].acquire();
		}
		//System.out.println(" Here "+CPT[(position.index())]);
		if(CPT[(position.index())]==5) {
			CPT2[(position.index())]++;
			mutex.release();
			Sem2[position.index()].acquire();
			mutex.acquire();
		}
		
		CPT[(position.index())]++;

		// Si on ne fait pas ça, on risque de bloquer sur le acquire alors qu'on a changé de position
		//System.out.println(position+" debug"+ CPT[(position.index())]);
		if(sens!=position.index() || CPT[(position.index())]>5 ) {
			//System.out.println(position+" aquiert");
			mutex.release();
			
			Sem[position.index()].acquire();
		}
		else {
			//System.out.println(position+" fin acquerir");
			mutex.release();
			
			/*Sem[position.index()].acquire();
			Sem[position.index()].release();*/
			// On peut s'en passer 
		}
			
	}
	
	public void lacher(Position position) throws InterruptedException {
		mutex.acquire();
		CPT[position.index()]--;
		//System.out.println(position+ " CPT "+CPT[position.index()]);
		
		if(CPT[position.index()]==0 && CPT[(position.index()+1)%2]!=0) {
			
			Sem[(position.index()+1)%2].release(Math.min(5, CPT[(position.index()+1)%2]));
			//sens=sensInverse <----------- on n'a pas la valeur de sens inverse de type Position
			// si on le garde en variable de la classe, il faudra faire des tests avec positions
			
			// un tableau avec les deux positions;
			Sem2[position.index()].release(Math.min(5, CPT2[(position.index())]));
			System.out.println("Changement de sens");
			sens=(sens+1)%2;
		}
		//System.out.println("fin lacher");
		mutex.release();
		
	}
}
