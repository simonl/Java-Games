package Simulation;

import Utils.Iterators;
import Utils.Lazies;
import Functional.Func;
import Functional.Iterator;
import Functional.Unit;

public final class Integration {

	private static final double h = 0.001;
	
	public static final Iterator<Double> euler(final Double start, final Iterator<Double> f) {
		
		return Iterators.cons(start, Lazies.thunk(new Func<Unit, Iterator<Double>>() {
			
			public Iterator<Double> invoke(final Unit unit) {
				
				return euler(start + f.current() * h, f.rest());
			}
		}));
	}

	public static final Iterator<Double> trapezoid(final Double start, final Iterator<Double> f) {
		
		return Iterators.cons(start, Lazies.thunk(new Func<Unit, Iterator<Double>>() {
			
			public Iterator<Double> invoke(final Unit unit) {
				
				return euler(start + (f.current() + f.rest().current()) / 2 * h, f.rest());
			}
		}));
	}
}