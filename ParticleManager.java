
import java.util.*;

public class ParticleManager implements Iterable<Particle>{

	//Particle List
	ArrayList<Particle> particles = new ArrayList<Particle>();

	Vector dist = new Vector();
	Vector gravity = new Vector();

	//Constructors
	public ParticleManager() {

	}

	public ParticleManager(Particle p) {
		this.add(p);
	}

	public ParticleManager(Particle[] ps) {
		this.add(ps);
	}

	//Adding
	public void add(Particle p) {
		this.particles.add(p);
	}

	public void add(Particle[] ps) {
		for(Particle p : ps)
			this.particles.add(p);
	}

	//Batch particle functions
	public void update() {
		move();
		collide();
	}

	public void update(Particle visitor) {
		update();

		doVisitor(visitor);
	}

	public void update(Particle[] visitors) {
		update();

		for(Particle visitor : visitors) {
			doVisitor(visitor);
		}

		mixVisitors(visitors);
	}

	private void move() {
		Vector force;
		for(Particle p : particles) {
			//force = particles.get(0).motion.position.substractVector(p.motion.position);
			//force = force.scalarProduct(1.0/force.getMagnitude());
			p.updateParticle(/*force*/ gravity, p.getMass());
		}
	}

	private void collide() {
		int i1 = 0;
		int i2 = 0;

		for(Particle p1 : particles) {
			for(Particle p2 : particles) {
				if(i2 > i1) {
					dist.setToVector(p2.motion.position);
					dist.setToVectorSubstraction(p1.motion.position);

					if(p1.dimensions.checkIfIntersect(p2.dimensions, dist))
						p1.collision(p2);
				}
				i2++;
			}
			i1++;
			i2 = 0;
		}
	}

	private void doVisitor(Particle visitor) {
		visitor.updateParticle(gravity, visitor.getMass());

		for(Particle p : particles) {
			dist.setToVector(p.motion.position);
			dist.setToVectorSubstraction(visitor.motion.position);

			if(visitor.dimensions.checkIfIntersect(p.dimensions, dist))
				visitor.collision(p);
		}
	}

	private void mixVisitors(Particle[] visitors) {
		int i1 = 0;
		int i2 = 0;

		for(Particle p1 : visitors) {
			for(Particle p2 : visitors) {
				if(i2 > i1) {
					dist.setToVector(p2.motion.position);
					dist.setToVectorSubstraction(p1.motion.position);

					if(p1.dimensions.checkIfIntersect(p2.dimensions, dist))
						p1.collision(p2);
				}
				i2++;
			}
			i1++;
			i2 = 0;
		}
	}

	//Useful
	public void setG(Vector gravity) {
		this.gravity.setToVector(gravity);
	}

	public Vector[] getPositionPointer() {
		Vector[] positions = new Vector[particles.size()];

		int i = 0;
		for(Particle p : particles) {
			positions[i] = p.motion.position;
			i++;
		}

		return positions;
	}

	public Iterator<Particle> iterator() {
		return particles.iterator();
	}
}