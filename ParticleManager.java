
import java.util.*;
import java.awt.*;

public class ParticleManager extends ParticleHandler implements Iterable<Particle>{

	Timer timer = new Timer(CoordinateSystem.DELAY);

	//BSPTree world = new BSPTree();

	int FPS = 120;
	long time = System.nanoTime();
	long cycleLen = 1000000000 / FPS;

	Vector dist = new Vector();
	Vector force = new Vector();

	final boolean[] prim;
	final boolean[] sec;

	double friction = 0;
	Vector gravity = new Vector();

	//Constructors
	public ParticleManager() {
		this.prim = new boolean[0];
		this.sec = new boolean[0];
	}

	public ParticleManager(Particle p) {
		super(p);
		this.prim = new boolean[1];
		this.sec = new boolean[1];
	}

	public ParticleManager(Particle[] ps) {
		super(ps);
		this.prim = new boolean[ps.length];
		this.sec = new boolean[ps.length];
	}

	//Batch particle functions
	public void update() {
		timer.increment();

		//long befMove = System.nanoTime();
		move();
		//long aftMove = System.nanoTime();
		collide();
		//long aftCollide = System.nanoTime();

		//long particlesTime = aftMove - befMove;
		//long paintTime = aftCollide - aftMove;
		//System.out.println(	"moving \t== " + particlesTime + "\n" +
		//					"colliding \t== " + paintTime + "\n" +
		//					"ratio \t\t== " + (1.0 * particlesTime / paintTime) + "\n");

	}

	public void stop() {
		for(Particle p : particles)
			p.motion.velocity.setToComponents(0, 0, 0);
	}

	private void move() {

		for(Particle p : particles) {

			force.setToVector(p.motion.velocity);
			force.setToScalarProduct(-friction);
			force.updateComponents(gravity);

			p.updateParticle(force, p.getMass());
		}
	}

	private void collide() {
		final int num = particles.size();
		Particle p1, p2;


		//System.out.println("\n----------------------\n");

		//long validationStart = System.nanoTime();
		for(int i = 0; i < num; i++) {
			p1 = particles.get(i);

			sec[i] = p1.shouldCollide();
			prim[i] = sec[i] && p1.moves();
		}
		//long validationEnd = System.nanoTime();


		//long innerStart = 0, innerEnd = 0;
		//long innerCount = 0;
		//long outerStart = System.nanoTime();
		for(int i = 0; i < num; i++) {
			if(prim[i]) {
				p1 = particles.get(i);
				sec[i] = false;

		//		innerStart = System.nanoTime();
				for(int j = 0; j < num; j++) {
					if(sec[j]) {
		//				innerCount++;
						p2 = particles.get(j);

						dist.setToVector(p2.motion.position);
						dist.setToVectorSubstraction(p1.motion.position);

							p1.collision(p2, dist);
					}
				}
		//		innerEnd = System.nanoTime();
			}
		}
		//long outerEnd = System.nanoTime();

		//long validationTime = validationEnd - validationStart;
		//long outerTime = outerEnd - outerStart;
		//long innerTime = innerEnd - innerStart;

		//		System.out.println(	"valid \t==" + validationTime + "\n" +
		//							"Outer \t== " + outerTime + "\n" +
		//							"Inner \t== " + innerTime + " * " + innerCount + "\n" +
		//					"ratio \t\t== " + (1.0 * outerTime / innerTime) + "\n");
	}

	public void setGravity(double g) {
		this.gravity .setToComponents(0, g, 0);
	}

	public void setFriction(double f) {
		this.friction = f;
	}

	public Vector[] getPositionPointers() {
		Vector[] positions = new Vector[particles.size()];

		int i = 0;
		for(Particle p : particles) {
			positions[i] = p.motion.position;
			i++;
		}

		return positions;
	}

	public void draw(Graphics g) {
		//System.out.println(timer);
		for(Particle p : particles)
			p.draw(g);
	}

	public String toString() {
		return timer + " >> " + particles.size();
	}

	public boolean shouldRedraw() {
		long now = System.nanoTime();

		if(timeDiff(now) > cycleLen) {
			this.time = now;
			return true;
		}

		return false;
	}

	public long timeDiff(long now) {
		return now - time;
	}
}

/*
	Bodies A, B

	detectCollision(A, B) -> Normal, Point
	collisionResponse(A, B, detectCollision(A, B)) -> void

	A.detectCollision(B) -> Vector[2] //Normal, Point of impact
*/