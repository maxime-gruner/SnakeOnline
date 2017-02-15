package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PlayerInput {
	HandlePlayer handler;
	InputStream in;
	
	public PlayerInput( InputStream in, HandlePlayer handler) throws IOException{
		this.in = in;
		this.handler = handler;
	}
	
	public void doRun() throws IOException{
		String name;
		try(BufferedReader is = new BufferedReader(new InputStreamReader(in))){
			while(true){
				String line = is.readLine();
				switch (line){
				case "NAME":
					name = is.readLine();
					handler.onReceiveName(name);
					break;
				default :
					System.out.println("invalid input");
				}
			}
		}catch (NullPointerException e){
			System.out.println("error input stream " + e.toString());
		}
	}
}
