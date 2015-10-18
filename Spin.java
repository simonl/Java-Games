package javaapps.physicsobject;

import java.util.Random;

public class Spin
{
    public static final Random randomizer = new Random();
    
    public static final Spin UP = new Spin(1);
    public static final Spin DOWN = new Spin(-1);
    
    private int direction;
    
    public Spin()
    {
	this(randomizer.nextBoolean() ? UP : DOWN);
    }
    
    private Spin(int direction)
    {
	this.direction = direction;
    }
    
    public Spin(Spin spin)
    {
	this.direction = spin.getDirection();
    }
    
    //GETTERS
    public int getDirection()
    {
	return this.direction;
    }
    
    //SETTERS
    public void setRandomDirection()
    {
	if(randomizer.nextBoolean())
	    this.setUp();
	else
	    this.setDown();
    }
    
    public void setUp()
    {
	this.direction = UP.getDirection();
    }
    
    public void setDown()
    {
	this.direction = DOWN.getDirection();
    }
    
    public void setToSpin(Spin other)
    {
	if(other.isUp())
	    this.setUp();
	else
	    this.setDown();
    }
    
    //TESTS
    public boolean isUp()
    {
	return this.direction == UP.getDirection();
    }
    
    public boolean isDown()
    {
	return this.direction == DOWN.getDirection();
    }
    
    public boolean equals(Spin other)
    {
	if(this.isUp() && other.isUp())
	    return true;
	else if(this.isDown() && other.isDown())
	    return true;
	else
	    return false;
    }
    
    //UTILITIES
    public Spin copy()
    {
	if(this.isUp())
	    return new Spin(UP);
	else
	    return new Spin(DOWN);
    }
    
    public String toString()
    {
	return "Spin is " + (this.isUp() ? "UP" : "DOWN");
    }
}
