
import java.math.BigInteger;
import java.math.BigDecimal;

public class Integer extends Integer_Number {

	private final BigInteger value;

	//CONSTRUCTORS
	public Integer() {
		this(BigInteger.ZERO);
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

	public boolean isZero() {
		return this.value.equals(BigInteger.ZERO);
	}

	public boolean isOne() {
		return this.value.equals(BigInteger.ONE);
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
		return new Real(new BigDecimal(this.value));
	}

	public Rational_Number toRational() {
		return new Rational(this.value);
	}

	public Integer_Number toInteger() {
		return this;
	}

	public long longValue() {
		return this.value.longValue();
	}

	public double doubleValue() {
		return this.value.doubleValue();
	}

	//UTILITIES
	public Numeric clone() {
		return new Integer(this);
	}

	public String toString() {
		return this.value.toString();
	}

	public static final Integer_Number parseInteger(final String s) {
		return new Integer(new BigInteger(s));
	}
}