
import java.math.*;
import java.util.*;

public class NumericTest {

	public static void main(String... args) {

		// 1 : 1,000,000 performance
		CompD d = new CompD(4293, 4293);
		Numeric c = new Complex(new Real(new BigDecimal("4293")), new Imaginary(new Real(new BigDecimal("4293"))));
		Numeric q = new Quantity();
		double r = 4293;
		double i = 4293;

	long start, end;

	long fTimes = 100000;
	start = System.nanoTime();
			c.times(c);
	end = System.nanoTime();
	long fEx = end - start;

	long pTimes = 50000000;
	start = System.nanoTime();
		d.times(d);
	end = System.nanoTime();
	long pEx = end - start;

	long dTimes = 0;
	start = System.nanoTime();

	end = System.nanoTime();
	long dEx = end - start;
		double r2 = r * r - i * i;
		double i2 = r * i + r * i;
	System.out.println("CRIT  ->\tFramework\t:\tPrimitives\t:\tdoubles");
	System.out.println("TIME  ->\t" + fEx + "\t\t:\t" + pEx + "\t\t:\t" + dEx);
	System.out.println("SPACE ->\t" + Numeric.getCount() + "\t\t:\t" + CompD.getCount() + "\t\t:\t0");

		/*
		final Scanner keyboard = new Scanner(System.in);
		Numeric n1, n2;
		char op;

		do {
			System.out.print("\nNum 1 >>> ");
			n1 = Numeric.nextNumeric(keyboard);

			System.out.print("\nNum 2 >>> ");
			n2 = Numeric.nextNumeric(keyboard);

			System.out.print("\nOp >>> ");
			op = keyboard.next().charAt(0);

			System.out.println(eval(n1, n2, op));
		}while(true);
		*/
	}

	private static class CompD {

		private static long count = 0;
		private final double r;
		private final double i;

		public static long getCount() {
			return count;
		}

		public CompD(final double r, final double i) {
			this.r = r;
			this.i = i;
			count++;
		}

		public CompD plus(final CompD other) {
			return new CompD(this.r + other.r, this.i + other.i);
		}

		public CompD times(final CompD other) {
			return new CompD(this.r * other.r - this.i * other.i, this.r * other.i + other.r * this.i);
		}
	}

	public static Numeric eval(Numeric n1, Numeric n2, char op) {
		switch(op) {
			case '+' : return n1.plus(n2);
			case '-' : return n1.minus(n2);
			case '*' : return n1.times(n2);
			case '/' : return n1.divide(n2);
			default : return null;
		}
	}
}