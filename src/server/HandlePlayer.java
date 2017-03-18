package server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

public class HandlePlayer implements Runnable, ServerProtocol {
	private final Socket playerSocket;
	private ServerOutput pOut;
	private ServerInput pIn;
	private String name = "";
	private Snake snake;
	private int score ;
	private IPlayerLogger logger = null;
	private boolean stop = false, play=true;

	private enum PlayerState{ ST_INIT, ST_LOGGED }
	private PlayerState state = PlayerState.ST_INIT;

	public HandlePlayer (Socket playerSocket, IPlayerLogger logger) throws IOException{
		this.playerSocket = playerSocket;
		this.logger = logger;
		this.score = 0;
	}
	
	public int getScore(){
		return this.score;
	}
	public String getName(){
		return this.name;
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
			SnakeModel.removePlayer(name); 
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
				this.name=name;
				SnakeModel.addPlayer(newName, this);
				logger.playerJoinsGame(newName);
			}
		}

	}



	@Override
	public void moveSnake() {
		if(state != PlayerState.ST_LOGGED) return;
		switch( SnakeModel.checkCollision(snake)){
			case 1: // collision
				snake.die();
				pOut.snakeDie();
				finish();
				break;
			case 0: // move regulier
				snake.move();
				SnakeModel.notifyNewMoveHead(snake.getHead(),name,0);
				SnakeModel.notifyNewMoveTail(snake.getTail(),name);
				snake.removeTail();
				break;
			case -1 :// mange pomme donc grow
				snake.move();
				score ++;
				SnakeModel.notifyNewMoveHead(snake.getHead(), name,1);
				SnakeModel.removeApple(snake.getHead());
				break;
				
			}
	}

	@Override
	public void sendMoveHead(Point head,String name, Integer score) {
		pOut.sendMoveHead(head, name, score);
	}
	
	@Override
	public void sendMoveTail(Point tail) {
		pOut.sendMoveTail(tail);
	}

	@Override
	public void nameOK() {
		pOut.nameOK();
	}

	@Override
	public void nameBad() {
		pOut.nameBad();

	}

	@Override
	public void aPList() {
		pOut.sendPList(SnakeModel.getPlayersName());

	}
	
	

	@Override
	public void pListChanged(){
		pOut.sendPList(SnakeModel.getPlayersName());
	}

	@Override
	public void newSnake(){
		snake = new Snake();

		SnakeModel.spawnSnake(snake.getBody());

		for (HandlePlayer handle : SnakeModel.getAllSnake()) {
			pOut.sendSnake(handle.name, handle.getBodySnake());
		}
		
		SnakeModel.notifyNewSnake(snake.getBody(),name);
		
		ArrayList<Point> appleListCopy = new ArrayList<>(SnakeModel.getAllApple());

		for ( Point apple : appleListCopy){
			pOut.newApple(apple);
		}
		state = PlayerState.ST_LOGGED;
	}

	@Override
	public void drawSnake(Collection<Point> body,String name) {
		if(this.name.equals(name)) return;
			pOut.createSnake(body,name);
	}


	@Override
	public void changeDir(String dir) {
		if(dir.equals("M")){
			SnakeModel.moveSingleSnake(name);
		}else{
			Direction d = null;
			switch (dir){
			case "Z":
				d=Direction.Up;
				break;
			case "S":
				d=Direction.Down;
				break;
			case "Q":
				d=Direction.Left;
				break;
			case "D":
				d=Direction.Right;
				break;
			default:
				logger.systemMessage("invalid Key :" +dir +" -> no action");
			}
			snake.setDirection(d);
			logger.playerTurn(name, dir);
		}
	}

	public Collection<Point> getBodySnake(){
		return snake.getBody();
	}

	@Override
	public void playerQuit(HandlePlayer handlePlayer) {
		pOut.cleanSnake(handlePlayer.getBodySnake());
	}

	@Override
	public void newApple(Point p) {
		pOut.newApple(p);
	}

	@Override
	public void sendScore(String name, int point) {
		pOut.sendScore(name, point);
	}


	




}
