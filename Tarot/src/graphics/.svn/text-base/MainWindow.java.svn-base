package graphics;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import game.Player;

public class MainWindow extends Window implements ActionListener, MouseListener {


	/* This window will contain all the buttons and check boxes 
	 * needed to implement the score and the game */


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String backupFile = new String("tarot_game.bak");
	private static String statFile = new String("tarot_game.stat");
	private JButton newDonne = new JButton("Un tour pour rien");
	private JButton newGame = new JButton("Nouvelle partie");
	private JButton newRound = new JButton("Nouvelle donne");
	private JButton correctScore = new JButton("Corriger score");

	public ArrayList<Player> players = new ArrayList<Player>();

	private JScrollPane scrollPane = new JScrollPane();
	private Panel buttonPane = new Panel();
	private Panel newGamePane = new Panel();
	
	public JButton cancelLastRound = new JButton("Annuler dernier tour");
	public ArrayList<Integer> previousRoundIndivScores = new ArrayList<Integer>();
	
	
	private DefaultTableModel dynamicScores = new DefaultTableModel(); // the object where we add dynamically the rows
	private JTable scoreTable = new JTable(dynamicScores) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	public int nbRounds = 0;
	
	public int quiDonne = 0;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public Dimension scrollArea = new Dimension(200, 100);
	
	public MainWindow(){

		super();

	    this.pack(); 
	    
	    this.setDefaultCloseOperation(Window.DO_NOTHING_ON_CLOSE);
	    WindowListener closingListener = new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            // We delete the window only if this is the only one remaining
	    		if (main.Main.windowList.size() == 1) {
	    			try {
						main.Main.mainWindow.saveStat();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
		            System.exit(0);
	    		}
	        }
	    };
	    this.addWindowListener(closingListener);
	    
	    File backup = new File(MainWindow.backupFile);
	    if (backup.exists()) {
	    	try {
				this.loadGame();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
	    } else {
	    	// We define, by default, 3 players
			ArrayList<Player> listOfPlayers = new ArrayList<Player>();
			listOfPlayers.add(new Player("Joueur 1"));
			listOfPlayers.add(new Player("Joueur 2"));
			listOfPlayers.add(new Player("Joueur 3"));
			
			this.setPlayers(listOfPlayers);
	    }
	    

		this.setTitle("Tarot");
		
		newDonne.addMouseListener(this);
		newGame.addMouseListener(this);
		newRound.addMouseListener(this);
		correctScore.addMouseListener(this);
		cancelLastRound.addMouseListener(this);
		
		// We can't cancel a round at the beginning
		cancelLastRound.setEnabled(false);
		
		gbc.gridx = gbc.gridy = 0; // the grid start at (0,0)
		gbc.insets = new Insets(10, 15, 0, 0); // left margin of 15 and top margin of 10.


		this.setSize(600, 400); 
		this.contentPane.setLayout(new GridBagLayout());

	
		//this.buttonPane.add(newGame);
		this.buttonPane.add(newRound);
		this.buttonPane.add(correctScore);
		this.buttonPane.add(cancelLastRound);
		
		this.newGamePane.add(newGame);
		this.newGamePane.add(newDonne);
		
		this.contentPane.add(this.newGamePane, gbc);
		
		gbc.gridy++;
		this.contentPane.add(this.buttonPane, gbc);
		
		scoreTable.setIntercellSpacing(new Dimension(20,1));
		scoreTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		this.scrollPane.getViewport().add(scoreTable);
		this.scrollPane.setPreferredSize(new Dimension(200,100));
		scrollPane.validate();
		gbc.gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.contentPane.add(this.scrollPane, gbc);
		
		this.setResizable(true); 
		this.validate();

	}
	
	public void setPlayers(ArrayList<Player> listOfPLayers) {
		this.players = new ArrayList<Player>();
		ArrayList<Integer> scoreLine = new ArrayList<Integer>();
		this.dynamicScores.setRowCount(0);
		this.dynamicScores.setColumnCount(0);

		for (Player player : listOfPLayers) {
			this.players.add(player);
			scoreLine.add(player.getScore());
			this.dynamicScores.addColumn(player.getName());
		}
		this.dynamicScores.addRow(scoreLine.toArray());
		
		this.quiDonne = 0; // initialise who give cards.
		
		this.setQuiDonne();
         
         this.nbRounds = 0;
				
		this.updateDisplay();
	}
	
	public void setQuiDonne() {
		String[] playerDonne = new String[this.players.size()];
		
		for(int i = 0; i < this.players.size(); i++) {
			playerDonne[i] = this.players.get(i).getName();
		}
		
		quiDonne = quiDonne % this.players.size();
		
		playerDonne[quiDonne] = this.players.get(quiDonne).getName()+" (donne)";
		
		int tmpCommence = (quiDonne + 1)  % this.players.size();
		playerDonne[tmpCommence] = this.players.get(tmpCommence).getName()+" (commence)";
		
		this.dynamicScores.setColumnIdentifiers(playerDonne);
         				
		this.updateDisplay();
	}
	

	public void updateScore() {
		ArrayList<Integer> scoreLine = new ArrayList<Integer>();
		
		this.setQuiDonne();
		
		for (Player player : players) {
			scoreLine.add(player.getScore());
		}
		this.dynamicScores.addRow(scoreLine.toArray());
	}
	
	
	public void updateDisplay(){
		
		scrollPane.validate();
		scrollPane.repaint();
		this.contentPane.repaint();//To clean the background with the background color defined in the class Panel
		this.validate();// To refresh the window
		scrollPane.getVerticalScrollBar().setValue(
	               scrollPane.getVerticalScrollBar().getMaximum());
		
		// We save the state of Players
		try {
			this.saveGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveGame()  throws IOException {
		FileOutputStream fos = new FileOutputStream(MainWindow.backupFile); // Link a data flux to a physical file
		ObjectOutputStream oos = new ObjectOutputStream(fos); // allow to convert an object into a data stream.
		for (Player player : players) {
			oos.writeObject(player);
		}
		oos.close();
	}
	
	public void saveStat() throws IOException {
		if (this.nbRounds != 0) {
			FileOutputStream fos = new FileOutputStream(MainWindow.statFile, true); // Link a data flux to a physical file
			String playerNames = new String("");
			String playerScores = new String("");
			
			
			
			for (Player player : players) {
				playerNames += player.getName()+"\t";
				playerScores += player.getScore()+"\t";
			}
			
			playerNames += "\n";
			playerScores += "\n";
			
			fos.write(playerNames.getBytes());
			fos.write(playerScores.getBytes());
			fos.close();
		}

	}
	
	
	public void loadGame()  throws IOException, ClassNotFoundException {
		ArrayList<Player> listOfPlayers = new ArrayList<Player>();
		FileInputStream fis = new FileInputStream(MainWindow.backupFile); 
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			Player obj = null;
			while ((obj = (Player)ois.readObject()) != null) {
				listOfPlayers.add(obj);
			}
				
		} catch (EOFException ex) { //This exception will be caught when EOF is reached
            System.out.println("List of players read successfully");
		} finally {
			ois.close();
		}
		
		this.setPlayers(listOfPlayers);
		
	}
	
	public void cancelLastRound() {
		/*
		 * This method will modify the score of each player to substract what had been added during the last round.
		 * Once done, we suppress the list of last round score, and prevent the possibility to cancel the round 
		 * that will be activated again once a new round will be done
		 */
		//TODO Ask to confirm before cancelling scores from the last round.
		
		for (int i=0 ; i < this.players.size(); i++) {
			Player player = players.get(i);
			int lastScore = - this.previousRoundIndivScores.get(i);
			player.addScore(lastScore);
		}
		
		// We prevent any new cancellation until a new round is done.
		this.previousRoundIndivScores = new ArrayList<Integer>();
		cancelLastRound.setEnabled(false);
		
		this.dynamicScores.removeRow(this.dynamicScores.getRowCount()-1);//we remove the last column
		
		this.nbRounds -= 1;
		if (this.quiDonne != 0) {
			this.quiDonne -= 1;
		} else {
			this.quiDonne = this.players.size() - 1;
		}
		
		this.setQuiDonne();
		
		scrollPane.validate();
		scrollPane.repaint();
		this.contentPane.repaint();//To clean the background with the background color defined in the class Panel
		this.validate();// To refresh the window
		scrollPane.getVerticalScrollBar().setValue(
	               scrollPane.getVerticalScrollBar().getMaximum());
	}
	
	public void modifyLastScore(){
		
		ArrayList<Integer> scoreLine = new ArrayList<Integer>();
		this.dynamicScores.removeRow(this.dynamicScores.getRowCount()-1);//we remove the last column
		for (Player player : players) {
			scoreLine.add(player.getScore());
		}
		this.dynamicScores.addRow(scoreLine.toArray());
		
		
		scrollPane.validate();
		scrollPane.repaint();
		this.contentPane.repaint();//To clean the background with the background color defined in the class Panel
		this.validate();// To refresh the window
		scrollPane.getVerticalScrollBar().setValue(
	               scrollPane.getVerticalScrollBar().getMaximum());
		
		this.updateDisplay();
	}
	
	public void incrementNbRounds() {
		this.nbRounds++;
		this.quiDonne++;

	}

	public void mousePressed(MouseEvent arg0) {

			
	}

	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==newGame){
			new NameWindow(this);

		}
		
		// If we have a round for nothing, to increment "who must beat the cards"
		if(arg0.getSource()==newDonne){
			this.quiDonne++;
			this.setQuiDonne();

		}
		
		if(arg0.getSource()==newRound){
			new RoundWindow(this);
			
		}
		
		if(arg0.getSource()==correctScore){
			new ErrorWindow(this);

		}
		
		if(arg0.getSource()==cancelLastRound && cancelLastRound.isEnabled()){
			this.cancelLastRound();
		}
	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
	
	@Override
	public void windowClosing(WindowEvent e){
		// Do nothing
	}
}



/*
 * TODO
 * 
 */
