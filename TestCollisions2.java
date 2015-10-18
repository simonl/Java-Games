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
	    //TIME
	    double COUNTER = 0;
	    final double DELAY = CoordinateSystem.DELAY;
	    
	    
	    //IMAGE
	    int imageCounter = 0;
	    final int FPS = 60;
	    final int CYCLES = (int)(1.0 / DELAY / FPS);
	    final int MAX_COORD = 700, MIN_COORD = 0;
	    
	    WindowMomentum2 window = new WindowMomentum2("Billiard!", MAX_COORD, MIN_COORD);
	    
	    
	    //GRAVITY
	    final double G = 0;            
	    final Vector gravity = new CartesianVector(0, G, 0);
	    
	    
	    //BALLS
	    int num = Integer.parseInt(JOptionPane.showInputDialog("Enter number of balls"));    
	    int numSep = num *(num-1)/2;
	    int sepIndex = 0;
	    
	    Particle[] balls = initializeParticleArray(num);
	    Vector[] separations = initializeVectorArray(numSep, 1);
		//Vector tempSep = new Vector();
	    
	    window.panel.balls = initializeVectorArray(num, 0);
	    window.panel.num = num;
	  
	    //ENERGY, MOMENTUM, FORCE
	    double kineticE = 0, potentialE = 0;
	    Vector totalMomentum = new Vector();
	    Vector boxMomentum = new Vector();
	    
	    window.setConstants();
	    window.assignConstants(kineticE, potentialE, totalMomentum.getMagnitude());
	    window.linkBalls(balls);
		    
	    
	    //GO!
	    while(true)
	    {
	    
		if(imageCounter % CYCLES == 0)
		{
		    if(imageCounter >= CYCLES)
			imageCounter = 0;
			
		    window.paintImage(COUNTER);
		}    
		
		
		sepIndex = 0;
		for(int i = 0; i < num-1; i++)
		{
		    for(int j = num-1; j > i; j--)
		    {
			separations[sepIndex].setToVector(balls[i].motion.position);
			    separations[sepIndex].setToVectorSubstraction(balls[j].motion.position);

			if(balls[i].dimensions.checkIfIntersect(balls[j].dimensions, separations[sepIndex]))
			    balls[i].collision(balls[j]);
		    
			// tempSep.setToVectorSubstraction(balls[i].motion.position, balls[i].motion.position);
			// 
			// if(balls[i].dimensions.checkIfIntersect(balls[j].dimensions, tempSep));
			//     balls[i].collision(balls[j]);
			    
			sepIndex ++;
		    }
		}
		
		
				
		
		for(int i = 0; i < num; i ++)
		{
		    hitWall(boxMomentum, balls[i], MAX_COORD, MIN_COORD);                                        
		    balls[i].updateParticle(gravity, balls[i].getMass());
		}
		
		
		if(Math.random() < DELAY)
		{
		    kineticE = 0;
		    potentialE = 0;
		    totalMomentum.setToScalarProduct(0);
		    
		    for(int i = 0; i < num; i++)
		    {
			kineticE += balls[i].getKineticEnergy();
			potentialE += (700 - balls[i].motion.position.getY()) * G * balls[i].getMass();
			totalMomentum.setToVectorAddition(balls[i].motion.velocity, balls[i].getMass());
		    }
		    
		    totalMomentum.setToVectorAddition(boxMomentum);
		    
			//System.out.println(momentum.getVectorCount()); 
			//150,000 vectors per second TRY 1!!! 130,150 vps TRY 2 !!! 10,000 vps TRY 3!!! 101 vps TRY 4!!!
			//17 vectors at startup + 0 vps TRY 5!!!
					
		    window.assignConstants(kineticE, potentialE, totalMomentum.getMagnitude());
		}
		
		
		COUNTER += DELAY;
		imageCounter ++;
	    }
	}
	
	public static void hitWall(Vector momentum, Particle thing, int MAX_COORD, int MIN_COORD)
	{                   
	    double ballMomentumX = thing.motion.velocity.getX() * thing.getMass();
	    double ballMomentumY = thing.motion.velocity.getY() * thing.getMass();
	    double ballMomentumZ = thing.motion.velocity.getZ() * thing.getMass();
	    
		if((thing.motion.position.getX() - 50 <= MIN_COORD) || (thing.motion.position.getX() + 50 >= MAX_COORD))
		{
		    momentum.updateComponents(ballMomentumX * 2.0, 0.0, 0.0);
		    thing.motion.velocity.setToReflectedVector(true, false, false);
		}                 
		
		
		if((thing.motion.position.getY() - 50 <= MIN_COORD) || (thing.motion.position.getY() + 50 >= MAX_COORD))
		{
		    momentum.updateComponents(0.0, ballMomentumY * 2.0, 0.0);
		    thing.motion.velocity.setToReflectedVector(false, true, false);
		}   
		
		
		if((thing.motion.position.getZ() - 50 <= MIN_COORD) || (thing.motion.position.getZ() + 50 >= MAX_COORD))
		{
		    momentum.updateComponents(0.0, 0.0, ballMomentumZ * 2.0);
		    thing.motion.velocity.setToReflectedVector(false, false, true);
		}   
						   
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
	
	public static Vector[] initializeVectorArray(int num, int type)
	{
	    Vector[] vectors = new Vector[num];
	    
	    
		for(int i = 0; i < num; i ++)
		{   
		    if(type == 1)
			vectors[i] = new Vector();
		    else
			vectors[i] = null;
		}
		
	    return vectors;
	}
	
	public static Particle[] initializeParticleArray(int num)
	{
	    Particle[] balls = new Particle[num];
	    
	    for(int i = 0; i < num; i ++)
	    {
		balls[i] = new Particle((int)(Math.random()*100.0)+1);
		
		    int posX = 150 * (i % 4 + 1);
		    int posY = 150 * ((i % 16) / 4 + 1);
		    int posZ = 150 * (i / 16 + 1);
		    
		balls[i].motion.position.setToComponents(posX, posY, posZ);
		
		    double comX = randomComponent(150);
		    double comY = randomComponent(150);
		    double comZ = randomComponent(150);
		
		balls[i].motion.velocity.setToComponents(comX, comY, comZ);
	    }
	    
	    return balls;
	}
	
	public static double randomComponent(double limit)
	{
	    return (limit * (Math.random() * 2.0 - 1.0));
	}
}
