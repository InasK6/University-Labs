package pobj.watch.state;

import pobj.watch.CsTimeCounter;

public class ChronoFreeze extends AbstractState{

	private CsTimeCounter clock;
	public ChronoFreeze(WatchWithState w, CsTimeCounter cs) {
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
		w.setState(new ChronoOn(w,clock));
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		clock.incr();
	}

}
