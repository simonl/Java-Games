
public interface Arithmetic<T> {
	public abstract T plus(final T other);
	public abstract T minus(final T other);
	public abstract T times(final T other);
	public abstract T divide(final T other);
	public abstract T negative();
	public abstract T inverse();
}