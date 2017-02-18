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
		notifyNewPlayer();
	}
	
	public static synchronized Set<String> getPlayersName(){
		return playerList.keySet();
	}
	
	public static synchronized void notifyNewPlayer(){
		playerList.values().forEach(c -> c.pListChanged());
	}

	public static synchronized void notifyNewSnake(Collection<Point> body) {
		playerList.values().forEach( c -> c.drawSnake(body));
	}
	
	public static synchronized Collection<HandlePlayer> getAllSnake(){
		return playerList.values();
	}
	

}
	
	
