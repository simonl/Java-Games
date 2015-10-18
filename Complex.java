package javaapps.physicsobject;

public class Complex
{
	//Of the form C = a + ib
	double a, b;

	public Complex()
	{

	}

	public Complex(double real, double imaginary)
	{
		setA(real);
		setB(imaginary);
	}

	public void setA(double a)
	{
		this.a = a;
	}

	public void setB(double b)
	{
		this.b = b;
	}

	public double getA()
	{
		return this.a;
	}

	public double getB()
	{
		return this.b;
	}

	///
	public Complex plus(Complex c)
	{
		return new Complex(getA()+c.getA(), getB()+c.getB());
	}

	public Complex plus(double r)
	{
		return plus(new Complex(r, 0));
	}

	public Complex minus(Complex c)
	{
		return plus(c.getNegative());
	}

	public Complex minus(double r)
	{
		return minus(new Complex(r, 0));
	}

	public Complex getNegative()
	{
		return new Complex(-getA(), -getB());
	}

	public Complex times(Complex c)
	{
		return new Complex(getA()*c.getA() - getB()*c.getB(), getA()*c.getB() + getB()*c.getA());
	}

	public Complex times(double r)
	{
		return times(new Complex(r, 0));
	}

	public Complex divideBy(Complex c)
	{
		if(c.isReal())
			return divideBy(c.getA());
		else if(c.isImaginary())
			return changeParts().divideBy(c.getB());
		else
			return times(c.getConjugate()).divideBy(c.times(c.getConjugate()));
	}

	public Complex divideBy(double r)
	{
		return new Complex(getA()/r, getB()/r);
	}

	public Complex exp(Complex c)
	{
		if(c.isReal())
			return exp(c.getA());
		else if(c.isImaginary())
			return;
	}

	public Complex exp(int j)
	{
		Complex exp = new Complex(1, 0);

		for(int i = 1; i <= j; i ++)
			exp = exp.times(this);

		return exp;
	}

	public Complex exp(double r)
	{
		if(isReal())
			return Math.exp(r * Math.log(getA());
		else if(isImaginary())
			return
		else
	}

	public Complex exp()
	{
		Complex exp = new Complex(java.lang.Math.E, 0);

		return (new Complex(Math.cos(getB()), Math.sin(getB()))).times(Math.exp(getA()));	//e^a*cos(b) , e^a*sin(b)

	}

	///
	public Complex getConjugate()
	{
		return new Complex(getA(), -getB());
	}

	public Complex changeParts()
	{
		return new Complex(getB(), getA());
	}

	public boolean isImaginary()
	{
		return getA() == 0;
	}

	public boolean isReal()
	{
		return getB() == 0;
	}

	public boolean isComplex()
	{
		return !isReal() && !isImaginary();
	}

}
