package boubouworld2;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class PanneauMomentum extends JPanel
{
	// public Vector ball1 = new Vector(), ball2 = new Vector(), ball3 = new Vector(), ball4 = new Vector();
	public double ballR = 50.0;
	public String kineticEnergy, momentum;
	public int num, limit;
	public Vector[] balls = null;
	
	public void paintComponent(Graphics g){
	
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2d.setColor(Color.red);
		    g2d.drawRect(0, 0, limit, limit);
		
		
		    // g2d.fillOval((int)(ball1.getX()-ballR), (int)(ball1.getY()-ballR), (int)(ballR*2.0), (int)(ballR*2.0));
		    // g2d.fillOval((int)(ball2.getX()-ballR), (int)(ball2.getY()-ballR), (int)(ballR*2.0), (int)(ballR*2.0)); 
		    // g2d.fillOval((int)(ball3.getX()-ballR), (int)(ball3.getY()-ballR), (int)(ballR*2.0), (int)(ballR*2.0)); 
		    // g2d.fillOval((int)(ball4.getX()-ballR), (int)(ball4.getY()-ballR), (int)(ballR*2.0), (int)(ballR*2.0)); 
		    
		    
		    
		for(int i = 0; i < num; i ++)
		{
		    g2d.fillOval((int)(balls[i].getX()-ballR), (int)(balls[i].getY()-ballR), (int)(ballR*2.0), (int)(ballR*2.0));
		}
		
		    if(kineticEnergy != null)
			g2d.drawString(kineticEnergy, 10, 20);
		    if(momentum != null)
		    g2d.drawString(momentum, 10, 35);
		      // this.getHeight()-
	}
 
	
} // Panneau class
