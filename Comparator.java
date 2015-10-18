
public enum Comparator {
	EQUALS,
	NOT_EQUALS,
	LESSER,
	GREATER,
	LESSER_EQUALS,
	GREATER_EQUALS;

	public boolean apply(double n1, double n2) {
		switch(this) {
			case EQUALS : return Math.abs(n1 - n2) < 1.0E-8;
			case NOT_EQUALS : return Math.abs(n1 - n2) > 1.0E-8;
			case LESSER : return n1 - n2 < -1.0E-8;
			case GREATER : return n1 - n2 > 1.0E-8;
			case LESSER_EQUALS : return n1 - n2 <= 1.0E-8;
			case GREATER_EQUALS : return n1 - n2 >= -1.0E-8;
			default : return false;
		}
	}

	public <T extends Comparable<T>> boolean apply(T c1, T c2) {
		switch(this) {
			case EQUALS : return c1.compareTo(c2) == 0;
			case NOT_EQUALS : return c1.compareTo(c2) != 0;
			case LESSER : return c1.compareTo(c2) < 0;
			case GREATER : return c1.compareTo(c2) > 0;
			case LESSER_EQUALS : return c1.compareTo(c2) <= 0;
			case GREATER_EQUALS : return c1.compareTo(c2) >= 0;
			default : return false;
		}
	}

	public boolean apply(Object o1, Object o2) {
		switch(this) {
			case EQUALS : return o1.equals(o2);
			case NOT_EQUALS : return !o1.equals(o2);
			default : return false;
		}
	}

	public Comparator getInverse() {
		switch(this) {
			case EQUALS : return NOT_EQUALS;
			case NOT_EQUALS : return EQUALS;
			case LESSER : return GREATER_EQUALS;
			case GREATER : return LESSER_EQUALS;
			case LESSER_EQUALS : return GREATER;
			case GREATER_EQUALS : return LESSER;
			default : return null;
		}
	}

	public String toString() {
		switch(this) {
			case EQUALS : return "==";
			case NOT_EQUALS : return "!=";
			case LESSER : return "<";
			case GREATER : return ">";
			case LESSER_EQUALS : return "<=";
			case GREATER_EQUALS : return ">=";
			default : return "false";
		}
	}
}