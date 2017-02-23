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
	public void sendPList(Collection<String> pList){
		os.println("PLIST");
		pList.forEach(os::println);
		os.println(".");
	}


	@Override
	public void createSnake(Collection<Point> body){
		os.println("SNAKE NEW");
		for (Point point : body) {
			os.println(point.getAbs());
			os.println(point.getOrd());
		}
		
		os.println(".");
		
	}

	@Override
	public void sendSnake(String name,Collection<Point> body) {
		os.println("SNAKE INIT");
		os.println(name);
		for (Point point : body) {
			os.println(point.getAbs());
			os.println(point.getOrd());
		}
		
		os.println(".");
		
	}


	@Override
	public void moveDone(String name, Point head, Point tail) {
		os.println("MOVE DONE");
		os.println(name);
		os.println(head.getAbs());
		os.println(head.getOrd());
		os.println(tail.getAbs());
		os.println(tail.getOrd());
	}


	


	
	
}
