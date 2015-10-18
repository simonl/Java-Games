
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


public class Cannonball
{
    //Instance variables
    public static final double G = 9.80000;
    public static final double DELAY = 0.05;
    public static Dimensions ballDimensions = new Dimensions(10.0, 10.0, 2);

    private int id;

    public Coordinates shotPosition, midWayPosition, finalPosition;
    public CoordinateSystem coordinates;


    //Constructors

    public Cannonball(double timeStamp, int id, double positionX)
    {
	setId(id);
	shotPosition = new Coordinates(timeStamp, positionX, 1.0);
    }


    //accessors
    public int getId()
    {
	return id;
    }

    public void setId(int id)
    {
	this.id = id;
    }

    //Custom Methods
    public void updateCannonball()
    {
	coordinates.updateCoordinates();

    }

    public void defineTrajectory(double xSpeed, double ySpeed)
    {
	coordinates = new CoordinateSystem(shotPosition.getPositionX(), shotPosition.getPositionY(), xSpeed, ySpeed);


	double maxTime = 2.0 * ySpeed /G + shotPosition.getPositionY() / (ySpeed + G / 2.0);
	double maxDistance = maxTime * xSpeed + shotPosition.getPositionX();

	    finalPosition = new Coordinates(maxTime, maxDistance, 0.0);

	double criticalTime = ySpeed / G;
	double maxHeight = Math.pow(ySpeed, 2) / 2.0 / G + shotPosition.getPositionY();
	double criticalDistance = criticalTime * xSpeed + shotPosition.getPositionX();

	    midWayPosition = new Coordinates(criticalTime, criticalDistance, maxHeight);

    }

    public boolean checkIfLanded()
    {
	return (coordinates.getPositionY() <= finalPosition.getPositionY());
    }

	public void explode() {
		this.finalPosition.setPositionX(this.coordinates.getPositionX());
		this.finalPosition.setPositionY(this.coordinates.getPositionY());
	}

}
