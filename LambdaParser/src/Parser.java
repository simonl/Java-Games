package javaapps;

import java.util.Scanner;
import java.io.*;

public class Parser
{
	/*
	public static ??? eval(String token)
	{
		if(isNumeric(token))
			return applyNumeric(token);
		else if(isString(token))
			return applyString(token);
		else if(isBoolean(token))
			return applyBoolean(token);
		else if(isFunction(token))
			return applyFunction(token);
		else if(isVariable(token))
			return applyVariable(token);
		else
			throw Exception;
	}

	(myFun 3 4)
	myLam = lambda x (lambda y (x y))
	other = lambda x (* x x)
	(myLam other 4)

	(lambda x (lambda y (x y)) (lambda x (* x x) 4)
	((lambda y ((lambda x (* x x) y) 4)
	((lambda y (* y y)) y)

	(lambda x (lambda y (* x y)) >3< 4)
	(lambda y (* 3 y) >4<)
	(* 3 4)
	12

	apply(eval(fn), args*)

	apply(fn, args) {
		if(isRef(fn))
			return apply(eval(fn);
		else if(isLambda(fn))
			return eval(doLambda(args));
	}

	doLambda(args)
	{
		return body with 'arg' where 'param'
	}

	((lambda x (* x x)) 4)
	(subst x (* x x) 4)
	(* 4 4)
	16

	(lambda x x)
	((lambda x x) 3)

	((lambda x (lambda y (* x y))) 4 6)
	(subst x (lambda y (* x y)) 4 6)
	((lambda y (* 4 y)) 6)				subst --> '(' + newBody + args + ')'
	(subst y (* 4 y) 6)
	(* 4 6)

	eval( >(lambda x 'body')< )
	{
		isRef X
		isFn V
	}

	apply( >'lambda' , 'x' , 'body'< , >4< )
	{
		isRef X
		isLambda() V
			return >'(subst x body)'<
		isSubst() X
	}

	((lambda x (* x x) 4)

	apply(eval(lambda...) , 4)


	(mine 6)
	((lambda x x) 6)
	(subst x x 6)
	x

	eval(token) {
		if(isRef)
			return getRef(token)
		else if(isFn(token))
			return apply(getName(token), getArgs(token));
	}

	subst(args)
	{
		for all 'param' in 'body' : 'arg'
		return body
	}


	define(String[] pieces)
	{
		Function f = new Function(extract(pieces[1]), pieces[2]);

		if(env.isTooFull())
			env.grow();

		env.add(pieces[0], f);
	}

	getRef(String name)
	{
		return env.get(name);
	}

	undefine(String name)
	{
		env.delete(name);
	}

	public Function(String params, String body)
	{
		this.body = body;
		this.params = Parser.tokenize(params);
	}

	(lambda 'param' 'body' 'arg'*) --> 'bo + arg + dy' 'args'(*-1)

	//TYPE CHECKS
	public static boolean isNumeric(String token)
	{
		return Character.isDigit(token.charAt(0));
	}

	public static boolean isString(String token)
	{
		return token.charAt(0) == '\"';
	}

	public static boolean isBoolean(String token)
	{
		return token.equals("true") || token.equals("false");
	}

	public static boolean isFunction(String token)
	{
		return token.charAt(0) == '(' && token.charAt(token.length()-1) == ')';
	}

	public static boolean isVariable(String token)
	{
		return true; //If everything else fails, this HAS to be good
	}

	//TYPE APPLIES
	public static double applyNumeric(String token)
	{
		return Double.parseDouble(token);
	}

	public static String applyString(String token)
	{
		return token;
	}

	public static boolean applyBoolean(String token)
	{
		return (token.equals(true) ? true : false);
	}

	public static ??? applyVariable(String token)
	{
		return eval(env.get(token));
	}

	public static ??? applyFunction(String token)
	{
		String[] tokens = tokenize(extract(token));
		String name = tokens[0];
		String[] args = breakArgs(tokens);

		if(isBuiltIn(name))
			return doBuiltIn(name, args);
		else if(isIf(name, args))
			return doIf(name, args);
		else if(isDefine(name))
			return doDefine(name, args);
		else if(isCustom(name))
			return doCustom(name, args);
	}


	(cond (bool act) (bool act))
	*/


	//			BEGIN
	////////////////////////////


	public static String[] functions = {"+", "-", "*", "/", "^", "#", "fact",
										"exp", "rand", "def", "root", "if",
										"del", "==", "cond", ">", "<", "&&",
										"||", "!", "save", "import"};
	public static String[] variables = {"pi", "e", "i", "mem", "exit"};

	public static String[][] environment = new String[10][3];
	public static int definitions = 0;



	//public static final Environment env = new Environment();

	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		//String s = "(/ (+ (r 4) (r 4)) 2)";

		/* DEFINE function
		 *
		 * (def name (params) (body))
  		 * (def fun1 (x y z) (+ (x) (y) (z)))
		 * (def fun2 (a b) (* (a) 2 (- 3 (b))))
		 * (def const () (+ 2 (- 4 2)))
		 * (def var1 () 34.2)
		 */

		while(display(evaluate(input(keyboard))));
	}

	/*
	double evaluate(String);
	String[] tokenize(String);
	String extract(String);
	double apply-function(String[])

	doNative(String[] args);
	doDefined(String[] args);
	getAtom(String name);
	getVariable(String name);

	Ex function : return
	*/







	public static void growEnv()
	{
		String[][] newList = new String[Parser.environment.length * 2][3];

		for(int i = 0; i < Parser.definitions; i++)
			newList[i] = Parser.environment[i];

		Parser.environment = newList;
	}

	public static boolean notEnoughSpace()
	{
		return Parser.definitions == Parser.environment.length - 2;
	}

	public static void define(String[] args)
	{
		if(notEnoughSpace())
			growEnv();

		String name = args[0];

		int num = findDefined(name);
		if(num != -1)
			evaluate("(del " + name + ")");

		num = num == -1 ? Parser.definitions : num;

		Parser.environment[num][0] = name;
		Parser.environment[num][1] = extract(args[1]);
		Parser.environment[num][2] = args[2];

		Parser.definitions++;
	}

	public static double doDefined(String name)
	{
		return evaluate(substituteVar(findDefined(name)));
	}

	public static String substituteVar(int id)
	{
		return Parser.environment[id][2];
	}

	public static double doDefined(String name, double[] args)
	{
		return evaluate(substituteParams(findDefined(name), args));
	}

	public static String substituteParams(int id, double[] args)
	{
		//System.out.println("Substitute --> " + listArgs(args));

		String[] argNames = tokenize(Parser.environment[id][1]);
		String body = Parser.environment[id][2];

		//System.out.println("\tIn --> " + body);

		int pos;
		String param;
		for(int i = 0; i < argNames.length; i++)
		{
			param = argNames[i];

			pos = findParam(param, body);

			//System.out.println(param + " : " + pos);
			while(pos != -1)
			{
				body = putArg(args[i], pos, body);
				//System.out.println("\t\tStep --> " + body);
				pos = findParam(param, body);
			}
		}

		return body;
	}

	public static int findParam(String param, String body)
	{
		int pos;

		pos = body.indexOf(' ' + param + ' ');
		if(pos != -1) return pos + 1;

		pos = body.indexOf('(' + param + ' ');
		if(pos != -1) return pos + 1;

		pos = body.indexOf(' ' + param + ')');
		if(pos != -1) return pos + 1;

		pos = body.indexOf('(' + param + ')');
		if(pos != -1) return pos + 1;

		return -1;
	}

	public static String putArg(double value, int pos, String body)
	{
		//System.out.print("\tReplace --> " + value + " at ");
		//System.out.println(body.substring(0, pos) + '*' + body.substring(pos, body.length()));

		int end = min(body.indexOf(' ', pos), body.indexOf(')', pos));

		//System.out.print(end + " --> ");
		String b = body.substring(0, pos) + value + body.substring(end, body.length());
		//System.out.println("Body --> " + b);
		return b;
	}

	public static int min(int num1, int num2)
	{
		if(num1 < num2) return num1 == -1 ? num2 : num1;
		return num2;
	}

	public static String[] tokenize(String args)
	{
		//System.out.print("Tokens --> " + args);
		String[] tokens = new String[args.length()];
		int num = 0;

		int parenCount = 0;
		int start = 0;

		for(int i = 0; i < args.length(); i++)
		{
			if(args.charAt(i) == '(')
				parenCount++;
			else if(args.charAt(i) == ')')
				parenCount--;
			else if(parenCount == 0 && args.charAt(i) == ' ')
			{
				tokens[num] = args.substring(start, i);
				num++;
				start = i + 1;
			}
		}
		tokens[num] = args.substring(start, args.length());
		num++;

		tokens = shrink(tokens, num);

		//System.out.println(" --> " + printTokens(tokens));

		return tokens;
	}

	public static String[] shrink(String[] list, int size)
	{
		String[] newList = new String[size];
		for(int i = 0; i < size; i++)
			newList[i] = list[i];
		return newList;
	}

	public static int findDefined(String name)
	{
		//System.out.println("Defined --> " + name);
		for(int i = 0; i < Parser.definitions; i++)
			if(Parser.environment[i][0].equals(name))
				return i;
		return -1;
	}

	public static char getType(String token)
	{
		//System.out.println("Typing --> " + token);
		if(isReference(token))
			if(isFunction(token))
				return 'F';
			else
				return 'V';
		else
			return 'A';


	}

	public static boolean isReference(String token)
	{
		return token.charAt(0) == '(';
	}

	public static boolean isFunction(String token)
	{
		return token.indexOf(' ') != -1;
	}

	public static boolean display(double result)
	{
		System.out.println(/*"\nResult --> " + */result);
		System.out.println("\n-------------------------" +
							  "-------------------------\n");
		return true;
	}







	/*			START INPUT			*/

	public static String input(Scanner keyboard)
	{
		String[] query = new String[15];
		int[] tabs = new int[15];
		int numLines = 0;
		int pos = 0;
		int parenCount = 0;
		System.out.print(">>> ");

			query[numLines] = keyboard.nextLine();

		do
		{

			for(int i = numLines; i >= 0; i--)
			{
				parenCount = getParenCount(query[i], parenCount);
				//System.out.println(i + " : " + parenCount);
				if(parenCount < 0)
				{
					pos = tabs[i] + getCurrentTab(query[i], parenCount);
					//System.out.println("chosen --> " + i + " : " + pos + " tabs");
					parenCount = 0;
					break;
				}
			}

			System.out.print("... " + repeat(' ', pos));
			tabs[numLines + 1] = pos;
			numLines++;

			query[numLines] = keyboard.nextLine();

		}while(!query[numLines].equals(""));

		System.out.println();

		return mash(query);
	}

	public static String mash(String[] lines)
	{
		String query = lines[0];
		for(int i = 1; i < lines.length; i++)
			if(lines[i] == null || lines[i].equals(""))
				return query;
			else
				query += " " + lines[i];

		return query;
	}

	public static int getCurrentTab(String line, int parenCount)
	{
		char c;

		for(int i = 0; i < line.length(); i++)
		{
			c = line.charAt(i);
			if(c == ')')
				parenCount--;
			else if(c == '(')
				parenCount++;
			if(parenCount == 0)
				return tokenEnd(line, i);
		}

		return 0;
	}

	public static int tokenEnd(String line, int pos)
	{
		int parenCount = 0;
		char c;

		for(int i = pos + 1; i < line.length(); i++)
		{
			c = line.charAt(i);
			//System.out.print(c + " ");
			if(c == ')')
				parenCount--;
			else if(c == '(')
			{
				parenCount++;
				if(parenCount == 0)
					if(getParenCount(line.substring(i) + 1, 0) == 0)
						return line.indexOf(' ', i);
			}
		}

		return line.length() + 1;
	}

		public static int getParenCount(String line, int parenCount)
		{
			char c;

			for(int i = line.length()-1; i >= 0; i--)
			{
				c = line.charAt(i);
				if(c == ')')
					parenCount++;
				else if(c == '(')
					parenCount--;
			}

			return parenCount;
	}

	/*			END INPUT		*/







	public static double evaluate(String token)
	{
		//System.out.println("Evaluate --> " + token);
		return apply(token, getType(token));
	}

	//NO
	public static double evaluate_2(String piece)
	{
		System.out.println("\n" + piece + " --> Evaluating");

		if(isMethod(piece))
			return parseMethod(piece.substring(1, piece.length() - 1));
		if(isNumber(piece))
		{
			System.out.println("\t" + toDouble(piece) + " --> VALUE");
			return toDouble(piece);
		}
		if(isWord(piece))
			return charAvg(piece);
		else
			return charAvg(piece);

	}

	public static String extract(String token)
	{
		//System.out.println("Extract --> " + token);
		if(token.length() == 2) return "";
		return token.substring(1, token.length()-1);
	}

	public static double apply(String token, char type)
	{
		//System.out.println("Apply --> " + token + ", " + type);
		switch(type)
		{
			case 'A' : return getValue(token);
			case 'V' : return getVariable(extract(token));
			case 'F' : return doFunction(tokenize(extract(token)));
			default : System.out.println("YOU FAIL!"); return 0.0;
		}
	}

	//NO
	public static int findEnd(String s, int start)
	{
		char c;
		int count = 0;
		for(int i = start; i < s.length(); i++)
		{
			c = s.charAt(i);
			if(c == '(')
				count++;
			else if(c == ')')
				count--;
			if(c == ' ' && count == 0)
				return i;
		}

		return s.length();
	}

	//NO
	public static double parseMethod(String call)
	{
		System.out.println("\t" + call + " --> Method Call");

		int nameEnd = call.indexOf(' ');
		if(nameEnd == -1)
			return matchVariable(call);

		String name = call.substring(0, nameEnd);
		String args = call.substring(nameEnd+1, call.length());

		if(name.equals("define"))
		{
			//define(args);
			return 0.0;
		}
		else if(isDefined(name))
		{
			return doDefined(name, evalEach(tokenize(args)));
		}

		return matchMethod(name, collectArgs(args));
	}

	//NO
	public static double[] collectArgs(String args)
	{
		//System.out.println("\t" + "\t" + args + " --> Arguments");

		return evalEach(tokenize(args));
	}

	public static double[] evalEach(String[] tokens)
	{
		//System.out.println(printTokens(tokens));

		double[] values = new double[tokens.length];
		for(int i = 0; i < tokens.length; i++)
			values[i] = evaluate(tokens[i]);
		return values;
	}

	public static String printTokens(String[] tokens)
	{
		String tok = "";
		for(String arg : tokens)
			tok += arg + " : ";
		return tok;
	}

	//NO
	public static int numOfArgs(String args)
	{
		int parCount = 0;
		int num = 1;

		for(int i = 0; i < args.length(); i++)
			if(args.charAt(i) == '(')
				parCount++;
			else if(args.charAt(i) == ')')
				parCount--;
			else if(args.charAt(i) == ' ' && parCount == 0)
				num++;

		return num;
	}

	public static String[] breakArgs(String[] pieces)
	{
		//System.out.print("Break --> " + printTokens(pieces));

		String[] args = new String[pieces.length-1];
		for(int i = 0; i < pieces.length - 1; i++)
			args[i] = pieces[i+1];


		//System.out.println(" --> " + printTokens(args));

		return args;
	}

	public static double getValue(String atom)
	{
		return Double.parseDouble(atom);
	}

	public static double doFunction(String[] pieces)
	{
		String name = pieces[0];
		String[] argsRaw = breakArgs(pieces);
		double[] args = null;

		int id = findFunction(name);
		if(id != 9 && id != 11 && id != 12 && id != 14
			&& id != 17 && id != 18 && id != 20 && id != 21)
			args = evalEach(argsRaw);

		//System.out.println(id);
		switch(id)
		{
			case -1: return doDefined(name, args);
			case 0 : return plus(args);
			case 1 : return minus(args);
			case 2 : return times(args);
			case 3 : return divide(args);
			case 4 : return power(args);
			case 5 : return self(args);
			case 6 : return factorial(args);
			case 7 : return exponent(args);
			case 8 : return random(args);
			case 9 : define(argsRaw); return 0.0;
			case 10 : return root(args);
			case 11 : return doIf(argsRaw);
			case 12 : delete(argsRaw[0]); return 0.0;
			case 13 : return equals(args);
			case 14 : return doCond(argsRaw);
			case 15 : return greaterThan(args);
			case 16 : return smallerThan(args);
			case 17 : return doAnd(argsRaw);
			case 18 : return doOr(argsRaw);
			case 19 : return doNot(args);
			case 20 : return doSave(argsRaw);
			case 21 : return doImport(argsRaw);
			default : System.out.println("YOU FAIL!"); return 0.0;
		}
	}

	public static void delete(String name)
	{
		int id;
		id = findDefined(name);
		if(id != -1)
		{
			environment[id] = new String[3];
			definitions--;
		}

	}

	public static int findFunction(String name)
	{
		//System.out.println(name + " ");
		for(int i = 0; i < Parser.functions.length; i++)
			if(name.equals(Parser.functions[i]))
				return i;
		return -1;
	}

	//NO
	public static double matchMethod(String method, double[] args)
	{
		System.out.println("\t" + "\t" + "\t" + method + " " + listArgs(args) + " --> Perform Function");

		double value = 0.0;
		switch(method.charAt(0))
		{
			case '+' : value = plus(args); break;
			case '-' : value = minus(args); break;
			case '*' : value = times(args); break;
			case '/' : value = divide(args); break;
			case '^' : value = power(args); break;
			case '#' : value = self(args); break;
			case '!' : value = factorial(args); break;
			case 'e' : value = exponent(args); break;
			case 'r' : value = random(args); break;
			default  : System.out.println("YOU ARE A FAILURE AT LIFE!!!"); break;
		}

		System.out.println("\n\t\t\t\t" + value + " --> VALUE\n");

		return value;
	}

	public static double getVariable(String var)
	{
		switch(findVariable(var))
		{
			case -1 : return doDefined(var);
			case 0 : return Math.PI;
			case 1 : return Math.E;
			case 2 : return -1;
			case 3 : return memContent();
			case 4 : System.exit(0);
			default : System.out.println("YOU FAIL!"); return 0.0;
		}
	}

	public static double memContent()
	{
		for(String[] item : Parser.environment)
		{
			for(String arg : item)
				System.out.print(arg + " : ");
			System.out.println();
		}
		return 0.0;
	}

	public static int findVariable(String name)
	{
		for(int i = 0; i < Parser.variables.length; i++)
			if(name.equals(Parser.variables[i]))
				return i;
		return -1;
	}

	//NO
	public static double matchVariable(String var)
	{
		System.out.println("\t" + "\t" + "\t" + var + " " + " --> Perform Substitution");

		double value = 0.0;
		switch(var.charAt(0))
		{
			case 'Z' : value = 0.0; break;
			case 'O' : value = 1.0; break;
			case 'P' : value = Math.PI; break;
			case 'E' : value = Math.E; break;
			default  : System.out.println("YOU ARE A FAILURE AT LIFE!!!"); break;
		}

		System.out.println("\n\t\t\t\t" + value + " --> VALUE\n");

		return value;
	}


/*			METHODS			*/

	public static String listArgs(double[] args)
	{
		String s = "";
		for(int i = 0; i < args.length; i++)
			s += args[i] + " : ";
		return s;
	}

	public static double times(double[] a)
	{
		double product = 1;
		for(int i = 0; i < a.length; i++)
			product *= a[i];
		return product;
	}

	public static double plus(double[] a)
	{
		double sum = 0;
		for(int i = 0; i < a.length; i++)
			sum += a[i];
		return sum;
	}

	public static double minus(double[] a)
	{
		double diff = a[0];

		if(a.length == 1)
			return -diff;

		for(int i = 1; i < a.length; i++)
			diff -= a[i];
		return diff;
	}

	public static double divide(double[] a)
	{
		double quotient = a[0];
		for(int i = 1; i < a.length; i++)
			quotient /= a[i];
		return quotient;
	}

	public static double power(double[] a)
	{
		double power = a[0];
		for(int i = 1; i < a.length; i++)
			power = Math.pow(power, a[i]);
		return power;
	}

	public static double self(double[] a)
	{
		return a[0];
	}

	public static double factorial(double[] a)
	{
		int n;
		int fact = 1;
		for(int j = 0; j < a.length; j++)
		{
			n = (int)a[j];
			for(int i = 2; i <= n; i++)
				fact *= i;
		}

		return fact;
	}

	public static double exponent(double[] a)
	{
		double exp = Math.E;
		for(int i = 0; i < a.length; i++)
			exp = Math.pow(exp, a[i]);
		return exp;
	}

	public static double random(double[] a)
	{
		double rand;
		if(a.length == 0)
			rand = Math.random();
		if(a.length == 1)
			rand =  Math.random() * a[0];
		else
			rand = (Math.random() * (a[1]-a[0])) + a[0];

		return (int)(rand * 100) / 100.0;
	}

	public static double root(double[] a)
	{
		double root = a[0];
		for(int i = 1; i < a.length; i++)
			root = Math.pow(root, 1.0/a[i]);
		return root;
	}

	public static double doIf(String[] a)
	{
		//System.out.print(a[0] + " : ");
		//System.out.print(a[1] + " : ");
		//System.out.println(a[2]);

		if(evaluate(a[0]) != 0)
			return evaluate(a[1]);
		else
			return evaluate(a[2]);
	}

	public static double doCond(String[] a)
	{
		String[] conds = new String[a.length];
		for(int i = 0; i < a.length - 1; i++)
			conds[i] = extract(a[i]);
			conds[a.length-1] = a[a.length - 1];

		String nested = "";
		for(int i = 0; i < a.length-1; i++)
			nested += "(if " + conds[i] + " ";

		nested += "(if 1 " + conds[a.length-1];
		nested += repeat(')', a.length);

		//System.out.println(nested);

		return evaluate(nested);
	}

	public static String repeat(char c, int n)
	{
		String r = "";
		for(int i = 0; i < n; i++)
			r += c;
		return r;
	}

	public static double equals(double[] a)
	{
		if(a[0] - a[1] == 0) return 1;
		return 0;
	}

	public static double greaterThan(double[] args)
	{
		if(args[0] > args[1]) return 1;
		return 0;
	}

	public static double smallerThan(double[] args)
	{
		if(args[0] < args[1]) return 1;
		return 0;
	}


	public static double doAnd(String[] args)
	{
		for(int i = 0; i < args.length; i++)
			if(evaluate(args[i]) == 0)
				return 0;
		return 1;
	}


	public static double doOr(String[] args)
	{
		for(int i = 0; i < args.length; i++)
			if(evaluate(args[i]) != 0)
				return 1;
		return 0;
	}

	public static double doNot(double[] args)
	{
		if(args[0] == 0.0)
			return 1;
		return 0;
	}


	@SuppressWarnings("finally")
	public static double doSave(String[] args)
	{
		try{

			File f = new File(args[0] + ".txt");
			PrintWriter writer = new PrintWriter(new FileWriter(f, true));

			System.out.println("\tSaved to : " + f.getAbsolutePath());
			if(!f.exists())
				f.createNewFile();

			if(f.canWrite())
			{
				String[] fn;

				for(int i = 0; i < definitions; i++)
				{
					fn = environment[i];

					if(findInFile(f, fn[0]))
						deleteInFile(f, fn[0]);

					writer.println("(def " + fn[0] + " (" + fn[1] + ") " + fn[2] + ")\n");
				}
			}

			writer.close();

		}finally {
			return 0.0;
		}
	}

	public static boolean findInFile(File f, String name)
	{

		return true;
	}

	public static boolean deleteInFile(File f, String name)
	{
		return true;
	}

	@SuppressWarnings("finally")
	public static double doImport(String[] args)
	{
		try{

		Scanner reader = new Scanner(new File(args[0] + ".txt"));
		String line = reader.nextLine();

		while(!line.equals(""))
		{
			evaluate(line);

			//System.out.println("\tImported : " + line.substring(5, line.indexOf(' ', 5)));

			reader.nextLine();
			line = reader.nextLine();

			//System.out.println("Line : " + line);
		}

		reader.close();

		}finally {
			return 0.0;
		}
	}


// (def *6*name*line.indexOf(' ', 6)* (...



	/*		OTHER		*/
	public static double toDouble(String s)
	{
		return Double.parseDouble(s);
	}


	public static double charAvg(String s)
	{
		double avg = 0;
		for(int i = 0; i < s.length(); i++)
			avg += (int)s.charAt(i);
		return avg / s.length();
	}

	public static boolean isMethod(String s)
	{
		return s.charAt(0) == '(' && s.charAt(s.length()-1) == ')';
	}

	public static boolean isCustom(String s)
	{
		return isMethod(s) && isDefined(s);
	}

	public static boolean isDefined(String s)
	{
		for(int i = 0 ; i < Parser.definitions; i++)
			if(s.equals(Parser.environment[i][1]))
				return true;
		return false;
	}

	public static boolean isNumber(String s)
	{
		return 48 <= s.charAt(0) && s.charAt(0) <= 57;
	}

	public static boolean isWord(String s)
	{
		for(int i = 0; i < s.length(); i++)
			if(!Character.isLetter(s.charAt(i)))
				return false;
		return true;
	}

/*
	public static String regexp(String s, String p)
	{
		int num = findNum(p);
		char c = p.charAt(0);
		int pos;

		if(Character.isLetter(c))
		{
			pos = s.indexOf(c);
			if(pos != -1)
				return regexp(s.substring(pos), p.substring(1));
		}
		else if(c == '[')
		{
			String trials = p.substring(1, p.indexOf(']'));
		}

		return "";
	}

	public static int findNum(String p)
	{
		int num = 0;
		char c;
		for(int i = 0; i < p.length(); i++)
		{
			c = p.charAt(i);

			if(Character.isLetter(c))
				num++;
			else if(c == '[')
			{
				int end = p.indexOf(']', i);
				num += end - i - 1;
				i = end;
			}
			else if(c == '*')
				num = -1;
		}

		return num;
	}
*/
}
