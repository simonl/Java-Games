package javaapps.physicsobject;

public class FractionalPowerUnitsException extends Exception
{
    Units units;
    int divisor;
    
    public FractionalPowerUnitsException(Units u, int n)
    {
	this.units = new Units(u);
	this.divisor = n;
    }
    
    public String toString()
    {
	return "Cannot take the " + this.divisor + "th root of Units : " + this.units;
    }
}
