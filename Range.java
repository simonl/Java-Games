
import java.util.*;

public class Range {

	//For cartesian
	public static final Range POSITIVE = new Range(	new Limits(0, Double.POSITIVE_INFINITY),
												   	Comparator.GREATER,
												   	Comparator.LESSER);

	public static final Range ZERO = new Range(	new Limits(0, 0),
												Comparator.GREATER_EQUALS,
												Comparator.LESSER_EQUALS);

	public static final Range NEGATIVE = new Range( new Limits(Double.NEGATIVE_INFINITY, 0),
													Comparator.GREATER,
													Comparator.LESSER);

	public static final Range POSITIVE_Z = new Range( new Limits(0, Double.POSITIVE_INFINITY),
												   	Comparator.GREATER_EQUALS,
												   	Comparator.LESSER);

	public static final Range NEGATIVE_Z = new Range( new Limits(Double.NEGATIVE_INFINITY, 0),
													Comparator.GREATER,
													Comparator.LESSER_EQUALS);

	public static final Range ALL = new Range( new Limits(Double.NEGATIVE_INFINITY,
												 		  Double.POSITIVE_INFINITY),
												  Comparator.GREATER_EQUALS,
												  Comparator.LESSER);

	//For polar
	public static final Range CIRCLE = new Range( new Limits(0, Math.PI * 2),
												  Comparator.GREATER_EQUALS,
												  Comparator.LESSER);

	public static final Range TOP = new Range( new Limits(0, Math.PI),
												  Comparator.GREATER_EQUALS,
												  Comparator.LESSER);

	public static final Range BOTTOM = new Range( new Limits(Math.PI, Math.PI * 2),
												  Comparator.GREATER_EQUALS,
												  Comparator.LESSER);

	public static final Range LEFT = new Range( new Limits(Math.PI / 2, 3 * Math.PI / 2),
												  Comparator.GREATER_EQUALS,
												  Comparator.LESSER);

	public static final Range RIGHT = new Range( new Limits(-Math.PI / 2, Math.PI / 2),
												  Comparator.GREATER_EQUALS,
												  Comparator.LESSER);

	public static final Range QUAD1 = new Range( new Limits(0, Math.PI / 2),
												  Comparator.GREATER_EQUALS,
												  Comparator.LESSER);

	public static final Range QUAD2 = new Range( new Limits(Math.PI / 2, Math.PI),
												  Comparator.GREATER_EQUALS,
												  Comparator.LESSER);

	public static final Range QUAD3 = new Range( new Limits(Math.PI, 3 * Math.PI / 2),
												  Comparator.GREATER_EQUALS,
												  Comparator.LESSER);

	public static final Range QUAD4 = new Range( new Limits(3 * Math.PI / 2, Math.PI * 2),
												  Comparator.GREATER_EQUALS,
												  Comparator.LESSER);


	//INSTANCE VARIABLES
	private Limits limits;
	private Comparator lower;
	private Comparator upper;

	public Range(Limits limits) {
		this(limits, Comparator.GREATER_EQUALS, Comparator.LESSER);
	}

	public Range(Limits limits, Comparator lower, Comparator upper) {
		this.limits = limits;
		this.lower = lower;
		this.upper = upper;
	}

	public boolean isInRange(double value) {
		return  lower.apply(value, limits.getMin()) &&
				upper.apply(value, limits.getMax());
	}

	private double enters(double step) {
		return limits.enters(step) - limitsDiff();
	}

	private int entersClean(double step) {
		return (int)enters(limits.clean(step));
	}

	public double clean(double step) {
		if(this == ZERO) return 0.1;
		return limits.clean(step);
	}

	private double getStep(int times) {
		if(this == ZERO) return 0.1;
		return getExtent(limits.getStep(times + limitsDiff())) / times;
	}

	public double getExtent(double step) {
		return getMax(step) - getMin(step);
	}

	public int limitsDiff() {
		return 0 - (isInRange(limits.getMin()) ? 0 : 1) -
			   	   (isInRange(limits.getMax()) ? 0 : 1);
	}

	public double getMin(double step) {
		double min = limits.getMin();
		return min + (isInRange(min) ? 0 : step);
	}

	public double getMax(double step) {
		double max = limits.getMax();
		return max - (isInRange(max) ? 0 : step);
	}

	public String toString() {
		return "" + lower + limits.getMin() + " <-> " + upper + limits.getMax();
	}

	public double[] iterate(double step) {
		double min = getMin(step);

		int num = 0;
		for(double val = min; isInRange(val); val += step, num++);

		double[] list = new double[num];

		int i = 0;
		for(double val = min; isInRange(val); list[i] = val, val += step, i++);

		return list;
	}

	public double[] iterateClean(double step) {
		return iterate(clean(step));
	}
}