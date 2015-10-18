package javaapps.functions;

import javaapps.Parser;

public class Addition extends Function
{
	public Addition(){ this.name = "+"; }

	public double apply(String[] args)
	{
		double sum = 0.0;
		for(String arg : args)
			sum += Parser.evaluate(arg);
		return sum;
	}
}
