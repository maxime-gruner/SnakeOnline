package server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SnakeModel {
	public static int WIDTH = 200;
	public static int HEIGHT = 200;
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
	
	public static synchronized TreeMap<String, Integer> getPlayersName(){
		TreeMap<String, Integer> to_return = new TreeMap<String, Integer>();
		playerList.values().forEach(c-> to_return.put(c.getName(), c.getScore()));
		
		
		return to_return;
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

	public static synchronized void removePlayer(String name) {
		notifyRemovePlayer(playerList.get(name));
		playerList.remove(name);
		notifyChangePlayer();
		playerListCopy = new TreeMap<>(playerList);
	}

	private static synchronized void notifyRemovePlayer(HandlePlayer handlePlayer) {
		for (Point p : handlePlayer.getBodySnake()) {
			map[p.getOrd()][p.getAbs()] = 0;
		}
		playerList.values().forEach(c -> c.playerQuit(handlePlayer));
	}
	
	public static synchronized int checkCollision(Snake snake){
		Point toTest = snake.getAhead();
		if(map[toTest.getOrd()][toTest.getAbs()] >= 1 ){
			return 1;
		}
		if(map[toTest.getOrd()][toTest.getAbs()] == -1 ){
			appleList.removeApple(toTest);
			return -1;
		}
		return 0;
		
	}

	public static synchronized void spawnSnake(Collection<Point> body) {
		for (Point point : body) {
			map[point.getOrd()][point.getAbs()] = 1;
		}
	}
	
	public static synchronized void addApple(){
		appleList.addApple(Point.randomCoord());
	}
	
	public static synchronized void notifyNewApple(Point a){
		playerList.values().forEach(c -> c.newApple(a));
		map[a.getOrd()][a.getAbs()] = -1;
	}
	
	public static synchronized ArrayList<Point> getAllApple(){
		return appleList.getList();
	}


	public static synchronized void removeApple(Point apple) {
		
		int n = appleList.removeApple(apple);
		if(n != -1){
			appleList.removeIndexApple(n);
		}
		
	}
}
	
	
