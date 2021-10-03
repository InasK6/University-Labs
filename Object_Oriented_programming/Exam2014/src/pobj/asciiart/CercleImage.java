package pobj.asciiart;
/*
 * DP Adaptor
 */
public class CercleImage implements Image{
	private AsciiCercle cercle;
	private int largeur;
	public CercleImage(int ox, int oy,int rayon, int largeur){
		cercle=new AsciiCercle(ox, oy, rayon  );
		this.largeur=largeur;
	}
	
	@Override
	public void affiche() {
		// TODO Auto-generated method stub
		cercle.dessine(largeur);
	}

	@Override
	public int nbLignes() {
		// TODO Auto-generated method stub
		return largeur;
	}

	@Override
	public int nbColonnes() {
		// TODO Auto-generated method stub
		return largeur;
	}

	@Override
	public int taille() {
		// TODO Auto-generated method stub
		return largeur*largeur;
	}
	
}
