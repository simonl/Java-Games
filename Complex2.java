
public class Quantity2 extends Quantity {

	private final Complex value;
	private final Units units;

	//CONSTRUCTORS
	public Quantity() {
		this(new Complex());
	}

	public Quantity(final Complex value) {
		this(value, Units.DIMENSIONLESS);
	}

	public Quantity(final Units units) {
		this(new Complex(), units);
	}

	public Quantity(final Complex value, final Units units) {
		this.value = value;
		this.units = units;
	}

	private Quantity(final Quantity q) {
		this(q.value, q.units);
	}

	//ARITHMETIC
	public Numeric plus(final Quantity other) {
		return new Quantity(this.value.plus(other.value), this.units.plus(other.units));
	}

	public Numeric times(final Quantity other) {
		return new Quantity(this.value.times(other.value), this.units.times(other.units));
	}

	public Numeric negative() {
		return new Quantity(this.value.negative(), this.units.negative());
	}

	public Numeric inverse() {
		return new Quantity(this.value.inverse(), this.units.inverse());
	}

	//COMPARISONS
	public int compareTo(final Quantity other) {
		return 0;
	}

	//UTILITIES
	public Numeric clone() {
		return new Quantity(this);
	}

	public String toString() {
		return this.value + " " + this.units;
	}
}

public class Complex2 extends Complex {

	private final Real real;
	private final Imaginary imag;

	//CONSTRUCTORS
	public Complex() {
		this(new Real());
	}

	public Complex(final Real real) {
		this(real, new Imaginary());
	}

	public Complex(final Imaginary imag) {
		this(new Real(), imag);
	}

	public Complex(final Real real, final Imaginary imag) {
		this.real = real;
		this.imag = imag;
	}

	private Complex(final Complex c) {
		this(c.real, c.imag);
	}


	//ARITHMETIC
	public Numeric plus(final Complex other) {
		return new Complex(this.real.plus(other.real).toReal(), this.imag.plus(other.imag).toImaginary());
	}

	public Numeric times(final Complex other) {
		return new Complex(this.real.times(other.real).plus(this.imag.times(other.imag)).toReal(),
						   this.imag.times(other.real).plus(other.imag.times(this.real)).toImaginary());
	}

	public Numeric negative() {
		return new Complex(this.real.negative(), this.imag.negative());
	}

	public Numeric inverse() {
		return this.conjugate().divide(this.norm());
	}

	private Complex conjugate() {
		return new Complex(this.real, this.imag.megative());
	}

	private Real norm() {
		return this.real.times(this.real).minus(this.imag.times(this.imag)).toReal();
	}

	//COMPARISON
	public int compareTo(Complex other) {
		return this.norm().compareTo(other.norm());
	}

	//UTILITIES
	public Numeric clone() {
		return new Complex(this);
	}

	public String toString() {
		return this.real + (this.imag.isPositive() ? "+" : "") + this.imag;
	}
}

public class Imaginary2 extends Imaginary {

	private final Real factor;

	//CONSTRUCTOR
	public Imaginary() {
		this(new Real());
	}

	public Imaginary(final Real factor) {
		this.factor = factor;
	}

	public Imaginary(final Imaginary i) {
		this(i.factor);
	}


	//ARITHMETIC
	public Numeric plus(final Imaginary other) {
		return new Imaginary(this.factor.plus(other.factor));
	}

	public Numeric times(final Imaginary other) {
		return this.factor.times(other.factor).negative();
	}

	public Numeric times(final Real other) {
		return new Imaginary(this.factor.times(other));
	}

	public Numeric negative() {
		return new Imaginary(this.factor.negative());
	}

	public Numeric inverse() {
		return new Imaginary(this.factor.inverse().negative());;
	}

	//COMPARISONS
	public int compareTo(final Imaginary other) {
		return this.factor.compareTo(other.factor);
	}

	//UTILITIES
	public Numeric clone() {
		return new Imaginary(this);
	}

	public String toString() {
		return this.factor + "*i";
	}
}

public class Real2 extends Real {

	private final BigDecimal value;

	//CONSTRUCTORS
	public Real() {
		this(new BigDecimal());
	}

	public Real(final BigDecimal value) {
		this.value = value;
	}

	public Real(final Real r) {
		this(r.value);
	}

	//ARITHMETIC
	public Numeric plus(final Real other) {
		return new Real(this.value.add(other.value));
	}

	public Numeric times(final Real other) {
		return new Real(this.value,.multiply(other.value));
	}

	public Numeric negative() {
		return new Real(this.value.negate());
	}

	public Numeric inverse() {
		return new Real(BigDecimal.ONE.divide(this.value));
	}

	//COMPARISON
	public int compareTo(final Real other) {
		return this.value.compareTo(other.value);
	}

	//UTILITIES
	public Numeric clone() {
		return new Real(this);
	}

	public String toString() {
		return this.value.toString();
	}
}

public class Rational2 extends Rational {

	private final BigInteger num;
	private final BigInteger den;

	//CONSTRUCTORS
	public Rational() {
		this(new BigInteger());
	}

	public Rational(final BigInteger num) {
		this(num, new BigInteger());
	}

	public Rational(final BigInteger num, final BigInteger den) {
		if(den.compareTo(BigInteger.ZERO) == 0)
			throw new IllegalArgumentException("Denominator cannot be 0!");

		final boolean neg = den.compareTo(BigInteger.ZERO) < 0;

		final BigInteger gcd = num.gcd(den);

		this.num = (neg ? num.negate() : num).divide(gcd);
		this.den = (neg ? den.negate() : den).divide(gcd);
	}

	public Rational(final Rational r) {
		this(r.num, r.den);
	}

	//ARITHMETIC
	public Numeric plus(final Rational other) {
		return new Rational(this.num.multiply(other.den).add(this.den.multiply(other.num)), this.den.multiply(other.den));
	}

	public Numeric times(final Rational other) {
		return new Rational(this.num.multiply(other.num), this.den.multiply(other.den));
	}

	public Numeric negative() {
		return new Rational(this.num.negate(), this.den);
	}

	public Numeric inverse() {
		return new Rational(this.den, this.num);
	}

	//COMPARISON
	public int compareTo(final Rational other) {
		return this.num.multiply(other.den).compareTo(this.den.multiply(other.num));
	}

	//UTILITIES
	public Numeric clone() {
		return new Rational(this);
	}

	public String toString() {
		return this.num + "/" + this.den;
	}
}

public class Integer2 extends Integer {

	private final BigInteger value;

	//CONSTRUCTORS
	public Integer() {
		this(new BigInteger());
	}

	public Integer(final BigInteger value) {
		this.value = value;
	}

	public Integer(final Integer i) {
		this(i.value);
	}

	//ARITHMETIC
	public Numeric plus(final Integer other) {
		return new Integer(this.value.add(other.value));
	}

	public Numeric times(final Integer other) {
		return new Integer(this.value.multiply(other.value));
	}

	public Numeric negative() {
		return new Integer(this.value.negate());
	}

	public Numeric inverse() {
		return new Rational(BigInteger.ONE, this.value);
	}

	//COMPARISON
	public int compareTo(final Integer other) {
		return this.value.compareTo(other.value);
	}

	//UTILITIES
	public Numeric clone() {
		return new Integer(this);
	}

	public String toString() {
		return this.value.toString();
	}
}