
public class CartesianVector extends Vector
{
    //Instance variables

    //Constructors
    public CartesianVector(double X)
    {
	setX(X);

	completeCartesianVector();
    }

    public CartesianVector(double X, double Y)
    {
	setX(X);
	setY(Y);

	completeCartesianVector();
    }

    public CartesianVector(double X, double Y, double Z)
    {
	setX(X);
	setY(Y);
	setZ(Z);

	completeCartesianVector();
    }


}
