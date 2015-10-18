package Functional;


public final class Vector {

	public static final Vector ZERO = new Vector(0, 0, 0);
	public static final Vector X = new Vector(1, 0, 0);
	public static final Vector Y = new Vector(0, 1, 0);
	public static final Vector Z = new Vector(0, 0, 1);




	public final double x;
	public final double y;
	public final double z;

	public Vector(final double x) {
		this(x, 0);
	}

	public Vector(final double x, final double y) {
		this(x, y, 0);
	}

	public Vector(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public final String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}



	public static final Vector scale(final Vector v, final double s) {
		return new Vector(v.x * s, v.y * s, v.z * s);
	}

	public static final Vector add(final Vector v, final Vector u) {
		return new Vector(v.x + u.x, v.y + u.y, v.z + u.z);
	}

	public static final double magnitude(final Vector v) {
		return Math.sqrt(sqr(v.x) + sqr(v.y) + sqr(v.z));
	}

	private static final double sqr(final double x) { return x * x;	}

	public static final Vector negate(final Vector v) {
		return new Vector(-v.x, -v.y, -v.z);
	}

	public static final Vector subtract(final Vector v, final Vector u) {
		return Vector.add(v, Vector.negate(u));
	}
	
	public static final double dot(final Vector v, final Vector u) {
		return v.x * u.x + v.y * u.y + v.z * u.z;
	}

	public static final Vector cross(final Vector v, final Vector u) {
		return new Vector(v.y * u.z - u.y * v.z, v.x * u.z - u.x * v.z, v.x * u.y - u.x * v.y);
	}
	
	public static final Vector unit(final Vector v) {
		return Vector.scale(v, 1 / Vector.magnitude(v));
	}
}
