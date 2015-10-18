package Simulation;

import Utils.Refs;
import Functional.Iterator;
import Functional.Readable;
import Functional.Particle;
import Functional.Ref;
import Functional.Vector;
import Functional.Writeable;
import Graph.SaneJFrame;

public final class SimulationApp {

	public static final void main(final String... args) {
		
		final Particle[] particles = new Particle[] {
			new Particle(new Vector(0, 200, 0),	new Vector(0.5, 0, 0),	1L),
			new Particle(new Vector(0, 150, 0),	new Vector(0.5, 0, 0),	1L),
			new Particle(new Vector(0, 100, 0),	new Vector(0.5, 0, 0.5),1L),
			new Particle(new Vector(0, 50, 0),	new Vector(0, 0, 1.5),	1L),
			new Particle(new Vector(0, 0, 0),	new Vector(0.1, 0, 0),	1000000000000L)
		};
		

		final Ref<Vector> camera = Refs.box(Vector.ZERO);
		
		final ParticleView view = new ParticleView((Readable<Vector>) camera, particles);
		final CameraMover mover = new CameraMover(camera);

		view.addMouseListener(mover);
		view.addMouseMotionListener(mover);
		
		final SaneJFrame display = new SaneJFrame(view);
		
		Iterator<Particle[]> iterator = Simulation.simulate(particles, Simulation.gravitational);
		
		while(true) {
			for(int count = 0; count < 1000; count++) {
				view.invoke(iterator.current());
				
				iterator = iterator.rest();
			}
		
			display.repaint();
		}		
	}
}
