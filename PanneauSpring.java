package boubouworld;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class PanneauSpring extends JPanel
{
	public Coordinates point1 = null, point2 = null;
	public double energy = 0.0, best = 100.0;
	
	public PanneauSpring()
	{
	    
	}
	
	
	public void paintComponent(Graphics g)
	{                   
	    int width = this.getWidth(), height = this.getHeight();
	    Graphics2D g2d = (Graphics2D) g;
	    
	    g2d.setColor(Color.WHITE);
	    g2d.fillRect(0, 0, width, height);
	    
	    g2d.setColor(Color.RED);
	    g2d.fillOval((int)point1.getPositionX()-5, (int)point1.getPositionY()-5, 10, 10);
	    
	    g2d.setColor(Color.BLUE);
	    g2d.fillOval((int)point2.getPositionX()-5, (int)point2.getPositionY()-5, 10, 10);
	    
	    g2d.setColor(Color.BLACK);
	    g2d.drawLine((int)point1.getPositionX(), (int)point1.getPositionY(), (int)point2.getPositionX(), (int)point2.getPositionY());
	    
	    g2d.drawString("E = " + energy, (int)point1.getPositionX()+50, (int)point1.getPositionY());
	    
	    g2d.drawLine((int)point2.getPositionX()-50, (int)best, (int)point2.getPositionX()+50, (int)best);
	    
	    g2d.drawString(""+best, (int)point2.getPositionX()+55, (int)best+5);
	}
       

} // Panneau class
