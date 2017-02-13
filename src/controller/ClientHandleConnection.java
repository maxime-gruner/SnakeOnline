package controller;

import java.io.IOException;
import java.net.Socket;

import model.ClientCore;

public class ClientHandleConnection extends Thread {
	
	
	Socket sock = null;
	private ClientCore c;
	private boolean stop;
	
	public ClientHandleConnection(Socket s,ClientCore c) {
		this.sock= s;
		this.c = c;
		
		//output
	}
	
	@Override
	public void run() {
		try(Socket s1 = sock){
			//input
			
		} catch (IOException e) {
			e.printStackTrace();
			finish();
		}
		
	}
	
	private void finish(){
		if(!stop){
			stop = true;
			try {
				sock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
