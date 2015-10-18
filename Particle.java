
import java.awt.*;
import java.awt.geom.*;

public class Particle
{
    //Instance variables
     final double DELAY = 1.0E-3, WINDOWMAX = 1000.0;
     final double C = 3.0E8, ELECTRONHOLE = 1.3E74, PLANCKHOLE = 6E51;

    public CoordinateSystem motion;
    public Dimensions dimensions;
    public Representation representation;

    public MomentInertia moment;

    private double restitution = 1;
    private double slidingFriction = 0;
    private double staticFriction = 0;



    private double mass, charge, spin, momentum, kineticEnergy, potentialEnergy;
    //public Vector momentumVector = new Vector();



	//NEW
	public boolean shouldCollide() {
		return true;
	}

	public boolean moves() {
		return this.motion.velocity.isNotZero();
	}

	public boolean shouldUpdate() {
		return true;
	}


	public void draw(Graphics g) {
		Image img = representation.getImage();
		int x = (int)motion.position.getX() - img.getWidth(null) / 2;
		int y = (int)motion.position.getY() - img.getHeight(null) / 2;

		g.drawImage(img, x, y, null);
	}

	public void draw(Graphics g, Vector angle) {

		Image img = representation.getImage(angle);

		int phi = img.getWidth(null) / 2;
		int theta = img.getHeight(null) / 2;
		int x = (int)angle.phi;
		int y = (int)angle.theta;

		g.drawImage(img , x - phi, y - theta, null);
	}

	public double boundingRadius() {
		return this.dimensions.R;
	}

    //Constructors
    public Particle()
    {
		this(1);
    }

    public Particle(double mass)
    {
		this(mass, 0, 0);
    }

    public Particle(double mass, double charge)
    {
		this(mass, charge, 0);
    }

    public Particle(double mass, double charge, double spin)
    {
	this.mass = mass;
	this.charge = charge;
	this.spin = spin;

	this.motion = new CoordinateSystem();
	this.dimensions = new Spheric(10.0);
	this.representation = new Representation(this, Color.BLUE, true);

    }

    public double getMass()
    {
	return mass;
    }

    public double getCharge()
    {
		return charge;
    }

    public double getKineticEnergy()
    {
		return (getMass() * motion.velocity.getMagnitude() * motion.velocity.getMagnitude() / 2.0);
    }

    //public Vector getMomentum()
    //{
    //    setMomentum();
    //    return momentumVector.copy();
    //}

    //public void setMomentum()
    //{
    //    momentumVector.setToVector(motion.velocity);
    //    momentumVector.setToScalarProduct(getMass());
    //}

    public double getSpin()
    {
	return spin;
    }

    //CUSTOM

    Vector forcePar = new Vector();
    Vector forcePerp = new Vector();

    public void push(Vector force, Vector radius) {

		this.forcePar.updateComponents(force.projectOnto(radius));
		this.forcePerp.updateComponents(force.substractVector(forcePar));
	}

	public void impulse(Vector momentum, Vector point) {

	}

    public void updateParticle(Vector force)
    {
	// if(fieldPoint != null)
	//     force = ((fieldPoint.gravitationalForce.scalarProduct(mass)).addVector(fieldPoint.electroMagneticForce.scalarProduct(charge))).copy();
	// else

	motion.updateMotion(force, mass);

	//mass = restMass / Math.sqrt(1 - Math.pow(motion.velocity.getMagnitude() / C, 2.0));
	//kineticEnergy = (mass - restMass) * C * C;
	// potentialEnergy = fieldPoint.potential;
    }

    public void updateParticle(Vector force, double factor)
    {
	// if(fieldPoint != null)
	//     force = ((fieldPoint.gravitationalForce.scalarProduct(mass)).addVector(fieldPoint.electroMagneticForce.scalarProduct(charge))).copy();
	// else

	force.setToScalarProduct(factor / getMass());
	motion.updateMotion(force);

	//mass = restMass / Math.sqrt(1 - Math.pow(motion.velocity.getMagnitude() / C, 2.0));
	//kineticEnergy = (mass - restMass) * C * C;
	// potentialEnergy = fieldPoint.potential;
    }

    public void updateParticle() {

	}

	public void collide(Particle other, Vector impact, Vector distAP, Vector distBP) {

		double multV1, multV2, multU1, multU2;
		final double m1 = getMass(), m2 = other.getMass();

		//Uncollide objects

		motion.position.updateComponents(motion.velocity, -CoordinateSystem.DELAY);
		other.motion.position.updateComponents(other.motion.velocity, -CoordinateSystem.DELAY);

		Vector[] comps1 = this.motion.velocity.components(impact);
		Vector[] comps2 = other.motion.velocity.components(impact);

		multV1 = motion.velocity.dotProduct(impact);
		multU1 = other.motion.velocity.dotProduct(impact);

		double C = this.restitution * other.restitution;
		double F = this.slidingFriction * other.slidingFriction;

		multV2 = (C * m2 * (multU1 - multV1) + multV1 * m1 + multU1 * m2) / (m1 + m2);
		multU2 = C * (multV1 - multU1) + multV2;

		motion.velocity.setToVectorSubstraction(impact, multV1);
		motion.velocity.setToVectorAddition(impact, multV2);

		motion.velocity.updateComponents(comps1[1], -F);
		other.motion.velocity.updateComponents(comps2[1], -F);


		other.motion.velocity.setToVectorSubstraction(impact, multU1);
		other.motion.velocity.setToVectorAddition(impact, multU2);
	}

	public void collide2(Particle other, Vector normal, Vector AP, Vector BP) {
/*
		double v1, v1f, v2, v2f;
		final double m1 = getMass(), m2 = other.getMass();

		Vector p1 = this.motion.velocity.scalarProduct(m1);
		p1.setToVectorAddition(this.motion.angularVelocity);

		normal = normal.unit();

		multV1 = motion.velocity.dotProduct(normal);
		multU1 = other.motion.velocity.dotProduct(normal);

		multV2 = (m2 * (2 * multU1 - multV1) + multV1 * m1) / (m1 + m2);
		// V1
		// 2 * m2 * u - v * m2 + v * m1  / mi + m2
		// 2 * m2 * (u - v) + v * m1
		//  (m2 * (2 * u - v) + m1 * v) / (m1 + m2)
		multU2 = multV1 - multU1 + multV2;



		Vector newV1 = normal.scalarProduct(multV2);
		Vector newV2 = normal.scalarProduct(multU2);

		Vector vPar1 = newV1.projectOnto(AP);
		Vector vPerp1 = newV1.substractVector(vPar1);
	*/

	}

	public void collision(Particle other, Vector separation) {
		Vector[] impact = dimensions.checkIfIntersect(other.dimensions, separation);

		if(impact != null) {
			collide(other, impact[0].unit(), impact[1], separation.substractVector(impact[1]));
		}
	}

}
