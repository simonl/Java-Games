public class TestZero {

	public static final double CIRCLE = Math.PI * 2;
	public static final double HALF = Math.PI;
	public static final double QUARTER = Math.PI / 2;

	public static void main(String[] args) {
		for(int i = -10; i < 11; i++)
			System.out.println(normalAngle(QUARTER * i));

		int[] array = new int[20];

	/*
		for(int i = -198290; i < 198290; i += 12438)
			array[(int)(hash(i) % 20)]++;

	*/

		for(int i = 0; i < 1000; i++) {
			array[(int)(hash(randString()) % 20)]++;
		}

		for(int i = 0; i < 20; i++)
			System.out.println(array[i]);

		double d = 5;
		d = d += d;
		System.out.println(d);
		System.out.println(Double.NEGATIVE_INFINITY + Double.POSITIVE_INFINITY);
	}

	public static String randString() {

		String s = "";

		for(int i = 0; i < 10; i++) {
			s += (char)(Math.random() * 256);
		}

		return s;
	}

	public static long hash(long val) {
		long OFFSET = 16777619L;
		long PRIME = 1099511628211L;

		long code = OFFSET;

		while(val != 0) {
			val /= 128;
			code *= PRIME;
			code = (code / 128 * 128) + (( (byte)(code % 128) ) ^ ( (byte)(val % 128) ));
		}

		return Math.abs(code);
	}

	public static long hash(String s) {
		long PRIME1 = 16777619L;
		long PRIME2 = 1099511628211L;

		long code = 1;

		for(int i = 0; i < s.length(); i++) {
			code = ((code >> 5) * PRIME1) ^ (s.charAt(i) * PRIME2);

			long tmp = PRIME1;
			PRIME1 = PRIME2;
			PRIME2 = tmp;
		}

		return Math.abs(code);
	}

	public static double normalAngle(double angle) {
		return absoluteAngle(angle % CIRCLE);
	}

	public static double absoluteAngle(double angle) {
		if(angle >= 0)
			return angle < HALF ? angle : -CIRCLE + angle;
		return angle > -HALF ? angle : CIRCLE + angle;
	}
}


/*

public class Area {
	private final ArrayList<Particle> = new ArrayList<Particle>();

	public

}


count = 0 <-> map( (x){ return x + 1 }, count );

list = a->b
 a.next = b;
 list = a;

StringEquals :
	popl %ebx
	popl %ecx

	cmp -4(%ebx), -4(%ecx)
	jne fail

	movl -4(%ebx), %edx
	xorl %eax, %eax

	comp_loop:
		cmpl %eax, %edx
		je pass

		cmpl (%ebx), (%ecx)
		jne fail

		incl %eax
		incl %ebx
		incl %ecx
		jmp comp_loop

	fail:
		xorl %eax, %eax
		RETURN
	pass:
		movl $1, %eax
		RETURN

StringHash :



cons :

	CALL allocate(2)
	popl 4(%eax)
	popl (%eax)
	RETURN

car :
	popl %eax
	movl (%eax), %eax
	RETURN

cdr :
	popl %eax
	movl 4(%eax), %eax
	RETURN


find : (pairList, key)
	CALL car(pairList)



rem, -> %edx
len, -> %ecx
hash -> %eax
str  -> %ebx

hash(char[] s) :

	popl %ebx
	movl -4(%ebx), %ecx

	movl %ecx, %edx
	andl $3, %edx

	rshl $2, %ecx

	main_loop:
		cmp $0, %ecx
		je end_main

		addl (%ebx), %eax



		decl %ecx
		incl %ebx
		jmp main_loop

	end_main:




	rem = len & 3;
	len >>= 2;

	/* Main loop
	for (;len > 0; len--) {
		hash  += get16bits (data);
		tmp    = (get16bits (data+2) << 11) ^ hash;
		hash   = (hash << 16) ^ tmp;
		data  += 2*sizeof (uint16_t);
		hash  += hash >> 11;
	}

	/* Handle end cases
	switch (rem) {
		case 3: hash += get16bits (data);
				hash ^= hash << 16;
				hash ^= data[sizeof (uint16_t)] << 18;
				hash += hash >> 11;
				break;
		case 2: hash += get16bits (data);
				hash ^= hash << 11;
				hash += hash >> 17;
				break;
		case 1: hash += *data;
				hash ^= hash << 10;
				hash += hash >> 1;
	}

	/* Force "avalanching" of final 127 bits
	hash ^= hash << 3;
	hash += hash >> 5;
	hash ^= hash << 4;
	hash += hash >> 17;
	hash ^= hash << 25;
	hash += hash >> 6;

	return hash;
*/