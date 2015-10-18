
import java.awt.event.*;
import java.awt.*;

public abstract class ParticleController implements MouseMotionListener,
													MouseListener,
												 	KeyListener {

	protected final Vector mouse = new Vector();
	protected Component container;

	protected final Vector position;
	protected final Vector velocity;
	protected final Vector acceleration;
	protected final Vector innerA;

	public ParticleController(CoordinateSystem motion) {
		this.position = motion.position;
		this.velocity = motion.velocity;
		this.acceleration = motion.acceleration;
		this.innerA = motion.innerA;
	}

	public void setContainer(Component c) {
		container = c;
	}

	public Vector getMouse() {
		return this.mouse;
	}

	public abstract boolean shouldCollide();
	public abstract boolean shouldUpdate();
	public abstract boolean hasDrawing();
	public abstract void draw(Graphics g);
	public abstract void stayCurrent();


	public void mouseMoved(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {

	}

	public void keyPressed(KeyEvent k) {

	}

	public void keyReleased(KeyEvent k) {

	}

	public void keyTyped(KeyEvent k) {

	}


}

