package javaapps.physicsobject;

public class VectorQ
{
    //public static final VectorQ UNIT_X = new VectorQ(Quantity.ONE, new Quantity(), new Quantity());
    //public static final VectorQ UNIT_Y = new VectorQ(new Quantity(), Quantity.ONE, new Quantity());
    //public static final VectorQ UNIT_Z = new VectorQ(new Quantity(), new Quantity(), Quantity.ONE);

    public static final Quantity TOO_SMALL = new Quantity(1.0E-8);

    private final Quantity x, y, z;

    //CONSTRUCTORS
    public VectorQ() throws Exception
    {
	this(Units.DIMENSIONLESS);
    }

    public VectorQ(Units u) throws Exception
    {
	this(0.0, 0.0, 0.0, u);
    }

    public VectorQ(Quantity x, Quantity y, Quantity z) throws Exception
    {
	/*
	if(!x.isCompatible(y))
	    throw new IncompatibleUnitsException(x.getUnits(), y.getUnits());
	else if(!y.isCompatible(z))
	    throw new IncompatibleUnitsException(y.getUnits(), z.getUnits());
	*/

	this.x = new Quantity(x);
	this.y = new Quantity(y);
	this.z = new Quantity(z);

	if(!this.isConsistent())
	    throw new IncompatibleVectorUnitsException(x.getUnits(), y.getUnits(), z.getUnits());
	//
	// Not appropriate : Not enough info about the 3 units
	/*
	if(x.abs().isLesserThan(TOO_SMALL))
	    x.assign(new Quantity(0.0, x.getUnits()));

	if(y.abs().isLesserThan(TOO_SMALL))
	    y.assign(new Quantity(0.0, y.getUnits()));

	if(z.abs().isLesserThan(TOO_SMALL))
	    z.assign(new Quantity(0.0, z.getUnits())); */
    }

    public VectorQ(Quantity r) throws Exception
    {
	this(r.getValue(), 0.0, 0.0, r.getUnits());
    }

    public VectorQ(double x, double y, double z, Units u) throws Exception
    {
	this(new Quantity(x, u), new Quantity(y, u), new Quantity(z, u));
    }

    public VectorQ(Vector v, Units u) throws Exception
    {
	this(v.getX(), v.getY(), v.getZ(), u);
    }

    public VectorQ(VectorQ vQ) throws Exception
    {
	this(vQ.getX(), vQ.getY(), vQ.getZ());
    }

    //GETTERS
    public Quantity getX()
    {
	return this.x;
    }

    public Quantity getY()
    {
	return this.y;
    }

    public Quantity getZ()
    {
	return this.z;
    }

    public Quantity getNorm() throws Exception
    {
	final Quantity TWO = Quantity.TWO;
	final Quantity xSq = this.x.pow(TWO);
	final Quantity ySq = this.y.pow(TWO);
	final Quantity zSq = this.z.pow(TWO);

	return (xSq.plus(ySq).plus(zSq)).root(TWO);
    }

    public Units getUnits() throws Exception
    {
	return this.getNorm().getUnits();
    }

    //SETTERS
    public void setX(Quantity x) throws Exception
    {
	    this.x.assign(x);
    }

    public void setY(Quantity y) throws Exception
    {
	    this.y.assign(y);
    }

    public void setZ(Quantity z) throws Exception
    {
	    this.z.assign(z);
    }

    //CUSTOM
    public VectorQ times(Quantity q) throws Exception
    {
	return new VectorQ(this.x.times(q), y.times(q), z.times(q));
    }

    public Quantity times(VectorQ vQ) throws Exception
    {
	return this.x.times(vQ.getX()).plus(this.y.times(vQ.getY())).plus(this.z.times(vQ.getZ()));
    }

    public VectorQ div(Quantity q) throws Exception
    {
	return new VectorQ(this.x.div(q), y.div(q), z.div(q));
    }

    public VectorQ plus(VectorQ vQ) throws Exception
    {
	return new VectorQ(this.x.plus(vQ.getX()), this.y.plus(vQ.getY()), this.z.plus(vQ.getZ()));
    }

    public VectorQ minus(VectorQ vQ) throws Exception
    {
	return this.plus(vQ.negative());
    }

    public VectorQ negative() throws Exception
    {
	return new VectorQ(this.x.negative(), this.y.negative(), this.z.negative());
    }

    public VectorQ reflect(boolean x, boolean y, boolean z) throws Exception
    {
	return new VectorQ( (x ? this.x.negative() : this.x),
			    (y ? this.y.negative() : this.y),
			    (z ? this.z.negative() : this.z)    );
    }

    public VectorQ getUnitVector() throws Exception
    {
	return this.div(this.getNorm());
    }

    public VectorQ getDimensionless() throws Exception
    {
	return new VectorQ(this.getX().getValue(), this.getY().getValue(), this.getZ().getValue(), Units.DIMENSIONLESS);
    }

    public VectorQ getProjectionAlong(VectorQ base) throws Exception
    {
	return base.times(this.times(base).div(base.getNorm().pow(Quantity.TWO)));
    }

    public VectorQ getProjectionOnPlane(VectorQ normal) throws Exception
    {
	return this.minus(getProjectionAlong(normal));
    }

    public VectorQ reflectThroughNormal(VectorQ normal) throws Exception
    {
	return this.getProjectionAlong(normal).times(Quantity.TWO).minus(this);
    }

    public VectorQ reflectThroughPlane(VectorQ normal) throws Exception
    {
	return this.minus(this.getProjectionAlong(normal).times(Quantity.TWO));
    }

    public VectorQ abs() throws Exception
    {
	return new VectorQ(this.x.abs(), this.y.abs(), this.z.abs());
    }

    //TESTS
    public boolean equals(VectorQ vQ)
    {
	return this.sameX(vQ) && this.sameY(vQ) && this.sameZ(vQ);
    }

    public boolean sameX(VectorQ vQ)
    {
	return this.x.equals(vQ.getX());
    }

    public boolean sameY(VectorQ vQ)
    {
	return this.y.equals(vQ.getY());
    }

    public boolean sameZ(VectorQ vQ)
    {
	return this.z.equals(vQ.getZ());
    }

    public boolean isConsistent()
    {
	if(!x.isCompatible(y))
	    return false;
	else if(!y.isCompatible(z))
	    return false;
	return true;
    }

    public boolean isStrictlySmallerThan(VectorQ vQ) throws Exception
    {
	VectorQ thisAbs = this.abs();
	VectorQ otherAbs = vQ.abs();

	return  thisAbs.getX().isLesserThan(otherAbs.getX()) &&
		thisAbs.getY().isLesserThan(otherAbs.getY()) &&
		thisAbs.getZ().isLesserThan(otherAbs.getZ());

    }

    public boolean isStrictlyGreaterThan(VectorQ vQ) throws Exception
    {
	VectorQ thisAbs = this.abs();
	VectorQ otherAbs = vQ.abs();

	return  thisAbs.getX().isGreaterThan(otherAbs.getX()) &&
		thisAbs.getY().isGreaterThan(otherAbs.getY()) &&
		thisAbs.getZ().isGreaterThan(otherAbs.getZ());

    }



    //UTILITIES
    public VectorQ copy() throws Exception
    {
	return new VectorQ(this);
    }

    public void assign(VectorQ vQ) throws Exception
    {
	this.x.assign(vQ.getX());
	this.y.assign(vQ.getY());
	this.z.assign(vQ.getZ());
    }

    public void assign(Quantity x, Quantity y, Quantity z)
    {
		this.x.assign(x);
		this.y.assign(y);
		this.z.assign(z);
	}

    public String toString()
    {
	return this.x + ", " + this.y + ", " + this.z;
    }

    public static void main(String[] args) throws Exception
    {
	System.out.println(new VectorQ(Units.SPEED).times(new Quantity()));
	//System.out.println(new VectorQ(Units.SPEED).plus(UNIT_X.times(new Quantity(Units.SPEED))));
	System.out.println(new VectorQ(Units.SPEED).getNorm());
	VectorQ diag = new VectorQ(1, 1, 0, Units.LENGTH);
	VectorQ vert = new VectorQ(0, 1, 0, Units.LENGTH);
	System.out.println(diag.reflectThroughNormal(vert));
	System.out.println(diag.reflectThroughPlane(vert));
	System.out.println(vert.reflectThroughNormal(diag));
	System.out.println(new VectorQ(new Quantity(Units.LENGTH), new Quantity(Units.LENGTH), new Quantity()));
    }
}
