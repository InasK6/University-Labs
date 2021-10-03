
public class SalleDeSpectacleTest {
	public static void main(String[] args) {
		Salle s= new Salle(7,4);
		int nb_groupes=5;
		Groupe[] groupes=new Groupe[nb_groupes];
		for(int a=0; a<nb_groupes;a++) {
			groupes[a]=new Groupe(3+a, s);
		}
		Thread[] thp=new Thread[nb_groupes];
		for(int a=0; a<nb_groupes; a++) {
			thp[a]=new Thread(groupes[a]);
			thp[a].start();
		}
		for(int a=0; a<nb_groupes; a++) {
			try {
				thp[a].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		s.annulerRes(groupes[0], 3);
	}
}
