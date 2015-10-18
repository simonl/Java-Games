package javaapps.functions;

import javaapps.Parser;

public class Division extends Function
{
	public Division(){ this.name = "/"; }

	public double apply(String[] args)
	{
		double quotient = Parser.evaluate(args[1]);
		if(args.length == 1) return 1.0 / quotient;

		for(String arg : args)
			quotient /= Parser.evaluate(arg);
		return quotient;
	}
}