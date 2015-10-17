package boubouworld2;

public class CartesianVector extends Vector
{
    //Instance variables        
    
    //Constructors
    public CartesianVector(double X)
    {
	setX(X);
	
	completeVector();        
    }
    
    public CartesianVector(double X, double Y)
    {
	setX(X);
	setY(Y);
	
	completeVector();
    }
    
    public CartesianVector(double X, double Y, double Z)
    {
	setX(X);
	setY(Y);
	setZ(Z);
	
	completeVector();
    }
    
    
    //CUSTOM
    public void completeVector()
    {
	setMagnitude(Math.sqrt(X*X + Y*Y + Z*Z));
	setTheta(Math.atan2(Y, X));
	setPhi(Math.atan(Z/X));
    }
}
