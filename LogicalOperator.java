

public enum LogicalOperator {
	AND,
	OR,
	XOR,
	NOT;

	public boolean apply(boolean[] p) {
		switch(this) {
			case AND : {
				for(boolean b : p)
					if(!b) return false;
				return true;
			}
			case OR : {
				for(boolean b : p)
					if(b) return true;
					return false;
			}
			case XOR : {
				int t = 0;
				for(boolean b : p)
					if(b) if(t > 1) return false; else t++;
				return true;
			}
			case NOT : return !p[0];
			default : return false;
		}
	}

	public boolean apply(boolean a, boolean b) {
		switch(this) {
			case AND : return a && b;
			case OR : return a || b;
			case XOR : return a ^ b;
			case NOT : return !a;
			default : return false;
		}
	}

	public boolean apply(boolean b) {
		switch(this) {
			case AND : return b;
			case OR : return b;
			case XOR : return b;
			case NOT : return !b;
			default : return false;
		}
	}

	public String toString() {
		switch(this) {
			case AND : return "&&";
			case OR : return "||";
			case XOR : return "^";
			case NOT : return "!";
			default : return "false";
		}
	}

}
