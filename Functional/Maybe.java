package Functional;

public interface Maybe<A> {
	boolean isNull();
	A get();
}

