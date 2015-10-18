package javaapps.physicsobject;

public class Bounds
{
    private double minX, maxX;
    private double minY, maxY;
    private double minZ, maxZ;

    public Bounds()
    {
    
    }
    
    public Bounds(double minX, double maxX)
    {
	setMinX(minX);
	setMaxX(maxX);
    }
    
    public Bounds(double minX, double maxX, double minY, double maxY)
    {
	setMinX(minX);
	setMaxX(maxX);
	
	setMinY(minY);
	setMaxY(maxY);
    }
    
    public Bounds(double minX, double maxX, double minY, double maxY, double minZ, double maxZ)
    {
	setMinX(minX);
	setMaxX(maxX);
	
	setMinY(minY);
	setMaxY(maxY);
	
	setMinZ(minZ);
	setMaxZ(maxZ);
    }

    
    //Getters
    public double getMinX()
    {
	return minX;
    }

    public double getMinY()
    {
	return minY;
    }
    
    public double getMinZ()
    {
	return minZ;
    }
    
    public double getMaxX()
    {
	return minX;
    }
    
    public double getMaxY()
    {
	return maxY;
    }
    
    public double getMaxZ()
    {
	return maxZ;
    }
    
    //Setters
    public void setMinX(double minX)
    {
	this.minX = minX;
    }
    
    public void setMaxX(double maxX)
    {
	this.maxX = maxX;
    }
    
    public void setMinY(double minY)
    {
	this.minY = minY;
    }
    
    public void setMaxY(double maxY)
    {
	this.maxY = maxY;
    }
    
    public void setMinZ(double minZ)
    {
	this.minX = minX;
    }
    
    public void setMaxZ(double maxZ)
    {
	this.maxZ = maxZ;
    }
    
}
