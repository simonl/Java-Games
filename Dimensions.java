package boubouworld2;

public class Dimensions
{
    //Instance variables
    double X, Y, Z;
    double R;
    int form;
	
    
    //Constructors
    public Dimensions()
    {
    
    }
    
    public Dimensions(double X)
    {        
	this.X = X;
	
	this.form = 1;
    }
    
    public Dimensions(double X, double Y)
    {        
	this.X = X;
	this.Y = Y;
	
	this.form = 1;
    }
    
    public Dimensions(double X, double Y, int Z)
    {        
	this.X = X;
	this.Y = Y;
	this.Z = Z;
	
	this.form = 1;
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
    
    
    //Custom Methods
    public boolean checkIfIntersect(Dimensions object2, Vector separation)
    {
	boolean answer;
	boolean checkX;
	boolean checkY;
	boolean checkZ;
	
	if(form == 0 && object2.form == 0)
	{
	    answer = Math.abs(separation.getMagnitude()) < getRadius() + object2.getRadius();
		
		    // System.out.println("        CHECK INTERSECTION");
		    // System.out.println("    " + separation);
		    // System.out.println("    " + answer);
		    // System.out.println("        END OF CHECK INTERSECTION");
	}
	else 
	{
	    if(form != 0 && object2.form != 0)
	    {
		checkX =  Math.abs(separation.getX()) < getX() + object2.getX();
		checkY =  Math.abs(separation.getY()) < getY() + object2.getY();
		checkZ =  Math.abs(separation.getZ()) < getZ() + object2.getZ();
	
		answer = checkX || checkY || checkZ;
	    }
	    else 
	    {
		if(form == 0)
		{
		    answer = false;
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
	if(form == 0)
	    return getRadius() * getRadius() * 4.0 * Math.PI;
	else
	    return 2.0*(getX()*getY() + getX()*getZ() + getY()*getZ());
    }
    
    public double getVolume()
    {
	if(form == 0)
	    return 4.0/3.0 * Math.PI * Math.pow(getRadius(), 3.0);
	else
	    return getX()*getY()*getZ();
    }
    
    
}
