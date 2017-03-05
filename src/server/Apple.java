package server;

import java.util.ArrayList;

public class Apple {
	
	private ArrayList<Point> appleList;
	
	public Apple(){
		this.appleList=  new ArrayList<Point>();
	}
	
	public void addApple (Point p){
		if(appleList.contains(p)) return;
		appleList.add(p);
		SnakeModel.notifyNewApple(p);
	}
	
	public int removeApple(Point p){
		int toDelete = 0;
		for (Point point : appleList) {
			if(point.getAbs() == p.getAbs() && point.getOrd() == p.getOrd()){
				System.out.println("trouver a l index " +toDelete + " total: " + appleList.size());
				return toDelete;
			}else{
				System.out.println("not in " + toDelete);
				toDelete++;
				
			}
			
			
		}
		return -1;
		
}
	
	public void removeIndexApple(int index){
		appleList.remove(index);
	}
	
	public ArrayList<Point> getList(){
		return appleList;
	}
}
