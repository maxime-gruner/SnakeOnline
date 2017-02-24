package server;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;

public class SnakeModel {
	static int WIDTH = 200;
	static int HEIGHT = 200;
	
	
	private static final TreeMap<String, HandlePlayer> playerList = new TreeMap<>();
	
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
		playerList.values().forEach(c -> c.pListChanged());
	}

	public static synchronized void notifyNewSnake(Collection<Point> body) {
		playerList.values().forEach( c -> c.drawSnake(body));
	}
	
	public static synchronized Collection<HandlePlayer> getAllSnake(){
		return playerList.values();
	}
	
	public static synchronized void moveSnake(){
		playerList.values().forEach(c -> c.moveSnake());
	}
	
	public static synchronized void notifyNewMove(Point head, Point tail){
		playerList.values().forEach(c-> c.sendMove(head, tail));
	}
	
	public static synchronized void moveSingleSnake(String name){
		playerList.get(name).moveSnake();
	}

	public static void removePlayer(String name) {
		notifyRemovePlayer(playerList.get(name));
		playerList.remove(name);
		notifyChangePlayer();
	}

	private static void notifyRemovePlayer(HandlePlayer handlePlayer) {
		playerList.values().forEach(c -> c.playerQuit(handlePlayer));
		
		
	}

}
	
	
