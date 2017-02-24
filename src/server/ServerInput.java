package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import controller.MyProtocolException;

public class ServerInput {
	HandlePlayer handler;
	InputStream in;
	
	public ServerInput( InputStream in, HandlePlayer handler) throws IOException{
		this.in = in;
		this.handler = handler;
	}
	
	public void doRun() throws IOException{
		String name, dir;
		try(BufferedReader is = new BufferedReader(new InputStreamReader(in))){
			while(true){
				String line = is.readLine();
				System.out.println("Serv received : " + line);
				switch (line){
				case "NAME":
					name = is.readLine();
					handler.onReceiveName(name);
					break;
				case "APLIST":
					handler.aPList();
					handler.newSnake();
					break;
				case "KEY":
					dir=is.readLine();
					handler.changeDir(dir);
					break;
				default :
					throw new MyProtocolException("Ivalid input on server : " + line);
				}
			}
		}
	}
}
