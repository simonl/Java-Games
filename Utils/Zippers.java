package Utils;

import Functional.Zipper;

public class Zippers {
	
	public static final <T> Zipper<T> fromArray(final T[] array, final int index) {
		
		return new Zipper<T>() {
			
			public T current() {
				return array[index];
			}

			public Zipper<T> left() {
				return fromArray(array, index - 1);
			}
			
			public Zipper<T> right() {
				return fromArray(array, index + 1);
			}
			
			public boolean valid() {
				return index < array.length && index >= 0;
			}
		};
	}
}
