package pobj.asciiart;

public class ImageBrute implements Image{
	private char[][] pixel;
	
	public ImageBrute(int nblignes, int nbcolonnes) {
		pixel=new char[nblignes][nbcolonnes];
		for(int i=0; i<pixel.length; i++) {
			for(int j=0; j<pixel[0].length; j++)
			pixel[i][j]=' ';
		}
	}
	
	public ImageBrute(char[][] pixel) {
		this.pixel=pixel;
	}


	@Override
	public void affiche() {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<nbLignes(); i++) {
			for(int j=0; j<nbColonnes(); j++) {
				sb.append(pixel[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	@Override
	public int nbLignes() {
		// TODO Auto-generated method stub
		return pixel.length;
	}

	@Override
	public int nbColonnes() {
		// TODO Auto-generated method stub
		return pixel[0].length;
	}

	@Override
	public int taille() {
		// TODO Auto-generated method stub
		return nbLignes()* nbColonnes();
	}

}
