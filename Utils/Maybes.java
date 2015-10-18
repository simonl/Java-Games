package Utils;
import Functional.Maybe;


public class Maybes {
	
	public static final <A> Maybe<A> maybe(final A content) {
		if(content == null) return nothing();
		
		return new Maybe<A>() {
			
			public boolean isNull() {
				return false;
			}
			
			public A get() {
				return content;
			}
		};
	}
	
	public static final <A> Maybe<A> nothing() {
		return new Maybe<A>() {

			public A get() {
				throw new NullPointerException();
			}

			public boolean isNull() {
				return true;
			}
		};
	}
}