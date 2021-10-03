package pobj.watch.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.TimeZone;

import pobj.watch.Watch;

public abstract class TestWatch {
	
	Calendar c = Calendar.getInstance(TimeZone.getDefault());
	
	public static void pass(Watch w, int nbtick) {
		for (int i = 0; i < nbtick; i++) {
			w.tick();
		}
	}
	
	protected void testHour(Watch w) {
		pass(w, 350);
		assertEquals(0, w.d1().getL());
		assertEquals(0, w.d1().getM());
		assertEquals(3, w.d1().getR());
		w.pushA();
		assertTrue(w.d2().isOn());
		assertEquals(c.get(Calendar.DAY_OF_MONTH), w.d2().getL());
		assertEquals(c.get(Calendar.MONTH) + 1, w.d2().getM());
		assertEquals(c.get(Calendar.YEAR), w.d2().getR());
		w.pushA();
		assertFalse(w.d2().isOn());
	}
	
}
