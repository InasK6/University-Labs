
public class AleaObject {
	//Question 1
	private static int CPT=0; 
	private int id;
	private int poids; 
	private static final Object mutex=new Object();
	AleaObject(int min , int max ){
		synchronized (mutex) {
			id=++CPT;
		}
		
		poids=min+ (int)(Math.random()*(max-min));
	}
	public int getPoids() {
		return poids;
	}
	
	public String toString() {
		String s="";
		s+="Marchandise "+id+" de poids "+poids;
		return s;
		
	}
	
}