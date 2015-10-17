package boubouworld2;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class WindowSpring extends JFrame
{
	private PanneauSpring panel = new PanneauSpring();
	
	public static final int FPS = 1000;
	public static final double DELAY = (int)(1.0/FPS) * 1000;
	
	private int frameX = 1000;
	private int frameY = 1000;
	private int frameZ = 1000;
	
	public WindowSpring(String s)
	{
	    this.setTitle(s);
	    this.setSize(frameX, frameY);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.setContentPane(panel);
	    this.setVisible(true);
	}
	
		
	public void paintImage(CoordinateSystem point1, CoordinateSystem point2)
	{
		panel.point1 = assignCoordinates(point1);
		panel.point2 = assignCoordinates(point2);
		
		panel.repaint();

	    if(DELAY >= 5)
	    {
		try {
		    Thread.sleep((long)(2));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	} 
	      

	public Coordinates assignCoordinates(CoordinateSystem point)
	{
	    return (new Coordinates(point.getTime(), point.getPositionX(), point.getPositionY()));
	}
	
	
}

