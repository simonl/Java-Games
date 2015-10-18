
public class CircularLayout extends ParticleLayout {

	protected Range THETA = Range.CIRCLE;
	protected Range PHI = Range.ZERO;

	private double angleSep = separation;

	public CircularLayout() {
		this.origin.setToVector(origin);
	}

	public CircularLayout(Particle p) {
		super(p);
	}

	public CircularLayout(Particle[] ps) {
		super(ps);
	}

	public void doParticular() {

		Vector pos = new Vector();
		double r;
		double phiTurn;
		double thetaTurn;

		int max = particles.size();
		int i = 0;

		int start = startLevel;

		if(start == 0) {
			particles.get(0).motion.position.setToVector(origin);
			start++;
			i++;
		}

		for(int level = start; true; level++) {

			r = level * separation;

			for(double phi : PHI.iterateClean(getAngle(r))) {

				for(double theta : THETA.iterateClean(getAngle(Math.cos(phi)*r))){

					if(i < max) {
						pos.setToPolarComponents(r, theta, phi);
						pos.updateComponents(origin);

						particles.get(i).motion.position.setToVector(pos);
						i++;

					} else
						return;
				}
			}
		}
	}

	private double getAngle(double r) {
		return r == 0 ? 0 : Math.acos(1 - Math.pow(angleSep/r, 2)/2);
	}

	public void setAngles(Range theta, Range phi) {
		this.THETA = theta;
		this.PHI = phi;
	}

	public void setAngleSep(double s) {
		this.angleSep = s;
	}
}