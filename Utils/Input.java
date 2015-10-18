package Utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import Functional.Action;
import Functional.Observable;
import Functional.Observer;
import Functional.Unit;
import Functional.Vector;
import Game.Direction;


public final class Input {

	private Input() { }

	public static final Observable<Direction> movement(final JFrame comp) {
	
		return new Observable<Direction>() {
			
			public Action<Unit> invoke(final Observer<Direction> observer) {
			
				final KeyListener l = new KeyListener() {
		
					@Override
					public void keyPressed(final KeyEvent ke) {
						
						switch(ke.getKeyCode()) {
							case KeyEvent.VK_UP : 
								observer.invoke(Direction.UP); return;
							case KeyEvent.VK_DOWN : 
								observer.invoke(Direction.DOWN); return;
							case KeyEvent.VK_LEFT :
								observer.invoke(Direction.LEFT); return;
							case KeyEvent.VK_RIGHT : 
								observer.invoke(Direction.RIGHT); return;
						}
					}
		
					public void keyReleased(KeyEvent arg0) { }
					public void keyTyped(KeyEvent arg0) { }
					
				};
				
				comp.addKeyListener(l);
				
				return new Action<Unit>() {
					
					public void invoke(final Unit u) {
						comp.removeKeyListener(l);
					}
				};
			}
		};
	}
	
	public static final Observable<Vector> mouseMovement(final JComponent component) {
		
		return new Observable<Vector>() {

			public Action<Unit> invoke(final Observer<Vector> listener) {
				
				final MouseMotionListener motion = new MouseMotionListener() {
					
					public void mouseDragged(final MouseEvent e) {
						listener.invoke(new Vector(e.getX(), e.getY()));						
					}

					public void mouseMoved(final MouseEvent e) {
						listener.invoke(new Vector(e.getX(), e.getY()));
					}
				};
				
				return new Action<Unit>() {
					
					public void invoke(final Unit t) {
						component.removeMouseMotionListener(motion);
					}
				};
			}
		};
	}
}
