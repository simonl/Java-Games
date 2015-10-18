
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


public class Tank
{
    //Instance variables
    public static final double DELAY = 0.05;
    public static Dimensions tankDimensions = new Dimensions(20.0, 10.0, 1);

    public CoordinateSystem coordinates;

    public Cannonball ball1 = null, ball2 = null;

    private String name;
    private int id;

    private double health = 100.0;


    //Default constructor
    public Tank (String name, int id)
    {
	this.name = name;
	this.id = id;

	coordinates = new CoordinateSystem();
    }


    //Accessors

    public double getHealth()
    {
	return health;
    }

	public boolean isAlive() {
		return health > 0;
	}

    public int getId()
    {
	return id;
    }

    public String getName()
    {
	return name;
    }

    //Setters

    public void setHealth(double health)
    {
	this.health = health;
    }

    public void setId(int id)
    {
	this.id = id;
    }

    public void setName(String name)
    {
	this.name = name;
    }

    //Custom Methods

    public void changeMotion()
    {
	coordinates.setAccelX(enterParameter(getName() + " - Enter the tank's desired acceleration (-1...0...1):"));
    }

    public void stopTank(double wall)
    {
	coordinates.setPositionX(wall);
	coordinates.setSpeedX(0.0);
	coordinates.setAccelX(0.0);
    }

    public int enterParameter(String instruction)
    {
	return Integer.parseInt(JOptionPane.showInputDialog(instruction));
    }

    public void play()
    {
	shootCannonball();
	changeMotion();
    }

    public void updateTank()
    {
	coordinates.updateCoordinates();

	if(ball1 != null)
	{
	    if(ball1.checkIfLanded())
		ball1 = null;
	    if(ball1 != null)
		ball1.updateCannonball();
	}
	if(ball2 != null)
	{
	    if(ball2.checkIfLanded())
		ball2 = null;
	    if(ball2 != null)
		ball2.updateCannonball();
	}
    }


    //Custom Methods
    public void shootCannonball()
    {
	if(ball1 == null)
	    ball1 = createCannonball();
	else if(ball2 == null)
	    ball2 = createCannonball();
    }


    public void getHit(Coordinates impactPoint)
    {
	double difference =
	    Math.pow(Math.pow(coordinates.getPositionX()-impactPoint.getPositionX(), 2)+Math.pow(impactPoint.getPositionY(), 2), 0.5);

	setHealth(getHealth() - 1000.0/Math.abs(difference));
    }

    public void crash(double speed, double wall)
    {
	setHealth(getHealth() - Math.pow(speed, 2)/9.0);

	stopTank(wall);
    }

    public void crash(double wall) {
		this.crash(this.coordinates.getSpeedX(), wall);
	}


    public Cannonball createCannonball()
    {
	double angle = checkAngle(enterParameter(getName() + " - Enter the angle of attack (-0<<-90 : 90>>0):"));
	double speed = checkSpeed(enterParameter(getName() + " - Enter the outgoing velocity (0-100 m/s):"));

	    Cannonball ball = new Cannonball(coordinates.getTime(), getId(), coordinates.getPositionX());

	double xSpeed = Math.cos(angle) * speed + coordinates.getSpeedX();
	double ySpeed = Math.sin(angle) * speed;

	    ball.defineTrajectory(xSpeed, ySpeed);

	return ball;
    }

    public double checkAngle(int angle)
    {
	if(angle < 0)
	    angle += 180;

	if(angle < 0)
	    angle = 0;
	if(angle > 180)
	    angle = 180;

	return angle/360.0*2.0*Math.PI;
    }

    public double checkSpeed(double speed)
    {
	    if(speed < 0)
		speed = 0;
	    if(speed > 100)
		speed = 100;

	return speed;
    }


}
