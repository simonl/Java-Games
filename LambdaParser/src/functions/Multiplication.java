package javaapps.functions;

import javaapps.Parser;

public class Multiplication extends Function
{
	public Multiplication(){ this.name = "*"; }

	public double apply(String[] args)
	{
		double product = 1.0;
			for(String arg : args)
				product *= Parser.evaluate(arg);
		return product;
	}
}