
public class ConvoyeurTest {
	public static void main(String[] args) {
		AleaStock stock=new AleaStock(15,2,5);
		Chariot chariot=new Chariot(10,5);
		Chargeur chargeur=new Chargeur(stock, chariot);
		Dechargeur d=new Dechargeur(chariot);
		
		Thread thc=new Thread(chargeur);
		Thread thd= new Thread(d);

		thd.start();
		thc.start();
		try {
			thc.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		thd.interrupt();
	}
}
