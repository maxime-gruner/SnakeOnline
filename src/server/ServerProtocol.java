package server;

import java.util.Collection;

public interface ServerProtocol {
	default public void onReceiveName(String name){}
	public void nameOK();
	public void nameBad();
	default public void sendPList(Collection<String> pList){}
	default public void createSnake(Collection<Point> body,String name){ }
	default public void aPList(){}
	default public void pListChanged(){}
	default void drawSnake(Collection<Point> body,String name){}
	default void newSnake(){}
	default void sendSnake(String name, Collection<Point> body){}
	default void sendMoveHead(Point head,String name){}
	default void sendMoveTail(Point tail){}
	public default void changeDir(String dir){}
	public default void moveSnake(){}
	default public void playerQuit(HandlePlayer handlePlayer){}
	default public void snakeDie(){}
	default public void cleanSnake(Collection<Point> bodySnake){}
	default public void newApple(Point p){}
	
}
