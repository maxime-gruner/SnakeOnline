package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;

public class ServerOutput implements ServerProtocol {
	PrintWriter os;
	
	public ServerOutput(OutputStream out) throws IOException{
		this.os = new PrintWriter(out, true);
	}

	
	@Override
	public void nameOK() {
		os.println("NAME OK");
		
	}

	@Override
	public void nameBad() {
		os.println("NAME BAD");
		
	}


	@Override
	public void sendPList(Collection<String> pList) {
		os.println("PLIST");
		pList.forEach(os::println);
		os.println(".");
		
	}
	
	
}
