package pobj.watch;

import java.util.Calendar;
import java.util.TimeZone;
/**
 * Contrôleur de montre qui initialise la montre, et met à jour l'écran D1 à chaque tick
 *p
 */
public class SimpleWatch extends AbstractWatch {
	
	private boolean date = false;
	
	public SimpleWatch() {
		d1().set(0, 0, 0);
	}
	
	public void tick() {
		super.tick();
		d1().set(clock().getHour(), clock().getMinute(), clock().getSecond());
	}
	/**
	 * permet d'afficher la date si elle n'est pas sur l'écran, sinon éteit l'écran
	 * Dans le deuxième écran
	 */
	public void pushA() {
		if (date) {
			d2().turnOff();
		} else {
			Calendar c = Calendar.getInstance(TimeZone.getDefault());
			d2().set(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
		}
		date = !date;
		
	}
	
	//ces méthodes ne font rien dans notre réalisation de montre simple
	public void pushB() {
	}

	@Override
	public void pushMode() {
		// TODO Auto-generated method stub
		
	}
	
}
