

public class UnitSet {

	public static final UnitSet JOULES = new UnitSet(2, -2, 1, 0, 0, 0, 0);

	private final Unit[] units = new Unit[7];

	public UnitSet() {
		this(0, 0, 0, 0, 0, 0, 0);
	}

	public UnitSet(int L, int T, int M, int C, int P, int Te, int A) {
		units[0] = new Unit(Units.METER, L);
		units[1] = new Unit(Units.SECOND, T);
		units[2] = new Unit(Units.KILOGRAM, M);
		units[3] = new Unit(Units.COULOMB, C);
		units[4] = new Unit(Units.VOLT, P);
		units[5] = new Unit(Units.KELVIN, Te);
		units[6] = new Unit(Units.MOLE, A);
	}

	public UnitSet(int[] exponents) {
		int i = 0;
		for(int exp : exponents) {
			units[i] = new Unit(Units.values()[i], exponents[i]);
			i++;
		}
	}

	public UnitSet plus(UnitSet u) {
		Unit[] result = new Unit[7];

		for(int i : range(7))
			result[i] = this.units[i].plus(u.units[i]);

		return new UnitSet(result);
	}

	public UnitSet minus(UnitSet u) {
		return plus(u);
	}

	public UnitSet times(UnitSet u) {
		Unit[] result = new Unit[7];

		for(int i : range(7))
			result[i] = this.units[i].times(u.units[i]);

		return new UnitSet(result);
	}

	public UnitSet divide(UnitSet u) {
		return times(u.inverse());
	}

	public String toString() {
		String desc = "";
		for(Unit unit : units)
			desc += unit + " ";
		return desc;
	}

	public UnitSet inverse() {
		Unit[] result = new Unit[7];

		for(int i : range(7))
			result[i] = units[i].inverse();

		return new UnitSet(result);
	}



	private class Unit {

		private final Units unit;
		private final int exponent;

		public Unit(Units unit, int exponent) {
			this.unit = unit;
			this.exponent = exponent;
		}

		public boolean equals(Unit u) {
			return sameBase(u) && this.exponent == u.exponent;
		}

		public boolean sameBase(Unit u) {
			return this.unit == u.unit;
		}

		public Unit plus(Unit u) {
			if(equals(u))
				return clone();
			throw new IllegalArgumentException("Incompatible units : " + this + " -- " + u);
		}

		public Unit minus(Unit u) {
			return plus(u);
		}

		public Unit times(Unit u) {
			if(sameBase(u))
				return new Unit(this.unit, this.exponent + u.exponent);
			throw new IllegalArgumentException("Use a higher class for cross-unit arithmetic!");
		}

		public Unit divide(Unit u) {
			return divide(u.inverse());
		}

		public Unit inverse() {
			return new Unit(unit, -exponent);
		}

		public String toString() {
			return exponent == 0 ? "" : unit + (exponent == 1 ? "" : "^" + exponent);
		}

		public Unit clone() {
			return new Unit(this.unit, this.exponent);
		}

		private enum Units {

			METER(Dimensions.LENGTH, "m", 1),
			SECOND(Dimensions.TIME, "s", 1),
			KILOGRAM(Dimensions.MASS, "kg", 1),
			COULOMB(Dimensions.CHARGE, "C", 1),
			VOLT(Dimensions.POTENTIAL, "V", 1),
			KELVIN(Dimensions.TEMPERATURE, "K", 1),
			MOLE(Dimensions.AMOUNT, "M", 1),

			FOOT(Dimensions.LENGTH, "ft", 0.3),
			POUND(Dimensions.MASS, "lbs", 2.2);

			private final Dimensions dimension;
			private final String symbol;
			private final double scale;

			private Units(Dimensions dimension, String symbol, double scale) {
				this.dimension = dimension;
				this.symbol = symbol;
				this.scale = scale;
			}

			public boolean compatible(Units u) {
				return this.dimension == u.dimension;
			}

			public String toString() {
				return symbol;
			}

			private enum Dimensions {
				LENGTH,
				TIME,
				MASS,
				CHARGE,
				POTENTIAL,
				TEMPERATURE,
				AMOUNT,
				DIMENSIONLESS;
			}
		}
	}
}


