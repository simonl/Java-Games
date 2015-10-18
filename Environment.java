

import java.util.*;

public class Environment {
	private final Environment parent;
	private final ArrayList<Binding> bindings = new ArrayList<Binding>();

	public Environment(final Environment parent) {
		this.parent = parent;
	}

	public Environment(final Environment parent, final Binding[] init) {
		this(parent);
		this.add(init);
	}

	public Object get(final String name) {
		for(Binding binding : this.bindings)
			if(binding.maps(name))
				return binding.value();

		if(this.parent != null)
			return this.parent.get(name);

		return Object.VOID;
	}

	public void add(final Binding[] bindings) {
		for(Binding binding : bindings)
			this.bindings.add(binding);
	}

	public void assign(final String name, final Object value) {
		if(!tryAssign(name, value))
			this.bindings.add(new Binding(name, value));
	}

	public void clear() {
		this.bindings.clear();
	}




	private boolean tryAssign(final String name, final Object value) {
		return assignHere(name, value) || assignParent(name, value);
	}

	private boolean assignHere(final String name, final Object value) {
		for(Binding binding : bindings)
			if(binding.maps(name))
				return binding.setValue(value);

		return false;
	}

	private boolean assignParent(final String name, final Object value) {
		return this.parent != null && this.parent.tryAssign(name, value);
	}

}

/*
import java.util.*;


public class Environment<K, V> extends HashMap<K, V> {
	private final Environment<K, V> parent;

	public Environment(final Environment<K, V> parent) {
		this.parent = parent;
	}

	public V get(final K key) {
		if(containsKey(key))
			super.get(key)

		if(this.parent != null)
			this.parent.get(key);

		return null;
	}

	public void put(final K key, final V value) {
		if(containsKey(key))
			super.put(key, value);

		if(this.parent != null)
			if(parent.contains(key))
				parent.put(key, value);
	}

	public boolean contains(K key) {
		return containsKey(key) ||
			  (this.parent != null &&
			   this.parent.contains(key));
	}
}

*/


