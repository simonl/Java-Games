package Reacts;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Coord;

@SuppressWarnings("serial")
public class EditorPanel extends JPanel {
	
	private final Editor editor;
	
	public EditorPanel(final Editor editor) {
		this.editor = editor;
		this.setLayout(new BorderLayout());
		
		final JPanel content = editor.view;
		
		this.add(content, BorderLayout.CENTER);
		
		final JPanel buttons = new JPanel();
		final JButton
			empty = new JButton("Empty"), 
			fire = new JButton("Fire"),
			bomb = new JButton("Bomb"),
			solid = new JButton("Solid");
		
		buttons.add(empty);
		buttons.add(solid);
		buttons.add(bomb);
		buttons.add(fire);
		
		this.add(buttons, BorderLayout.SOUTH);
		
		empty.addActionListener(select(Block.EMPTY));
		solid.addActionListener(select(Block.SOLID));
		bomb.addActionListener(select(Block.BOMB));
		fire.addActionListener(select(Block.FIRE));
		
		content.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(final MouseEvent me) {
				editor.moveTo(position(me));
				repaint();
			} 
			
			public void mouseDragged(final MouseEvent me) {
				editor.moveTo(position(me));
				editor.put();
				repaint();
			}
		});
		
		content.addMouseListener(new MouseAdapter() {
			public void mousePressed(final MouseEvent me) {
				editor.put();
				repaint();
			}
		});
	}
	
	private ActionListener select(final Block block) {
		return new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				editor.select(block);
			} 
		};
	}
	
	private static Coord position(final MouseEvent me) {
		return new Coord(me.getX() / Block.size, me.getY() / Block.size);
	}

	public Block[][] instance() {
		return editor.instance();
	}
}
