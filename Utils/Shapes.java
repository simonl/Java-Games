package Utils;

import java.awt.Graphics;

import Functional.Maybe;
import Functional.Shape;
import Functional.Vector;

public class Shapes {

	public static final Shape circle(final double radius) {
		return new Shape() {

			@Override
			public Maybe<Vector> collide(final Vector position, final Vector bounds) {
				final int x = fromBound(position.x, bounds.x);
				final int y = fromBound(position.y, bounds.y);
				final int z = fromBound(position.z, bounds.z);
				
				if(x == 0 && y == 0 && z == 0)
					return Maybes.nothing();
				
				return Maybes.maybe(new Vector(x, y, z));
			}
			
			private final int fromBound(final double pos, final double max) {
				return (pos - radius <= 0) ? -1 : (pos + radius >= max) ? 1 : 0;
			}
			
			public void draw(final Graphics g, final Vector position) {
				g.fillOval(
					(int)(position.x - radius), 
					(int)(position.y - radius), 
					(int)(radius * 2), 
					(int)(radius * 2));
			}
			
			public double bounding() {
				return radius;
			}
		};
	}
	
	public static final Shape square(final double side) {
		return new Shape() {

			@Override
			public Maybe<Vector> collide(final Vector position, final Vector bounds) {
				return Maybes.nothing();
			}
			
			public void draw(final Graphics g, final Vector position) {
				g.fillRect(
					(int)(position.x - side / 2),
					(int)(position.y - side / 2),
					(int)side,
					(int)side);
			}
			
			public double bounding() {
				return Vector.magnitude(new Vector(side / 2, side / 2, side / 2));
			}
		};
	}
}
