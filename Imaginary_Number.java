
public abstract class Imaginary_Number extends Complex_Number {
	public NumericType getType() {
		return NumericType.IMAGINARY;
	}

	public abstract Numeric plus(Real_Number other);
	public abstract Numeric times(Real_Number other);
}