package javaapps.test;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javaapps.physicsobject.Coordinates;

public class WindowSpring extends JFrame
{
	private PanneauSpring panel = new PanneauSpring();
	private PanneauPath panel2 = new PanneauPath();
	
	public static final int FPS = 100;
	public static final double DELAY = 1.0/FPS;
	
	private int frameWidth = 1000;
	private int frameHeight = 900;
	
	public WindowSpring(String s)
	{
	    this.setTitle(s);
	    this.setSize(frameWidth, frameHeight);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.setContentPane(panel);
	    this.setVisible(true);
	}
	
		
	public void paintImage(CoordinateSystemGravity point1, CoordinateSystemGravity point2, double energy, double best)
	{
		panel.point1 = assignCoordinates(point1);
		panel.point2 = assignCoordinates(point2);
		panel.energy = energy;
		panel.best = best;
		
		panel2.energy = energy;
		panel2.best = best;
		    
		if(panel2.point2 == null)
		{
		    panel2.point2 = new Coordinates();
		}
		else
		{
		    panel2.point1.setX(panel2.point2.getX());
		    panel2.point1.setY(panel2.point2.getY());
		}
		    panel2.point2 = assignCoordinates(point2);
		    
		panel2.repaint();
		    
		if(panel2.point1 == null)
		    panel2.point1 = new Coordinates();
		    
		// try {
		//     Thread.sleep((long)(10));
		// } catch (InterruptedException e) {
		//         // TODO Auto-generated catch block
		//         e.printStackTrace();
		// }

	} 
	      

	public Coordinates assignCoordinates(CoordinateSystemGravity point)
	{
	    return (new Coordinates(point.getPositionX(), point.getPositionY(), point.getTime()));
	}
	
	
}

