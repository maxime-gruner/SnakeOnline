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
		try(BufferedReader is = new BufferedReader(new InputStreamReader(in))){
			while(true){
			}
		}catch (NullPointerException e){
			System.out.println("error input stream " + e.toString());
		}
	}
}
