package pobj.asciiart;

import java.util.Comparator;

/*
 * il faut ajouter extends Comparable<Image>,
 * la classe qui implémengte Image doit avoir une méthode public int compareTO
 */
public interface Image {
	public void affiche();
	public int nbLignes();
	public int nbColonnes();
	public int taille();
}

/*
public int compare(Image i1, Image i2){
	return compare(i1.taille(), i2.taille());
}

*/