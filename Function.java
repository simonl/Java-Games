
//o.abc -> env.get("o").getAttribute("abc")
//f(...) -> env.get("f").call(...)
//o.f(...) -> env.get("o").getAttribute("f").call(...)



public class Function implements Callable {
	public static final Types type = Types.FUNCTION;
	public static final Function VOID = new Function(new Environment(null), new FunctionBody(new String[0]));

	private final Environment local;
	private final ParameterList params;
	private final FunctionBody body;


	public Function(final Environment local, final FunctionBody body) {
		this(local, new ParameterList(new String[0]), body);
	}

	public Function(final Environment local,
					final ParameterList params,
					final FunctionBody body) {
		this.params = params;
		this.body 	= body;
		this.local 	= local;
	}

	public Object call(final Object[] args) {
		local.add(this.params.toBindings(args));

		final Object returned = this.body.evaluate(local);

		local.clear();

		return returned;
	}

	public String toString() {
		return this.params + " -> " + this.body;
	}
}


