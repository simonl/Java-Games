package Reacts;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public final class React extends JFrame {
		
	private static final int size = 20;
	
	private final EditorPanel editor = new EditorPanel(new Editor(size));
	
	private final JPanel content = new JPanel(new BorderLayout());
	
	public static void main(final String... args) {
		new React();
	}
	
	private React() {
		this.setVisible(true);
		this.setSize(size * Block.size + 50, size * Block.size + 50);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		this.setContentPane(editor);
		
		final JButton simulate = new JButton("Simulate");
		this.add(simulate, BorderLayout.EAST);
		
		simulate.addActionListener(new ActionListener() { 
			public void actionPerformed(final ActionEvent ae) {
				final SimulatorPanel sim = new SimulatorPanel(new Simulator(editor.instance()));
				setContentPane(sim);

				final JButton edit = new JButton("Edit");
				add(edit, BorderLayout.EAST);

				edit.addActionListener(new ActionListener() { 
					public void actionPerformed(final ActionEvent ae) {

						setContentPane(editor);
					}
				});
				
				repaint();
			}
		});
		
	}
	
	private void simulate() {
		final SimulatorPanel sim = new SimulatorPanel(new Simulator(editor.instance()));
		setContentPane(sim);
	}
	
	private void edit() {
		setContentPane(editor);
	}
}
