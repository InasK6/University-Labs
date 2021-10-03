package forum;

public abstract class ForumDecorator implements IForum{
	private IForum forum;
	
	public ForumDecorator (IForum f) {
		forum=f;
	}


	/**
	 * @return
	 * @see forum.IForum#getName()
	 */
	public String getName() {
		return forum.getName();
	}

	/**
	 * @param auteur
	 * @param message
	 * @return
	 * @see forum.IForum#posterMessage(java.lang.String, java.lang.String)
	 */
	public boolean posterMessage(String auteur, String message) {
		return forum.posterMessage(auteur, message);
	}

	/**
	 * @param chatter
	 * @return
	 * @see forum.IForum#rejoindre(forum.IChatter)
	 */
	public boolean rejoindre(IChatter chatter) {

		
		return forum.rejoindre(chatter);
	}

	/**
	 * @return
	 * @see forum.IForum#nbParticipants()
	 */
	public int nbParticipants() {
		return forum.nbParticipants();
	}

	/**
	 * @param chatter
	 * @return
	 * @see forum.IForum#quitter(forum.IChatter)
	 */
	public boolean quitter(IChatter chatter) {
		return forum.quitter(chatter);
	}
	
	
}
