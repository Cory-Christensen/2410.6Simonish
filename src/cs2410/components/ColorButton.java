package cs2410.components;

import cs2410.game.ChangePanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionListener;

/** ColorButton - component of Simonish Game
 * Each ColorButton is a JButton for the Simonish Game
 * @author Cory Christensen
 *
 */

public class ColorButton extends JButton {
	
	/**
	 * Declaration for button color
	 */
	private Color btnColor;
	
	/**
	 * Causes button to blink
	 */
	private void blink() {
		this.setBackground(this.btnColor.brighter());
		this.update(getGraphics());
		System.out.println("pause");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setBackground(this.btnColor.darker());
		this.update(getGraphics());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Constructor. Gets Button's color and adds a listener
	 * @param color
	 * @param btnListener
	 */
	public ColorButton(Color color, ActionListener btnListener)
	{
		btnColor = color;
		if (btnListener != null)
			this.addActionListener(btnListener);
	}

	/**
	 * Activates button (calls Blink)
	 * @param changePanel
	 */
	public void activate(ChangePanel changePanel) {
		blink();
	}
	
	
	
}
