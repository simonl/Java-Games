package boubouworld;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class SpringTest
{
	public static void main(String[] args)
	{            
	    int COUNTER = 0;
	    
	    CoordinateSystem point1 = null, point2 = null;
	    Coordinates springPoint = null, tension = null;
	    WindowSpring window = null;
	    
	    double distance, best = 0.0;
	    double E, Ek, Ee, elongation, coefficient;
	    
	    //GO!
			
	    point1 = new CoordinateSystem();
		point1.setLimitsMotion(000.0, 000.0, 000.0, 000.0);
		point1.setLimitsPosition(-10000.0, 10000.0, -10000.0, 10000.0);
	    point2 = new CoordinateSystem();
		point2.setLimitsMotion(1000.0, 1000.0, 1000.0, 1000.0);
		point2.setLimitsPosition(-10000.0, 10000.0, -10000.0, 10000.0);
	    
	    point1.setPositionX(450.0);
	    point1.setPositionY(450.0);
	    
	    point1.setSpeedX(00.0);
	    point1.setSpeedY(00.0);
	    
	    point2.setPositionX(point1.getPositionX() + 00.0);
	    point2.setPositionY(point1.getPositionY() + 200.0);
			
	    point2.setSpeedX(100.0);
	    point2.setSpeedY(00.0);
	    
		window = new WindowSpring("Spring Physics!");
		tension = new Coordinates();

	    E = 0.0;
		
	    System.out.println(""+ point1.solveQuadratic(1.0, 1.0, 20.0/100.0));
	    while(true)
	    {
		if(COUNTER % 10000 == 0)
		    window.paintImage(point1, point2, E, best);

		springPoint = setMiddle(point1, point2);

		distance = point1.getProximity(point2);
		tension.setTime(getTension(distance));

		tension.setPositionX((point2.getPositionX()-point1.getPositionX())/(distance) * tension.getTime());
		tension.setPositionY((point2.getPositionY()-point1.getPositionY())/(distance) * tension.getTime());

		point1.setAccelX(tension.getPositionX());
		point1.setAccelY(tension.getPositionY());

		point2.setAccelX(-tension.getPositionX());
		point2.setAccelY(-tension.getPositionY());
		
		    point1.updateCoordinates();
		    point2.updateCoordinates();


		Ek = Math.pow(point2.getVelocity(), 2.0)/2.0;

		if(distance >= 100.0)
		    Ee = Math.pow(distance-100.0, 2.0)/2.0;
		else
		    Ee = 100*(distance-100*Math.log(distance)-100.0+100*Math.log(100.0));

		E = Ek+Ee;
		
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
	
	
	public static double getTension(double lenght)
	{                
	    
	    double normalLenght = 100.0;
		lenght /= normalLenght;
	    double difference = lenght - 1.0;
	    double tension;
	    double exponent = Math.abs(difference)/difference;
	    
	    if(lenght != 1.0)
		tension = (Math.pow(lenght, exponent) - 1) * exponent;
	    else
		tension = 0.0;
			    
	    return tension*normalLenght;
	}
	
	
	public static Coordinates setMiddle(CoordinateSystem point1, CoordinateSystem point2)
	{
	    return (new Coordinates(0.0, (point1.getPositionX()+point2.getPositionX())/2.0, (point1.getPositionY()+point2.getPositionY())/2.0));
	}
	
	
	public void breakSpring()
	{
	
	}
}
