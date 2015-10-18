package javaapps.physicsobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JApplet;
import javax.swing.JPanel;

public class AppletTest extends JApplet implements MouseMotionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	Particle player = new Particle();
	Particle ball = new Particle();

	Vector position = player.motion.position;
	Vector velocity = player.motion.velocity;

	JPanel pane = new BallPanel();

	long time;

	public void mouseMoved(MouseEvent e) {
		double dt = CoordinateSystem.DELAY;
		double dx = position.getX() - e.getX();
		double dy = position.getY() - e.getY();

		position.setX(e.getX());
		position.setY(e.getY());

		velocity.setX(dx/dt);
		velocity.setY(dy/dt);
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void init() {
		ball.motion.position = new Vector();
		ball.motion.position.setX(200);
		ball.motion.position.setY(200);
		this.addMouseMotionListener(this);
		this.add(pane);
		go();
	}

	public void go() {
		Vector dist = new Vector();

		while(true) {
			time++;
			player.updateParticle(new Vector());
			ball.updateParticle(new Vector());

			dist.setToVector(player.motion.position);
			dist.setToVectorSubstraction(ball.motion.position);

			if(player.dimensions.checkIfIntersect(ball.dimensions, dist));
				player.collision(ball);
			pane.repaint();
		}
	}


	private class BallPanel extends JPanel {

		public void paint(Graphics g) {
		    super.paint(g);
		    int r, x, y;

		    g.setColor(Color.RED);
		    r = (int)player.dimensions.getR();
		    x = (int)position.getX();
		    y = (int)position.getY();
		    g.fillOval(x - r, y - r, x + r, y + r);

		    g.setColor(Color.BLUE);
		    r = (int)player.dimensions.getR();
		    x = (int)ball.motion.position.getX();
		    y = (int)ball.motion.position.getY();
		    g.fillOval(x - r, y - r, x + r, y + r);
		}
	}

}
