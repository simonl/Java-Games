package Game;


public final class Coord {
	
	public static final Coord origin = new Coord(0, 0);
	
	public final int x;
	public final int y;
	
	public Coord(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public Coord move(final Direction dir) {
		switch(dir) {
			case UP : return new Coord(x, y - 1); 
			case DOWN : return new Coord(x, y + 1);
			case LEFT : return new Coord(x - 1, y);
			case RIGHT : return new Coord(x + 1, y);
			default : throw new RuntimeException("Never happens");
		}
	}
	
	public boolean equals(final Coord other) {
		return this.x == other.x && this.y == other.y;
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
