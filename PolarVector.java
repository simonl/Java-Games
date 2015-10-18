
public class PolarVector extends Vector
{
    //Instance variables


    //Constructors
    public PolarVector(double magnitude)
    {
	setMagnitude(magnitude);

	completePolarVector();
    }

    public PolarVector(double magnitude, double theta)
    {
	setMagnitude(magnitude);
	setTheta(theta);

	completePolarVector();
    }

    public PolarVector(double magnitude, double theta, double phi)
    {
	setMagnitude(magnitude);
	setTheta(theta);
	setPhi(phi);

	completePolarVector();
    }
}
