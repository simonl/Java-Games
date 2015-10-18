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
    //public Vector momentumVector = new Vector();
    
    //COLLISION VECTORS
    private Vector impactComponent = new Vector();
    
    
    
    
    //Constructors
    public Particle()
    {
	this.motion = new CoordinateSystem();
	this.dimensions = new Spheric(50.0);
	this.mass = 1.0;
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
    public void updateParticle(Vector force)
    {   
	// if(fieldPoint != null)
	//     force = ((fieldPoint.gravitationalForce.scalarProduct(mass)).addVector(fieldPoint.electroMagneticForce.scalarProduct(charge))).copy();
	// else
	    
	motion.updateMotion(force, mass);
	
	mass = restMass / Math.sqrt(1 - Math.pow(motion.velocity.getMagnitude() / C, 2.0));
	kineticEnergy = (mass - restMass) * C * C;
	// potentialEnergy = fieldPoint.potential;
    }
    
    public void updateParticle(Vector force, double factor)
    {   
	// if(fieldPoint != null)
	//     force = ((fieldPoint.gravitationalForce.scalarProduct(mass)).addVector(fieldPoint.electroMagneticForce.scalarProduct(charge))).copy();
	// else
	
	force.setToScalarProduct(factor / getMass());
	motion.updateMotion(force);
	
	mass = restMass / Math.sqrt(1 - Math.pow(motion.velocity.getMagnitude() / C, 2.0));
	kineticEnergy = (mass - restMass) * C * C;
	// potentialEnergy = fieldPoint.potential;
    }
    
    public void collision(Particle other)
    {
	double multV1, multV2, multU1, multU2;
	double m1 = mass, m2 = other.mass;
	
	    impactComponent.setToVector(other.motion.position);
	    impactComponent.setToVectorSubstraction(motion.position);
	    impactComponent.setToScalarProduct(1.0/impactComponent.getMagnitude());
	
	multV1 = motion.velocity.dotProduct(impactComponent); 
	multU1 = other.motion.velocity.dotProduct(impactComponent);
	    
	multV2 = (2 * m2 * multU1 - multV1 * (m2 - m1)) / (m1 + m2);
	multU2 = multV1 - multU1 + multV2;
	
		
	motion.velocity.setToVectorSubstraction(impactComponent, multV1);        
	motion.velocity.setToVectorAddition(impactComponent, multV2);
	   
	other.motion.velocity.setToVectorSubstraction(impactComponent, multU1);        
	other.motion.velocity.setToVectorAddition(impactComponent, multU2);
	
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
