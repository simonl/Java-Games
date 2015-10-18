import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class WindowTank extends JFrame
{
	private PanneauCannon panel = new PanneauCannon();
	public final double DELAY = 0.05;
	private int frameWidth = 1000;
	private int frameHeight = 900;

	public WindowTank(String s)
	{
	    this.setTitle(s);
	    this.setSize(frameWidth, frameHeight);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    this.setContentPane(panel);
	    this.setVisible(true);
	}


	public void paintImage(Tank tank1, Tank tank2)
	{
	    if(tank1 != null)
	    {
			panel.ball1 = assignCoordinates(tank1.ball1);
			panel.ball2 = assignCoordinates(tank1.ball2);
			panel.tank1 = assignCoordinates2(tank1);
				panel.lifeTank1 = (int)tank1.getHealth();
				panel.speedTank1 = (int)tank1.coordinates.getSpeedX();
	    }

	    if(tank2 != null)
	    {
			panel.ball3 = assignCoordinates(tank2.ball1);
			panel.ball4 = assignCoordinates(tank2.ball2);
			panel.tank2 = assignCoordinates2(tank2);
				panel.lifeTank2 = (int)tank2.getHealth();
				panel.speedTank2 = (int)tank2.coordinates.getSpeedX();
	    }

		panel.repaint();

		try {
		    Thread.sleep((long)(DELAY*1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public Coordinates assignCoordinates(Cannonball ball)
	{
		if(ball == null)
			return null;


	    return new Coordinates(
					ball.coordinates.getTime(),
	    			ball.coordinates.getPositionX(),
	    			ball.coordinates.getPositionY());

	}


	public Coordinates assignCoordinates2(Tank tank)
	{
	    return new Coordinates(
					tank.coordinates.getTime(),
	    			tank.coordinates.getPositionX(),
	    			tank.coordinates.getPositionY());

	}


}

