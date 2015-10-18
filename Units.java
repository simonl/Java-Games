
public class Units implements Arithmetic<Units> {

	//SI Units
	public static final Units DIMENSIONLESS = new Units(0);
	public static final Units METER 		= new Units(1);
	public static final Units SECOND 		= new Units(0, 1);
	public static final Units KILOGRAM 		= new Units(0, 0, 1);
	public static final Units COULOMB 		= new Units(0, 0, 0, 1);
	public static final Units KELVIN 		= new Units(0, 0, 0, 0, 1);
	public static final Units MOLE	 		= new Units(0, 0, 0, 0, 0, 1);
	public static final Units STERRADIAN 	= new Units(0, 0, 0, 0, 0, 0, 1);

	//SI Derived Units
	public static final Units SPEED			= METER.divide(SECOND);
	public static final Units ACCELERATION  = SPEED.divide(SECOND);
	public static final Units MOMENTUM		= SPEED.times(KILOGRAM);
	public static final Units NEWTON		= MOMENTUM.divide(SECOND);

	public static final Units JOULE			= NEWTON.times(METER);
	public static final Units WATT			= JOULE.divide(SECOND);
	public static final Units ACTION		= JOULE.times(SECOND);
	public static final Units HERTZ			= SECOND.inverse();

	public static final Units AMPERES		= COULOMB.divide(SECOND);
	public static final Units VOLT			= JOULE.divide(COULOMB);
	public static final Units OHM			= VOLT.divide(AMPERES);

	//Class Constants
	private static final int numUnits = 7;
	private static final String[] symbols = {"m", "s", "kg", "C", "K", "M", "Sr" };
	private static final String[] derivedSymbols = {"N", "J", "W", "Hz", "A", "V" };
	private static final Units[] derivedUnits = {NEWTON, JOULE, WATT, HERTZ, AMPERES, VOLT };


	//Instance Fields
	private final int[] powers = new int[numUnits];

	private Units(final int... powers) {
		for(int i = 0; i < powers.length; i++)
			this.powers[i] = powers[i];
	}

	private Units(final Units u) {
		this(u.powers);
	}

	//STANDARD ARITHMETIC
	public Units plus(final Units other) {
		if(!this.equals(other))
			throw new IllegalArgumentException(this + " != " + other);
		return this;
	}

	public Units minus(final Units other) {
		return this.plus(other.negative());
	}

	public Units times(final Units other) {
		final int[] powers = new int[numUnits];
		for(int i = 0; i < numUnits; i++)
			powers[i] = this.powers[i] + other.powers[i];
		return new Units(powers);
	}

	public Units divide(final Units other) {
		return this.times(other.inverse());
	}

	public Units negative() {
		return this;
	}

	public Units inverse() {
		final int[] powers = new int[numUnits];
		for(int i = 0; i < numUnits; i++)
			powers[i] = -this.powers[i];
		return new Units(powers);
	}

	//NON-STANDARD ARITHMETIC
	/*
	public Units power(final Numeric n) {
		if(n.getType() == NumericType.RATIONAL) {
			final Rational r = n.toRational();
			return this.power(r.num()).root(r.den());
		}
		if(n.getType() != NumericType.INTEGER)
			throw new IllegalArgumentException(n + " is not an integer power!");

		final int power = n.toInt();
		final int[] powers = new int[numUnits];
		for(int i = 0; i < numUnits; i++)
			powers[i] = this.powers[i] * power;
		return new Units(powers);
	}

	public Units root(final Numeric n) {
		if(n.getType() == NumericType.RATIONAL) {
			final Rational r = n.toRational();
			return this.power(r.den()).root(r.num());
		}
		if(n.getType() != NumericType.INTEGER)
			throw new IllegalArgumentException(n + " is not an integer root!");

		final int root = n.toInt();
		final int[] powers = new int[numUnits];
		for(int i = 0; i < numUnits; i++) {
			if(this.powers[i] % root == 0)
				powers[i] = this.powers[i] / root;
			else
				throw new IllegalArgumentException(n + " doesn't root " + this + " evenly!");
		}
		return new Units(powers);
	}
	*/

	//COMPARISON
	public boolean equals(final Object o) {
		if(o == null) return false;
		if(this.getClass() != o.getClass()) return false;

		final Units u = (Units)o;

		for(int i = 0; i < numUnits; i++)
			if(this.powers[i] != u.powers[i])
				return false;
		return true;
	}

	//UTILITIES
	public Units clone() {
		return new Units(this);
	}

	public String toString() {
		for(int i = 0; i < derivedUnits.length; i++)
			if(this.equals(derivedUnits[i]))
				return derivedSymbols[i];

		String s = "";
		for(int i = 0; i < numUnits; i++)
			if(powers[i] != 0) {
				s += symbols[i];
				if(powers[i] != 1)
					s += "^" + powers[i];
				s += " ";
			}
		return s;
	}

	public static final Units parseUnits(String s) {
		final int[] powers = new int[numUnits];
		for(int i = 0; i < numUnits; i++)
			powers[i] = getExponent(symbols[i], s);
		return new Units(powers);
	}

	private static int getExponent(String symbol, String s) {
		final int pos = s.indexOf(symbol);
		if(pos == -1)
			return 0;

		int end = s.indexOf(' ', pos);
		if(end == -1)
			end = s.length();

		return getExponent(s.substring(pos, end));
	}

	private static int getExponent(String s) {
		final int caret = s.indexOf('^');
		if(caret == -1)
			return 1;
		return java.lang.Integer.parseInt(s.substring(caret + 1));
	}
}