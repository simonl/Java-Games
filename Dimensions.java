
public class Dimensions
{
    //Instance variables
    double X, Y, Z;
    double R;
    boolean isASphere;


    //Constructors
    public Dimensions()
    {

    }

    public Dimensions(double X)
    {
		this(X, 0, 0);
    }

    public Dimensions(double X, double Y)
    {
		this(X, Y, 0);
    }

    public Dimensions(double X, double Y, int Z)
    {
		this.X = Math.abs(X);
		this.Y = Math.abs(Y);
		this.Z = Math.abs(Z);

		this.R = Math.sqrt(X*X + Y*Y + Z*Z);
		this.isASphere = false;
    }

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

    public double getR()
    {
	return R;
    }

    public boolean isASphere()
    {
	return isASphere;
    }

    public void setY(double Y)
    {
	this.Y = Y;
    }

    public void setX(double X)
    {
	this.X = X;
    }

    public void setZ(double Z)
    {
	this.Z = Z;
    }

    public void setR(double R)
    {
	this.R = R;
    }

    public void setIsASphere(boolean isASphere)
    {
	this.isASphere = isASphere;
    }

    //Custom Methods
    /*
     *
     *			COLLISION DETECTION SYSTEM
     *
     *
     */

	final Vector[] impact = { new Vector(), new Vector() };
	final int NORMAL = 0;
	final int POINT = 1;

    private Vector[] checkSpheres(Dimensions object2, Vector separation) {

		double mag = separation.magnitude;
		double otherR = object2.R;

		if( mag < R + otherR && mag > Math.abs(R - otherR) ) {

			impact[NORMAL] = separation;
			impact[POINT].setToVector(separation);
			impact[POINT].setToScalarProduct(R / mag);

			return impact;
		}

		return null;
	}



	private Vector[] checkCubes(Dimensions object2, Vector separation) {

		separation.toAbsolute();

		double r = separation.magnitude;

		double oR = object2.R;

		if(r > R + oR)
			return null;


		double x = separation.X;
		double y = separation.Y;
		double z = separation.Z;

		double oX = object2.X;
		double oY = object2.Y;
		double oZ = object2.Z;

		boolean faces = (x < X + oX) && (y < Y + oY) && (z < Z + oZ);

		if( !faces )
			return null;


		final boolean intersectX =  x > Math.abs(X - oX);
		final boolean intersectY =  y > Math.abs(Y - oY);
		final boolean intersectZ =  z > Math.abs(Z - oZ);

		if( !(intersectX || intersectY || intersectZ) )
			return null;

		double touchX = X + oX - x;
		double touchY = Y + oY - y;
		double touchZ = Z + oZ - z;

		impact[NORMAL].setToComponents(
					(intersectX ? touchY * touchZ : 0),
					(intersectY ? touchX * touchZ : 0),
					(intersectZ ? touchX * touchY : 0) );


		impact[POINT].setToComponents(	X - touchX / 2,
										Y - touchY / 2,
										Z - touchZ / 2);

		return impact;
	}

	Vector corner = new Vector();
	Vector fromCorner = new Vector();
	Vector absSep = new Vector();

	private Vector[] checkCubeAndSphere(Dimensions object2, Vector separation) {

		//long startCheck = System.nanoTime();

		absSep.setToVector(separation);
		absSep.toAbsolute();

		double r = object2.R;

		if(separation.getMagnitude() > R + r)
			return null;

		double x = absSep.X;
		double y = absSep.Y;
		double z = absSep.Z;

		final boolean withinX = x < X;
		final boolean withinY = y < Y;
		final boolean withinZ = z < Z;

		final boolean facesX = withinY && withinZ;
		final boolean facesY = withinX && withinZ;
		final boolean facesZ = withinX && withinY;

		if(facesX || facesY || facesZ) {

			impact[POINT].setToVector(separation);

			if(facesX && ( x < X + r && x > Math.abs(X - r) )) {
				impact[NORMAL].setToVector(Vector.AxisX);
				impact[POINT].updateComponents((withinX ? 1 : -1) * (separation.X > 0 ? 1 : -1) * r);
			}
			else if(facesY && ( y < Y + r && y > Math.abs(Y - r) )) {
				impact[NORMAL].setToVector(Vector.AxisY);
				impact[POINT].updateComponents(0, (withinY ? 1 : -1) * (separation.Y > 0 ? 1 : -1) * r);
			}
			else if(facesZ && ( z < Z + r && z > Math.abs(Z - r) )) {
				impact[NORMAL].setToVector(Vector.AxisZ);
				impact[POINT].updateComponents(0, 0, (withinZ ? 1 : -1) * (separation.Z > 0 ? 1 : -1) * r);
			}
			else
				return null;

			return impact;
		}

		fromCorner.setToVector(separation);
		corner.setToComponents( separation.X < 0 ? -X : X,
								separation.Y < 0 ? -Y : Y,
								separation.Z < 0 ? -Z : Z);

		fromCorner.setToVectorSubstraction(corner);

		if(fromCorner.magnitude < r) {
			impact[NORMAL].setToVector(fromCorner);
			impact[POINT].setToVector(corner);

			return impact;
		}

		x = fromCorner.X;
		y = fromCorner.Y;
		z = fromCorner.Z;

		if( withinZ && (Math.sqrt(x*x + y*y) < r)) {
			impact[NORMAL].setToComponents(x, y, 0);
			impact[POINT].setToComponents(X, Y, separation.Z);
		}
		else if( withinY && (Math.sqrt(x*x + z*z) < r)) {
			impact[NORMAL].setToComponents(x, 0, z);
			impact[POINT].setToComponents(X, separation.Y, Z);
		}
		else if( withinX && (Math.sqrt(y*y + z*z) < r)) {
			impact[NORMAL].setToComponents(0, y, z);
			impact[POINT].setToComponents(separation.X, Y, Z);
		}
		else
			return null;

		//long endCheck = System.nanoTime();

		//System.out.println("Sphere & Cube check \t== " + (endCheck - startCheck));

		return impact;
	}

    public Vector[] checkIfIntersect(Dimensions object2, Vector separation) {
		//long startDetect = System.nanoTime();

		if(this.isASphere() && object2.isASphere())
			return checkSpheres(object2, separation);

		if(!this.isASphere() && !object2.isASphere())
			return checkCubes(object2, separation);

		if(this.isASphere() && !object2.isASphere())
			return object2.checkCubeAndSphere(this, separation.negative());

		//long endDetect = System.nanoTime();

		//System.out.println("Detection Time -- "+ (endDetect - startDetect));

		return checkCubeAndSphere(object2, separation);
	}
	/*
	 *
	 *
	 *			COLLISION DETECTION SYSTEM END
	 *
	 */


    public double getRadius()
    {
	return getX()*getX() + getY()*getY() + getZ()*getZ();
    }

    public double getArea()
    {
	if(isASphere())
	    return getRadius() * getRadius() * 4.0 * Math.PI;
	else
	    return 2.0*(getX()*getY() + getX()*getZ() + getY()*getZ());
    }

    public double getVolume()
    {
	if(isASphere())
	    return 4.0/3.0 * Math.PI * Math.pow(getRadius(), 3.0);
	else
	    return getX()*getY()*getZ();
    }

    public boolean equals(Dimensions other) {
		if(isASphere != other.isASphere)
			return false;

		if(isASphere && other.isASphere)
			return R == other.R;

		return X == other.X && Y == other.Y && Z == other.Z;
	}


}
