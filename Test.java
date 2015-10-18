package boubouworld2;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class Test
{
	public static void main(String[] args)
	{            
	    int COUNTER = 0;
	    final int MAX_COORD = 800;
	    WindowMomentum window = new WindowMomentum("Billiard!", 800);
	    
	    String inputString = JOptionPane.showInputDialog("Enter number of balls");
	    
								int num = Integer.parseInt(inputString);
								
	    
	    int numSep = num*(num-1)/2;
	    int sepIndex = 0;
	    
	    Particle[] balls = new Particle[num];
	    Vector[] separations = new Vector[numSep];
	    
	    for(int i = 0; i < num; i ++)
	    {
		balls[i] = new Particle((int)(Math.random()*100.0)+1);
		int posX = 150 * (i % 6 + 1);
		int posY = 150 * (i / 6 + 1);
		int posZ = 500;
		balls[i].motion = new CoordinateSystem(new CartesianVector(posX, posY, posZ), randomCartesianVector(250));
	    }
	    
	    
	    // Particle thing1 = new Particle(1), thing2 = new Particle(1), thing3 = new Particle(1), thing4 = new Particle(1);
	    // 
	    //     thing1.motion = new CoordinateSystem(new CartesianVector(450,300), new CartesianVector(0, 00));
	    //     thing2.motion = new CoordinateSystem(new CartesianVector(550,300), new CartesianVector(00, 0));
	    //     thing3.motion = new CoordinateSystem(new CartesianVector(500,400), new CartesianVector(000, 00));
	    //     thing4.motion = new CoordinateSystem(new CartesianVector(510,700), new CartesianVector(00, -200));
	    
		double kineticE = 0, momentumD = 0;
		Vector momentum = new Vector(), wallMomentum = new Vector(), tempMomentum = new Vector();
		
	    
	    //GO!
	    while(true)
	    {
		window.paintImage(balls, num);
		
		// separation12 = thing1.motion.position.substractVector(thing2.motion.position);
		// separation13 = thing1.motion.position.substractVector(thing3.motion.position);
		// separation14 = thing1.motion.position.substractVector(thing4.motion.position);
		// 
		// separation23 = thing2.motion.position.substractVector(thing3.motion.position);
		// separation24 = thing2.motion.position.substractVector(thing4.motion.position);
		// 
		// separation34 = thing3.motion.position.substractVector(thing4.motion.position);
		
		// System.out.println(separation);
		// 
		
		sepIndex = 0;
		for(int i = 0; i < num-1; i++)
		{
		    for(int j = num-1; j > i; j--)
		    {
			separations[sepIndex] = balls[i].motion.position.substractVector(balls[j].motion.position);
				  
			if(balls[i].dimensions.checkIfIntersect(balls[j].dimensions, separations[sepIndex]))
			{
			    // System.out.println("        TEST COLLISION");
			    balls[i].collision(balls[j]);
			}        
		    
			sepIndex ++;
		    }
		}
		
				    
		// if(thing1.dimensions.checkIfIntersect(thing2.dimensions, separation12))
		// {
		//     // System.out.println("        TEST COLLISION");
		//     thing1.collision(thing2);
		// }             
		// if(thing1.dimensions.checkIfIntersect(thing3.dimensions, separation13))
		// {
		//     // System.out.println("        TEST COLLISION");
		//     thing1.collision(thing3);
		// }              
		// if(thing1.dimensions.checkIfIntersect(thing4.dimensions, separation14))
		// {
		//     // System.out.println("        TEST COLLISION");
		//     thing1.collision(thing4);
		// }
		// 
		// ///////////////
		// 
		// if(thing2.dimensions.checkIfIntersect(thing3.dimensions, separation23))
		// {
		//     // System.out.println("        TEST COLLISION");
		//     thing2.collision(thing3);
		// } 
		// if(thing2.dimensions.checkIfIntersect(thing4.dimensions, separation24))
		// {
		//     // System.out.println("        TEST COLLISION");
		//     thing2.collision(thing4);
		// }
		// 
		// ///////////////
		// 
		// if(thing3.dimensions.checkIfIntersect(thing4.dimensions, separation34))
		// {
		//     // System.out.println("        TEST COLLISION");
		//     thing3.collision(thing4);
		// } 
		
		for(int i = 0; i < num; i ++)
		{
		    wallMomentum = wallMomentum.addVector(hitWall(balls[i].motion, balls[i].getMass(), MAX_COORD));
		}
		
		// hitWall(thing1.motion);
		// hitWall(thing2.motion);
		// hitWall(thing3.motion);
		// hitWall(thing4.motion);
		
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
		
		// thing1.updateParticle(null);
		// thing2.updateParticle(null);
		// thing3.updateParticle(null);
		// thing4.updateParticle(null);
		
		
	    }
	    // thing.collision(thing2);
	}
	
	public static Vector hitWall(CoordinateSystem thing, double mass, int MAX_COORD)
	{   
		Vector momentum = new Vector();
		
		if(thing.position.getX() <= 50)
		{
		    momentum = new CartesianVector(thing.velocity.scalarProduct(2*mass).getX(), 0.0, 0.0);
		    thing.velocity = thing.velocity.reflectVector(1.0, 0.0, 0.0);
		}                 
		
		if(thing.position.getX() >= MAX_COORD-50)
		{
		    momentum = new CartesianVector(thing.velocity.scalarProduct(2*mass).getX(), 0.0, 0.0);
		    thing.velocity = thing.velocity.reflectVector(1.0, 0.0, 0.0);
		}   
		
		if(thing.position.getY() >= MAX_COORD-50)
		{
		    momentum = new CartesianVector(0.0, thing.velocity.scalarProduct(2*mass).getY(), 0.0);
		    thing.velocity = thing.velocity.reflectVector(0.0, 1.0, 0.0);
		}   
		
		if(thing.position.getY() <= 50)
		{
		    momentum = new CartesianVector(0.0, thing.velocity.scalarProduct(2*mass).getY(), 0.0);
		    thing.velocity = thing.velocity.reflectVector(0.0, 1.0, 0.0);
		}  
		
		if(thing.position.getZ() >= MAX_COORD-50)
		{
		    momentum = new CartesianVector(0.0, 0.0, thing.velocity.scalarProduct(2*mass).getZ());
		    thing.velocity = thing.velocity.reflectVector(0.0, 0.0, 1.0);
		}   
		
		if(thing.position.getZ() <= 50)
		{
		    momentum = new CartesianVector(0.0, 0.0, thing.velocity.scalarProduct(2*mass).getZ());
		    thing.velocity = thing.velocity.reflectVector(0.0, 0.0, 1.0);
		} 
		
		
		    // if(momentum.getMagnitude() != 0.0)
		    // {
		    //     System.out.println("\n\n    ONTACHI!\n");
		    //     System.out.println(momentum + "\n\n" + thing.velocity.scalarProduct(mass) + "\n");
		    //     System.out.println(momentum.getX() / thing.velocity.scalarProduct(mass).getX() + "\n");
		    //     System.out.println(momentum.getY() / thing.velocity.scalarProduct(mass).getY() + "\n");
		    //     System.out.println(momentum.getZ() / thing.velocity.scalarProduct(mass).getZ() + "\n");
		    // }
			
		return momentum;
	}
	
	public static Vector randomPolarVector()
	{
	    double side = (int)(Math.random() * 2) * Math.PI;
	    double elevation = Math.random() * 180.0 - 90.0;
	    double magnitude = Math.random() * 500.0;
	    
	    return new PolarVector(magnitude, elevation, side);
	}
	
	public static Vector randomCartesianVector(double limit)
	{            
	    double X = limit * (Math.random() * 2.0 - 1);
	    double Y = limit * (Math.random() * 2.0 - 1);
	    double Z = limit * (Math.random() * 2.0 - 1);
	    
	    return new CartesianVector(X, Y, 0);
	}
	
	public static Vector getMomentum(Particle[] things, int index, int max)
	{
	    if(index < max - 1)
		return things[index].getMomentum().addVector(getMomentum(things, index + 1, max));
	    else
		return things[index].getMomentum();
	}
	
}
