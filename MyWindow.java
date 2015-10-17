package boubouworld;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class MyWindow extends JFrame
{
	private int frameWidth = 400;
	private int frameHeight = 300;
	private int frameLocationX = 100;
	private int frameLocationY = 100;
	
	private Panneau panel = new Panneau();
	private JButton bouton = new JButton("mon bouton");
	private JPanel container = new JPanel();
	
	public MyWindow(String s)
	{
	    this.setTitle(s);
	    this.setSize(frameWidth, frameHeight);
	    this.setLocation(frameLocationX, frameLocationY);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.add(panel, BorderLayout.CENTER);
		container.add(bouton, BorderLayout.SOUTH);
		
	    this.setContentPane(container);
	    this.setVisible(true);
	    
	    go();
	}
	
	
	public static void main(String[] args)
	{
	    MyWindow window = new MyWindow("Une fenetre!");
	
	}
	
	private void go()
	{
	    
	    while(true)
	    {
		int x = panel.getPosX(), y = panel.getPosY(), theta = panel.getAngle();
		int x2 = panel.getPosX2(), y2 = panel.getPosY2(), aX = panel.getAnchorX(), aY = panel.getAnchorY();
		
		panel.setAngle(theta+panel.getDirAngle());
		
		
		detectCollision(x, y, theta, panel);
		detectCollision(x2, y2, 180+theta, panel);
		
		aX += panel.getDirHori();
		aY += panel.getDirVert();
		theta += panel.getDirAngle();
		
		panel.setAnchorX(aX);
		panel.setAnchorY(aY);
		panel.setAngle(theta);
		
		panel.repaint();  
		try {
		    Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    } 
	    
	    
	}       

	    public void detectCollision(int posX, int posY, int theta, Panneau panel)
	    {                    
		if(theta >= 360)
		    theta -= 360;
		    
		if(posX <= 0)
		{
		    panel.setDirHori(1);
		    if(panel.getDirAngle() == 1)
			if(theta <= 180)
			    panel.setDirAngle(-1);
		    if(panel.getDirAngle() == -1)
			if(theta >= 180)
			    panel.setDirAngle(1);
		}
			    
		if(posX+10 >= panel.getWidth())
		{
		    panel.setDirHori(-1);
		    if(panel.getDirAngle() == 1)
			if(theta >= 270)
			    panel.setDirAngle(-1);
		    if(panel.getDirAngle() == -1)
			if(theta <= 90)
			    panel.setDirAngle(1);
		}
			    
		if(posY <= 0)
		{
		    panel.setDirVert(1);
		    if(panel.getDirAngle() == 1)
			if(theta <= 90)
			    panel.setDirAngle(-1);
		    if(panel.getDirAngle() == -1)
			if(theta >= 90)
			    panel.setDirAngle(1);
		}
			    
		if(posY+10 >= panel.getHeight()) 
		{
		    panel.setDirVert(-1);
		    if(panel.getDirAngle() == 1)
			if(theta <= 270)
			    panel.setDirAngle(-1);   
		    if(panel.getDirAngle() == -1)
			if(theta >= 270)
			    panel.setDirAngle(1);   
		}
	    }
}

