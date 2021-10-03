package pobj.tme6;

public class Turtle implements ITurtle {
	private int x;
	private int y;
	//angle en degrès
	private int angle;
	private boolean baisse;
	
	public Turtle() {
		x=0;
		y=0;
		angle=0;
		baisse=true;
	}
	@Override
	public void move(int length) {
		int oldX=x;
		int oldY=y;
		x=x+(int)(length* Math.sin(angle*Math.PI/180.));
		y=y+(int)(length* Math.cos(angle*Math.PI/180.));
		if(baisse)
			draw(oldX, oldY, x, y);
		
	}

	@Override
	public void turn(int angle) {
		this.angle+=angle;
		
	}

	@Override
	public void up() {
		baisse=false;
		
	}

	@Override
	public void down() {
		baisse=true;
		
	}
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	// voir plus tard si on veut la rendre private
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	public void draw(int x1, int y1, int x2, int y2) {
		System.out.println(x1+" "+y1+" "+x2+" "+y2);
	}
	

}
