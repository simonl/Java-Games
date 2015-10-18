package Functional;

public interface Zipper<T> {
	T current();
	Zipper<T> left();
	Zipper<T> right();
	boolean valid();
}
