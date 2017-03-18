package client.model;

public class Player implements Comparable<Player> {
	Integer score = 0 ;
	String name ="";
	
	public Player(String name, Integer score) {
		this.name = name;
		this.score = score;
	}
	
	public void increaseScore(Integer inc){
		this.score +=inc;
	}
	
	
	public int getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Player o) {
		int compareScore = o.score;
		int tmp = compareScore - this.score;
		if(tmp == 0) return 1;
		return tmp;
	}
}
