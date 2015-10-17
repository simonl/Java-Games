package boubouworld2;

public class CoordinateSystem
{
    //Instance variables
     final double DELAY = 1.0E-6, WINDOWMAX = 1000.0;
     final double C = 3.0E8, ELECTRONHOLE = 1.3E74, PLANCKHOLE = 6E51;
    
    public Vector position, velocity, acceleration;
    public Bounds positionBounds, velocityBounds, accelerationBounds;
    
    
    //Constructors
    public CoordinateSystem()
    {
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
	accelerationBounds = new CubicBounds(ELECTRONHOLE);
    }
    
    public void updateMotion(Vector force, double mass)
    {
	position.updateComponents(velocity.getX() * DELAY, velocity.getY() * DELAY, velocity.getZ() * DELAY);
	
	velocity.updateComponents(acceleration.getX() * DELAY, acceleration.getY() * DELAY, acceleration.getZ() * DELAY);   
	
	acceleration = force.scalarProduct(1.0 / mass);
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
