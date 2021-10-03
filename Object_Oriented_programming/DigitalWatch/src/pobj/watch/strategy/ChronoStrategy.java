package pobj.watch.strategy;

import pobj.watch.CsTimeCounter;
import pobj.watch.Display;
import pobj.watch.Watch;

public class ChronoStrategy implements Strategy {
	private boolean date=false; 
	private CsTimeCounter cs2;
	private boolean stop=true;	//au début le chrono n'a pas encore démarré
	private boolean freeze=false;
	public ChronoStrategy(Watch w) {
		Display d1=w.d1();
		Display d2=w.d2();
		
		cs2=new CsTimeCounter();
		d1.set(0, 0, 0);
		d2.set(0, 0, 0);
	}
	
	
	public void tick(Watch w) {
		Display d1=w.d1();
		Display d2=w.d2();
		//Si le chrono n'est pas arrêté, on incrémente le compteur 
		if(!stop) {
			cs2.incr();
		
		// si le chrono n'est pas figé, on met à jour l'affichage
		if(!freeze) {
			d1.set(cs2.getHour(), cs2.getMinute(), cs2.getSecond());
			d2.set(0, 0, cs2.getCentiSecond());
		}
		}
	}
	/**
	 * permet de stopper le chrono ou le redémarrer
	 */
	@Override
	public void pushA(Watch w) {
		// TODO Auto-generated method stub

		stop=!stop;
	}
	/**
	 * permet de reinitialiser à 0 le chronomètre quand il est à l'arrêt
	 * figer la valeur du chrono sur l'écran quand il est en marche sans arrêter le chrono
	 * puis reprendre son affichage réel
	 */
	@Override
	public void pushB(Watch w) {
		// TODO Auto-generated method stub
		Display d1=w.d1();
		Display d2=w.d2();
		if(stop) {
			cs2.reset();
			d1.set(0, 0, 0);
			d2.set(0, 0, 0);
		}
		else {
			freeze=!freeze;
			if(!freeze) {
			d1.set(cs2.getHour(), cs2.getMinute(), cs2.getSecond());
			d2.set(0, 0, cs2.getCentiSecond());
			}
		}
	}

}
