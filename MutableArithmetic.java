
public interface MutableArithmetic<T> extends Arithmetic<T>, Mutable<T> {
	public abstract T plusAssign(final T other);
	public abstract T minusAssign(final T other);
	public abstract T timesAssign(final T other);
	public abstract T divideAssign(final T other);
	public abstract T negate();
	public abstract T invert();
}