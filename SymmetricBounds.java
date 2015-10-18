package javaapps.physicsobject;

public class SymmetricBounds extends Bounds
{
    private double minX, maxX;
    private double minY, maxY;
    private double minZ, maxZ;
    
    public SymmetricBounds(double boundX)
    {
	setMinX(-boundX);
	setMaxX(boundX);
    }
    
    public SymmetricBounds(double boundX, double boundY)
    {
	setMinX(-boundX);
	setMaxX(boundX);
	
	setMinY(-boundY);
	setMaxY(boundY);
    }
    
    public SymmetricBounds(double boundX, double boundY, double boundZ)
    {
	setMinX(-boundX);
	setMaxX(boundX);
	
	setMinY(-boundY);
	setMaxY(boundY);
	
	setMinZ(-boundZ);
	setMaxZ(boundZ);
    }        
}
