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
	
	private int frameX = 1000;
	private int frameY = 1000;
	private int frameZ = 1000;
	
	public WindowMomentum2(String s, int limitU, int limitD)
	{
	    this.setTitle(s);
	    this.setSize(950, 950);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.setContentPane(panel);
	    this.setVisible(true);
	    
	    panel.realU = limitU;
	    panel.realD = limitD;
	}
	
		
	public void paintImage(Particle[] points, int num, double time)
	{
		if(panel.balls == null)
		{
		    panel.num = num;
		    panel.balls = new Vector[num];
		}
		
		for(int i = 0; i < num; i ++)
		{
		    panel.balls[i] = assignCoordinates(points[i].motion.position.copy());
		}
		
		panel.time = time;
		
		// panel.ball1 = assignCoordinates(point1);
		// panel.ball2 = assignCoordinates(point2);
		// panel.ball3 = assignCoordinates(point3);
		// panel.ball4 = assignCoordinates(point4);
		
		panel.repaint();
	    // 
	    // if(DELAY >= 5)
	    // {
	    //     try {
	    //         Thread.sleep((long)(5));
	    //     } catch (InterruptedException e) {
	    //             // TODO Auto-generated catch block
	    //             e.printStackTrace();
	    //     }
	    // }
	} 
	      

	public Vector assignCoordinates(Vector point)
	{
	    return (new CartesianVector(point.getX(), point.getY(), point.getZ()));
	}
	
	public void assignConstants(double kE, double p)
	{
	    panel.kineticEnergy = kE;
	    panel.momentum = p;
	}
	
	public void setConstants()
	{
	    panel.lowestE = 9E10;
	    panel.lowestP = 9E10;
	    panel.highestE = 0.0;
	    panel.highestP = 0.0;
	}
	
}

