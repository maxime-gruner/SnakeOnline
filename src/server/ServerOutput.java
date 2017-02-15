package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ServerOutput  {
	PrintWriter os;
	
	public ServerOutput(OutputStream out) throws IOException{
		this.os = new PrintWriter(out, true);
	}
	
	
}
