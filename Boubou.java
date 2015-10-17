package	boubouworld

public class Boubou
{
	private String characterName;
	private int specialNumber;
	private int height;
	private int width;

	public Boubou()
	{
        height = 20;
        width = 10;
    }

	public Boubou(String characterName, int specialnumber)
	{

        height = 20;
        width = 10;
        this.characterName = characterName;
        this.specialNumber = specialnumber;
	}

	public int getStuff()
	{
        System.out.println("The height is : " + height);
        System.out.println("The width is : " + width);
        System.out.println("The charachet name is : " + characterName);
        System.out.println("The special number is : " + specialNumber);
	}

}

