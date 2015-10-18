package javaapps;

import javaapps.Parser;

public class Substraction extends Function
{
	public Substraction(){ this.name = "-"; }

	public double apply(String[] args)
	{
		double sub = Parser.evaluate(args[0]);
		if(args.length == 1) return -sub;

		for(int i = 1; i < args.length(); i++)
			sub -= Parser.evaluate(args[i]);
		return sub;
	}
}