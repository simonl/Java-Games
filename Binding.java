
public class Binding<K, V> {
	private final K key;
	private final V value;

	public Binding(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return this.key;
	}

	public V getValue() {
		return this.value;
	}

	public String toString() {
		return key + " :: " + value;
	}

}