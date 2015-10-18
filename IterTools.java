
public class IterTools {

	public static int[] range(int max) {
		return range(0, max, 1);
	}

	public static int[] range(int min, int max) {
		return range(min, max, 1);
	}

	public static int[] range(int min, int max, int inc) {
		int num = 0;
		for(int i = min; i < max; i++)
			num++;

		int[] range = new int[num];

		int count = 0;
		for(int i = min; i < max; i += inc) {
			range[count] = i;
			count++;
		}

		return range;
	}
}