
public class Complex extends Complex_Number {

	private final Real_Number real;
	private final Imaginary_Number imag;

	//CONSTRUCTORS
	public Complex() {
		this(new Integer());
	}

	public Complex(final Real_Number real) {
		this(real, new Imaginary());
	}

	public Complex(final Imaginary_Number imag) {
		this(new Integer(), imag);
	}

	public Complex(final Real_Number real, final Imaginary_Number imag) {
		this.real = real;
		this.imag = imag;
	}

	private Complex(final Complex c) {
		this(c.real, c.imag);
	}


	//ARITHMETIC
	public Numeric plus(final Complex other) {
		return new Complex((Real_Number)this.real.plus(other.real), (Imaginary_Number)this.imag.plus(other.imag));
	}

	public Numeric times(final Complex other) {

		if(this.isZero() || other.isZero())
			return new Integer();
		if(this.isOne())
			return other;
		if(other.isOne())
			return this;

		if(this.real.isZero()) {
			if(other.real.isZero())
				return ((Imaginary)this.imag).times((Imaginary)other.imag);

			if(other.imag.isZero())
				return this.imag.times(other.real);

			return new Complex((Real_Number)((Imaginary)this.imag).times((Imaginary)other.imag),
							   (Imaginary_Number)this.imag.times(other.real));
		}

		if(this.imag.isZero()) {
			if(other.real.isZero())
				return other.imag.times(this.real);

			if(other.imag.isZero())
				return this.real.times(other.real);

			return new Complex((Real_Number)this.real.times(other.real),
							   (Imaginary_Number)other.imag.times(this.real));
		}

		return this.real.times(other.real).plus(((Imaginary)this.imag).times((Imaginary)other.imag)).plus(
					other.imag.times(this.real)).plus(this.imag.times(other.real));
	}

	public Numeric negative() {
		return new Complex((Real_Number)this.real.negative(), (Imaginary_Number)this.imag.negative());
	}

	public Numeric inverse() {
		return this.conjugate().divide(this.norm());
	}

	private Complex_Number conjugate() {
		return new Complex(this.real, (Imaginary_Number)this.imag.negative());
	}

	private Real_Number norm() {
		return (Real_Number)this.real.times(this.real).minus(this.imag.times(this.imag));
	}

	//COMPARISON
	public int compareTo(final Complex other) {
		return this.norm().compareTo(other.norm());
	}

	public boolean equals(final Complex other) {
		return this.real.equals(other.real) && this.imag.equals(other.imag);
	}

	public boolean isZero() {
		return this.real.isZero() && this.imag.isZero();
	}

	public boolean isOne() {
		return this.real.isOne() && this.imag.isZero();
	}

	public boolean isI() {
		return this.imag.isI() && this.real.isZero();
	}


	//CONVERSIONS
	public Quantity_Number toQuantity() {
		return new Quantity(this);
	}

	public Complex_Number toComplex() {
		return this;
	}

	public Imaginary_Number toImaginary() {
		return this.imag;
	}

	public Real_Number toReal() {
		return this.real;
	}

	public Rational_Number toRational() {
		return this.real.toRational();
	}

	public Integer_Number toInteger() {
		return this.real.toInteger();
	}

	public long longValue() {
		return this.real.longValue();
	}

	public double doubleValue() {
		return this.real.doubleValue();
	}

	//UTILITIES
	public Numeric clone() {
		return new Complex(this);
	}

	public String toString() {
		return this.real + "+" + this.imag;
	}

	public static final Complex_Number parseComplex(final String s) {
		final int midIndex = s.indexOf('+') > 0 ? s.indexOf('+') : s.indexOf('-', 1);
		final boolean plus = s.charAt(midIndex) == '+';
		return new Complex( Real.parseReal(s.substring(0, midIndex)),
							Imaginary.parseImaginary(s.substring(midIndex + (plus ? 1 : 0))));
	}
}
