package com.iggirex.war;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="turn")
public class Turn {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="player1Score")
	private int player1Score;
	
	@Column(name="player1GameDeck")
	private int player1GameDeck;
	
	@Column(name="player1WinDeck")
	private int player1WinDeck;
	
	@Column(name="player2Score")
	private int player2Score;
	
	@Column(name="player2GameDeck")
	private int player2GameDeck;
	
	@Column(name="player2WinDeck")
	private int player2WinDeck;
	
	
	private Player gameWinner;	
	private boolean hasGameBeenWon;
	
	public boolean runTurns(Deck gameDeck, Player player1, Player player2) {		
		
		logTurnInfo(player1, player2);
		compareCards(player1, player2, null);

		return hasGameBeenWon;		
	}
	
	public void compareCards(Player player1, Player player2, Deck incomingWinPile) {
		
		// checking if game won up here because is less code for recursive call
		setHasGameBeenWon(player1, player2);
		
		if (!hasGameBeenWon) {
						
			if(player1.getTotalAmountOfCards() < 2 || player2.getTotalAmountOfCards() < 2) {
				System.out.println("Game about to possibly end");
				System.out.println("Player1 cards before compare: " + player1.getDeck().getCards());
				System.out.println("Player2 cards before compare: " + player2.getDeck().getCards());
			}
			
			player1.turnWinDeckIntoPlayingDeck();
			player2.turnWinDeckIntoPlayingDeck();

			Deck winPile = incomingWinPile == null ? new Deck() : incomingWinPile;
			
			Card player1Card = player1.getDeck().takeACard();
			Card player2Card = player2.getDeck().takeACard();
			
			System.out.println("COMPARING PLAYER1 CARD: " + player1Card + 
								" TO PLAYER2 CARD: " + player2Card);
			
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
				compareCards(player1, player2, winPile);	
			}			
		}		
	}
	
	public void logTurnInfo(Player player1, Player player2) {
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
	}
	
	public void gameHasBeenWon(boolean gameHasBeenWon) {
		this.hasGameBeenWon = gameHasBeenWon;
	}

	public boolean setHasGameBeenWon(Player player1, Player player2) {
		
		player1Score = player1.getTotalAmountOfCards();
		player2Score = player2.getTotalAmountOfCards();
		
		if (player1.getTotalAmountOfCards() == 0) {
			hasGameBeenWon = true;
			gameWinner = player2;
		} else if (player2.getTotalAmountOfCards() == 0) {
			hasGameBeenWon = true;
			gameWinner = player1;
		}
		return hasGameBeenWon;
	}
	

	public boolean getHasGameBeenWon() {
		return hasGameBeenWon;
	}

	public Player getWinner() {
		// TODO Auto-generated method stub
		return gameWinner;
	}

}
