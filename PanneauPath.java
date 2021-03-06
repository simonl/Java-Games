package boubouworld;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class PanneauPath extends JPanel
{
	public Coordinates point1 = null, point2 = null;
	public double energy = 0.0, best = 100.0;
	
	public PanneauPath()
	{
	    
	}
	
	
	public void paintComponent(Graphics g)
	{                   
	    int width = this.getWidth(), height = this.getHeight();
	    Graphics2D g2d = (Graphics2D) g;
	    
	    
	    
	    if(point1 != null)
	    {
		g2d.setColor(Color.BLACK);
		g2d.drawLine((int)point1.getPositionX(), (int)point1.getPositionY(), (int)point2.getPositionX(), (int)point2.getPositionY());
		g2d.fillRect(0,0,200,20);
		
		g2d.setColor(Color.RED);
		g2d.fillOval(445, 445, 10, 10);
		g2d.fillOval(645, 445, 10, 10);
		
		g2d.setColor(Color.WHITE);
		g2d.drawString("Energy = " + energy, 0, 15);
	    }
	    
	    
	}
       

} // Panneau class
