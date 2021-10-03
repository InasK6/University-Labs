public class Requete {
	private Client c;
	private int id;
	private static Object o=new Object();
	private static int CPT=0;
	private int type; // type \in {1, 2}
	
	public Requete(Client cl, int type) {
		synchronized(o) {
			id=++CPT;
		}
		c=cl;
		this.type=type;
	}
	public String toString() {
		return "Requete "+id+" émise par "+c+ " de type"+ type;
	}
	public Client getC() {
		return c;
	}
	public int getId() {
		return id;
	}
	public int getType() {
		return type;
	}
}