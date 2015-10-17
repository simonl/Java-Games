package boubouworld2;

public class Vector
{
    //Instance variables
    double magnitude, theta, phi;
    double X, Y, Z;
	
    
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
    
    public double getDifferenceOfMagnitude(Vector vector)
    {                
	return getMagnitude() - vector.getMagnitude(); 
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
    
    public double separatingAngle(Vector vector)
    {
	return Math.acos(dotProduct(vector) / getMagnitude() / vector.getMagnitude());
    }
    
    public Vector crossProduct(Vector vector)
    {
	double partX = getY() * vector.getZ() - getZ() * vector.getY();
	double partY = getZ() * vector.getX() - getX() * vector.getZ();
	double partZ = getX() * vector.getY() - getY() * vector.getX();
	
	return (new CartesianVector(partX, partY, partZ));
    } 
    
    public Vector scalarProduct(double factor)
    {
	return (new CartesianVector(getX() * factor, getY() * factor, getZ() * factor));
    }
    
    public double getMultiplierA(Vector vector1, Vector vector2)
    {
	return (getX() * vector2.getY() - getY() * vector2.getX()) / (vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX());
    }
    
    public double getMultiplierB(Vector vector1, Vector vector2)
    {
	return (getX() * vector1.getY() - getY() * vector1.getX()) / (vector2.getX() * vector1.getY() - vector2.getY() * vector1.getX());
    }
    
    public boolean equals(Vector vector)
    {
	boolean checkX = getX() == vector.getX();
	boolean checkY = getY() == vector.getY();
	boolean checkZ = getZ() == vector.getZ();
	
	return (checkX && checkY && checkZ);
    }
    
    public Vector copy()
    {
	return (new PolarVector(getMagnitude(), getTheta(), getPhi()));
    }
    
    public String toString()
    {
	return "Vector of magnitude " + getMagnitude() + ", an inclination of " + getTheta() + ", a rotation of " + getPhi();
    }
    
}
