package Functional;

import java.awt.Graphics;

public class RigidBody {

	public final Particle core;
	public final Shape shape;
	
	public RigidBody(final Particle core, final Shape shape) {
		this.core = core;
		this.shape = shape;
	}
	
	public static RigidBody applyForce(final RigidBody body, final Vector force) {
		return new RigidBody(Particle.applyForce(body.core, force), body.shape);
	}
	
	public static RigidBody collide(final RigidBody body, final Vector bounds) {
		final Maybe<Vector> mimpact = body.shape.collide(body.core.position, bounds);
		
		if(mimpact.isNull()) return body;
		final Vector impact = mimpact.get();
		
		final Vector vel = new Vector(
			(impact.x == 0 ? 1 : -1) * body.core.velocity.x,
			(impact.y == 0 ? 1 : -1) * body.core.velocity.y,
			(impact.z == 0 ? 1 : -1) * body.core.velocity.z
		);
		
		return new RigidBody(new Particle(body.core.position, vel, body.core.mass), body.shape);
	}
	
	public static void draw(final Graphics g, final RigidBody body) {
		body.shape.draw(g, body.core.position);
	}
}
