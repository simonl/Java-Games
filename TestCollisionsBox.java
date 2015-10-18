package javaapps.test;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javaapps.physicsobject.*;

public class TestCollisionsBox
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
	    
	    WindowMomentumBox window = new WindowMomentumBox("Billiard!", MAX_COORD, MIN_COORD);
	    
	    
	    //GRAVITY
	    final double G = 0;            
	    final Vector gravity = new CartesianVector(0, G, 0);
	    
	    
	    //BALLS
	    Particle box = new Particle(4);
		box.motion.position.setToComponents(250, 250, 0);
		box.motion.velocity.setToComponents(-5, 10, 0);
		//box.dimensions = new Dimensions(350, 350, 0);
		box.dimensions = new Spheric(350);

	    Particle ball = new Particle(1);
		ball.motion.position.setToComponents(250, 250, 0);
		ball.motion.velocity.setToComponents(20, -30, 0);
	    
	    
	    window.panel.ball = ball.motion.position;
	    window.panel.box = box.motion.position;
	  
	    //ENERGY, MOMENTUM, FORCE
	    double kineticE = 0, potentialE = 0;
	    Vector totalMomentum = new Vector();
	    Vector boxMomentum = new Vector();
	    Vector separation = new Vector();
	    
	    window.setConstants();
	    window.assignConstants(kineticE, potentialE, totalMomentum.getMagnitude());
		    
	    
	    //GO!
	    while(true)
	    {
	    
		if(imageCounter % CYCLES == 0)
		{
		    if(imageCounter >= CYCLES)
			imageCounter = 0;
			
		    window.paintImage(COUNTER);
		}    
		
		
		separation = box.motion.position.substractVector(ball.motion.position);
		if(box.dimensions.checkIfIntersect(ball.dimensions, separation))
		{
		    ball.collision(box);
		}
		
		
		box.updateParticle(gravity, box.getMass());                                           
		ball.updateParticle(gravity, ball.getMass());
		
		
		if(Math.random() < DELAY)
		{
		    kineticE = 0;
		    potentialE = 0;
		    totalMomentum.setToScalarProduct(0);
		    
			kineticE += ball.getKineticEnergy();
			potentialE += (700 - ball.motion.position.getY()) * G * ball.getMass();
			totalMomentum.setToVectorAddition(ball.motion.velocity, ball.getMass());
			
			kineticE += box.getKineticEnergy();
			potentialE += (700 - box.motion.position.getY()) * G * box.getMass();
			totalMomentum.setToVectorAddition(box.motion.velocity, box.getMass());
					
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
