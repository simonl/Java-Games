

public enum ArithmeticOperator {
	PLUS,
	MINUS,
	NEGATIVE,
	TIMES,
	DIVIDE,
	INVERSE,
	EXPONENT;

	public int apply(int[] n) {
		switch(this) {
			case PLUS : {
				int sum = 0;
				for(int i : n)
					sum += i;
				return sum;
			}
			case MINUS : {
				int diff = n[0] * 2;
				for(int i : n)
					diff -= i;
				return diff;
			}
			case NEGATIVE : {
				return -n[0];
			}
			case TIMES : {
				int prod = 1;
				for(int i : n)
					prod *= i;
				return prod;
			}
			case DIVIDE : {
				int quo = n[0] * n[0];
				for(int i : n)
					quo /= i;
				return quo;
			}
			case INVERSE : {
				return 1/n[0];
			}
			case EXPONENT : {
				int pow = n[0];
				for(int i = 1; i < n.length; i++)
					pow = (int)Math.pow(pow, i);
				return pow;
			}
			default : return 0;
		}
	}

	public double apply(double[] n) {
		switch(this) {
			case PLUS : {
				double sum = 0;
				for(double i : n)
					sum += i;
				return sum;
			}
			case MINUS : {
				double diff = n[0] * 2;
				for(double i : n)
					diff -= i;
				return diff;
			}
			case NEGATIVE : {
				return -n[0];
			}
			case TIMES : {
				double prod = 1;
				for(double i : n)
					prod *= i;
				return prod;
			}
			case DIVIDE : {
				double quo = n[0] * n[0];
				for(double i : n)
					quo /= i;
				return quo;
			}
			case INVERSE : {
				return 1/n[0];
			}
			case EXPONENT : {
				double pow = Math.pow(n[0], Math.pow(n[0], -1));
				for(double i : n)
					pow = Math.pow(pow, i);
				return pow;
			}
			default : return 0;
		}
	}

	public double apply(double i) {
		switch(this) {
			case PLUS : return i;
			case MINUS : return -i;
			case NEGATIVE : return -i;
			case TIMES : return i;
			case DIVIDE : return 1 / i;
			case INVERSE : return 1 / i;
			case EXPONENT : return i;
			default : return 0;
		}
	}

	public double apply(int i) {
		switch(this) {
			case PLUS : return i;
			case MINUS : return -i;
			case NEGATIVE : return -i;
			case TIMES : return i;
			case DIVIDE : return 1 / i;
			case INVERSE : return 1 / i;
			case EXPONENT : return i;
			default : return 0;
		}
	}

	public double apply(double i, double j) {
		switch(this) {
			case PLUS : return i + j;
			case MINUS : return i - j;
			case NEGATIVE : return -i;
			case TIMES : return i * j;
			case DIVIDE : return i / j;
			case INVERSE : return 1 / i;
			case EXPONENT : return Math.pow(i, j);
			default : return 0;
		}
	}

	public int apply(int i, int j) {
		switch(this) {
			case PLUS : return i + j;
			case MINUS : return i - j;
			case NEGATIVE : return -i;
			case TIMES : return i * j;
			case DIVIDE : return i / j;
			case INVERSE : return 1 / i;
			case EXPONENT : return (int)Math.pow(i, j);
			default : return 0;
		}
	}

	public String toString() {
		switch(this) {
			case PLUS : return "+";
			case MINUS : return "-";
			case NEGATIVE : return "0-";
			case TIMES : return "*";
			case DIVIDE : return "/";
			case INVERSE : return "1/";
			default : return "";
		}
	}
}