import java.util.Random;

public class TestRestaurant {
	public static void main(String[] args) {
		int NB_Clients_Gr=3;
		Restaurant resto=new Restaurant();
		
		Random r=new Random();
		for(int i=0; i<NB_Clients_Gr; i++) {
			int k=6+r.nextInt(4);
			GroupeClients g=new GroupeClients(i+1,resto,k);
			new Thread(g).start();
		}
		
		// la création du groupe, implique la création des clients
		//Il sont executés dans des Threads indépendants, on suppose qu'ils sont lancé par la classe GroupeClients
		
	}
}
