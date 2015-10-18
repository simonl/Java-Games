
import java.math.BigDecimal;

public class Real extends Real_Number {

	private final BigDecimal value;

	//CONSTRUCTORS
	public Real() {
		this(BigDecimal.ZERO);
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
		return new Real(this.value.multiply(other.value));
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

	public boolean isZero() {
		return this.value.equals(BigDecimal.ZERO);
	}

	public boolean isOne() {
		return this.value.equals(BigDecimal.ONE);
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
		return this;
	}

	public Rational_Number toRational() {
		return new ContFrac(this, 15).toRational();
	}

	public Integer_Number toInteger() {
		return new Integer(this.value.toBigInteger());
	}

	public long longValue() {
		return this.value.longValue();
	}

	public double doubleValue() {
		return this.value.doubleValue();
	}

		private static class ContFrac {
			private final Integer_Number whole;
			private ContFrac fraction;

			private ContFrac(final Real_Number d, final int max) {
				this.whole = d.toInteger();

				if(max > 0)
					this.fraction = new ContFrac(
							(Real_Number)d.minus(whole).inverse(), max - 1);
			}

			public Rational_Number toRational() {
				return (Rational_Number)whole.toRational().plus(
						fraction.toRational().inverse());
			}
		}

	//UTILITIES
	public Numeric clone() {
		return new Real(this);
	}

	public String toString() {
		return this.value.toString();
	}

	public static final Real_Number parseReal(final String s) {
		return new Real(new BigDecimal(s));
	}
}