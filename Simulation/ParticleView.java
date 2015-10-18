package Simulation;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Functional.Observer;
import Functional.Particle;
import Functional.Readable;
import Functional.Ref;
import Functional.Vector;
import Utils.Refs;

public class ParticleView  extends JPanel implements Observer<Particle[]> {

	private final Readable<Vector> rcamera;
	private final Ref<Particle[]> rparticles;
	private long count = 0;
	
	public ParticleView(final Readable<Vector> rcamera, final Particle[] initial) {
		this.rparticles = Refs.box(initial);
		this.rcamera = rcamera;
		this.setSize(500, 500);
	}
	
	@Override
	public void invoke(final Particle[] particles) {
		this.rparticles.write(particles);
		count++;
	}
	
	public void paint(final Graphics g) {
		final Particle[] particles = rparticles.read();
		final Vector camera = rcamera.read();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 500, 500);
		
		g.setColor(Color.BLUE);
		for(final Particle particle : particles)
			g.fillOval(
				(int)(particle.position.x - camera.x),
				(int)(particle.position.y - camera.y),
				(int)Particle.radius * 2,
				(int)Particle.radius * 2);
		
		g.setColor(Color.BLACK);
		g.drawString("" + count, 10, 25);
	}
}
