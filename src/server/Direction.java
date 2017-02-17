package server;

public enum Direction {
	Up(0,-1), Down(1,0), Left(0,-1),Right(1,0);
	
	public int x;
	public int y;
	
	Direction(int x,int y){
		this.x = x;
		this.y = y;
	}

}
