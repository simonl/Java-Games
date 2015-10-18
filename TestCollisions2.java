package javaapps.test;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javaapps.physicsobject.*;

public class TestCollisions2
{
	public static void main(String[] args)
	{            
	    double COUNTER = 0;
	    double DELAY = 1.0E-4;
	    int imageCounter = 0, cycles = (int)(1.0 / DELAY / 100.0);
	    final int MAX_COORD = 700, MIN_COORD = 0;
	    WindowMomentum2 window = new WindowMomentum2("Billiard!", MAX_COORD, MIN_COORD);
	    
	    String inputString = JOptionPane.showInputDialog("Enter number of balls");
	    
								int num = Integer.parseInt(inputString);
								
	    
	    int numSep = num*(num-1)/2;
	    int sepIndex = 0;
	    
	    Particle[] balls = new Particle[num];
	    Vector[] separations = new Vector[numSep];
	    
	    for(int i = 0; i < num; i ++)
	    {
		balls[i] = new Particle((int)(Math.random()*100.0)+1);
		int posX = 150 * (i % 4 + 1);
		int posY = 150 * ((i % 16) / 4 + 1);
		    int posZ = 150 * (i / 16 + 1);
		balls[i].motion = new CoordinateSystem(new CartesianVector(posX, posY, posZ), randomCartesianVector(250, 250, 250));
	    }
	    
	    
		double kineticE = 0;
		Vector momentum = new Vector(), wallMomentum = new Vector();
		
		    
		    for(int i = 0; i < num; i++)
		    {
			kineticE += balls[i].getKineticEnergy();
		    }
		    momentum = momentum.addVector(getMomentum(balls, 0, num));
		    
		    window.assignConstants(kineticE, momentum.getMagnitude());
		    window.setConstants();
		    
	    //GO!
	    while(true)
	    {
	    
		if(imageCounter % cycles == 0)
		{
		    if(imageCounter > cycles)
			imageCounter = 0;
		    window.paintImage(balls, num, COUNTER);
		}
		
		
		sepIndex = 0;
		for(int i = 0; i < num-1; i++)
		{
		    for(int j = num-1; j > i; j--)
		    {
			separations[sepIndex] = balls[i].motion.position.substractVector(balls[j].motion.position);
				  
			if(balls[i].dimensions.checkIfIntersect(balls[j].dimensions, separations[sepIndex]))
			{
			    balls[i].collision(balls[j]);
			}        
		    
			sepIndex ++;
		    }
		}
		
		for(int i = 0; i < num; i ++)
		{
		    wallMomentum = wallMomentum.addVector(hitWall(balls[i].motion, balls[i].getMass(), MAX_COORD, MIN_COORD));
		}
				
		if(Math.random() > 0.9999)
		{
		    kineticE = 0;
		    momentum = new Vector();
		    
		    for(int i = 0; i < num; i++)
		    {
			kineticE += balls[i].getKineticEnergy();
		    }
					
		    momentum = momentum.addVector(getMomentum(balls, 0, num));
		    
		    window.assignConstants(kineticE, momentum.addVector(wallMomentum).getMagnitude());
		}
		
		    
		for(int i = 0; i < num; i ++)
		{
		    balls[i].updateParticle(null);
		}
		
		COUNTER += DELAY;
		imageCounter ++;
	    }
	}
	
	public static Vector hitWall(CoordinateSystem thing, double mass, int MAX_COORD, int MIN_COORD)
	{   
		Vector momentum = new Vector();
		
		if(thing.position.getX() - 50 <= MIN_COORD)
		{
		    momentum = new CartesianVector(thing.velocity.scalarProduct(2*mass).getX(), 0.0, 0.0);
		    thing.velocity = thing.velocity.reflectVector(1.0, 0.0, 0.0);
		}                 
		
		if(thing.position.getX() + 50 >= MAX_COORD)
		{
		    momentum = new CartesianVector(thing.velocity.scalarProduct(2*mass).getX(), 0.0, 0.0);
		    thing.velocity = thing.velocity.reflectVector(1.0, 0.0, 0.0);
		}   
		
		if(thing.position.getY() + 50 >= MAX_COORD)
		{
		    momentum = new CartesianVector(0.0, thing.velocity.scalarProduct(2*mass).getY(), 0.0);
		    thing.velocity = thing.velocity.reflectVector(0.0, 1.0, 0.0);
		}   
		
		if(thing.position.getY() - 50 <= MIN_COORD)
		{
		    momentum = new CartesianVector(0.0, thing.velocity.scalarProduct(2*mass).getY(), 0.0);
		    thing.velocity = thing.velocity.reflectVector(0.0, 1.0, 0.0);
		}  
		
		if(thing.position.getZ() + 50 >= MAX_COORD)
		{
		    momentum = new CartesianVector(0.0, 0.0, thing.velocity.scalarProduct(2*mass).getZ());
		    thing.velocity = thing.velocity.reflectVector(0.0, 0.0, 1.0);
		}   
		
		if(thing.position.getZ() - 50 <= MIN_COORD)
		{
		    momentum = new CartesianVector(0.0, 0.0, thing.velocity.scalarProduct(2*mass).getZ());
		    thing.velocity = thing.velocity.reflectVector(0.0, 0.0, 1.0);
		} 
					
		return momentum;
	}
	
	public static Vector randomPolarVector()
	{
	    double side = (int)(Math.random() * 2) * Math.PI;
	    double elevation = Math.random() * 180.0 - 90.0;
	    double magnitude = Math.random() * 500.0;
	    
	    return new PolarVector(magnitude, elevation, side);
	}
	
	public static Vector randomCartesianVector(double limitX, double limitY, double limitZ)
	{            
	    double X = limitX * (Math.random() * 2.0 - 1);
	    double Y = limitY * (Math.random() * 2.0 - 1);
	    double Z = limitZ * (Math.random() * 2.0 - 1);
	    
	    return new CartesianVector(X, Y, Z);
	}
	
	public static Vector getMomentum(Particle[] things, int index, int max)
	{
	    if(index < max - 1)
		return things[index].getMomentum().addVector(getMomentum(things, index + 1, max));
	    else
		return things[index].getMomentum();
	}
	
}
