
public class Limits {
	private final double MIN;
	private final double MAX;

	public Limits(double min, double max) {
		this.MIN = min;
		this.MAX = max;
	}

	public double getMin() {
		return MIN;
	}

	public double getMax() {
		return MAX;
	}

	public double getExtent() {
		return MAX - MIN;
	}

	public double enters(double step) {
		return getExtent() / step;
	}

	public int entersClean(double step) {
		return (int)(enters(step) + 1.0E-8);
	}

	public double clean(double step) {
		return getStep(entersClean(step));
	}

	public double getStep(int n) {
		return getExtent() / n;
	}

	public String toString() {
		return MIN + " <-> " + MAX;
	}
}