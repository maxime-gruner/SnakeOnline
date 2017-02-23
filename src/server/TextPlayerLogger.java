package server;

public class TextPlayerLogger implements IPlayerLogger{

	@Override
	public void playerTurn(String name, String dir) {
		System.out.println(name + " turned " + dir);
	}

	@Override
	public void playerConnected(String ip) {
		System.out.println("Connecting to ..." + ip );
		
	}

	@Override
	public void playerDisconnected(String ip, String name) {
		System.out.println("Player" + name + " disconnected from" + ip );
		
	}

	@Override
	public void playerGotName(String ip, String name) {
		System.out.println("Player connected to " +ip +" new name is " + name  );
		
	}

	@Override
	public void playerJoinsGame(String name) {
		System.out.println("New snake launch by " + name);
		
	}

	@Override
	public void snakeDies(String name) {
		System.out.println(name + "'s snake dies");
		
	}

	@Override
	public void systemMessage(String msg) {
		System.out.println("incoming system message : " + msg);
		
	}

	@Override
	public Object getLogger(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
