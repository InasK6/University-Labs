package forum;

import java.util.Arrays;
import java.util.Collection;

public class TestDecoration {
	public static void main(String[] args) {
		IChatter alice = new SimpleChatter("alice");
		IChatter bob = new SimpleChatter("bob");
		IChatter charlie = new SimpleChatter("charlie");
		
		IForum forum = new Forum("Java");
		
		forum = new ForumAnnonce(forum);
		
		Collection<String> bad = Arrays.asList("Python","Ruby","Scala");
		forum = new ForumProtege(forum, bad);
		
		forum.rejoindre(alice);
		forum.posterMessage("alice", "Il n'y a personne ?");
		forum.rejoindre(charlie);
		
		forum.posterMessage("alice", "Vive les examens ! Surtout en POBJ !");
		forum.posterMessage("charlie", "Tu parles, Python c'est tellement mieux !");
		forum.rejoindre(bob);
		forum.posterMessage("bob", "Ouais c'est trop cooool java.");
		forum.posterMessage("charlie", "Si au moins le prof connaissait Ruby...");
		
		forum.quitter(bob);
		forum.quitter(alice);
		forum.posterMessage("charlie", "Java 8 c'est le Scala du pauvre");
	}
}
/**
(chez alice) Java@Java> alice rejoint la discussion.
(chez alice) alice@Java> Il n'y a personne ?
(chez alice) Java@Java> charlie rejoint la discussion.
(chez charlie) Java@Java> charlie rejoint la discussion.
(chez alice) alice@Java> Vive les examens ! Surtout en POBJ !
(chez charlie) alice@Java> Vive les examens ! Surtout en POBJ !
(chez alice) Java@Java> bob rejoint la discussion.
(chez charlie) Java@Java> bob rejoint la discussion.
(chez bob) Java@Java> bob rejoint la discussion.
(chez alice) bob@Java> Ouais c'est trop cooool java.
(chez charlie) bob@Java> Ouais c'est trop cooool java.
(chez bob) bob@Java> Ouais c'est trop cooool java.
(chez alice) Java@Java> bob quitte la discussion.
(chez charlie) Java@Java> bob quitte la discussion.
(chez charlie) Java@Java> alice quitte la discussion.
**/