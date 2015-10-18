package javaapps;

import java.util.Scanner;
//import java.io.*;

import environment.*;

public class LambdaParser
{
	public static final GlobalEnvironment global = new GlobalEnvironment();
	public static final Scanner keyboard = new Scanner(System.in);
	//public static Scanner file;
	
	
	//static{
	//	try {
	//		file = new Scanner(new File("gilles"));
	//	} catch (FileNotFoundException e) {
	//		e.printStackTrace();
	//	}
	//}
	

	public static void init(String[] defs)
	{
		for(String def : defs)
			eval(def, global);
	}
	
	public static String[] getBuiltins()
	{
		String[] builtins = {
				"(define (++ a) (lambda (f x) (f (a f x))))",
				"(define (+ a b) (a ++ b))",
				//"(define (-- n) (lambda (f x) (n (lambda (g k) (g 1 (lambda u (PLUS (g k) 1)) k)) (lambda l 0) 0)))",
				//"(define (-- a) (lambda (f x) (a (lambda (g h) (h (g f))) (lambda u x) (lambda u u))))",
				"(define (-- a) (FIRST (a NEXT (PAIR 0 0))))",
				"(define (- a b) (b -- a))",
				"(define (* a b) (a (+ b) 0))",
				"(define (^ a b) (a b))",
				"(define (/ a b) 0)",
				
				"(define (ID x) x)",
				"(define (TRUE a b) a)",
				"(define (FALSE a b) b)",
				"(define (IF c t f) (c t f))",
				"(define (AND a b) (a b a))",
				"(define (OR a b) (a a b))",
				"(define (NOT a) (a FALSE TRUE))",
				"(define (XOR a b) (OR (AND a (NOT b)) (AND (NOT a) b)))",
				
				"(define (isZERO n) (n (lambda x FALSE) TRUE))",
				"(define (<= a b) (isZERO (- a b)))",
				"(define (== a b) (AND (<= a b) (<= b a)))",
				"(define (> a b) (NOT (<= a b))",
				"(define (>= a b) (AND (> a b) (== b a)))",
				
				"(define (PAIR a b) (lambda f (f a b)))",
				"(define (FIRST p) (p TRUE))",
				"(define (SECOND p) (p FALSE))",
				"(define (P_MIN p) (if (isZERO (- (FIRST p) (SECOND p))) (FIRST p) (SECOND p)))",
				"(define (NEXT p) (PAIR (SECOND p) (++ (SECOND p))))"
				};
		
		return builtins;
	}
	
	public static void main(String[] args)
	{
		init(getBuiltins());
		System.out.println(global);
		while(print(force(eval(read(keyboard), global))));
	}


	private static Lambda force(Lambda lambda) {
		return lambda;
	}

	/*			READ			*/


	public static String read(Scanner keyboard)
	{
		System.out.print("> ");
		return keyboard.nextLine();
	}


	/*			EVAL			*/


	public static Lambda eval(String expression, Environment env)
	{
		print("EVAL --> " + expression);
		//SPECIAL CASES
		if(isNumber(expression))
			return churchEncode(Integer.parseInt(expression), env);
		if(!isEnclosed(expression)/*isReference(expression)*/)
			return env.get(expression).clone(env);

		return apply(tokenize(extract(expression)), env);
	}
	//Referc form --> aLambda
	//Proced form --> ( ... )

	private static String[] tokenize(String expression) {
		int numTokens = 1;
		int parenCount = 0;
		int[] pos = new int[10];
		
		//System.out.print(expression + " --> ");

		pos[0] = -1;
		for(int i = 0; i < expression.length(); i++)
		{
				if(expression.charAt(i) == '(')
					parenCount++;
				else if(expression.charAt(i) == ')')
					parenCount--;
				else if(parenCount == 0 && expression.charAt(i) == ' ')
				{
					pos[numTokens] = i;
					numTokens++;
				}					
		}
			pos[numTokens] = expression.length();
			
		String[] tokens = new String[numTokens];
		for(int i = 0; i < numTokens; i++)
			tokens[i] = expression.substring(pos[i] + 1, pos[i+1]);
		
		//printEach(tokens);
		
		return tokens;
	}
	
	private static void printEach(String[] a)
	{
		for(String s : a)
			System.out.print(s + " : ");
		System.out.println();
	}


	private static String extract(String token) {
		return token.substring(1, token.length()-1);
	}


	@SuppressWarnings("unused")
	private static boolean isReference(String token) {
		return Character.isLetter(token.charAt(0));
	}

	public enum Keywords{ lambda, define, let, cond, unencode, swch, };
	public enum Builtins{ plus, minus, times} ;
	
	
	public static Lambda apply(String[] tokens, Environment env)
	{
		//printEach(tokens);
		//SPECIAL CASES
		try
		{
			Keywords key = Keywords.valueOf(tokens[0]);
			System.out.println(tokens[0]);
			tokens = breakArgs(tokens);
			int num;
		switch(key)
		{
			case lambda : {
				return (	isEnclosed(tokens[0]) ? 
							eval(unsugarLambda(tokens), env) :
							lambda(tokens, env) );
			}
			case define : {
				return (	isEnclosed(tokens[0]) ? 
							eval(unsugarDefine(tokens), env) :
							define(tokens, env) );
			}
			case unencode : {
				num = unencode(tokens[1]);
				System.out.println(num + " <--");
				return eval("" + num, env);
			}
			case cond : {
				return eval(unsugarCond(tokens), env);
			}
			case let : {
				return eval(unsugarLet(tokens), env);
			}
			case swch : {
				System.out.println("WOW!");
				return eval(unsugarSwitch(tokens), env);
			}
		}
		}catch(Exception e) {
			return (	tokens.length == 2 ? 
						eval(tokens[0], env).call(tokens[1]) :
						eval(unsugarCall(tokens), env) );
		}
		
		return null;
	}
	
	private static String unsugarSwitch(String[] tokens) {
		String pivot = tokens[0];
		tokens = tokenize(extract(tokens[1]));
		
		String cond = "(cond ";
		
		String[] pair;
		for(int i = 0; i < tokens.length - 1; i++) {
			pair = tokenize(extract(tokens[i]));
			cond += "((== " + pair[0] + " " + pivot + ") " + pair[1] + ") ";
		}
		
		System.out.println("Switch --> " + cond + tokens[tokens.length] + ")");
		
		return cond + tokens[tokens.length] + ")";
	}
	// (switch a ((0 1) (2 4) (3 8) default))
	// (cond  ((== 0 a) 1) ((== 2 a) 4) ((== 3 a) 8) default)
	
	private static int unencode(String token) {
		
		//Ex. 3 --> (lambda f (lambda x (f (f (f x)))))
		
		String fs = token;
		int parenCount = 0;
		
		for(int i = fs.length()-1; i >= 0; i--)
		{
			if(fs.charAt(i) == ')')
				parenCount ++;
			else if(fs.charAt(i) != ')')
				break;			
		}
		return parenCount-1;
	}

	//lambda form --> (lambda x (lambda y x))
	//define form --> (define w (lambda x x))
	//proced form --> ((First Two) Three)


	/*			PRINT			*/


	private static String unsugarCall(String[] tokens) {
		String call = tokens[0];
		printEach(tokens);
		for(String arg : breakArgs(tokens)) {
			call = "(" + call + " " + arg + ")";
			System.out.println("--> " + call);
		}
		
		return call;
	}

	public static boolean isEnclosed(String token)
	{
		return token.charAt(0) == '(';			
	}

	private static String[] breakArgs(String[] tokens) {
		String[] args = new String[tokens.length-1];
		for(int i = 0; i < args.length; i++)
			args[i] = tokens[i+1];
		return args;
	}


	public static boolean print(Object value)
	{
		System.out.println((value == null ? "" : value));
		System.out.println("\n- - - - - - - - - - - - - -\n");
		//System.out.println(global);
		return true;
	}


	/*			SPECIAL CASES	*/

	public static Lambda churchEncode(int num, Environment env)
	{		
		String enc = "x))";
		for(int i = num; i > 0; i--)
			enc = "(f " + enc + ")";
		enc = "(lambda f (lambda x " + enc;
		
		return eval(enc, env);
	}

	public static Lambda define(String[] tokens, Environment env)
	{
			//System.out.println("Def --> " + tokens[0]);
		env.add(tokens[0], new FnContainer(tokens[1], env));
		
		//System.out.println(tokens[0] + " : " + tokens[1]);
		
		return null;
	}

	public static Lambda lambda(String[] tokens, Environment env)
	{
		return new Lambda(tokens[0], tokens[1], env);
	}

	//(lambda (x y) (...)) --> (lambda x (lambda y (...)))
	private static String unsugarLambda(String[] tokens)
	{
			String[] params = tokenize(extract(tokens[0]));
			int numOfItems = params.length;
			
			String lambda = tokens[1];
			for(int i = numOfItems - 1; i >= 0; i--)
				lambda = "(lambda " + params[i] + " " + lambda + ")";
			
			return lambda;
	}

	//(define (f x) (...)) --> (define f (lambda x (...)))
	private static String unsugarDefine(String[] tokens)
	{
		int space = tokens[0].indexOf(' ');		
		String name = tokens[0].substring(1, space);
		tokens[0] = "(" + tokens[0].substring(space + 1);

		return "(define " + name + " " + unsugarLambda(tokens) + ")";
	}
	
	//(cond (pred1 val1)	-->	(if pred1 val1 (if pred2 val2 (if ... default)))
	//		(pred2 val2)
	//		...
	//		default)	
	private static String unsugarCond(String[] tokens) {
		String[] cases = tokenize(extract(tokens[0]));
		
		String iff = tokens[1];
		for(int i = cases.length - 1; i >= 0; i--) {
			iff = "(IF " + extract(cases[i]) + " " + iff + ")";
			//System.out.println("###> " + iff);
		}
		
		return iff;
	}

	//(let ((g 3) (h 2)	... ) body) --> ((lambda (g h ...) body) 3 2 ...)
	private static String unsugarLet(String[] tokens) {
		String params = "";
		String args = "";
		int space = 0;
		
		for(String token : tokenize(extract(tokens[0]))) {
			token = extract(token);
			space = token.indexOf(' ');
			params += " " + token.substring(0, space);
			args += token.substring(space);
		}
		
		params = '(' + params.substring(1) + ')';
		args = args.substring(1);
		
		return "((lambda " + params + " " + tokens[1] + ") " + args + ")";
	}
	
	/*		TESTS		*/
	public static boolean isNumber(String token)
	{
		//System.out.println(">" + token + "<");
		return Character.isDigit(token.charAt(0));
	}
}
