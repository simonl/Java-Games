package javaapps.test;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javaapps.physicsobject.Vector;

public class FrameOfReference extends JPanel
{
	double c = 3.0E8;
	int numberOfObjects();
	PhysicsObject[] objects = null;

	Vector origin = null;
	Vector relativeVelocity = null;
		Vector currentObjectPosition = new Vector();
		Vector currentObjectVelocity = new Vector();

	public FrameOfReference()
	{

	}

	public FrameOfReference(int numberOfObjects)
	{
		setNumberOfObjects(numberOfObjects);
	}


	public void paintComponent(Graphics g)
	{

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

		g2d.setColor(Color.RED);
		for(int i = 0; i < numberOfObjects; i ++)
		{
			int theta = (int)objects[i].motion.position.getTheta();
			int phi = (int)(-objects[i].motion.position.getPhi() + Math.PI / 2.0);
			int angularRadius = (int)Math.atan(objects[i].dimensions.getR() /
										objects[i].motion.position.getMagnitude());

			g2d.fillOval(phi - angularRadius, theta - angularRadius, angularRadius * 2, angularRadius * 2);
		}

	}


		public void changeFrame(PhysicsObject[] objects, int referenceIndex)
		{
			//NON RELATIVITIC =(
			for(int i = 0; i < numberOfObjects; i ++)
			{
				if(i != referenceIndex)
				{
					//Position
					objects[i].motion.position.setToVectorSubstraction(objects[referenceIndex].motion.position);

					//Velocity
					objects[i].motion.velocity.setToVector(
						getVelocitySubstraction(objects[i].motion.velocity, objects[referenceIndex].motion.velocity));
				}
			}

			objects[referenceIndex].motion.position.setToScalarMultiplication(0);
			objects[referenceIndex].motion.velocity.setToScalarMultiplication(0);
		}

		public double getVelocityAddition(Vector v1, Vector v2)
		{
			Vector v3 = new Vector();

			v3.setX((v1.getX() + v2.getX()) / (1 + (v1.getX() * v2.getX() / c / c)));
			v3.setY((v1.getY() + v2.getY()) / (1 + (v1.getY() * v2.getY() / c / c)));
			v3.setZ((v1.getZ() + v2.getZ()) / (1 + (v1.getZ() * v2.getZ() / c / c)));

			v3.completeCartesianVector();

			return v3;
		}

		public double getVelocitySubstraction(Vector v3, Vector v1)
		{
			Vector v2 = new Vector();

			v2.setX((v3.getX() - v1.getX()) / (1 - (v1.getX() * v3.getX() / c / c)));
			v2.setY((v3.getY() - v1.getY()) / (1 - (v1.getY() * v3.getY() / c / c)));
			v2.setZ((v3.getZ() - v1.getZ()) / (1 - (v1.getZ() * v3.getZ() / c / c)));

			v2.completeCartesianVector();

			return v2;
		}

	/*
		g2d.setColor(Color.BLUE);
		for(int i = 50; i <= 700; i = i + 25)
		{
		    //double mod = 1.0 * zFocus * zFocus / i / i;
		    double mod = 1.0 * zFocus / i;

		    int box = (int)((realU - realD) * mod);
		    g2d.drawRect((limitU - box) / 2, (limitU - box) / 2, box , box);

		    if(i == 700)
		    {
			g2d.drawLine(0, 0, (limitU - box) / 2, (limitU - box) / 2);
			g2d.drawLine(0, limitU, (limitU - box) / 2, (limitU - box) / 2 + box);
			g2d.drawLine(limitU , 0, (limitU - box) / 2 + box, (limitU - box) / 2);
			g2d.drawLine(limitU, limitU, (limitU - box) / 2 + box, (limitU - box) / 2 + box);
		    }
		}


		    g2d.setColor(Color.DARK_GRAY);
		for(int i = 0; i < num; i ++)
		{
		    //double mod = 1.0 * zFocus * zFocus / balls[i].getZ() / balls[i].getZ();
		    double mod = 1.0 * zFocus / balls[i].getZ();

		    double ballReal = ballR * mod;

		    int box = (int)((realU - realD) * mod);

		    int bound = (int)((limitU - box) / 2);

		    g2d.fillOval((int)(balls[i].getX() * mod + bound - ballReal), (int)(bound + box - ballReal), (int)(ballReal * 2.0), (int)(ballReal * 2.0));
		}

		    g2d.setColor(Color.BLUE);
		for(int i = 0; i < num; i ++)
		{
		    //double mod = 1.0 * zFocus * zFocus / balls[i].getZ() / balls[i].getZ();
		    double mod = 1.0 * zFocus / balls[i].getZ();

		    double ballReal = ballR * mod;

		    int box = (int)((realU - realD) * mod);

		    int bound = (int)((limitU - box) / 2);

			g2d.fillOval((int)(balls[i].getX() * mod + bound - ballReal), (int)(balls[i].getY() * mod + bound - ballReal), (int)(ballReal * 2.0), (int)(ballReal * 2.0));

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

		g2d.setColor(Color.RED);
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
	*/

} // Panneau class
