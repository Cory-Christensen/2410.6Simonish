package cs2410.game;
import cs2410.components.ColorButton;
import cs2410.components.ScoreBoard;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Random;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/** ChangePanel - Main class. Creates a Panel and updates according to 
 * actions in the Simonish Game 
 * @author Cory Christensen
 *
 */

public class ChangePanel {
	
	/**
	 * Definitions for components of Game
	 */
	private JFrame frame;
	private Container pane;
	private JPanel panel;
	private ColorChangeListener colorListener;
	private StartGame start;
	
	private int numCorrect;
	private int highScore;
	private Random generateRandom;
	private ScoreBoard scoreBoard;
	private int score;
	private ColorButton redBtn;
	private ColorButton greenBtn;
	private ColorButton yellowBtn;
	private ColorButton blueBtn;
	private Vector<Integer> seq;
	
	/**
	 * Constructor. Builds the JFrame with Components. Calls for the start of the Game
	 */
	public ChangePanel() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		pane = frame.getContentPane();
		pane.setPreferredSize(new Dimension(400, 500));
		highScore = 0;
		numCorrect = 0;
		
		scoreBoard = new ScoreBoard(score, highScore);
		generateRandom = new Random();
		
		colorListener = new ColorChangeListener();
		ButtonMash btnListener = new ButtonMash();
		
		/**
		 * Configure the Buttons for the game
		 */
		blueBtn = new ColorButton(Color.BLUE, btnListener);
		redBtn = new ColorButton(Color.RED, btnListener);
		yellowBtn = new ColorButton(Color.YELLOW, btnListener);
		greenBtn = new ColorButton(Color.GREEN, btnListener);
		blueBtn.setBackground(java.awt.Color.BLUE.darker());
		redBtn.setBackground(java.awt.Color.RED.darker());
		yellowBtn.setBackground(java.awt.Color.YELLOW.darker());
		greenBtn.setBackground(java.awt.Color.GREEN.darker());
		blueBtn.addMouseListener(colorListener);
		redBtn.addMouseListener(colorListener);
		yellowBtn.addMouseListener(colorListener);
		greenBtn.addMouseListener(colorListener);
		
		/**
		 * Set the layout for the JFrame and add components		
		 */
		pane.setLayout(new BorderLayout());

		scoreBoard.setPreferredSize(new Dimension(400, 75));
		pane.add(scoreBoard, BorderLayout.NORTH);
		redBtn.setPreferredSize(new Dimension(133,100));
		pane.add(redBtn, BorderLayout.WEST);
		greenBtn.setPreferredSize(new Dimension(100,100));
		pane.add(greenBtn, BorderLayout.CENTER);
		yellowBtn.setPreferredSize(new Dimension(133,100));
		pane.add(yellowBtn,BorderLayout.EAST);
		blueBtn.setPreferredSize(new Dimension(100,150));
		pane.add(blueBtn, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);	
		frame.setVisible(true);
		
		start = new StartGame();
		
		
	}
	
	/**
	 * Class for starting game. Creates sequence and plays.
	 *
	 */
	public class StartGame implements ActionListener
	{

		StartGame() {
			JButton startBtn = ScoreBoard.getStartBtn();
			startBtn.addActionListener(this);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == ScoreBoard.getStartBtn()) {
				seq = new Vector<Integer>();
				playSequence();
				score = 0;
			}
		}
	}
	
	/**
	 * ButtonMash class - 
	 * Checks to see if the Buttons pressed match with the sequence
	 * If not, calls lose function
	 */
	public class ButtonMash implements ActionListener 
	{
		public ButtonMash(){}
		
		public void actionPerformed(ActionEvent event) {
			ColorButton temp = (ColorButton) event.getSource();
			if(temp.equals(redBtn) && seq.get(numCorrect) == 0) {
				numCorrect++;
				score++;
				redBtn.activate(ChangePanel.this);
			}
			else if(temp.equals(greenBtn) && seq.get(numCorrect) == 1) {
				numCorrect++;
				score++;
				greenBtn.activate(ChangePanel.this);
			}
			else if(temp.equals(yellowBtn) && seq.get(numCorrect) == 2) {
				numCorrect++;
				score++;
				yellowBtn.activate(ChangePanel.this);
			}
			else if(temp.equals(blueBtn) && seq.get(numCorrect) == 3) {
				numCorrect++;
				score++;
				blueBtn.activate(ChangePanel.this);
			}
			else {
				lose();
			}
			if(numCorrect == seq.size()) {
				numCorrect = 0;
				playSequence();
			}
		}
		public void lose() {
			if(score > highScore)
				highScore = score;
			numCorrect = 0;
		}
		
	}
	
	class ColorChangeListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == blueBtn) {
				blueBtn.setBackground(blueBtn.getBackground().brighter());
			}
			if (e.getSource() == redBtn) {
				redBtn.setBackground(redBtn.getBackground().brighter());
			}
			if (e.getSource() == yellowBtn) {
				yellowBtn.setBackground(yellowBtn.getBackground().brighter());
			}
			if (e.getSource() == greenBtn) {
				greenBtn.setBackground(greenBtn.getBackground().brighter());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == blueBtn) {
				blueBtn.setBackground(blueBtn.getBackground().darker());
			}
			if (e.getSource() == redBtn) {
				redBtn.setBackground(redBtn.getBackground().darker());
			}
			if (e.getSource() == yellowBtn) {
				yellowBtn.setBackground(yellowBtn.getBackground().darker());
			}
			if (e.getSource() == greenBtn) {
				greenBtn.setBackground(greenBtn.getBackground().darker());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void playSequence(){
		score++;
		seq.add(generateRandom.nextInt(4));
		
		/**
		 * loops through the integers in the sequence and activates each one.
		 */
		for(Integer i : seq) {
			if(i.equals(0))
				redBtn.activate(this);
			if(i.equals(1))
				greenBtn.activate(this);
			if(i.equals(2))
				yellowBtn.activate(this);
			if(i.equals(3))
				blueBtn.activate(this);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChangePanel();

	}

}
