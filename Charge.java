package javaapps.physicsobject;

public class Charge
{
    public static final Charge POSITIVE = new Charge(1);
    public static final Charge NEGATIVE = new Charge(-1);
    public static final Charge NEUTRAL = new Charge(0);
    
    public static final String SYMBOL = "C";
    
    private int value = 0;
    
    //CONSTRUCTORS
    public Charge()
    {
	this(NEUTRAL);
    }
    
    private Charge(int value)
    {
	this.value = value;
    }
    
    public Charge(Charge charge)
    {
	this.value = charge.getValue();
    }
    
    //GETTER
    public int getValue()
    {
	return this.value;
    }
    
    //SETTERS
    public void setPositive()
    {
	this.value = POSITIVE.getValue();
    }
    
    public void setNegative()
    {
	this.value = NEGATIVE.getValue();
    }
    
    public void setNeutral()
    {
	this.value = NEUTRAL.getValue();   
    }
    
    public void setToCharge(Charge other)
    {
	if(other.isPositive())
	    this.setPositive();
	else if(other.isNegative())
	    this.setNegative();
	else
	    this.setNeutral();    
    }
    
    //TESTS
    public boolean isCharged()
    {
	return !this.isNeutral();
    }
    
    public boolean isPositive()
    {
	return this.equals(POSITIVE);
    }
    
    public boolean isNegative()
    {
	return this.equals(NEGATIVE);
    }
    
    public boolean isNeutral()
    {
	return this.equals(NEUTRAL);
    }
    
    public boolean equals(Charge other)
    {
	return this.getValue() == other.getValue();
    }
    
    //UTILITIES
    public Charge copy()
    {
	if(this.isPositive())
	    return new Charge(POSITIVE);
	else if(this.isNegative())
	    return new Charge(NEGATIVE);
	else
	    return new Charge(NEUTRAL);
    }
    
    public String toString()
    {
	return "Charge of " + getValue();
    }
}
