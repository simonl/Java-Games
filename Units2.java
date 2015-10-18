package javaapps.physicsobject;

public class Units
{
    //FUNDAMENTAL SI UNITS
    public static final Units DIMENSIONLESS = new Units();
    public static final Units MASS          = new Units(1, 0, 0, 0, 0, 0);
    public static final Units LENGTH        = new Units(0, 1, 0, 0, 0, 0);
    public static final Units TIME          = new Units(0, 0, 1, 0, 0, 0);
    public static final Units CHARGE        = new Units(0, 0, 0, 1, 0, 0);
    public static final Units TEMPERATURE   = new Units(0, 0, 0, 0, 1, 0);
    public static final Units AMOUNT        = new Units(0, 0, 0, 0, 0, 1);
    //public static final Units ANGLE         = new Units(0, 0, 0, 0, 0 ,0 , 1);

    //USEFUL UNITS
    public static final Units SPEED         = LENGTH.div(TIME);
    public static final Units ACCELERATION  = SPEED.div(TIME);

    public static final Units MOMENTUM      = SPEED.times(MASS);
    public static final Units FORCE         = ACCELERATION.times(MASS);
    public static final Units PRESSURE     = FORCE.div(LENGTH.pow(2));

    public static final Units ENERGY        = FORCE.times(LENGTH);
    public static final Units POWER         = ENERGY.div(TIME);
    public static final Units ACTION        = ENERGY.times(TIME);
    public static final Units FREQUENCY     = TIME.inverse();

    public static final Units POTENTIAL     = ENERGY.div(CHARGE);
    public static final Units CURRENT       = CHARGE.div(TIME);
    public static final Units RESISTANCE    = POTENTIAL.div(CURRENT);

    //SYMBOLS OF FUNDAMENTAL UNITS
    public static final String METER = "m";
    public static final String SECOND = "s";
    public static final String KILOGRAM = "kg";
    public static final String COULOMB = "C";
    public static final String KELVIN = "K";
    public static final String MOLE = "M";

    //SOME DERIVED UNITS' SYMBOLS
    public static final String JOULE = "J";
    public static final String NEWTON = "N";
    public static final String WATT = "W";
    public static final String KILOPASCAL = "KPa";
    public static final String VOLT = "V";
    public static final String AMPERES = "A";
    public static final String NO_DIMENSIONS = "-";

    //INSTANCE VARIABLES
    private final int massCount;
    private final int lengthCount;
    private final int timeCount;
    private final int chargeCount;
    private final int tempCount;
    private final int amountCount;

    public Units()
    {
	this(0, 0, 0, 0, 0, 0);
    }

    private Units(int m, int l, int t, int c, int T, int A)
    {
	this.massCount = m;
	this.lengthCount = l;
	this.timeCount = t;
	this.chargeCount = c;
	this.tempCount = T;
	this.amountCount = A;
    }

    public Units(Units u)
    {
	this(   u.getMassCount(),
		u.getLengthCount(),
		u.getTimeCount(),
		u.getChargeCount(),
		u.getTempCount(),
		u.getAmountCount()    );

    }

    //Getters
    public int getMassCount()
    {
	return this.massCount;
    }

    public int getLengthCount()
    {
	return this.lengthCount;
    }

    public int getTimeCount()
    {
	return this.timeCount;
    }

    public int getChargeCount()
    {
	return this.chargeCount;
    }

    public int getTempCount()
    {
	return this.tempCount;
    }

    public int getAmountCount()
    {
	return this.amountCount;
    }

    //CUSTOM
    public Units times(Units u)
    {
	return new Units(   this.massCount      +   u.getMassCount(),
			    this.lengthCount    +   u.getLengthCount(),
			    this.timeCount      +   u.getTimeCount(),
			    this.chargeCount    +   u.getChargeCount(),
			    this.tempCount      +   u.getTempCount(),
			    this.amountCount    +   u.getAmountCount()    );
    }

    public Units div(Units u)
    {
	return new Units(   this.massCount      -   u.getMassCount(),
			    this.lengthCount    -   u.getLengthCount(),
			    this.timeCount      -   u.getTimeCount(),
			    this.chargeCount    -   u.getChargeCount(),
			    this.tempCount      -   u.getTempCount(),
			    this.amountCount    -   u.getAmountCount()    );
    }

    public Units plus(Units u) throws Exception
    {
	if(this.equals(u))
	    return new Units(this);
	else
	    throw new IncompatibleUnitsException(this, u);
    }

    public Units minus(Units u) throws Exception
    {
	if(this.equals(u))
	    return new Units(this);
	else
	    throw new IncompatibleUnitsException(this, u);
    }

    public Units inverse()
    {
	return DIMENSIONLESS.div(this);
    }

    public Units pow(int n)
    {
	return new Units(   this.massCount      * n,
			    this.lengthCount    * n,
			    this.timeCount      * n,
			    this.chargeCount    * n,
			    this.tempCount      * n,
			    this.amountCount    * n    );
    }

    public Units root(int n) throws Exception
    {
	if(this.isRootableBy(n))
	    return new Units(   this.massCount      / n,
				this.lengthCount    / n,
				this.timeCount      / n,
				this.chargeCount    / n,
				this.tempCount      / n,
				this.amountCount    / n    );
	else
	    throw new FractionalPowerUnitsException(this, n);
    }

    //TESTS
    public boolean includes(Units u)
    {
	return this.massCount       >= u.getMassCount()     &&
		this.lengthCount    >= u.getLengthCount()   &&
		this.timeCount      >= u.getTimeCount()     &&
		this.chargeCount    >= u.getChargeCount()   &&
		this.tempCount      >= u.getTempCount()     &&
		this.amountCount    >= u.getAmountCount();
    }

    public boolean equals(Units u)
    {
	return  this.massCount      == u.getMassCount()     &&
		this.lengthCount    == u.getLengthCount()   &&
		this.timeCount      == u.getTimeCount()     &&
		this.chargeCount    == u.getChargeCount()   &&
		this.tempCount      == u.getTempCount()     &&
		this.amountCount    == u.getAmountCount();

    }

    public boolean isRootableBy(int n)
    {
	return  this.massCount      % n     == 0    &&
		this.lengthCount    % n     == 0    &&
		this.timeCount      % n     == 0    &&
		this.chargeCount    % n     == 0    &&
		this.tempCount      % n     == 0    &&
		this.amountCount    % n     == 0;
    }

    //UTILITIES
    public Units copy()
    {
	return new Units(this);
    }

    public void assign(Units u) throws Exception
    {
	if(!this.equals(u))
	    throw new IncompatibleUnitsException(this, u);
	/* NO ASSIGNMENT
	this.massCount      = u.getMassCount();
	this.lengthCount    = u.getLengthCount();
	this.timeCount      = u.getTimeCount();
	this.chargeCount    = u.getChargeCount();
	this.tempCount      = u.getTempCount();
	this.amountCount    = u.getAmountCount();
	*/
    }

    public String toString()
    {

	if(this.equals(ENERGY))
	    return JOULE;
	else if(this.equals(FORCE))
	    return NEWTON;
	else if(this.equals(POWER))
	    return WATT;
	else if(this.equals(CURRENT))
	    return AMPERES;
	else if(this.equals(POTENTIAL))
	    return VOLT;
	else if(this.equals(PRESSURE))
	    return KILOPASCAL;
	else if(this.equals(DIMENSIONLESS))
	    return NO_DIMENSIONS;

	/*
	if(this.includes(ENERGY))
	    return JOULE + " " + this.div(ENERGY);
	else if(this.includes(FORCE))
	    return NEWTON + " " + this.div(FORCE);
	else if(this.includes(POWER))
	    return WATT + " " + this.div(POWER);
	else if(this.includes(CURRENT))
	    return AMPERES + " " + this.div(CURRENT);
	else if(this.includes(POTENTIAL))
	    return VOLT + " " + this.div(POTENTIAL);
	else if(this.includes(PRESSURE))
	    return KILOPASCAL + " " + this.div(PRESSURE);
	else if(this.equals(DIMENSIONLESS))
	    return NO_DIMENSIONS;
	*/

	String length = (this.lengthCount != 0 ? METER + (this.lengthCount != 1 ? "^" + this.lengthCount : "") : "");
	String time = (this.timeCount != 0 ? SECOND + (this.timeCount != 1 ? "^" + this.timeCount : "") : "");
	String mass = (this.massCount != 0 ? KILOGRAM + (this.massCount != 1 ? "^" + this.massCount : "") : "");
	String charge = (this.chargeCount != 0 ? COULOMB + (this.chargeCount != 1 ? "^" + this.chargeCount : "") : "");
	String temp = (this.tempCount != 0 ? KELVIN + (this.tempCount != 1 ? "^" + this.tempCount : "") : "");
	String amount = (this.amountCount != 0 ? MOLE + (this.amountCount != 1 ? "^" + this.amountCount : "") : "");


	return  (mass != "" ? mass  + " ": "") +
		(length != "" ? length + " ": "") +
		(time != "" ? time + " ": "") +
		(charge != "" ? charge + " ": "") +
		(temp != "" ? temp + " ": "") +
		(amount != "" ? amount: "");
    }


    public static void main(String[] args) throws Exception
    {
	System.out.println(MASS);
	System.out.println(LENGTH);
	System.out.println(TIME);
	System.out.println(SPEED);
	System.out.println(ACCELERATION);
	System.out.println(ACTION.root(2));
    }

}
