package javaapps.physicsobject;

public class Vector
{
    //Instance variables
    double magnitude, theta, phi;
    double X, Y, Z;
	
    final double TOOSMALL = 1E-10;
    static long vectorCount = 0;
    
    //Constructors
    public Vector()
    {
	setVectorCount(getVectorCount() + 1);
	    //System.out.println(getVectorCount());
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
    
    public double getMagnitude(double X, double Y, double Z)
    {
	return Math.sqrt(X*X + Y*Y + Z*Z);
    }
    
    public double getTheta()
    {
	return theta;
    }
      
    public double getTheta(double X, double Y)
    {
	return Math.atan(Y / X);
    }
      
    public double getPhi()
    {
	return phi;
    }
    
    public double getPhi(double X, double Z)
    {
	return Math.atan(X / Z);
    }
    
    public long getVectorCount()
    {
	return vectorCount;
    }
    
    //Setters
    public void setX(double X)
    {
	this.X = zeroIfTooSmall(X);
    }
    
    public void setY(double Y)
    {
	this.Y = zeroIfTooSmall(Y);
    }
    
    public void setZ(double Z)
    {
	this.Z = zeroIfTooSmall(Z);
    }
    
    public void setMagnitude(double magnitude)
    {
	this.magnitude = zeroIfTooSmall(magnitude);
    }
    
    public void setTheta(double theta)
    {
	this.theta = zeroIfTooSmall(theta);
    }
    
    public void setPhi(double phi)
    {            
	this.phi = zeroIfTooSmall(phi);
    }
    
    public void setVectorCount(long vectorCount)
    {
	this.vectorCount = vectorCount;
    }
    
    //CUSTOM
    public void updateComponents(double dX)
    {
	setX(getX() + dX);
	
	completeCartesianVector();
    }
    
    public void updateComponents(double dX, double dY)
    {
	setX(getX() + dX);
	setY(getY() + dY);
	
	completeCartesianVector();
    }
    
    public void updateComponents(double dX, double dY, double dZ)
    {
	setX(getX() + dX);
	setY(getY() + dY);
	setZ(getZ() + dZ);
	
	completeCartesianVector();
    }
    
    public void updateComponents(Vector dV)
    {
	setX(getX() + dV.getX());
	setY(getY() + dV.getY());
	setZ(getZ() + dV.getZ());
	
	completeCartesianVector();
    }
    
    public void completeCartesianVector()
    {    
	if(isZero())
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
    
    public void completePolarVector()
    {
	setX(magnitude * Math.sqrt(1.0 / ( 1.0 + Math.tan(theta) * Math.tan(theta) + Math.tan(phi) * Math.tan(phi))));
	setY(Math.tan(theta) * X);
	setZ(Math.tan(phi) * X);
    }
	
    public double zeroIfTooSmall(double value)
    {
	if(value < TOOSMALL && value > -TOOSMALL)
	    value = 0;
	    
	return value;
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
    
    ///
    public void setToVectorAddition(Vector vector)
    {
	setX(getX() + vector.getX());
	setY(getY() + vector.getY());
	setZ(getZ() + vector.getZ());
	
	completeCartesianVector();
    }
    
    ///
    public void setToVectorAddition(Vector vector, double factor)
    {
	setX(getX() + vector.getX() * factor);
	setY(getY() + vector.getY() * factor);
	setZ(getZ() + vector.getZ() * factor);
	
	completeCartesianVector();
    }
    
    ///
    public void setToVectorAddition(Vector vector1, Vector vector2, double factor2)
    {
	setX(vector1.getX() + vector2.getX() * factor2);
	setY(vector1.getY() + vector2.getY() * factor2);
	setZ(vector1.getZ() + vector2.getZ() * factor2);
	
	completeCartesianVector();          
    }    
    
    ///
    public void setToVectorAddition(Vector vector1, double factor1, Vector vector2, double factor2)
    {
	setX(vector1.getX() * factor1 + vector2.getX() * factor2);
	setY(vector1.getY() * factor1 + vector2.getY() * factor2);
	setZ(vector1.getZ() * factor1 + vector2.getZ() * factor2);
	
	completeCartesianVector();          
    }    
    
    public Vector substractVector(Vector vector)
    {
	double newX = getX() - vector.getX();
	double newY = getY() - vector.getY();
	double newZ = getZ() - vector.getZ();
	
	return (new CartesianVector(newX, newY, newZ));
    }
    
    ///
    public void setToVectorSubstraction(Vector vector)
    {
	setX(getX() - vector.getX());
	setY(getY() - vector.getY());
	setZ(getZ() - vector.getZ());
	
	completeCartesianVector();           
    }
    
    ///
    public void setToVectorSubstraction(Vector vector, double factor)
    {
	setX(getX() - vector.getX() * factor);
	setY(getY() - vector.getY() * factor);
	setZ(getZ() - vector.getZ() * factor);
	
	completeCartesianVector();          
    }
    
    ///
    public void setToVectorSubstraction(Vector vector1, Vector vector2)
    {
	setX(vector1.getX() - vector2.getX());
	setY(vector1.getY() - vector2.getY());
	setZ(vector1.getZ() - vector2.getZ());
	
	completeCartesianVector();          
    }    
    
    ///
    public void setToVectorSubstraction(Vector vector1, Vector vector2, double factor2)
    {
	setX(vector1.getX() - vector2.getX() * factor2);
	setY(vector1.getY() - vector2.getY() * factor2);
	setZ(vector1.getZ() - vector2.getZ() * factor2);
	
	completeCartesianVector();          
    }    
    
    ///
    public void setToVectorSubstraction(Vector vector1, double factor1, Vector vector2, double factor2)
    {
	setX(vector1.getX() * factor1 - vector2.getX() * factor2);
	setY(vector1.getY() * factor1 - vector2.getY() * factor2);
	setZ(vector1.getZ() * factor1 - vector2.getZ() * factor2);
	
	completeCartesianVector();          
    }    
    
    public double dotProduct(Vector vector)
    {
	double productX = getX() * vector.getX();
	double productY = getY() * vector.getY();
	double productZ = getZ() * vector.getZ();
	
	return (productX + productY + productZ);
    }
    
    ///
    public double dotProduct(Vector vector1, Vector vector2)
    {
	double productX = vector1.getX() * vector2.getX();
	double productY = vector1.getY() * vector2.getY();
	double productZ = vector1.getZ() * vector2.getZ();
	
	return (productX + productY + productZ);
    }
    
    public double separatingAngle(Vector vector)
    {
	return Math.acos(dotProduct(vector) / getMagnitude() / vector.getMagnitude());
    }
    
    ///
    public double separatingAngle(Vector vector1, Vector vector2)
    {
	return Math.acos(dotProduct(vector1, vector2) / vector1.getMagnitude() / vector2.getMagnitude());
    }
    
    public Vector crossProduct(Vector vector)
    {
	double partX = getY() * vector.getZ() - getZ() * vector.getY();
	double partY = getZ() * vector.getX() - getX() * vector.getZ();
	double partZ = getX() * vector.getY() - getY() * vector.getX();
	
	return (new CartesianVector(partX, partY, partZ));
    } 
    
    ///
    public void setToCrossProduct(Vector vector)
    {
	double partX = getY() * vector.getZ() - getZ() * vector.getY();
	double partY = getZ() * vector.getX() - getX() * vector.getZ();
	double partZ = getX() * vector.getY() - getY() * vector.getX();
		
	setX(partX);
	setY(partY);
	setZ(partZ);
	
	completeCartesianVector();  
    }
    
    ///
    public void setToCrossProduct(Vector vector1, Vector vector2)
    {
	double partX = vector1.getY() * vector2.getZ() - vector1.getZ() * vector2.getY();
	double partY = vector1.getZ() * vector2.getX() - vector1.getX() * vector2.getZ();
	double partZ = vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX();
		
	setX(partX);
	setY(partY);
	setZ(partZ);
	
	completeCartesianVector();  
    }
    
    public Vector scalarProduct(double factor)
    {
	return (new CartesianVector(getX() * factor, getY() * factor, getZ() * factor));
    }
    
    ///
    public void setToScalarProduct(double factor)
    {        
	setX(getX() * factor);
	setY(getY() * factor);
	setZ(getZ() * factor);
	
	completeCartesianVector();   
    }
    
    ///
    public void setToScalarProduct(Vector vector, double factor)
    {
	setX(vector.getX() * factor);
	setY(vector.getY() * factor);
	setZ(vector.getZ() * factor);
	
	completeCartesianVector();   
    }
    
    public double getMultiplierA(Vector vector1, Vector vector2)
    {
	return (getX() * vector2.getY() - getY() * vector2.getX()) / (vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX());
    }
    
    public double getMultiplierB(Vector vector1, Vector vector2)
    {
	return (getX() * vector1.getY() - getY() * vector1.getX()) / (vector2.getX() * vector1.getY() - vector2.getY() * vector1.getX());
    }
    
    public double getMultiplier(Vector vector)
    {
	if(equalsDirection(vector))
	{
	    return getMagnitude()/vector.getMagnitude();
	}
	else if(equalsOrientation(vector))
	{
	    return -getMagnitude()/vector.getMagnitude();
	}
	else
	    return 0;
    }
    
    public Vector reflectVector(double X, double Y, double Z)
    {
	if(X != 0)
	    X = -getX();
	else
	    X = getX();
	    
	if(Y != 0)
	    Y = -getY();
	else
	    Y = getY();
	    
	if(Z != 0)
	    Z = -getZ();
	else
	    Z = getZ();
	    
	return (new CartesianVector(X, Y, Z));
    }
    
    ///
    public void setToReflectedVector(boolean X, boolean Y, boolean Z)
    {
	if(X)
	    setX(-getX());
	    
	if(Y)
	    setY(-getY());
	    
	if(Z)
	    setZ(-getZ());
	    
	if(X || Y || Z)
	{    
	    completeCartesianVector();
	}
    }

    ///
    public void setToVector(Vector vector)
    {
	setX(vector.getX());
	setY(vector.getY());
	setZ(vector.getZ());
	
	completeCartesianVector();  
    }
    
    ///
    public void setToComponents(double X, double Y, double Z)
    {
	setX(X);
	setY(Y);
	setZ(Z);
	
	completeCartesianVector();  
    }
    
    ///
    public void setToVector(Vector vector, double factor)
    {
	setX(vector.getX() * factor);
	setY(vector.getY() * factor);
	setZ(vector.getZ() * factor);
	
	completeCartesianVector();
    }
    
    ///
    public void setToUnitVector()
    {
	setToScalarProduct(1.0 / getMagnitude());
    }
    
    public void setToAbsoluteValue()
    {
	setX(Math.abs(getX()));
	setY(Math.abs(getY()));
	setZ(Math.abs(getZ()));
	completeCartesianVector();
    }
    
    public void setToUnitVector(Vector vector)
    {
	setToVector(vector);
	setToUnitVector();
    }
    
    public boolean isZero()
    {
	return (getX() == 0 && getY() == 0 && getZ() == 0);
    }
    
    public boolean isUnit()
    {
	return (getMagnitude() == 1);
    }
    
    //Obligatory
    public boolean equals(Vector vector)
    {
	boolean checkX = getX() == vector.getX();
	boolean checkY = getY() == vector.getY();
	boolean checkZ = getZ() == vector.getZ();
	
	return (checkX && checkY && checkZ);
    }
    
    public boolean equalsDirection(Vector vector)
    {
	boolean checkTheta = getTheta() == vector.getTheta();
	boolean checkPhi = getPhi() == vector.getPhi();
	
	return (checkTheta && checkPhi);
    }
    
    public boolean equalsOrientation(Vector vector)
    {
	Vector reverse = vector.reflectVector(1, 1, 1);
	
	return (equalsDirection(vector) || equalsDirection(reverse));
    
    }
    
    public Vector copy()
    {
	return (new CartesianVector(getX(), getY(), getZ()));
    }
    
    ///
    public Vector transferReference()
    {
	return this;
    }
    
    public String toString()
    {
	String description = "Vector of magnitude " + getMagnitude() + 
			     ", an inclination of " + getTheta() + 
			     ", a rotation of " + getPhi() + "\n" +
			     "X component : " + getX() +
			     ", Y component : " + getY() + 
			     ", Z component : " + getZ();
			     
	return description;
    }
    
}
