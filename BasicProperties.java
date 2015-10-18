package javaapps.physicsobject;

public class BasicProperties
{
    
    public static final BasicProperties ELECTRON    = new BasicProperties(Mass.ELECTRON, Charge.NEGATIVE);
    public static final BasicProperties PROTON      = new BasicProperties(Mass.PROTON, Charge.POSITIVE);
    public static final BasicProperties NEUTRON     = new BasicProperties(Mass.NEUTRON, Charge.NEUTRAL);
    
    public Mass mass;
    public Charge charge;
    public Spin spin;
    
    //CONSTRUCTORS
    public BasicProperties()
    {
	this(new Mass(), new Charge(), new Spin());
    }
    
    public BasicProperties(Mass mass)
    {
	this(mass, new Charge(), new Spin());
    }
    
    public BasicProperties(Mass mass, Charge charge)
    {
	this(mass, charge, new Spin());
    }
    
    public BasicProperties(Mass mass, Charge charge, Spin spin)
    {
	this.mass = new Mass(mass);
	this.charge = new Charge(charge);
	this.spin = new Spin(spin);
    }
    
    public BasicProperties(BasicProperties other)
    {
	this(other.mass, other.charge, other.spin);
    }
    
    //GETTERS
    public Mass getMass()
    {
	return this.mass;
    }
    
    public Charge getCharge()
    {
	return this.charge;
    }
    
    public Spin getSpin()
    {
	return this.spin;
    }
    
    //SETTER
    public void setMass(double value)
    {
	this.mass = new Mass(value);
    }
    
    public void setCharge(int value)
    {
	if(value >= 1)
	    this.charge = new Charge(Charge.POSITIVE);
	else if(value <= -1)
	    this.charge = new Charge(Charge.NEGATIVE);
	else
	    this.charge = new Charge(Charge.NEUTRAL);
    }
    
    public void setSpin(int direction)
    {
	if(direction > 0)
	    this.spin = new Spin(Spin.UP);
	else
	    this.spin = new Spin(Spin.DOWN);
    }
    
    //TESTS
    public boolean equals(BasicProperties other)
    {
	return  this.mass.equals(other.mass) &&
		this.charge.equals(other.charge) &&
		this.spin.equals(other.spin);
    }
    
    //UTILITIES
    public BasicProperties copy()
    {
	return new BasicProperties(this);
    }
    
    public String toString()
    {
	return "Properties : " + this.mass + ", " + this.charge + ", " + this.spin;
    }
}
