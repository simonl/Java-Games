package boubouworld2;

public class Particle
{
    //Instance variables
     final double DELAY = 1.0E-6, WINDOWMAX = 1000.0;
     final double C = 3.0E8, ELECTRONHOLE = 1.3E74, PLANCKHOLE = 6E51;
    
    public CoordinateSystem motion;
    
    private double restMass;
    private double mass = 1, charge, spin, momentum, kineticEnergy, potentialEnergy;
    
    //Constructors
    public Particle()
    {
    
    }
    
    public Particle(double mass)
    {
	this.restMass = this.mass = mass;
    }
    
    public Particle(double mass, double charge)
    {
	this.mass = mass;
	this.charge = charge;
    }
    
    public Particle(double mass, double charge, Vector momentum)
    {
	this.mass = mass;
	this.charge = charge;
	this.momentum = momentum.getMagnitude();
	
	motion.velocity = momentum.scalarProduct(1.0 / mass);
    }
    
    
    //CUSTOM
    public void updateParticle(StaticPoint fieldPoint)
    {
	Vector force = ((fieldPoint.gravitationalForce.scalarProduct(mass)).addVector(fieldPoint.electroMagneticForce.scalarProduct(charge))).copy();
	motion.updateMotion(force, mass);
	
	mass = restMass / Math.sqrt(1 - Math.pow(motion.velocity.getMagnitude() / C, 2.0));
	kineticEnergy = (mass - restMass) * C * C;
	potentialEnergy = fieldPoint.potential;
    }
    
    public void collision(Particle other)
    {
	Vector impactComponent, deflectComponent;
	Vector impactVelocity, deflectVelocity, totalMomentum;
	double m1, m2;
	Vector v1, v2, u1, u2;
	Vector pv2, pu2;
	Vector tan1, tan2;
	double E, mult1, mult2, multv2;
	m1 = mass;
	m2 = other.mass;
	
	    totalMomentum = motion.velocity.addVector(other.motion.velocity);
	    // System.out.println(totalMomentum.toString());
	    
	E = Math.pow(motion.velocity.getMagnitude(), 2.0)+Math.pow(other.motion.velocity.getMagnitude(), 2.0);
	    // System.out.println("" + E);
	    //  System.out.println("\n A");
	
	    impactComponent = other.motion.position.substractVector(motion.position);
	impactComponent = impactComponent.scalarProduct(1.0/impactComponent.getMagnitude());
	    // System.out.println(impactComponent.toString());
		    
	    deflectVelocity = motion.velocity.substractVector(impactComponent.scalarProduct(motion.velocity.dotProduct(impactComponent)));
	    
	    deflectComponent = deflectVelocity.scalarProduct(1.0/deflectVelocity.getMagnitude());
	    // System.out.println(deflectComponent.toString());
	    //  System.out.println("\n A A");
	
	v1 = impactComponent.scalarProduct(motion.velocity.getMultiplierA(impactComponent, deflectComponent));            
	    tan1 = deflectComponent.scalarProduct(motion.velocity.getMultiplierB(impactComponent, deflectComponent));
	    // System.out.println(v1.toString());
	    // System.out.println("" + motion.velocity.getMultiplierA(impactComponent, deflectComponent));
	    // System.out.println(tan1.toString());
	    // System.out.println("" + motion.velocity.getMultiplierB(impactComponent, deflectComponent));
	    //  System.out.println("\n A A A");
	    
	u1 = impactComponent.scalarProduct(other.motion.velocity.getMultiplierA(impactComponent, deflectComponent));
	    tan2 = deflectComponent.scalarProduct(other.motion.velocity.getMultiplierB(impactComponent, deflectComponent));
	    // System.out.println(u1.toString());
	    // System.out.println("" + other.motion.velocity.getMultiplierA(impactComponent, deflectComponent));
	    // System.out.println(tan2.toString());
	    // System.out.println("" + other.motion.velocity.getMultiplierB(impactComponent, deflectComponent));
	    //  System.out.println("\n A A A A");
	     
	     mult1 = motion.velocity.getMultiplierA(impactComponent, deflectComponent);
	     mult2 = other.motion.velocity.getMultiplierA(impactComponent, deflectComponent);
	     // System.out.println("" + mult1);
	     // System.out.println("" + mult2);
	     
	v2 = impactComponent.scalarProduct((2 * m2 * u1.getMagnitude() * getSign(mult2) - v1.getMagnitude() * getSign(mult1) * (m2 - m1)) / (m1 + m2));
	    // System.out.println(v2.toString());
		multv2 = v2.getMultiplierA(impactComponent, deflectComponent);
		
	u2 = impactComponent.scalarProduct(v1.getMagnitude() * getSign(mult1) - u1.getMagnitude() * getSign(mult2) + v2.getMagnitude() * getSign(multv2));
	    // System.out.println(u2.toString());
	    //  System.out.println("\n A A A A A");
	
	motion.velocity = v2.addVector(tan1);
	    // System.out.println(motion.velocity.toString());
	other.motion.velocity = u2.addVector(tan2);
	//     System.out.println(other.motion.velocity.toString());
	//      System.out.println("\n A A A A A A");
	// System.out.println("" + Math.atan2(1,0) + " " + Math.atan2(1,1) + " " + Math.atan2(0,1) + " " + Math.atan2(-1,1) + " " + Math.atan2(-1,0));
	// System.out.println("" + Math.atan2(-1,-1) + " " + Math.atan2(0,-1) + " " + Math.atan2(1,-1));
	
	
	    totalMomentum = motion.velocity.addVector(other.motion.velocity);
	    // System.out.println(totalMomentum.toString());
	    
	E = Math.pow(motion.velocity.getMagnitude(), 2.0)+Math.pow(other.motion.velocity.getMagnitude(), 2.0);
	    // System.out.println("" + E);
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
