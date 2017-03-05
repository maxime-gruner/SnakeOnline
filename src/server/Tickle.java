package server;

public class Tickle extends Thread {
	private boolean pommes = false;
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
				SnakeModel.moveSnake();
				if (pommes ){
					pommes = false;
				}else{
					pommes = true;
					SnakeModel.addApple();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
