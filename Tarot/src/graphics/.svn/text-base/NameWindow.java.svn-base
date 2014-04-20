package graphics;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import game.Player;

public class NameWindow extends Window implements ActionListener, MouseListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* This window will contain all the buttons and check boxes 
	 * needed to implement the score and the game */

	// We define the main window, to be able to modify parameters (in particular the list of players)
	private MainWindow F = null;
	
	public JLabel namePlayerLabel= new JLabel("Names : ");
	public ArrayList<JTextField> playerNames = new ArrayList<JTextField>();

	
	public JButton cancel= new JButton("Cancel");
	public JButton validateNames= new JButton("Valider");
	public JButton addPlayer= new JButton("Add Player");
	public JButton removePlayer = new JButton("Remove Player");
	
	
	private ArrayList<Boolean> playerNameNeverDefined = new ArrayList<Boolean>();

	public BorderLayout bl = new BorderLayout();
	
	public Panel buttonPane = new Panel();
	public Panel playersPane = new Panel();


	public NameWindow(MainWindow currentMainWindow){

		super();
		this.F=currentMainWindow;
		
		/* We display the old players names (with the right number of players, 
		 * allowing to define quickly a new game without having to redefine all the names
		 */
		for (Player player : F.players) {
			this.playerNames.add(new JTextField(player.getName(), 8));
			this.playerNameNeverDefined.add(true);
		}
		
		this.setTitle("Define the players names"); 
		
		addPlayer.addMouseListener(this);
		removePlayer.addMouseListener(this);
		validateNames.addMouseListener(this);
		cancel.addMouseListener(this);
		
		this.setSize(600, 400); 
		this.contentPane.setLayout(bl);
		
		this.contentPane.add(this.buttonPane, BorderLayout.NORTH);
		
		this.contentPane.add(this.playersPane, BorderLayout.CENTER);
		
		this.buttonPane.add(cancel);
		this.buttonPane.add(addPlayer);
		this.buttonPane.add(removePlayer);
		this.buttonPane.add(validateNames);
		
		// We display the fields that allow us to specify names
		for (JTextField playerName : playerNames) {
			this.playersPane.add(playerName);
		}

		// For each text field, we add a mouse listener
		for (JTextField playerName : playerNames) {
			// To avoid several listener for the same object, we remove just in case, before adding
			playerName.addMouseListener(this);
		}

		this.setResizable(true); 
	}
	
	public void mousePressed(MouseEvent arg0) {

		for (int i=0, n=playerNames.size(); i<n; i++) {
			if(arg0.getSource()==playerNames.get(i)&&playerNameNeverDefined.get(i)){
				((JTextField)arg0.getSource()).setText("");
				playerNameNeverDefined.set(i, false);
			}
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==validateNames){
			
			ArrayList<Player> listOfPlayers = new ArrayList<Player>();
			for (JTextField playerName : playerNames) {
				listOfPlayers.add(new Player(playerName.getText()));
			}
			
			F.setPlayers(listOfPlayers);
			
			// We can't cancel a round at the beginning
			F.cancelLastRound.setEnabled(false);
			F.previousRoundIndivScores.clear();
			
			// To quit the window once the players are defined
			this.dispose();


		}
		
		if(arg0.getSource()==cancel){
			this.dispose();
		}
		
		if(arg0.getSource()==addPlayer){
			int nbPlayers = this.playerNames.size();
			
			if (nbPlayers < 5) {
				String newPlayerName = new String("joueur"+(this.playerNames.size()+1));
				JTextField newComponent = new JTextField(newPlayerName,8);
			     this.playerNames.add(newComponent);
			     this.playerNameNeverDefined.add(true);
			     this.playersPane.add(newComponent);
			     this.playersPane.repaint();
			     
			     newComponent.addMouseListener(this);
			     this.validate();
			}
			
		}
		
		if(arg0.getSource()==removePlayer){
			/*
			 * We delete the last element of the ArrayList playerNames
			 */
			int nbPlayers = this.playerNames.size();
			
			if (nbPlayers > 3) {
				JTextField oldComponent = this.playerNames.get(nbPlayers-1);
				this.playerNames.remove(oldComponent);//We remove the component from the list of JTextField
			    this.playerNameNeverDefined.remove(nbPlayers-1);// We remove the boolean associated
			    this.playersPane.remove(oldComponent);// We remove the component from the graphic display (insite the playerPane)
			    this.playersPane.repaint();// We clean the background
			    this.validate();//we refresh the window
			}
		     
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
