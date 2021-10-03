package pobj.watch.strategy;

import pobj.watch.Watch;

public interface Strategy {
	public void pushA(Watch w);
	public void pushB(Watch w);
	public void tick(Watch w);
}
