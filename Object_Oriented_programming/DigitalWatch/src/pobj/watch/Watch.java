package pobj.watch;

public interface Watch {
	public void tick();// appelé à chaque interruption horloge
	
	public void pushA();// ce qu'on doit faire en appuyant sur A
	
	public void pushB();// ce qu'on doit faire en appuyant sur B
	
	public CsTimeCounter clock();// compteur de ticks de l'horloge de la montre
	
	public Display d1();// obtenir la référence sur l'écran D1
	
	public Display d2();// obtenir la référence sur l'écran D2
	
	public void pushMode(); // permet de passer du mode heure au mode chrono, et vice versa
}
