package server;

public class Point {
	private int abs;
	private int ord;
	
	public Point (int x, int y){
		this.setAbs(x);
		this.setOrd(y);
	}

	public int getAbs() {
		return abs;
	}

	public void setAbs(int abs) {
		this.abs = abs;
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}
}
