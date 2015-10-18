

import java.awt.event.*;
import java.awt.*;

public class PointerProjectile extends ParticleController {

		protected final Vector separation = new Vector();
		protected final double factor;

		protected boolean dragged = false;
		private boolean pull = true;
		private boolean relative = false;

		public void setPush() {
			this.pull = false;
		}

		public void setPull() {
			this.pull = true;
		}

		public void setRelativeAction() {
			this.relative = true;
		}

		public void setAbsoluteAction() {
			this.relative = false;
		}

		public boolean shouldUpdate() {
			return true;
		}

		public boolean shouldCollide() {
			return true;
		}

		public void stayCurrent() {
			separation.setToVector(mouse);
			separation.setToVectorSubstraction(position);
		}

		public void draw(Graphics g) {

			Vector p1 = pull ? position : mouse;
			Vector p2 = pull ? mouse : position;
			g.setColor(Color.BLACK);

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


			int mouseX = (int)mouse.getX();
			int mouseY = (int)mouse.getY();
			g.drawString("V = " + separation.getMagnitude() * factor,
						 mouseX + 20, mouseY + (mouseY > 400 ? 20 : -10));

		}

		public boolean hasDrawing() {
			return dragged;
		}

						public PointerProjectile(CoordinateSystem motion) {
							this(motion, 1);
						}

						public PointerProjectile(CoordinateSystem motion, double factor) {
							super(motion);

							this.factor = factor;
						}

						public void mouseMoved(MouseEvent e) {
							mouse.setX(e.getX());
							mouse.setY(e.getY());
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
							this.stayCurrent();
						}

					public void mouseReleased(MouseEvent e) {
							dragged = false;

						velocity.setX(separation.getX() * (pull ? factor : -factor) + (relative ? velocity.getX() : 0));
						velocity.setY(separation.getY() * (pull ? factor : -factor) + (relative ? velocity.getY() : 0));

					}

					public void mouseDragged(MouseEvent e) {
						mouse.setX(e.getX());
						mouse.setY(e.getY());

						this.stayCurrent();
					}


}
