package javaapps.functions;

import javaapps.Parser;

public class DefinedAppl extends Defined
{
	public DefinedAppl(String[] pieces)
	{
		super(pieces);
	}

	public String substitute(String[] args)
	{
		String token;
		String body = this.body;
		int pos;
		double value;

		for(int i = 0; i < this.params.length; i++)
		{
			token = '(' + this.params[i] + ')';
			pos = body.indexOf(token);
			value = Parser.evaluate(args[i]);

			while(pos != -1)
			{
				body = replace(value, pos, body);
				pos = body.indexOf(token, pos);
			}
		}

		return body;
	}

	public String replace(double value, int pos, String body)
	{
		return body.substring(0, pos) + value + body.substring(body.indexOf(')', pos), body.length());
	}
}