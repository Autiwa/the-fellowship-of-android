package graphics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;

public class RuleWindow extends Window implements ActionListener, MouseListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* This window will contain all the buttons and check boxes 
	 * needed to implement the score and the game */

	// We define the main window, to be able to modify parameters (in particular the list of players)
	private MainWindow F = null;
	
	public JTextArea rules= null;
	
	public String ruleText= new String("");
	


	public RuleWindow(MainWindow currentMainWindow){

		super();
		this.F=currentMainWindow;
		
		ruleText += "Contrat : \n" +
				"Avec 0, 1, 2 ou 3 bouts, il faut faire 56, 51, 41 ou 36 points.\n\n";
		
		ruleText += "Chien : \n" +
				"On ne peut mettre ni roi ni bouts au chien. \n" +
				"On peut mettre des atouts mais il faut les montrer a la defense.\n\n";
		
		ruleText += "Poignee : ";
		
		int nbPlayers = this.F.players.size();
		if (nbPlayers == 3) {
			ruleText += "13 ; 15 ; 18\n";
		} else if (nbPlayers == 4) {
			ruleText += "10 ; 13 ; 15\n";
		} else {
			ruleText += "8 ; 10 ; 13\n";
		}
		
		ruleText += "La poignee se montre triee et juste avant de jouer sa premiere carte\n" +
				"Il est possible d'annoncer plusieurs poignees. \n" +
				"Elles compteront pour celui qui remporte la partie (attaque ou defense)\n\n";
		
		ruleText += "Petit au bout : \n" +
				"Qu'on perde ou qu'on gagne, les points du petit au bout vont \n" +
				"a celui qui remporte le dernier pli (attackers ou defenders).\n\n";
		
		ruleText += "Chelem : \n" +
				"La personne ayant fait l'annonce prend logiquement la main.\n" +
				"Un chelem non annonce compte moins, mais compte quand meme.\n" +
				"En cas de chelem, l'excuse est jouee a la fin et remporte le pli.";
		
		rules = new JTextArea(ruleText);
		
		rules.setEditable(false);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c= new GridBagConstraints();
		c.insets=new Insets(5, 10, 10, 10);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=c.gridy=0;
		c.gridheight=c.gridwidth=1;
		
		this.add(rules);
		
		this.setVisible(true); 
		this.setResizable(true); 
	}
	
	public void mousePressed(MouseEvent arg0) {

		
	}

	public void mouseClicked(MouseEvent arg0) {
		
		
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
