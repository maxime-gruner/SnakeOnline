package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class PlayerOutput  implements PlayerProtocol{
	
	PrintWriter os;
	
	public PlayerOutput(OutputStream out) throws IOException{
		this.os = new PrintWriter(out,true);
	}

	@Override
	public void sendName(String name) {
		os.println("NAME");
		os.println(name);
		
	}

}
