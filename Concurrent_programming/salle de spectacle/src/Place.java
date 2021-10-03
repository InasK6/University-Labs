
public class Place {
	private int rang;
	private int placeDansRang;
	
	public Place(int r, int p) {
		rang=r;
		placeDansRang=p;
	}
	
	public String toString() {
		String s="place "+placeDansRang+" au rang "+rang;
		return s;
	}
	
	public int getRang() {
		return rang;
	}
	
	public int getPlaceDansRang() {
		return placeDansRang;
	}
}
