package Functional;

import java.awt.Graphics;

public interface Shape {
	Maybe<Vector> collide(final Vector position, final Vector bounds);
	void draw(final Graphics g, final Vector position);
	double bounding();
}
