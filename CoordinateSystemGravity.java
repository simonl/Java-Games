package javaapps.test;

import javax.swing.*;

/** Card Object.
 *  Has a card suit, from 0 to 3.
 *  Has a face value from 1 to 13.
 *  Can display the card name from the face value and suit.
 *
 *  @authors Simon Langlois, Pierre Miguel
 *           and Vincenzo Patrinostro - A933125 - 420-111-DW section 1
 *  @version    12/04/2009
 */


public class CoordinateSystemGravity
{
    //Instance variables
    public static final int FPS = (int)Math.pow(10.0, 6.0);
    public static final double DELAY = 1.0/FPS;
    
    private static double time;
    
    private double positionX, positionY;
    public static double limitLeft, limitRight, limitDown, limitUp;
    
    private double speedX, speedY;
    private double limitSpeedX, limitSpeedY;
    
    private double accelX, accelY;
    private double limitAccelX, limitAccelY;
    
    
    //Constructors
    
    public CoordinateSystemGravity()
    {        
    
    }
    
    public CoordinateSystemGravity(int number)
    {        
	    setLimitsMotion(30.0, 0.0, 1.0, 0.0);
	    setLimitsPosition(10.0, 974.0, 0.0, 800.0);
	setPositionX(getRandomNumber(limitLeft+10.0, limitRight-10.0));
    }
    
    public CoordinateSystemGravity(double positionX, double positionY, double speedX, double speedY)
    {
	    setLimitsMotion(100.0, 100.0, 0.0, 9.8);
	    setLimitsPosition(10.0, 974.0, 0.0, 800.0);
	setPositionX(positionX);
	setPositionY(positionY);
	setSpeedX(speedX);
	setSpeedY(speedY);
	setAccelY(-9.8);
    }
    
    //Getters
    
    public double getTime()
    {
	return time;
    }    
    
    public double getPositionY()
    {
	return positionY;
    }
    
    public double getPositionX()
    {
	return positionX;
    }
    
    public double getSpeedX()
    {
	return speedX;
    }
    
    public double getSpeedY()
    {
	return speedY;
    }
    
    public double getAccelX()
    {
	return accelX;
    }
    
    public double getAccelY()
    {
	return accelY;
    }
    
    public double getLimitSpeedX()
    {
	return limitSpeedX;
    }
    
    public double getLimitSpeedY()
    {
	return limitSpeedY;
    }
    
    public double getLimitAccelX()
    {
	return limitAccelX;
    }
    
    public double getLimitAccelY()
    {
	return limitAccelY;
    }
    
    
    //Setters  
      
    public void setTime(double time)
    {
	if(time < 0.0)
	    time = 0.0;
	    
	this.time = time;
    }
    
    public void setPositionX(double positionX)
    {
	if(positionX > limitRight)
	    positionX = limitRight;
	else if(positionX < limitLeft)
	    positionX = limitLeft;

	this.positionX = positionX;
    }
    
    public void setPositionY(double positionY)
    {
	if(positionY > limitUp)
	    positionY = limitUp;
	else if(positionY < limitDown)
	    positionY = limitDown;
	    
	this.positionY = positionY;
    }    
    
    public void setSpeedX(double speedX)
    {
	if(speedX > limitSpeedX)
	    speedX = limitSpeedX;
	else if(speedX < -limitSpeedX)
	    speedX = -limitSpeedX;
	    
	this.speedX = speedX;
    }
    
    public void setSpeedY(double speedY)
    {
	if(speedY > limitSpeedY)
	    speedY = limitSpeedY;
	else if(speedY < -limitSpeedY)
	    speedY = -limitSpeedY;
	    
	this.speedY = speedY;
    }
    
    public void setAccelX(double accelX)
    {
	if(accelX > limitAccelX)
	    accelX = limitAccelX;
	else if(accelX < -limitAccelX)
	    accelX = -limitAccelX;
	    
	this.accelX = accelX;
    }
    
    public void setAccelY(double accelY)
    {
	if(accelY > limitAccelY)
	    accelY = limitAccelY;
	else if(accelY < -limitAccelY)
	    accelY = -limitAccelY;
	    
	this.accelY = accelY;
    }
    
    public void setLimitsMotion(double limit1, double limit2, double limit3, double limit4)
    {
	limitSpeedX = limit1;
	limitSpeedY = limit2;
	limitAccelX = limit3;
	limitAccelY = limit4;
    }
    
    public void setLimitsPosition(double limit1, double limit2, double limit3, double limit4)
    {
	limitLeft = limit1;
	limitRight = limit2;
	limitDown = limit3;
	limitUp = limit4;
    }
    
    
    //
    //Custom methods
    //
	
    private double getRandomNumber(double minimum, double maximum)
    {
	return (Math.random() * (maximum-minimum) + minimum);
    }
	
    public void updateCoordinates()
    {
	setPositionX(getPositionX() + getSpeedX() * DELAY);
	setPositionY(getPositionY() + getSpeedY() * DELAY);
	
	setSpeedX(getSpeedX() + getAccelX() * DELAY);
	setSpeedY(getSpeedY() + getAccelY() * DELAY);
	
    }
    
    public double getVelocity()
    {
	return Math.pow(getSpeedX() * getSpeedX() + getSpeedY() * getSpeedY(), 0.5);
    }
    
    public double getForce()
    {
	return Math.pow(getAccelX() * getAccelX() + getAccelY() * getAccelY(), 0.5);
    }
    
    public String timeToStop()
    {
	String timeTillStop;
	
	if(speedX*accelX < 0.0 && speedY*accelY < 0.0)
	{
	    if(-speedX/accelX == -speedY/accelY)
		timeTillStop = "" + (-speedX/accelX);
	    else
		timeTillStop = "NONE - Asynchronous stop";
	}
	else
	    timeTillStop = "NONE - Unending trajectory";
	
	return timeTillStop;
    }
    
    
    public String[] getIntersection(CoordinateSystemGravity other)
    {
	boolean theyIntersect;
	double intersectX1 = 0.0, intersectX2 = 0.0, intersectY1 = 0.0, intersectY2 = 0.0;
	double A, B, C;
	String[] solutionsX = {"", ""}, solutionsY = {"", ""};
	
	    A = (getAccelX()-other.getAccelX())/2.0;
	    B = getSpeedX()-other.getSpeedX();
	    C = getPositionX()-other.getPositionX();
	
	    solutionsX = solveQuadratic(A, B, C);
		
	    if(!(solutionsX[1].equals(""))) 
	    {
		theyIntersect = true;
		
		intersectX1 = Double.parseDouble(solutionsX[0]);
		intersectX2 = Double.parseDouble(solutionsX[1]);
	    }
	    else
		theyIntersect = false;
	
		
	    if(theyIntersect)
	    {
	    
		A = (getAccelY()-other.getAccelY())/2.0;
		B = getSpeedY()-other.getSpeedY();
		C = getPositionY()-other.getPositionY();
	
		solutionsY = solveQuadratic(A, B, C);
		
		if(!(solutionsY[1].equals(""))) 
		{
		    intersectY1 = Double.parseDouble(solutionsY[0]);
		    intersectY2 = Double.parseDouble(solutionsY[1]);
		}
		else
		    theyIntersect = false;
	    }            
	    
	    
	    String[] intersection = {"No intersection", ""};
	    
	    if(theyIntersect)
	    {                
		if(intersectX1 == intersectY1) 
		{
		    intersection[0] = "" + intersectX1;
		    intersection[1] = "" + intersectY1;
		}
		if(intersectX1 == intersectY2)
		{
		    intersection[0] = "" + intersectX1;
		    intersection[1] = "" + intersectY2;
		}
		if(intersectX2 == intersectY1)
		{
		    intersection[0] = "" + intersectX2;
		    intersection[1] = "" + intersectY1;
		}
		if(intersectX2 == intersectY2)
		{
		    intersection[0] = "" + intersectX2;
		    intersection[1] = "" + intersectY2;
		}
		
	    }
	    
	    return intersection;
    }
    
    public String[] solveQuadratic(double A, double B, double C)
    {
	double discriminant = B * B - 4.0 * A * C;
	String[] answers = {"", ""};
	
	if((B*B - 4.0*A*C) >= 0.0)
	{
	    double root1 = (B + Math.pow(discriminant, 0.5))/(2.0*A);
	    double root2 = (B - Math.pow(discriminant, 0.5))/(2.0*A);
	    
	    if(root1 >= 0.0 || root2 >= 0.0)
	    {
		if(root1 < root2)
		{
		    answers[0] = "" + root1;
		    answers[1] = "" + root2;
		}
		else
		{
		    answers[0] = "" + root2;
		    answers[1] = "" + root1;
		}
	    }
	    else
		answers[0] = "No roots";
	}
	else
	    answers[0] = "Imaginary roots";
	    
	return answers;
    }
    
    public double getProximity(CoordinateSystemGravity point)
    {        
	return (Math.pow(Math.pow(point.getPositionX()-getPositionX(), 2.0) + Math.pow(point.getPositionY()-getPositionY(), 2.0), 0.5)); 
    }
    
    public boolean checkIfSamePosition(CoordinateSystemGravity point)
    {
	return (getPositionX() == point.getPositionX() && getPositionY() == point.getPositionY());
    }
}
