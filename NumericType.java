

public enum NumericType {
	QUANTITY, COMPLEX, IMAGINARY, REAL, RATIONAL, INTEGER;

	public static NumericType getCommon(final NumericType type1, final NumericType type2) {
		if(type1 == type2) return type1;

		final NumericType type3 = type1.compareTo(type2) < 0 ? type1 : type2;

		return type3 == IMAGINARY ? COMPLEX : type3;
	}
}
