package pobj.watch;

public abstract class AbstractWatch implements Watch {
	private Display d1;
	private Display d2;
	private CsTimeCounter cs;
	
	/**
	 * Constructeur
	 */
	public AbstractWatch() {
		d1=new Display();
		d2=new Display();
		cs=new CsTimeCounter();
	}
	
	/**
	 * appelé à chaque interruption horloge
	 */
	public void tick() {
		cs.incr();
		
	}
	
	/**
	 *  compteur de ticks de l'horloge de la montre
	 */
	public CsTimeCounter clock() {
		return cs;
	}
	
	/**
	 *  obtenir la référence sur l'écran D1
	 */
	public Display d1() {
		return d1;
	}
	
	/**
	 *  obtenir la référence sur l'écran D2
	 */
	public Display d2() {
		return d2;
	}

}
