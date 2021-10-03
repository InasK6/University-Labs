//Question 2
public class AleaStock {
	private int taille;
	private int taille_actuelle;
	private AleaObject[] stock;
	
	public AleaStock(int t, int min, int max){
		taille=t;
		taille_actuelle=0;
		stock=new AleaObject[t];
		for(int i=0; i<t; i++) {
			stock[i]=new AleaObject(min, max);
		}
		taille_actuelle=t;
	}
	
	// fonction pour manipuler le stock
	synchronized public void setObjectStock(AleaObject a) {
		if(taille_actuelle<taille) {
			stock[taille_actuelle]=a;
			taille_actuelle++;
			
		}
		else {
			System.out.println("Stock plein");
		}
	}
	synchronized public boolean estVide() {
		if(taille_actuelle==0) {
			return true;
		}
		else return false;
	}
	
	synchronized public AleaObject removeObjectStock(){
		if(this.estVide()) {
			System.out.println("Stock vide");
			return null;
			
		}
		
		AleaObject a=stock[taille_actuelle-1];
		taille_actuelle--;
		System.out.println("Retrait de l'objet --> "+a.toString()+" <-- du stock par le chargeur");
		return a; 

		
	}
	public String toString() {
		String s="Contenu du Stock!!\n";
		for ( int i=0; i<taille_actuelle; i++) {
			s+=stock[i].toString();
			s+="\n";
		}
		return s;
	}
}