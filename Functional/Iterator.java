package Functional;

public interface Iterator<A> {
	boolean more();
	A current();
	Iterator<A> rest();
}
