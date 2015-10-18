

import java.awt.*;
import java.awt.event.*;

public class ControlledParticle extends Particle {

	private ParticleController controller;
	private boolean inputEnabled = true;

	public void addController(ParticleController pC) {
		this.controller = pC;
	}

	public void setComponent(Component component) {
		component.addMouseListener(controller);
		component.addMouseMotionListener(controller);
		component.addKeyListener(controller);

		controller.setContainer(component);
	}

	public void updateParticle(Vector force, double mass) {
		if(shouldUpdate())
			super.updateParticle(force, mass);

		controller.stayCurrent();
	}

	public void collision(Particle other, Vector separation) {
		if(shouldCollide())
			super.collision(other, separation);
	}

	public ParticleController getController() {
		return this.controller;
	}

	public boolean shouldUpdate() {
		return controller.shouldUpdate();
	}

	public boolean shouldCollide() {
		return controller.shouldCollide();
	}

	public void draw(Graphics g) {
		super.draw(g);

		if(controller.hasDrawing())
			controller.draw(g);
	}
}