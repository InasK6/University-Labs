package abonne;

import java.util.Collection;

/**
 * Représente un ensemble d'abonnements.
 */
public interface IAbonnements {
	/**
	 * Enregistre un abonnement : suiveur est abonné de suivi ; suivi est ajouté aux abonnements de suiveur.
	 * @param suivi le nom de la personne suivie
	 * @param suiveur le nom de la personne qui s'abonne
	 * @return true si l'abonnement n'existait pas encore, false sinon.
	 */
	public boolean addAbonnement (String suivi, String suiveur);
	/**
	 * Efface un abonnement : suiveur n'est plus abonné de suivi ; suivi est retiré des abonnements de suiveur.
	 * @param suivi le nom de la personne suivie
	 * @param suiveur le nom de la personne qui s'abonne
	 * @return true si l'abonnement existait et a été détruit, false (et aucun effet) sinon.
	 */
	public boolean remAbonnement (String suivi, String suiveur);
	/**
	 * Calcule la liste des abonnés d'une personne.
	 * @param suivi le nom de la personne suivie
	 * @return l'ensemble (sans doublon) des noms des personnes abonnées à suivi
	 */
	public Collection<String> getAbonnes (String suivi);
	/**
	 * Calcule la liste des abonnements d'une personne, i.e. l'ensemble des personnes qu'elle suit.
	 * @param suiveur le nom de la personne qui s'abonne
	 * @return l'ensemble (sans doublon) des noms des personnes auquel suiveur est abonné
	 */
	public Collection<String> getAbonnements (String suiveur);
	/**
	 * Calcule l'ensemble (SANS doublon) des noms de toute personne qui a au moins un abonné.
	 */
	public Collection<String> getAllSuivis();
	/**
	 * Calcule l'ensemble (SANS doublon) des noms de toute personne abonnée à au moins une autre personne.
	 */
	public Collection<String> getAllSuiveurs();
}