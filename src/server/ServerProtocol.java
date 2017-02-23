package server;

import java.util.Collection;

public interface ServerProtocol {
	default public void onReceiveName(String name){}
	public void nameOK();
	public void nameBad();
	default public void sendPList(Collection<String> pList){}
	default public void createSnake(Collection<Point> body){ }
	default public void aPList(){}
	default public void pListChanged(){}
	default void drawSnake(Collection<Point> body){}
	default void newSnake(){}
	default void sendSnake(String name, Collection<Point> body){}
	default void sendMove(Point head, Point tail){}
	public default void changeDir(String dir){}
	public default void moveSnake(){}
}
