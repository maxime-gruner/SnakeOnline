package server.model;

public class Tickle extends Thread {
	final private int base = 75;
	private int pomme = 0, speedPomme=0, duration=0;
	private int time = base;
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(time);
				SnakeModel.moveSnake();
				if(time != base ){
					if (duration == 100) {
						duration = 0;
						time = base;
					}
					duration ++;
				}
				if(pomme >= 5){
					pomme = 0;
					SnakeModel.addApple();
				}
				if(speedPomme >= 200){
					speedPomme = 0;
					SnakeModel.addSpeedApple();
				}
				
				pomme++;
				speedPomme++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setTime(int newTime){
		this.time = newTime;
	}
	
}
