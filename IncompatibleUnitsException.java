package javaapps.physicsobject;

public class IncompatibleUnitsException extends Exception
{
    private Units units1;
    private Units units2;
    
    public IncompatibleUnitsException(Units u1, Units u2)
    {
	this.units1 = new Units(u1);
	this.units2 = new Units(u2);
    }
    
    public String toString()
    {
	return "Units are incompatible --> " + units1 + " != " + units2;
    }
}
