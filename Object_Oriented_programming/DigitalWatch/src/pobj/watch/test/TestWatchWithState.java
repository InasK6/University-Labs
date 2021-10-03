package pobj.watch.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pobj.watch.state.WatchWithState;

public class TestWatchWithState extends TestWatch {
	@Test
	public void test() {
		WatchWithState w = new WatchWithState();
		testHour(w);
		w.pushB();// vers mode chrono
		
		pass(w, 250);// t=600
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(0, w.d1().getR());
		
		w.pushB();// vers mode heure
		pass(w, 20);// t=620
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(6, w.d1().getR());
		
		w.pushB();// vers mode chrono
		w.pushA();// demarre chrono
		pass(w, 365);// t=985
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(3, w.d1().getR());
		assertEquals(0, w.d2().getL());
		assertEquals(0, w.d2().getM());
		assertEquals(65, w.d2().getR());
		
		w.pushA();// stop chrono
		pass(w, 20);// t=1005
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(3, w.d1().getR());
		assertEquals(0, w.d2().getL());
		assertEquals(0, w.d2().getM());
		assertEquals(65, w.d2().getR());
		
		w.pushA();// demarre chrono
		pass(w, 40);// t=1045
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(4, w.d1().getR());
		assertEquals(0, w.d2().getL());
		assertEquals(0, w.d2().getM());
		assertEquals(5, w.d2().getR());
		
		w.pushB();// freeze chrono
		pass(w, 40);// t=1085
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(4, w.d1().getR());
		assertEquals(0, w.d2().getL());
		assertEquals(0, w.d2().getM());
		assertEquals(5, w.d2().getR());
		
		w.pushB();// reprendre chrono
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(4, w.d1().getR());
		assertEquals(0, w.d2().getL());
		assertEquals(0, w.d2().getM());
		assertEquals(45, w.d2().getR());
		
		w.pushA();// arreter chrono
		w.pushB();// reset chrono
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(0, w.d1().getR());
		assertEquals(0, w.d2().getL());
		assertEquals(0, w.d2().getM());
		assertEquals(0, w.d2().getR());
		
		w.pushB();// mode heure
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(10, w.d1().getR());
		assertEquals(0, w.d2().getL());
		assertEquals(0, w.d2().getM());
		assertEquals(0, w.d2().getR());
	}
}
