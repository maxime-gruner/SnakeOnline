package server.model;

import java.util.ArrayList;

public class Apple {
	
	private ArrayList<Point> appleList;
	int nbApple;
	
	public Apple(){
		this.appleList=  new ArrayList<Point>();
		nbApple=0;
	}
	
	public synchronized void addApple (Point p){
		if(appleList.contains(p) || (nbApple>=200)) return;
		appleList.add(p);
		SnakeModel.notifyNewApple(p);
		nbApple++;
	}
	
	
	public synchronized void addSpeedApple (Point p){
		if(appleList.contains(p) || (nbApple>=200)) return;
		appleList.add(p);
		SnakeModel.notifyNewSpeedApple(p);
		nbApple++;
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
		nbApple--;
		
		return -1;
		
}
	
	public synchronized void removeIndexApple(int index){
		appleList.remove(index);
	}
	
	public synchronized ArrayList<Point> getList(){
		return appleList;
	}
}
