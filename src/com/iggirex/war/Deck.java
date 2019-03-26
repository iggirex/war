package com.iggirex.war;

import java.util.ArrayList;

public class Deck {

	private ArrayList<Card> cards = new ArrayList<>();
	
	public void addACard(Card theCard) {
		cards.add(theCard);
	}
	
	public Card takeACard() {
		Card theCard = cards.get(0);
		cards.remove(0);
		return theCard;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return "Deck [cards=" + cards + "]";
	}
	
}
