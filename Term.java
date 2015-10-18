package javaapps.physicsobject;

public class Term
{
    private char variable;
    private double varValue;
    private double power;
    private double factor;

	public static void main(String[] args)
	{
		Term term = new Term();
		System.out.println(term.toString() + "\n" + term.getValue());
	}
    public Term()
    {
		setVariable('A');
		setVarValue(1.0);
		setFactor(1.0);
		setPower(1.0);
    }

	///
	public char getVariable()
	{
		return this.variable;
	}

	public double getVarValue()
	{
		return this.varValue;
	}

	public double getFactor()
	{
		return this.factor;
	}

	public double getPower()
	{
		return this.power;
	}

	public double getValue()
	{
		return getFactor() * Math.pow(getVarValue(), getPower());
	}

	///
	public void setVariable(char variable)
	{
		this.variable = variable;
	}

	public void setVarValue(double varValue)
	{
		this.varValue = varValue;
	}

	public void setFactor(double factor)
	{
		this.factor = factor;
	}

	public void setPower(double power)
	{
		this.power = power;
	}

	///
	public String toString()
	{
		return getFactor() + "*" + getVariable() + "^" + getPower();
	}

}
