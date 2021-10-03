package pobj.watch.state;

import java.util.Calendar;
import java.util.TimeZone;

public class HDate extends AbstractState{

	public HDate(WatchWithState w) {
		super(w);
		// TODO Auto-generated constructor stub
		Calendar c = Calendar.getInstance(TimeZone.getDefault());
		w.d2().set(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
	
	}

	@Override
	public void pushA() {
		// TODO Auto-generated method stub
		WatchWithState w=getWatch();
		w.setState(new Heure(w));
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
		
	}

}
