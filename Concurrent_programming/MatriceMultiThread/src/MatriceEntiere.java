
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/*Question 1
 * Il y a deux méthodes possibles:
 * public static Double valueof(String s) throws NumberFormatException
 * public static Double parseDouble(String r) throws NumberFormatException
 */

/*
 * Question 2
 */
public class MatriceEntiere {
	private int nblignes;
	private int nbcolonnes;
	private int[][] tab;

	
	public MatriceEntiere(int lignes, int colonnes) {
		nblignes=lignes;
		nbcolonnes=colonnes;
		tab=new int[lignes][colonnes];
		
	}
	public MatriceEntiere(File fichier) throws FileNotFoundException{
		BufferedReader in;
		in=new BufferedReader(new FileReader(fichier));
		Scanner sc=new Scanner(in);
		nblignes=sc.nextInt();
		nbcolonnes=sc.nextInt();
		tab=new int[nblignes][nbcolonnes];
		int i=0;
		int j=0;
		
		while (i<nblignes) {
			j=0;
			while(j<nbcolonnes) {
				tab[i][j]=sc.nextInt();
				j++;
			}
			i++;
		}
		sc.close();
		
		
	}
	// getters
	public int getNblignes() {
		return nblignes;
	}

	public int getNbcolonnes() {
		return nbcolonnes;
	}

	public int[][] getTab() {
		return tab;
	}
	public void setTab(int[][] tab) {
		this.tab = tab;
	}
	
	
	public int getElem(int i, int j) {
		return tab[i][j];
	}
	
	public void setElem(int i, int j, int val) {
		tab[i][j]=val;
	}
	
	
	//Question 3
	public String toString() {
		String s="";
		for(int i=0; i<nblignes; i++) {
			for(int j=0; j<nbcolonnes; j++) {
				s+=tab[i][j]+" ";
			}
			s+="\n";
		}
		return s;
	}
	
	//affichage
	public void affichage() {
		System.out.println(this.toString());
	}
	
	//Question 4
	public void initZero() {
		for(int i=0; i<nblignes; i++) {
			for(int j=0; j<nbcolonnes; j++) {
				tab[i][j]=0;
			}
		}
	}
	public MatriceEntiere Transpose() {
		MatriceEntiere mat=new MatriceEntiere(nbcolonnes,nblignes);
		for(int i=0; i<nbcolonnes; i++) {
			for(int j=0; j<nblignes; j++) {
				mat.tab[i][j]=tab[j][i];
			}
		}
		return mat;
	}
	public MatriceEntiere sommeMat(MatriceEntiere mat2) throws TaillesNonConcordantesException{
		
		if(nblignes!=mat2.nblignes || nbcolonnes!=mat2.nbcolonnes) {
			throw new TaillesNonConcordantesException("La tailles de deux matrices lors d'une addition doivent être égales");
		}
		MatriceEntiere mat=new MatriceEntiere(nbcolonnes,nblignes);

		for(int i=0; i<nblignes; i++) {
			for(int j=0; j<nbcolonnes; j++) {
				mat.tab[i][j]=tab[i][j]+mat2.tab[i][j];
			}
		}
		return mat;
	}
	 
	public MatriceEntiere MatriceScalaire(int scalaire) {
		MatriceEntiere mat=new MatriceEntiere(nblignes, nbcolonnes);
		for(int i=0; i<nblignes; i++) {
			for(int j=0; j<nbcolonnes;j++) {
				mat.tab[i][j]=scalaire* tab[i][j];
			}
		}
		return mat;
	}
	public MatriceEntiere produitMat(MatriceEntiere mat) throws TaillesNonConcordantesException{
		if(this.nbcolonnes!=mat.nblignes) {
			throw new TaillesNonConcordantesException("Le nombre de colonnes de votre première matrice doit être égale aux nombre de lignes de votre deuxième colonne");
		}
		 MatriceEntiere M=new MatriceEntiere(this.nblignes, mat.nbcolonnes);
		 for(int i=0; i<nblignes; i++) {
			 for(int j=0; j<mat.nbcolonnes; j++) {
				 M.tab[i][j]=0;
				 for(int k=0; k<this.nbcolonnes; k++) {
					 M.tab[i][j]+=this.tab[i][k]*mat.tab[k][j];
				}
			 }
		 }
		return M; 
	 }
	
	// TME 2 suite
	
	//Question 1
	public static int produitLigneColonne(MatriceEntiere m1, int i, MatriceEntiere m2, int j ) throws TaillesNonConcordantesException{
		// Exception Case
		if( m1.nbcolonnes!= m2.nblignes) {
			throw new TaillesNonConcordantesException("Le nombre de lignes de votre première matrice doit être égale aux nombre de colonnes de votre deuxième colonne");
		}
		
		// calcul du produit de la ligne
		int produit=0;
		for(int k=0; k< m1.nbcolonnes; k++) {
			produit+=m1.tab[i][k]*m2.tab[k][j];
		}
		return produit; 
	}
	
}
