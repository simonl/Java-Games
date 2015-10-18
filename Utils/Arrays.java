package Utils;
import Functional.Func;
import Functional.Iterator;


public final class Arrays {
	private Arrays() { }
	
	@SuppressWarnings("unchecked")
	public static final <A, B> B[] map(final A[] array, final Func<A, B> f) {
		final B[] ret = (B[]) new Object[array.length];
		
		for(int i = 0; i < ret.length; i++)
			ret[i] = f.invoke(array[i]);
		
		return ret;
	}
	
	public static final <A, B> B fold(final A[] array, final B init, final Func<B, Func<A, B>> f) {
		B ret = init;
		for(final A a : array)
			ret = f.invoke(ret).invoke(a);
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public static final <A> A[] set(final A[] array, final int index, final A a) {
		final A[] ret = (A[]) new Object[array.length];
		
		for(int i = 0; i < index; i++)
			ret[i] = array[i];
		ret[index] = a;
		for(int i = index+1;i < ret.length; i++)
			ret[i] = array[i];
		
		return ret;
	}
	
	public static final <A> Iterator<A> iterator(final A[] array) {
		return iterator(array, 0);
	}
	
	private static final <A> Iterator<A> iterator(final A[] array, final int index) {
		return new Iterator<A>() {
			
			public A current() {
				return array[index];
			}

			public boolean more() {
				return index < array.length;
			}

			public Iterator<A> rest() {
				return iterator(array, index+1);
			}
		};
	}
}
