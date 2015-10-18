package OtherGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Functional.Particle;
import Functional.Ref;
import Functional.RigidBody;
import Functional.Shape;
import Functional.Vector;
import Game.Direction;
import Utils.Refs;
import Utils.Shapes;

@SuppressWarnings("serial")
public class Game extends JFrame {

	private static final Vector BOUNDS = new Vector(400, 400, 400);
	
	private static final Shape BACKGROUND_SHAPE = Shapes.square(400);
	private static final Vector BACKGROUND_POSITION = new Vector(200, 200, 200);
	
	private static final double FRICTION = -0.1;
	private static final double GRAVITY = 9.8;
	private static final double THRUST = 10;
	
	private static final Vector gforce = new Vector(0, GRAVITY);
	
	private final Ref<Vector> acceleration = Refs.box(Vector.ZERO);
	
	private final Ref<RigidBody> player = Refs.box(
			new RigidBody(new Particle(new Vector(15, 15), 1), Shapes.circle(10)));
	
	public static void main(final String... args) {
		
		new Game().start();
		
	}
	
	public Game() {
		this.setVisible(true);
		this.setSize((int)BOUNDS.x + 50, (int)BOUNDS.y + 50);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setContentPane(new JPanel() {
			
			private final Shape puff = Shapes.circle(5);
			
			public void paint(Graphics g) {
				g.setColor(Color.WHITE);
				BACKGROUND_SHAPE.draw(g, BACKGROUND_POSITION);
				
				final Vector accel = acceleration.read();
				final RigidBody body = player.read();
				final Particle par = body.core;
				
				g.setColor(Color.BLUE);
				RigidBody.draw(g, body);
				
				g.setColor(Color.RED);
				final Vector blast = Vector.add(par.position, 
										Vector.scale(Vector.unit(accel), 
												-(body.shape.bounding() + puff.bounding())));
				if((int)Vector.magnitude(blast) != 0)
					puff.draw(g, blast);
				
				g.setColor(Color.BLACK);
				g.drawString("" + par.position, 100, 10);
				g.drawString("" + par.velocity, 100, 25);
				g.drawString("" + accel, 100, 40);
			}
		});
			
		this.addKeyListener(new JumpListener());
		this.addKeyListener(new DirectionListener());
	}
	
	public void start() {
		final int steps = (int)(1 / Particle.dt / 60);
		
		while(true) {
			
			repaint();
			
			for(int i = 0; i < steps; i++) {
				final Vector accel = acceleration.read();
				final RigidBody body = player.read();
				
				final RigidBody forced = RigidBody.applyForce(body, 
						sum(	gforce, 
								Vector.scale(body.core.velocity, FRICTION), 
								Vector.scale(accel, THRUST)));
				final RigidBody bounded = RigidBody.collide(forced, BOUNDS);
				
				player.write(bounded);
			}
		}
	}
	
	private static final Vector sum(final Vector... vs) {
		Vector accum = Vector.ZERO;
		for(final Vector v : vs)
			accum = Vector.add(accum, v);
		return accum;
	}

	private static final boolean onGround(final Vector pos, final Vector vel) {
		return pos.y > (BOUNDS.y - 10 - 1) && Math.abs(vel.y) < 1;
	}
	
	private static final Direction keyToDir(final int keyCode) {
		switch(keyCode) {
			case KeyEvent.VK_UP : return Direction.UP;
			case KeyEvent.VK_DOWN : return Direction.DOWN;
			case KeyEvent.VK_LEFT : return Direction.LEFT;
			case KeyEvent.VK_RIGHT : return Direction.RIGHT;
			default : return null;
		}
	}
	
	private static final Vector dirToVec(final Direction dir) {
		switch(dir) {
			case UP : return Vector.negate(Vector.Y);
			case DOWN : return Vector.Y;
			case LEFT : return Vector.negate(Vector.X);
			case RIGHT : return  Vector.X;
			default : throw new RuntimeException("Never happens");
		}
	}
	
	private final class JumpListener implements KeyListener {
		private int space = 0;
		
		@Override
		public void keyPressed(final KeyEvent ke) {
			if(ke.getKeyCode() != KeyEvent.VK_SPACE) return;
			
			final RigidBody body = player.read();
			
			if(space == 0 && onGround(body.core.position, body.core.velocity)) {
				space = 10;
				acceleration.write(Vector.add(acceleration.read(), new Vector(0, -space, 0)));
			} else if (space != 0){
				space -= 1;
				acceleration.write(Vector.add(acceleration.read(), new Vector(0, 1, 0)));
			}
		}

		@Override
		public void keyReleased(final KeyEvent ke) {
			if(ke.getKeyCode() != KeyEvent.VK_SPACE) return;

			acceleration.write(Vector.add(acceleration.read(), new Vector(0, space, 0)));
			space = 0;
		}
		
		@Override
		public void keyTyped(final KeyEvent ke) { }
	}
	
	private final class DirectionListener implements KeyListener {
		
		private final Map<Direction, Boolean> pressed = 
			new HashMap<Direction, Boolean>() {
			{
				put(Direction.UP, false);
				put(Direction.DOWN, false);
				put(Direction.LEFT, false);
				put(Direction.RIGHT, false);
			}
		};
		
		@Override
		public void keyPressed(final KeyEvent ke) {
			final Direction dir = keyToDir(ke.getKeyCode());
			if(dir == null || pressed.get(dir)) return;

			pressed.put(dir, true);
							
			acceleration.write(Vector.add(acceleration.read(), dirToVec(dir)));
		}

		@Override
		public void keyReleased(final KeyEvent ke) {
			final Direction dir = keyToDir(ke.getKeyCode());
			if(dir == null) return;
			
			pressed.put(dir, false);
			
			acceleration.write(Vector.add(acceleration.read(), Vector.negate(dirToVec(dir))));
		}

		@Override
		public void keyTyped(final KeyEvent ke) { } 
	}
}
