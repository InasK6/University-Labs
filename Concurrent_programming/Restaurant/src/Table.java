
public class Table {
	private int nb_places_reserve=0;
	private final int CPT=2;
	private GroupeClients g=null;
	
	public void setGr(GroupeClients g) {
		this.g=g;
	}
	public GroupeClients getGr() {
		return g;
	}
	
	public synchronized void reservePlace() {
		nb_places_reserve++;
	}
	
}
