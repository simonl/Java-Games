package javaapps.test;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javaapps.physicsobject.Coordinates;

public class GravityTest
{
	public static void main(String[] args)
	{            
	    int COUNTER = 0;
	    
	    CoordinateSystemGravity point1 = null, point2 = null, point3 = null;
	    Coordinates springPoint = null, tension = null;
	    WindowSpring window = null;
	    
	    double distance, distance2, best = 0.0, mass = 4000000.0, mass2 = 4000000.0;
	    double E, Ek, Ep, elongation, coefficient;
	    
	    //GO!
			
	    point1 = new CoordinateSystemGravity();
		point1.setLimitsMotion(000.0, 000.0, 000.0, 000.0);
		point1.setLimitsPosition(-10000.0, 10000.0, -10000.0, 10000.0);
	    point3 = new CoordinateSystemGravity();
		point3.setLimitsMotion(000.0, 000.0, 000.0, 000.0);
		point3.setLimitsPosition(-10000.0, 10000.0, -10000.0, 10000.0);
		
	    point2 = new CoordinateSystemGravity();
		point2.setLimitsMotion(1000.0, 1000.0, 1000.0, 1000.0);
		point2.setLimitsPosition(-10000.0, 10000.0, -10000.0, 10000.0);
	    
	    point1.setPositionX(450.0);
	    point1.setPositionY(450.0);
	    
	    point1.setSpeedX(00.0);
	    point1.setSpeedY(00.0);
	    
	    point3.setPositionX(650.0);
	    point3.setPositionY(450.0);
	    
	    point3.setSpeedX(00.0);
	    point3.setSpeedY(00.0);
	    
	    point2.setPositionX(point1.getPositionX() + 100.0);
	    point2.setPositionY(point1.getPositionY() + 200.0);
			
	    point2.setSpeedX(100);
	    point2.setSpeedY(-100.0);
	    
		window = new WindowSpring("Gravity Physics!");
		tension = new Coordinates();

	    E = 0.0;
		
	    while(true)
	    {
		if(COUNTER % 10000 == 0)
		    window.paintImage(point1, point2, E, best);

		springPoint = setMiddle(point1, point2);

		distance = point1.getProximity(point2);
		distance2 = point3.getProximity(point2);
		
		tension.setZ(getTension(distance, mass));

		tension.setX((point1.getPositionX()-point2.getPositionX())/(distance) * tension.getZ());
		tension.setY((point1.getPositionY()-point2.getPositionY())/(distance) * tension.getZ());

		tension.setZ(getTension(distance2, mass2));

		tension.setX(tension.getX() + (point3.getPositionX()-point2.getPositionX())/(distance2) * tension.getZ());
		tension.setY(tension.getY() + (point3.getPositionY()-point2.getPositionY())/(distance2) * tension.getZ());
		
		point2.setAccelX(tension.getX());
		point2.setAccelY(tension.getY());
		
		    point1.updateCoordinates();
		    point2.updateCoordinates();


		Ek = Math.pow(point2.getVelocity(), 2.0)/2.0;

		    Ep = -(mass/distance + mass2/distance2);

		E = Ek+Ep;
		
		if(point2.getPositionY() > best)
		    best = point2.getPositionY();
		
		// System.out.println("Energy = " + E +
		//                     "    Kinetic = " + Ek +
		//                     "    Elastic = " + Ee);
				   
		COUNTER++; 
	    }
	    
	    
	    
	}
	
	
	public double getRandomNumber(double minimum, double maximum)
	{
	    return (Math.random() * (maximum - minimum) + minimum);
	}
	
	
	public static double getTension(double lenght, double M)
	{       
	    return M/Math.pow(lenght, 2.0);
	}
	
	
	public static Coordinates setMiddle(CoordinateSystemGravity point1, CoordinateSystemGravity point2)
	{
	    return (new Coordinates(0.0, (point1.getPositionX()+point2.getPositionX())/2.0, (point1.getPositionY()+point2.getPositionY())/2.0));
	}
	
	
	public void breakSpring()
	{
	
	}
}
