package game;

import game.Player;
import java.util.ArrayList;
/**
 * Class that define a round. 
 * 
 */
public class Round {
	private ArrayList<Player> players=new ArrayList<Player>();
	private int remainingPoints;
	private boolean won;
	private Player leader;
	private Player partner;
	private String partyType;
	
	
	private boolean isChelemAnounced;
	private boolean isChelemRealised;
	private Player playerChelem;

	private int petitAuBout;
	
	private boolean isMisere;
	private ArrayList<String> valuesMisere;
	
	private boolean isPoigne;
	private ArrayList<String> valuesPoigne;
	
	
	private ArrayList<Player> attackers = new ArrayList<Player>();
	private ArrayList<Player> defenders = new ArrayList<Player>();
	private int nbDefenders;
	

	public Round(ArrayList<Player> players, String partyType, Player leader, Player partner, 
			boolean won, int remainingPoints, boolean isPoigne, ArrayList<String> valuesPoigne, 
			boolean isChelemAnounced, boolean isChelemRealised, Player playerChelem, 
			int petitAuBout, boolean isMisere, ArrayList<String> valuesMisere) {
		super();
		this.players = players;
		this.partyType = partyType;
		
		this.leader = leader;
		if (partner == null) {
			this.partner = leader;//he is his own partner
		} else {
			this.partner = partner;
		}
		
		this.won = won;
		this.remainingPoints = remainingPoints;
		this.isPoigne = isPoigne;
		this.valuesPoigne = valuesPoigne;
		this.isChelemAnounced = isChelemAnounced;
		this.isChelemRealised = isChelemRealised;
		this.playerChelem = playerChelem;
		this.petitAuBout = petitAuBout;
		this.isMisere = isMisere;
		this.valuesMisere = valuesMisere;
		
		// We define attackers and defenders
		attackers.add(leader);
		if (partner != leader) {
			attackers.add(partner);
		}
		
		for (Player player : players) {
			if (!attackers.contains(player)) {
				defenders.add(player);
			}
		}
		this.nbDefenders = defenders.size();
		
	}
	
	public ArrayList<Integer> attributePoints() {
		/*
		 * Calculate the points and attribute them to the players. 
		 * The function return the list of points that were attributed to each player, 
		 * allowing to cancel this attribution in case of error
		 */
		
		int absoluteAttackerScore = 25;
		absoluteAttackerScore += this.remainingPoints;
		
		int indivDefender;
		if (won) {
			// If attacker win, defender loose
			indivDefender = - absoluteAttackerScore;
		} else {
			indivDefender = absoluteAttackerScore;
		}
		
		if (this.petitAuBout == 1) {
			indivDefender -= 10;
		} else if (this.petitAuBout == -1) {
			indivDefender += 10;
		}
		
		if (this.isMisere) {
			for (int i = 0 ; i < players.size(); i++) {
				String valueMisere = valuesMisere.get(i);
				Player player = players.get(i);
				
				int scoreMisere = 0;
				if (valueMisere == "Simple") {
					scoreMisere = 10;
				} else if (valueMisere == "Double") {
					scoreMisere = 20;
				}
				
				if (scoreMisere != 0) {
					if (attackers.contains(player)) {
						indivDefender -= scoreMisere;
					} else {
						indivDefender += scoreMisere;
					}
				}
			}
		}
		
		// We get the individual defense score
		if (partyType == "Garde") {
			indivDefender = indivDefender * 2;
		} else if (partyType == "Garde Sans") {
			indivDefender = indivDefender * 4;
		} else if (partyType == "Garde Contre") {
			indivDefender = indivDefender * 6;
		}
		
		int pointsPoignee = 0;
		if (isPoigne) {
			for (String poigne : valuesPoigne) {
				if (poigne.startsWith("Simple")) {
					pointsPoignee += 20;
				} else if (poigne.startsWith("Double")) {
					pointsPoignee += 30;
				} else if (poigne.startsWith("Triple")) {
					pointsPoignee += 40;
				}
			}
		}
		
		
		
		if (won) {
			indivDefender -= pointsPoignee;
		} else {
			indivDefender += pointsPoignee;
		}
		
		
		// We apply the modification due to chelem
		if (this.isChelemAnounced || this.isChelemRealised) {
			int chelemByDefender;
			if (defenders.contains(this.playerChelem)) {
				chelemByDefender = 1;
			} else {
				chelemByDefender = -1;
			}
			if (this.isChelemAnounced && this.isChelemRealised) {
				indivDefender += chelemByDefender * 400;
			} else if (!this.isChelemAnounced && this.isChelemRealised) {
				indivDefender += chelemByDefender * 200;
			} else if (this.isChelemAnounced && !this.isChelemRealised) {
				indivDefender -= chelemByDefender * 200;
			}
		}
		
		
		int totalAttackers = - indivDefender * nbDefenders;

		
		ArrayList<Integer> indivRoundScore = new ArrayList<Integer>();
		for (Player player : players) {
			int roundScore = 0;
			if (defenders.contains(player)) {
				roundScore = indivDefender;
			} else if (player == leader) {
				if (this.leader == this.partner) {
					/* We only test once per player, so we check if there 
					 * is a partner or not directly in this part.
					 */
					roundScore = totalAttackers;
					
				} else {
					roundScore = (int) ((2./3.) * totalAttackers);
				}
			} else if (player == partner) {
				roundScore = (int) ((1./3.) * totalAttackers);
			}
			player.addScore(roundScore);
			indivRoundScore.add(roundScore);
		}
		return indivRoundScore;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Round :\n" +
				"players=" + players + "\n" + 
				"remainingPoints=" + remainingPoints + "\n" + 
				"attackers=" + attackers + "\n" + 
				"defenders=" + defenders + "\n" + 
				"nbDefenders=" + nbDefenders + "\n\n" + 
				"partyType=" + partyType + "\n" + 
				"leader=" + leader + "\n" + 
				"partner=" + partner + "\n" + 
				"won=" + won + "\n\n" + 
				"isChelemAnounced=" + isChelemAnounced + "\n" + 
				"isChelemRealised=" + isChelemRealised + "\n" + 
				"playerChelem=" + playerChelem + "\n\n" + 
				"petitAuBout=" + petitAuBout + "\n\n" + 
				"isMisere=" + isMisere + "\n" + 
				"valuesMisere=" + valuesMisere + "\n\n" +  
				"isPoigne=" + isPoigne + "\n" + 
				"valuesPoigne=" + valuesPoigne + "\n";
	}
	

}
