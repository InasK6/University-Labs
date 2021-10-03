package pobj.watch.strategy;

import java.util.Calendar;
import java.util.TimeZone;

import pobj.watch.CsTimeCounter;
import pobj.watch.Display;
import pobj.watch.Watch;

public class TimeStrategy implements Strategy {
	private boolean date=false; 
	
	/**
	 * 
	 * @param d1 écran 1
	 * @param d2 écran 2
	 * @param cs compteur de temps
	 */
	public TimeStrategy(Watch w) {
		Display d1=w.d1();
		Display d2=w.d2();
		CsTimeCounter cs=w.clock();
		d1.set(cs.getHour(), cs.getMinute(), cs.getSecond());
		d2.turnOff();
	}
	
	public void tick(Watch w) {
		CsTimeCounter cs=w.clock();
		w.d1().set(cs.getHour(), cs.getMinute(), cs.getSecond());
	}
	
	@Override
	public void pushA(Watch w) {
		// TODO Auto-generated method stub
		Display d2=w.d2();
		if (date) {
			d2.turnOff();
		} else {
			Calendar c = Calendar.getInstance(TimeZone.getDefault());
			d2.set(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
		}
		date = !date;
		
	}

	@Override
	public void pushB(Watch w) {
		// TODO Auto-generated method stub
		
	}
	
}
