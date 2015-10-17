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
	    
	    Particle thing = new Particle(), thing2 = new Particle();
	    
		thing.motion = new CoordinateSystem(new CartesianVector(25,25), new CartesianVector(10));
		
		thing2.motion = new CoordinateSystem(new CartesianVector(30,30), new CartesianVector(-15, -5));
	    
		
	    
	    //GO!
	      
	    thing.collision(thing2);
	    
	}
	
	
}
