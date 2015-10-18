


public class Counter {
	protected long count = 0;

	public void increment() {
		count++;
	}

	public void restart() {
		count = 0;
	}

	public long getCount() {
		return count;
	}

	public String toString() {
		return "" + count;
	}
}