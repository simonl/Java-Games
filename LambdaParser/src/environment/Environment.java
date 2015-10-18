package environment;

import javaapps.*;

public abstract class Environment
{
	protected final Environment parent;
	
	public static Environment[] stats = new Environment[20];
	public static int numOfEnv = 0;
	
	public Environment(Environment parent)
	{
		this.parent = parent;
		stats[numOfEnv] = this;
	}

	public abstract boolean add(String name, FnContainer item);
	public abstract Lambda get(String name);
	public abstract boolean delete(String name);

	public abstract boolean exists(String name);
	public int depth()
	{
		return this.parent == null ? 0 : 1 + this.parent.depth();
		
	}
}
