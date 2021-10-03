package forum;

public interface IChatter {
	/**
	 * Le nom de ce chatter, utilisé comme nom d'auteur pour les message qu'il envoie.
	 * @return une chaine non vide, désignant de façon unique ce chatter
	 */
	String getName();
	/**
	 * Signale que quelqu'un a posté un message sur l'un des IForum auquel le chatter participe actuellement.
	 * Comme le chatter peut être abonné à plusieurs forums, on lui précise le nom du forum ou ce message a été posté.
	 * @param forum nom du forum où a été posté ce message
	 * @param auteur nom de l'auteur du message
	 * @param message contenu du message
	 */
	void messageRecu (String forum, String auteur, String message);
	
}
