package Game;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Functional.Action;
import Functional.Observable;
import Functional.Observer;
import Functional.Ref;
import Functional.Unit;
import Utils.Input;
import Utils.Refs;


public class GameTesting {

	private static final int width = 8, height = 8;
	private static final int BLOCK_SIZE = 50;

	private static final JFrame window = new JFrame();
	private static final Observable<Direction> inputs = Input.movement(window);
	
	public static void main(String... args) {
		
		window.setVisible(true);
		window.setSize(BLOCK_SIZE * width, BLOCK_SIZE * height);
		
		for(final Level level : Levels.levels) {
			play(window, new Game(level));
		}
		
		window.dispose();
	}

	@SuppressWarnings({ "serial" })
	private static final void play(final JFrame window, final Game game) {

		final Ref<Boolean> loop = Refs.box(true);
		final Ref<Game> rgame = Refs.box(game);
		
		window.setContentPane(new JPanel() {
			
			public void paint(Graphics g) {
				Game.draw(g, rgame.read());
			}
		});
		
		final Action<Unit> dispose = inputs.invoke(new Observer<Direction>() {
			
			public void invoke(final Direction dir) {
				final Game played = rgame.read().move(dir);

				rgame.write(played);
				if(played.hasWon())
					loop.write(false);
				
				window.repaint();
			}
		});
		
		while(loop.read());
		
		dispose.invoke(Unit.UNIT);
	}
}
