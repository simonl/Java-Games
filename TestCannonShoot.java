package javaapps.test;

import javax.swing.*;

public class TestCannonShoot
{
	public static void main(String[] args)
	{
	    final double G = 9.80000, PI = Math.PI;
	    int speedMPS, angleDegree, time;
	    String inputString, velocity;
	    double ySpeed, xSpeed, speedPrecise, angleRadian;;
	    double height, distance; 
	    double maxTime, maxDistance;
	    double maxHeight, criticalTime, criticalDistance;
	    
		inputString =
		    JOptionPane.showInputDialog ("Enter the angle of attack :");
		angleDegree = Integer.parseInt (inputString);
	    
		inputString =
		    JOptionPane.showInputDialog ("Enter the outgoing velocity :");
		speedMPS = Integer.parseInt (inputString);
		
		    speedPrecise = speedMPS;
		    angleRadian = angleDegree / 360.0 * (2.0 * PI);
		    
		    ySpeed = Math.sin(angleRadian) * speedPrecise;
		    xSpeed = Math.cos(angleRadian) * speedPrecise;
		    velocity = "Speed of " + speedPrecise + " m/s, angle of " + angleRadian + " Radian";
				
		    
		    System.out.println(velocity + " : ");
		    
		    maxTime = Math.sin(angleRadian) * speedPrecise / (G / 2.0);
		    maxDistance = Math.sin(angleRadian) * Math.cos(angleRadian) * Math.pow(speedPrecise, 2) / (G / 2.0);
		    maxHeight = Math.pow(Math.sin(angleRadian), 2) * Math.pow(speedPrecise, 2) / (2.0 * G);
		    criticalTime = Math.sin(angleRadian) * speedPrecise / G;
		    criticalDistance = Math.sin(angleRadian) * Math.cos(angleRadian) * Math.pow(speedPrecise, 2) / G;
		    
		    
		    time = 1;
		    
			System.out.println("        ( " + 0.0 + " , " + 0.0 + " , " + 0.0 + " )");
			
		    while(time < (maxTime / 2))
		    {
			height = -(G / 2.0) * Math.pow(time, 2) + Math.sin(angleRadian) * speedPrecise * time;
			distance = time * Math.cos(angleRadian) * speedPrecise;
			System.out.println("        ( " + time + " , " + distance + " , " + height + " )");  
			
			time++;
		    }
			
			System.out.println("        ( " + criticalTime + " , " + criticalDistance + " , " + maxHeight + " )");
		    
		    while(time < maxTime )
		    {
			height = -(G / 2.0) * Math.pow(time, 2) + Math.sin(angleRadian) * speedPrecise * time;
			distance = time * Math.cos(angleRadian) * speedPrecise;
			System.out.println("        ( " + time + " , " + distance + " , " + height + " )");  
			
			time++;
		    }
		    
			height = -(G / 2.0) * Math.pow(maxTime, 2) + Math.sin(angleRadian) * speedPrecise * maxTime;
			distance = maxTime * Math.cos(angleRadian) * speedPrecise;
			System.out.println("        ( " + maxTime + " , " + maxDistance + " , " + 0.0 + " )");
		    
			     
	}
}

