package forum;

public class TestPerroquet {
	public static void main(String[] args) {
		IChatter alice = new SimpleChatter("alice");		
		IForum f1 = new Forum("Java");		
		f1.rejoindre(alice);		
		IForum f2 = new Forum("Prog");		
		
		IChatter charlie = new Perroquet("charlie", f2);
		f1.rejoindre(charlie);
				
		IChatter bob = new SimpleChatter("bob");
		f2.rejoindre(bob);
		
		// question 1.g)
		// f2.rejoindre(charlie);
		
		f1.posterMessage("alice", "J'ai la solution en O(1) !");
		f2.posterMessage("bob", "Menteur charlie, c'est Alice qui a trouvé !");
	}
}
/** Trace :
(chez alice) alice@Java> J'ai la solution en O(1) !
(chez bob) charlie@Prog> J'ai la solution en O(1) !
(chez bob) bob@Prog> Menteur charlie, c'est Alice qui a trouvé !
**/