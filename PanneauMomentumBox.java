package javaapps.test;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javaapps.physicsobject.Vector;

public class PanneauMomentumBox extends JPanel
{
	public double energy, kineticEnergy, potentialEnergy, momentum;
	public double lowestE, highestE, diffE, lowestPE, highestPE, diffPE, lowestKE, highestKE, diffKE, lowestP, highestP, diffP, time;
	public int num, limitD = 0, zFocus = 50, realU, realD, limitU, vision = 700;
	
	public Vector ball = null;
	    public double ballR = 50.0;
	    
	public Vector box = null;
	    public double boxX = 350.0;
	    public double boxY = 350.0;
	    public double boxZ = 350.0;
	
	public void paintComponent(Graphics g){
	
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		double scaling = 0.5;
		double zero = 175;
		
		//BOX OUTLINES
		double boxPosX = zero + box.getX() * scaling;
		double boxPosY = zero + box.getY() * scaling;
		
		g2d.setColor(Color.BLUE);
		g2d.drawRect((int)(boxPosX - boxX * scaling), (int)(boxPosY - boxY * scaling), (int)boxX, (int)boxY);
		g2d.drawOval((int)(boxPosX - boxX * scaling), (int)(boxPosY - boxY * scaling), (int)boxX, (int)boxY);
	     
		
		    
		//FOR EVERY BALL 
		double ballPosX = zero + ball.getX() * scaling;
		double ballPosY = zero + ball.getY() * scaling; 
		      
		g2d.fillOval((int)(ballPosX - ballR * scaling), (int)(ballPosY - ballR * scaling), (int)ballR, (int)ballR);
		
		    
		
		
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
