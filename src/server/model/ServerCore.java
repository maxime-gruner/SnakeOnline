package server.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import server.controller.HandlePlayer;

public class ServerCore extends Thread {
	private int port;
	ServerSocket serverSocket;
	private boolean stop = false;
	private IPlayerLogger logger = null;
	
	public ServerCore (int port) throws IOException{
		this.port = port;
		logger = new TextPlayerLogger();
		logger.systemMessage ("Server online");		
		this.start();
	}
	
	public void run() {
		try(ServerSocket serverSocket = new ServerSocket(port)){
			serverSocket.setSoTimeout(1000);
			SnakeModel.initMap();
			while(!stop){
				try{
					Socket playerSocket = serverSocket.accept();
					logger.playerConnected(playerSocket.toString());
					new Thread(new HandlePlayer(playerSocket, logger)).start();
				}catch(SocketTimeoutException ex){
				}
			}
		} catch (IOException e) {
			
		}
		
	}
	
	public synchronized void finish(){
		this.stop=true;
	}
}
