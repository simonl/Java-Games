
public class Spheric extends Dimensions
{
    //Instance variables


    //Constructors
    public Spheric(double R)
    {
	this.R = Math.abs(R);
	this.X = Math.sqrt(R * R/3.0);
	this.Y = Math.sqrt(R * R/3.0);
	this.Z = Math.sqrt(R * R/3.0);

	this.isASphere = true;
    }

}
