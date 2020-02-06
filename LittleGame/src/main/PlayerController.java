package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.Timer;

public class PlayerController implements KeyListener {

	private HashSet<Integer> keysPressed;
	private KeyHandled keyHandled;
	private int speed = 3;
	private boolean gravity = false;
	
	public PlayerController(KeyHandled keyHandled) {
		keysPressed = new HashSet<Integer>();
		this.keyHandled = keyHandled;
		timer.start();
	}
	
	private Timer timer = new Timer(speed, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (gravity) {
				if (!keysPressed.contains(KeyEvent.VK_W)) {
					keyHandled.keyEvent(KeyEvent.VK_S);
				}
			}
			for (int i: keysPressed) {
				keyHandled.keyEvent(i);
			}
		}
	});
	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		keysPressed.add(e.getKeyCode());
	}
	public void keyReleased(KeyEvent e) {
		keysPressed.remove(e.getKeyCode());
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setGravity(boolean active) {
		gravity = active;
	}
	
}
