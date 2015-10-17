package boubouworld2;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class WindowMomentum extends JFrame
{
	private PanneauMomentum panel = new PanneauMomentum();
	
	public static final int FPS = 1000;
	public static final double DELAY = (int)(1.0/FPS) * 1000;
	
	private int frameX = 1000;
	private int frameY = 1000;
	private int frameZ = 1000;
	
	public WindowMomentum(String s)
	{
	    this.setTitle(s);
	    this.setSize(frameX, frameY);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.setContentPane(panel);
	    this.setVisible(true);
	}
	
		
	public void paintImage(Vector point1, Vector point2, Vector point3, Vector point4)
	{
		panel.ball1 = assignCoordinates(point1);
		panel.ball2 = assignCoordinates(point2);
		panel.ball3 = assignCoordinates(point3);
		panel.ball4 = assignCoordinates(point4);
		
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
	    panel.kineticEnergy = "" + kE;
	    panel.momentum = "" + p;
	}
	
}

