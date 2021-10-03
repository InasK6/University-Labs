
public class Kong extends Babouin {
	private String nom;
	public Kong(int i, Corde3 c, Position p) {
		super(i,c,p);
		nom="Kong <3 ";
	}
	public String toString() {
		return nom+this.toString();
	}
	
	public void run() {
		try {
			super.corde.accederKong(position);
			System.out.println(this.toString()+ "a pris la corde ");
			Batifoler();
			System.out.println(this.toString()+" a fini de cueillir les fruits"); 
			traverser();
			System.out.println(this.toString()+ "est arrivé ");
			super.corde.lacherKong(position);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
