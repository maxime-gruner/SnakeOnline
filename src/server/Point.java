package server;

import java.util.Random;

public class Point {
	@Override
	public String toString() {
		return "Point [abs=" + abs + ", ord=" + ord + "]";
	}

	private static Random rnd = new Random();
	
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
	
	public static Point randomCoord(){
		int x = rnd.nextInt(SnakeModel.WIDTH-10);
		int y = rnd.nextInt(SnakeModel.HEIGHT-10);
		return new Point(x, y);
	}
}
