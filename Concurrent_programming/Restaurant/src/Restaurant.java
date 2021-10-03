
public class Restaurant {
	private Table[] tables;
	private final int nb_tables=10;
	private int tables_dispo=nb_tables;
	private int num_res;
	/*
	 * Variables pour se déplacer dans le tableau
	 */

	public Restaurant() {
		tables=new Table[nb_tables];
		for(int i=0; i<nb_tables; i++) {
			tables[i]=new Table();
		}
	}
	/*
	 * Sortie: - le numéro de la reservation
	 * 			- -1 si la reservation est impossible
	 */
	// gère la réservation de tout le groupe
	public synchronized int reserver(GroupeClients c) {
		int nb_Clients=c.getNbClients();
		int a=(int)nb_Clients/2;;
		if(nb_Clients%2==1) {
			a=a+1;
		}
		
		if(tables_dispo-a>=0) {
			tables_dispo-=a;
			num_res++;
			return num_res;
		}
		else {
			return -1;
		}
		/*if(tables_reservées==nb_tables) {
			return -1;
		}
		if(tables[id].getGr()==null) {
			// On affecte la table au groupe
			tables[id].setGr(c);
			tables[id].reservePlace();
			tables_reservées++;
		}
		else {
			if(tables[id].getGr()==c) {
				tables[id].reservePlace();
				id++;
			}
			else {
				
			}
		}
		num_res++;
		return num_res ;*/
	}
	
}
