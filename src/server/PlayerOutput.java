package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class PlayerOutput  {
	PrintWriter os;
	
	public PlayerOutput(OutputStream out) throws IOException{
		this.os = new PrintWriter(out, true);
	}
	
	
}
