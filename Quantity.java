
public class Quantity extends Quantity_Number {

	private final Complex_Number value;
	private final Units units;

	//CONSTRUCTORS
	public Quantity() {
		this(new Integer());
	}

	public Quantity(final Complex_Number value) {
		this(value, Units.DIMENSIONLESS);
	}

	public Quantity(final Units units) {
		this(new Integer(), units);
	}

	public Quantity(final Complex_Number value, final Units units) {
		this.value = value;
		this.units = units;
	}

	private Quantity(final Quantity q) {
		this(q.value, q.units);
	}

	//ARITHMETIC
	public Numeric plus(final Quantity other) {
		return new Quantity((Complex_Number)this.value.plus(other.value), this.units.plus(other.units));
	}

	public Numeric times(final Quantity other) {
		return new Quantity((Complex_Number)this.value.times(other.value), this.units.times(other.units));
	}

	public Numeric negative() {
		return new Quantity((Complex_Number)this.value.negative(), this.units.negative());
	}

	public Numeric inverse() {
		return new Quantity((Complex_Number)this.value.inverse(), this.units.inverse());
	}

	//COMPARISONS
	public int compareTo(final Quantity other) {
		return 0;
	}

	public boolean isZero() {
		return this.value.isZero();
	}

	public boolean isOne() {
		return this.value.isOne();
	}

	public boolean isI() {
		return this.value.isI();
	}

	//CONVERSIONS
	public Quantity_Number toQuantity() {
		return this;
	}

	public Complex_Number toComplex() {
		return this.value;
	}

	public Imaginary_Number toImaginary() {
		return this.value.toImaginary();
	}

	public Real_Number toReal() {
		return this.value.toReal();
	}

	public Rational_Number toRational() {
		return this.value.toRational();
	}

	public Integer_Number toInteger() {
		return this.value.toInteger();
	}

	public long longValue() {
		return this.value.longValue();
	}

	public double doubleValue() {
		return this.value.doubleValue();
	}

	//UTILITIES
	public Numeric clone() {
		return new Quantity(this);
	}

	public String toString() {
		return this.value + " " + this.units;
	}

	public static final Quantity_Number parseQuantity(final String s) {
		final int space = s.indexOf(' ');
		return new Quantity((Complex_Number)Numeric.parseNumeric(s.substring(0, space)),
							Units.parseUnits(s.substring(space + 1)));
	}
}