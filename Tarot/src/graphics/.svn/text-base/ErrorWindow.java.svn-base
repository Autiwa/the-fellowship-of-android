package graphics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import game.Player;

public class ErrorWindow extends Window implements ActionListener, MouseListener {


	/* This window will contain all the buttons and check boxes 
	 * needed to implement the score and the game */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// We define the main window, to be able to modify parameters (in particular the list of players)
	private MainWindow F = null;
	
	public ArrayList<JLabel> playerLabels = new ArrayList<JLabel>();
	public ArrayList<JTextField> playerScores = new ArrayList<JTextField>();
	
	public JButton cancel= new JButton("Cancel");
	public JButton validate= new JButton("Valider");
	
	
	public GridBagConstraints gbc= new GridBagConstraints();


	public ErrorWindow(MainWindow currentMainWindow){

		super();
		this.F=currentMainWindow;
		
		for (Player player : F.players) {
			this.playerLabels.add(new JLabel(player.getName()));
			this.playerScores.add(new JTextField("0", 8));
		}
	     
		
		this.setTitle("Apply a manual correction to the last scores"); 
		

		this.setSize(600, 400); 
		this.contentPane.setLayout(new GridBagLayout());
		gbc.gridx = gbc.gridy = 0; 
		gbc.insets = new Insets(10, 15, 0, 0); 
		
		
		this.contentPane.add(cancel, gbc);
		gbc.gridx++;
		this.contentPane.add(validate, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		// We display the name of the players
		for (JLabel playerLabel : playerLabels) {
			this.contentPane.add(playerLabel, gbc);
			gbc.gridx++;
		}
		
		gbc.gridx = 0;
		gbc.gridy++;
		// We display the name of the players
		for (JTextField playerScore : playerScores) {
			this.contentPane.add(playerScore, gbc);
			gbc.gridx++;
		}
		
		validate.addMouseListener(this);
		cancel.addMouseListener(this);

		this.setResizable(true); 
		this.setVisible(true);
		this.validate();
	}
	
	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==validate){
			
			ArrayList<Integer> modifiedScores = new ArrayList<Integer>();
			int checkScore = 0;
			for (JTextField playerScore : this.playerScores) {
				int score = Integer.parseInt(playerScore.getText());
				checkScore += score;
				modifiedScores.add(score);
			}
			
			// We test if the total sum equal 0
			if (checkScore != 0) {
				JOptionPane.showMessageDialog(null, "The total sum of the modifications must be 0", "Erreur : Total sum not 0", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			for (int i=0, n=F.players.size(); i<n; i++) {
				Player player = F.players.get(i);
				player.addScore(modifiedScores.get(i));
			}
			
			F.modifyLastScore();
			
			// To quit the window once the players are defined
			this.dispose();


		}
		
		if(arg0.getSource()==cancel){
			this.dispose();
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
