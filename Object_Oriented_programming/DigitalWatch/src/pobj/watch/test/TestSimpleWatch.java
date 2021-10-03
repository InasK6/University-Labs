package pobj.watch.test;

import org.junit.Test;

import pobj.watch.SimpleWatch;

public class TestSimpleWatch extends TestWatch {
	@Test
	public void test() {
		SimpleWatch w = new SimpleWatch();
		w.pushB();
		testHour(w);
		w.pushB();
		
	}
}
