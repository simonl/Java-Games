
import java.util.*;

public abstract class ParticleHandler {
	protected final ArrayList<Particle> particles = new ArrayList<Particle>();

	public ParticleHandler() {

	}

	public ParticleHandler(Particle p) {
		add(p);
	}

	public ParticleHandler(Particle[] ps) {
		add(ps);
	}

	public void add(Particle p) {
		particles.add(p);
	}

	public void add(Particle[] ps) {
		for(Particle p : ps)
			particles.add(p);
	}

	public ArrayList<Particle> getList() {
		return particles;
	}

	public Vector getMomentum() {
		Vector momentum = new Vector();

		for(Particle p : particles)
			momentum.setToVectorAddition(p.motion.velocity, p.getMass());

		return momentum;
	}

	public double getKineticEnergy() {
		double e = 0;
		for(Particle p : particles)
			e += p.getKineticEnergy();
		return e;
	}

	public Iterator<Particle> iterator() {
		return particles.iterator();
	}
}