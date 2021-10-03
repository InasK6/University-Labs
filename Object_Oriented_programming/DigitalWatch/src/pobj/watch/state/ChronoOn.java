package pobj.watch.state;

import pobj.watch.CsTimeCounter;

public class ChronoOn extends AbstractState {

	private CsTimeCounter clock;
	public ChronoOn(WatchWithState w, CsTimeCounter cs) {
		super(w);
		// TODO Auto-generated constructor stub
		clock=cs;
		w.d1().set(cs.getHour(), cs.getMinute(), cs.getSecond());
		w.d2().set(0,0,cs.getCentiSecond());
	}

	@Override
	public void pushA() {
		// TODO Auto-generated method stub
		WatchWithState w=getWatch();
		w.setState(new ChronoOff(w,clock));
	}

	@Override
	public void pushB() {
		// TODO Auto-generated method stub
		WatchWithState w=getWatch();
		w.setState(new ChronoFreeze(w,clock));
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		WatchWithState w=getWatch();
		clock.incr();
		w.d1().set(clock.getHour(), clock.getMinute(), clock.getSecond());
		w.d2().set(0, 0, clock.getCentiSecond());
	}

}
