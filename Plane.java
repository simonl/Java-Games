
public class Plane {

	private double A, B, C, D;
	private Vector normal = new Vector();

	public Plane(double A, double B, double C) {
		this(A, B, C, 0);
	}

	public Plane(double A, double B, double C, double D) {
		this(new CartesianVector(A, B, C), D);
	}

	public Plane(Vector normal) {
		this(normal, 0);
	}

	public Plane(Vector normal, double D) {
		this.normal = normal;

		this.A = normal.X;
		this.B = normal.Y;
		this.C = normal.Z;
		this.D = D;
	}

	public boolean on(Vector point) {
		return distance(point) == 0;
	}

	public double distance(Vector point) {
		return normal.dotProduct(point) + D;
	}

	public int side(Vector point) {
		return distance(point) >= 0 ? 1 : 0;
	}
}
