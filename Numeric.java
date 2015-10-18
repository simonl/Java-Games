
import java.math.*;
import java.util.*;

public abstract class Numeric implements XArithmetic<Numeric>,
										Comparable<Numeric>, Numerable {

	private static final Integer 	INTEGER 	= new Integer();
	private static final Rational 	RATIONAL 	= new Rational();
	private static final Real 		REAL 		= new Real();
	private static final Imaginary 	IMAGINARY 	= new Imaginary();
	private static final Complex 	COMPLEX 	= new Complex();
	private static final Quantity 	QUANTITY 	= new Quantity();

	private static long count = 0;

	public Numeric() {
		count++;
		//System.out.println(getType());
	}

	public static long getCount() {
		return count;
	}

	private Integer getInteger() {
		return INTEGER;
	}

	private Rational getRational() {
		return RATIONAL;
	}

	private Real getReal() {
		return REAL;
	}

	private Imaginary getImaginary() {
		return IMAGINARY;
	}

	private Complex getComplex() {
		return COMPLEX;
	}

	private Quantity getQuantity() {
		return QUANTITY;
	}

	//DEFAULT IMPLEMENTATIONS OF MIXED-MODE ARITHMETIC
	public final Numeric plus(final Numeric other) {
		final Numeric[] common = convert(this, other);

		//System.out.println("AH! " + common[0] + " -- " + common[1]);
		switch(common[0].getType()) {
			case QUANTITY :
				final Quantity a = getQuantity();
				return a.getClass().cast(common[0]).plus(a.getClass().cast(common[1]));
			case COMPLEX :
				final Complex b = getComplex();
				return b.getClass().cast(common[0]).plus(b.getClass().cast(common[1]));
			case IMAGINARY :
				final Imaginary c = getImaginary();
				return c.getClass().cast(common[0]).plus(c.getClass().cast(common[1]));
			case REAL :
				final Real d = getReal();
				return d.getClass().cast(common[0]).plus(d.getClass().cast(common[1]));
			case RATIONAL :
				final Rational e = getRational();
				return e.getClass().cast(common[0]).plus(e.getClass().cast(common[1]));
			case INTEGER :
				final Integer f = getInteger();
				return f.getClass().cast(common[0]).plus(f.getClass().cast(common[1]));
			default : return null;
		}
	}

	public final Numeric minus(final Numeric other) {
		return this.plus(other.negative());
	}

	public final Numeric times(final Numeric other) {
		final Numeric[] common = convert(this, other);

		switch(common[0].getType()) {
			case QUANTITY :
				final Quantity a = getQuantity();
				return a.getClass().cast(common[0]).times(a.getClass().cast(common[1]));
			case COMPLEX :
				final Complex b = getComplex();
				return b.getClass().cast(common[0]).times(b.getClass().cast(common[1]));
			case IMAGINARY :
				final Imaginary c = getImaginary();
				return c.getClass().cast(common[0]).times(c.getClass().cast(common[1]));
			case REAL :
				final Real d = getReal();
				return d.getClass().cast(common[0]).times(d.getClass().cast(common[1]));
			case RATIONAL :
				final Rational e = getRational();
				return e.getClass().cast(common[0]).times(e.getClass().cast(common[1]));
			case INTEGER :
				final Integer f = getInteger();
				return f.getClass().cast(common[0]).times(f.getClass().cast(common[1]));
			default : return null;
		}
	}

	public final Numeric divide(final Numeric other) {
		return this.times(other.inverse());
	}

	public final Numeric intDiv(final Numeric other) {
		return this.divide(other).toInteger();
	}

	public final Numeric mod(final Numeric other) {
		return this.minus(this.intDiv(other));
	}

	//Prototype for the type
	public abstract NumericType getType();


	//Comparable
	public final int compareTo(Numeric other) {
		final Numeric[] common = convert(this, other);

		switch(common[0].getType()) {
			case QUANTITY :
				final Quantity a = getQuantity();
				return a.getClass().cast(common[0]).compareTo(a.getClass().cast(common[1]));
			case COMPLEX :
				final Complex b = getComplex();
				return b.getClass().cast(common[0]).compareTo(b.getClass().cast(common[1]));
			case IMAGINARY :
				final Imaginary c = getImaginary();
				return c.getClass().cast(common[0]).compareTo(c.getClass().cast(common[1]));
			case REAL :
				final Real d = getReal();
				return d.getClass().cast(common[0]).compareTo(d.getClass().cast(common[1]));
			case RATIONAL :
				final Rational e = getRational();
				return e.getClass().cast(common[0]).compareTo(e.getClass().cast(common[1]));
			case INTEGER :
				final Integer f = getInteger();
				return f.getClass().cast(common[0]).compareTo(f.getClass().cast(common[1]));
			default : return 0;
		}
	}

	public abstract boolean isZero();
	public abstract boolean isOne();
	public abstract boolean isI();

	//Utility methods for this semi-god class
	private static final Numeric[] convert(final Numeric n, final Numeric m) {
		final NumericType common = NumericType.getCommon(n.getType(), m.getType());
		final Numeric[] both = new Numeric[2];

		both[0] = convert(n, common);
		both[1] = convert(m, common);

		return both;
	}

	private static final Numeric convert(final Numeric n, final NumericType nT) {
		switch(nT) {
			case QUANTITY : return n.toQuantity();
			case COMPLEX : return n.toComplex();
			case IMAGINARY : return n.toImaginary();
			case REAL : return n.toReal();
			case RATIONAL : return n.toRational();
			case INTEGER : return n.toInteger();
			default : return n;
		}
	}

	public static final Numeric parseNumeric(final String s) {
		if(s.indexOf(' ') != -1) return Quantity.parseQuantity(s);
		if(s.indexOf('+') > 0 ||
		   s.indexOf('-') > 0  ) return Complex.parseComplex(s);
		if(s.indexOf("i") != -1) return Imaginary.parseImaginary(s);
		if(s.indexOf('.') != -1) return Real.parseReal(s);
		if(s.indexOf('/') != -1) return Rational.parseRational(s);
		else					 return Integer.parseInteger(s);
	}

	public static final Numeric nextNumeric(final Scanner keyboard) {
		return parseNumeric(keyboard.nextLine());
	}
}
