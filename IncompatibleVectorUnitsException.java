package javaapps.physicsobject;

public class IncompatibleVectorUnitsException extends Exception
{
    private Units units1;
    private Units units2;
    private Units units3;

    public IncompatibleVectorUnitsException(Units u1, Units u2, Units u3)
    {
	this.units1 = new Units(u1);
	this.units2 = new Units(u2);
	this.units3 = new Units(u3);
    }

    public String toString()
    {
	return "Vector units are incompatible --> " + units1 + "!= " + units2 + "OR " + units2 + "!= " + units3;
    }
}
