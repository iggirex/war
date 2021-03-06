package com.iggirex.war;

import java.util.Collections;

public class Player {
	
	private String name;
	private Deck deck;
	private Deck winDeck;
	
	public Player(String name) {
		this.name = name;
	}
	
	public void addToDeck(Card tempCard) {
		
		if(deck == null) {
			deck = new Deck();
		}
		deck.addACard(tempCard);
	}
	
	public void addToWinDeck(Deck wonCards) {
		
		for(Card tempCard : wonCards.getCards()) {
		
			if(winDeck == null) {
				winDeck = new Deck();
			}
			winDeck.addACard(tempCard);
		}
	}
	
	public boolean turnWinDeckIntoPlayingDeck() {
		
//		System.out.println("IIIIIIIIIIIIIIIII inside turnWinDeckToGameDeck for: " + name + " and they have " 
//					+ getAmountOfCards() + " cards left  and this is winDeck: " + winDeck);
//		
//		System.out.println("\n======== " + (winDeck != null && getAmountOfCards() > 0) + " =========\n");
//		
		if(winDeck != null && getAmountOfCards() == 0) {
						
			Collections.shuffle(winDeck.getCards());
			
//			System.out.println(" this is deck: " + deck);
			
			deck.setCards(winDeck.getCards());
//			System.out.println(" this is deck AFTER ADDING WINCARDS!!!! : " + deck);
			
			winDeck = null;
			return false;
		} else {
			// opposite player wins
			return true;
		}
		
	}
	
	public Card takeTopCard() {
		Card topCard  = deck.takeACard();
		return topCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public int getAmountOfCards() {
		return deck.getCards().size();
	}
	
	// RF
	public int getAmountOfWinCards() {
		if (winDeck == null) {
			return 0;
		} else if(winDeck.getCards().size() > 0) {
			return winDeck.getCards().size();
		}
		return 0;
	}

	public Deck getWinDeck() {
		return winDeck;
	}

	public int getTotalAmountOfCards() {
				
		if (winDeck != null && winDeck.getCards().size() > 0) {
			return deck.getCards().size() + winDeck.getCards().size();	
		}
		return getAmountOfCards();
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", deck=" + deck + ", winDeck=" + winDeck + "]";
	}
	
	public String deckToString() {
		return "name = " + name + "  >>>> Deck = " + deck;
	}
	
	public String winDeckToString() {
		return "name = " + name + "  >>>> Deck = " + winDeck;
	}
	
}
