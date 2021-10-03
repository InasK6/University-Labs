package pobj.watch.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pobj.watch.strategy.WatchWithStrategy;

public class TestWatchWithStrategy extends TestWatch {
	@Test
	public void test() {
		WatchWithStrategy w = new WatchWithStrategy();
		testHour(w);// t=250
		
		w.pushMode();		//on passe au mode chrono
		
		pass(w, 250);// t=600
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(0, w.d1().getR());
		
		w.pushMode();		// ON REPASSE AU MODE HEURE NORMALE
		pass(w, 20);// t=620
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(6, w.d1().getR());
		
		w.pushMode();		// On repasse en mode chrono
		w.pushA();			// on démarre le chrono
		pass(w, 365);// t=985
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(3, w.d1().getR());
		assertEquals(0, w.d2().getL());
		assertEquals(0, w.d2().getM());
		assertEquals(65, w.d2().getR());
		
		w.pushA();			// on arrête le chrono 
		pass(w, 20);// t=1005
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(3, w.d1().getR());
		assertEquals(0, w.d2().getL());
		assertEquals(0, w.d2().getM());
		assertEquals(65, w.d2().getR());
		
		w.pushA();			// On redémarre le chrono 
		pass(w, 40);// t=1045
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(4, w.d1().getR());
		assertEquals(0, w.d2().getL());
		assertEquals(0, w.d2().getM());
		assertEquals(5, w.d2().getR());
		w.pushB();			// on fige l'affichage du chrono 
		pass(w, 40);// t=1085
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(4, w.d1().getR());
		assertEquals(0, w.d2().getL());
		assertEquals(0, w.d2().getM());
		assertEquals(5, w.d2().getR());
		w.pushB();			// on défige l'affichage du chrono, il n'y a pas de tick après, donc pushB doit se charger de mettre à jour l'affichage aussi
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(4, w.d1().getR());
		assertEquals(0, w.d2().getL());
		assertEquals(0, w.d2().getM());
		assertEquals(45, w.d2().getR());
		w.pushA();			//On arrête le chrono
		w.pushB();			//on reinitialise le chrono
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(0, w.d1().getR());
		assertEquals(0, w.d2().getL());
		assertEquals(0, w.d2().getM());
		assertEquals(0, w.d2().getR());
		
		w.pushMode();		//On passe en mode heure
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(10, w.d1().getR());
		assertEquals(0, w.d2().getL());
		assertEquals(0, w.d2().getM());
		assertEquals(0, w.d2().getR());
	}
}
