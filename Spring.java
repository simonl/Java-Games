package javaapps.physicsobject;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javaapps.physicsobject.Coordinates;

public class Spring extends Particle
{

    private final double k;
    private final double l;
    
    private Particle p1 = null, p2 = null;
    
    
    public Spring()
    {
	this(1.0);
    }
    
    public Spring(double k)
    {
	this(k, 1.0);   
    }
    
    public Spring(double k, double l)
    {
	this(k, l, new Particle());
    }
    
    public Spring(double k, double l, Particle p1)
    {
	this(k, l, p1, new Particle());   
    }
    
    public Spring(double k, Particle p1, Particle p2)
    {
	super();
	
	this.k = k;
	this.l = l;
	this.p1 = p1;
	this.p2 = p2;
    }
	   
    public void updateParticle(Vector force)
    {                
	super.updateParticle(force);
	p1.minus
	    
	    
    }
}
