import java.util.concurrent.Callable;

//Question 4 de TD thème 9
public class produitLigneColonneClass implements Callable<Integer>{

	private MatriceEntiere m1;
	private MatriceEntiere m2;
	private int ligne;
	private int colonne;
	
	public produitLigneColonneClass(MatriceEntiere e1, MatriceEntiere e2,int l, int c) {
		m1=e1;
		m2=e2;
		ligne=l;
		colonne=c;
	}
	public Integer call() throws TaillesNonConcordantesException{
		
			int val=MatriceEntiere.produitLigneColonne(m1, ligne, m2, colonne);
			return new Integer(val);
		}

	}
	


