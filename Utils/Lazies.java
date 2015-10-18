package Utils;

import Functional.Func;
import Functional.Lazy;
import Functional.Unit;

public final class Lazies {
	private Lazies() { }
	
	public static final <A> Lazy<A> thunk(final Func<Unit, A> thunk) {
		
		return new Lazy<A>() {

			private A value = null;
			
			public A force() {
				if(this.value == null)
					this.value = thunk.invoke(Unit.UNIT);
				return this.value;
			}
		};
	}
	
	public static final <A> Lazy<A> value(final A value) {
		
		return new Lazy<A>() {
			
			public A force() { return value; }
			
		};
	}
}
