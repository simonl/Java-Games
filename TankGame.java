package boubouworld;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class TankGame
{
	public static void main(String[] args)
	{
	    double globalTime = 0.0000;
	    final double DELAY = 0.05;
	    int loops = (int)(10/DELAY), counter = 0;
	    
	    Tank tank1 = null, tank2 = null;      
	    
	    //Show map
	    WindowTank window = new WindowTank("Tank Game!");
		window.paintImage(tank1, tank2);
		
	    //Initialize Players
	    tank1 = createTank(1);
		window.paintImage(tank1, tank2);
	    tank2 = createTank(2);
		window.paintImage(tank1, tank2);
	    
				    
	    //Start the game loop, goes on while the players live
	    while(tank1.getHealth() > 0 && tank2.getHealth() > 0)
	    {
		    tank1.coordinates.setTime(globalTime);
		    
		    tank1.updateTank();
		    tank2.updateTank();
		    
		//Shoot 1 bullet for one player, from 2 available
		//When waiting to shoot again, the projectiles fly      
		if(counter % loops == 0)
		{
		    tank1.play();
		    tank2.play();
		}
		    
		//Check for impacts
		
		checkForImpacts(tank1, tank2, window);
		    
		
		globalTime += DELAY;
		counter++;
		
	}
	    
	    //Game is end
	    window.paintImage(tank1, tank2);
	    announceWinner(tank1, tank2);
	    
	    System.exit (0);
	}
	
	
	public static Tank createTank(int playerNum)
	{
	    String playerName =
		JOptionPane.showInputDialog ("Enter the name of Player " + playerNum + " : ");
	    return (new Tank(playerName, playerNum));
	}
	
		
	public static void checkForImpacts(Tank tank1, Tank tank2, WindowTank screen)
	{   
	    checkTankForWall(tank1);
	    checkTankForWall(tank2);
	    checkForCrash(tank1, tank2);
	    
	    checkBallForWall(tank1.ball1);
	    checkBallForWall(tank1.ball2);
	    checkBallForWall(tank2.ball1);
	    checkBallForWall(tank2.ball2);
	    
		checkBallForBall(tank1.ball1, tank2.ball1);
	    
		checkBallForBall(tank1.ball1, tank1.ball2);
		checkBallForBall(tank1.ball1, tank2.ball2);
		
		checkBallForBall(tank1.ball2, tank2.ball1);
		checkBallForBall(tank1.ball2, tank2.ball2);
		
		checkBallForBall(tank2.ball1, tank2.ball2);
	    
		screen.paintImage(tank1, tank2);
	    
	    tank1.ball1 = checkForImpact(tank1, tank2, tank1.ball1);
	    tank1.ball2 = checkForImpact(tank1, tank2, tank1.ball2);
	    tank2.ball1 = checkForImpact(tank1, tank2, tank2.ball1);
	    tank2.ball2 = checkForImpact(tank1, tank2, tank2.ball2);
	    
	}   

	public static Cannonball checkForImpact(Tank tank1, Tank tank2, Cannonball ball)
	{
	    if(ball != null && ball.checkIfLanded())
	    {                
		tank1.getHit(ball.finalPosition);
		tank2.getHit(ball.finalPosition);
		
		ball = null;
	    }
	    
	    return ball;
	}
	
	public static void checkBallForBall(Cannonball ball1, Cannonball ball2)
	{
	    if(!(ball1 == null || ball2 == null))
	    {
		if(ball1.coordinates.getProximity(ball2.coordinates) <= ball1.ballDimensions.getHeight()/2.0)
		{
		    ball1.finalPosition.setPositionX(ball1.coordinates.getPositionX());
		    ball1.finalPosition.setPositionY(ball1.coordinates.getPositionY());
		    
		    ball2.finalPosition.setPositionX(ball2.coordinates.getPositionX());
		    ball2.finalPosition.setPositionY(ball2.coordinates.getPositionY());
		}
	    }
	}
	
	public static void checkTankForWall(Tank tank)
	{            
	    if(tank.coordinates.getPositionX() <= tank.tankDimensions.getWidth()) 
	    {
		tank.crash(tank.coordinates.getSpeedX(), 20.0);
	    }
	    else if(tank.coordinates.getPositionX()+tank.tankDimensions.getWidth() >= 992.0) 
	    {
		tank.crash(tank.coordinates.getSpeedX(), 972.0);
	    }
	}
	
	public static void checkBallForWall(Cannonball ball)
	{
	    if(ball != null && ball.coordinates.getPositionX() <= 15.0)
	    {
		ball.finalPosition.setPositionX(15.0);
		ball.finalPosition.setPositionY(ball.coordinates.getPositionY());
	    }
	    else if(ball != null && ball.coordinates.getPositionX() >= 977.0)
	    {
		ball.finalPosition.setPositionX(977.0);
		ball.finalPosition.setPositionY(ball.coordinates.getPositionY());
	    }
	}
	
	public static double keepTheSign(double number, char operator)
	{
	    double returned = 0.0;
	    
	    if(number != 0.0)
	    {
		switch(operator)
		{
		    case '*' :
			returned = Math.abs(number)*number;
			break;
		    case '/' :
			returned = number/Math.abs(number);
			break;
		}
	    }
		
	    return returned;
	}
	
	public static void checkForCrash(Tank tank1, Tank tank2)
	{          
	    if(Math.abs(tank1.coordinates.getPositionX() - tank2.coordinates.getPositionX()) <= tank1.tankDimensions.getWidth())
	    {
		double speed = (tank1.coordinates.getSpeedX() - tank2.coordinates.getSpeedX())/2.0;
		double middle = (tank1.coordinates.getPositionX() + tank2.coordinates.getPositionX())/2.0;
		
		
		if(tank1.coordinates.getPositionX() <= tank2.coordinates.getPositionX())
		{
		    tank1.crash(speed, middle - 10.0);
		    tank2.crash(speed, middle + 10.0);
		}
		else
		{
		    tank1.crash(speed, middle + 10.0);
		    tank2.crash(speed, middle - 10.0);
		}
	       
	    }
		
		
	}
	
	public static void announceWinner(Tank tank1, Tank tank2)
	{      
		String winner, loser;
		if(tank1.getHealth() >= tank2.getHealth())
		{
		    winner = tank1.getName();
		    loser = tank2.getName();
		}
		else
		{
		    winner = tank2.getName();
		    loser = tank1.getName();
		}
		
		JOptionPane.showMessageDialog (null,
		"********** THE GREAT TANK GAME OUTCOMES **********\n\n" +
		"WINNER :     " + winner + "\n\n" +
		"EPIC FAILER :" + loser + "\n\n" +
		"Written by : Simon Langlois\n" ,
		"Winner",
		JOptionPane.INFORMATION_MESSAGE);
	    //     
	    //     
	    //     System.out.print("\n\n\n");
	    // writeCharacters('$', 100);
	    //     System.out.println();
	    // writeCharacters('$', 1);
	    // writeCharacters(' ', 98);
	    // writeCharacters('$', 1);
	    //     System.out.println();
	    // writeCharacters('$', 1);
	    // writeCharacters(' ', 39);
	    //     System.out.print("AND THE WINNER IS...");
	    // writeCharacters(' ', 39);
	    // writeCharacters('$', 1);
	    //     System.out.println();
	    // writeCharacters('$', 1);
	    // writeCharacters(' ', 98);
	    // writeCharacters('$', 1);
	    //     System.out.println();
	    // writeCharacters('$', 1);
	    // writeCharacters(' ', 39);
	    // 
	    // if(tank1.getTankLife() > tank2.getTankLife())
	    //     System.out.print("Player " + tank1.getTankNumber() + " : " + tank1.getTankNumber());
	    // else if(tank2.getTankLife() > tank1.getTankLife())
	    //     System.out.print("Player " + tank2.getTankNumber() + " : " + tank2.getTankNumber());
	    //     
	    // writeCharacters(' ', 39);
	    // writeCharacters('$', 1);
	    //     System.out.println();
	    // writeCharacters('$', 1);
	    // writeCharacters(' ', 98);
	    // writeCharacters('$', 1);
	    //     System.out.println();
	    // writeCharacters('$', 100);
	}
	
	// 
	// 
	// public static void drawCannonball(Cannonball bullet)
	// {
	//     int about, number;
	//                 
	//         about = (int)(bullet.currentPosition.getD() / 10.0);
	//             number = bullet.getTankNumber();
	//     
	//         //Position
	//         writeCharacters(' ', about - 2);
	//             System.out.print("<<" + number + ">>\n");
	// }
	// 
	// 
	// public static void drawCannonballs(Cannonball bullet1, Cannonball bullet2)
	// {
	//     int about1, about2, number1 = 0, number2 = 0;
	//     
	//     double bullet1Position = bullet1.currentPosition.getD();
	//     double bullet2Position = bullet2.currentPosition.getD();
	//     
	//     if(bullet1Position < bullet2Position)
	//     {
	//         about1 = (int)(bullet1Position / 10.0);
	//             number1 = bullet1.getTankNumber();
	//         about2 = (int)(bullet2Position / 10.0);
	//             number2 = bullet2.getTankNumber();
	//     }
	//     else
	//     {
	//         about1 = (int)(bullet2Position / 10.0);
	//             number1 = bullet2.getTankNumber();
	//         about2 = (int)(bullet1Position / 10.0);
	//             number2 = bullet1.getTankNumber();
	//     }
	//     
	//         //Position
	//         writeCharacters(' ', about1 - 2);
	//             System.out.print("<<" + number1 + ">>");
	//         writeCharacters(' ', (about2 - about1 - 5));
	//             System.out.print("<<" + number2 + ">>\n");
	// }
	// // 
	// 
	// public static void drawPlayers(Tank tank1, Tank tank2)
	// {
	//     double about1, about2, life1, life2, number1 = 0, number2 = 0;
	//     
	//     if(tank1.tankPosition.getD() < tank2.tankPosition.getD())
	//     {
	//         about1 = tank1.tankPosition.getD() / 10;
	//             life1 = tank1.getTankLife();
	//             number1 = tank1.getTankNumber();
	//         about2 = tank2.tankPosition.getD() / 10;
	//             life2 = tank2.getTankLife();
	//             number2 = tank2.getTankNumber();
	//     }
	//     else
	//     {
	//         about1 = tank2.tankPosition.getD() / 10;
	//             life1 = tank2.getTankLife();
	//             number1 = tank2.getTankNumber();
	//         about2 = tank1.tankPosition.getD() / 10;
	//             life2 = tank1.getTankLife();
	//             number2 = tank1.getTankNumber();
	//     }
	//     
	//         //Position
	//             System.out.print("\n\n");
	//         writeCharacters('_', about1);
	//             System.out.print(number1);
	//         writeCharacters('_', (about2 - about1 - 1));
	//             System.out.print(number2);
	//         writeCharacters('_', (100 - about2 - 1));
	//             System.out.println();
	//         
	//         //Life
	//         writeCharacters(' ', about1);
	//             System.out.print(life1);
	//         writeCharacters(' ', (about2 - about1 - 3));
	//             System.out.print(life2 + "\n\n");
	//         writeCharacters('*', 100);
	//             System.out.print("\n\n\n");
	//     
	// 
	// }
	// 

	// public static void writeCharacters(char character, double numberOfTimes)
	// {
	//     for(int i = 1; i <= (int)numberOfTimes; i++)
	//     {
	//         System.out.print(character);
	//     }
	// }


	
	// 
	// public static void drawProjectiles(Tank tank)
	// {
	//             //if statement to draw any assortment of projectiles
	//             if(tank.ball1 != null)
	//                 if(tank.ball2 != null)
	//                     drawCannonballs(tank.ball1, tank.ball2);
	//                 else
	//                     drawCannonball(tank.ball1);     
	//             else if(tank.ball2 != null)
	//                 drawCannonball(tank.ball2);
	//                 
	// }
	// 
}

