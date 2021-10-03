package pobj.watch;

public class CsTimeCounter {
	
	private int cs = 0;
	private int s = 0;
	private int m = 0;
	private int h = 0;
	
	public void setHour(int h) {
		this.h = (h % 24);
	}
	
	public void setMinute(int m) {
		this.m = (m % 60);
	}
	
	public void reset() {
		cs = 0;
		s = 0;
		m = 0;
		h = 0;
	}
	
	public void incr() {
		cs++;
		if (cs == 100) {
			cs = 0;
			s++;
			if (s == 60) {
				s = 0;
				m++;
				if (m == 60) {
					m = 0;
					h = (h + 1) % 24;
				}
			}
		}
	}
	
	public boolean isReset() {
		return h == 0 && m == 0 && s == 0 && cs == 0;
	}
	
	public int getHour() {
		return h;
	}
	
	public int getMinute() {
		return m;
	}
	
	public int getSecond() {
		return s;
	}
	
	public int getCentiSecond() {
		return cs;
	}
}
