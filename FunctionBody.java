
public class FunctionBody implements Evaluable {
	private final String[] body;

	public FunctionBody(final String[] body) {
		this.body = body;
	}

	public Object evaluate(final Environment env) {
		return Object.VOID;
	}
}