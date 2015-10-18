package Reacts;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Coord;

@SuppressWarnings("serial")
public class SimulatorPanel extends JPanel {
	
	@SuppressWarnings("unused")
	private final Simulator simulator;
	
	public SimulatorPanel(final Simulator simulator) {
		this.simulator = simulator;
		this.setLayout(new BorderLayout());
		
		final JButton step = new JButton("Step");
		this.add(step, BorderLayout.SOUTH);
		step.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				simulator.step();
				repaint();
			}
		});
		
		final JPanel content = new JPanel() {
			public void paint(final Graphics g) {
				simulator.drawWhole(g);
			}
		};
		
		this.add(content, BorderLayout.CENTER);
		content.addMouseListener(new MouseAdapter() {
			public void mousePressed(final MouseEvent me) {
				simulator.hit(position(me));
			}
		});
	}
	
	private static Coord position(final MouseEvent me) {
		return new Coord(me.getX() / Block.size, me.getY() / Block.size);
	}
}
