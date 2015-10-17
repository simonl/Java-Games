package dawson111.labexercises;

import javax.swing.*;

/** Card Object.
 *  Has a card suit, from 0 to 3.
 *  Has a face value from 1 to 13.
 *  Can display the card name from the face value and suit.
 *
 *  @authors Simon Langlois, Pierre Miguel
 *           and Vincenzo Patrinostro - A933125 - 420-111-DW section 1
 *  @version    12/04/2009
 */


public class Card
{
    //Instance variables
    private int cardSuit;
    private int cardFaceValue;

    //Default constructor
    public Card ()
    {}
    
    //Two parameter constructor
    public Card (int cardFaceValue, int cardSuit)
    {
	this.cardSuit = cardSuit;
	this.cardFaceValue = cardFaceValue;
    }
    
    //Return the card's face value
    public int getFaceValue()
    {
	return cardFaceValue;
    }
    
    //Return the card's suit
    public int getSuit()
    {
	return cardSuit;
    }
    
    //Get the card name by converting the face value and the suit to their string equivalent
    public String getCardName()
    {
    
	String faceValueName, suitName, cardName;
	
	//Get the face value name
	switch (cardFaceValue)
	{
	    case 1:
		faceValueName = "Ace";
		break;
	    case 2:
		faceValueName = "Two";
		break;
	    case 3:
		faceValueName = "Three";
		break;
	    case 4:
		faceValueName = "Four";
		break;
	    case 5:
		faceValueName = "Five";
		break;
	    case 6:
		faceValueName = "Six";
		break;
	    case 7:
		faceValueName = "Seven";
		break;
	    case 8:
		faceValueName = "Eight";
		break;
	    case 9:
		faceValueName = "Nine";
		break;
	    case 10:
		faceValueName = "Ten";
		break;
	    case 11:
		faceValueName = "Jack";
		break;
	    case 12:
		faceValueName = "Queen";
		break;
	    case 13:
		faceValueName = "King";
		break;
	    default:
		faceValueName = "Error";
		break;
	}    
	
	//Get the suit name
	if (cardSuit <= 2)
	    if (cardSuit <= 1)
		if (cardSuit == 0)
		    suitName = "Hearts";
		else
		    suitName = "Diamonds";
	    else
		suitName = "Clubs";
	else
	    suitName = "Spades";
	
	//Put the two together in a coherent way
	cardName = "The " + faceValueName + " of " + suitName;
	
	//Return the card name
	return cardName;
    }
    
} //end Card class
