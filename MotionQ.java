package javaapps.physicsobject;

public class MotionQ
{
    public static final Quantity STEP = new Quantity(1E-4, Units.TIME);
    
    private final VectorQ position;
    private final VectorQ velocity;
    private final VectorQ acceleration;
    
    public MotionQ() throws Exception
    {
	this.position = new VectorQ(Units.LENGTH);
	this.velocity = new VectorQ(Units.SPEED);
	this.acceleration = new VectorQ(Units.ACCELERATION);
    }
    
    public MotionQ(VectorQ p, VectorQ v, VectorQ a) throws Exception
    {
	this();
	this.position.assign(p);
	this.velocity.assign(v);
	this.acceleration.assign(a);
    }
    public MotionQ(MotionQ mQ) throws Exception
    {
	this(mQ.getPosition(), mQ.getVelocity(), mQ.getAcceleration());
    }
    
    //GETTERS
    public VectorQ getPosition()
    {
	return this.position;
    }
    
    public VectorQ getVelocity()
    {
	return this.velocity;
    }
    
    public VectorQ getAcceleration()
    {
	return this.acceleration;
    }
    
    //CUSTOM
    public void updateMotion(VectorQ newAcc) throws Exception
    {
	this.position.assign(this.position.plus(this.velocity.times(STEP)));
	
	this.velocity.assign(this.velocity.plus(this.acceleration.times(STEP)));
	
	this.acceleration.assign(newAcc);
    }
    
    //TESTS
    public boolean equals(MotionQ mQ)
    {
	return  this.position.equals(mQ.getPosition()) &&
		this.velocity.equals(mQ.getVelocity()) &&
		this.acceleration.equals(mQ.getAcceleration());
    }
    
    //UTILITIES
    public MotionQ copy() throws Exception
    {
	return new MotionQ(this);
    }
    
    public void assign(MotionQ mQ) throws Exception
    {
	this.position.assign(mQ.getPosition());
	this.velocity.assign(mQ.getVelocity());
	this.acceleration.assign(mQ.getAcceleration());
    }
    
    public String toString()
    {
	return  "Position\t:\t( " + this.position + ");\n" + 
		"Velocity\t:\t( " + this.velocity + ");\n" + 
		"Acceleration\t:\t( " + this.acceleration + ");\n";
    }
    
    public static void main(String[] args) throws Exception
    {
	System.out.println(new MotionQ());
	
	MotionQ m = new MotionQ();
	//VectorQ vQ = VectorQ.UNIT_X.times(new Quantity(Units.ACCELERATION));
	//m.updateMotion(vQ);
	
	System.out.println(m);
	System.out.println(m.copy());
	System.out.println(new MotionQ(m));
    }
}
