
import java.text.*;
public class Timer extends Counter {

	private final double DELAY;
	private DecimalFormat format = new DecimalFormat("#.##");

	private Timer() {
		this(1.0);
	}

	public Timer(double delay) {
		this.DELAY = delay;
	}

	public double getTime() {
		return DELAY * getCount();
	}

	public double getDelay() {
		return DELAY;
	}

	public String toString() {
		return super.toString() + " -- " + format.format(getTime());
	}
}
