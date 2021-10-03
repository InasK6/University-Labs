package forum;
public class TestForum {
	public static void main(String[] args) {
		IChatter alice = new SimpleChatter("alice");
		IChatter bob = new SimpleChatter("bob");
		IChatter charlie = new SimpleChatter("charlie");
		
		IForum forum = new Forum("Java");

		forum.rejoindre(alice);
		forum.posterMessage("alice", "Il n'y a personne ?");
		forum.rejoindre(charlie);
		
		forum.posterMessage("alice", "Vive les examens ! Surtout en POBJ !");
		forum.posterMessage("charlie", "Tu parles, Python c'est tellement mieux !");
		forum.rejoindre(bob);
		forum.posterMessage("bob", "Ouais c'est trop cooool java.");
		forum.posterMessage("charlie", "Si au moins le prof connaissait Ruby...");
		
		forum.quitter(alice);
		forum.quitter(bob);
	}
}
/** Trace attendue :
(chez alice) alice@Java> Il n'y a personne ?
(chez alice) alice@Java> Vive les examens ! Surtout en POBJ !
(chez charlie) alice@Java> Vive les examens ! Surtout en POBJ !
(chez alice) charlie@Java> Tu parles, Python c'est tellement mieux !
(chez charlie) charlie@Java> Tu parles, Python c'est tellement mieux !
(chez alice) bob@Java> Ouais c'est trop cooool java.
(chez charlie) bob@Java> Ouais c'est trop cooool java.
(chez bob) bob@Java> Ouais c'est trop cooool java.
(chez alice) charlie@Java> Si au moins le prof connaissait Ruby...
(chez charlie) charlie@Java> Si au moins le prof connaissait Ruby...
(chez bob) charlie@Java> Si au moins le prof connaissait Ruby...
**/