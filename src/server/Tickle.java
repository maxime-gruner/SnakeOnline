package server;

public class Tickle extends Thread {

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
				SnakeModel.moveSnake();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
