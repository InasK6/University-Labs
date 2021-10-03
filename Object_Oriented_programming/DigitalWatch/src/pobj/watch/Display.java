package pobj.watch;

public class Display {
	
	private int l;
	private int m;
	private int r;
	
	private boolean on = false;
	
	public boolean isOn() {
		return on;
	}
	
	public void turnOff() {
		this.on = false;
	}
	
	public void set(int l, int m, int r) {
		this.on = true;
		this.l = l;
		this.m = m;
		this.r = r;
	}
	
	public int getL() {
		return l;
	}
	
	public int getM() {
		return m;
	}
	
	public int getR() {
		return r;
	}
	
}
