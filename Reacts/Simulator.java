package Reacts;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import Functional.Action;
import Functional.Unit;
import Game.Coord;
import Game.Direction;

public class Simulator {
	
	private final Queue<Action<Unit>> events = new LinkedList<Action<Unit>>();
	private final Block[][] board;
	private final int size;
	
	public Simulator(final Block[][] board) {
		this.board = board;
		this.size = board.length;
	}
	
	public void hit(final Coord pos) {
		if(pos.x < 0 || pos.x >= size || pos.y < 0 || pos.y >= size) return;
		
		switch(board[pos.x][pos.y]) {
			case SOLID : become(pos, Block.EMPTY); break;
			case FIRE : break;
			case EMPTY : break;
			case BOMB : explode(pos); break;
		}
	}
	
	public void step() {
		final int numEvents = events.size();
		for(int i = 0; i < numEvents; i++)
			events.remove().invoke(Unit.UNIT);
	}
	
	private void explode(final Coord pos) {
		this.events.add(new Action<Unit>() {
			public void invoke(final Unit unit) {
				if(board[pos.x][pos.y] != Block.BOMB) return;
				
				board[pos.x][pos.y] = Block.FIRE;
				become(pos, Block.EMPTY);

				hit(pos.move(Direction.UP));
				hit(pos.move(Direction.DOWN));
				hit(pos.move(Direction.LEFT));
				hit(pos.move(Direction.RIGHT));
			}
		});
	}

	private void become(final Coord pos, final Block to) {
		final Block was = board[pos.x][pos.y];
		this.events.add(new Action<Unit>() {
			public void invoke(final Unit unit) {
				if(board[pos.x][pos.y] != was) return;
				
				board[pos.x][pos.y] = to;
			}
		});
	}
	
	public void drawWhole(final Graphics g) {		
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				drawBlock(g, new Coord(i, j), board[i][j]);
	}
	
	private void drawBlock(final Graphics g, final Coord pos, final Block block) {
		switch(block) {
			case BOMB : g.setColor(Color.BLACK); break;
			case FIRE : g.setColor(Color.RED); break;
			case EMPTY : g.setColor(Color.WHITE); break;
			case SOLID : g.setColor(Color.BLUE); break;
		}
		
		g.fillRect(pos.x * Block.size, pos.y * Block.size, Block.size, Block.size);
	}

}
