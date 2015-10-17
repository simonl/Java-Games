package boubouworld2;

public class Spheric extends Dimensions
{
    //Instance variables
	
    
    //Constructors
    public Spheric(double R)
    {        
	this.R = R;
	this.X = Math.sqrt(R/3.0);
	this.Y = Math.sqrt(R/3.0);
	this.Z = Math.sqrt(R/3.0);
	
	this.form = 0;
    }
	
}
