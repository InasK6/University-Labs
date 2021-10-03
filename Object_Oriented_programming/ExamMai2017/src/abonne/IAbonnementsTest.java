package abonne;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import abonne.Abonnements;

public class IAbonnementsTest {

	@Test
	public void testBasique() {
		IAbonnements abos = new Abonnements();
		
		assertTrue(abos.getAbonnes("a").isEmpty());
		assertTrue(abos.getAbonnes("b").isEmpty());
		assertTrue(abos.getAbonnes("c").isEmpty());
		assertTrue(abos.getAllSuivis().isEmpty());
		assertTrue(abos.getAbonnements("a").isEmpty());
		assertTrue(abos.getAbonnements("b").isEmpty());
		assertTrue(abos.getAbonnements("c").isEmpty());
		assertTrue(abos.getAllSuiveurs().isEmpty());
		
	
		assertTrue(abos.addAbonnement("a", "b"));
		assertTrue(abos.addAbonnement("a", "c"));
		assertTrue(abos.addAbonnement("b", "c"));
		

		
		Collection<String> set = abos.getAbonnes("a");
		assertTrue(set.size()==2 && set.contains("b") && set.contains("c"));
		set = abos.getAbonnes("b");
		
		assertTrue(set.size()==1 && set.contains("c"));
		assertTrue(abos.getAbonnes("c").isEmpty());
		set = abos.getAllSuivis();
		assertTrue(set.size()==2 && set.contains("a") && set.contains("b"));
		
		System.out.print(abos.getAbonnements("a"));
		assertTrue(abos.getAbonnements("a").isEmpty());
		set = abos.getAbonnements("b");
		assertTrue(set.size()==1 && set.contains("a"));
		set = abos.getAbonnements("c");
		assertTrue(set.size()==2 && set.contains("a") && set.contains("b"));
		set = abos.getAllSuiveurs();		
		assertTrue(set.size()==2 && set.contains("b") && set.contains("c"));
		
		assertTrue(abos.remAbonnement("b", "c"));

		set = abos.getAbonnes("a");
		assertTrue(set.size()==2 && set.contains("b") && set.contains("c"));
		set = abos.getAbonnes("b");
		assertTrue(set.isEmpty());
		assertTrue(abos.getAbonnes("c").isEmpty());
		set = abos.getAllSuivis();
		assertTrue(set.size()==1 && set.contains("a") );
		
		assertTrue(abos.getAbonnements("a").isEmpty());
		set = abos.getAbonnements("b");
		assertTrue(set.size()==1 && set.contains("a"));
		set = abos.getAbonnements("c");
		assertTrue(set.size()==1 && set.contains("a"));
		set = abos.getAllSuiveurs();		
		assertTrue(set.size()==2 && set.contains("b") && set.contains("c"));				
	}

}