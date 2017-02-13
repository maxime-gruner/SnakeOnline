package server;

import java.io.IOException;
import java.net.Socket;

public class HandlePlayer implements Runnable {
	private final Socket playerSocket;
	private PlayerOutput pOut;
	private PlayerInput pIn;
	private String playerName = "";
	private IPlayerLogger logger = null;
	private boolean stop = false;
	
	private enum PlayerState{ ST_INIT, ST_LOGGED }
	private PlayerState state = PlayerState.ST_INIT;
	
	public HandlePlayer (Socket playerSocket, IPlayerLogger logger) throws IOException{
		this.playerSocket = playerSocket;
		this.logger = logger;
	}
	
	@Override
	public void run() {
		try(Socket s1 = playerSocket){
			pOut = new PlayerOutput(s1.getOutputStream());
			pIn  = new PlayerInput(s1.getInputStream(), this);
			pIn.doRun();
		}catch (IOException e){
			if(!stop){
				logger.systemMessage("deco d'un joueur");
				finish();
			}
		}
		
	}
	
	public synchronized void finish(){
		if(!stop){
			stop=true;
			try{
				playerSocket.close();
			} catch(IOException e){ e.printStackTrace();}
			// retirer cleint de la liste quand elle sera faite,
			logger.playerDisconnected(playerSocket.toString(), playerName);
		}
	}
	
}
