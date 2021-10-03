package state;

import pobj.watch.CsTimeCounter;

public class ChronoInit extends AbstractState{

	public ChronoInit(WatchWithState w) {
		super(w);
		// TODO Auto-generated constructor stub
		w.d1().set(0, 0, 0);
		w.d2().set(0, 0, 0);
	}

	@Override
	public void pushA() {
		// TODO Auto-generated method stub
		WatchWithState w=getWatch();
		w.setState(new ChronoOn(w, new CsTimeCounter()));
	}

	@Override
	public void pushB() {
		// TODO Auto-generated method stub
		WatchWithState w=getWatch();
		w.setState(new Heure(w));
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
