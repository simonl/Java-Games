package environment;

import javaapps.*;

public class GlobalEnvironment extends Environment
{
	private int numOfItems = 0;
	private ObjectList[] items = makeLists(13);

	private int smallP = 17;
	private int largeP = 13;

	public GlobalEnvironment()
	{
		super(null);
	}

	public boolean add(String name, FnContainer item)
	{
		if(isTooFull())
		{
			grow();
			System.out.println(this);
		}
		
		this.numOfItems++;
		return this.items[function(name)].add(name, item);
	}

	public boolean delete(String name)
	{
		this.numOfItems--;
		return this.items[function(name)].delete(name);
	}

	public Lambda get(String name)
	{
		return this.items[function(name)].get(name);
	}

	public int capacity()
	{
		return this.items.length;
	}

	private void grow()
	{
		this.smallP = nextPrime(this.smallP);
		this.largeP = nextPrime(this.largeP);

		ObjectList[] oldItems = this.items;
		this.items = makeLists(this.largeP);

		ObjectNode node;
		for(ObjectList list : oldItems)
			while(list.hasNext())
			{
				node = list.pop();
				add(node.name(), node.item());
			}

	}

	private ObjectList[] makeLists(int num)
	{
		ObjectList[] lists = new ObjectList[num];
		for(int i = 0; i < num; i++)
			lists[i] = new ObjectList();

		return lists;
	}

	private int function(String name)
	{
		int num = 0;
		for(int i = 0; i < name.length(); i++)
			num = num * this.smallP + (int)name.charAt(i);
		//System.out.println(name + " --> " + num);
		return num % this.largeP;
	}

	private int nextPrime(int num)
	{
		int i = num * 2 + 1;
		while(!isPrime(i))
			i += 2;
		return i;
	}

	private boolean isPrime(int num)
	{
		if(num < 2) return false;
		if(num % 2 == 0)
			if(num == 2) return true;
			else return false;

		int sqrt = (int)Math.sqrt(num) + 1;
		for(int i = 3; i < sqrt; i++)
			if(num % i == 0)
				return false;

		return true;
	}

	public boolean isTooFull()
	{
		return this.numOfItems > this.largeP * 2;
	}

	public String toString()
	{
		String s = "";
		String list = "";
		for(int i = 0; i < this.items.length; i++)
		{
			list = "" + this.items[i];
			if(!list.equals(""))
				s += "Box " + i + " : \n" + this.items[i];
		}

		return s;
	}

	public boolean exists(String name)
	{
		return this.items[function(name)].exists(name);
	}

	public static void main(String[] args)
	{

	}
}