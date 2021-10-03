package pobj.asciiart;

public class ImageTitre extends AbstractImageTitre {
	private	String titre;
	public ImageTitre(Image img, String title) {
		super(img);
		assert(title.length()<=img.nbColonnes());
		titre=title;
		
	}
	
	public void affiche() {
		System.out.println(titre);
		super.affiche();
	}
	public int nbLignes() {
		return super.nbLignes()+1;
	}
	public int taille() {
		return super.taille()+super.nbColonnes();
	}
}
