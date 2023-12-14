package main;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) 
	{
		JFrame window = new JFrame(); //instantiate a JFame window object named window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets the window object to close when closed with the ("X") button
		window.setResizable(false); //window size can't be changed
		window.setTitle("PLACE HOLDER"); //tile that shows up at top of window, NOTE:NEW TITLE NEEDED
		
		GamePanel gamePanel = new GamePanel(); //instantiate a gamePanel object
		window.add(gamePanel); // places the gamePanel into the window 
		
		
		window.pack(); //sets the size of the window to the set size of the subcomponent in this case gamepanel 
		
		window.setLocationRelativeTo(null); //where the window will be located, null means center the window 
		window.setVisible(true); //lets the window be seen, can' be seen if not set to true
		
		gamePanel.startGameThread();
	}

}
