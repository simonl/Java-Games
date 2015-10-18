
public class BoundingBox {

	public static final double LARGE = 1.0E3;

	private final Vector[] limits = new Vector[3];

	public BoundingBox() {
		this.limits[0] = Vector.MINIMUM;
		this.limits[1] = Vector.ZERO;
		this.limits[2] = Vector.MAXIMUM;
	}

	public BoundingBox(Vector min, Vector max) {
		this.limits[0] = min;
		this.limits[1] = limitAverage(min, max);
		this.limits[2] = max;
	}

	public BoundingBox[][][] partition() {

		BoundingBox[][][] octants = new BoundingBox[2][2][2];

		for(int x = 0; x < 2; x++)
			for(int y = 0; y < 2; y++)
				for(int z = 0; z < 2; z++)
					octants[x][y][z] = new BoundingBox(
									new CartesianVector(limits[x].X  , limits[y].Y  , limits[z].Z  ),
									new CartesianVector(limits[x+1].X, limits[y+1].Y, limits[z+1].Z));

		return octants;
	}

	public Vector center() {
		return limits[1];
	}

	private static Vector limitAverage(Vector min, Vector max) {
		return new CartesianVector(
								limitAverage(min.X, max.X),
								limitAverage(min.Y, max.Y),
								limitAverage(min.Z, max.Z)	);
	}

	private static double limitAverage(double min, double max) {
		if(min == Double.NEGATIVE_INFINITY)
			if(max == Double.POSITIVE_INFINITY)
				return 0;
			else
				return max - LARGE;
		else
			if(max == Double.POSITIVE_INFINITY)
				return min + LARGE;
			else
				return (min + max) / 2;
	}

	public String toString() {
		return  limits[0].X + " <= " + limits[1].X + " <= " + limits[2].X + "\n" +
				limits[0].Y + " <= " + limits[1].Y + " <= " + limits[2].Y + "\n" +
				limits[0].Z + " <= " + limits[1].Z + " <= " + limits[2].Z + "\n";
	}

	public String toString(String tab) {
		return  tab + limits[0].X + " <= " + limits[1].X + " <= " + limits[2].X + "\n" +
				tab + limits[0].Y + " <= " + limits[1].Y + " <= " + limits[2].Y + "\n" +
				tab + limits[0].Z + " <= " + limits[1].Z + " <= " + limits[2].Z + "\n";
	}
}