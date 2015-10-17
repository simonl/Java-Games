package boubouworld2;

public class PolarVector extends Vector
{
    //Instance variables
	
    
    //Constructors        
    public PolarVector(double magnitude)
    {
	setMagnitude(magnitude);
	
	completeVector();
    }
    
    public PolarVector(double magnitude, double theta)
    {
	setMagnitude(magnitude);
	setTheta(theta);
	
	completeVector();
    }
    
    public PolarVector(double magnitude, double theta, double phi)
    {
	setMagnitude(magnitude);
	setTheta(theta);
	setPhi(phi);
	
	completeVector();
    }
    
    
    //CUSTOM
    public void completeVector()
    {
	setX(magnitude * Math.sqrt(1.0 / ( 1.0 + Math.tan(theta) * Math.tan(theta) + Math.tan(phi) * Math.tan(phi))));
	setY(Math.tan(theta) * X);
	setZ(Math.tan(phi) * X);
    }
}
