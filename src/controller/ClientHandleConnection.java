package controller;

import java.io.IOException;
import java.net.Socket;


import model.ClientCore;

public class ClientHandleConnection extends Thread implements Runnable, ClientProtocol  {
	
	
	Socket sock = null;
	ClientOutput cOut;
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
			cOut = new ClientOutput(s1.getOutputStream());
			sendName(c.getName());
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

	@Override
	public void sendName(String name) {
		cOut.sendName(name);
	}

}
