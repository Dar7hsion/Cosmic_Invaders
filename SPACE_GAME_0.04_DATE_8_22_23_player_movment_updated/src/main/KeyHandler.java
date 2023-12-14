package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener // picks up if a listed key is pressed, held down and or released
{
	
    public boolean moveForward, canForward, canBackward, moveBackward, left,
	right, fire, special;
	

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == e.VK_UP || e.getKeyCode() == e.VK_Z
				|| e.getKeyCode() == e.VK_W) {
			moveForward = true;

		}
		if (e.getKeyCode() == e.VK_DOWN || e.getKeyCode() == e.VK_S) {
			moveBackward = true;

		}
		if (e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_Q
				|| e.getKeyCode() == e.VK_A) {
			left = true;
		}
		if (e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_D) {
			right = true;
		}
		if (e.getKeyCode() == e.VK_SHIFT) {
			fire = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == e.VK_UP || e.getKeyCode() == e.VK_Z
				|| e.getKeyCode() == e.VK_W) {
			moveForward = false;
		}
		if (e.getKeyCode() == e.VK_DOWN || e.getKeyCode() == e.VK_S) {
			moveBackward = false;
		}
		if (e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_Q
				|| e.getKeyCode() == e.VK_A) {

			left = false;
		}
		if (e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_D) {
			right = false;
		}
		if (e.getKeyCode() == e.VK_SHIFT) {
			fire = false;
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
