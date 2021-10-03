
//Test avec locks
/*public class Test {
	public static void main(String[] args) {
		 int Nb_BAB=14;
		 Corde2 c=new Corde2();
		 
		 for(int i=0; i<7 ;i++) {
			 Babouin b=new Babouin(i,c,Position.NORD);
			 new Thread(b).start();
		 }
		 for(int i=5;i<14; i++) {
			 Babouin b=new Babouin(i,c,Position.SUD);
			 new Thread(b).start();
		 }
	}
}*/
//Test avec Semaphores
public class Test {
	public static void main(String[] args) {
		 int Nb_BAB=14;
		 Corde3 c=new Corde3();
		 
		 for(int i=0; i<7 ;i++) {
			 Babouin b=new Babouin(i,c,Position.NORD);
			 new Thread(b).start();
		 }
		 for(int i=5;i<14; i++) {
			 Babouin b=new Babouin(i,c,Position.SUD);
			 new Thread(b).start();
		 }
	}
}