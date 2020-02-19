package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.Timer;

public class PlayerController implements KeyListener {

	private HashSet<Integer> keysPressed;
	private Player player;
	private KeyHandled keyHandled;
	private int speed = 3;
	private boolean gravity = false;
	
	public PlayerController(KeyHandled keyHandled, Player player) {
		this.player = player;
		keysPressed = new HashSet<Integer>();
		this.keyHandled = keyHandled;
		timer.start();
	}
	
	private Timer timer = new Timer(speed, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (gravity) {
				if (!keysPressed.contains(KeyEvent.VK_W) && player.getJump()<=0) {
					System.out.println("hello");
					keyHandled.keyEvent(KeyEvent.VK_S);
				}else if (player.getJump() <= 0) {
					keyHandled.keyEvent(KeyEvent.VK_S);
				}
			}
			for (int i: keysPressed) {
				if (i != KeyEvent.VK_W) {
					keyHandled.keyEvent(i);	
				}else {
					if (player.getJump() == -1) {
						player.setJump(player.getMaxJumpHeight());
						keyHandled.keyEvent(KeyEvent.VK_W);
					}
				}
			}
			if (player.getJump() > 0) {
				keyHandled.keyEvent(KeyEvent.VK_W);
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
	
	public HashSet<Integer> getKeysPressed() {
		return keysPressed;
	}
	
	public void setKeysPressed(HashSet<Integer> keysPressed) {
		this.keysPressed = keysPressed;
	}
	
}
