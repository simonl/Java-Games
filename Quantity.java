package javaapps.physicsobject;

public class Quantity
{
	/* Commonly used values */
    public static final Quantity ZERO = new Quantity();
    public static final Quantity ONE = new Quantity(1.0);
    public static final Quantity TWO = new Quantity(2.0);

    /* Basic dimensional values */
	public static final Quantity METER = new Quantity(1.0, Units.LENGTH);
	public static final Quantity SECOND = new Quantity(1.0, Units.TIME);
	public static final Quantity KILOGRAM = new Quantity(1.0, Units.MASS);
	public static final Quantity KELVIN = new Quantity(1.0, Units.TEMPERATURE);
	public static final Quantity MOLE = new Quantity(1.0, Units.AMOUNT);
	public static final Quantity KILOPASCAL = new Quantity(1.0, Units.PRESSURE);
	public static final Quantity COULOMB = new Quantity(1.0, Units.CHARGE);

	public static final Quantity NEWTON = new Quantity(1.0, Units.FORCE);
	public static final Quantity JOULE = new Quantity(1.0, Units.ENERGY);
	public static final Quantity AMPERE = new Quantity(1.0, Units.CURRENT);
	public static final Quantity VOLT = new Quantity(1.0, Units.POTENTIAL);
	public static final Quantity WATT = new Quantity(1.0, Units.POWER);
	public static final Quantity HERTZ = new Quantity(1.0, Units.FREQUENCY);
	public static final Quantity OHM = new Quantity(1.0, Units.RESISTANCE);        

    /* Physical Constants */
    public static final Quantity G      = new Quantity(6.67428E-11, Units.FORCE.times(Units.LENGTH.div(Units.MASS).pow(2)));
    public static final Quantity H      = new Quantity(6.626068E-34, Units.ACTION);
    public static final Quantity C      = new Quantity(299792458, Units.SPEED);
    public static final Quantity ELEMENTARY_CHARGE
										= new Quantity(1.60217646E-19, Units.CHARGE);
    public static final Quantity Ke     = new Quantity(8.854187817E-12, Units.RESISTANCE.times(Units.TIME));
    public static final Quantity K      = new Quantity(1.3806504E-23, Units.ENERGY.div(Units.TEMPERATURE));
    public static final Quantity A      = new Quantity(6.02214179E23, Units.AMOUNT.inverse());

    private double value;
    private final Units units;

    //CONSTRUCTORS
    public Quantity()
    {
	this(0.0, Units.DIMENSIONLESS);
    }

    public Quantity(double value)
    {
	this(value, Units.DIMENSIONLESS);
    }

    public Quantity(Units u)
    {
	this(1.0, u);
    }

    public Quantity(double value, Units u)
    {
	this.value = value;
	this.units = new Units(u);
    }

    public Quantity(Quantity q)
    {
	this(q.getValue(), q.getUnits());
    }

    //GETTERS
    public double getValue()
    {
	return this.value;
    }

    public Units getUnits()
    {
	return this.units;
    }

    //CUSTOM
    public Quantity times(Quantity q)
    {
	return new Quantity(this.value * q.getValue(), this.units.times(q.getUnits()));
    }

    public Quantity div(Quantity q)
    {
	return new Quantity(this.value / q.getValue(), this.units.div(q.getUnits()));
    }

    public Quantity plus(Quantity q) throws Exception
    {
	return new Quantity(this.value + q.getValue(), this.units.plus(q.getUnits()));
    }

    public Quantity minus(Quantity q) throws Exception
    {
	return this.plus(q.negative());
    }

    public Quantity negative()
    {
	return new Quantity(-this.value, this.units);
    }

    public Quantity pow(Quantity q) throws Exception
    {
	if(q.isDimensionless())
	    return new Quantity(Math.pow(this.value, (int)q.getValue()), this.units.pow((int)q.getValue()));
	else
	    throw new IncompatibleUnitsException(Units.DIMENSIONLESS, q.getUnits());
    }

    public Quantity root(Quantity q) throws Exception
    {
	if(q.isDimensionless())
	    return new Quantity(Math.pow(this.value, 1.0/(int)q.getValue()), this.units.root((int)q.getValue()));
	else
	    throw new IncompatibleUnitsException(Units.DIMENSIONLESS, q.getUnits());
    }

    public Quantity abs()
    {
	return new Quantity(Math.abs(this.value), this.units);
    }

    //TESTS
    public boolean equals(Quantity q)
    {
	return this.hasSameValue(q) && this.isCompatible(q);
    }

    public boolean isCompatible(Quantity q)
    {
	return this.units.equals(q.getUnits());
    }

    public boolean hasSameValue(Quantity q)
    {
	return this.value == q.getValue();
    }

    public boolean isDimensionless()
    {
	return this.units.equals(Units.DIMENSIONLESS);
    }

    public boolean hasUnits()
    {
	return !this.isDimensionless();
    }

    public boolean isGreaterThan(Quantity q) throws Exception
    {
	if(this.isCompatible(q))
	    return this.value > q.getValue();
	else
	    throw new IncompatibleUnitsException(this.units, q.getUnits());
    }

    public boolean isLesserThan(Quantity q) throws Exception
    {
	if(this.isCompatible(q))
	    return this.value < q.getValue();
	else
	    throw new IncompatibleUnitsException(this.units, q.getUnits());
    }

    //UTILITIES
    public String toString()
    {
	return this.value + " " + this.units;
    }

    public void assign(Quantity q) throws Exception
    {
	this.value = q.getValue();
	this.units.assign(q.getUnits());
    }

    public Quantity copy()
    {
	return new Quantity(this);
    }

    public static void main(String[] args) throws Exception
    {
	System.out.println(ONE);
	System.out.println(new Quantity(44, Units.SPEED));
	System.out.println(new Quantity(44, Units.SPEED).pow(new Quantity()));
	System.out.println((new Quantity(5, Units.MOMENTUM)).times(new Quantity(6, Units.SPEED)));
	System.out.println((new Quantity(5, Units.ENERGY)).plus(new Quantity(6, Units.ENERGY)));
	System.out.println((new Quantity(5, Units.FORCE)).plus(new Quantity(6, Units.ENERGY)));
	System.out.println((new Quantity(5, Units.LENGTH)).pow(new Quantity(3, Units.DIMENSIONLESS)));
	//System.out.println((new Quantity(5, Units.LENGTH)).pow(new Quantity(3, Units.TIME)));
	System.out.println((new Quantity(5, Units.MOMENTUM)).equals(new Quantity(5, Units.SPEED)));
	System.out.println((new Quantity(5, Units.SPEED)).equals(new Quantity(5, Units.SPEED)));
    }
}
