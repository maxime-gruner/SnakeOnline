package server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;

public class SnakeModel {
	static int WIDTH = 200;
	static int HEIGHT = 200;
	static int map[][]= new int[HEIGHT][WIDTH];
	
	
	
	private static final TreeMap<String, HandlePlayer> playerList = new TreeMap<>();
	private static TreeMap<String, HandlePlayer> playerListCopy = new TreeMap<>();
	private static Apple appleList;
	
	public  static void initMap(){ 
		for (int i = 0; i < HEIGHT; i++) {
			map[i][0] = 999;
			map[i][WIDTH-1] = 999;
		}
		for (int i = 0; i < map.length; i++) {
			map[0][i] = 999;
			map[HEIGHT-1][i] = 999;
		}
		appleList=new Apple();
	}
	
	
	public static synchronized boolean existUserName (String name){
		return playerList.containsKey(name);
	}
	
	public static synchronized void addPlayer (String name, HandlePlayer player){
		playerList.put(name, player);
		notifyChangePlayer();
	}
	
	public static synchronized Set<String> getPlayersName(){
		return playerList.keySet();
	}
	
	public static synchronized void notifyChangePlayer(){
		playerListCopy = new TreeMap<>(playerList);
		playerList.values().forEach(c -> c.pListChanged());
	}

	public static synchronized void notifyNewSnake(Collection<Point> body,String name) {
		playerList.values().forEach( c -> c.drawSnake(body,name));
	}
	
	public static synchronized Collection<HandlePlayer> getAllSnake(){
		return playerList.values();
	}
	
	public static synchronized void moveSnake(){
		
		playerListCopy.values().forEach(c -> c.moveSnake());
	}
	
	public static synchronized void notifyNewMoveHead(Point head,String name){
		playerList.values().forEach(c-> c.sendMoveHead(head, name));
		map[head.getOrd()][head.getAbs()] = 1;
		
	}

	public static synchronized void notifyNewMoveTail(Point tail,String name){
		playerList.values().forEach(c-> c.sendMoveTail(tail));
		map[tail.getOrd()][tail.getAbs()] = 0;
		
	}
	
	public static synchronized void moveSingleSnake(String name){
		playerList.get(name).moveSnake();
	}

	public static void removePlayer(String name) {
		notifyRemovePlayer(playerList.get(name));
		playerList.remove(name);
		notifyChangePlayer();
		playerListCopy = new TreeMap<>(playerList);
	}

	private static void notifyRemovePlayer(HandlePlayer handlePlayer) {
		playerList.values().forEach(c -> c.playerQuit(handlePlayer));
	}
	
	public static int checkCollision(Snake snake){
		Point toTest = snake.getAhead();
		System.out.println("test: "+ toTest);
		if(map[toTest.getOrd()][toTest.getAbs()] >= 1 ){
			System.out.println("COLLIDE");
			return 1;
		}
		if(map[toTest.getOrd()][toTest.getAbs()] == -1 ){
			System.out.println("MANGE POMME");
			System.out.println("COORDONNEES POMME : "+ toTest.getAbs() + toTest.getOrd());
			appleList.removeApple(toTest);
			return -1;
		}
		return 0;
		
	}

	public static void spawnSnake(Collection<Point> body) {
		for (Point point : body) {
			map[point.getOrd()][point.getAbs()] = 1;
			System.out.println("in " + point.getOrd() + "  " + point.getAbs());
		}
	}
	
	public static void addApple(){
		appleList.addApple(Point.randomCoord());
	}
	
	public static void notifyNewApple(Point a){
		playerList.values().forEach(c -> c.newApple(a));
		map[a.getOrd()][a.getAbs()] = -1;
	}
	
	public synchronized static ArrayList<Point> getAllApple(){
		return appleList.getList();
	}


	public static void removeApple(Point apple) {
		
		appleList.removeApple(apple);
		
	}
}
	
	
