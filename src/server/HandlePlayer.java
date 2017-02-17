package server;

import java.io.IOException;
import java.net.Socket;

public class HandlePlayer implements Runnable, ServerProtocol {
	private final Socket playerSocket;
	private ServerOutput pOut;
	private ServerInput pIn;
	private String name = "";
	private Snake snake;
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
			pOut = new ServerOutput(s1.getOutputStream());
			pIn  = new ServerInput(s1.getInputStream(), this);
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
			logger.playerDisconnected(playerSocket.toString(), name);
		}
	}

	@Override
	public void onReceiveName(String name) {
		String newName = name;
		if(SnakeModel.existUserName(name)){
			nameBad();
		}else{
			nameOK();
			if(state == PlayerState.ST_INIT){
				SnakeModel.addPlayer(newName, this);
				logger.playerJoinsGame(newName);
			}
		}
		
	}

	@Override
	public void nameOK() {
		pOut.nameOK();
		
	}

	@Override
	public void nameBad() {
		pOut.nameBad();
		
	}

	
	public void aPList() {
		pOut.sendPList(SnakeModel.getPlayersName());
		
	}
	public void pListChanged(){
		pOut.sendPList(SnakeModel.getPlayersName());
	}

	
}
