package com.iggirex.war;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="game")
public class Game {
	
	private int turnsToWin;
	private String winner;
	
	public void play(Player player1, Player player2, Dealer theDealer, Deck gameDeck) {
		while (!theDealer.hasGameBeenWon()) {
			theDealer.runGame(gameDeck, player1, player2); ///////////
		}
		
		turnsToWin = theDealer.getTurnNumber();
		winner = theDealer.getGameWinner().getName();
		
		System.out.println("COMING FROM GGAAMMMEEE this is gameHasBeenWon: " +
						theDealer.hasGameBeenWon());
			
		System.out.println("Congratulations " + winner + ", you have won the game!!");	
	}
	

}
