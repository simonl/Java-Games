

public class GridLayout extends ParticleLayout {

	public GridLayout() {
		this.origin.setToVector(origin);
	}

	public GridLayout(Particle p) {
		super(p);
	}

	public GridLayout(Particle[] ps) {
		super(ps);
	}

	public void doParticular() {
		int level = startLevel;
		int incX = 0;
		int incY = 1;

		int max = getLevelNum(level);
		int num = max;

		int x = level;
		int y = 0;

		for(Particle p : particles) {
			p.motion.position.setToComponents(x * separation + origin.getX(),
											  y * separation + origin.getY(), 0);

			if(num == 1) {
				level++;

				x = level;
				y = 0;

				incX = 0;
				incY = 1;

				max = getLevelNum(level);
				num = max;

				continue;
			}

			if(x == level) {
				if(y == level) {
					incX = -1;
					incY = 0;
				} else if(y == -level) {
					incX = 0;
					incY = 1;
				}
			} else if(x == -level) {
				if(y == level) {
					incX = 0;
					incY = -1;
				} else if(y == -level) {
					incX = 1;
					incY = 0;
				}
			}

			x += incX;
			y += incY;
			num--;
		}
	}

	private void doParticular2() {
		int level = startLevel;
		int side = getSide(level);
		int i = 0;

		if(level == 0) {
			particles.get(i).motion.position.setToVector(origin);
			level++;
			i++;
		}

		for(; true; level++) {
			for(double x : X.iterate(separation));
		}
	}

	private void doOuter(int level, Vector origin) {
		int x = 0;
	}

	private int getSide(int level) {
		return 2*level + 1;
	}

	private int getLevelNum(int level) {
		return level == 0 ? 1 : 8 * level;
	}
}