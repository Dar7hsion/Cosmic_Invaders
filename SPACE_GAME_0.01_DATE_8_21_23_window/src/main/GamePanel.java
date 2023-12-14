package main;

import java.awt.Color;//used in line 23
import java.awt.Dimension;//used for line 21
import javax.swing.JPanel;//used for line 6

public class GamePanel extends JPanel //inheritance, gamepanel has all attributes of parent class jpanel
{
	// SCREEN SETTING
	final int originaTileSize = 16; // 16x16 tile size
	final int scale = 3; //16 Pixels is super small so we scale the image up 
	
	final int tileSize = originaTileSize * scale; //48x48
	final int maxScreenCol = 16; //number of columns 
	final int maxScreenRow = 12; //number of Rows
	final int screenWidth = tileSize * maxScreenCol; // 0-768 pixels from upper left corner
	final int screenHeight = tileSize * maxScreenRow; //0-576 pixels from upper left corner
	
	//CONSTRUCTOR //builds the object 
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //set the size of the window, Dimension takes width and height
		this.setBackground(Color.black); //sets background color to black
		this.setDoubleBuffered(true); // Double-buffering is the process of drawing graphics into an off-screen image buffer and then copying the contents of the buffer to the screen all at once
		
	}
}
