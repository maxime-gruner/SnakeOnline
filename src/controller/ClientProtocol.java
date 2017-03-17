package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import server.Point;

public interface ClientProtocol {
	public void sendName (String name); // envoie le nom au server en se connectant
	default public void nameOK(){};
	default public void nameBad(){};
	default public void askPList(){};
	default public void sendPlist(Map<String, Integer> pList){};
	default public void initSnake(Collection<Point> bodyP,String name){}	 
	default public void sendDir(String dir){}
	default void drawMoveHead(Point head,String name){}
	default void drawMoveTail(Point tail){}
	default public void eraseSnake(ArrayList<Point> bodyP){}
	default public void die(){}
	default public void drawApple(Point a){}
	default public void reclass(String name, Integer score){}
}
