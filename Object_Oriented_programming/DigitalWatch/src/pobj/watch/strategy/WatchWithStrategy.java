package pobj.watch.strategy;

import pobj.watch.AbstractWatch;

public class WatchWithStrategy extends AbstractWatch{
	private Strategy s;
	/**
	 * Mode de base correspond à la montre qui affiche l'heure, mode=false
	 */
	private boolean mode=false;
	
	public WatchWithStrategy() {
		s=new TimeStrategy(this);
	}
	
	public void pushMode() {
		if(mode) {
			
			s=new TimeStrategy(this);
		}
		else {
			
			s=new ChronoStrategy(this);
		}
		mode=!mode;
		
	}
	public Strategy getStrategy() {
		return s;
	}
	

	@Override
	public void pushA() {
		s.pushA(this);
		
	}
	@Override
	public void pushB() {
		// TODO Auto-generated method stub
		s.pushB(this);
	}
	@Override
	public void tick() {
		super.tick();
		s.tick(this);
	}
}
