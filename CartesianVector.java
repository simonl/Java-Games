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
	if(X == 0 && Y == 0 && Z == 0)
	{
	    setMagnitude(0);
	    setTheta(0);
	    setPhi(0);
	}
	else
	{
	    setMagnitude(Math.sqrt(X*X + Y*Y + Z*Z));
	    setTheta(Math.atan(Y / X));
	    setPhi(Math.atan2(Z, X));
	}
    }
}
