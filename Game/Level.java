package Game;
import java.awt.Color;
import java.awt.Graphics;



public final class Level {

	private static final int BLOCK_SIZE = 50;
	private static final Block OUT_OF_BOUNDS = Block.SOLID;
	
	public final Block[][] blocks;
	public final Coord start;
	public final Coord end;
	
	public Level(final Block[][] blocks, final Coord start, final Coord end) {
		
		this.blocks = blocks;
		this.start = start;
		this.end = end;
		
		this.set(start, Block.CLEAR);
		this.set(end, Block.CLEAR);
	}
	
	public Block get(final Coord coord) {
		
		final int x = coord.x;
		final int y = coord.y;
		
		if(x >= 0 && x < blocks.length)
			if(y >= 0 && y < blocks[x].length)
				return blocks[x][y];
		
		return OUT_OF_BOUNDS;
	}
	
	private void set(final Coord coord, final Block block) {
		this.blocks[coord.x][coord.y] = block;
	}
	
	public Level change(final Coord coord, final Block block) {
		
		final Block[][] newBlocks = new Block[this.blocks.length][];
		
		for(int i = 0; i < coord.x; i++)
			newBlocks[i] = this.blocks[i];

		{
			final Block[] inner = this.blocks[coord.x];
			final Block[] newInner = new Block[inner.length];
			
			for(int j = 0; j < coord.y; j++)
				newInner[j] = inner[j];
			newInner[coord.y] = block;
			for(int j = coord.y + 1; j < inner.length; j++)
				newInner[j] = inner[j];
			
			newBlocks[coord.x] = newInner;
		}
		
		for(int i = coord.x + 1; i < this.blocks.length; i++)
			newBlocks[i] = this.blocks[i];
		
		return new Level(newBlocks, this.start, this.end);
	}

	public static final void draw(final Graphics g, final Level level) {
		for(int i = 0; i < level.blocks.length; ++i) {
			for(int j = 0; j < level.blocks[i].length; ++j) {
				
				switch(level.blocks[i][j]) {
					case SOLID : g.setColor(Color.BLACK); break;
					case MOVABLE : g.setColor(Color.LIGHT_GRAY); break;
					case CLEAR : g.setColor(Color.WHITE); break;
				}
				
				g.fillRect(i * BLOCK_SIZE, j * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
			}
		}
	}
}
