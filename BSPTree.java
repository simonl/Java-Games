
public class BSPTree {

	private final Plane separation;
	private BSPTree[] sides = null;
	private Particle local = null;

	public BSPTree() {

	}

	public BSPTree(Particle p) {
		this.local = p;
	}

	public boolean add(Particle p) {
		if(octants == null)
			if(local == null) {
				local = p;
				return true;
			}
			else {
				partition();
				place(local);
				local = null;
			}

		return place(p);
	}

	public void partition() {

		this.bounds[0] = new Plane(Vector.AxisX, -center.X);
		this.bounds[1] = new Plane(Vector.AxisY, -center.Y);
		this.bounds[2] = new Plane(Vector.AxisZ, -center.Z);

		BoundingBox[][][] subVolumes = this.volume.partition();
		octants = new OctTree[2][2][2];

		for(int x = 0; x < 2; x++)
			for(int y = 0; y < 2; y++)
				for(int z = 0; z < 2; z++)
					octants[x][y][z] = new OctTree(subVolumes[x][y][z]);
	}

	public boolean place(Particle p) {
		int x = bounds[0].side(p.motion.position);
		int y = bounds[1].side(p.motion.position);
		int z = bounds[2].side(p.motion.position);

		System.out.println(x + " - " + y + " - " + z);
		return octants[x][y][z].add(p);
	}

	public static void main(String[] args) {
		OctTree mainTree = new OctTree();

		Particle p = new Particle();
		mainTree.add(p);

		p = new Particle();
		p.motion.position.updateComponents(300);
		mainTree.add(p);

		System.out.println(mainTree);
	}

	public String toString() {
		return this.toString("\t");
	}

	public String toString(String tab) {
		String rep = volume.toString(tab);

		rep += local != null ? tab + "--> P !\n" : "";

		if(octants != null)
			for(OctTree[][] sub : octants)
				for(OctTree[] subsub : sub)
					for(OctTree subTree : subsub)
						rep += "\n" + subTree.toString(tab + "\t");

		return rep;
	}
}