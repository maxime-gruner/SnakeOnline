package client.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import client.controller.ClientProtocol;
import client.model.Player;
import server.model.Point;

public class ClientInput {
	
	private ClientProtocol handler;
	private InputStream in;
	private boolean stop = false;
	
	public ClientInput(InputStream in, ClientProtocol handler) {
		this.in = in;
		this.handler = handler;
	}
	
	public void doRun() throws IOException{
		ArrayList<Player> pList;
		ArrayList<Point> bodyP;
		String name;
		String abs;
		String ord;
		Integer score;
		
		try(BufferedReader is = new BufferedReader(new InputStreamReader(in))){
			while(!stop){
				String line = is.readLine();
				switch(line){
				case "NAME OK":
					handler.nameOK();
					break;
				case "NAME BAD":
					handler.nameBad();
					break;
				case "PLIST":
					pList = new ArrayList<>();
					String x;
					while(!(x = is.readLine()).equals(".")){
						score = Integer.valueOf(is.readLine());
						pList.add(new Player(x,score));
					}
					handler.sendPlist(pList);
					break;
				case "SNAKE NEW":
					bodyP = new ArrayList<>();
					name = is.readLine();
					
					while(!(abs = is.readLine()).equals(".")){
						ord = is.readLine();
						
						Point p = new Point(Integer.valueOf(abs), Integer.valueOf(ord));
						bodyP.add(p);
						
					}
					
					handler.initSnake(bodyP,name);
					break;
				case "SNAKE INIT":
					bodyP = new ArrayList<>();
					name = is.readLine();
					
					while(!(abs = is.readLine()).equals(".")){
						ord = is.readLine();
						
						Point p = new Point(Integer.valueOf(abs), Integer.valueOf(ord));
						bodyP.add(p);
					}
					
					handler.initSnake(bodyP,name);
					break;
				case "MOVE HEAD":
					name = is.readLine();
					abs=is.readLine();
					ord=is.readLine();
					score = Integer.valueOf(is.readLine());
					handler.drawMoveHead(new Point(Integer.valueOf(abs), Integer.valueOf(ord)),name);
					if(score != 0)  handler.reclass(name,score); 
					break;
				case "MOVE TAIL":
					abs=is.readLine();
					ord=is.readLine();
					handler.drawMoveTail(new Point(Integer.valueOf(abs), Integer.valueOf(ord)));
					break;
				case "CLEAN":
					bodyP = new ArrayList<>();
					while(!(abs = is.readLine()).equals(".")){
						ord = is.readLine();
						
						Point p = new Point(Integer.valueOf(abs), Integer.valueOf(ord));
						bodyP.add(p);
					}
					handler.eraseSnake(bodyP);
					break;
				case "SCORE":
					name = is.readLine();
					ord=is.readLine();
					handler.reclass(name, Integer.valueOf(ord));
					break;
				case "DIE":
					handler.die();
					stop = true;
					break;
				case "NEW APPLE":
					abs=is.readLine();
					ord=is.readLine();
					handler.drawApple(new Point(Integer.valueOf(abs), Integer.valueOf(ord)));
					break;
				case "SPEED APPLE":
					abs=is.readLine();
					ord=is.readLine();
					handler.drawSpeedApple(new Point(Integer.valueOf(abs), Integer.valueOf(ord)));
					break;
				default:
					throw new MyProtocolException("Invalid input on client: " + line);
				}
			}
		}
		
	}
	
	public void setStop(){
		stop = true;
	}
	

}
