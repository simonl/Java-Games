package javaapps.physicsobject;

public class Matrix
{
    private int m, n;
    private double[][] grid;
    
    
    //CONSTRUCTORS
    public Matrix()
    {
	setGrid(1, 1);
    }
    
    public Matrix(int m, int n)
    {
	setM(m);
	setN(n);
	
	setGrid(m, n);
    }

    public Matrix(Matrix b)
    {
	setToMatrix(b);
    }
    
    public Matrix(double[][] arrays, int m, int n)
    {
	setM(m);
	setN(n);
	
	setGrid(getM(), getN());
	setGrid(arrays);
    }
    
    public Matrix(Matrix[] vectors, int m, int n, boolean pos)
    {
	setM(m);
	setN(n);
	
	setGrid(getM(), getN());
	setGrid(vectors, pos);
    }
    
    //GETTERS
    public Matrix copy()
    {
	return new Matrix(this);
    }
    
    public int getM()
    {
	return this.m;
    }
    
    public int getN()
    {
	return this.n;
    }
    
    public double[][] getGrid()
    {
	return this.grid;
    }
    
    public double getCoefficient(int m, int n)
    {
	return this.grid[m-1][n-1];
    }
    
    public Matrix getRow(int i)
    {
	Matrix rowVector = new Matrix(1, getN());
	
	for(int j = 1; j <= getN(); j ++)
	    rowVector.setCoefficient(1, j, getCoefficient(i, j));
	
	return rowVector;
    }
    
    public Matrix getColumn(int j)
    {
	Matrix columnVector = new Matrix(getM(), 1);
	
	for(int i = 1; i <= getM(); i ++)
	    columnVector.setCoefficient(i, 1, getCoefficient(i, j));
	
	return columnVector;
    }
    
    public Matrix[] getRowSeries()
    {
	Matrix[] rowSeries = new Matrix[getM()];
	
	for(int i = 1; i <= getM(); i ++)
	    rowSeries[i] = new Matrix(getRow(i));
	    
	return rowSeries;
    }
    
    public Matrix[] getColumnSeries()
    {
	Matrix[] columnSeries = new Matrix[getN()];
	
	for(int j = 1; j <= getN(); j ++)
	    columnSeries[j] = new Matrix(getColumn(j));
	    
	return columnSeries;
    }
    
    //SETTERS
    public void setM(int m)
    {
	this.m = m;
    }
    
    public void setN(int n)
    {
	this.n = n;
    }
    
    public void setGrid(int m, int n)
    {
	this.grid = new double[m][n];
    }
    
    public void setGrid(double[][] b)
    {        
	for(int i = 1; i <= getM(); i++)
	    for(int j = 1; j <= getN(); j++)
		setCoefficient(i, j, b[i-1][j-1]);
    }
    
    public void setRow(Matrix rowVector, int row)
    {
	for(int j = 1; j <= getN(); j ++)
	    setCoefficient(row, j, rowVector.getCoefficient(1, j));
    }
    
    public void setColumn(Matrix columnVector, int col)
    {
	for(int i = 1; i <= getM(); i ++)
	    setCoefficient(i, col, columnVector.getCoefficient(i, 1));
    }
    
    public void setGrid(Matrix[] vectors, boolean pos)
    {
	if(pos)
	{
	    for(int i = 1; i <= getM(); i ++)
		setRow(vectors[i-1], i);
	}
	else
	{
	    for(int j = 1; j <= getN(); j ++)
		setRow(vectors[j-1], j);
	}
    }

    public void setCoefficient(int m, int n, double value)
    {
	this.grid[m-1][n-1] = value;
    }
    
    public void setToMatrix(Matrix b)
    {
	setM(b.getM());
	setN(b.getN());
	setGrid(b.getM(), b.getN());
	setGrid(b.getGrid());
    }
    
    
    //CUSTOM
    
    
    //ELLEMENTARY ROW OPERATIONS
    public void exchangeRows(int row1, int row2)
    {
	Matrix temp1 = getRow(row1);
	Matrix temp2 = getRow(row2);
	    setRow(temp1, row2);
	    setRow(temp2, row1);
    }
    
    public Matrix getMultipleOfRow(int row, double factor)
    {
	Matrix rowMultiple = getRow(row);
	
	    rowMultiple.setToScalarProduct(factor);
	    
	return rowMultiple;
    }
    
    public void multiplyRow(int row, double factor)
    {
	setRow(getRow(row).getScalarProduct(factor), row);
    }
    
    public void addRows(int row1, int row2)
    {
	setRow(getRow(row1).getMatrixAddition(getRow(row2)), row1);
    }
    
    public void addMultipleOfRow(int row1, int row2, double factor)
    {
	setRow(getRow(row1).getMatrixAddition(getMultipleOfRow(row2, factor)), row1);
    }
    
    public void addMultipleOfRows(int row1, double factor1, int row2, double factor2)
    {
	setRow(getMultipleOfRow(row1, factor1).getMatrixAddition(getMultipleOfRow(row2, factor2)), row1);
    }
    
    
    //INVERSE
    public Matrix getInverse()
    {
	Matrix aInverse = copy();
	
	    aInverse.setToInverse();
	
	return aInverse;
    }
    
    public void setToInverse()
    {        
	double determinant;
	
	determinant = getDeterminant();
	
	if(determinant != 0)
	{
	    setToAdjugate();
	    setToScalarProduct(1.0/determinant);
	}
    }
    
    public double getDeterminant()
    {
	double determinant = 0;
	
	if(getM() == getN())
	{
	    if(getM() == 2 && getN() == 2)
		determinant = getCoefficient(1, 1) * getCoefficient(2, 2) - getCoefficient(1, 2) * getCoefficient(2, 1);
	    else
	    {
		for(int i = 1; i <= getN(); i ++)
		{
		    determinant += getCoefficient(i, 1) * getCofactor(i, 1); 
		}
	    }
	}
	
	return determinant;
    }
    
    // public double getDeterminantLarge()
    // {
    //     double determinant = 0;
    // 
    //     if(getM() == getN())
    //     {
    //         if(getM() == 2 && getN() == 2)
    //             determinant = getCoefficient(1, 1) * getCoefficient(2, 2) - getCoefficient(1, 2) * getCoefficient(2, 1);
    //         else
    //             determinant = getDirectProduct(getCofactorMatrix()).getRow(1).getSum();
    //     }
    // 
    //     return determinant;
    // }
    
    public Matrix getAdjugate()
    {
	Matrix aAdjugate = copy();
	
	    aAdjugate.setToAdjugate();
	
	return aAdjugate;
    }
    
    public void setToAdjugate()
    {
	setToCofactorMatrix();
	setToTranspose();
    }
    
    public double getCofactor(int row, int col)
    {
	double cofactor = Math.pow((-1), row + col) * getMinor(row, col);
	
	return cofactor;
    }
    
    public double getMinor(int row, int col)
    {
	double minorDeterminant;
	Matrix minor = new Matrix(getM() - 1, getN() - 1);
	
	int minorI = 1, minorJ = 1;
	for(int i = 1; i <= getM(); i ++)
	{
	    if(i != row)
	    {
		for(int j = 1; j <= getN(); j ++)
		{
		    if(col != j)
		    {                        
			minor.setCoefficient(minorI, minorJ, getCoefficient(i, j));
			minorJ ++;
		    }
		}
		minorI ++;
		minorJ = 1;
	    }
	}
	
	
	minorDeterminant = minor.getDeterminant();
	
	return minorDeterminant;
    }
    
    
    //BASIC OPERATIONS
    public Matrix getMatrixAddition(Matrix b)
    {
	Matrix aAddition = copy();
	
	    aAddition.setToMatrixAddition(b);
	    
	return aAddition;
    }
    
    public void setToMatrixAddition(Matrix b)
    {
	for(int i = 1; i <= getM(); i ++)
	    for(int j = 1; j <= getN(); j ++)
		setCoefficient(i, j, getCoefficient(i, j) + b.getCoefficient(i, j));
    }
    
    public Matrix getScalarProduct(double scalar)
    {   
	Matrix aScalar = copy();
	
	    aScalar.setToScalarProduct(scalar);
		
	return aScalar;
    }
    
    public void setToScalarProduct(double scalar)
    {   
	for(int i = 1; i <= getM(); i++)
	    for(int j = 1; j <= getN(); j++)
		setCoefficient(i, j, getCoefficient(i, j) * scalar);
    }
    
    public Matrix getMatrixProduct(Matrix b)
    {
	Matrix aProduct = copy();
	
	    aProduct.setToMatrixProduct(b);
	    
	return aProduct;
    }
    
    public void setToMatrixProduct(Matrix b)
    {
	Matrix product = new Matrix(getM(), b.getN());
	    product.setToScalarProduct(0);
	    
	if(getN() == b.getM())
	{
	    for(int i = 1; i <= product.getM(); i ++)
		for(int j = 1; j <= product.getN(); j ++)
		{
		    double coefficient = 0;
		    
		    for(int a = 1; a <= getN(); a ++)
			coefficient += getCoefficient(i, a) * b.getCoefficient(a, j);
			
		    product.setCoefficient(i, j, coefficient);    
		}            
	}
	
	setToMatrix(product);
    }
    
    
    //TRANSPOSE    
    public Matrix getTranspose()
    {
	Matrix aTranspose = copy();
	
	    aTranspose.setToTranspose();
	
	return aTranspose;
    }
    
    public void setToTranspose()
    {
	Matrix transpose = new Matrix(getN(), getM());
	for(int i = 1; i <= getM(); i++)
	    for(int j = 1; j <= getN(); j++)
		 transpose.setCoefficient(j, i, getCoefficient(i, j));
	setToMatrix(transpose);
    }
    
    
    //OTHER OPERATIONS
    public double getSum()
    {
	double sum = 0;
	
	for(int i = 1; i <= getM(); i ++)
	    for(int j = 1; j <= getN(); j ++)
		sum += getCoefficient(i, j);
		
	return sum;
    }
    
    public Matrix getDirectProduct(Matrix b)
    {
	Matrix aProduct = copy();
	
	    aProduct.setToDirectProduct(b);
	    
	return aProduct;
    }
    
    public void setToDirectProduct(Matrix b)
    {
	if(getM() == b.getM() && getN() == b.getN())
	    for(int i = 1; i <= getM(); i ++)
		for(int j = 1; j <= getN(); j ++)
		    setCoefficient(i, j, getCoefficient(i, j) * b.getCoefficient(i, j));
    }
    
    public Matrix getCofactorMatrix()
    {
	Matrix aCofactor = copy();
	
	    aCofactor.setToCofactorMatrix();
	    
	return aCofactor;
    }
    
    public void setToCofactorMatrix()
    {
	Matrix tempCofactors = new Matrix(getM(), getN());
	
	for(int i = 1; i <= getM(); i++)
	    for(int j = 1; j <= getN(); j++)
		tempCofactors.setCoefficient(i, j, getCofactor(i, j));
	
	setToMatrix(tempCofactors);
    }
	
    public Matrix getSignMatrix()
    {
	Matrix aSign = copy();
	
	    aSign.setToSignMatrix();
	    
	return aSign;
    }
    
    public void setToSignMatrix()
    {    
	for(int i = 1; i <= getM(); i ++)
	    for(int j = 1; j <= getN(); j ++)
		setCoefficient(i, j, (int)Math.pow((-1), (i + j)));
    }
    
    //Utilities
    public String toString()
    {
	return "A Matrix of " + getM() + "x" + getN() + " entries\n";
    }
    
    public String drawMatrix()
    {
	String description = "";
	
	for(int i = 1; i <= getM(); i ++)
	{
	    for(int j = 1; j <= getN(); j ++)
	    {
		if(getCoefficient(i, j) >= 0)
		    description += "|  " + getCoefficient(i, j) + " ";
		else
		    description += "| " + getCoefficient(i, j) + " ";
	    }
	    description += "|\n";
	}
	
	return description;
    }
    
    public Matrix getIdentityMatrix(int size)
    {
	Matrix identity = new Matrix(size, size);
    
	for(int i = 1; i <= size; i ++)
	    for(int j = 1; j <= size; j ++)
	    {
		if(i == j)
		    identity.setCoefficient(i, j, 1);
		else
		    identity.setCoefficient(i, j, 0);            
	    }
	    
	return identity;
    }
    
    public boolean equals(Matrix b)
    {
	boolean areEqual = false;
	
	if(getM() == b.getM() && getN() == b.getN())
	{
	    areEqual = true;
	    
	    for(int i = 1; i < getM(); i ++)
		for(int j = 1; j < getN(); j ++)
		    if(getCoefficient(i, j) != b.getCoefficient(i, j))
			areEqual = false;
	}
	
	return areEqual;
    }
}
