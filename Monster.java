package boubouworld;
import javax.swing.*;

public abstract class Monster
{
    private int monsterId;
    private String monsterName;
    private double[] monsterLife = new double[6];
    private double[] monsterDefence = new double[6];
    private boolean[][] monsterAbilities = new boolean[6][5];
    
    public Monster()
    {
    
    }
    
    public Monster(int id)
    {
    
    }
}
