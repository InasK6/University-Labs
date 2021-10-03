
/*public class NoelTest {
	public static void main(String[] args) {
		int NB_RENNES_MAX=9;
		Santa s=new Santa(NB_RENNES_MAX);
		Thread ts=new Thread(s);
		ts.start();
		Renne[] r=new Renne[NB_RENNES_MAX];
		for(int i=0; i<NB_RENNES_MAX; i++) {
			r[i]=new Renne(i+1, s, NB_RENNES_MAX);
			new Thread(r[i]).start();
		}
	}
}*/

public class NoelTest {
	public static void main(String[] args) {
		int NB_RENNES_MAX=9;
		PereNoel s=new PereNoel(NB_RENNES_MAX);
		Thread ts=new Thread(s);
		ts.start();
		Renne[] r=new Renne[NB_RENNES_MAX];
		for(int i=0; i<NB_RENNES_MAX; i++) {
			r[i]=new Renne(i+1, s, NB_RENNES_MAX);
			new Thread(r[i]).start();
		}
	}
}
