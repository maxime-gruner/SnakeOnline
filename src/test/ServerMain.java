package test;



import java.io.IOException;

import server.model.ServerCore;

public class ServerMain {

	public static void main(String[] args) {
		int port = 1234;
		try{
			new ServerCore(port);
		}catch (IOException e){
			System.out.println("error during initialisation" + e.toString());
		}
	}

}
