package javaapps.physicsobject;

public class ParticleQ
{
    private final Quantity mass;
    private final Quantity charge;
    private final MotionQ motion;
    //private final SizeQ size;
    
    //CONSTRUCTORS
    public ParticleQ() throws Exception
    {
	this.mass = new Quantity(1.0, Units.MASS);
	this.charge = new Quantity(0.0, Units.CHARGE);
	this.motion = new MotionQ();
	//this.size = new SizeQ();
    }
    
    public ParticleQ(Quantity mass) throws Exception
    {
	this(mass, new Quantity(0.0, Units.CHARGE), new MotionQ());
    }
    
    public ParticleQ(Quantity mass, Quantity charge) throws Exception
    {
	this(mass, charge, new MotionQ());
    }
    
    public ParticleQ(Quantity mass, Quantity charge, MotionQ mQ) throws Exception
    {
	this();
	this.mass.assign(mass);
	this.charge.assign(charge);
	this.motion.assign(mQ);
    }
    
    public ParticleQ(Quantity mass, MotionQ mQ) throws Exception
    {
	this(mass, new Quantity(0.0, Units.CHARGE), mQ);
    }
    
    public ParticleQ(ParticleQ pQ) throws Exception
    {
	this(pQ.getMass(), pQ.getCharge(), pQ.getMotion());
    }
    
    //GETTERS
    public Quantity getMass()
    {
	return this.mass;
    }
    
    public Quantity getCharge()
    {
	return this.charge;
    }
    
    public MotionQ getMotion()
    {
	return this.motion;
    }
    
    //public SizeQ getSize()
    {
    //    return this.size;
    }
    
    public Quantity getWork(VectorQ dist) throws Exception
    {
	return dist.times(getActingForce());
    }
    
    //CUSTOM METHODS
    public void updateParticle(VectorQ force) throws Exception
    {
	this.motion.updateMotion(force.div(this.mass));
    }
    
    public VectorQ getActingForce() throws Exception
    {
	return this.motion.getAcceleration().times(this.mass);
    }
    
    public VectorQ getMomentum() throws Exception
    {
	return this.motion.getVelocity().times(this.mass);
    }
    
    public VectorQ getGravitationalForce(ParticleQ pQ) throws Exception
    {
	Quantity force = Quantity.G.times(this.mass).times(pQ.getMass());
	
	return this.getForceLaw(pQ).times(force);
    }
    
    public VectorQ getElectricForce(ParticleQ pQ) throws Exception
    {        
	Quantity force = Quantity.Ke.times(this.charge).times(pQ.getCharge());
	
	return this.getForceLaw(pQ).times(force);
    }
    
    public VectorQ getForceLaw(ParticleQ pQ) throws Exception
    {
	VectorQ vR = this.getSeparation(pQ);
	Quantity R = vR.getNorm();
	
	vR.assign(vR.getUnitVector().getDimensionless());
	
	return vR.div(R).div(R);
    }
    
    public VectorQ getSeparation(ParticleQ pQ) throws Exception
    {
	return pQ.getMotion().getPosition().minus(this.motion.getPosition());
    }
    
    //public VectorQ getCollisionDirection(ParticleQ pQ) throws Exception
    {
    //    return this.size.determineIntersection(pQ.getSize(), this.getSeparation(pQ));
    }
    
    public void collision(ParticleQ other) throws Exception
    {
	Quantity m1 = this.getMass(); //Mass
	Quantity m2 = other.getMass(); //Mass
	VectorQ u = this.motion.getVelocity(); //Speed
	VectorQ v = other.getMotion().getVelocity(); //Speed
	
	//VectorQ hitComponent = this.getCollisionDirection(other).getUnitVector(); //DIMENSIONLESS
				//Have to check this one
	
	//Quantity multU1 = u.times(hitComponent); //Speed
	//Quantity multV1 = v.times(hitComponent); //Speed
	
	//Quantity multU2 = (Quantity.TWO).times(m2).times(multV1).minus(multU1.times(m2.minus(m1))).div(m1.plus(m2)); //(P-P)M = SPEED
	//Quantity multV2 = multU1.minus(multV1).plus(multU2);    //SPEED
	
	//u.assign(u.getProjectionOnPlane(hitComponent).plus(hitComponent.times(multU2)));
	//v.assign(v.getProjectionOnPlane(hitComponent).plus(hitComponent.times(multV2)));
    }
    
    public void collision(ParticleQ other, VectorQ collisionDirection) throws Exception
    {
	Quantity m1 = this.getMass(); //Mass
	Quantity m2 = other.getMass(); //Mass
	VectorQ u = this.motion.getVelocity(); //Speed
	VectorQ v = other.getMotion().getVelocity(); //Speed
	
	//VectorQ hitComponent = this.getCollisionDirection(other).getUnitVector(); //DIMENSIONLESS
	
	//Quantity multU1 = u.times(hitComponent); //Speed
	//Quantity multV1 = v.times(hitComponent); //Speed
	
	//Quantity multU2 = (Quantity.TWO).times(m2).times(multV1).minus(multU1.times(m2.minus(m1))).div(m1.plus(m2)); //(P-P)M = SPEED
	//Quantity multV2 = multU1.minus(multV1).plus(multU2);    //SPEED
	
	//u.assign(u.getProjectionOnPlane(hitComponent).plus(hitComponent.times(multU2)));
	//v.assign(v.getProjectionOnPlane(hitComponent).plus(hitComponent.times(multV2)));
    }
    
    //TESTS
    public boolean equals(ParticleQ pQ)
    {
	return  this.mass.equals(pQ.getMass()) && 
		this.charge.equals(pQ.getCharge()) && 
		this.motion.equals(pQ.getMotion());
    }
    
    //UTILITIES
    public ParticleQ copy() throws Exception
    {
	return new ParticleQ(this);
    }
    
    public void assign(ParticleQ pQ) throws Exception
    {
	this.mass.assign(pQ.getMass());
	this.charge.assign(pQ.getCharge());
	this.motion.assign(pQ.getMotion());
    }
    
    public String toString()
    {
	return  "Mass\t\t:\t" + this.mass + ";\n" +
		"Charge\t\t:\t" + this.charge + ";\n" +
		//this.size + "\n" + 
		this.motion;
    }
    
    public static void main(String[] args) throws Exception
    {
	System.out.println(new ParticleQ());
	
	ParticleQ pQ = new ParticleQ();
	System.out.println(pQ.getActingForce());
	pQ.updateParticle(new VectorQ(new Quantity(20.0, Units.FORCE)));
	VectorQ dx = new VectorQ(new Quantity(10.0, Units.LENGTH));
	
	System.out.println(pQ.getActingForce());
	System.out.println(pQ.getWork(dx));
    }
}
