package main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Hero 
{
	private int tmpAngle, sx, sy, reload, numToShoot, spread, bWidth, bHeight;
	public double x, y, a; // x,y and angle
	private int w, h, spriteN, tmpLoad; // width and height and reloading counter (for// shooting)
	public double screenX, screenY;
	//private ArrayList bullets; // this will hold our bullets 
	private BufferedImage R1, R2, R3, current;  //and this is the image 
	private GamePanel gp;

	// constructor
	public Hero(double x, double y, double a, int w, int h, GamePanel gp) {

		this.x = x;
		this.y = y;
		this.a = a;
		this.w = w;
		this.h = h;
		this.gp = gp;
		screenX = gp.screenWidth/2 - (getX()/2);
		screenY = gp.screenWidth/2 - (getY()/2);
//		bullets = new ArrayList();
		tmpLoad = 0;
		tmpAngle = 0;
		spriteN = 0;
		gp.keyH.special = gp.keyH.fire = gp.keyH.left = gp.keyH.right = gp.keyH.moveForward = gp.keyH.moveBackward = false;
		gp.keyH.canForward = gp.keyH.canBackward = true;
		sx = sy = 2;
		getHeroImage();
		current=R1;
	}

	// returning all the necessary value of this class

	public Image getI() {
		return current;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getA() {
		return a;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, w, h);
	}

	/*public ArrayList getBullets() {
		return bullets;
	}*/

	// setting the values
	public void setA(int aa) {

		a = Math.toRadians(aa);
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	// move toward the angle
	// //forward
	public void moveForward(int sx, int sy) {
		x += Math.cos(a) * sx;
		y += Math.sin(a) * sy;
	}

	// //backward
	public void moveBackword(int sx, int sy) {
		x -= Math.cos(a) * sx;
		y -= Math.sin(a) * sy;
	}
	
	public void getHeroImage()
	{
		try {
			R1 = ImageIO.read(getClass().getResourceAsStream("/rocket1.png"));
			R2 = ImageIO.read(getClass().getResourceAsStream("/rocket2-1.png.png"));
			R3 = ImageIO.read(getClass().getResourceAsStream("/rocket3-1.png.png"));
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void update()
	{
				// if the hero get off the screen
				// we make it appear from the opposite side of the screen
				if (getX() > gp.worldWidth) {
					setX(0);
				} else if (getX() < 0) {
					setX(gp.worldWidth);
				}

				if (getY() > gp.worldHeight) {
					setY(0);
				} else if (getY() < 0) {
					setY(gp.worldHeight);
				}
		
				
				// changing the hero angle
				if (gp.keyH.left) {
					tmpAngle -= 1;
				}
				if (gp.keyH.right) {
					tmpAngle += 1;
				}

				// setting the hero angle
				
				setA(tmpAngle);
				// this is just to keep the angle between 0 and 360
				if (tmpAngle > 360) {
					tmpAngle = 0;
				} else if (tmpAngle < 0) {
					tmpAngle = 360;

				}

				// moving the hero
				if (gp.keyH.moveForward) {
					if (gp.keyH.canForward) {
						moveForward(sx, sy);
					}
				}
				if (gp.keyH.moveBackward) {
					if (gp.keyH.canBackward) {
						moveBackword(sx, sy);
					}
				}
				
				switch(spriteN) {
				
				case 0:
					current = R2;
					spriteN++;
				break;
				
				case 1:
					current = R3;
					spriteN++;
				break;
				
				case 2:
					current = R1;
					spriteN = 0;
				break;
				}
	}

	public void draw(Graphics2D g2)
	{
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		AffineTransform old = g2.getTransform();
		
		// rotating the hero, rotation point is the middle of the square
				g2.rotate(getA(), (int)screenX + getW(),
						(int)screenY + getH() / 2);
				// draw the image
				g2.drawImage(getI(), (int)screenX, (int)screenY,
						getW(), getH(), null);
				g2.setTransform(old);

	}

}
