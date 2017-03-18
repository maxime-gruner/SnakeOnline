package server.model;

public class Tickle extends Thread {
	private int pomme = 0;
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(200);
				SnakeModel.moveSnake();
				if(pomme >= 5){
					pomme = 0;
					SnakeModel.addApple();
				}
				pomme++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
