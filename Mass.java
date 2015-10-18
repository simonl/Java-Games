package javaapps.physicsobject;

public class Mass
{
    public static final Mass ELECTRON   = new Mass(9.10938188E-31);
    public static final Mass PROTON     = new Mass(1.67262158E-27);
    public static final Mass NEUTRON    = new Mass(1.67492729E-27);
    public static final Mass MASSLESS   = new Mass(0.0);
    
    public static final Mass ONE = new Mass(1.0); 
       
    public static final String SYMBOL = "kg";
    public static final String NAME = "Kilogram";
    
    private double value;
    
    public Mass()
    {
	
    }
    
    public Mass(double value)
    {
	this.value = value;
    }
    
    public Mass(Mass mass)
    {
	this.value = mass.getValue();
    }
    
    //GETTERS
    public double getValue()
    {
	return this.value;
    }
    
    //SETTERS
    public void setValue(double value)
    {
	this.value = value;
    }
    
    //TESTS
    public boolean hasMass()
    {
	return this.getValue() != 0.0;
    }
    
    public boolean isMassless()
    {
	return this.getValue() == 0.0;
    }
    
    public boolean equals(Mass other)
    {
	return this.getValue() == other.getValue();
    }
    
    //UTILITIES
    public Mass copy()
    {
	return new Mass(this);
    }
    
    public String toString()
    {
	return "Mass of " + this.getValue();
    }
}
