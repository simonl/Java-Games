

public class Vector implements MutableArithmetic<Vector> {


	//QUICK INDICES
	public static final int X = 0, Y = 1, Z = 2;
	public static final Vector I = new Vector(1), J = new Vector(0, 1), K = new Vector(0, 0, 1);

	//VALUES
	private Quantity[] values;


	//CONSTRUCTOR
	public Vector(Quantity... values) {
		int max = values.length;
		for(int i = values.length - 1; i >= 0; i--)
			if(!values[i].isZERO) {
				max = i;
				break;
			}

		this.values = new Quantity[max];
		for(int i = 0; i < max; i++)
			this.values[i] = values[i];
	}

	private Vector(Vector v) {
		this(v.values);
	}


	//GETTERS
	public Quantity get(int item) {
		if(item < 0 || item > values.length)
			return Quantity.ZERO;
		return values[item];
	}

	public Quantity getNorm() {
		return this.mult(this).sqrt();
	}

	public Quantity getTheta() {
		return Math.atan(get(Y) / get(X));
	}

	public Quantity getPHI() {
		return Math.atan2(get(Z), get(X));
	}

	public Integer getDimensions() {
		return values.length;
	}


	//SETTERS
	public void set(int item, Quantity value) {
		if(this.values.length < item)
			grow(item);

		values[item] = value;
	}

	public void set(Quantity... values) {
		set(0, values);
	}

	public void set(int index, Quantity... values) {
		if(this.values.length < index + values.length)
			grow(index + values.length);

		for(int i = 0; i < values.length; i++)
			this.values[index + i] = values[i];
	}

	public void rotateTheta(Quantity angle) {

	}

	public void rotatePhi(Quantity angle) {

	}


	//ARITHMETIC
	public Vector plus(Vector v) {
		Quantity[] values = new Quantity[Math.max(this.values.length, v.values.length)];

		for(int i = 0; i < values.length; i++)
			values[i] = this.get(i).plus(v.get(i));

		return new Vector(values);
	}

	public Vector minus(Vector v) {
		return this.plus(v.negative());
	}

	public Vector times(Vector v) {
		//ONLY VALID FOR 3D

		return new Vector(	get(Y).times(v.get(Z)).minus(get(Z).times(v.get(Y))),
							get(Z).times(v.get(X)).minus(get(X).times(v.get(Z))),
							get(X).times(v.get(Y)).minus(get(Y).times(v.get(X)))	);
	}

	public Vector times(Vector... v) {
		return new Matrix().determinant();
	}

	public Quantity mult(Vector v) {
		int max = Math.min(this.values.length, v.values.length);

		Quantity total = new Quantity();
		for(int i = 0; i < max; i++)
			total.plusAssign(this.get(i).times(v.get(i)));

		return total;
	}

	public Vector divide(Vector v) {
		return this.times(v.inverse());
	}

	public Vector negative() {
		Quantity[] values = new Quantity[this.values.length];

		for(int i = 0; i < values.length; i++)
			values[i] = this.values[i].negative();

		return new Vector(values);
	}

	public Vector inverse() {
		Quantity factor = getNorm().sqr();
		Quantity[] values = new Quantity[this.values.length];

		for(int i = 0; i < values.length; i++)
			values[i] = this.values[i].divide(factor);

		return new Vector(values);
	}

	//ASSIGNMENTS
	public void assign(Vector v) {
		this.values = v.values;
	}

	public void plusAssign(Vector v) {

	}
	public void minusAssign(Vector v) {

	}
	public void timesAssign(Vector v) {

	}
	public void divideAssign(Vector v) {

	}

	public void negate() {
		for(int i = 0; i < values.length; i++)
			values[i] = values[i].negative();
	}

	public void invert() {
		Quantity factor = getNorm().sqr();
		for(int i = 0; i < values.length; i++)
			values[i] = values[i].divide(factor);
	}




}