package boubouworld2;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class GravityTest
{
    public Point anchor1 = null, anchor2 = null, springMiddle;
    
	public static void main(String[] args)
	{
	    double globalTime = 0.0000;
	    
	    double distance, best = 0.0;
	    
	    //GO!
			
	    anchor1 = new CoordinateSystem();
		anchor1.setLimitsMotion(1000.0, 1000.0, 1000.0, 1000.0);
		anchor1.setLimitsPosition(-10000.0, 10000.0, -10000.0, 10000.0);
	    anchor2 = new CoordinateSystem();
		anchor2.setLimitsMotion(1000.0, 1000.0, 1000.0, 1000.0);
		anchor2.setLimitsPosition(-10000.0, 10000.0, -10000.0, 10000.0);
	    
	    anchor1.setPositionX(450.0);
	    anchor1.setPositionY(450.0);
	    
	    anchor1.setSpeedX(-50.0);
	    anchor1.setSpeedY(00.0);
	    
	    anchor2.setPositionX(anchor1.getPositionX() + 100.0);
	    anchor2.setPositionY(anchor1.getPositionY() + 0.0);
			
	    anchor2.setSpeedX(50.0);
	    anchor2.setSpeedY(00.0);
	    
		window = new WindowSpring("Spring Physics!");
		tension = new Coordinates();

	    while(true)
	    {
		window.paintImage(anchor1, anchor2);

		springPoint = setMiddle(anchor1, anchor2);

		distance = anchor1.getProximity(anchor2);
		tension.setTime(getTension(distance)/2.0);

		tension.setPositionX((springPoint.getPositionX()-anchor1.getPositionX())/distance * tension.getTime());
		tension.setPositionY((springPoint.getPositionY()-anchor1.getPositionY())/distance * tension.getTime());

		anchor1.setAccelX(tension.getPositionX());
		anchor1.setAccelY(tension.getPositionY());

		anchor2.setAccelX(-tension.getPositionX());
		anchor2.setAccelY(-tension.getPositionY());
		
		    anchor1.updateCoordinates();
		    anchor2.updateCoordinates();

		window.paintImage(anchor1, anchor2);
		
		if(distance > best)
		{
		    best = distance;
		    System.out.println("Longest stretch = " + best);
		}
		    
	    }
	    
	    
	    
	}
	
	
	public double getRandomNumber(double minimum, double maximum)
	{
	    return (Math.random() * (maximum - minimum) + minimum);
	}
	
	
	public static double getTension(double lenght)
	{                
	    double tension;
	    
		if(lenght > maxLenght)
		    breakSpring();
		else if(lenght >= minLenght)
		    tension = (lenght - normalLenght)*springConstant;
		else
		{
		    lenght = minLenght;
		    tension = (lenght - normalLenght)*springConstant;
		}
			    
	    return tension;
	}
	
	
	public static Coordinates setMiddle()
	{
	    return (new Coordinates(0.0, (anchor1.getPositionX()+anchor2.getPositionX())/2.0, (anchor1.getPositionY()+anchor2.getPositionY())/2.0));
	}
	
	      
	    
	
	
}
