package graphics;

import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import game.Player;
import game.Round;

public class RoundWindow extends Window implements ActionListener, MouseListener  {


	/* All the buttons and check boxes used in the window */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton rule = new JButton("Regles");
	
	private static JLabel typePartie = new JLabel("Type de la partie :");
	private static String[] partyList = {"Prise", "Garde", "Garde Sans", "Garde Contre"};
	private JComboBox<String> choicepartyType = new JComboBox<String>(partyList);

	private static JLabel quiPrend=new JLabel("Qui prend ?");
	private ArrayList<JCheckBox> joueursQuiPrend = new ArrayList<JCheckBox>();

	private static JLabel avecQui=new JLabel("Avec qui ?");
	private ArrayList<JCheckBox> joueursPartenaires = new ArrayList<JCheckBox>();

	private static JLabel finalement= new JLabel("Finalement :");
	private static String[] faiteList = {"Gagnee","Perdue"};
	private JComboBox<String> faiteOuNon = new JComboBox<String>(faiteList);
	private JTextField ecart = new JTextField("... de combien?");
	
	private JCheckBox chelemAnounced = new JCheckBox("Annonce Chelem");
	private ArrayList<JCheckBox> playersChelem = new ArrayList<JCheckBox>();
	private JCheckBox chelemRealise = new JCheckBox("Chelem Realise (Non)");
		
	private static String[] listePetitAuBout = {"Attaquants","Defenseurs"};
	private JCheckBox petitAuBout = new JCheckBox("Petit au bout");
	private JComboBox<String> petitAuBoutAQui = new JComboBox<String>(listePetitAuBout);

	private static int[] nbCards_3p = {0,13,15,18};
	private static int[] nbCards_4p = {0,10,13,15};
	private static int[] nbCards_5p = {0,8,10,13};
	private int[] nbCardsPoigne;
	private String[] listePoigne = {"Aucune","Simple","Double","Triple"};
	private JCheckBox poigne;
	private ArrayList<JComboBox<String>> playersPoignee= new ArrayList<JComboBox<String>>();
	
	
	private static String[] listeMisere = {"Aucune","Simple","Double"};
	private JCheckBox misere = new JCheckBox("Misere");
	private ArrayList<JComboBox<String>> playersMisere = new ArrayList<JComboBox<String>>();
	
	private JButton calculer = new JButton("Calculer");
	private JButton annuler = new JButton("Annuler");

	private boolean PremiereFoisEcart = true;

	private MainWindow F = null;
	private Player joueurQuiPrend = null;
	private Player joueurAssocie = null;
	private Player playerChelem = null;
	
	public RoundWindow(MainWindow currentMainWindow) {
		super();
		this.F=currentMainWindow;
		this.setSize(1024, 600);
		
		String[] playerDonne = new String[this.F.players.size()];
		
		for(int i = 0; i < this.F.players.size(); i++) {
			playerDonne[i] = this.F.players.get(i).getName();
		}
		
		this.F.quiDonne = this.F.quiDonne % this.F.players.size();
		
		playerDonne[this.F.quiDonne] = this.F.players.get(this.F.quiDonne).getName()+" (donne)";
		
		int tmpCommence = (this.F.quiDonne + 1)  % this.F.players.size();
		playerDonne[tmpCommence] = this.F.players.get(tmpCommence).getName()+" (commence)";
		
		for (String playerName : playerDonne) {
			joueursQuiPrend.add(new JCheckBox(playerName));
		}
		
		for (Player player : this.F.players) {
			joueursPartenaires.add(new JCheckBox(player.getName()));
			playersChelem.add(new JCheckBox(player.getName()));
			playersMisere.add(new JComboBox<String>(listeMisere));			
		}

		for (JComboBox<String> playerMisere : playersMisere) {
			playerMisere.setEnabled(false);
		}
		
		for (JCheckBox playerChelem : playersChelem) {
			playerChelem.setEnabled(false);
		}
		
		int nbPlayers = this.F.players.size();
		if (nbPlayers == 3) {
			nbCardsPoigne = nbCards_3p;
		} else if (nbPlayers == 4) {
			nbCardsPoigne = nbCards_4p;
		} else if (nbPlayers == 5) {
			nbCardsPoigne = nbCards_5p;
		}
		
		poigne = new JCheckBox("Poigne (>"+String.valueOf(nbCardsPoigne[1])+")");
		
		for (int i=0 ; i< 4 ; i++) {
			listePoigne[i] = listePoigne[i]+" ("+String.valueOf(nbCardsPoigne[i])+")";
		}
		
		for (int i=0 ; i<nbPlayers ; i++) {
			JComboBox<String> tmpBox = new JComboBox<String>(listePoigne);
			
			playersPoignee.add(tmpBox);
			tmpBox.setEnabled(false);
		}
		
		petitAuBoutAQui.setEnabled(false);
		
		
		// We define here the window, and what is inside
		this.setTitle("Donne numero : "+(F.nbRounds+1)); 
		this.setLocationRelativeTo(null); 

		this.setLayout(new GridBagLayout());
		GridBagConstraints c= new GridBagConstraints();
		c.insets=new Insets(5, 10, 10, 10);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=c.gridy=0;
		c.gridheight=c.gridwidth=1;

		this.add(typePartie,c);
		
		c.gridx++;
		this.add(choicepartyType, c);
		
		c.gridx++;
		this.add(rule,c);
		
		c.gridy++;
		c.gridx=0;
		this.add(quiPrend,c);
		
		for (JCheckBox joueurQuiPrend : joueursQuiPrend) {
			c.gridy++;
			this.add(joueurQuiPrend,c);
		}

		
		if (this.F.players.size()==5) {
			c.gridx++;
			c.gridy=1;
			// We do not display partners if there is 3 or 4 players
			this.add(avecQui,c);
	
			for (JCheckBox partenaire : joueursPartenaires) {
				c.gridy++;
				this.add(partenaire,c);
			}
		}

		c.gridx++;
		c.gridy=1;
		this.add(poigne,c);
		
		for (JComboBox<String> playerPoigne : playersPoignee) {
			c.gridy++;
			this.add(playerPoigne,c);
		}
		
		c.gridx++;
		c.gridy=1;
		this.add(misere,c);
		
		for (JComboBox<String> playerMisere : playersMisere) {
			c.gridy++;
			this.add(playerMisere,c);
		}
		
		
		c.gridx++;
		c.gridy=1;
		this.add(chelemAnounced, c);
		
		for (JCheckBox playerChelem : playersChelem) {
			c.gridy++;
			this.add(playerChelem,c);
		}
		
		c.gridy++;
		c.gridx=0;
		this.add(finalement,c);

		c.gridy++;
		int yidx = c.gridy;//y Position for the finish
		this.add(faiteOuNon,c);

		c.gridy++;
		this.add(ecart,c);

		c.gridy = yidx;
		c.gridx++;
		this.add(petitAuBout,c);
		
		c.gridy++;
		this.add(petitAuBoutAQui,c);
		
		c.gridx++;
		c.gridy = yidx;
		this.add(chelemRealise,c);
		
		c.gridx=0;
		c.gridy = yidx + 2;
		c.gridheight=c.gridwidth=2;
		this.add(calculer,c);

		c.gridx++;
		c.gridx++;
		this.add(annuler,c);

		this.setResizable(true); 
		this.setVisible(true); 

		annuler.addMouseListener(this);
		calculer.addMouseListener(this);
		rule.addMouseListener(this);

		faiteOuNon.addMouseListener(this);
		ecart.addMouseListener(this);
		misere.addMouseListener(this);
		poigne.addMouseListener(this);
		petitAuBout.addMouseListener(this);
		chelemAnounced.addMouseListener(this);
		chelemRealise.addMouseListener(this);
		
		for (JComboBox<String> playerPoigne : playersPoignee) {
			playerPoigne.addMouseListener(this);
		}
		
		for (JCheckBox leader : joueursQuiPrend) {
			leader.addMouseListener(this);
		}

		for (JCheckBox partenaire : joueursPartenaires) {
			partenaire.addMouseListener(this);
		}
		
		for (JCheckBox playerChelem : playersChelem) {
			playerChelem.addMouseListener(this);
		}
	}

	public void mousePressed(MouseEvent arg0) {
		if(arg0.getSource()==ecart&&PremiereFoisEcart){
			ecart.setText("");
			PremiereFoisEcart=false;
		}

	}

	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==calculer){
			boolean roundOK = true;
			
			// We set the leader and partner
			for (int i=0 ; i < this.F.players.size() ; i++) {
				
				if (joueursQuiPrend.get(i).isSelected()) {
					this.joueurQuiPrend = this.F.players.get(i);
				}
				if (joueursPartenaires.get(i).isSelected()) {
					this.joueurAssocie = this.F.players.get(i);
				}
				
			}
			if (this.joueurQuiPrend == null) {
				roundOK = false;
				JOptionPane.showMessageDialog(null, "Quelqu'un doit prendre.", "Erreur : Leader not set?", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (this.F.players.size() == 5 && this.joueurAssocie == null) {
				roundOK = false;
				JOptionPane.showMessageDialog(null, "Qui est le partenaire?", "Erreur : Partner not set?", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			
			// The sign, plus or minus, is set with a boolean elsewhere.
			int remainingPoints;
			try{
				remainingPoints = Math.abs(Integer.parseInt(ecart.getText()));
			}
			catch(Exception e){
				roundOK = false;
				JOptionPane.showMessageDialog(null, "Impossible to calculate the score, missing the 'ecart'", "Erreur : Ecart not set?", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			boolean won;
			if(faiteOuNon.getSelectedItem().toString()=="Gagnee"){
				won = true;
			} else if (faiteOuNon.getSelectedItem().toString()=="Perdue"){
				won = false;
			} else {
				won = false;
				roundOK = false;
				JOptionPane.showMessageDialog(null, "Impossible to tell if the round was won or not", "Erreur : faiteOuNon", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// Announces
			
			boolean isPoigne = poigne.isSelected();
			ArrayList<String> valuesPoigne = new ArrayList<String>();
			if (isPoigne) {
				int totalCount = 0;
				for (JComboBox<String> poigneValue : playersPoignee) {
					valuesPoigne.add(poigneValue.getSelectedItem().toString());
					totalCount += nbCardsPoigne[poigneValue.getSelectedIndex()];
				}
				
				if (totalCount >= 22) {
					roundOK = false;
					JOptionPane.showMessageDialog(null, "La somme des cartes des poignees ne peut exceder le nombre total d'atouts", "Erreur : Poignees incorrectes", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			// Chelem
			boolean isChelemAnounced = chelemAnounced.isSelected();;
			boolean isChelemRealised = chelemRealise.isSelected();;
			if (isChelemAnounced || isChelemRealised) {
				if (playerChelem == null) {
					roundOK = false;
					JOptionPane.showMessageDialog(null, "The player for the Chelem must be set.", "Erreur : playerChelem missing", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			
			String tmpPetit = "";
			int valuePetitAuBout = 0;
			if (petitAuBout.isSelected()) {
				tmpPetit = petitAuBoutAQui.getSelectedItem().toString();
				
				// We attribute an int value, so that, the language doesn't have an influence on the value
				if (tmpPetit == listePetitAuBout[0]) {
					valuePetitAuBout = 1;
				} else if (tmpPetit == listePetitAuBout[1]) {
					valuePetitAuBout = -1;
				} else {
					valuePetitAuBout = 0;
				}
			}
			
			boolean isMisere = misere.isSelected();
			ArrayList<String> valuesMisere = new ArrayList<String>();
			if (isMisere) {
				for (JComboBox<String> misereValue : playersMisere) {
					valuesMisere.add(misereValue.getSelectedItem().toString());
				}
			}
			
			String partyType = choicepartyType.getSelectedItem().toString();

			
			if (roundOK) {
				Round thisRound = new Round(F.players, partyType, 
						joueurQuiPrend, joueurAssocie, won, 
						remainingPoints, isPoigne, valuesPoigne, 
						isChelemAnounced, isChelemRealised, this.playerChelem, valuePetitAuBout,
						isMisere, valuesMisere);
				
				F.previousRoundIndivScores = thisRound.attributePoints();
				
				// We allow to cancel the last round (each time we cancel, they are put to false)
				F.cancelLastRound.setEnabled(true);
				
				F.incrementNbRounds();
								
				F.updateScore();
				F.updateDisplay();
				
				this.dispose();
			}
				
		}
		
		if(arg0.getSource()==annuler){
			this.dispose();
		}
		
		if(arg0.getSource()==rule){
			new RuleWindow(this.F);
		}
		
		
		if(this.joueursPartenaires.contains(arg0.getSource())){

			if(((AbstractButton) arg0.getSource()).isSelected()){
				for (JCheckBox partner : joueursPartenaires) {
					partner.setEnabled(false);
				}

				((AbstractButton) arg0.getSource()).setEnabled(true);
				
				int partnerIndex = joueursPartenaires.indexOf(arg0.getSource());
				
				this.joueurAssocie = F.players.get(partnerIndex);
				

			}else{
				if (((AbstractButton) arg0.getSource()).isEnabled()) {
					for (JCheckBox partner : joueursPartenaires) {
						partner.setEnabled(true);
						partner.setSelected(false);
						
					}
				}
				
				this.joueurAssocie = null;
			}
		}
		
		// We set the player that took the lead
		if(this.joueursQuiPrend.contains(arg0.getSource())){

			if(((AbstractButton) arg0.getSource()).isSelected()){
				for (JCheckBox leader : joueursQuiPrend) {
					leader.setEnabled(false);
				}
				((AbstractButton) arg0.getSource()).setEnabled(true);
				
				int leaderIndex = joueursQuiPrend.indexOf(arg0.getSource());
				
				this.joueurQuiPrend = F.players.get(leaderIndex);
				// With 5 players, the leader has the choice to play alone (with 3 and 4, he hasn't
				if (F.players.size() == 5) {
					joueursPartenaires.get(leaderIndex).setText("Seul");
				}

			}else{
				if (((AbstractButton) arg0.getSource()).isEnabled()) {
					for (JCheckBox leader : joueursQuiPrend) {
						leader.setEnabled(true);
						leader.setSelected(false);
					}
					
					this.joueurQuiPrend = null;
					
					if (F.players.size() == 5) {
						for (int i = 0 ; i < joueursPartenaires.size(); i++) {
							JCheckBox partner = joueursPartenaires.get(i);
							String partnerName = this.F.players.get(i).getName();
							partner.setText(partnerName);
						}
					}
				
				}
			}
		}
		
		if(this.playersChelem.contains(arg0.getSource())){

			if(((AbstractButton) arg0.getSource()).isSelected()){
				for (JCheckBox player : playersChelem) {
					player.setEnabled(false);
				}

				((AbstractButton) arg0.getSource()).setEnabled(true);
				
				int playerIndex = playersChelem.indexOf(arg0.getSource());
				
				this.playerChelem = F.players.get(playerIndex);
				

			}else{
				if (((AbstractButton) arg0.getSource()).isEnabled()) {
					for (JCheckBox player : playersChelem) {
						player.setEnabled(true);
						player.setSelected(false);
						
					}
				}
				
				this.playerChelem = null;
			}
		}
		
		if(arg0.getSource()==misere){
			for (JComboBox<String> playerMisere : playersMisere) {
				playerMisere.setEnabled(misere.isSelected()==true);
			}
		}
		
		if(arg0.getSource()==petitAuBout){
			petitAuBoutAQui.setEnabled(petitAuBout.isSelected()==true);
		}
		
		if(arg0.getSource()==poigne){
			for (JComboBox<String> playerPoigne : playersPoignee) {
				playerPoigne.setEnabled(poigne.isSelected()==true);
			}
		}
		
		// Chelem must be allowed if it is announced, or if it is realized even if not announced
		if (arg0.getSource() == chelemAnounced) {
			if (((AbstractButton) arg0.getSource()).isSelected()) {
				for(JCheckBox playerChelem : playersChelem) {
					playerChelem.setEnabled(true);
				}
			} else if (chelemRealise.isSelected() == false) {
				for(JCheckBox playerChelem : playersChelem) {
					playerChelem.setEnabled(false);
				}
			}
		} 
		
		if (arg0.getSource() == chelemRealise) {
			if (((AbstractButton) arg0.getSource()).isSelected()) {
				chelemRealise.setText("Chelem Realise (Oui)");
				for(JCheckBox playerChelem : playersChelem) {
					playerChelem.setEnabled(true);
				}
			} else {
				chelemRealise.setText("Chelem Realise (Non)");
				if (chelemAnounced.isSelected() == false) {
					for(JCheckBox playerChelem : playersChelem) {
						playerChelem.setEnabled(false);
					}
				}
			}
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


/*
 * TODO
 * aucune: mettre inferieur au nombre de carte
 */

}

