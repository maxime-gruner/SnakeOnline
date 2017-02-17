package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;

import server.Point;

public class ClientOutput  implements ClientProtocol{
	
	PrintWriter os;
	
	public ClientOutput(OutputStream out) throws IOException{
		this.os = new PrintWriter(out,true);
	}

	@Override
	public void sendName(String name) {
		os.println("NAME");
		os.println(name);
		
	}

	@Override
	public void askPList() {
		os.println("APLIST");
	}

	

	
	
	

}