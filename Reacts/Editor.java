package Reacts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Functional.Ref;
import Game.Coord;
import Utils.Refs;

public class Editor {
	
	private final Block[][] working;
	private final int size;
	
	private final Ref<Coord> cursor = Refs.box(Coord.origin);
	private final Ref<Block> select = Refs.box(Block.EMPTY);
	
	public final EditorView view;
	
	public Editor(final int size) {
		final Block[][] blocks = new Block[size][];
		for(int i = 0; i < size; i++) {
			blocks[i] = new Block[size];
			for(int j = 0; j < size; j++)
				blocks[i][j] = Block.EMPTY;
		}
		
		this.working = blocks;
		this.size = size;
		this.view = new EditorView();
	}
	
	public Editor(final Block[][] working) {
		this.working = working;
		this.size = working.length;
		this.view = new EditorView();
	}
	
	public void put() {
		final Coord pos = cursor.read();
		final Block block = select.read();
		
		this.working[pos.x][pos.y] = block;
	}
	
	public void select(final Block block) {
		view.updateBlock(block);
		
		this.select.write(block);
	}
	
	public void moveTo(final Coord pos) {
		if(pos.x < 0 || size <= pos.x || pos.y < 0 || size <= pos.y) return;
		
		view.updateCursor(pos);
		
		this.cursor.write(pos);
	}
	
	
	public class EditorView extends JPanel {

		private final BufferedImage repr;
		private final Graphics reprG;
				
		private EditorView() {
			this.repr = new BufferedImage(size * Block.size, size * Block.size, BufferedImage.TYPE_INT_RGB);
			this.reprG = this.repr.getGraphics();
			
			drawWhole(reprG);
		}
		
		private void updateCursor(final Coord pos) {
			final Coord oldP = cursor.read();
			
			if(pos.equals(oldP)) return;
			
			drawBlock(reprG, oldP, working[oldP.x][oldP.y]);
			drawBlock(reprG, pos, select.read());
		}
		
		private void updateBlock(final Block block) {
			final Block oldB = select.read();
			
			if(oldB == block) return;
			
			drawBlock(reprG, cursor.read(), block);
		}
		
		public void paint(final Graphics g) {
			g.drawImage(repr, 0, 0, null);
		}
		
		private void drawWhole(final Graphics g) {		
			for(int i = 0; i < size; i++)
				for(int j = 0; j < size; j++)
					drawBlock(g, new Coord(i, j), working[i][j]);
			
			drawBlock(g, cursor.read(), select.read());
		}
		
		private void drawBlock(final Graphics g, final Coord pos, final Block block) {
			switch(block) {
				case BOMB : g.setColor(Color.BLACK); break;
				case FIRE : g.setColor(Color.RED); break;
				case EMPTY : g.setColor(Color.WHITE); break;
				case SOLID : g.setColor(Color.BLUE); break;
			}
			
			g.fillRect(pos.x * Block.size, pos.y * Block.size, Block.size, Block.size);
			
			g.setColor(Color.GRAY);
			g.drawRect(pos.x * Block.size, pos.y * Block.size, Block.size, Block.size);
		}
	}


	public Block[][] instance() {
		final Block[][] instance = new Block[size][size];
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				instance[i][j] = working[i][j];
		return instance;
	}
}
