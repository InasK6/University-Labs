import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCalculParallelCall {
	public static void main(String[] args) {
		//matrice 1
		File f=new File("donnees_produit1");
		MatriceEntiere mat=null;
		try {
		mat=new MatriceEntiere(f);
		System.out.println("Matrice produit 1");
		mat.affichage();
		}
		catch( FileNotFoundException e) {
			System.out.println("Votre fichier n'existe pas");
		}
		//matrice 2
		File f2=new File("donnees_produit2");
		MatriceEntiere mat1=null;
		try {
		mat1=new MatriceEntiere(f2);
		System.out.println("Matrice produit 2");
		mat1.affichage();
		}
		catch( FileNotFoundException e) {
			System.out.println("Votre fichier n'existe pas");
		}
		
		MatriceEntiere m=new MatriceEntiere(mat.getNblignes(),mat1.getNbcolonnes());
		
		//initialisation des valeurs de la matrice à 0
		for (int i=0; i<mat.getNblignes(); i++) {
			for(int j=0; j<mat1.getNbcolonnes(); j++) {
				m.setElem(i, j, 0);
			}
		}

		
		
		produitLigneColonneClass[] calcul= new produitLigneColonneClass[mat.getNblignes()*mat1.getNbcolonnes()];

		for(int i=0; i<mat.getNblignes(); i++) {
			for(int j=0; j<mat1.getNbcolonnes(); j++) {
				calcul[i*mat1.getNbcolonnes()+j]= new produitLigneColonneClass( mat, mat1,i, j);
			}
		}
		//Question 1
		int NB_THREADS=10;
		ExecutorService e=Executors.newFixedThreadPool(NB_THREADS);
		
		/*
		 * Question 5/6: pour soumettre la tâche pour execution on utilise submit
		 * 				pour récupérer le résultat on utilise un ExecutorCompletionService, on utilise la méthode take
		 */
		
		
		ExecutorCompletionService ECS=new ExecutorCompletionService(e);
		
		for(int k=0; k<mat.getNblignes()*mat1.getNbcolonnes(); k++) {
			e.submit(calcul[k]);
		}
		//Question 3
		e.shutdown();
		
		// plus besoin de l'attente car take et get attendent la fin du calcul par le thread
		/*while(!e.isTerminated()) {
			//Attente semi-active, on aurait pas pu faire un wait car on n'a pas de signal quelque part
			try{
				Thread.sleep(10);
			}catch ( Exception exep) {
				exep.printStackTrace();
			}
		}*/
		try{for(int i=0; i<mat.getNblignes(); i++) {
			for(int j=0; j<mat1.getNbcolonnes(); j++) {
				m.setElem(i,j,((Integer) ECS.take().get()).intValue());
			}
		}
		
		m.affichage();
		}catch( Exception exep ) {
			exep.printStackTrace();
		}
		
	}
}
