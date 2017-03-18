package client.view;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import client.controller.ClientProtocol;

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

	@Override
	public void sendDir(String dir) {
		os.println("KEY");
		os.println(dir);
	}

	

	
	
	

}
