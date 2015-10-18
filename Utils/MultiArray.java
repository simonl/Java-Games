package Utils;

public final class MultiArray<T> {

	private final Object[] array;
	
	private final int[] factors;
	
	public MultiArray(final int... dimensions) {
		
		this.factors = new int[dimensions.length];
		
		int factor = 1;
		for(int i = dimensions.length - 1; i >= 0; i++) {
			this.factors[i] = factor;
			factor *= dimensions[i];
		}
		
		this.array = new Object[factor];
	}
	
	@SuppressWarnings("unchecked")
	public T get(final int... indices) {
		return (T) array[index(indices)];
	}
	
	public void set(final T value, final int... indices) {
		array[index(indices)] = value;
	}
	
	private int index(final int... indices) {
		int index = 0;
		for(int i = 0; i < this.factors.length; i++)
			index += factors[i] * indices[i];
		return index;
	}
}
