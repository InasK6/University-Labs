
public class Dechargeur implements Runnable{
	
	private Chariot chariot;
	
	public Dechargeur(Chariot c) {
		chariot=c;
	}
	
	public String toString() {
		String s="Dechargeur de"+chariot;
		return s;
	}
	public void run() {
		try {
			while(true) {
			//for(int i=0; i<10; i++) {
				chariot.decharger();
				Thread.sleep(10);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
