package state;

import pobj.watch.AbstractWatch;

public class WatchWithState extends AbstractWatch{
	State state;
	
	public WatchWithState() {
		state=new Heure(this);
	}
	
	public void setState(State s) {
		state=s;
	}
	@Override
	public void pushA() {
		// TODO Auto-generated method stub
		state.pushA();
	}

	@Override
	public void pushB() {
		// TODO Auto-generated method stub
		state.pushB();
	}

	@Override
	public void pushMode() {
		// TODO Auto-generated method stub
		// ne fait rien car ce bouton ne fait rien dans la configuration state
		// on a décidé de le retirer de la montre
	}
	
	public void tick() {
		super.tick();
	}

}
