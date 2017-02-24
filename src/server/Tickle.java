package server;

public class Tickle extends Thread {

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
				SnakeModel.moveSnake();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
