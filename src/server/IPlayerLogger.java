package server;

public interface IPlayerLogger {
	public void playerConnected(String ip);
	public void playerDisconnected(String ip, String name);
	public void playerGotName(String ip, String name);
	public void playerJoinsGame(String name);
	public void snakeDies(String name);
	public void systemMessage(String msg);
	public Object getLogger(String name);
}
