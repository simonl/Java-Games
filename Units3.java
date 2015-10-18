

public enum Unit {
	LENGTH("m"),
	TIME("s"),
	MASS("kg"),
	CHARGE("C"),
	POTENTIAL("V"),
	TEMPERATURE("K"),
	AMOUNT("M");

	private final String symbol;

	private Units(String symbol) {
		this.symbol = symbol;
	}

	public Units toString() {
		return this.symbol;
	}
}