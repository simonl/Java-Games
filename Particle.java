package javaapps.physicsobject;

public class Particle
{
    //Instance variables
     final double DELAY = 1.0E-3, WINDOWMAX = 1000.0;
     final double C = 3.0E8, ELECTRONHOLE = 1.3E74, PLANCKHOLE = 6E51;
    
    public CoordinateSystem motion;
    public Dimensions dimensions;
    
    private double restMass;
    private double mass, charge, spin, momentum, kineticEnergy, potentialEnergy;
    
    //Constructors
    public Particle()
    {
	this.motion = new CoordinateSystem();
	this.dimensions = new Spheric(50.0);
    }
    
    public Particle(double mass)
    {
	this.restMass = this.mass = mass;
	
	this.motion = new CoordinateSystem();
	this.dimensions = new Spheric(50.0);
    }
    
    public Particle(double mass, double charge)
    {
	this.mass = mass;
	this.charge = charge;
	
	this.motion = new CoordinateSystem();
	this.dimensions = new Spheric(50.0);
    }
    
    public Particle(double mass, double charge, Vector momentum)
    {
	this.mass = mass;
	this.charge = charge;
	this.momentum = momentum.getMagnitude();
	
	this.motion = new CoordinateSystem();
	motion.velocity = momentum.scalarProduct(1.0 / mass);
	
	this.dimensions = new Spheric(50.0);
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
	return (getMomentum().getMagnitude() * motion.velocity.getMagnitude() / 2.0);
    }
    
    public Vector getMomentum()
    {
	return motion.velocity.scalarProduct(mass);
    }
    
    public double getSpin()
    {
	return spin;
    }
    
    //CUSTOM
    public void updateParticle(StaticPoint fieldPoint)
    {   
	Vector force;
	// if(fieldPoint != null)
	//     force = ((fieldPoint.gravitationalForce.scalarProduct(mass)).addVector(fieldPoint.electroMagneticForce.scalarProduct(charge))).copy();
	// else
	    force = null;
	    
	motion.updateMotion(force, mass);
	
	mass = restMass / Math.sqrt(1 - Math.pow(motion.velocity.getMagnitude() / C, 2.0));
	kineticEnergy = (mass - restMass) * C * C;
	// potentialEnergy = fieldPoint.potential;
    }
    
    public void collision(Particle other)
    {
	Vector impactComponent, deflectComponent;
	Vector impactVelocity, deflectVelocity;
	Vector v1, v2, u1, u2;
	Vector tan1, tan2;
	double mult1, mult2, multv2;
	double m1 = mass, m2 = other.mass;
	
	    
	impactComponent = other.motion.position.substractVector(motion.position);
	    impactComponent = impactComponent.scalarProduct(1.0/impactComponent.getMagnitude());
	      
	
	mult1 = motion.velocity.dotProduct(impactComponent); 
	mult2 = other.motion.velocity.dotProduct(impactComponent);
	    
	v1 = impactComponent.scalarProduct(mult1);            
	    tan1 = motion.velocity.substractVector(v1);
	    
	u1 = impactComponent.scalarProduct(mult2);
	    tan2 = other.motion.velocity.substractVector(u1);
	    
	v2 = impactComponent.scalarProduct((2 * m2 * mult2 - mult1 * (m2 - m1)) / (m1 + m2));
		multv2 = v2.dotProduct(impactComponent);
		
	u2 = impactComponent.scalarProduct(mult1 - mult2 + multv2);
	
	    
	motion.velocity = v2.addVector(tan1);
	other.motion.velocity = u2.addVector(tan2);
	    
    }
    
    public double getSign(double n)
    {
	double sign;
    
	if(n != 0)
	    sign = Math.abs(n)/n;
	else
	    sign = 0.0;
	    
	return sign;
    }
}
