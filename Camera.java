
import java.util.*;
import java.awt.*;

public class Camera {

	private Vector position;
	private Vector orientation = new Vector();

	public Camera(Vector position) {
		this.position = position;
	}

	public void drawView(Graphics g, ArrayList<Particle> particles) {
		for(Particle p : particles) {

			Vector sep = p.motion.position.substractVector(this.position);

			if(isInFront(sep)) {
				sep.normalizeTo(orientation);
				p.draw(g, sep);
			}
		}

	}

	public boolean isInFront(Vector sep) {
		return sep.dotProduct(this.orientation) > 0.0;
	}

	public void follow(Particle p) {
		this.position = p.motion.position;
		this.orientation = p.motion.velocity;
	}
}