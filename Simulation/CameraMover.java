package Simulation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Functional.Ref;
import Functional.Vector;
import Functional.Writeable;
import Utils.Refs;

public class CameraMover extends MouseAdapter {

	private final Ref<Vector> wcamera;

	public CameraMover(final Ref<Vector> wcamera) {
		this.wcamera = wcamera;
	}

	private Ref<Vector> mouseOrigin = Refs.box(null);
	private Ref<Vector> camOrigin = Refs.box(null);
	
	@Override
	public void mouseDragged(final MouseEvent e) {
		final Vector start = mouseOrigin.read();
		final Vector origin = camOrigin.read();
		if(start == null) return;
		
		wcamera.write(new Vector(origin.x - (e.getX() - start.x), origin.y - (e.getY() - start.y)));
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		this.mouseOrigin.write(new Vector(e.getX(), e.getY()));
		this.camOrigin.write(wcamera.read());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.mouseOrigin.write(null);
	}
}
