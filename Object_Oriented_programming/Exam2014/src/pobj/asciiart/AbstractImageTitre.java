package pobj.asciiart;
/*
 * DP Decorator
 */
public abstract class AbstractImageTitre implements Image{
	private Image img;
	
	public AbstractImageTitre(Image i) {
		img=i;
	}
	/**
	 * 
	 * @see pobj.asciiart.Image#affiche()
	 */
	public void affiche() {
		img.affiche();
	}
	/**
	 * @return
	 * @see pobj.asciiart.Image#nbLignes()
	 */
	public int nbLignes() {
		return img.nbLignes();
	}
	/**
	 * @return
	 * @see pobj.asciiart.Image#nbColonnes()
	 */
	public int nbColonnes() {
		return img.nbColonnes();
	}
	/**
	 * @return
	 * @see pobj.asciiart.Image#taille()
	 */
	public int taille() {
		return img.taille();
	}
}
