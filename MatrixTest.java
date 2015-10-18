package javaapps.test;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javaapps.physicsobject.Matrix;

public class MatrixTest
{
	public static void main(String[] args)
	{            
	
	    Matrix[] list = {};
	    Matrix matrix1 = null, matrix2 = null;
	    
	    int num = 0;
	    
	    boolean goOn = true;
	    
	    
	    do
	    {
		int numOfChoices = 19;
		int mainChoice = getInput("What is the Matrix problem that you wish to solve?\n\n" +
					    "ELEMENTARY ROW OPERATIONS :\n" +
					    "   1. Exchange Rows\n" + 
					    "   2. Add Rows w/ factor\n" +
					    "   3. Multiply row\n\n" + 
					    "BASIC MATRIX OPERATIONS :\n" +
					    "   4. Matrix Addition\n" + 
					    "   5. Matrix Multiplication\n" +
					    "   6. Scalar Multiplication\n\n" + 
					    "MATRIX INVERSION :\n" +
					    "   7. Matrix Inversion\n" + 
					    "   8. Matrix Adjugate\n" +
					    "   9. Matrix Determinant\n" + 
					    "   10. Matrix Cofactor Matrix\n" +
					    "   11. Sign Matrix\n\n" +
					    "MATRIX TRANSPOSE :\n" +
					    "   12. Matrix Transpose\n\n" + 
					    "MATRIX INFORMATION :\n" +
					    "   13. Get Row\n" +
					    "   14. Get Column\n" +
					    "   15. Get Coefficient Sum\n" + 
					    "   16. Direct Multiplication\n\n" +
					    "UTILITIES :\n" +
					    "   17. Identity  Matrix\n" +
					    "   18. Create New Matrix\n" +
					    "   19. Consult Memory\n\n", 1, numOfChoices);
					    
		if(mainChoice < numOfChoices - 1)
		{
		    list = chooseMatrix(list, num);
		    num = java.lang.reflect.Array.getLength(list);
		    matrix1 = list[num - 1].copy();
		}
		
		int choiceInput = 0;
		
		switch(mainChoice)
		{
		    case 1 :
			    matrix1.exchangeRows(getInput("Enter row to exchange - 1 : ", 1, matrix1.getM()), 
						 getInput("Enter row to exchange - 2 : ", 1, matrix1.getM()));
			    break;
		    case 2 :
			    matrix1.addMultipleOfRow(getInput("Enter row to which to add : ", 1, matrix1.getM()),
						     getInput("Enter row to be added : ", 1, matrix1.getM()),
						     getInputDouble("Enter the factor of the second row : ", 
							Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
			    break;
		    case 3 :
			    int row = getInput(matrix1.drawMatrix() + 
					       "\nEnter row to multiply : ", 1, matrix1.getM());
			    double factor = getInputDouble(matrix1.getRow(row).drawMatrix() + 
						    "\nEnter the factor of multiplication : ", 
						    Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
			    matrix1.multiplyRow(row, factor);
			    break;
		    case 4 :
			    list = chooseMatrix(list, num);
			    num = java.lang.reflect.Array.getLength(list);
			    matrix2 = list[num - 1].copy();
			    
			    matrix1.setToMatrixAddition(matrix2);
			   break;
		    case 5 :
			    list = chooseMatrix(list, num);
			    num = java.lang.reflect.Array.getLength(list);
			    matrix2 = list[num - 1].copy();
			    
			    matrix1.setToMatrixProduct(matrix2);
			    break;
		    case 6 :
			    matrix1.setToScalarProduct(getInputDouble(matrix1.drawMatrix() + 
							"\nEnter the factor of multiplication : ", 
							Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)); 
			    break;
		    case 7 :
			    if(matrix1.equals(matrix1.getInverse()))
				displayMessage(matrix1.drawMatrix() + 
					       "\nThis matrix has no inverse!");
			    else
				matrix1.setToInverse();
			    break;
		    case 8 :
			    matrix1.setToAdjugate();
			    break;
		    case 9 :
			    displayMessage(matrix1.drawMatrix() + 
					   "\nDeterminant = " + matrix1.getDeterminant());
			    break;
		    case 10 :
			    matrix1.setToCofactorMatrix();
			    break;
		    case 11 :
			    matrix1.setToSignMatrix();
			    break;
		    case 12 :
			    matrix1.setToTranspose();
			    break;
		    case 13 :
			    matrix1 = matrix1.getRow(getInput(matrix1.drawMatrix() + 
							      "\nEnter the row to extract : ",  
							      1, Integer.MAX_VALUE)).copy();
			    break;
		    case 14 :
			    matrix1 = matrix1.getColumn(getInput(matrix1.drawMatrix() + 
							      "\nEnter the column to extract : ",  
							      1, Integer.MAX_VALUE)).copy();
			    break;
		    case 15 :
			    displayMessage("" + matrix1.getSum());
			    break;
		    case 16 :
			    list = chooseMatrix(list, num);
			    num = java.lang.reflect.Array.getLength(list);
			    matrix2 = list[num - 1].copy();
			    
			    matrix1.setToDirectProduct(matrix2);
			    break;
		    case 17 :
			    matrix1 = matrix1.getIdentityMatrix(getInput("Enter the order of the identity matrix : ", 
									 1, Integer.MAX_VALUE)).copy();
			    break;
		    case 18 :
			    matrix1 = createMatrix(num);
			    break;
		    case 19 :
			    consultMemory(list);
			    break;
		    default :
			    ;
			    break;
					    
					    
		}
		
		if(mainChoice < numOfChoices)
		{
		    if(getInput("Do you wish to save the new matrix?\n" + 
				"   1. Yes\n    2. No\n\n" + matrix1.drawMatrix(), 1, 2) == 1)
		    {
			list = saveMatrix(list, matrix1);
			num = java.lang.reflect.Array.getLength(list);
		    }
		}
		if(getInput("Do you want to do something else?\n    1. Yes\n    2. No\n", 1, 2) == 2)
		    goOn = false;
		    
	    }while(goOn);
	    
	    System.exit(0);
	    
	}
	
	public static int getInput(String message, int min, int max)
	{
	    int input = min - 1;
	    
	    while(input < min || input > max)
	    {
		String in = JOptionPane.showInputDialog(message);
		
		input = Integer.parseInt(in);
	    }
	    
	    return input;
	}
	
	public static double getInputDouble(String message, double min, double max)
	{
	    double input = 0;
	    
	    do
	    {
		String in = JOptionPane.showInputDialog(message);
		
		input = Integer.parseInt(in);
		
	    } while(input < min || input > max);
	    
	    return input;
	}
	
	public static Matrix createMatrix(int num)
	{
	    int m = getInput("Matrix " + num + " - Enter the number of rows : ", 0, Integer.MAX_VALUE);
	    int n = getInput("Matrix " + num + " - ENter the number of columns : ", 0 , Integer.MAX_VALUE);
			
	    Matrix newMatrix = new Matrix(m, n);
	    
		for(int i = 1; i <= m; i ++)
		    for(int j = 1; j <= n; j ++)
			newMatrix.setCoefficient(i, j, 
			    getInputDouble("Matrix " + num + " - Input the coefficient ( " + i + " , " + j + " ) : ",
				Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
			
	    
			JOptionPane.showMessageDialog(null, 
				    "New matrix :\n\n" + newMatrix.drawMatrix(), 
				    "New Matrix", 
				    JOptionPane.INFORMATION_MESSAGE);      
			
	    return newMatrix;            
	}
	
	public static Matrix[] saveMatrix(Matrix[] list, Matrix matrix)
	{
	    int num = java.lang.reflect.Array.getLength(list);
	
		    boolean isAlreadyThere = false;
		    
		    for(int i = 0; i < num; i ++)
			if(list[i].equals(matrix))
			{
			    for(int j = i; j < num-1; j ++)
				list[j] = list[j+1];
			    isAlreadyThere = true;
			}
			
			if(!isAlreadyThere)
			{
				if(num == 0)
				{
				    list = new Matrix[num+1];
				    list[num] = matrix;
				}
				else
				{   
				    Matrix[] newList = new Matrix[num+1];
				    System.arraycopy(list, 0, newList, 0, num);
				    newList[num] = matrix;
				    list = newList;                                    
				}
			}
			else
			    list[num - 1] = matrix;
			    
				return list;
	}
	
	public static void consultMemory(Matrix[] list)
	{
	    int num = java.lang.reflect.Array.getLength(list);
	    
	    String message = "";
	    
	    for(int i = 0; i < num; i += 4)
	    {
		message = "";
		
		for(int j = i; j - i < 4 && j < num ; j ++)
		    message += "Matrix #" + j + " : \n" +list[j].drawMatrix() + "\n";
		    
		displayMessage(message);
	    }  
	}
	
	public static void displayMessage(String message)
	{
		JOptionPane.showMessageDialog(null, 
			    message, 
			    "MATRIX PROGRAM", 
			    JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static Matrix[] chooseMatrix(Matrix[] list, int num)
	{
	    
		int matrixChoice = 0;
		Matrix matrix = null;
		
		while(matrixChoice < 1 || matrixChoice > 3)
		{                              
		    matrixChoice = getInput("Choose a matrix to use :\n" +
					    "   1. Enter a new matrix\n" +
					    "   2. Choose a previously entered matrix\n" +
					    "   3. Consult memory\n\n", 1, 3);
		 
		    if(num == 0 && matrixChoice != 1)
		    {
			matrixChoice = 1;
			displayMessage("No matrices in memory !\n\nYou will have to input a matrix.");
		    }
		    
		    
		
			switch(matrixChoice)
			{
			    case 1:
				matrix = createMatrix(num);
				list = saveMatrix(list, matrix);
				break;
			    case 2:
				int id = getInput("Enter the matrix id (-1 to cancel): ", -1, num-1);
				if(id != -1)
				{
				    matrix = list[id].copy();
	    
				    JOptionPane.showMessageDialog(null, 
						"Matrix #" + id + " :\n\n" + matrix.drawMatrix(), 
						"Recovered Matrix", 
						JOptionPane.INFORMATION_MESSAGE);  
											       
				    list = saveMatrix(list, matrix);
				}
				else
				    matrixChoice = -1;
				break;
			    case 3:
				consultMemory(list);
				matrixChoice = -1;
				break;
			    default :
				break;
			    
			}
		}
		    
	
	    return list;
	}
	
		
}
