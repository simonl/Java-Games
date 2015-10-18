import java.util.ArrayList;


public final class Oranges {

	private static final int len = 40;
	private static final int num = 9;
	private static final int[] positions = new int[num];
	private static final boolean[] distances = new boolean[len];

	private static int count = 0;
	
	public static final void main(final String... args) {
		System.out.println("GO");
		loop(0, 0);
		System.out.println(count);
	}
	
	private static final void loop(final int depth, final int start) {
		if(depth < num) {
			for(int pos = start; pos < len; pos++) {
				if(set(depth, pos)) {
					loop(depth+1, pos+1);
					clear(depth, pos);
				}
			}
		} else {			
			count++;
		}
	}
	
	private static final boolean set(final int depth, final int position) {
		final int[] newDists = new int[depth];
		
		for(int i = 0; i < depth; i++) {
			final int dist = position - positions[i];
			if(distances[dist]) {
				return false;
			} else
				newDists[i] = dist;
		}
		
		for(int i = 0; i < depth; i++)
			distances[newDists[i]] = true;
		
		positions[depth] = position;
		
		return true;
	}
	
	private static final void clear(final int depth, final int position) {
		for(int i = 0; i < depth; i++) {
			final int dist = position - positions[i];
			distances[dist] = false;
		}
	}
}
