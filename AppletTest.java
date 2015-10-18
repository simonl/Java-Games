
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.io.*;
import javax.imageio.*;

import javax.swing.JApplet;

public class AppletTest {

	Particle player = new Particle();
	Particle[] dynaBalls = { player };
	Particle[] balls = { new Particle(10), new Particle() , new Particle(),
						 new Particle(), new Particle(), new Particle(),
						 new Particle(), new Particle(), new Particle()};

	ParticleManager ballsM = new ParticleManager(balls);

	Vector position = player.motion.position;
	Vector velocity = player.motion.velocity;
	Vector dist = new Vector();
	Vector gravity = new Vector();
	double g = 10;

	Vector mouse = new Vector();

	BallPanel pane = new BallPanel();
	BallFrame frame = new BallFrame();
	long time;

	boolean notDragged = true;

	public static void main(String[] args) {
		new AppletTest().go();
	}

	public AppletTest() {

				gravity.setY(g);
				ballsM.setG(gravity);

				int xCenter = 400;
				int yCenter = 400;
				double angle = Math.PI/4;
				double dist = 250;

				balls[0].dimensions = new Spheric(400);
				balls[0].motion.position.setX(xCenter);
				balls[0].motion.position.setY(yCenter);

				position.setX(xCenter);
				position.setY(yCenter);


				for(int i = 1; i < balls.length; i++) {
					int x = (int)(Math.cos(angle * i) * dist) + xCenter;
					int y = (int)(Math.sin(angle * i) * dist) + yCenter;

					balls[i].motion.position.setX(x);
					balls[i].motion.position.setY(y);

				}

	}

	public void go() {

		do{
			time++;

			if(/*notDragged*/true)
				ballsM.update(player/*dynaBalls*/);
			else
				ballsM.update();

			frame.paintImage();

		}while(true);
	}

	/*
	public void go2() {

		do{
			time++;

			for(Particle ball : balls) {
				ball.updateParticle(gravity, ball.getMass());
			}


			for(int i = 0; i < balls.length; i++) {
				for(int j = i+1; j < balls.length; j++) {
					dist.setToVector(balls[i].motion.position);
					dist.setToVectorSubstraction(balls[j].motion.position);

					if(balls[i].dimensions.checkIfIntersect(balls[j].dimensions, dist))
						balls[i].collision(balls[j]);
				}

			}

			frame.paintImage();

		}while(true);
	}
	*/

	private class BallPanel extends JPanel {

		public BallPanel() {

				this.addMouseMotionListener(new Mouser());
				this.addMouseListener(new Mouser());
		}

		public void paint(Graphics g) {
		    super.paint(g);
		    int r, x, y;


			//PLAYER
		    g.setColor(Color.RED);
		    r = (int)player.dimensions.getR();
		    x = (int)position.getX();
		    y = (int)position.getY();

		    g.fillOval(x - r, y - r, 2 * r, 2 * r);


			//BOX
		    g.setColor(Color.GREEN);
		    r = (int)balls[0].dimensions.getR();
		    x = (int)balls[0].motion.position.getX();
		    y = (int)balls[0].motion.position.getY();

		    g.drawOval(x - r, y - r, 2 * r, 2 * r);


			//OTHER BALLS
		    g.setColor(Color.BLUE);
		    for(int i = 1; i < balls.length; i++) {
		    	r = (int)balls[i].dimensions.getR();
		    	x = (int)balls[i].motion.position.getX();
		    	y = (int)balls[i].motion.position.getY();

		    	g.fillOval(x - r, y - r, 2 * r, 2 * r);
			}

			g.setColor(Color.BLACK);
		    g.drawString("Time : " + time, 10, 10);
		    g.drawString("Sp : " + position, 200, 10);
		    g.drawString("Sp : " + velocity, 200, 30);

		    //g.drawString("Sp : " + ball.motion.position, 200, 70);
		    //g.drawString("Sp : " + ball.motion.velocity, 200, 90);

		    g.drawString("Sp : " + dist, 200, 130);

		    if(!notDragged)
		    	drawArrow(g, position, mouse);
		}

		public void drawArrow(Graphics g, Vector p1, Vector p2) {

			int tipX = (int)p2.getX();
			int tipY = (int)p2.getY();
			int baseX = (int)p1.getX();
			int baseY = (int)p1.getY();

			double dx = tipX - baseX;
			double dy = tipY - baseY;
			double angle = Math.atan2(dy, dx);
			double turn = Math.PI*3/4;

			g.drawLine(baseX, baseY, tipX, tipY);


			int side = 20;
			double left = angle + turn;

			int leftX = (int)(Math.cos(left) * side) + tipX;
			int leftY = (int)(Math.sin(left) * side) + tipY;

			g.drawLine(tipX, tipY, leftX, leftY);

			double right = angle - turn;
			int rightX = (int)(Math.cos(right) * side) + tipX;
			int rightY = (int)(Math.sin(right) * side) + tipY;

			g.drawLine(tipX, tipY, rightX, rightY);

			g.drawString("V = " + mouse.substractVector(position).getMagnitude(),
						 tipX + 20, tipY + (tipY > 400 ? 20 : -10));
		}

	}

	private class BallFrame extends JFrame {

		public BallFrame() {
	    	this.setTitle("BALLS");
				this.setSize(1200, 700);
	    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    	this.setContentPane(pane);
	    	this.setVisible(true);
		}

		public void paintImage()
		{
				pane.repaint();
		}
	}

	private class Mouser implements MouseMotionListener, MouseListener {

		long lastMove = 0;

					public void mouseMoved(MouseEvent e) {
						//mouse.setX(e.getX());
						//mouse.setY(e.getY());
					}

					public void mouseExited(MouseEvent e) {

					}

					public void mouseEntered(MouseEvent e) {

					}

					public void mouseClicked(MouseEvent e) {

					}

					public void mousePressed(MouseEvent e) {
						notDragged = false;
					}

					public void mouseReleased(MouseEvent e) {
						notDragged = true;
						Vector newV = mouse.substractVector(position);

						velocity.setX(newV.getX());
						velocity.setY(newV.getY());
					}

					public void mouseDragged(MouseEvent e) {
						mouse.setX(e.getX());
						mouse.setY(e.getY());

						/*
						long t = time;
						double dt = CoordinateSystem.DELAY * (time - lastMove);
						lastMove = t;

						double dx = e.getX() - position.getX();
						double dy = e.getY() - position.getY();

						position.setX(e.getX());
						position.setY(e.getY());

						velocity.setX(dx/dt);
						velocity.setY(dy/dt);
						*/
			}

	}

}
