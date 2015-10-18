package javaapps.test;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javaapps.physicsobject.Vector;

public class PanneauMomentum2 extends JPanel
{
	public double ballR = 50.0;
	public double kineticEnergy, momentum;
	public double lowestE, highestE, diffE, lowestP, highestP, diffP, time;
	public int num, limitD = 0, zFocus = 50, realU, realD, limitU;
	public Vector[] balls = null;
	
	public void paintComponent(Graphics g){
	
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
			limitU = (realU-realD) * zFocus / 50;
		
		g2d.setColor(Color.BLUE); 
		// for(int i = 50; i <= 700; i = i + 25)
		// {
		//     //double mod = 1.0 * zFocus * zFocus / i / i;
		//     double mod = 1.0 * zFocus / i;
		//     
		//     int box = (int)((realU - realD) * mod);
		//     g2d.drawRect((limitU - box) / 2, (limitU - box) / 2, box , box);
		//     
		//     if(i == 700)
		//     {
		//         g2d.drawLine(0, 0, (limitU - box) / 2, (limitU - box) / 2);
		//         g2d.drawLine(0, limitU, (limitU - box) / 2, (limitU - box) / 2 + box);
		//         g2d.drawLine(limitU , 0, (limitU - box) / 2 + box, (limitU - box) / 2);
		//         g2d.drawLine(limitU, limitU, (limitU - box) / 2 + box, (limitU - box) / 2 + box);
		//     }
		// }
		
		    int middle = (realU - realD) / 2;
		for(int i = 0; i < num; i++)
		{
		    int posX = (int)balls[i].getX() - middle;
		    int posY = (int)balls[i].getY() - middle;
		    int posZ = (int)balls[i].getZ();
		    
		    int angleTheta = (int)(Math.atan(posX / posZ)*700.0/Math.PI);
		    int anglePhi = (int)(Math.atan(posY / posZ)*700.0/Math.PI);
		    
		    double distance = Math.sqrt(posX*posX + posY*posY + posZ*posZ);
		    int size = (int)(Math.atan(50/distance)*700.0/Math.PI);
		    System.out.println(" Hey " + i + "  ---   " + angleTheta + "     " + anglePhi + "    " + size);
		    g2d.fillOval(middle + angleTheta - size, middle + anglePhi - size, size*2, size*2);
		
		}
		    
		    g2d.setColor(Color.DARK_GRAY);
		// for(int i = 0; i < num; i ++)
		// {
		//     //double mod = 1.0 * zFocus * zFocus / balls[i].getZ() / balls[i].getZ();
		//     double mod = 1.0 * zFocus / balls[i].getZ();
		//     
		//     double ballReal = ballR * mod;
		//     
		//     int box = (int)((realU - realD) * mod);
		//     
		//     int bound = (int)((limitU - box) / 2);
		//     
		//     g2d.fillOval((int)(balls[i].getX() * mod + bound - ballReal), (int)(bound + box - ballReal), (int)(ballReal * 2.0), (int)(ballReal * 2.0));
		// }
		    
		    g2d.setColor(Color.BLUE);
		// for(int i = 0; i < num; i ++)
		// {
		//     //double mod = 1.0 * zFocus * zFocus / balls[i].getZ() / balls[i].getZ();
		//     double mod = 1.0 * zFocus / balls[i].getZ();
		//     
		//     double ballReal = ballR * mod;
		//     
		//     int box = (int)((realU - realD) * mod);
		//     
		//     int bound = (int)((limitU - box) / 2);
		//     
		//         g2d.fillOval((int)(balls[i].getX() * mod + bound - ballReal), (int)(balls[i].getY() * mod + bound - ballReal), (int)(ballReal * 2.0), (int)(ballReal * 2.0));
		//         
		// }
		
		
		    if(kineticEnergy < lowestE)
			lowestE = kineticEnergy;
		    if(kineticEnergy > highestE)
			highestE = kineticEnergy;
			
		    if(momentum < lowestP)
			lowestP = momentum;
		    if(momentum > highestP)
			highestP = momentum;
			
			diffE = highestE - lowestE;
			diffP = highestP - lowestP;
			
			 
		g2d.setColor(Color.RED);              
			g2d.drawString("Kinetic Energy :    " + kineticEnergy, 10, 20);
			g2d.drawString("    Ranging from :    " + lowestE + "    to    " + highestE + "    ( " + diffE + "    OR    " + Math.round(diffE/kineticEnergy*10000.0)/100.0 + "% )", 10, 35);
			
			g2d.drawString("Momentum magnitude :    " + momentum, 10, 50);
			g2d.drawString("    Ranging from :    " + lowestP + "    to    " + highestP + "    ( " + diffP + "    OR    " + Math.round(diffP/momentum*10000.0)/100.0 + "% )", 10, 65);
			
			g2d.drawString("Time Elapsed :    " + time, 10, 80);
	}
 
	
} // Panneau class
