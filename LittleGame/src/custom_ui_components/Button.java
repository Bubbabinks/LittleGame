package custom_ui_components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.AudioManager;

public class Button extends JLabel implements MouseListener {

	private static final long serialVersionUID = 2013277262440223580L;
	
	private Color defaultBackground;
	private Color defaultForeground;
	private Color highlightBackground;
	private Color highlightForeground;
	private Color ClickedBackground;
	private Color ClickedForeground;
	
	private boolean buttonPressed = false;
	private boolean buttonSubjected = false;
	
	private ActionEvent actionEvent = new ActionEvent(this, 0, "ButtonPressed");
	
	private ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();
	
	public void setActionEvent(int id, String txt) {
		actionEvent = new ActionEvent(this, id, txt);
	}
	
	public ActionEvent getActionEvent() {
		return actionEvent;
	}
	
	public void addActionListener(ActionListener actionListener) {
		actionListeners.add(actionListener);
	}
	
	public Button(int width, int height, String text) {
		setPreferredSize(new Dimension(width, height));
		setHorizontalAlignment(SwingConstants.CENTER);
		setText(text);
		setOpaque(true);
		setBackground(new Color(181,143,76));
		setForeground(Color.WHITE);
		defaultBackground = new Color(181,143,76);
		defaultForeground = Color.WHITE;
		highlightBackground = new Color(161, 123, 56);
		highlightForeground = Color.WHITE;
		ClickedBackground = new Color(141, 103, 36);
		ClickedForeground = Color.WHITE;
		addMouseListener(this);
		setBorder(BorderFactory.createLineBorder(new Color(121, 83, 16), 3));
	}
	
	public void buttonAction() {
		new AudioManager().buttonPressed();
		for (ActionListener actionListener: actionListeners) {
			actionListener.actionPerformed(actionEvent);
		}
	}

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == e.BUTTON1) {
			setBackground(ClickedBackground);
			setForeground(ClickedForeground);
			buttonPressed = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == e.BUTTON1) {
			if (buttonSubjected) {
				setBackground(highlightBackground);
				setForeground(highlightForeground);
			}else {
				setBackground(defaultBackground);
				setForeground(defaultForeground);
			}
			buttonPressed = false;
			if (buttonSubjected) {
				buttonAction();
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		buttonSubjected = true;
		if (!buttonPressed) {
			setBackground(highlightBackground);
			setForeground(highlightForeground);
		}else {
			setBackground(ClickedBackground);
			setForeground(ClickedForeground);
		}
	}

	public void mouseExited(MouseEvent e) {
		buttonSubjected = false;
		if (!buttonPressed) {
			setBackground(defaultBackground);
			setForeground(defaultForeground);
		}
	}
	
}