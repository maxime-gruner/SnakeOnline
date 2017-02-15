package server;

import java.util.TreeMap;

public class SnakeModel {
	private static final TreeMap<String, HandlePlayer> playerList = new TreeMap<>();
	
	public static synchronized boolean existUserName (String name){
		return playerList.containsKey(name);
	}
	
	public static synchronized void addPlayer (String name, HandlePlayer player){
		playerList.put(name, player);
	}
	
}
