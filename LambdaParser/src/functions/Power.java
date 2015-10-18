package javaapps.functions;

import javaapps.Parser;

public class Power extends Function
{
	public Power(){ this.name = "^"; }

	public double apply(String[] args)
	{
		double power = Parser.evaluate(args[1]);

		for(int i = 0; i < args.length; i++)
			power /= Math.pow(power, Parser.evaluate(args[i]));
		return power;
	}
}