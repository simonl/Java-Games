package Utils;
import Functional.Ref;


public class Refs {

	public static final <A> Ref<A> arrayRef(final A[] array, final int index) {
		
		return new Ref<A>() {
			
			public A read() {
				return array[index];
			}
			
			public void write(final A a) {
				array[index] = a;	
			}
		};
	}
	
	public static final <A> Ref<A> box(final A init) {
		
		return new Ref<A>() {
			
			private A content = init;
			
			public A read() {
				return this.content;
			}
			
			public void write(final A a) {
				this.content = a;
			}
		};
	}
	
}
