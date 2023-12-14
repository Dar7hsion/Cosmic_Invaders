package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener // picks up if a listed key is pressed, held down and or released
{
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W)
		{
			upPressed = true;
		}
		
		if(code == KeyEvent.VK_S)
		{
			downPressed = true;
		}
		
		if(code == KeyEvent.VK_A)
		{
			leftPressed = true;
		}
		
		if(code == KeyEvent.VK_D)
		{
			rightPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W)
		{
			upPressed = false;
		}
		
		if(code == KeyEvent.VK_S)
		{
			downPressed = false;
		}
		
		if(code == KeyEvent.VK_A)
		{
			leftPressed = false;
		}
		
		if(code == KeyEvent.VK_D)
		{
			rightPressed = false;
		}
		
	}

}

/* Backspace 8
 * Enter 10
 * Shift 16
 * Alt 18
 * A 65
 * C 67
 * E 69
 * G 71
 * I 73
 * Tab 9
 * Clear 12
 * Ctrl 17
 * B 66
 * D 68
 * F 70
 * H 72
 * J 74
 */
