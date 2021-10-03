package forum;

public class TestForumDecorator {
	public static void main(String[] args) {
		Forum f = new Forum("Java");
		IForum decore = new ForumDecoreConcret(f);
		// on peut utiliser f ou decore de façon identique, cf tests précédents.	
	}
}

/** Une classe concrète, joue le rôle de décorateur concret.
 */
class ForumDecoreConcret extends ForumDecorator {
	/**
	 * ForumDecorator a donc un constructeur prenant un IForum en paramètre.
	 * @param decore le forum à décorer.
	 */
	public ForumDecoreConcret(IForum decore) {
		super(decore);
	}
}