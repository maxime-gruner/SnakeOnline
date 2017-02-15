package controller;

import java.io.IOException;
import java.net.Socket;


import model.ClientCore;
import controller.PlayerOutput;

public class ClientHandleConnection extends Thread implements Runnable, ClientProtocol  {
	
	
	Socket sock = null;
	PlayerOutput pOut;
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
			pOut = new PlayerOutput(s1.getOutputStream());
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
		pOut.sendName(name);
	}

}
