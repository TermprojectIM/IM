package data;

public class Unranked {
	private int level = 0;
	
	public Unranked() {
		this.level = 1;
	}
	
	protected Unranked(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
