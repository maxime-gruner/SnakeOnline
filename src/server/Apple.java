package server;

import java.util.ArrayList;

public class Apple {
	
	private ArrayList<Point> appleList;
	
	public Apple(){
		this.appleList=  new ArrayList<Point>();
	}
	
	public synchronized void addApple (Point p){
		if(appleList.contains(p)) return;
		appleList.add(p);
		SnakeModel.notifyNewApple(p);
	}
	
	public synchronized int removeApple(Point p){
		int toDelete = 0;
		for (Point point : appleList) {
			if(point.getAbs() == p.getAbs() && point.getOrd() == p.getOrd()){
				return toDelete;
			}else{
				toDelete++;
				
			}
			
			
		}
		return -1;
		
}
	
	public synchronized void removeIndexApple(int index){
		appleList.remove(index);
	}
	
	public synchronized ArrayList<Point> getList(){
		return appleList;
	}
}
