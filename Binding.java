
public class Binding {
	private final String name;
	private Object value;

	public Binding(final String name, final Object value) {
		this.name = name;
		this.value = value;
	}

	public Object value() {
		return this.value;
	}

	public boolean setValue(final Object value) {
		this.value = value;
		return true;
	}

	public boolean maps(final String name) {
		return this.name.equals(name);
	}
}


