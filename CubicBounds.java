package javaapps.physicsobject;

public class CubicBounds extends Bounds
{
    private double minX, maxX;
    private double minY, maxY;
    private double minZ, maxZ;
    
    public CubicBounds(double bound)
    {
	setMinX(-bound);
	setMaxX(bound);
	
	setMinY(-bound);
	setMaxY(bound);
	
	setMinZ(-bound);
	setMaxZ(bound);
    }        
}
