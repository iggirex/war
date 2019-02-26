package war;

import java.util.ArrayList;
import java.util.Collections;

public class Dealer {
	
	private boolean gameHasBeenWon;
	private Player gameWinner;
	private boolean gameHasBeenTied;
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
				
				// get suit and rank
				String tempSuit = suits.get(x);
				String tempRank = ranks.get(i);
				
				// make card
				Card tempCard = new Card(tempSuit, tempRank);
				
				// add card to deck
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
				player1.addToDeck(tempCard, player1);
			} else {
				player2.addToDeck(tempCard, player2);
			}				 
		}
	}
	
	public void runTurn(Deck gameDeck, Player player1, Player player2) {
		
		System.out.println("\n\n======================== Beginning of Turn -" 
							+ turnNumber + "- =============================");
		System.out.println(">>>>>>>>>>>>>> PLAYER1 TOTAL CARDS: " + player1.getTotalAmountOfCards());
		System.out.println("\nPLAYER1 GAME DECK: " + player1.getDeck().getCards().size());
		
		if (player1.getWinDeck() != null) {
			System.out.println("PLAYER1 WIN DECK: " + player1.getWinDeck().getCards().size());
		} else {
			System.out.println("PLAYER1 WIN DECK: 0");
		}
		
		System.out.println("\n>>>>>>>>>>>>>> PLAYER2 TOTAL CARDS: " + player2.getTotalAmountOfCards());
		System.out.println("\nPLAYER2 GAME DECK: " + player2.getDeck().getCards().size());
		if (player2.getWinDeck() != null) {
			System.out.println("PLAYER2 WIN DECK: " + player2.getWinDeck().getCards().size() + "\n");
		} else {
			System.out.println("PLAYER2 WIN DECK: 0\n");
		}
		
		if (player1.getAmountOfCards() == 0) {
			player1.turnWinDeckIntoPlayingDeck();
		}
		if (player2.getAmountOfCards() == 0) {
			player2.turnWinDeckIntoPlayingDeck();
		}
		
		// compare cards from players
		compareCards(player1, player2, null);
		
		// check if anyone won
		isGameHasBeenWon(player1, player2);
		
		turnNumber++;
		
		System.out.println("===========================================================================\n");
	}
	public void compareCards(Player player1, Player player2, Deck incomingWinPile) {
		
		// check if game has been won or is a draw
		if (!isGameHasBeenWon(player1, player2)) {
			
			player1.turnWinDeckIntoPlayingDeck();
			player2.turnWinDeckIntoPlayingDeck();
		
			// create a winPile
			Deck winPile = incomingWinPile == null ? new Deck() : incomingWinPile;
			
			Card player1Card = player1.getDeck().takeACard();
			Card player2Card = player2.getDeck().takeACard();
			
			System.out.println("COMPARING PLAYER1 CARD: " + player1Card + 
								" TO PLAYER2 CARD: " + player2Card);
			
			// add players' cards to winPile		
			winPile.addACard(player1Card);
			winPile.addACard(player2Card);
			
			if(player1Card.getValue() > player2Card.getValue()) {
				player1.addToWinDeck(winPile);
				System.out.println("Player 1 just won, their game deck has: " + 
									player1.getWinDeck().getCards().size() +
									" and here is all their cards: " + player2.getTotalAmountOfCards());
			} else if(player2Card.getValue() > player1Card.getValue()) {
				player2.addToWinDeck(winPile);
				System.out.println("Player 2 just won, their game deck has: " +
									player2.getDeck().getCards().size() +
						" and here is all their cards: " + player2.getTotalAmountOfCards());
			} else {
				
				// check if game is a draw // otherwise pull another card to compare
				if (player1.getTotalAmountOfCards() == 0 && player2.getTotalAmountOfCards() == 0) {
					gameHasBeenTied = true;
				} else {
					compareCards(player1, player2, winPile);	
				}
			}
		
		} else {
			System.out.println("Game has been won  --" + gameHasBeenWon + 
								"--  but game won't end somehow, winner is: " + gameWinner);
		}
				
	}

	public boolean isGameHasBeenWon(Player player1, Player player2) {
		
		if (player1.getTotalAmountOfCards() == 0) {
			gameHasBeenWon(true);
			gameWinner = player2;
		} else if (player2.getTotalAmountOfCards() == 0) {
			gameHasBeenWon(true);
			gameWinner = player1;
		}
		return gameHasBeenWon;
	}

	public void gameHasBeenWon(boolean gameHasBeenWon) {
		this.gameHasBeenWon = gameHasBeenWon;
	}

	public boolean hasGameBeenWon() {
		return gameHasBeenWon;
	}

	public Player getGameWinner() {
		return gameWinner;
	}

	public boolean isGameHasBeenWon() {
		return gameHasBeenWon;
	}

	public boolean isGameHasBeenTied() {
		return gameHasBeenTied;
	}
	
}
