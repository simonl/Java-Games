
import java.math.BigInteger;
import java.math.BigDecimal;

public class Rational extends Rational_Number {

	private final BigInteger num;
	private final BigInteger den;

	//CONSTRUCTORS
	public Rational() {
		this(BigInteger.ZERO);
	}

	public Rational(final BigInteger num) {
		this(num, BigInteger.ONE);
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
		return new Rational(this.num.multiply(other.den).add(this.den.multiply(other.num)),
							this.den.multiply(other.den));
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

	public boolean isZero() {
		return this.num.equals(BigInteger.ZERO);
	}

	public boolean isOne() {
		return this.num.equals(this.den);
	}

	public boolean isI() {
		return false;
	}

	//CONVERSIONS
	public Quantity_Number toQuantity() {
		return new Quantity(this);
	}

	public Complex_Number toComplex() {
		return new Complex(this);
	}

	public Imaginary_Number toImaginary() {
		return new Imaginary();
	}

	public Real_Number toReal() {
		return new Real(new BigDecimal(this.num).divide(new BigDecimal(this.den)));
	}

	public Rational_Number toRational() {
		return this;
	}

	public Integer_Number toInteger() {
		return new Integer(this.num.divide(this.den));
	}

	public long longValue() {
		return this.toReal().longValue();
	}

	public double doubleValue() {
		return this.toReal().doubleValue();
	}

	//UTILITIES
	public Numeric clone() {
		return new Rational(this);
	}

	public String toString() {
		return this.num + "/" + this.den;
	}

	public static final Rational_Number parseRational(final String s) {
		final int div = s.indexOf('/');
		return new Rational(new BigInteger(s.substring(0, div)),
							new BigInteger(s.substring(div+1)));
	}
}