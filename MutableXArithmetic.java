
public interface MutableXArithmetic<T> extends MutableArithmetic<T> {
	public abstract T intDivAssign(final T other);
	public abstract T modAssign(final T other);
}