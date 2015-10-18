
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

public class Representation {

	private Color color;
	private boolean isOpaque;
	private Particle host;
	private Dimensions used;
	private BufferedImage image;

	public Representation(Particle host) {
		this(host, Color.BLUE);
	}

	public Representation(Particle host, Color color) {
		this(host, color, true);
	}

	public Representation(Particle host, Color color, boolean isOpaque) {
		this.host = host;
		this.used = host.dimensions;
		this.color = color;
		this.isOpaque = isOpaque;

		setImage();
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setOpaque(boolean b) {
		this.isOpaque = b;
	}

	public void setImage() {
		int x, y;
		boolean sphere = used.isASphere();
		if(sphere) {
			x = y = (int)(2 * used.getR());
		}
		else {
			x = (int)(2 * used.getX());
			y = (int)(2 * used.getY());
		}

		image = new BufferedImage(x + 1, y + 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setColor(color);

		Shape shape = sphere ? new Ellipse2D.Double(0, 0, x, y) : new Rectangle(0, 0, x, y);

		if(isOpaque)
				g.fill(shape);
		else
				g.draw(shape);
	}

	public Image getImage() {
		if(!used.equals(host.dimensions)) {
			this.used = host.dimensions;
			setImage();
		}

		return image;
	}

	public Image getImage(Vector angle) {
		if(!used.equals(host.dimensions)) {
			this.used = host.dimensions;
			setImage();
		}


		int x, y;
		boolean sphere = used.isASphere();
		if(sphere) {
			x = y = Math.abs((int)(Math.atan(used.getR() / angle.getMagnitude())));
		}
		else {
			x = (int)(2 * used.getX());
			y = (int)(2 * used.getY());
		}

		BufferedImage image = new BufferedImage(x + 1, y + 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setColor(color);

		Shape shape = sphere ? new Ellipse2D.Double(0, 0, x, y) : new Rectangle(0, 0, x, y);

		if(isOpaque)
				g.fill(shape);
		else
				g.draw(shape);

		return image;
	}
}