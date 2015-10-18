package Utils;

import Functional.Func;
import Functional.Iterator;
import Functional.Lazy;
import Functional.Unit;

public class Iterators {

	// iterate f x = x : iterate f (f x)
	// iterate f = unfold (\x -> (x, f x))
	
	public static <A> Iterator<A> iterate(final A initial, final Func<A, A> step) {
		
		return cons(
				initial, 
				Lazies.thunk(new Func<Unit, Iterator<A>>() {

					public Iterator<A> invoke(final Unit unit) {
						
						return iterate(step.invoke(initial), step);
					}
				}));
	}
	
	public static <A> Iterator<A> reverse(Iterator<A> xs) {
		Iterator<A> other = nil();
		
		while(xs.more()) {
			other = cons(xs.current(), other);
			xs = xs.rest();
		}
		
		return other;
	}
	
	public static final <A, B> Iterator<B> fmap(final Iterator<A> it, final Func<A, B> f) {
		
		if(!it.more()) return nil();
		
		return cons(
				f.invoke(it.current()),
				Lazies.thunk(new Func<Unit, Iterator<B>>() {

					public Iterator<B> invoke(final Unit unit) {

						return fmap(it.rest(), f);
					}
				}));
	}
	
	public static final <A> Iterator<A> nil() {
		return new Iterator<A>() {

			public A current() {
				throw new UnsupportedOperationException("Empty list doesn't have a current element!");
			}

			public boolean more() {
				return false;
			}

			public Iterator<A> rest() {
				throw new UnsupportedOperationException("Empty list doesn't have a tail!");
			}
		};
	}
	
	public static final <A> Iterator<A> cons(final A head, final Iterator<A> tail) {
		return new Iterator<A>() {
			public A current() {
				return head;
			}
			
			public boolean more() {
				return true;
			}
			
			public Iterator<A> rest() {
				return tail;
			}
		};
	}
	
	public static final <A> Iterator<A> cons(final A head, final Lazy<Iterator<A>> tail) {
		return new Iterator<A>() {
			public A current() {
				return head;
			}
			
			public boolean more() {
				return true;
			}
			
			public  Iterator<A> rest() {
				return tail.force();
			}
		};
	}
}
