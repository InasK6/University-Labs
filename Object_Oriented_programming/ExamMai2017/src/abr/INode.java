package abr;

public interface INode {
	/**
	 * La valeur (clé) associée à ce node.
	 * @return la string associée au noeud courant
	 */
	String getValue();
	/**
	 * Le fils gauche de ce noeud, dont toutes les clés sont inférieures strictement à la clé. 
	 * @return le fils gauche ou null s'il n'y en a pas.
	 */
	INode getLeft();
	/**
	 * Le fils droit de ce noeud, dont toutes les clés sont supérieures strictement à la clé.
	 * @return le fils gauche ou null s'il n'y en a pas.
	 */
	INode getRight();
	/**
	 * Teste l'existence d'une clé particulière dans le sous arbre dont ce noeud est la racine.
	 * L'exploration compareTo la clé "s" à la clé courante, et décide si on peut répondre vrai,
	 *  où s'il faut poursuivre l'exploration dans le fils gauche ou le fils droit (attention aux fils à null).
	 * @param s la clé recherchée
	 * @return vrai si un node porte cette clé, faux sinon.
	 */
	boolean contains(String s);
	/**
	 * Ajoute une chaine à l'arbre.
	 * Crée un nouveau noeud et l'ajout à la bonne position s'il n'y a pas encore de noeud portant cette clé.
	 * @param s la clé à insérer
	 * @return vrai si la valeur a été insérée (avec création d'un Node), faux sinon (la clé existait déjà)
	 */
	boolean add (String s);
}
