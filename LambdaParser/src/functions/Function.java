package javaapps.functions;

import javaapps.Parser;

public abstract class Function
{
	public String type = "function";
	public String name;
	public abstract double apply(String[] args);
}