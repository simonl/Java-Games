
import java.text.*;

public class Vector
{
	public static final Vector ZERO  = new Vector();

	public static final Vector AxisX = new CartesianVector(1);
	public static final Vector AxisY = new CartesianVector(0, 1);
	public static final Vector AxisZ = new CartesianVector(0, 0, 1);

	public static final Vector neg_AxisX = new CartesianVector(-1);
	public static final Vector neg_AxisY = new CartesianVector(0, -1);
	public static final Vector neg_AxisZ = new CartesianVector(0, 0, -1);

	public static final Vector MINIMUM = new CartesianVector(Double.NEGATIVE_INFINITY,
															 Double.NEGATIVE_INFINITY,
															 Double.NEGATIVE_INFINITY);

	public static final Vector MAXIMUM = new CartesianVector(Double.POSITIVE_INFINITY,
															 Double.POSITIVE_INFINITY,
															 Double.POSITIVE_INFINITY);


    //Instance variables
    double magnitude, theta, phi;
    double X, Y, Z;

    final double TOOSMALL = 1E-10;
    static long vectorCount = 0;

    //Constructors
    public Vector()
    {
	setVectorCount(getVectorCount() + 1);
	    //System.out.println(getVectorCount());
    }


    //Getters
    public double getX()
    {
	return X;
    }

    public double getY()
    {
	return Y;
    }

    public double getZ()
    {
	return Z;
    }

    public double get(int el) {
		switch(el) {
			case 0 : return X;
			case 1 : return Y;
			case 2 : return Z;
			default : return magnitude;
		}
	}

    public double getMagnitude()
    {
	return magnitude;
    }

    public double getMagnitude(double X, double Y, double Z)
    {
	return Math.sqrt(X*X + Y*Y + Z*Z);
    }

    public double getTheta()
    {
	return theta;
    }

    public double getTheta(double X, double Y)
    {
	return Math.atan(Y / X);
    }

    public double getPhi()
    {
	return phi;
    }

    public double getPhi(double X, double Z)
    {
	return Math.atan(X / Z);
    }

    public long getVectorCount()
    {
	return vectorCount;
    }

    //Setters
    public void setX(double X)
    {
	this.X = zeroIfTooSmall(X);
    }

    public void setY(double Y)
    {
	this.Y = zeroIfTooSmall(Y);
    }

    public void setZ(double Z)
    {
	this.Z = zeroIfTooSmall(Z);
    }

    public void setMagnitude(double magnitude)
    {
	this.magnitude = zeroIfTooSmall(magnitude);
    }

    public void setTheta(double theta)
    {
	this.theta = zeroIfTooSmall(theta);
    }

    public void setPhi(double phi)
    {
	this.phi = zeroIfTooSmall(phi);
    }

    public void setVectorCount(long vectorCount)
    {
	this.vectorCount = vectorCount;
    }

    //CUSTOM
    public void updateComponents(double dX)
    {
	setX(getX() + dX);

	completeCartesianVector();
    }

    public void updateComponents(double dX, double dY)
    {
	setX(getX() + dX);
	setY(getY() + dY);

	completeCartesianVector();
    }

    public void updateComponents(double dX, double dY, double dZ)
    {
	setX(getX() + dX);
	setY(getY() + dY);
	setZ(getZ() + dZ);

	completeCartesianVector();
    }

    public void updateComponents(Vector dV)
    {
	setX(getX() + dV.getX());
	setY(getY() + dV.getY());
	setZ(getZ() + dV.getZ());

	completeCartesianVector();
    }

    public void updateComponents(Vector dV, double timespan) {
		this.X += dV.X * timespan;
		this.Y += dV.Y * timespan;
		this.Z += dV.Z * timespan;

		completeCartesianVector();
	}

	public void turnTheta(double dT) {
		this.theta += dT;

		completePolarVector();
	}

	public void turnPhi(double dP) {
		this.phi += dP;

		completePolarVector();
	}

	public void turn(double dT, double dP) {
		this.theta += dT;
		this.phi += dP;

		completePolarVector();
	}

    public void completeCartesianVector()
    {
		this.phi = Math.atan2(X, Z);
		this.theta = Math.atan(Y / Math.sqrt(X*X + Z*Z));
		this.magnitude = Math.sqrt(X*X + Y*Y + Z*Z);
    }

    public void completePolarVector()
    {
		//setX(magnitude * Math.sqrt(1.0 / ( 1.0 + Math.tan(theta) * Math.tan(theta) + Math.tan(phi) * Math.tan(phi))));
		//setY(Math.tan(theta) * X);
		//setZ(Math.tan(phi) * X);

		this.X = Math.sin(phi) * magnitude;
		this.Y = Math.sin(theta) * magnitude;
		this.Z = Math.cos(phi) * magnitude;
    }

    public double zeroIfTooSmall(double value)
    {
	if(value < TOOSMALL && value > -TOOSMALL)
	    value = 0;

	return value;
    }

    public double getDifferenceOfMagnitude(Vector vector)
    {
	return getMagnitude() - vector.getMagnitude();
    }




	//ARITHMETIC
    public Vector addVector(Vector vector)
    {
	double newX = getX() + vector.getX();
	double newY = getY() + vector.getY();
	double newZ = getZ() + vector.getZ();

	return (new CartesianVector(newX, newY, newZ));
    }

    ///
    public void setToVectorAddition(Vector vector)
    {
	setX(getX() + vector.getX());
	setY(getY() + vector.getY());
	setZ(getZ() + vector.getZ());

	completeCartesianVector();
    }

    ///
    public void setToVectorAddition(Vector vector, double factor)
    {
	setX(getX() + vector.getX() * factor);
	setY(getY() + vector.getY() * factor);
	setZ(getZ() + vector.getZ() * factor);

	completeCartesianVector();
    }

    public Vector negative() {
		return new CartesianVector(-X, -Y, -Z);
	}

    public Vector substractVector(Vector vector)
    {
	double newX = getX() - vector.getX();
	double newY = getY() - vector.getY();
	double newZ = getZ() - vector.getZ();

	return (new CartesianVector(newX, newY, newZ));
    }

    ///
    public void setToVectorSubstraction(Vector vector)
    {
	setX(getX() - vector.getX());
	setY(getY() - vector.getY());
	setZ(getZ() - vector.getZ());

	completeCartesianVector();
    }

    ///
    public void setToVectorSubstraction(Vector vector, double factor)
    {
	setX(getX() - vector.getX() * factor);
	setY(getY() - vector.getY() * factor);
	setZ(getZ() - vector.getZ() * factor);

	completeCartesianVector();
    }

    public double dotProduct(Vector vector)
    {
	double productX = getX() * vector.getX();
	double productY = getY() * vector.getY();
	double productZ = getZ() * vector.getZ();

	return (productX + productY + productZ);
    }


    public double separatingAngle(Vector vector)
    {
		return Math.acos(dotProduct(vector) / getMagnitude() / vector.getMagnitude());
    }

    public Vector crossProduct(Vector vector)
    {
		double partX = getY() * vector.getZ() - getZ() * vector.getY();
		double partY = getZ() * vector.getX() - getX() * vector.getZ();
		double partZ = getX() * vector.getY() - getY() * vector.getX();

		return (new CartesianVector(partX, partY, partZ));
    }

    ///
    public void setToCrossProduct(Vector vector)
    {
		double partX = getY() * vector.getZ() - getZ() * vector.getY();
		double partY = getZ() * vector.getX() - getX() * vector.getZ();
		double partZ = getX() * vector.getY() - getY() * vector.getX();

		setX(partX);
		setY(partY);
		setZ(partZ);

		completeCartesianVector();
    }

    public Vector scalarProduct(double factor)
    {
	return (new CartesianVector(getX() * factor, getY() * factor, getZ() * factor));
    }

    ///
    public void setToScalarProduct(double factor)
    {
	setX(getX() * factor);
	setY(getY() * factor);
	setZ(getZ() * factor);

	completeCartesianVector();
    }

    public Vector projectOnto(Vector other) {
		Vector otherUnit = other.unit();
		return otherUnit.scalarProduct( this.dotProduct(otherUnit) );
	}

    public double getMultiplierA(Vector vector1, Vector vector2)
    {
	return (getX() * vector2.getY() - getY() * vector2.getX()) / (vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX());
    }

    public double getMultiplierB(Vector vector1, Vector vector2)
    {
	return (getX() * vector1.getY() - getY() * vector1.getX()) / (vector2.getX() * vector1.getY() - vector2.getY() * vector1.getX());
    }

    public double getMultiplier(Vector vector)
    {
	if(equalsDirection(vector))
	{
	    return getMagnitude()/vector.getMagnitude();
	}
	else if(equalsOrientation(vector))
	{
	    return -getMagnitude()/vector.getMagnitude();
	}
	else
	    return 0;
    }

    public Vector reflectVector(double X, double Y, double Z)
    {
	if(X != 0)
	    X = -getX();
	else
	    X = getX();

	if(Y != 0)
	    Y = -getY();
	else
	    Y = getY();

	if(Z != 0)
	    Z = -getZ();
	else
	    Z = getZ();

	return (new CartesianVector(X, Y, Z));
    }

	public Vector unit() {
		return new CartesianVector(X / getMagnitude(), Y / getMagnitude(), Z / getMagnitude());
	}

    ///
    public void setToReflectedVector(boolean X, boolean Y, boolean Z)
    {
	if(X)
	    setX(-getX());

	if(Y)
	    setY(-getY());

	if(Z)
	    setZ(-getZ());

	if(X || Y || Z)
	{
	    completeCartesianVector();
	}
    }

    ///
    public void setToVector(Vector vector)
    {
	setX(vector.getX());
	setY(vector.getY());
	setZ(vector.getZ());

	completeCartesianVector();
    }

    ///
    public void setToComponents(double X, double Y, double Z)
    {
	setX(X);
	setY(Y);
	setZ(Z);

	completeCartesianVector();
    }

    public void setToPolarComponents(double magnitude, double theta, double phi) {
		setMagnitude(magnitude);
		setTheta(theta);
		setPhi(phi);

		completePolarVector();
	}

    ///
    public void setToVector(Vector vector, double factor)
    {
	setX(vector.getX() * factor);
	setY(vector.getY() * factor);
	setZ(vector.getZ() * factor);

	completeCartesianVector();
    }

    public Vector abs() {
		return new CartesianVector(Math.abs(X), Math.abs(Y), Math.abs(Z));
	}

	public void toAbsolute() {
		this.X = Math.abs(X);
		this.Y = Math.abs(Y);
		this.Z = Math.abs(Z);
	}

	public void makeNull() {
		this.Z = this.Y = this.X = 0.0;
	}

	public void normalizeTo(Vector ref) {
		this.theta -= ref.theta;
		this.phi -= ref.phi;

		completePolarVector();
	}

	public Vector[] components(Vector axis) {
		Vector[] comps = new Vector[2];

		comps[0] = this.projectOnto(axis);
		comps[1] = this.substractVector(comps[0]);

		return comps;
	}

	public Vector average(Vector b) {
		return new CartesianVector((X + b.X) / 2, (Y + b.Y) / 2, (Z + b.Z) / 2);
	}

    //Obligatory
    public boolean equals(Vector vector)
    {
	boolean checkX = getX() == vector.getX();
	boolean checkY = getY() == vector.getY();
	boolean checkZ = getZ() == vector.getZ();

	return (checkX && checkY && checkZ);
    }

    public boolean equalsDirection(Vector vector)
    {
	boolean checkTheta = getTheta() == vector.getTheta();
	boolean checkPhi = getPhi() == vector.getPhi();

	return (checkTheta && checkPhi);
    }

    public boolean equalsOrientation(Vector vector)
    {
	Vector reverse = vector.reflectVector(1, 1, 1);

	return (equalsDirection(vector) || equalsDirection(reverse));

    }

    public Vector copy()
    {
	return (new CartesianVector(getX(), getY(), getZ()));
    }

    ///
    public boolean isZero() {
		return X == 0 && Y == 0 && Z == 0;
	}

	public boolean isNotZero() {
		return X != 0 || Y != 0 || Z != 0;
	}

    public Vector transferReference()
    {
	return this;
    }

    public String toString()
    {
		DecimalFormat format = new DecimalFormat("#.##");

	String description = "C(" + format.format(X) +
						 ", " + format.format(Y) +
						 ", " + format.format(Z) +
						 ") -- P(" + format.format(magnitude) +
						 ", " + format.format(theta) +
						 ", " + format.format(phi) + ")";
	return description;
    }

}
