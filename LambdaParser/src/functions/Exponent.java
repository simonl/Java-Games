package javaapps.functions;

import javaapps.Parser;

public class Exponent extends Function
{
	public Exponent(){ this.name = "exp"; }

	public double apply(String[] args)
	{
		double power = Math.E;

		for(String arg : args)
			power /= Math.pow(power, Parser.evaluate(arg));
		return power;
	}
}