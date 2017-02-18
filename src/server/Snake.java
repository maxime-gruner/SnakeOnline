package server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Snake {

	private final int INITSIZE = 3;
	
	private List<Point> body = new ArrayList<>();
	private Direction direction;
	private boolean alive = true;
		
	
	public Snake(){
		direction = Direction.Right;
		Point start;
		start = Point.randomCoord();
		
		for (int i = 0; i < INITSIZE; i++) {
			Point p = new Point(start.getAbs()+i, start.getOrd());
			body.add(p);
		}
		
	}
	
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction){
		this.direction = direction;
		
	}
	
	public Collection<Point> getBody(){
		return body;
	}
	
	

	
	
}
