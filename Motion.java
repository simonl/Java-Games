package boubouworld;

public class Motion
{
    //Instance variables
    public static final double DELAY = 0.1;
    private double speedX, speedY, accelX, accelY;
    private double limitSpeedX = 100.0, limitSpeedY = 100.0, limitAccelX = 100.0, limitAccelY = 100.0;
	
    
    //Constructors
    public Motion()
    {
    
    }
    
    public Motion(double speedX, double speedY, double accelX, double accelY)
    {        
	this.speedX = speedX;
	this.speedY = speedY;
	this.accelX = accelX;
	this.accelY = accelY;
    }
    
    public void setLimits(double limit1, double limit2, double limit3, double limit4)
    {
	limitSpeedX = limit1;
	limitSpeedY = limit2;
	limitAccelX = limit3;
	limitAccelY = limit4;
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
    
    public void updateSpeed()
    {
	setSpeedX(accelX*DELAY);
	setSpeedY(accelY*DELAY);
    }
    
    
    public double getVelocity()
    {
	return Math.pow(speedX * speedX + speedY * speedY, 0.5);
    }
    
    public double getForce()
    {
	return Math.pow(accelX * accelX + accelY * accelY, 0.5);
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
    
    
    public String[] getIntersection(Coordinates thisPoint, Coordinates otherPoint, Motion otherMovement)
    {
	boolean theyIntersect;
	double intersectX1 = 0.0, intersectX2 = 0.0, intersectY1 = 0.0, intersectY2 = 0.0;
	double A, B, C;
	String[] solutionsX = {"", ""}, solutionsY = {"", ""};
	
	    A = (getAccelX()-otherMovement.getAccelX())/2.0;
	    B = getSpeedX()-otherMovement.getSpeedX();
	    C = thisPoint.getPositionX()-otherPoint.getPositionX();
	
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
	    
		A = (getAccelY()-otherMovement.getAccelY())/2.0;
		B = getSpeedY()-otherMovement.getSpeedY();
		C = thisPoint.getPositionY()-otherPoint.getPositionY();
	
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
    
}
