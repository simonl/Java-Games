package javaapps.test;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javaapps.physicsobject.*;

public class WindowMomentum2 extends JFrame
{
	public PanneauMomentum2 panel = new PanneauMomentum2();
	
	public static final int FPS = 1000;
	public static final double DELAY = (int)(1.0/FPS) * 1000;
	
	private int frameX = 700;
	private int frameY = 700;
	private int frameZ = 700;
	
	public WindowMomentum2(String s, int limitU, int limitD)
	{
	    this.setTitle(s);
	    this.setSize(frameX, frameY);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.setContentPane(panel);
	    this.setVisible(true);
	    
	    panel.realU = limitU;
	    panel.realD = limitD;
	}
	
		
	public void paintImage(double time)
	{                                  
		panel.time = time;
		
		panel.repaint();
	} 
	      
	public void linkBalls(Particle[] points)
	{
	    for(int i = 0; i < panel.num; i ++)
		panel.balls[i] = points[i].motion.position;
	}
	
	public void assignConstants(double kE, double pE, double p)
	{
	    panel.kineticEnergy = kE;
	    panel.potentialEnergy = pE;
	    panel.momentum = p;
	}
	
	public void setConstants()
	{
	    panel.lowestE = 9E10;
	    panel.lowestPE = 9E10;
	    panel.lowestKE = 9E10;
	    panel.lowestP = 9E10;
	    
	    panel.highestE = 0.0;
	    panel.highestPE = 0.0;
	    panel.highestKE = 0.0;
	    panel.highestP = 0.0;
	}
	
}

