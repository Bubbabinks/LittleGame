package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

public class KeyHandler implements KeyListener {

	private KeyHandled keyHandled;
	private int speed = 100;
	
	public KeyHandler(KeyHandled keyHandled) {
		this.keyHandled = keyHandled;
	}
	
	private Timer forwardTimer = new Timer(speed, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			keyHandled.keyEvent(KeyEvent.VK_W);
		}
	});
	
	private Timer backwardTimer = new Timer(speed, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			keyHandled.keyEvent(KeyEvent.VK_S);
		}
	});
	
	private Timer rightTimer = new Timer(speed, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			keyHandled.keyEvent(KeyEvent.VK_D);
		}
	});
	
	private Timer leftTimer = new Timer(speed, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			keyHandled.keyEvent(KeyEvent.VK_A);
		}
	});
	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			if (!forwardTimer.isRunning()) {
				forwardTimer.getActionListeners()[0].actionPerformed(new ActionEvent(this, 0, "Activate"));
				forwardTimer.start();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			if (!backwardTimer.isRunning()) {
				backwardTimer.getActionListeners()[0].actionPerformed(new ActionEvent(this, 0, "Activate"));
				backwardTimer.start();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			if (!rightTimer.isRunning()) {
				rightTimer.getActionListeners()[0].actionPerformed(new ActionEvent(this, 0, "Activate"));
				rightTimer.start();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			if (!leftTimer.isRunning()) {
				leftTimer.getActionListeners()[0].actionPerformed(new ActionEvent(this, 0, "Activate"));
				leftTimer.start();
			}
		}
	}
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			forwardTimer.stop();
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			backwardTimer.stop();
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			rightTimer.stop();
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			leftTimer.stop();
		}
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
