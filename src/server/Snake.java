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
		if(this.direction == Direction.Right && direction == Direction.Left){
			return;
		}if(this.direction == Direction.Left && direction == Direction.Right){
			return;
		}
		if(this.direction == Direction.Down && direction == Direction.Up){
			return;
		}if(this.direction == Direction.Up && direction == Direction.Down){
			return;
		}
		this.direction = direction;
		
	}
	
	public Collection<Point> getBody(){
		return body;
	}
	
	public Point getHead(){
		return this.body.get(body.size()-1);
	}
	
	public Point getTail(){
		return this.body.get(0);
	}
	
	public void move() {
		Point head = getHead();
		Point next = new Point(head.getAbs() + direction.x, head.getOrd() + direction.y);
		body.add(next);
		
	}
	
	public Point getAhead(){
		return new Point(getHead().getAbs()+direction.x,getHead().getOrd()+direction.y);
	}
	
	public void removeTail(){
		body.remove(0);
	}


	public void die() { //ne sers a rien ?
		alive = false;
	}
	

	
	
}
