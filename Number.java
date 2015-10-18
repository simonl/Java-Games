

public abstract final class NumericUtil {

	public static final Numeric getQuantity(Complex c, Units u) {
		return new Quantity(c, u);
	}

	public static final Numeric getComplex(Real r, Imaginary i) {
		return new Complex(r, i);
	}

	public static final Numeric getImaginary(Real r) {
		return new Imaginary(r);
	}

	public static final Numeric getReal(BigDecimal d) {
		return new Real(d);
	}

	public static final Numeric getRational(BigInteger n, BigInteger d) {
		return new Rational(n, d);
	}

	public static final Numeric getInteger(BigInteger i) {
		return new Integer(i);
	}

	public static final Numeric getInteger(long l) {
		return getInteger(new BigInteger(l));
	}

	public static final Numeric getReal(double d) {
		return getReal(new BigDecimal(d));
	}

}








public abstract Quantity extends Numeric {
	public NumericType getNumber() {
		return NumericType.QUANTITY;
	}

	public static final Numeric parse(String s) {

	}
}

public Quantity_Impl extends Quantity {

	private final Complex value;
	private final Units units;

	package
}

public Units implements Arithmetic<Units> {

}

public abstract Complex extends Quantity {
	public NumericType getNumber() {
		return NumericType.COMPLEX;
	}
}

public abstract Real extends Complex {
	public NumericType getNumber() {
		return NumericType.REAL;
	}
}

public abstract Imaginary extends Complex {
	public NumericType getNumber() {
		return NumericType.IMAGINARY;
	}
}

public abstract Rational extends Real {
	public NumericType getNumber() {
		return NumericType.RATIONAL;
	}
}

public abstract Integer extends Rational {
	public NumericType getNumber() {
		return NumericType.INTEGER;
	}
}