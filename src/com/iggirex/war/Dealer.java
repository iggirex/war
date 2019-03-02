package com.iggirex.war;

import java.util.ArrayList;
import java.util.Collections;

public class Dealer {
	
	private boolean gameHasBeenWon;
	private Player gameWinner;
	public int turnNumber = 0;

	public Deck makeInitialDeck(){
		
		Deck initialDeck = new Deck();
		
		ArrayList<String> ranks = new ArrayList<>();
		ArrayList<String> suits = new ArrayList<>();
		
		for( int i=2; i<11; i++) {
			ranks.add(Integer.toString(i));
		}
		
		ranks.add("j");
		ranks.add("q");
		ranks.add("k");
		ranks.add("a");
		suits.add("heart");
		suits.add("diamond");
		suits.add("club");
		suits.add("spade");
		
		for( int i=0; i<ranks.size(); i++) {
			for( int x=0; x<suits.size(); x++) {
				
				String tempSuit = suits.get(x);
				String tempRank = ranks.get(i);
				Card tempCard = new Card(tempSuit, tempRank);
				
				initialDeck.addACard(tempCard);
			}	
		}
		
		ArrayList<Card> tempDeckCards = initialDeck.getCards();
		
		Collections.shuffle(tempDeckCards);
		initialDeck.setCards(tempDeckCards);
		
		return initialDeck;
	}
	
	public void deal(ArrayList<Card> theDeck, Player player1, Player player2) {
		
		for(int i=0; i<theDeck.size(); i++) {
			Card tempCard = theDeck.get(i);
			
			if(i % 2 == 0) {
				player1.addToDeck(tempCard);
			} else {
				player2.addToDeck(tempCard);
			}
		}
	}
	
	public void runGame(Deck gameDeck, Player player1, Player player2) {
		
		System.out.println("\n======================== Beginning of Turn " 
							+ turnNumber + " =============================");
		
		Turn turn = new Turn();
		gameHasBeenWon = turn.runTurns(gameDeck, player1, player2);
		turnNumber++;
		
		System.out.println("=================================================="
							+ "=========================\n");

		gameWinner = turn.getWinner();
	}

	public boolean hasGameBeenWon() {
		return gameHasBeenWon;
	}

	public Player getGameWinner() {
		return gameWinner;
	}

	public int getTurnNumber() {
		return turnNumber;
	}
	
}
