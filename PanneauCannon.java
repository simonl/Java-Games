import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class PanneauCannon extends JPanel
{
	public Coordinates ball1 = null, ball2 = null, ball3 = null, ball4 = null;
	public Coordinates tank1 = null, tank2 = null;
	public int lifeTank1, lifeTank2, speedTank1, speedTank2;


	private static final int playerBoxSide = 100, playerBox2 = 900, tankL = 20, tankH = 10, ballR = 10, textH = 10 , spacing = 5;

	private int width = 1000;
	private int groundLevel = 800;


	public PanneauCannon()
	{

	}

	private static final GradientPaint sky		= new GradientPaint(0, 0, Color.BLUE, 0, 800, Color.cyan, true);
	private static final GradientPaint ground	= new GradientPaint(0, 0, Color.GREEN, 0, 200, Color.BLACK, true);
	private static final GradientPaint box1		= new GradientPaint(0, 0, Color.RED, playerBoxSide+50, playerBoxSide+50, Color.BLACK, true);
	private static final GradientPaint box2		= new GradientPaint(playerBoxSide+50, 0, Color.ORANGE, 0, playerBoxSide+50, Color.BLACK, true);

	public void paintComponent(Graphics g)
	{
		this.width = this.getWidth();
		this.groundLevel = this.getHeight() - 100;

	    Graphics2D g2d = (Graphics2D) g;
	    GradientPaint gp;

	    g2d.setPaint(sky);
	    g2d.fillRect(0, 0, width, groundLevel);

	    g2d.setPaint(ground);
	    g2d.fillRect(0, groundLevel, width, 100);

	    g2d.setColor(Color.GRAY);
	    g2d.fillRect(0, 0, 10, groundLevel);
	    g2d.fillRect(width-10, 0, 10, groundLevel);



		drawTank(g2d, tank1, 0, box1, Color.RED);

		for(Coordinates ball : new Coordinates[]{ ball1, ball2 })
			drawBall(g2d, ball);

		drawTank(g2d, tank2, width-playerBoxSide, box2, Color.ORANGE);

		for(Coordinates ball : new Coordinates[]{ ball3, ball4 })
			drawBall(g2d, ball);



		g2d.setColor(Color.BLACK);

		writeStats(g2d, tank1, "1", spacing, lifeTank1, speedTank1);

		for(Coordinates ball : new Coordinates[]{ ball1, ball2 })
			writeBall(g2d, ball, "1");

		writeStats(g2d, tank2, "2", width-playerBoxSide+spacing, lifeTank2, speedTank2);

		for(Coordinates ball : new Coordinates[]{ ball3, ball4 })
			writeBall(g2d, ball, "2");

	}

	public void drawBall(Graphics2D g2d, Coordinates ball) {
		if(ball == null)
			return;

		g2d.fillOval(
			((int)ball.getPositionX())-ballR/2,
			groundLevel-(int)ball.getPositionY()-ballR/2,
			ballR,
			ballR);
	}

	public void writeBall(Graphics2D g2d, Coordinates ball, String id) {
		if(ball == null)
			return;

		g2d.drawString(
			id,
			(int)ball.getPositionX(),
			groundLevel-(int)ball.getPositionY()+10);
	}

	public void drawTank(Graphics2D g2d, Coordinates tank, int boxPosition, GradientPaint gp, Color color) {
		if(tank == null)
			return;

		g2d.setPaint(gp);
		g2d.fillRect(boxPosition, 0, playerBoxSide, playerBoxSide);

		g2d.setColor(color);
		g2d.fillRect((int)tank.getPositionX()-tankL/2, groundLevel-tankH, tankL, tankH);

	}
	public void writeStats(Graphics2D g2d, Coordinates tank, String id, int position, int health, int speed) {
		if(tank == null)
			return;

		g2d.drawString(id, (int)tank.getPositionX(), groundLevel);
		g2d.drawString("        TANK " + id, position, textH + spacing);
		g2d.drawString("POSITION : " + (int)tank.getPositionX(), position, 2*textH + 3*spacing);
		g2d.drawString("HEALTH   : " + health, position, 3*textH + 4*spacing);
		g2d.drawString("SPEED    : " + speed, position, 4*textH + 5*spacing);
	}

} // Panneau class
