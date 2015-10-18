package javaapps;
import environment.*;

public class Lambda extends FnContainer
{
	public static final Lambda IDENTITY = new Lambda("x","x", LambdaParser.global);
	public static final Lambda TRUE = new Lambda("x", "(lambda y x)", LambdaParser.global);

	private final LocalEnvironment env;

	private String param;

	public Lambda(String param, String body, Environment env)
	{
		super(body, env);
		this.param = param;
		this.env = new LocalEnvironment(env);
	}

	public Lambda call(String arg)
	{
		LambdaParser.eval("(define " + this.param + " " + arg + ")", this.env);
		
		return this.evaluate();
	}

	@Override
	public String toString()
	{
		return "(lambda " + this.param + " " + this.body + ")";
	}

	
	@Override
	public boolean isEvaled()
	{
		return true;
	}

	public Lambda clone(Environment env)
	{
		return new Lambda(this.param, this.body, env);
	}
	
	private Lambda evaluate() {
			return LambdaParser.eval(this.body, this.env);
	}
}