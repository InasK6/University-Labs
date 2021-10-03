package pobj.asciiart;
/*
 * 
 * Design pattern Composite
 */
public class ImageDouble implements Image {
	private Image[] images=new Image[2];

	public ImageDouble(Image img1, Image img2) {
		assert(img1.nbColonnes()==img2.nbColonnes());
		images[0]=img1;
		images[1]=img2;
	}



	@Override
	public void affiche() {
		// TODO Auto-generated method stub
		for(int i=0; i<images.length; i++) {
			images[i].affiche();
		}
	}

	@Override
	public int nbLignes() {
		// TODO Auto-generated method stub
		return images[0].nbLignes()+images[1].nbLignes();
	}

	@Override
	public int nbColonnes() {
		// TODO Auto-generated method stub
		return images[0].nbColonnes();
	}

	@Override
	public int taille() {
		// TODO Auto-generated method stub
		return images[0].taille()+images[1].taille();
	}
}
