package javaapps.physicsobject;

public class CoordinateSystem
{
    //Instance variables
     public static final double DELAY = 5.0E-4;
     final double WINDOWMAX = 1000.0;
     final double C = 3.0E8, ELECTRONHOLE = 1.3E74, PLANCKHOLE = 6E51;
    
    public Vector position, velocity, acceleration;
    public Bounds positionBounds, velocityBounds, accelerationBounds;
    
    
    //Constructors
    public CoordinateSystem()
    {
	this.position = new Vector();
	this.velocity = new Vector();
	this.acceleration = new Vector();
	
	setBounds();
    }
    
    public CoordinateSystem(Vector position)
    {
	this.position = position;
	this.velocity = new Vector();
	this.acceleration = new Vector();
	
	setBounds();
    }
    
    public CoordinateSystem(Vector position, Vector velocity)
    {
	this.position = position;
	this.velocity = velocity;
	this.acceleration = new Vector();
	
	setBounds();
    }
    
    public CoordinateSystem(Vector position, Vector velocity, Vector acceleration)
    {
	this.position = position;
	this.velocity = velocity;
	this.acceleration = acceleration;
	
	setBounds();
    }
    
    
    //CUSTOM
    public void setBounds()
    {
	positionBounds = new CubicBounds(WINDOWMAX);
	velocityBounds = new CubicBounds(C);
	accelerationBounds = new CubicBounds(Double.POSITIVE_INFINITY);
    }
    
    public void updateMotion(Vector force, double mass)
    {        
	double dxX = velocity.getX() * DELAY;
	double dxY = velocity.getY() * DELAY;
	double dxZ = velocity.getZ() * DELAY;
	
	position.updateComponents(dxX, dxY, dxZ);
	    
	double dvX = acceleration.getX() * DELAY;
	double dvY = acceleration.getY() * DELAY;
	double dvZ = acceleration.getZ() * DELAY;
	
	velocity.updateComponents(dvX, dvY, dvZ);
	
	acceleration.setToScalarProduct(0);
	if(force != null)
	{
	    double daX = force.getX() / mass;
	    double daY = force.getY() / mass;
	    double daZ = force.getZ() / mass;
	    
	    acceleration.updateComponents(daX, daY, daZ);
	}
    }
    
    ///
    public void updateMotion(Vector newAcceleration)
    {        
	double dxX = velocity.getX() * DELAY;
	double dxY = velocity.getY() * DELAY;
	double dxZ = velocity.getZ() * DELAY;
	
	position.updateComponents(dxX, dxY, dxZ);
	    
	double dvX = acceleration.getX() * DELAY;
	double dvY = acceleration.getY() * DELAY;
	double dvZ = acceleration.getZ() * DELAY;
	
	velocity.updateComponents(dvX, dvY, dvZ);
	
	acceleration.setToScalarProduct(0);
	if(newAcceleration != null)
	{
	    double daX = newAcceleration.getX();
	    double daY = newAcceleration.getY();
	    double daZ = newAcceleration.getZ();
	    
	    acceleration.updateComponents(daX, daY, daZ);
	}
    }
    
    public Vector getMomentum(double mass)
    {
	return velocity.scalarProduct(mass);
    }
    
    public Vector getActingForce(double mass)
    {
	return acceleration.scalarProduct(mass);
    }
    
}
