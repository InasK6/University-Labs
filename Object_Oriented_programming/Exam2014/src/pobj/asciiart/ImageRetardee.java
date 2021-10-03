package pobj.asciiart;
/*
 * DP Proxy
 */
public class ImageRetardee implements Image {
	private ImageBrute img;
	private int nbLignes;
	private int nbColonnes;
	
	public ImageRetardee(int nblignes, int nbcol) {
		nbLignes=nblignes;
		nbColonnes=nbcol;
		
	}
	@Override
	public void affiche() {
		// TODO Auto-generated method stub
		if(img==null) {
			img=new ImageBrute(nbLignes, nbColonnes);
		}
		img.affiche();
	}

	@Override
	public int nbLignes() {
		// TODO Auto-generated method stub
		return nbLignes;
	}

	@Override
	public int nbColonnes() {
		// TODO Auto-generated method stub
		return nbColonnes;
	}

	@Override
	public int taille() {
		// TODO Auto-generated method stub
		if(img==null) {
			return 0;
		}
		return img.taille();
	}


}
