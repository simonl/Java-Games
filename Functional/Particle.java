package Functional;


public final class Particle {
	
	public static final double dt = 0.0001;
	public static final double radius = 10;
	
	public final Vector position;
	public final Vector velocity;
	public final double mass;
	
	public Particle(final double mass) {
		this(Vector.ZERO, mass);
	}
	
	public Particle(final Vector position, final double mass) {
		this(position, Vector.ZERO, mass);
	}
	
	public Particle(final Vector position, final Vector velocity, final double mass) {
		this.position = position;
		this.velocity = velocity;
		this.mass = mass;
	}
	
	public static Particle applyForce(final Particle par, final Vector force) {
		final Vector acceleration = Vector.scale(force, 1/par.mass);
		
		final Vector newPos = Vector.add(par.position, Vector.scale(par.velocity, dt));
		final Vector newVel = Vector.add(par.velocity, Vector.scale(acceleration, dt));
		
		return new Particle(newPos, newVel, par.mass);
		
		/*
		 * 
		 *	k1=h*fx(x0,y0);
    		k2=h*fx(x0+h/2,y0+k1/2);
    		k3=h*fx(x0+h/2,y0+k2/2);
    		k4=h*fx(x0+h,y0+k3);
    		y1=y0+0.1667*(k1+(2*k2)+(2*k3)+k4);
    		x1=x0+h;
    		x0=x1;
    		y0=y1;
		 * 
		 * 
		 * 
		 */
	}
}
