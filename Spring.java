package javaapps.test;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javaapps.physicsobject.Coordinates;

public class Spring
{

    private double springConstant, spirals, width, thickness;
    private double normalLenght, minLenght, maxLenght;
    
    public CoordinateSystemGravity anchor1 = null, anchor2 = null, springMiddle;
    
    
    public Spring()
    {
    
    }
    
    public Spring(double normalLenght, double springConstant, double spirals)
    {
	this.normalLenght = normalLenght;
	this.springConstant = springConstant;
	this.spirals = spirals;
	
	this.width = 0.05;
	this.thickness = 0.002;
	
	this.maxLenght = Math.pow(Math.pow(normalLenght, 2.0) + Math.pow(2*Math.PI*width*spirals, 2.0), 0.5);
	this.minLenght = thickness * spirals;
	
    }
    public static void main(String[] args)
    {
	Spring spring = new Spring();
	spring.go();
    }
	public void go()
	{
	
	    double globalTime = 0.0000;
	    
	    double distance = 0, best = 0.0;
	    
	    
	    
	    //GO!
			
	    anchor1 = new CoordinateSystemGravity();
		anchor1.setLimitsMotion(1000.0, 1000.0, 1000.0, 1000.0);
		anchor1.setLimitsPosition(-10000.0, 10000.0, -10000.0, 10000.0);
	    anchor2 = new CoordinateSystemGravity();
		anchor2.setLimitsMotion(1000.0, 1000.0, 1000.0, 1000.0);
		anchor2.setLimitsPosition(-10000.0, 10000.0, -10000.0, 10000.0);
	    
	    anchor1.setPositionX(450.0);
	    anchor1.setPositionY(450.0);
	    
	    anchor1.setSpeedX(-50.0);
	    anchor1.setSpeedY(00.0);
	    
	    anchor2.setPositionX(anchor1.getPositionX() + 100.0);
	    anchor2.setPositionY(anchor1.getPositionY() + 10.0);
			
	    anchor2.setSpeedX(50.0);
	    anchor2.setSpeedY(00.0);
	    
		WindowSpring window = new WindowSpring("Spring Physics!");
		Coordinates tension = new Coordinates(), springPoint;
	    
	    while(true)
	    {
		window.paintImage(anchor1, anchor2, distance, best);

		springPoint = setMiddle();

		distance = anchor1.getProximity(anchor2);
		tension.setZ(getTension(distance)/2.0);

		tension.setX((springPoint.getX()-anchor1.getPositionX())/distance * tension.getZ());
		tension.setY((springPoint.getY()-anchor1.getPositionY())/distance * tension.getZ());

		anchor1.setAccelX(tension.getX());
		anchor1.setAccelY(tension.getY());

		anchor2.setAccelX(-tension.getX());
		anchor2.setAccelY(-tension.getY());
		
		    anchor1.updateCoordinates();
		    anchor2.updateCoordinates();
		    // System.out.println(anchor1.getPositionX() + "  " + anchor1.getPositionY());
		    // System.out.println(anchor2.getPositionX() + "  " + anchor2.getPositionY());
		    // System.out.println("\n\n");

		window.paintImage(anchor1, anchor2, distance, best);
		
		if(distance > best)
		{
		    best = distance;
		    //System.out.println("Longest stretch = " + best);
		}
		    
	    }
	    
	    
	    
	}
	
	
	public double getRandomNumber(double minimum, double maximum)
	{
	    return (Math.random() * (maximum - minimum) + minimum);
	}
	
	
	public double getTension(double lenght)
	{                
	    double tension = 0;
	    
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
	
	
	public Coordinates setMiddle()
	{
	    return (new Coordinates(0.0, (anchor1.getPositionX()+anchor2.getPositionX())/2.0, (anchor1.getPositionY()+anchor2.getPositionY())/2.0));
	}
	
	
	public void breakSpring()
	{
	
	}
}
