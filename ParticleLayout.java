
import java.util.ArrayList;

public abstract class ParticleLayout extends ParticleHandler {


	protected final Vector origin = new Vector();
	protected ArrayList<Binding<Particle, Vector>> specialCases = new ArrayList<Binding<Particle, Vector>>();

	protected Range X = Range.ALL;
	protected Range Y = Range.ALL;
	protected Range Z = Range.ZERO;

	protected int startLevel = 0;
	protected double separation = 150;

	public ParticleLayout() {

	}

	public ParticleLayout(Particle p) {
		super(p);
	}

	public ParticleLayout(Particle[] ps) {
		super(ps);
	}

	public void setOrigin(Vector origin) {
		this.origin.setToVector(origin);
	}

	public void setDirections(Range X, Range Y, Range Z) {
		this.X = X;
		this.Y = Y;
		this.Z = Z;
	}

	public Vector getOrigin() {
		Vector other = new Vector();
		other.setToVector(origin);
		return other;
	}

	public void setStartingLevel(int i) {
		startLevel = i < 0 ? 0 : i;
	}

	public void setSeparation(double d) {
		separation = d < 0 ? -d : d;
	}

	public void addSpecialCase(Particle p, Vector pos) {
		specialCases.add(new Binding<Particle, Vector>(p, pos));
	}

	public void addSpecialCase(Binding<Particle, Vector> b) {
		specialCases.add(b);
	}

	public void doSpecialCases() {
		for(Binding<Particle, Vector> b : specialCases)
			b.getKey().motion.position.setToVector(b.getValue());
	}

	public final void setup() {
		doParticular();
		doSpecialCases();
	}

	public abstract void doParticular();
}