package javaapps.physicsobject;

public class SizeQ
{
    private final VectorQ dimensions;
    private boolean isASphere = true;

    //CONSTRUCTORS
    public SizeQ() throws Exception
    {
		dimensions = new VectorQ(Units.LENGTH);
    }

    public SizeQ(Quantity q) throws Exception
    {
		this(new VectorQ(q), true);
    }

    public SizeQ(VectorQ vQ, boolean isASphere) throws Exception
    {
		this();
		dimensions.assign(vQ.abs());
		isASphere = isASphere;
    }

    public SizeQ(VectorQ vQ) throws Exception
    {
		this(vQ, true);
    }

    public SizeQ(SizeQ sQ) throws Exception
    {
		this(sQ.getDimensions(), sQ.isASphere());
    }

    //GETTERS
    public VectorQ getDimensions()
    {
		return this.dimensions;
    }

    public Quantity getRadius() throws Exception
    {
		return this.dimensions.getNorm();
    }

    public boolean isASphere()
    {
		return this.isASphere;
    }

    //CUSTOM METHODS

    /*                                  THE COLLISION DETECTION SYSTEM
     *
     *
     *                                          AND IT WORKS!
     *
     *
     */
    public VectorQ determineIntersection(SizeQ other, VectorQ separation) throws Exception
    {
		if(this.isASphere() && other.isASphere())
		{
			//IF Sphere && Sphere
			final boolean rIntersect =  separation.getNorm().isLesserThan(this.getRadius().plus(other.getRadius())) &&
						separation.getNorm().isGreaterThan(this.getRadius().minus(other.getRadius()).abs());

			if(rIntersect)
				return separation;

		}
		else if(!this.isASphere() && !other.isASphere())
		{
			//IF Cube && Cube
			final VectorQ add = this.dimensions.plus(other.getDimensions());
			final VectorQ sub = this.dimensions.minus(other.getDimensions()).abs();

			final boolean xIntersect =  separation.getX().isLesserThan(add.getX()) &&
						separation.getX().isGreaterThan(sub.getX());
			final boolean yIntersect =  separation.getY().isLesserThan(add.getY()) &&
						separation.getY().isGreaterThan(sub.getY());
			final boolean zIntersect =  separation.getX().isLesserThan(add.getX()) &&
						separation.getX().isGreaterThan(sub.getX());

			if(xIntersect)
				return new VectorQ(1, 0, 0, Units.LENGTH);

			if(yIntersect)
				return new VectorQ(0, 1, 0, Units.LENGTH);

			if(zIntersect)
				return new VectorQ(0, 0, 1, Units.LENGTH);

		}
		else if(this.isASphere() && !other.isASphere())
		{
			//IF Sphere && Cube
			other.determineIntersection(this, separation.negative());
		}
		else if(separation.getNorm().isLesserThan(this.dimensions.getNorm().plus(other.getRadius())))
		{
			//IF Cube and Sphere
			final boolean facesX = separation.getY().isLesserThan(this.dimensions.getY()) && separation.getZ().isLesserThan(this.dimensions.getZ());
			final boolean facesY = separation.getX().isLesserThan(this.dimensions.getX()) && separation.getZ().isLesserThan(this.dimensions.getZ());
			final boolean facesZ = separation.getX().isLesserThan(this.dimensions.getX()) && separation.getY().isLesserThan(this.dimensions.getY());

			if(facesX)
			{
				final boolean touchesFaceX =  separation.getX().isLesserThan(this.dimensions.getX().plus(other.getRadius())) &&
							 				  separation.getX().isGreaterThan(this.dimensions.getX().minus(other.getRadius()).abs());

				if(touchesFaceX)
					return new VectorQ(1, 0, 0, Units.LENGTH);

			}
			else if(facesY)
			{
				final boolean touchesFaceY =  separation.getY().isLesserThan(this.dimensions.getY().plus(other.getRadius())) &&
							  				  separation.getY().isGreaterThan(this.dimensions.getY().minus(other.getRadius()).abs());

				if(touchesFaceY)
					return new VectorQ(0, 1, 0, Units.LENGTH);

			}
			else if(facesZ)
			{
				final boolean touchesFaceZ =  separation.getZ().isLesserThan(this.dimensions.getZ().plus(other.getRadius())) &&
							  separation.getZ().isGreaterThan(this.dimensions.getZ().minus(other.getRadius()).abs());

				if(touchesFaceZ)
					return new VectorQ(0, 0, 1, Units.LENGTH);

			}
			else
			{
				//COMPLICATED MESS
				final VectorQ fromCorner = new VectorQ(Units.LENGTH);

				final VectorQ corner = new VectorQ(Units.LENGTH);
				final Quantity cornerX = new Quantity(Units.LENGTH);
				final Quantity cornerY = new Quantity(Units.LENGTH);
				final Quantity cornerZ = new Quantity(Units.LENGTH);

				for(int i = 0; i < 8; i++)
				{
					cornerX.assign(i % 4 == 0 ? this.dimensions.getX() : this.dimensions.getX().negative());
					cornerY.assign((i/2) % 2 == 0 ? this.dimensions.getY() : this.dimensions.getY().negative());
					cornerZ.assign(i % 2 == 0 ? this.dimensions.getZ() : this.dimensions.getZ().negative());

					corner.assign(cornerX, cornerY, cornerZ);

					fromCorner.assign(separation.minus(corner));

					if(fromCorner.getNorm().isLesserThan(other.getRadius()))
						return fromCorner;

				}
			}
		}

		return null;
    }
    /*                                      END OF THE CDS (Collision Detection System)
     *
     *
     *
     *                                                  AND IT WORKED!
     *
     *
     */

    //TESTS

    //UTILITITES
    public SizeQ copy() throws Exception
    {
		return new SizeQ(this);
    }

    public void assign(SizeQ sQ) throws Exception
    {
		this.dimensions.assign(sQ.getDimensions());
		this.isASphere = sQ.isASphere();
    }

    public String toString() throws Exception
    {
		return 78 + "Size : " + (this.isASphere() ? "Sphere of R=" + this.getRadius() : "Solid of sides : " + this.dimensions);
    }

    public static void main(String[] args) throws Exception
    {
		System.out.println(new SizeQ());
    }
}
