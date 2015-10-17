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
	    Vector separation12, separation13, separation14, separation23, separation24, separation34;
	    WindowMomentum window = new WindowMomentum("Billiard!");
	    
	    Particle thing1 = new Particle(1), thing2 = new Particle(1), thing3 = new Particle(1), thing4 = new Particle(1);
	    
		thing1.motion = new CoordinateSystem(new CartesianVector(450,300), new CartesianVector(0, 00));
		thing2.motion = new CoordinateSystem(new CartesianVector(550,300), new CartesianVector(00, 0));
		thing3.motion = new CoordinateSystem(new CartesianVector(500,400), new CartesianVector(000, 00));
		thing4.motion = new CoordinateSystem(new CartesianVector(510,700), new CartesianVector(00, -200));
	    
		double kineticE = 0;
		double momentum = 0;
	    
	    //GO!
	    while(true)
	    {
		window.paintImage(thing1.motion.position, thing2.motion.position, thing3.motion.position, thing4.motion.position);
		
		separation12 = thing1.motion.position.substractVector(thing2.motion.position);
		separation13 = thing1.motion.position.substractVector(thing3.motion.position);
		separation14 = thing1.motion.position.substractVector(thing4.motion.position);
		
		separation23 = thing2.motion.position.substractVector(thing3.motion.position);
		separation24 = thing2.motion.position.substractVector(thing4.motion.position);
		
		separation34 = thing3.motion.position.substractVector(thing4.motion.position);
		
		// System.out.println(separation);
		// 
		if(Math.random() > 0.9999)
		{
		    kineticE = 0;
		    momentum = 0;
		    
		    kineticE = thing1.getKineticEnergy() + thing2.getKineticEnergy() + thing3.getKineticEnergy() + thing4.getKineticEnergy();
		    momentum = (thing1.getMomentum().addVector(thing2.getMomentum().addVector(thing3.getMomentum().addVector(thing4.getMomentum())))).getMagnitude();
		    
		    window.assignConstants(kineticE, momentum);
		}
				    
		if(thing1.dimensions.checkIfIntersect(thing2.dimensions, separation12))
		{
		    // System.out.println("        TEST COLLISION");
		    thing1.collision(thing2);
		}             
		if(thing1.dimensions.checkIfIntersect(thing3.dimensions, separation13))
		{
		    // System.out.println("        TEST COLLISION");
		    thing1.collision(thing3);
		}              
		if(thing1.dimensions.checkIfIntersect(thing4.dimensions, separation14))
		{
		    // System.out.println("        TEST COLLISION");
		    thing1.collision(thing4);
		}
		
		///////////////
		
		if(thing2.dimensions.checkIfIntersect(thing3.dimensions, separation23))
		{
		    // System.out.println("        TEST COLLISION");
		    thing2.collision(thing3);
		} 
		if(thing2.dimensions.checkIfIntersect(thing4.dimensions, separation24))
		{
		    // System.out.println("        TEST COLLISION");
		    thing2.collision(thing4);
		}
		
		///////////////
		
		if(thing3.dimensions.checkIfIntersect(thing4.dimensions, separation34))
		{
		    // System.out.println("        TEST COLLISION");
		    thing3.collision(thing4);
		} 
		
		
		
		hitWall(thing1.motion);
		hitWall(thing2.motion);
		hitWall(thing3.motion);
		hitWall(thing4.motion);
		
		thing1.updateParticle(null);
		thing2.updateParticle(null);
		thing3.updateParticle(null);
		thing4.updateParticle(null);
		// separation = thing1.motion.position.substractVector(thing2.motion.position);
		
	    
	    }
	    // thing.collision(thing2);
	}
	
	public static void hitWall(CoordinateSystem thing)
	{   
		if(thing.position.getX() <= 50)
		{
		    thing.velocity = thing.velocity.reflectVector(1.0, 0.0, 0.0);
		} 
		
		if(thing.position.getX() >= 950)
		{
		    thing.velocity = thing.velocity.reflectVector(1.0, 0.0, 0.0);
		}   
		
		if(thing.position.getY() >= 950)
		{
		    thing.velocity = thing.velocity.reflectVector(0.0, 1.0, 0.0);
		}   
		
		if(thing.position.getY() <= 50)
		{
		    thing.velocity = thing.velocity.reflectVector(0.0, 1.0, 0.0);
		}  
		
		if(thing.position.getZ() >= 950)
		{
		    thing.velocity = thing.velocity.reflectVector(0.0, 0.0, 1.0);
		}   
		
		if(thing.position.getZ() <= 50)
		{
		    thing.velocity = thing.velocity.reflectVector(0.0, 0.0, 1.0);
		}  
	}
	
	
}
