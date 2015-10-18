package environment;

import javaapps.*;

public class ObjectList
{
	private ObjectNode sentinel = new ObjectNode("Sentinel", null);
	private int numOfItems = 0;

	public ObjectList()
	{
		this.sentinel.setPrev(this.sentinel);
		this.sentinel.setNext(this.sentinel);
	}

	public boolean add(String name, FnContainer item)
	{
		ObjectNode node = new ObjectNode(name, item);

		this.sentinel.prev().insert(node);
		this.numOfItems++;

		return true;
	}

	public Lambda get(String name)
	{
		return this.find(name).item();
	}

	private ObjectNode find(String name)
	{
		ObjectNode current = this.sentinel.next();

		while(!current.is(this.sentinel))
		{
			System.out.print("\t" + name + " == " + current.name() + " ?");
			//System.out.println(current.name());
			if(match(current.name(), name))
			{
				System.out.println(" true!");
				return current;
			}
			current = current.next();
		}

		throw new IllegalArgumentException("Not a valid name --> " + name);
	}

	public boolean delete(String name)
	{
		try {
			find(name).fadeOut();
			this.numOfItems--;
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	private boolean match(String name, String key)
	{
		return name.equals(key);
	}

	public ObjectNode pop()
	{
		ObjectNode node = this.sentinel.next();
		node.fadeOut();
		this.numOfItems--;

		return node;
	}

	public boolean hasNext()
	{
		return !this.sentinel.is(this.sentinel.next());
	}

	public String toString()
	{
		String s = "";
		ObjectNode current = this.sentinel.next();
		while(!current.is(this.sentinel))
		{
			s += "\t" + current + "\n";
			current = current.next();
		}
		return s;
	}

	public boolean exists(String name)
	{
		try{
			find(name);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
