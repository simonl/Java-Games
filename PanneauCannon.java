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
	private int width = this.getWidth(), groundLevel = this.getHeight() - 100;
	
	public PanneauCannon()
	{
	    
	}
	
	
	public void paintComponent(Graphics g)
	{                   
	    Graphics2D g2d = (Graphics2D) g;
	    
	    drawArea(g2d);
			
	    drawPlayer(g2d, tank1, 1, 0);
	    drawPlayer(g2d, tank2, 2, width-playerBoxSide);
	    
	    drawBall(g2d, ball1, 1);
	    drawBall(g2d, ball2, 1);
	    drawBall(g2d, ball3, 2);
	    drawBall(g2d, ball4, 2);
	}

	public void drawArea(Graphics2D g2d)
	{
	    g2d.setPaint(new GradientPaint(0, 0, Color.BLUE, 0, groundLevel, Color.cyan, true));
	    g2d.fillRect(0, 0, width, groundLevel);
	    
	    g2d.setPaint(new GradientPaint(0, 0, Color.GREEN, 0, 100, Color.BLACK, true));
	    g2d.fillRect(0, groundLevel, width, 100);
	    
	    g2d.setColor(Color.GRAY);
	    g2d.fillRect(0, 0, 10, groundLevel);
	    g2d.fillRect(width-10, 0, 10, groundLevel);
	}
	
	public void drawPlayer(Graphics2D g2d, Coordinates tank, int number, int boxLeft)
	{
	    int lifeTank, speedTank;
	
	    if(tank != null) 
	    {
	    
		if(number == 1)
		{
		    g2d.setColor(Color.RED);
		    lifeTank = (int)lifeTank1;
		    speedTank = (int)speedTank1;
		}
		else
		{
		    g2d.setColor(Color.ORANGE);
		    lifeTank = (int)lifeTank2;
		    speedTank = (int)speedTank2;
		}
		
		g2d.fillRect((int)tank.getPositionX()-tankL/2, groundLevel-tankH, tankL, tankH);
	
		    g2d.setPaint(new GradientPaint(0, 0, g2d.getColor(), playerBoxSide, playerBoxSide, Color.BLACK, true));
		g2d.fillRect(boxLeft, 0, playerBoxSide, playerBoxSide);
			
		    g2d.setColor(Color.BLACK);
		g2d.drawString("" + number, (int)tank.getPositionX(), groundLevel);
		g2d.drawString("        TANK " + number, boxLeft + spacing, textH+spacing);
		g2d.drawString("POSITION : " + (int)tank.getPositionX(), boxLeft + spacing, 2*textH+3*spacing);
		g2d.drawString("HEALTH   : " + lifeTank, boxLeft + spacing, 3*textH+4*spacing);
		g2d.drawString("SPEED    : " + speedTank, boxLeft + spacing, 4*textH+5*spacing);
	    }
	}
	
	public void drawBall(Graphics2D g2d, Coordinates ball, int number)
	{
	    if(ball != null)
	    {
	    
		if(number == 1)
		    g2d.setColor(Color.RED);
		else
		    g2d.setColor(Color.ORANGE);
		
		g2d.fillOval(((int)ball.getPositionX())-ballR/2, groundLevel-(int)ball.getPositionY()-ballR/2, ballR, ballR);
		
		    g2d.setColor(Color.BLACK);
		g2d.drawString("" + number, (int)ball.getPositionX(), groundLevel-(int)ball.getPositionY()+10);
	    }
	    
	}
	
	

} // Panneau class
