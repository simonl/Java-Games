
 public class ParameterList {
 	private final String[] names;

 	public ParameterList(final String[] names) {
 		this.names = names;
 	}

 	public int size() {
 		return this.names.length;
 	}

 	public Binding[] toBindings(final Object[] values) {

 		Binding[] bindings = new Binding[size()];

 		for(int i = 0; i < bindings.length; i++)
 			bindings[i] = new Binding(names[i], values[i]);

 		return bindings;
 	}
}