package boubouworld;

import javax.swing.*;

/** Determines the value and suite of a card corresponding to
 *  the number, from 0 to 51, inputted by the user.
 *  Value from 1 to 13.
 *  Suite from hearts, represented by 0, diamonds, clubs
 *  and to spades, being 3.
 *
 *  @author Simon Langlois - A933125 - 420-1111-DW section 1
 *  @version    09/11/2009
 */


public class LotteryApp
{
    public static void main (String [] args)
    {
	//variable declarations
	int lottery1 = getRandomNumber();
	int lottery2 = getRandomNumber();
	int lottery3 = getRandomNumber();
	
	int input1 = readInt();
	int input2 = readInt();
	int input3 = readInt();
    System.out.println("Allo");
	
	int prizeMoney = determinePrize(lottery1, lottery2, lottery3, input1, input2, input3);
	
	
	//Display the output
	JOptionPane.showMessageDialog(null,
	    "************** Lottery Exercise ****************\n\n" +
	    "Money Won :       " + prizeMoney + "\n\n" +
	    "Written by: Simon Langlois\n\n",
	    "Lottery",
	JOptionPane.INFORMATION_MESSAGE);
	System.exit(0);

    }//end main()
    
    public static int readInt()
    {
	String inputString;
	int inputNumber = 0;
	
	while(inputNumber <= 0 || inputNumber >= 26);
	{
	    inputString = JOptionPane.showInputDialog("Enter any integer between 1 and 25, inclusively:");
	    inputNumber = Integer.parseInt(inputString);
	}
	
	return inputNumber;
    }
    
    public static int determinePrize(int lot1, int lot2, int lot3, int bet1, int bet2, int bet3)
    {
	int prizeMoney = 0;
	
	int[] lot = {lot1, lot2, lot3};
	int[] bet = {bet1, bet2, bet3};
	
	//Contains the matched number, lot position and input position
	int[][] match = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
	
	for(int i = 0; i <= 2; i++)
	{
	    for(int j = 0; j <= 2; j++)
	    {
		if(bet[i] == lot[j] && match[i][0] == 0)
		{
		    if(i == 1 && match[0][2] == j)
			break;
		    if(i == 2 && (match[1][2] == j || match[0][2] == j))
			break;
			
		    match[i][0] = bet[i];
		    match[i][1] = i;
		    match[i][2] = j;
		    
		    break;
		}
	    }
	}
	
	if(match[0][0] != 0 || match[1][0] != 0 || match[2][0] != 0)
	{
	    prizeMoney = 1;
	    
	    if((match[0][0] != 0 && match[1][0] != 0) || (match[1][0] != 0 || match[2][0] != 0) || (match[0][0] != 0 || match[2][0] != 0))
	    {
		prizeMoney =
	    }
	}
	
	
	return prizeMoney;
    }
    
    public static int getRandomNumber()
    {
	int randomNum = (int)(Math.random() * 25.0 + 1.0);
	
	return randomNum;
    }
}//end Purse class
