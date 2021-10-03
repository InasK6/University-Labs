
public class CalculElem implements Runnable {
	private MatriceEntiere m;
	private MatriceEntiere m1;
	private MatriceEntiere m2;
	private int ligne;
	private int colonne;
	
	public CalculElem(MatriceEntiere e, MatriceEntiere e1, MatriceEntiere e2,int l, int c) {
		m=e;
		m1=e1;
		m2=e2;
		ligne=l;
		colonne=c;
	}
	public void run() {
		try {
			int val=MatriceEntiere.produitLigneColonne(m1, ligne, m2, colonne);
			m.setElem(ligne, colonne, val);
		}
		catch(TaillesNonConcordantesException e) {
			System.out.println("Exception Tailles");
		}
	}
	public MatriceEntiere getM() {
		return m;
	}
}
