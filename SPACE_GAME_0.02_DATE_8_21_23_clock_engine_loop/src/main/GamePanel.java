package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
	
	Thread gameThread; // Thread is a little weight process, used to make a looping game object that will run until stopped by the user 
	
	
	//CONSTRUCTOR //builds the object 
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //set the size of the window, Dimension takes width and height
		this.setBackground(Color.black); //sets background color to black
		this.setDoubleBuffered(true); // Double-buffering is the process of drawing graphics into an off-screen image buffer and then copying the contents of the buffer to the screen all at once
		
	}
	
	public void startGameThread()
	{
		gameThread = new Thread(this);//instantiates thread
		gameThread.start(); //starts thread
	}


	@Override
	public void run() //method is called with thread is started/ Game loop
	{
		while(gameThread != null)
		{
			//Test line
			//System.out.println("test");
			//loop needs to do two things every frame cycle
			//1. UPADATE: update information such as character position 
			update();
			
			//2. DRAW: draw the screen with the updated information
			repaint();
		
		}
	}
	
	public void update() //updates information 
	{
		
	}
	
	public void paintComponent(Graphics g) //draws image 
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; //add more graphic features 
		
		g2.setColor(Color.white);
		
		g2.fillRect(100, 100, 48, 48);
		
		g2.dispose();
	}
}
