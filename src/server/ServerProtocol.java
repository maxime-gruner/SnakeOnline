package server;

import java.util.Collection;

public interface ServerProtocol {
	default public void onReceiveName(String name){}
	public void nameOK();
	public void nameBad();
	default public void sendPList(Collection<String> pList){}
	public void createSnake(Collection<Point> body);
}
