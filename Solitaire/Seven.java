package Solitaire;

public final class Seven<T> {
	private final T zeroth;
	private final T first;
	private final T second;
	private final T third;
	private final T fourth;
	private final T fifth;
	private final T sixth;
	
	public Seven(
			final T zeroth,
			final T first,
			final T second,
			final T third,
			final T fourth,
			final T fifth,
			final T sixth) {
		this.zeroth = zeroth;
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.fifth = fifth;
		this.sixth = sixth;
	}

	public T get(final int index) {
		switch(index) {
			case 0 : return zeroth;
			case 1 : return first;
			case 2 : return second;
			case 3 : return third;
			case 4 : return fourth;
			case 5 : return fifth;
			case 6 : return sixth;
		}
		
		throw new RuntimeException("Non-exhaustive switch in : T Seven<T>.get(nat < 7)");
	}
	
	public Seven<T> set(final int index, final T value) {
		switch(index) {
			case 0 : return new Seven<T>(value, first, second, third, fourth, fifth, sixth);
			case 1 : return new Seven<T>(zeroth, value, second, third, fourth, fifth, sixth);
			case 2 : return new Seven<T>(zeroth, first, value, third, fourth, fifth, sixth);
			case 3 : return new Seven<T>(zeroth, first, second, value, fourth, fifth, sixth);
			case 4 : return new Seven<T>(zeroth, first, second, third, value, fifth, sixth);
			case 5 : return new Seven<T>(zeroth, first, second, third, fourth, value, sixth);
			case 6 : return new Seven<T>(zeroth, first, second, third, fourth, fifth, value);
		}
		
		throw new RuntimeException("Non-exhaustive switch in : Seven<T> Seven<T>.set(nat < 7, T)");
	}		
}