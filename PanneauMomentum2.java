package javaapps.test;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javaapps.physicsobject.Vector;

public class PanneauMomentum2 extends JPanel
{
	public double ballR = 50.0;
	public double energy, kineticEnergy, potentialEnergy, momentum;
	public double lowestE, highestE, diffE, lowestPE, highestPE, diffPE, lowestKE, highestKE, diffKE, lowestP, highestP, diffP, time;
	public int num, limitD = 0, zFocus = 50, realU, realD, limitU, vision = 700;
	public Vector[] balls = null;
	
	public void paintComponent(Graphics g){
	
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		
			
		int side = (realU-realD);
		int middle = side / 2;
		
		//BOX OUTLINES
		g2d.setColor(Color.BLUE); 
		
		double wallX = realD - middle;
		double wallY = realD - middle;
		
		for(int i = 0; i <= side; i = i + 25)
		{
		    double wallZ = i;

		    int angleTheta = (int)(Math.atan(wallX / wallZ) * vision/Math.PI);
		    int anglePhi = (int)(Math.atan(wallY / wallZ) * vision/Math.PI);
					
		    g2d.drawRect(middle + angleTheta, middle + anglePhi, -2 * angleTheta, -2 * anglePhi);

		}
		
		    //Back Wall
		    double backZ = side;
		    
		    int backTheta = (int)(Math.atan(wallX / backZ) * vision/Math.PI);
		    int backPhi = (int)(Math.atan(wallY / backZ) * vision/Math.PI);
		    
		    g2d.fillRect(middle + backTheta, middle + backPhi, -2 * backTheta, -2 * backPhi);
		
		    g2d.drawLine(0, 0, vision, vision);
		    g2d.drawLine(0, vision, vision, 0);
		    
		    
		//FOR EVERY BALL 
		g2d.setColor(Color.DARK_GRAY);
		for(int i = 0; i < num; i ++)
		{
		    //SHADOW
		    double posX = (int)balls[i].getX() - middle;
		    double posY = realU - middle;
		    double posZ = (int)balls[i].getZ();
		    
		    int angleTheta = (int)(Math.atan(posX / posZ) * vision/Math.PI);
		    int anglePhi = (int)(Math.atan(posY / posZ) * vision/Math.PI);
		    
		    double distance = Math.sqrt(posX*posX + posY*posY + posZ*posZ);
		    int size = (int)(Math.atan(ballR/distance) * vision/Math.PI);
		    
		    g2d.fillOval(middle + angleTheta - size, middle + anglePhi - size, size*2, size*2);
		    
		    
		    //BACK SHADOW
		    // posY = (int)balls[i].getY() - middle;
		    // posZ = 700;
		    // 
		    // angleTheta = (int)(Math.atan(posX / posZ) * vision/Math.PI);
		    // anglePhi = (int)(Math.atan(posY / posZ) * vision/Math.PI);
		    // 
		    // distance = Math.sqrt(posX*posX + posY*posY + posZ*posZ);
		    // size = (int)(Math.atan(ballR/distance) * vision/Math.PI);
		    // 
		    // g2d.fillOval(middle + angleTheta - size, middle + anglePhi - size, size*2, size*2);
		}
		
		g2d.setColor(Color.RED);
		for(int i = 0; i < num; i ++)
		{
		    double posX = (int)balls[i].getX() - middle;
		    double posY = (int)balls[i].getY() - middle;
		    double posZ = (int)balls[i].getZ();
		    
		    int angleTheta = (int)(Math.atan(posX / posZ) * vision/Math.PI);
		    int anglePhi = (int)(Math.atan(posY / posZ) * vision/Math.PI);
		    
		    double distance = Math.sqrt(posX*posX + posY*posY + posZ*posZ);
		    int size = (int)(Math.atan(ballR/distance) * vision/Math.PI);
		      
		    g2d.fillOval(middle + angleTheta - size, middle + anglePhi - size, size*2, size*2);
		}
		    
		
		
		energy = potentialEnergy + kineticEnergy;
		
		if(kineticEnergy != 0)
		{
		    
		    if(energy < lowestE)
			lowestE = energy;
		    if(energy > highestE)
			highestE = energy;
					    
		    if(potentialEnergy < lowestPE)
			lowestPE = potentialEnergy;
		    if(potentialEnergy > highestPE)
			highestPE = potentialEnergy;
			
		    if(kineticEnergy < lowestKE)
			lowestKE = kineticEnergy;
		    if(kineticEnergy > highestKE)
			highestKE = kineticEnergy;
			
		    if(momentum < lowestP)
			lowestP = momentum;
		    if(momentum > highestP)
			highestP = momentum;
			
			diffE = highestE - lowestE;
			diffPE = highestPE - lowestPE;
			diffKE = highestKE - lowestKE;
			diffP = highestP - lowestP;
		}   
			 
		g2d.setColor(Color.BLACK);              
			g2d.drawString("Energy :    " + energy, 10, 20);
			g2d.drawString("    Ranging from :    " + lowestE + "    to    " + highestE + "    ( " + diffE + "    OR    " + Math.round(diffE/highestE*10000.0)/100.0 + "% )", 10, 35);
			
			g2d.drawString("Kinetic Energy :    " + kineticEnergy, 10, 55);
			g2d.drawString("    Ranging from :    " + lowestKE + "    to    " + highestKE + "    ( " + diffKE + "    OR    " + Math.round(diffKE/highestKE*10000.0)/100.0 + "% )", 10, 70);
		     
			g2d.drawString("Potential Energy :    " + potentialEnergy, 10, 90);
			g2d.drawString("    Ranging from :    " + lowestPE + "    to    " + highestPE + "    ( " + diffPE + "    OR    " + Math.round(diffPE/highestPE*10000.0)/100.0 + "% )", 10, 105);
			
			g2d.drawString("Momentum magnitude :    " + momentum, 10, 125);
			g2d.drawString("    Ranging from :    " + lowestP + "    to    " + highestP + "    ( " + diffP + "    OR    " + Math.round(diffP/highestP*10000.0)/100.0 + "% )", 10, 140);
			
			g2d.drawString("Time Elapsed :    " + time, 10, 155);
	}
 
	
} // Panneau class
