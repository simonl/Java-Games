


public interface Expression {
	public abstract Numeric reduce();
}

public class Plus implements Expression {

	private Numeric result;
	
	private final Expression e1;
	private final Expression e2;
	
	public Plus(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Numeric reduce() {
		if(result == null)
			result = e1.reduce().plus(e2.reduce());
		return result;
	}
}

public class Minus implements Expression {

	private Numeric result;
	
	private final Expression e1;
	private final Expression e2;
	
	public Plus(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Numeric reduce() {
		if(result == null)
			result = e1.reduce().minus(e2.reduce());
		return result;
	}
}

public class Power implements Expression {

	private Numeric result;
	
	private final Expression e1;
	private final Expression e2;
	
	public Plus(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Numeric reduce() {
		if(result == null)
			result = e1.reduce().pow(e2.reduce());
		return result;
	}
}

public class Root implements Expression {

	private Numeric result;
	
	private final Expression e1;
	private final Expression e2;
	
	public Plus(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Numeric reduce() {
		if(result == null)
			result = e1.reduce().root(e2.reduce());
		return result;
	}
}

public class Times implements Expression {

	private Numeric result;
	
	private final Expression e1;
	private final Expression e2;
	
	public Plus(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Numeric reduce() {
		if(result == null)
			result = e1.reduce().times(e2.reduce());
		return result;
	}
}

public class Divide implements Expression {

	private Numeric result;
	
	private final Expression e1;
	private final Expression e2;
	
	public Plus(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Numeric reduce() {
		if(result == null)
			result = e1.reduce().divide(e2.reduce());
		return result;
	}
}

public class Atom implements Expression {

	private Numeric value;
	
	public Plus(Numeric e1) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Numeric reduce() {
		return value;
	}
}