package controller;

import java.util.ArrayList;
import java.util.Collection;

import server.Point;

public interface ClientProtocol {
	public void sendName (String name); // envoie le nom au server en se connectant
	default public void nameOK(){};
	default public void nameBad(){};
	default public void askPList(){};
	default public void sendPlist(Collection<String> pList){};
	default public void initSnake(Collection<Point> bodyP){}	 
	default public void sendDir(String dir){}
	default void drawMove(Point head, Point tail){}
	default public void eraseSnake(ArrayList<Point> bodyP){}
	default public void die(){}
}
