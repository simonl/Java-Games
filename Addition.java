

public class Addition {

	public static void main(String... args) {
		boolean[] num1 = make(10);
		boolean[] num2 = make(14);
		boolean[] even = make(8);
		boolean[] two = make(12);
		System.out.println(unmake(add(num1, num2)));
		System.out.println(unmake(ushift(even, two)));
	}

	public static boolean[] make(long n) {
		long part;
		boolean[] num = new boolean[32];
		for(int i = num.length - 1; i >= 0; i--) {
			part = pow(2, i);
			while(n >= part) {
				num[i] = !num[i];
				n -= part;
			}
		}
		return num;
	}

	public static void print(boolean[] bs) {
		for(boolean b : bs)
			System.out.print((b ? "T" : "F") + ", ");
			System.out.println();
	}

	public static long unmake(boolean[] n) {
		long num = 0;
		for(int i = 0; i < n.length; i++)
			num += n[i] ? pow(2, i) : 0;
		return num;
	}

	public static boolean[] add(boolean[] num1, boolean[] num2) {
		boolean[] result = new boolean[32];
		boolean carry = false;
		for(int i = 0; i < result.length; i++) {
			result[i] = num1[i] ^ num2[i];
			result[i] = carry ? !result[i] : result[i];
			carry = (num1[i] && num2[i]) || ((num1[i] ^ num2[i]) && carry);
		}

		return result;
	}

	public static boolean[] dshift(boolean[] num, boolean[] shift) {
		long pow;
		boolean[] cop = copy(num);
		for(int i = 0; i < shift.length; i++) {
			if(shift[i]) {
				pow = pow(2, i);
				for(int j = 0; j < num.length - pow; j++) {
					cop[j] = cop[j + (int)pow];
				}
			}
		}
		return cop;
	}

	public static boolean[] ushift(boolean[] num, boolean[] shift) {
		long pow;
		boolean[] cop = copy(num);
		for(int i = 0; i < shift.length; i++) {
			if(shift[i]) {
				pow = pow(2, i);
				for(int j = num.length - 1; j >= (int)pow; j--) {
					cop[j] = cop[j - (int)pow];
				}
			}
		}
		return cop;
	}

	public static boolean[] copy(boolean[] num) {
		boolean[] cop = new boolean[32];
		for(int i = 0; i < 32; i++)
			cop[i] = num[i];
		return cop;
	}

	public static boolean[] mult(boolean[] num1, boolean[] num2) {
		boolean[][] parts = new boolean[0][0];
		return parts[1];
	}

	public static long pow(long b, long p) {
		if(p == 0)
			return 1;
		if(p == 1)
			return b;
		if(even(p))
			return pow(square(b), p / 2);
		return b * pow(b, p - 1);
	}

	public static long square(long a) {
		return a * a;
	}

	public static boolean even(long n) {
		return n % 2 == 0;
	}
}

public abstract class MachineNumber {
	protected final int size;
	protected final boolean[] bits;

	public MachineNumber(int size) {
		this.size = size;
		this.bits = new boolean[size];
	}

	protected boolean bit(int bit) {
		return this.bits[bit];
	}

	protected void setTo(long num) {
		long part;
		for(int i = size - 1; i >= 0; i--) {
			part = value(i);
			while(num >= part) {
				bits[i] = !bits[i];
				num -= part;
			}
		}
	}

	protected int size() {
		return this.size;
	}

	protected long value(long bit) {
		return (long)Math.pow(2, bit);
	}

	public abstract MachineNumber add(MachineNumber other) {
		int len = Math.min(this.size, other.size);
		boolean[] num = new [len];
		boolean carry = false;
		for(int i = 0; i < len; i++) {
			result[i] = bit(i) ^ other.bit(i);
			result[i] = carry ? !result[i] : result[i];
			carry = (num1[i] && num2[i]) || ((num1[i] ^ num2[i]) && carry);
		}

		return result;
	}

	public abstract MachineNumber minus(MachineNumber other);
	public abstract MachineNumber compl(MachineNumber other);
	public abstract MachineNumber times(MachineNumber other) {
		MachineNumber result = new LongNumber();
		for(int i = 0; i < other.numBits; i++) {
			if(other.bit(i)) {

			}
		}
	}

	public abstract MachineNumber div(MachineNumber other);

	public abstract MachineNumber ushift(MachineNumber other);
	public abstract MachineNumber dshift(MachineNumber other);
	public abstract MachineNumber uroll(MachineNumber other);
	public abstract MachineNumber droll(MachineNumber other);
}

public class ByteNumber extends MachineNumber {
	public ByteNumber() {
		super(8);
	}

	public ByteNumber(long num) {
		this();
		this.setTo(num);
	}
}