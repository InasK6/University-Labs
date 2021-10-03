package state;

import pobj.watch.CsTimeCounter;
import pobj.watch.Display;


public class Heure extends AbstractState {

	public Heure(WatchWithState w) {
		super(w);
		// TODO Auto-generated constructor stub
		Display d1=w.d1();
		Display d2=w.d2();
		CsTimeCounter cs=w.clock();
		d1.set(cs.getHour(), cs.getMinute(), cs.getSecond());
		d2.turnOff();
	}

	@Override
	public void pushA() {
		// TODO Auto-generated method stub
		WatchWithState w=getWatch();
		w.setState(new HDate(w));
	}

	@Override
	public void pushB() {
		// TODO Auto-generated method stub
		WatchWithState w=getWatch();
		w.setState(new ChronoInit(w));
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		WatchWithState w=getWatch();
		CsTimeCounter cs=w.clock();
		w.d1().set(cs.getHour(), cs.getMinute(), cs.getSecond());
	}

}
