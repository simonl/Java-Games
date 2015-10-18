package javaapps.functions;

import javaapps.Parser;

public abstract class Defined extends Function
{
	public String[] params;
	public String body;

	public Defined(String[] pieces)
	{
		this.name = pieces[0];
		this.args = Parser.tokenize(pieces[1]);
		this.body = pieces[2];
	}

	public double apply(String[] args)
	{
		return Parser.evaluate(substitute(args));
	}

	public String substitute(String[] args);
}