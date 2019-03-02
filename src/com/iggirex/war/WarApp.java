package com.iggirex.war;

public class WarApp {

	public static void main(String[] args) {
		
		Game game = new Game();		
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		Dealer theDealer = new Dealer();
		Deck gameDeck = theDealer.makeInitialDeck();
		
		theDealer.deal(gameDeck.getCards(), player1, player2);
		
		game.play(player1, player2, theDealer, gameDeck);
	}
}
