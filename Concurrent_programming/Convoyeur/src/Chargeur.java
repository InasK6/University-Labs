
public class Chargeur implements Runnable{
	private AleaStock marchandise;
	private Chariot chariot;
	public Chargeur(AleaStock m, Chariot c) {
		marchandise=m;
		chariot=c;
	}
	
	public String toString() {
		String s="Chargeur de "+chariot+" et de marchandise: "+marchandise;
		return s;
	}
	public void run() {
		try{
			while(!marchandise.estVide()) {
			//for(int i=0; i<10;i++) {
				AleaObject o=marchandise.removeObjectStock();
				if(o==null) {
					chariot.StockVide();
				}
				else {
					chariot.PlaceDispo(o);
						chariot.charger(o);
					
					
				}
				Thread.sleep(10);
			}
		}catch(InterruptedException e) {
				e.printStackTrace();
		}
	}
}
