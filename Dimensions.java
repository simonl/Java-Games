package javaapps.physicsobject;

public class Dimensions
{
    //Instance variables
    double X, Y, Z;
    double R;
    boolean isASphere;

    Vector size;

    //Constructors
    public Dimensions()
    {
	this(0, 0, 0);
    }

    public Dimensions(double X)
    {
		this(X, 0, 0);
    }

    public Dimensions(double X, double Y)
    {
		this(X, Y, 0);
    }

    public Dimensions(double X, double Y, double Z)
    {
		this.X = X;
		this.Y = Y;
		this.Z = Z;

		this.R = Math.sqrt(X*X + Y*Y + Z*Z);
		this.isASphere = false;
		
		this.size = new CartesianVector(X, Y, Z);
    }

    
    //GETTERS
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
	Vector orientation = separation.copy();
	separation.setToAbsoluteValue();
	
	

	if(isASphere() && object2.isASphere())
	{
	    boolean checkR =   separation.getMagnitude() <= getRadius() + object2.getRadius() &&
				separation.getMagnitude() >= Math.abs(getRadius() - object2.getRadius());
	    return checkR;
	}
	
	    boolean withinX = separation.getX() >= Math.abs(getX() - object2.getR());
	    boolean outsideX = separation.getX() <= getX() + object2.getR();
	    boolean checkX = withinX && outsideX && (separation.getY() <= getY()) && (separation.getZ() <= getZ());
	    
	    
	    boolean withinY = separation.getY() >= Math.abs(getY() - object2.getR());
	    boolean outsideY = separation.getY() <= getY() + object2.getR();
	    boolean checkY = withinY && outsideY && (separation.getX() <= getX()) && (separation.getZ() <= getZ());
	    
	    boolean withinZ = separation.getZ() >= Math.abs(getZ() - object2.getR());
	    boolean outsideZ = separation.getZ() <= getZ() + object2.getR();
	    boolean checkZ = withinZ && outsideZ && (separation.getX() <= getX()) && (separation.getY() <= getY());
	    
	    return checkX || checkY || checkZ;
	
	/*
	if(!isASphere() && !object2.isASphere())
	{
	    boolean checkX =  separation.getX() <= getX() + object2.getX() &&
				separation.getX() >= Math.abs(getX() - object2.getX());
	    boolean checkY =  separation.getY() <= getY() + object2.getY() &&
				separation.getY() >= Math.abs(getY() + object2.getY());
	    boolean checkZ =  separation.getZ() <= getZ() + object2.getZ() &&
				separation.getZ() >= Math.abs(getZ() - object2.getZ());

	    boolean checkBound = checkX && checkY && checkZ;
	    
	    return checkBound;
	}
		
	if(!isASphere() && object2.isASphere())
	{
	    boolean checkX =  separation.getX() <= getX() + object2.getR() &&
				separation.getX() >= Math.abs(getX() - object2.getR());
	    boolean checkY =  separation.getY() <= getY() + object2.getR() &&
				separation.getY() >= Math.abs(getY() + object2.getR());
	    boolean checkZ =  separation.getZ() <= getZ() + object2.getR() &&
				separation.getZ() >= Math.abs(getZ() - object2.getR());

	    boolean checkBound = checkX && checkY && checkZ;
	    
	    if(checkBound)
	    {
		Vector[] corners = {    new CartesianVector(getX(), getY(), getZ()),
					new CartesianVector(getX(), getY(), -getZ()),
					new CartesianVector(getX(), -getY(), getZ()),
					new CartesianVector(getX(), -getY(), -getZ()),
					new CartesianVector(-getX(), getY(), getZ()),
					new CartesianVector(-getX(), getY(), -getZ()),
					new CartesianVector(-getX(), -getY(), getZ()),
					new CartesianVector(-getX(), -getY(), -getZ()),
				    };
		for(int i = 0; i < corners.length; i++)
		{
		    corners[i].setToVectorSubstraction(orientation, corners[i]);
		    boolean checkCorner = corners[i].getMagnitude() <= object2.getRadius();
		    
		    if(checkCorner)
			return true;
		}
	    }
	    return false;
	}
	
	else
	{
	    
	    boolean checkX =  separation.getX() <= getR() + object2.getX() &&
				separation.getX() >= Math.abs(getR() - object2.getX());
	    boolean checkY =  separation.getY() <= getR() + object2.getY() &&
				separation.getY() >= Math.abs(getR() + object2.getY());
	    boolean checkZ =  separation.getZ() <= getR() + object2.getZ() &&
				separation.getZ() >= Math.abs(getR() - object2.getZ());

	    boolean checkBound = checkX && checkY && checkZ;
	    
	    if(checkBound)
	    {
		orientation.setToReflectedVector(true, true, true);
		
		Vector[] corners = {    new CartesianVector(object2.getX(), object2.getY(), object2.getZ()),
					new CartesianVector(object2.getX(), object2.getY(), -object2.getZ()),
					new CartesianVector(object2.getX(), -object2.getY(), object2.getZ()),
					new CartesianVector(object2.getX(), -object2.getY(), -object2.getZ()),
					new CartesianVector(-object2.getX(), object2.getY(), object2.getZ()),
					new CartesianVector(-object2.getX(), object2.getY(), -object2.getZ()),
					new CartesianVector(-object2.getX(), -object2.getY(), object2.getZ()),
					new CartesianVector(-object2.getX(), -object2.getY(), -object2.getZ()),
				    };
		for(int i = 0; i < corners.length; i++)
		{
		    corners[i].setToVectorSubstraction(orientation, corners[i]);
		    boolean checkCorner = corners[i].getMagnitude() <= getRadius();
		    
		    if(checkCorner)
			return true;
		}
	    } 
	    return false;
	}
	*/
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
