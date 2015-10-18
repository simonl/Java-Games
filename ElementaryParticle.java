package javaapps.physicsobject;

/** Displays a menu, so that the user can choose between converting
 *  a hexadecimal or decimal number the he will input into the other type.
 *
 *  @authors 	Kendy Jeune, Kimberly Noel and Simon Langlois - A933125 - 420-212 section 02
 *	@since		1.5
 *  @version    16/02/2010
 */

public class ElementaryParticle extends PhysicsObject
{
	double = mass;
	Rational spin = new Rational(1, 2);

	Rational electricCharge = new Rational(1, 3);
	int redCharge;
	int blueCharge;
	int greenCharde

	int leptonNumber;
	Rational hadronNumber = new Rational(1, 3);

	public ElementaryParticle()
	{

	}

	public ElementaryParticle(int atomicNumber)
	{
		setAtomicNumber(atomicNumber);
		setIsotopeNumber(atomicNumber);
		setIonNumber(atomicNumber);

		setParticles();
	}

	public ElementaryParticle(int atomicNumber, int isotopeNumber)
	{
		setAtomicNumber(atomicNumber);
		setIsotopeNumber(isotopeNumber);
		setIonNumber(atomicNumber);

		setParticles();
	}

	public ElementaryParticle(int atomicNumber, int isotopeNumber, int ionNumber)
	{
		setAtomicNumber(atomicNumber);
		setIsotopeNumber(isotopeNumber);
		setIonNumber(ionNumber);

		setParticles();
	}

	//Getters
	public int getAtomicNumber()
	{
		return this.atomicNumber;
	}

	public int getIsotopeNumber()
	{
		return this.isotopeNumber;
	}

	public int getIonNumber()
	{
		return this.ionNumber;
	}

	//Setters
	public void setAtomicNumber(int atomicNumber)
	{
		if(atomicNumber >= 0)
			this.atomicNumber = atomicNumber;
		else
			this.atomicNumber = 0;
	}

	public void setIsotopeNumber(int isotopeNumber)
	{
		if(isotopeNumber >= atomicNumber)
			this.isotopeNumber = isotopeNumber;
		else
			this.isotopeNumber = atomicNumber;
	}

	public void setIonNumber(int ionNumber)
	{
		if(ionNumber <= atomicNumber)
			this.ionNumber = ionNumber;
		else
			this.ionNumber = 0;
	}

	public void setParticles()
	{
		this.protons = new ElementaryParticle[atomicNumber];
		this.neutrons = new ElementaryParticle[isotopeNumber - atomicNumber]
		this.electrons = new ElementaryParticle[atomicNumber - ionNumber]
	}
}