package boubouworld2;

public class Coordinates
{
    //Instance variables
    private double X, Y, Z;
	
    
    //Constructors
    public Coordinates()
    {
    
    }
    
    public Coordinates(double X)
    {        
	this.X = X;
    }
    
    public Coordinates(double X, double Y)
    {        
	this.X = X;
	this.Y = Y;
    }
    
    public Coordinates(double X, double Y, double Z)
    {        
	this.X = X;
	this.Y = Y;
	this.Z = Z;
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
    
	
    public void setX(double X)
    {
	this.X = X;
    }
    
    public void setY(double Y)
    {
	this.Y = Y;
    }
    
    public void setZ(double Z)
    {
	this.Z = Z;
    }
    
    
    //CUSTOM
    public void updateCoordinates(double dX)
    {
	setX(getX() + dX);
    }
    
    public void updateCoordinates(double dX, double dY)
    {
	setX(getX() + dX);
	setY(getY() + dY);
    }
    
    public void updateCoordinates(double dX, double dY, double dZ)
    {
	setX(getX() + dX);
	setY(getY() + dY);
	setZ(getZ() + dZ);
    }
    
    public double getDifference(Coordinates point)
    {        
	double deltaX = point.getX() - getX();
	double deltaY = point.getY() - getY();
	double deltaZ = point.getZ() - getZ();
	
	return (Math.pow(Math.pow(deltaX, 2.0) + Math.pow(deltaY, 2.0) + Math.pow(deltaZ, 2.0), 0.5)); 
    }
}
