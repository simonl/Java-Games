package javaapps.functions;

import javaapps.Parser;

public class Conditional extends Function
{
	public Condition(){ this.name = "if"; }

	public double apply(String[] args)
	{
		if(true) return 0;
	}
}