

import java.awt.event.*;
import java.awt.*;

public class ParticleDragger extends ParticleController {

			private boolean dragged = true;
			private boolean alwaysOn = false;

			private Timer timer = new Timer(CoordinateSystem.DELAY);
			private long last;

			public void setAlwaysActive(boolean b) {
				this.alwaysOn = b;
			}

			public boolean shouldCollide() {
				return alwaysOn || !dragged;
			}

			public boolean shouldUpdate() {
				return !dragged;
			}

			public boolean hasDrawing() {
				return false;
			}

			public void draw(Graphics g) {

			}

			public void stayCurrent() {
				if(dragged) {
					if(timer.getCount() - last > 500) {
						int dx = (int)(position.getX() - mouse.getX());
						int dy = (int)(position.getY() - mouse.getY());

						if(dx == 0 && dx == 0) {
							velocity.setX(0);
							velocity.setY(0);
						}
					}
				}

				timer.increment();

			}


						public ParticleDragger(CoordinateSystem motion) {
							super(motion);
						}

						public void mouseMoved(MouseEvent e) {
						}

						public void mouseExited(MouseEvent e) {

						}

						public void mouseEntered(MouseEvent e) {

						}

						public void mouseClicked(MouseEvent e) {

						}

						public void mousePressed(MouseEvent e) {
							dragged = true;

							mouse.setX(e.getX());
							mouse.setY(e.getY());

							last = timer.getCount();
							position.setX(e.getX());
							position.setY(e.getY());
							velocity.setX(0);
							velocity.setY(0);
						}

						public void mouseReleased(MouseEvent e) {
							dragged = false;
						}

						public void mouseDragged(MouseEvent e) {

							long t = timer.getCount();
							double x = e.getX();
							double y = e.getY();

							mouse.setX(x);
							mouse.setY(y);

							double dt = timer.getDelay() * (t - last);
							last = t;

							double dx = x - position.getX();
							double dy = y - position.getY();

							position.setX(x);
							position.setY(y);

							velocity.setX(dx/dt);
							velocity.setY(dy/dt);
						}
}