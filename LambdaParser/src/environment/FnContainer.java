package environment;

public class FnContainer {

	protected String body;
	private Environment parent;
	
	public FnContainer(String body, Environment parent)
	{
		this.body = body;
		this.parent = parent;
	}
	
	public boolean isEvaled(){
		return false;
	}

	public String body() {
		return this.body;
	}
	public Environment parent() {
		return this.parent;
	}
	
	public String toString()
	{
		return this.body();
	}

}
