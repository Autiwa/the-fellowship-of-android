package main;
import graphics.MainWindow;
import graphics.WindowList;

import game.Player;
import game.Round;

import java.util.ArrayList;
import java.util.Arrays;


public class Main {
	
	public static final WindowList windowList = new WindowList();
	
	public static final MainWindow mainWindow = new MainWindow();;
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Some unitary tests of the 'Round' class. (using the example of wikipedia about tarot)
		//testRoundScore();
						

	}
	
	@SuppressWarnings("unused")
	private static void testRoundScore() {
		// The output
		ArrayList<Integer> expectedScore = null;
		
		// declaring inputs
		ArrayList<Player> players = new ArrayList<Player>();
		
		String partyType = null;
		Player leader = null;
		Player partner = null;
		boolean won = false;
		int remainingPoints = 0;
		boolean isPoigne = false;
		ArrayList<String> valuesPoigne = new ArrayList<String>();
		boolean isChelemAnounced = false;
		boolean isChelemRealised = false;
		Player playerChelem = null;
		int valuePetitAuBout = 0;
		boolean isMisere = false;
		ArrayList<String> valuesMisere = null;
		
		
		
		// Tests avec 4 joueurs
		players.add(new Player("Joueur 1"));
		players.add(new Player("Joueur 2"));
		players.add(new Player("Joueur 3"));
		players.add(new Player("Joueur 4"));
		
		for (Player player : players) {
			valuesPoigne.add("");
		}
		
		//Global initialisations
		leader = players.get(0);
		partner = null;
		
		// TEST 1
		System.out.println("TEST 1 :");
		partyType = "Garde";
		won = true;
		isPoigne = true;
		valuesPoigne.set(0, "Simple (10)");
		remainingPoints = 8;
		isChelemAnounced = false;
		isChelemRealised = false;
		playerChelem = null;
		valuePetitAuBout = 1;
		isMisere = false;
		valuesMisere = null;
		
		expectedScore = new ArrayList<Integer>(Arrays.asList(318, -106, -106, -106));
		
		testOneRound(players, partyType, leader, partner, won, remainingPoints, isPoigne, valuesPoigne, 
				isChelemAnounced, isChelemRealised, playerChelem, 
				valuePetitAuBout, isMisere, valuesMisere, expectedScore);
		
		// TEST 2
		System.out.println("TEST 2 :");
		partyType = "Garde Sans";
		won = true;
		isPoigne = false;
		valuesPoigne.set(0, "");
		remainingPoints = 4;
		isChelemAnounced = false;
		isChelemRealised = false;
		playerChelem = null;
		valuePetitAuBout = -1;
		isMisere = false;
		valuesMisere = null;
		
		expectedScore = new ArrayList<Integer>(Arrays.asList(228, -76, -76, -76));
		
		testOneRound(players, partyType, leader, partner, won, remainingPoints, isPoigne, valuesPoigne, 
				isChelemAnounced, isChelemRealised, playerChelem, 
				valuePetitAuBout, isMisere, valuesMisere, expectedScore);
		
		// TEST 3
		System.out.println("TEST 3 :");
		partyType = "Petite";
		won = false;
		isPoigne=true;
		valuesPoigne.set(0, "Simple (10)");
		remainingPoints = 7;
		isChelemAnounced = false;
		isChelemRealised = false;
		playerChelem = null;
		valuePetitAuBout = 1;
		isMisere = false;
		valuesMisere = null;
		
		expectedScore = new ArrayList<Integer>(Arrays.asList(-126, 42, 42, 42));
		
		testOneRound(players, partyType, leader, partner, won, remainingPoints, isPoigne, valuesPoigne, 
				isChelemAnounced, isChelemRealised, playerChelem, 
				valuePetitAuBout, isMisere, valuesMisere, expectedScore);
		
		// TEST 4
		System.out.println("TEST 4 :");
		partyType = "Garde";
		won = true;
		isPoigne = true;
		valuesPoigne.set(0, "Simple (10)");
		remainingPoints = 11;
		isChelemAnounced = false;
		isChelemRealised = false;
		playerChelem = null;
		valuePetitAuBout = 0;
		isMisere = false;
		valuesMisere = null;
		
		expectedScore = new ArrayList<Integer>(Arrays.asList(276, -92, -92, -92));
		
		testOneRound(players, partyType, leader, partner, won, remainingPoints, isPoigne, valuesPoigne, 
				isChelemAnounced, isChelemRealised, playerChelem, 
				valuePetitAuBout, isMisere, valuesMisere, expectedScore);
		
		// TEST 5
		System.out.println("TEST 5 :");
		partyType = "Garde";
		won = true;
		isPoigne = true;
		valuesPoigne.set(0, "Simple (10)");
		remainingPoints = 46;
		isChelemAnounced = true;
		isChelemRealised = true;
		playerChelem = leader;
		valuePetitAuBout = 1;
		isMisere = false;
		valuesMisere = null;
		
		expectedScore = new ArrayList<Integer>(Arrays.asList(1746, -582, -582, -582));
		
		testOneRound(players, partyType, leader, partner, won, remainingPoints, isPoigne, valuesPoigne, 
				isChelemAnounced, isChelemRealised, playerChelem, 
				valuePetitAuBout, isMisere, valuesMisere, expectedScore);
	}
	
	private static void testOneRound(ArrayList<Player> players, String partyType, Player leader, Player partner, 
			boolean won, int remainingPoints, boolean isPoigne, ArrayList<String> valuesPoigne, 
			boolean isChelemAnounced, boolean isChelemRealised, Player playerChelem, 
			int petitAuBout, boolean isMisere, ArrayList<String> valuesMisere, 
			ArrayList<Integer> expectedScore) {
		
		// The output
		ArrayList<Integer> outputScores;
		
		Round thisRound = new Round(players, partyType, 
				leader, partner, won, 
				remainingPoints, isPoigne, valuesPoigne, 
				isChelemAnounced, isChelemRealised, playerChelem, petitAuBout,
				isMisere, valuesMisere);
		
		outputScores = thisRound.attributePoints();
		
		boolean isError = false;
		for (int i=0 ; i < outputScores.size(); i++) {
			if (!outputScores.get(i).equals(expectedScore.get(i))) {
				isError = true;
			}
		}
		
		if (isError) {
			System.out.println("output : "+outputScores.toString());
			System.out.println("expected : "+expectedScore.toString());
		} else {
			System.out.println("OK");
		}
	}
	
	
}

/* TODO list

 * Define unitary tests of the roundScore for 3 and 5 players also?
 */