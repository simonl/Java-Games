package Game;


public final class Levels {
	private Levels() { }
	
	public static final Level[] levels = new Level[2];
	
	static {
		final int width = 16;
		final int height = 16;
		
		{
			final Block[][] blocks = new Block[width][height];
			
			for(int i = 0; i < width; i++)
				for(int j = 0; j < height; j++)
					blocks[i][j] = Block.CLEAR;
				
			blocks[3][3] = Block.MOVABLE;
			blocks[4][3] = Block.MOVABLE;
			blocks[4][4] = Block.MOVABLE;
			
			levels[0] = new Level(blocks, Coord.origin, new Coord(width - 1, height - 1));
		}
		
		{
			final Block[][] blocks = new Block[width][height];
			
			for(int i = 0; i < width; i++)
				for(int j = 0; j < height; j++)
					blocks[i][j] = Block.CLEAR;
			
			blocks[4][3] = Block.SOLID;
			
			levels[1] = new Level(blocks, Coord.origin, new Coord(width - 1, height - 1));
		}
	}
}
