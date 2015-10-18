package javaapps.physicsobject;

public class Dimensions
{
    //Instance variables
    double X, Y, Z;
    double R;
    boolean isASphere;
	
    
    //Constructors
    public Dimensions()
    {
    
    }
    
    public Dimensions(double X)
    {        
	this.X = X;
	
	this.R = X;
	this.isASphere = false;
    }
    
    public Dimensions(double X, double Y)
    {        
	this.X = X;
	this.Y = Y;
	
	this.R = Math.sqrt(X*X + Y*Y);
	this.isASphere = false;
    }
    
    public Dimensions(double X, double Y, int Z)
    {        
	this.X = X;
	this.Y = Y;
	this.Z = Z;
	
	this.R = Math.sqrt(X*X + Y*Y + Z*Z);
	this.isASphere = false;
    }
    
    public double getX()
    {
	return X;
    }
    
    public double getY()
    {
	return Y;
    }
    
    public double getZ()
    {
	return Z;
    }
    
    public double getR()
    {
	return R;
    }
    
    public boolean isASphere()
    {
	return isASphere;
    }
    
    public void setY(double Y)
    {
	this.Y = Y;
    }
    
    public void setX(double X)
    {
	this.X = X;
    }
    
    public void setZ(double Z)
    {
	this.Z = Z;
    }
    
    public void setR(double R)
    {
	this.R = R;
    }
    
    public void setIsASphere(boolean isASphere)
    {
	this.isASphere = isASphere;
    }
    
    //Custom Methods
    public boolean checkIfIntersect(Dimensions object2, Vector separation)
    {
	boolean answer = false;
	boolean checkX;
	boolean checkY;
	boolean checkZ;
	
	if(isASphere() && object2.isASphere())
	{
	    if(separation.getMagnitude() >= Math.abs(getRadius() - object2.getRadius()))
		if(separation.getMagnitude() <= getRadius() + object2.getRadius())
		    answer = true;
	}
	else 
	{
	    if(!isASphere() && !object2.isASphere())
	    {
		checkX =  Math.abs(separation.getX()) < getX() + object2.getX();
		checkY =  Math.abs(separation.getY()) < getY() + object2.getY();
		checkZ =  Math.abs(separation.getZ()) < getZ() + object2.getZ();
	
		answer = checkX || checkY || checkZ;
	    }
	    else 
	    {
		if(isASphere() && !object2.isASphere())
		{
		    if(separation.getMagnitude() < object2.getR() + getR())
			if(false)
		    checkX = separation.getX() < object2.getX();
		}
		else
		{
		    answer = false;
		}
	    }
	}    
	
	return answer;
    }
    
    public double getRadius()
    {   
	return getX()*getX() + getY()*getY() + getZ()*getZ();
    }
    
    public double getArea()
    {
	if(isASphere())
	    return getRadius() * getRadius() * 4.0 * Math.PI;
	else
	    return 2.0*(getX()*getY() + getX()*getZ() + getY()*getZ());
    }
    
    public double getVolume()
    {
	if(isASphere())
	    return 4.0/3.0 * Math.PI * Math.pow(getRadius(), 3.0);
	else
	    return getX()*getY()*getZ();
    }
    
    
}
