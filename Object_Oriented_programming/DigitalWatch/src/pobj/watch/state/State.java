package pobj.watch.state;

import pobj.watch.Watch;

/*
 * En réalisant l'automate d'état de Moore, on se rend compte qu'on a six états
 */
public interface State {
	//WatchWithState w;
	public void pushA();
	public void pushB();
	public void tick();

}
