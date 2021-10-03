package pobj.asciiart;

public class AsciiCercle {
	private double ox,oy;
	private double rayon;
	public AsciiCercle(double ox,double oy,double rayon) {
		this.ox = ox;
		this.oy = oy;
		this.rayon = rayon;
		
	}
	public void dessine (int largeur) {
		for(int line=0; line <= largeur ; line ++) {
			for(int car=0; car <= largeur; car++){
				if(estDansCercle(line,car)) {
					System.out.print("o");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	private boolean estDansCercle(int line, int car) {
		return Math.sqrt((line-oy)*(line-oy)+(car-ox)*(car-ox))<=rayon;
	}
}
