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
	
	public void removeApple(Point p){
		if(appleList.contains(p)) appleList.remove(p);
		else System.out.println("pomme pas trouvée \n_n");
	}
	
	public ArrayList<Point> getList(){
		return appleList;
	}
}
