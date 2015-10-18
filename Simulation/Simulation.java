package Simulation;

import Functional.Func;
import Functional.Iterator;
import Functional.Particle;
import Functional.Vector;
import Utils.Iterators;

public class Simulation {

	private static final double G = 6.67E-11;
	public static interface Interactions extends Func<Particle[], Vector[]> { }
	public static interface Interaction {
		public Vector interact(final Particle p1, final Particle p2);
	}
	
	// iterate (\particles -> zipWith applyForce particles (interactions particles)) initial
	
	public static Iterator<Particle[]> simulate(final Particle[] initial, 
												final Interaction inter) {
		
		final Interactions inters = interactions(inter);
		
		return Iterators.iterate(initial, new Func<Particle[], Particle[]>() {

			public Particle[] invoke(final Particle[] particles) {
				return applies(particles, inters.invoke(particles));
			} 
		});
	}
	
	
	private static Particle[] applies(final Particle[] particles, final Vector[] forces) {

		final Particle[] news = new Particle[particles.length];
		
		for(int i = 0; i < particles.length; i++)
			news[i] = Particle.applyForce(particles[i], forces[i]);
		
		return news;
	}

	public static final Interactions interactions(final Interaction inter) {
		
		return new Interactions() {
			
			public Vector[] invoke(final Particle[] particles) {
	
				final Vector[] news = new Vector[particles.length];
				for(int i = 0; i < news.length; ++i)
					news[i] = Vector.ZERO;
	
				for(int i = 0; i < particles.length; ++i) {
					for(int j = i + 1; j < particles.length; ++j) {
						final Vector force = inter.interact(particles[i], particles[j]);
	
						news[i] = Vector.add(news[i], force);
						news[j] = Vector.add(news[j], Vector.negate(force));
					}
				}
	
				return news;
			}
		};
	}
	
	public static final Interaction gravitational =  new Interaction() {
		
		public Vector interact(final Particle p1, final Particle p2) {
			
			final Vector separation = Vector.subtract(p2.position, p1.position);

			final double distance = Vector.magnitude(separation);
			final Vector direction = Vector.unit(separation);

			final double force = G * p1.mass * p2.mass / distance / distance;

			return Vector.scale(direction, force);
		}
	};
}
