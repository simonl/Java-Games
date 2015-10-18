
public class Coordinates
{
    //Instance variables
    public static final double DELAY = 0.1;
    private double time, positionX, positionY;


    //Constructors
    public Coordinates()
    {

    }

    public Coordinates(double time, double positionX, double positionY)
    {
	this.time = time;
	this.positionY = positionY;
	this.positionX = positionX;
    }

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

    public void setTime(double time)
    {
	this.time = time;
    }

    public void setPositionY(double positionY)
    {
	this.positionY = positionY;
    }

    public void setPositionX(double positionX)
    {
	this.positionX = positionX;
    }

    //CUSTOM
    public void updatePosition(double speedX, double speedY)
    {
	setPositionX(getPositionX() + speedX*DELAY);
	setPositionY(getPositionY() + speedY*DELAY);
	setTime(getTime() + DELAY);
    }

    public double getProximity(Coordinates point)
    {
	return (Math.pow(Math.pow(point.getPositionX()-positionX, 2.0) + Math.pow(point.getPositionY()-positionY, 2.0), 0.5));
    }
}
