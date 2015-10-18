
public class Object {
	public static final Object VOID = new Object(new Environment(null));
	public static final Types type = Types.OBJECT;

	private final Environment members;

	public Object(final Environment members) {
		this.members = members;
	}

	public Object getAttribute(final String name) {
		return this.members.get(name);
	}

	public Object call(final Object[] args) {
		return VOID;
	}
}
