import java.io.File;
import java.io.FileNotFoundException;

public class TestProduitParallele {

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

		
		
		CalculElem[] calcul= new CalculElem[mat.getNblignes()*mat1.getNbcolonnes()];

		for(int i=0; i<mat.getNblignes(); i++) {
			for(int j=0; j<mat1.getNbcolonnes(); j++) {
				calcul[i*mat1.getNbcolonnes()+j]= new CalculElem(m, mat, mat1,i, j);
			}
		}
		
		Thread[] ThCalculs=new Thread[mat.getNblignes()*mat1.getNbcolonnes()];
		
		for(int k=0; k<mat.getNblignes()*mat1.getNbcolonnes(); k++) {
			ThCalculs[k]=new Thread(calcul[k]);
			ThCalculs[k].start();
		}
		
		try {
			for(int k=0; k<mat.getNblignes()*mat1.getNbcolonnes(); k++) {
				ThCalculs[k].join();
			}
			System.out.println("Resultat produit");
			m.affichage();
		}
		catch (InterruptedException e) {
			System.out.println("Calcul interrompu");
		}	
		
		

	}

}
