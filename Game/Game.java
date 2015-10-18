package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Game {

	private static final int BLOCK_SIZE = 50;
	
	public final Level level;
	public final Coord player;
	
	public Game(final Level level) {
		this(level, level.start);
	}
	
	private Game(final Level level, final Coord player) {
		this.level = level;
		this.player = player;
	}

	public final boolean hasWon() {
		return this.player.equals(this.level.end);
	}
	
	public final Game move(final Direction dir) {

		final Coord moved = player.move(dir);
		final Block at = level.get(moved);
		
		switch(at) {
			case SOLID : return this;
			case CLEAR : return new Game(this.level, moved);
			case MOVABLE :
				final Coord next = moved.move(dir);
				final Block atnext = level.get(next);
				
				switch(atnext) {
					case SOLID : return this;
					case MOVABLE : return this;
					case CLEAR :
						final Level newLevel = level.change(next, Block.MOVABLE)
						.change(moved, Block.CLEAR);
						return new Game(newLevel , moved);
				}
		}
		
		throw new RuntimeException("Never Happens");
	}
	
	public static final void draw(final Graphics g, final Game game) {
		Level.draw(g,game.level);
		
		final Coord player = game.player;
		g.setColor(Color.GREEN);
		g.fillOval(player.x * BLOCK_SIZE, player.y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
	}
}
