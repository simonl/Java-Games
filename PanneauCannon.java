package boubouworld;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class PanneauCannon extends JPanel
{
	public Coordinates ball1 = null, ball2 = null, ball3 = null, ball4 = null;
	public Coordinates tank1 = null, tank2 = null;
	public double lifeTank1, lifeTank2, speedTank1, speedTank2;
	
	private int playerBoxSide = 100, tankL = 20, tankH = 10, ballR = 10, textH = 10 , spacing = 5;
	
	public PanneauCannon()
	{
	    
	}
	
	
	public void paintComponent(Graphics g)
	{   
	    int width = this.getWidth();
	    int groundLevel = this.getHeight() - 100;
	    
	    Graphics2D g2d = (Graphics2D) g;
	    GradientPaint gp = new GradientPaint(0, 0, Color.BLUE, 0, groundLevel, Color.cyan, true);
	    
	    g2d.setPaint(gp);
	    g2d.fillRect(0, 0, width, groundLevel);
	    
	    gp = new GradientPaint(0, 0, Color.GREEN, 0, 100, Color.BLACK, true);
	    
	    g2d.setPaint(gp);
	    g2d.fillRect(0, groundLevel, width, 100);
	    
	    g2d.setColor(Color.GRAY);
	    g2d.fillRect(0, 0, 10, groundLevel);
	    g2d.fillRect(width-10, 0, 10, groundLevel);
	    
			
	if(tank1 != null) {
		gp = new GradientPaint(0, 0, Color.RED, playerBoxSide, playerBoxSide, Color.BLACK, true);
		g2d.setPaint(gp);
	    g2d.fillRect(0, 0, playerBoxSide, playerBoxSide);
	    
		g2d.setColor(Color.RED);
	    g2d.fillRect((int)tank1.getPositionX()-tankL/2, groundLevel-tankH, tankL, tankH);
	    }
	    
	if(ball1 != null)
	    g2d.fillOval(((int)ball1.getPositionX())-ballR/2, groundLevel-(int)ball1.getPositionY()-ballR/2, ballR, ballR);
	    
	if(ball2 != null)
	    g2d.fillOval(((int)ball2.getPositionX())-ballR/2, groundLevel-(int)ball2.getPositionY()-ballR/2, ballR, ballR);

			
	if(tank2 != null) {
		gp = new GradientPaint(width, 0, Color.ORANGE, width-playerBoxSide, playerBoxSide, Color.BLACK, true);
		g2d.setPaint(gp);
	    g2d.fillRect(width-playerBoxSide, 0, playerBoxSide, playerBoxSide);
	    
		g2d.setColor(Color.ORANGE);
	    g2d.fillRect((int)tank2.getPositionX()-tankL/2, groundLevel-tankH, tankL, tankH);
	    }
	    
	if(ball3 != null)
	    g2d.fillOval(((int)ball3.getPositionX())-ballR/2, groundLevel-(int)ball3.getPositionY()-ballR/2, ballR, ballR);
	    
	if(ball4 != null)
	    g2d.fillOval(((int)ball4.getPositionX())-ballR/2, groundLevel-(int)ball4.getPositionY()-ballR/2, ballR, ballR);



	    g2d.setColor(Color.BLACK);

	if(tank1 != null)
	{
	    g2d.drawString("1", (int)tank1.getPositionX(), groundLevel);
	    g2d.drawString("        TANK 1", spacing, textH+spacing);
	    g2d.drawString("POSITION : " + (int)tank1.getPositionX(), spacing, 2*textH+3*spacing);
	    g2d.drawString("HEALTH   : " + (int)lifeTank1, spacing, 3*textH+4*spacing);
	    g2d.drawString("SPEED    : " + (int)speedTank1, spacing, 4*textH+5*spacing);
	}
	if(ball1 != null)
	    g2d.drawString("1", (int)ball1.getPositionX(), groundLevel-(int)ball1.getPositionY()+10);
	if(ball2 != null)
	    g2d.drawString("1", (int)ball2.getPositionX(), groundLevel-(int)ball2.getPositionY()+10);

	if(tank2 != null)
	{
	    g2d.drawString("2", (int)tank2.getPositionX(), groundLevel);
	    g2d.drawString("        TANK 2", width-playerBoxSide+spacing, textH+spacing);
	    g2d.drawString("POSITION : " + (int)tank2.getPositionX(), width-playerBoxSide+spacing, 2*textH+3*spacing);
	    g2d.drawString("HEALTH   : " + (int)lifeTank2, width-playerBoxSide+spacing, 3*textH+4*spacing);
	    g2d.drawString("SPEED    : " + (int)speedTank2, width-playerBoxSide+spacing, 4*textH+5*spacing);
	}
	if(ball3 != null)
	    g2d.drawString("2", (int)ball3.getPositionX(), groundLevel-(int)ball3.getPositionY()+10);
	if(ball4 != null)
	    g2d.drawString("2", (int)ball4.getPositionX(), groundLevel-(int)ball4.getPositionY()+10);

	    
	}


} // Panneau class
