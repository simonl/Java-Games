

public class UnitSet {

	public static void main(String[] args) {

		System.out.println(DIMENSIONLESS);
		System.out.println(KILOGRAM);
		System.out.println(VOLT.times(MOLE));
		System.out.println(ACCELERATION);
		System.out.println(FORCE);
	}

	//Fundamentals
	public static final UnitSet DIMENSIONLESS = new UnitSet();
	public static final UnitSet METER = 	new UnitSet(1, 0, 0, 0, 0, 0, 0);
	public static final UnitSet SECOND = 	new UnitSet(0, 1, 0, 0, 0, 0, 0);
	public static final UnitSet KILOGRAM = 	new UnitSet(0, 0, 1, 0, 0, 0, 0);
	public static final UnitSet COULOMB = 	new UnitSet(0, 0, 0, 1, 0, 0, 0);
	public static final UnitSet VOLT = 		new UnitSet(0, 0, 0, 0, 1, 0, 0);
	public static final UnitSet KELVIN =	new UnitSet(0, 0, 0, 0, 0, 1, 0);
	public static final UnitSet MOLE = 		new UnitSet(0, 0, 0, 0, 0, 0, 1);

	//Derived units
	public static final UnitSet VELOCITY =  new UnitSet(1, -1, 0, 0, 0, 0, 0);
	public static final UnitSet MOMENTUM =  new UnitSet(1, -1, 1, 0, 0, 0, 0);
	public static final UnitSet ACCELERATION =  new UnitSet(1, -2, 0, 0, 0, 0, 0);
	public static final UnitSet FORCE =  new UnitSet(1, -2, 1, 0, 0, 0, 0);

	public static final UnitSet JOULES = new UnitSet(2, -2, 1, 0, 0, 0, 0);

	private final Unit[] units = {  Unit.METER,
									Unit.SECOND,
									Unit.KILOGRAM,
									Unit.COULOMB,
									Unit.VOLT,
									Unit.KELVIN,
									Unit.MOLE };

	//Constructors
	private UnitSet() {
		this(0, 0, 0, 0, 0, 0, 0);
	}

	private UnitSet(int L, int T, int M, int C, int P, int Te, int A) {
		units[0] = units[0].exponent(L);
		units[1] = units[1].exponent(T);
		units[2] = units[2].exponent(M);
		units[3] = units[3].exponent(C);
		units[4] = units[4].exponent(P);
		units[5] = units[5].exponent(Te);
		units[6] = units[6].exponent(A);
	}

	private UnitSet(int[] exponents) {
		for(int i : IterTools.range(7))
			units[i] = units[i].exponent(exponents[i]);
	}

	private UnitSet(Unit[] units) {
		for(int i : IterTools.range(7))
			this.units[i] = units[i];
	}

	//Arithmetic
	public UnitSet plus(UnitSet u) {
		Unit[] result = new Unit[7];

		for(int i : IterTools.range(7))
			result[i] = this.units[i].plus(u.units[i]);

		return new UnitSet(result);
	}

	public UnitSet minus(UnitSet u) {
		return plus(u);
	}

	public UnitSet times(UnitSet u) {
		Unit[] result = new Unit[7];

		for(int i : IterTools.range(7))
			result[i] = this.units[i].times(u.units[i]);

		return new UnitSet(result);
	}

	public UnitSet divide(UnitSet u) {
		return times(u.inverse());
	}

	public String toString() {
		String desc = "";
		String one;
		for(Unit unit : units) {
			one = unit.toString();
			desc += one + (one.equals("") ? "" : " ");
		}
		return desc;
	}

	public UnitSet inverse() {
		Unit[] result = new Unit[7];

		for(int i : IterTools.range(7))
			result[i] = units[i].inverse();

		return new UnitSet(result);
	}


	//Single unit	u^e
	private class Unit {

		public static final Unit METER = new Unit(Units.METER, 1);
		public static final Unit SECOND = new Unit(Units.SECOND, 1);
		public static final Unit KILOGRAM = new Unit(Units.KILOGRAM, 1);
		public static final Unit COULOMB = new Unit(Units.COULOMB, 1);
		public static final Unit VOLT = new Unit(Units.VOLT, 1);
		public static final Unit KELVIN = new Unit(Units.KELVIN, 1);
		public static final Unit MOLE = new Unit(Units.MOLE, 1);

		private final Units unit;
		private final int exponent;

		private Unit(Units unit, int exponent) {
			this.unit = unit;
			this.exponent = exponent;
		}

		public boolean equals(Unit u) {
			return sameBase(u) && this.exponent == u.exponent;
		}

		public boolean sameBase(Unit u) {
			return this.unit == u.unit;
		}

		//Arithmetic
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

		public Unit exponent(int e) {
			return new Unit(unit, exponent * e);
		}

		public String toString() {
			return exponent == 0 ? "" : unit + (exponent == 1 ? "" : "^" + exponent);
		}

		public Unit clone() {
			return new Unit(this.unit, this.exponent);
		}

		//Some SI units and others
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

			private class DimensionSet {
				private final int[] dimensions = new int[7];

				public DimensionSet(int a, int b, int c, int d, int e, int f, int g) {

				}
			}

			private class DimensionOrder {

				public static final DimensionOrder LENGTH = new DimensionOrder(Dimensions.LENGTH, 1);
				public static final DimensionOrder TIME = new DimensionOrder(Dimensions.TIME, 1);
				public static final DimensionOrder MASS = new DimensionOrder(Dimensions.MASS, 1);
				public static final DimensionOrder CHARGE = new DimensionOrder(Dimensions.CHARGE, 1);
				public static final DimensionOrder POTENTIAL = new DimensionOrder(Dimensions.POTENTIAL, 1);
				public static final DimensionOrder TEMPERATURE = new DimensionOrder(Dimensions.TEMPERATURE, 1);
				public static final DimensionOrder AMOUNT = new DimensionOrder(Dimensions.AMOUNT, 1);

				private final Dimensions dimension;
				private final int exponent;

				public DimensionOrder(Dimensions dimension, int exponent) {
					this.dimension = dimension;
					this.exponent = exponent;
				}

				public boolean equals(DimensionOrder d) {
					return sameDimension(d) && exponent == d.exponent;
				}

				public boolean sameDimension(DimensionOrder d) {
					return this.dimension == d.dimension;
				}

				public DimensionOrder plus(DimensionOrder d) {
					if(equals(d))
						return clone();
					throw new IllegalArgumentException("Incompatible dimensions : " + this + " -- " + d);
				}

				public DimensionOrder minus(DimensionOrder d) {
					return plus(d);
				}

				public DimensionOrder times(DimensionOrder d) {
					if(sameDimension(d))
						return new DimensionOrder(this.dimension, this.exponent + d.exponent);
					throw new IllegalArgumentException(
						"Use a higher class for cross-dimensional arithmetic!");
				}

				public DimensionOrder divide(DimensionOrder d) {
					return times(d.inverse());
				}

				public DimensionOrder inverse() {
					return new DimensionOrder(dimension, -exponent);
				}

				public DimensionOrder clone() {
					return new DimensionOrder(dimension, exponent);
				}
			}

			//Fundamental dimensional scales
			private enum Dimensions {
				LENGTH("[L]"),
				TIME("[T]"),
				MASS("[M]"),
				CHARGE("[Q]"),
				POTENTIAL("[P]"),
				TEMPERATURE("[L]"),
				AMOUNT("[L]");

				private final String symbol;

				private Dimensions(String symbol) {
					this.symbol = symbol;
				}

				public String toString() {
					return this.symbol;
				}
			}
		}
	}
}


