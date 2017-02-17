package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
						pList.add(x);
					}
					handler.sendPlist(pList);
					break;
				default:
					throw new MyProtocolException("Invalid input on client: " + line);
				}
			}
		}
		
	}
	

}
