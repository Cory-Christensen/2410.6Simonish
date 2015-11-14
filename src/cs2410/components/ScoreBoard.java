package cs2410.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Scoreboard for top of Simonish Game
 * @author final
 *
 */
public class ScoreBoard extends JPanel {
	/**
	 * Declarations for members of ScoreBoard
	 */
	private int score;
	private int highScore;
	private JLabel scoreLabel;
	private JLabel highScoreLabel;
	private static JButton startBtn;
	
	/**
	 * Constructor. Updates score, highscore and adds to layout with Startbutton
	 * @param score
	 * @param highScore
	 */
	public ScoreBoard(int score, int highScore)
	{
		this.score = score;
		this.highScore = highScore;
		startBtn = new JButton("Start Game");
		scoreLabel = new JLabel("Current Score: " + this.score);
		highScoreLabel = new JLabel("High Score: " + this.highScore);
		
		this.setLayout(new GridLayout(1,3));
		add(startBtn);
		add(scoreLabel);
		add(highScoreLabel);		
	}
	
	/**
	 * returns Start button for ActionListener
	 * @return
	 */
	public static JButton getStartBtn() {
		return startBtn;
	}
}
