package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import server.Point;

public class ClientInput {
	
	private ClientProtocol handler;
	private InputStream in;
	private boolean stop = false;
	
	public ClientInput(InputStream in, ClientProtocol handler) {
		this.in = in;
		this.handler = handler;
	}
	
	public void doRun() throws IOException{
		ArrayList<String> pList;
		ArrayList<Point> bodyP;
		
		try(BufferedReader is = new BufferedReader(new InputStreamReader(in))){
			while(!stop){
				String line = is.readLine();
				System.out.println("client received: " + line);
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
						pList.add(x);
					}
					handler.sendPlist(pList);
					break;
				case "SNAKE INIT":
					bodyP = new ArrayList<>();
					String abs;
					String ord;
					while(!(abs = is.readLine()).equals(".")){
						ord = is.readLine();
						
						Point p = new Point(Integer.valueOf(abs), Integer.valueOf(ord));
						bodyP.add(p);
						
					}
					
					handler.initSnake(bodyP);
					break;
				default:
					throw new MyProtocolException("Invalid input on client: " + line);
				}
			}
		}
		
	}
	

}
