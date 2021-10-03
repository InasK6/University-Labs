import java.util.Random;

public class ReponseRequete {

	private int  numRequete,resultat;
	private Client c;
	private Random gen=new Random();
	
	public ReponseRequete(Client c, int numRequete) {
		this.c=c;
		this.numRequete=numRequete;
		this.resultat=gen.nextInt(100);
	}
	
	public String toString (){
		return "le client "+this.c+"a envoyé la requete numero : "+this.numRequete+"et a recu le resulat : "+this.resultat;
		
	}
}