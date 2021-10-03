package forum;

public interface IForum {
	/**
	 * Le nom de ce forum, typiquement le sujet de discussion.
	 * @return le nom du forum
	 */
	String getName();
	/**
	 * Permet de poster ou écrire un message sur le forum.
	 * Tous les participants actifs en seront notifiés.
	 * @param auteur Le nom de l'auteur du message, peut être arbitraire (pas nécessairement le nom d'un IChatter participant).
	 * @param message Le contenu du message
	 * @return vrai si le message a bien été posté, faux sinon.
	 */
	boolean posterMessage (String auteur, String message);

	/**
	 * Permet à un IChatter de rejoindre le forum.
	 * A partir du moment où il a rejoint le forum, il sera notifié @linkto{IChatter.messageRecu} de tout message posté dessus.
	 * On en peut pas rejoindre deux fois le même forum (return false).
	 * @param chatter la personne à abonner au forum
	 * @return vrai si le chatter à effectivement réussi à rejoindre le forum
	 */
	boolean rejoindre (IChatter chatter);
	/**
	 * Le nombre de participants actifs, i.e. ayant actuellement rejoint avec succès ce forum.
	 * @return un entier >= 0
	 */
	int nbParticipants();
	/**
	 * Permet à un IChatter ayant précédemment rejoint le forum de s'en désabonner.
	 * Il ne sera plus notifié des posts sur ce forum.
	 * @param chatter le chatter qui souhaite quitter le forum
	 * @return vrai si le chatter était effectivement abonné, faux sinon 
	 */
	boolean quitter (IChatter chatter);	
}
