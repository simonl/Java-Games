package boubouworld;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class PanelTank extends JPanel
{
	
	private int tankX1, tankX2;
	private int ballX1, ballY1, ballX1_1, ballY1_1;
	private int ballX2, ballY2, ballX2_2, ballY2_2;
	
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.red);
		
		g.fillOval(posX, posY, 10, 10);
		g.fillOval(posX2, posY2, 10, 10);         
	}
 
	public int getAnchorX() {
		return anchorX;
	}
 
	public void setAnchorX(int anchorX) {
		this.anchorX = anchorX;
	}
	
	public int getAnchorY() {
		return anchorY;
	}
 
	public void setAnchorY(int anchorY) {
		this.anchorY = anchorY;
	}
	
	public int getPosX() {
		return posX;
	}
 
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosX2() {
		return posX2;
	}
 
	public void setPosX2(int posX2) {
		this.posX2 = posX2;
	}
 
	public int getPosY() {
		return posY;
	}
 
	public void setPosY(int posY) {
		this.posY = posY;
	}
 
	public int getPosY2() {
		return posY2;
	}
 
	public void setPosY2(int posY2) {
		this.posY2 = posY2;
	}
	
	public int getAngle() {
		return angle;
	}
 
	public void setAngle(int angle) {
		if(angle == 360 && dirAngle == 1)
		    angle = 0;
		if(angle == 0 && dirAngle == -1)
		    angle = 360;
		this.angle = angle;
		
		posX = (int)(Math.cos(angle/360.0*2.0*Math.PI)*20)+ anchorX;
		posY = (int)-(Math.sin(angle/360.0*2.0*Math.PI)*20)+ anchorY;
		posX2 = 2*anchorX - posX;
		posY2 = 2*anchorY - posY;
	}

	public int getDirVert() {
		return dirVert;
	}
	
	public void setDirVert(int dirVert) {
		this.dirVert = dirVert;
	}

	public int getDirHori() {
		return dirHori;
	}
	
	public void setDirHori(int dirHori) {
		this.dirHori = dirHori;
	}

	public int getDirAngle() {
		return dirAngle;
	}
	
	public void setDirAngle(int dirAngle) {
		this.dirAngle = dirAngle;
	}
	
} // Panneau class
