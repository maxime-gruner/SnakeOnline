package server.model;

import java.util.ArrayList;

public class Apple {
	
	private ArrayList<Point> appleList;
	int nbApple, nbSpeedApple;
	
	public Apple(){
		this.appleList=  new ArrayList<Point>();
		nbApple=0;
		nbSpeedApple=0;
	}
	
	public synchronized void addApple (Point p){
		if(appleList.contains(p) || (nbApple>=200)) return;
		appleList.add(p);
		SnakeModel.notifyNewApple(p);
		nbApple++;
	}
	
	
	public synchronized void addSpeedApple (Point p){
		if(appleList.contains(p) || (nbSpeedApple>=4)) return;
		appleList.add(p);
		SnakeModel.notifyNewSpeedApple(p);
		nbSpeedApple++;
	}
	
	
	public synchronized int removeApple(Point p, int which){
		int toDelete = 0;
		for (Point point : appleList) {
			if(point.getAbs() == p.getAbs() && point.getOrd() == p.getOrd()){
				switch(which){
				case -1:
					System.out.println("nb  applle " + nbApple);
					nbApple --; break;
				case -2:
					System.out.println("nb Speed applle " + nbSpeedApple);
					nbSpeedApple --; break;
				}
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
