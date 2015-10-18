

public class Imaginary extends Imaginary_Number {

	private final Real_Number factor;

	//CONSTRUCTOR
	public Imaginary() {
		this(new Integer());
	}

	public Imaginary(final Real_Number factor) {
		this.factor = factor;
	}

	public Imaginary(final Imaginary i) {
		this(i.factor);
	}


	//ARITHMETIC
	public Numeric plus(final Imaginary other) {
		return new Imaginary((Real_Number)this.factor.plus(other.factor));
	}

	public Numeric plus(final Real_Number other) {
		return new Complex(other, this);
	}

	public Numeric times(final Imaginary other) {
		return this.factor.times(other.factor).negative();
	}

	public Numeric times(final Real_Number other) {
		return new Imaginary((Real_Number)this.factor.times(other));
	}

	public Numeric negative() {
		return new Imaginary((Real_Number)this.factor.negative());
	}

	public Numeric inverse() {
		return new Imaginary((Real_Number)this.factor.inverse().negative());
	}

	//COMPARISONS
	public int compareTo(final Imaginary other) {
		return this.factor.compareTo(other.factor);
	}

	public boolean isZero() {
		return this.factor.isZero();
	}

	public boolean isOne() {
		return false;
	}

	public boolean isI() {
		return this.factor.isOne();
	}

	//CONVERSIONS
	public Quantity_Number toQuantity() {
		return new Quantity(this);
	}

	public Complex_Number toComplex() {
		return new Complex(this);
	}

	public Imaginary_Number toImaginary() {
		return this;
	}

	public Real_Number toReal() {
		return new Real();
	}

	public Rational_Number toRational() {
		return new Rational();
	}

	public Integer_Number toInteger() {
		return new Integer();
	}

	public long longValue() {
		return 0;
	}

	public double doubleValue() {
		return 0.0;
	}

	//UTILITIES
	public Numeric clone() {
		return new Imaginary(this);
	}

	public String toString() {
		return this.factor + "*i";
	}

	public static final Imaginary_Number parseImaginary(final String s) {
		return new Imaginary((Real_Number)Numeric.parseNumeric(s.substring(0, s.indexOf("*i"))));
	}
}
