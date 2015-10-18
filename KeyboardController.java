
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;


public class KeyboardController extends ParticleController {



	public static class Controls {

		public static final Controls OVERHEAD2 = new Controls(KeyBinding.UP_UP, KeyBinding.DOWN_DOWN,
										  KeyBinding.LEFT_LEFT, KeyBinding.RIGHT_RIGHT);
		public static final Controls OVERHEAD1 = new Controls(KeyBinding.W_UP, KeyBinding.A_LEFT,
										  KeyBinding.S_DOWN, KeyBinding.D_RIGHT);

		public static final Controls SIDESCROLL1 = new Controls(KeyBinding.A_LEFT, KeyBinding.D_RIGHT,
										  KeyBinding.SPACE_JUMP);

		private final KeyBinding[] bindings;
		private final boolean[] isDown;

		public Controls(KeyBinding... bindings) {
			this.bindings = bindings;
			this.isDown = new boolean[bindings.length];
		}

		public void press(int key, Vector target) {
			for(int i = 0; i < bindings.length; i++)
				if(bindings[i].maps(key) && !isDown[i]) {
					bindings[i].getAction().act(target);
					isDown[i] = true;
				}
		}

		public void release(int key, Vector target) {
			for(int i = 0; i < bindings.length; i++)
				if(bindings[i].maps(key) && isDown[i]) {
					bindings[i].getAction().undo(target);
					isDown[i] = false;
				}
		}


		public boolean isDown(int key) {
			for(int i = 0; i < bindings.length; i++)
				if(bindings[i].maps(key))
					return isDown[i];
			return false;
		}

		public Action get(int key) {
			for(KeyBinding bK : bindings)
				if(bK.maps(key))
					return bK.getAction();
			return Action.UNDEFINED;
		}
	}

	public enum Action {
		LEFT(new CartesianVector(-1, 0, 0)),
		RIGHT(new CartesianVector(1, 0, 0)),
		UP(new CartesianVector(0, -1, 0)),
		DOWN(new CartesianVector(0, 1, 0)),
		BACKWARD(new CartesianVector(0, 0, -1)),
		FORWARD(new CartesianVector(0, 0, 1)),
		JUMP(new CartesianVector(0, -1, 0)),

		UNDEFINED(new Vector());

		private final Vector effect;
		private double scale = 50;

		private Action(Vector effect) {
			this.effect = effect;
		}

		private Vector scaledEffect() {
			return effect.scalarProduct(scale);
		}

		public void act(Vector target) {
			if(this == JUMP) {
				System.out.println(target.getY());
				if(Math.abs(target.getY()) < 1.0E-5)
					return;

			}
			target.updateComponents(scaledEffect());
		}

		public void undo(Vector target) {
			final double eX = effect.getX();
			final double eY = effect.getY();
			final double eZ = effect.getZ();

			final double tX = target.getX();
			final double tY = target.getY();
			final double tZ = target.getZ();

			final boolean X = eX != 0 && Math.signum(eX) == Math.signum(tX);
			final boolean Y = eY != 0 && Math.signum(eY) == Math.signum(tY);
			final boolean Z = eZ != 0 && Math.signum(eZ) == Math.signum(tZ);


			target.setToComponents(
				X ? 0 : tX,
				Y ? 0 : tY,
				Z ? 0 : tZ );
		}
	}

	public static class KeyBinding {

		public static final KeyBinding UP_UP =
							new KeyBinding(KeyEvent.VK_UP, Action.UP);
		public static final KeyBinding DOWN_DOWN =
							new KeyBinding(KeyEvent.VK_DOWN, Action.DOWN);
		public static final KeyBinding LEFT_LEFT =
							new KeyBinding(KeyEvent.VK_LEFT, Action.LEFT);
		public static final KeyBinding RIGHT_RIGHT =
							new KeyBinding(KeyEvent.VK_RIGHT, Action.RIGHT);
		public static final KeyBinding SPACE_JUMP =
							new KeyBinding(KeyEvent.VK_SPACE, Action.JUMP);
		public static final KeyBinding UP_FORWARD =
							new KeyBinding(KeyEvent.VK_UP, Action.FORWARD);
		public static final KeyBinding DOWN_BACKWARD =
							new KeyBinding(KeyEvent.VK_DOWN, Action.BACKWARD);

		public static final KeyBinding W_UP =
							new KeyBinding(KeyEvent.VK_W, Action.UP);
		public static final KeyBinding S_DOWN =
							new KeyBinding(KeyEvent.VK_S, Action.DOWN);
		public static final KeyBinding A_LEFT =
							new KeyBinding(KeyEvent.VK_A, Action.LEFT);
		public static final KeyBinding D_RIGHT =
							new KeyBinding(KeyEvent.VK_D, Action.RIGHT);

		private final int key;
		private final Action value;

		public KeyBinding(int key, Action value) {
			this.key = key;
			this.value = value;
		}

		public boolean maps(int key) {
			return this.key == key;
		}

		public Action getAction() {
			return this.value;
		}
	}


	Controls controls = Controls.OVERHEAD1;


	double amount = 10;
	Vector target = velocity;

	public void setControls(Controls controls) {
		this.controls = controls;
	}

	public void setAmount(double d) {
		this.amount = d;
	}

	public void targetVelocity() {
		this.target = velocity;
	}

	public void targetAcceleration() {
		this.target = innerA;
	}

	public KeyboardController(CoordinateSystem motion) {
		super(motion);
	}

	public void stayCurrent() {

	}

	public boolean shouldUpdate() {
		return true;
	}

	public boolean shouldCollide() {
		return true;
	}

	public boolean hasDrawing() {
		return false;
	}

	public void draw(Graphics g) {

	}

	public void mouseClicked(MouseEvent e) {
		container.requestFocusInWindow();
	}

	public void keyReleased(KeyEvent k) {

		Action a = controls.get(k.getKeyCode());

		controls.release(k.getKeyCode(), a == Action.JUMP ? velocity : innerA);
	}

	public void keyPressed(KeyEvent k) {

		Action a = controls.get(k.getKeyCode());

		controls.press(k.getKeyCode(), a == Action.JUMP ? velocity : innerA);
	}

	public void keyTyped(KeyEvent k) {

	}
}