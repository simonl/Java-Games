package environment;

import javaapps.*;

public class LocalEnvironment extends Environment
{
	private ObjectNode node;

	public LocalEnvironment(Environment parent)
	{
		super(parent);
	}

	public boolean add(String name, FnContainer item)
	{
		this.node = new ObjectNode(name, item);
		return true;
	}

	public Lambda get(String name)
	{
		System.out.print("\t" + name + " == " + (this.node == null ? "null" : this.node.name()) + " ?");
		if(this.node == null || !this.node.name().equals(name))
		{
			System.out.println(" false!" + (this.parent == null ? " > null" : ""));
			return this.parent.get(name);
		}
		
		System.out.println(" true! --> " + node);
		return node.item();
	}

	public boolean delete(String name)
	{
		if(this.node == null || !this.node.name().equals(name))
			return this.parent.delete(name);
		this.node = null;
		return true;
	}

	public boolean exists(String name)
	{
		if(this.node == null || !this.node.name().equals(name))
			return this.parent.exists(name);
		return true;
	}
	
	public String toString()
	{
		return "" + this.node + this.parent;
	}
	
}