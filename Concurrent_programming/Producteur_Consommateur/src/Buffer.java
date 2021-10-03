
public class Buffer {
	
	private int taille_actuelle=0;
	private int[] tab;
	
	
	public Buffer(int taille) {
		tab=new int[taille];
		
	}
	
	public String toString() {
		String s=" ";
		for(int i=0; i<taille_actuelle; i++) {
			s=s+tab[i]+" ";
		}
		return s;
	}
	
	public synchronized void depot(int a) {
		if(taille_actuelle<tab.length) {
			tab[taille_actuelle]=a;
			taille_actuelle++;
			System.out.println("Dépot de "+a+" réussi");
			
		}
		else {
			System.out.println("Dépot de "+a+" échoué, Buffer Plein!!");
		}
	}
	
	// accesseur pour connaître le nombre d'éléments actuels
	public int getNbElem() {
		return taille_actuelle;
	}

	public synchronized int retrait() {
		if(taille_actuelle==0) {
			System.out.println("Retrait impossible! Buffer Vide");
			return -1;
		}
		else {
			taille_actuelle--;
			return tab[taille_actuelle];
		}
	}
}
