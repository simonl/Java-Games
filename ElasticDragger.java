
import java.awt.event.*;
import java.awt.*;

public class ElasticDragger extends PointerProjectile {


	public ElasticDragger(CoordinateSystem motion) {
		this(motion, 1);
	}

	public ElasticDragger(CoordinateSystem motion, double factor) {
		super(motion, factor);
	}

	public void stayCurrent() {
		super.stayCurrent();

		if(dragged) {
			innerA.setX(separation.getX() * factor);
			innerA.setY(separation.getY() * factor);
		}
	}

	public void mousePressed(MouseEvent e) {
		dragged = true;

		mouse.setX(e.getX());
		mouse.setY(e.getY());

		separation.setToVector(mouse.substractVector(position));

		innerA.setX(separation.getX() * factor);
		innerA.setY(separation.getY() * factor);
	}

	public void mouseReleased(MouseEvent e) {
		dragged = false;

		innerA.setX(0);
		innerA.setY(0);
	}

	public void mouseDragged(MouseEvent e) {
		mouse.setX(e.getX());
		mouse.setY(e.getY());

		separation.setToVector(mouse.substractVector(position));

		innerA.setX(separation.getX() * factor);
		innerA.setY(separation.getY() * factor);
	}

}
