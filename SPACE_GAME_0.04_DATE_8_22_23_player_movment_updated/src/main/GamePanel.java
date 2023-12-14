package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable//inheritance, gamepanel has all attributes of parent class jpanel, implements the Runnable interface
{
	// SCREEN SETTING
	final int originaTileSize = 16; // 16x16 tile size
	final int scale = 3; //16 Pixels is super small so we scale the image up 
	
	final int tileSize = originaTileSize * scale; //48x48
	final int maxScreenCol = 16; //number of columns 
	final int maxScreenRow = 12; //number of Rows
	final int screenWidth = tileSize * maxScreenCol; // 0-768 pixels from upper left corner
	final int screenHeight = tileSize * maxScreenRow; //0-576 pixels from upper left corner
	
	//FPS
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler(); //instantiates the keyhandler
	Thread gameThread; // Thread is a little weight process, used to make a looping game object that will run until stopped by the user 
	
	//set player's default position 
	private int tmpAngle, sx, sy, reload, numToShoot, spread, bWidth, bHeight;
	Hero hero = new Hero(100, 100, 0, 50, 30);
	
	//CONSTRUCTOR //builds the object 
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //set the size of the window, Dimension takes width and height
		this.setBackground(Color.black); //sets background color to black
		this.setDoubleBuffered(true); // Double-buffering is the process of drawing graphics into an off-screen image buffer and then copying the contents of the buffer to the screen all at once
		this.addKeyListener(keyH); //add the keylistener to game panel
		this.setFocusable(true); //allows the game panel to be focused
		tmpAngle = 0;
		keyH.special = keyH.fire = keyH.left = keyH.right = keyH.moveForward = keyH.moveBackward = false;
		keyH.canForward = keyH.canBackward = true;
		sx = sy = 2;
	}
	
	public void startGameThread()
	{
		gameThread = new Thread(this);//instantiates thread
		gameThread.start(); //starts thread
	}


//	@Override
//	public void run() //method is called with thread is started/ Game loop
//	{
//		double drawInterval = 1000000000/FPS;//sleep method, 0.0166666 seconds
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		while(gameThread != null)
//		{
//			long currentTime = System.nanoTime(); // current time of clock
//			//System.out.println("Current Time "+System.nanoTime());
//			//1. UPADATE: update information such as character position 
//			update();
//			
//			//2. DRAW: draw the screen with the updated information
//			repaint();
//			
//			try 
//			{
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				
//				if(remainingTime < 0)
//				{
//					remainingTime = 0;
//				}
//				Thread.sleep((long)remainingTime); //cast to long
//				
//				nextDrawTime += drawInterval;
//			} 
//			catch (InterruptedException e) 
//			{
//				e.printStackTrace();
//			}
//		
//		}
//	}
	
	@Override
	public void run() //method is called with thread is started/ Game loop
	{
		double drawInterval = 1000000000/FPS;//0.0166666 seconds
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null)
		{
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
			
			if(delta >= 1)
			{
					update();
			
					repaint();	
					
					delta--;
				
			}
		}
	}
	
	public void update() //updates information X:0,Y:0 is upper left corner
	{
		
		// changing the hero angle
		if (keyH.left) {
			tmpAngle -= 1;
		}
		if (keyH.right) {
			tmpAngle += 1;
		}

		// setting the hero angle
		
		hero.setA(tmpAngle);
		// this is just to keep the angle between 0 and 360
		if (tmpAngle > 360) {
			tmpAngle = 0;
		} else if (tmpAngle < 0) {
			tmpAngle = 360;

		}

		// moving the hero
		if (keyH.moveForward) {
			if (keyH.canForward) {
				hero.moveForward(sx, sy);
			}
		}
		if (keyH.moveBackward) {
			if (keyH.canBackward) {
				hero.moveBackword(sx, sy);
			}
		}
	}
	
	public void paintComponent(Graphics g) //draws image 
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; //add more graphic features 
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		AffineTransform old = g2.getTransform();
		
		//g2.setColor(Color.white);
		
		// rotating the hero, rotation point is the middle of the square
				g2.rotate(hero.getA(), hero.getX() + hero.getW(),
						hero.getY() + hero.getH() / 2);
				// draw the image
				g2.drawImage(hero.getI(), (int) hero.getX(), (int) hero.getY(),
						hero.getW(), hero.getH(), this);
				g2.setTransform(old);

		
		//g2.dispose();
	}
}
