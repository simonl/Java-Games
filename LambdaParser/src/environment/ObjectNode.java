package environment;

import javaapps.*;

public class ObjectNode
{
	private FnContainer item;
	private String name;

	private ObjectNode prev;
	private ObjectNode next;

	@SuppressWarnings("unused")
	private ObjectNode() { }

	public ObjectNode(String name, FnContainer item)
	{
		this.name = name;
		this.item = item;
	}

	public void setPrev(ObjectNode node)
	{
		this.prev = node;
	}

	public void setNext(ObjectNode node)
	{
		this.next = node;
	}

	public Lambda item()
	{
		if(!this.item.isEvaled())
		{
			this.item = LambdaParser.eval(this.item.body(), this.item.parent());
			System.out.println(item);
		}
		return ((Lambda)this.item);
	}

	public String name()
	{
		return this.name;
	}

	public ObjectNode prev()
	{
		return this.prev;
	}

	public ObjectNode next()
	{
		return this.next;
	}

	public boolean is(ObjectNode node)
	{
		return this == node;
	}

	public boolean isLast()
	{
		return next().item() == null;
	}

	public boolean isFirst()
	{
		return prev().item() == null;
	}

	public String toString()
	{
		return name() + " : " + this.item;
	}
	
	public void fadeOut()
	{
		prev().setNext(next());
		next().setPrev(prev());
		setPrev(null);
		setNext(null);
	}

	public void insert(ObjectNode node)
	{
		node.setNext(next());
		node.setPrev(this);

		next().setPrev(node);
		setNext(node);
	}

}