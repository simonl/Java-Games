
public interface Numerable {
	public abstract Quantity_Number toQuantity();
	public abstract Complex_Number toComplex();
	public abstract Imaginary_Number toImaginary();
	public abstract Real_Number toReal();
	public abstract Rational_Number toRational();
	public abstract Integer_Number toInteger();

	public abstract long longValue();
	public abstract double doubleValue();
}