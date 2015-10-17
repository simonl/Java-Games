package boubouworld2;

public class Scalar
{
    //Instance variables
    private value;
	
    
    //Constructors
    public Vector()
    {
    
    }
	
    
    //Getters
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
    
    public double getMagnitude()
    {
	return magnitude;
    }
    
    public double getTheta()
    {
	return theta;
    }
	
    public double getPhi()
    {
	return phi;
    }
    
    
    //Setters
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
    
    public void setMagnitude(double magnitude)
    {
	this.magnitude = magnitude;
    }
    
    public void setTheta(double theta)
    {
	this.theta = theta;
    }
    
    public void setPhi(double phi)
    {
	this.phi = phi;
    }
    
    
    //CUSTOM
    public void updateComponents(double dX)
    {
	setX(getX() + dX);
    }
    
    public void updateComponents(double dX, double dY)
    {
	setX(getX() + dX);
	setY(getY() + dY);
    }
    
    public void updateComponents(double dX, double dY, double dZ)
    {
	setX(getX() + dX);
	setY(getY() + dY);
	setZ(getZ() + dZ);
    }
    
    public double getDifference(Vector vector)
    {        
	double deltaX = vector.getX() - getX();
	double deltaY = vector.getY() - getY();
	double deltaZ = vector.getZ() - getZ();
	
	return (Math.sqrt(Math.pow(deltaX, 2.0) + Math.pow(deltaY, 2.0) + Math.pow(deltaZ, 2.0))); 
    }
	
    public Vector addVector(Vector vector)
    {
	double newX = getX() + vector.getX();
	double newY = getY() + vector.getY();
	double newZ = getZ() + vector.getZ();
	
	return (new CartesianVector(newX, newY, newZ));
    }
    
    public Vector substractVector(Vector vector)
    {
	double newX = getX() - vector.getX();
	double newY = getY() - vector.getY();
	double newZ = getZ() - vector.getZ();
	
	return (new CartesianVector(newX, newY, newZ));
    }
    
    public double dotProduct(Vector vector)
    {
	double productX = getX() * vector.getX();
	double productY = getY() * vector.getY();
	double productZ = getZ() * vector.getZ();
	
	return (productX + productY + productZ);
    }
    
    public Vector crossProduct(Vector vector)
    {
	double partX = getY() * vector.getZ() - getZ() * vector.getY();
	double partY = getZ() * vector.getX() - getX() * vector.getZ();
	double partZ = getX() * vector.getY() - getY() * vector.getX();
	
	return (new CartesianVector(partX, partY, partZ));
    } 
}
