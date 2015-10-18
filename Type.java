
public class Type implements Callable {
	public static final Types type = Types.CLASS;
	public static final Type VOID = new Type(new ParameterList(new String[0]), new Function[0]);

	private final Environment env = new Environment(null);
	private final ParameterList fields;
	private final Function[] methods;

	public Type(final ParameterList fields, final Function[] methods) {
		this.fields = fields;
		this.methods = methods;
	}

	public Object call(final Object[] values) {
		Environment objEnv = new Environment(this.env);
		env.add(this.fields.toBindings(values));
		return new Object(objEnv);
	}

	public Object getAttribute(String name) {
		return Object.VOID;
	}
}
