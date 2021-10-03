
public class Buffer_bis {
	
	private int taille_actuelle=0;
	private int[] tab;
	
	private int depot=0;
	private int retrait=0;
	
	public Buffer_bis(int taille) {
		tab=new int[taille];
		
	}
	
	public String toString() {
		String s=" ";
		for(int i=0; i<taille_actuelle; i++) {
			s=s+tab[i]+" ";
		}
		return s;
	}
	
	public synchronized void depot(int a) throws InterruptedException {
		while(taille_actuelle>=tab.length) {
			System.out.println("Dépot de "+a+" échoué, Buffer Plein!!");
			Thread.sleep(10);
		}
		
			tab[depot]=a;
			depot=(depot+1)%tab.length;
			taille_actuelle++;
			System.out.println("Dépot de "+a+" réussi");
			
		

	}
	
	// accesseur pour connaître le nombre d'éléments actuels
	public int getNbElem() {
		return taille_actuelle;
	}

	public synchronized int retrait() throws InterruptedException {
		while(taille_actuelle==0) {
			System.out.println("Retrait impossible! Buffer Vide");
			Thread.sleep(10);
		}
		int r=tab[retrait];
		retrait=(retrait+1)%tab.length;
		taille_actuelle--;
		return r;
		
	}
	/*
	 * On peut avoir un problème d'un producteur qui veut faire un dépot dans un Buffer plein, il garde le verrou avec le sleep
	 * donc un consommateur ne peut pas démarrer car il n'a pas le verrou, il faut alors faire wait() qui lache le verrou et notifyall() pour le reprendre
	 */
	/*
	 * Avantages et inconvénients de la solution avec le moniteur de Hoare
	 * - Simplicité
	 * Inconvénients:
	 * - limite le parralélisme car un seul accés à un instant donné alors qu'il y a plusieurs cases sur le tampon
	 * - un seul waitset pour des threads bloqués pour différentes raisons
	 */
}
