package boubouworld;

public class Dimensions
{
    //Instance variables
    private double width, height;
    private int form;
	
    
    //Constructors
    public Dimensions()
    {
    
    }
    
    public Dimensions(double width, double height, int form)
    {        
	this.height = height;
	this.width = width;
	this.form = form;
    }
    
    public double getWidth()
    {
	return width;
    }
    
    public double getHeight()
    {
	return height;
    }
    
    public void setHeight(double height)
    {
	this.height = height;
    }
    
    public void setWidth(double width)
    {
	this.width = width;
    }
    
    //Custom Methods
    public void checkIfOverlap(Coordinates thisPoint, Coordinates otherPoint, Dimensions otherSize)
    {
	
    }
    
}
