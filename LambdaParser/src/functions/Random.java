package javaapps.functions;

import javaapps.Parser;

public class Random extends Function
{
	public Random(){ this.name = "random"; }

	public double apply(String[] args)
	{
		if(args.length == 0) return Math.random();
		else if(args.length == 1) return Math.random() * Parser.evaluate(args[0]);
		else
		{
			double min = Parser.evaluate(args[0]);
			double max = Parser.evaluate(args[1]);
			double diff = max - min;
			return Math.random() * diff + min;
		}
	}
}