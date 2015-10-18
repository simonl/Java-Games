
public class Sorter {
	public static final int sort(int[] array) {
		return sort(array, 0, array.length);
	}

	private static final int sort(int[] array, int start, int end) {
		final int length = end - start;
		final int pivot = end - 1;

		if(length < 2)
			return 1;

				  			swap(array, (start + end) / 2, pivot);
		final int lessPos = move(array, start, pivot);
							swap(array, lessPos, pivot);

		return 1 + Math.max(	sort(array, start, lessPos),
								sort(array, lessPos + 1, end) );
	}

	private static void swap(int[] array, int pos1, int pos2) {
		final int temp = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = temp;
	}

	private static int move(int[] array, int start, int pivot) {
		int lessPos = start;
		for(int i = start; i < pivot; i++)
			if(array[i] < array[pivot]) {
				swap(array, lessPos, i);
				lessPos++;
			}
		return lessPos;
	}

	public static void printArray(int[] array, int start, int end) {
		for(int i = start; i < end - 1; i++)
			System.out.print(array[i] + ", ");
		if(end != 0)
			System.out.print(array[end - 1]);
	}

	public static void printArrayln(int[] array, int start, int end) {
		printArray(array, start, end);
		System.out.println();
	}

	public static void printArray(int[] array) {
		printArray(array, 0, array.length);
	}

	public static void printArrayln(int[] array) {
		printArrayln(array, 0, array.length);
	}


	public static void main(String... args) {
		final int size = 10000000;
		final int[] array = new int[size];
		for(int i = 0; i < size; i++)
			array[i] = (int)(Math.random() * size);

		//printArrayln(array);
		//final int depth = sort(array);
		//printArrayln(array);

		System.out.println("\nNumber --> " + size);
		System.out.println("Depth  --> " + sort(array));
	}
}