
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

import javax.swing.JApplet;

public class AppletTest {

	ControlledParticle player = new ControlledParticle();
	ControlledParticle player2 = new ControlledParticle();

	Particle[] balls = { new Particle(10000), new Particle() , new Particle(),
						 new Particle(), new Particle(), new Particle(),
						 new Particle(), player2, player};


	/*
	 *	MANAGER
	 */
	ParticleManager manager = new ParticleManager(balls);



	/*
	 *	LAYOUTS
	 */
	ParticleLayout layout = new GridLayout(balls);
	//ParticleLayout layout = new CircularLayout(balls);



	/*
	 *	CONTROLLERS
	 */
	//ParticleController controller = new ParticleDragger(player.motion);
	//ParticleController controller = new PointerProjectile(player.motion);
	ParticleController controller = new ElasticDragger(player.motion, 0.1);
	//KeyboardController controller = new KeyboardController(player.motion);
	KeyboardController controller2 = new KeyboardController(player2.motion);





	JPanel panel = new JPanel();
	BallPanel pane = new BallPanel();
	BallFrame frame = new BallFrame(panel);
	MenuPanel menu = new MenuPanel();
	JButton button = new JButton("RESTART");
	JButton stepButton = new JButton("STEP");
	JButton switchButton = new JButton("ON");


	public static void main(String[] args) {
		new AppletTest().go();
	}

	public AppletTest() {

				final  double g = 0;

				manager.setGravity(g);
				manager.setFriction(1);

				//balls[3].motion.velocity.updateComponents(0, 20, 50);

				layout.setOrigin(new CartesianVector(400, 400, 0));
				layout.addSpecialCase(balls[0], layout.getOrigin());
				layout.addSpecialCase(player, layout.getOrigin());
				layout.setStartingLevel(0);
				//layout.setAngles(Range.CIRCLE, Range.ZERO);
				layout.setup();

				panel.setFocusable(true);

				panel.setLayout(new BorderLayout());
				panel.add(pane, BorderLayout.CENTER);
				panel.add(button, BorderLayout.NORTH);
				panel.add(stepButton, BorderLayout.SOUTH);
				panel.add(switchButton, BorderLayout.EAST);
				//panel.add(menu, BorderLayout.EAST);

				button.addActionListener(new RestartButton());
				stepButton.addActionListener(new Stepper());
				switchButton.addActionListener(new Stopper());
				//controller.setPush();

				player.addController(controller);
				player2.addController(controller2);
				//controller.targetAcceleration();
				controller2.targetAcceleration();

				player.setComponent(pane);
				player2.setComponent(pane);

				//controller.setControls(KeyboardController.Controls.OVERHEAD2);
				controller2.setControls(KeyboardController.Controls.OVERHEAD1);

				//controller.setAmount(50);
				controller2.setAmount(50);

				//Custom changes
				balls[0].representation.setOpaque(false);
				balls[0].dimensions = new Dimensions(350, 350, 350);
				balls[4].dimensions = new Dimensions(50, 45, 30);
				balls[0].motion.innerA.setToComponents(0, -g, 0);
				player2.dimensions = new Dimensions(60, 40, 20);

	}

	boolean on = true;


	public void go() {


		do{


			if(on)
				manager.update();


			if(manager.shouldRedraw())
				frame.paintImage();


		}while(true);
	}


	private class BallPanel extends JPanel {

		public void paint(Graphics g) {
			super.paint(g);

			manager.draw(g);
		    g.drawString("STATS : " + manager, 10, 30);
		    g.drawString("KE : " + manager.getKineticEnergy(), 10, 50);
		    g.drawString("MOM : " + manager.getMomentum(), 10, 70);

		}
	}

	private class MenuPanel extends JPanel {

		public MenuPanel() {
			this.setVisible(true);
		}

		public void paint(Graphics g) {
			g.setColor(Color.BLACK);
		    g.drawString("STATS : " + manager, 10, 30);
		    g.drawString("KE : " + manager.getKineticEnergy(), 10, 50);
		    g.drawString("MOM : " + manager.getMomentum(), 10, 70);
		}
	}

	private class BallFrame extends JFrame {

		public BallFrame(JPanel panel) {
	    	this.setTitle("BALLS");
			this.setSize(1000, 750);
	    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	this.setContentPane(panel);
	    	this.setVisible(true);
		}

		public void paintImage()
		{
				panel.repaint();

		}
	}

	private class RestartButton implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			on = true;
			manager.stop();
			layout.setup();
		}
	}

	public class Stepper implements ActionListener {

		public void actionPerformed(ActionEvent ae) {
			manager.update();
			frame.paintImage();
		}
	}

	public class Stopper implements ActionListener {

		public void actionPerformed(ActionEvent ae) {
			on = !on;
			switchButton.setText(on ? "ON" : "OFF");
		}
	}
}
