
public class MomentInertia {

	private static final int X = 0, Y = 1, Z = 2;

	private final double[][] moment;

	public MomentInertia() {
		this(new double[3][3]);
	}

	public MomentInertia(double[][] moment) {
		this.moment = moment;
	}


}