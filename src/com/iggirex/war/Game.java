package com.iggirex.war;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="game")
public class Game {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="turnsToWin")
	private int turnsToWin;
	
	@Column(name="winner")
	private String winner;

	public int getTurnsToWin() {
		return turnsToWin;
	}

	public void setTurnsToWin(int turnsToWin) {
		this.turnsToWin = turnsToWin;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	
	
//	public void play(Player player1, Player player2, Dealer theDealer, Deck gameDeck) {
//		while (!theDealer.hasGameBeenWon()) {
//			theDealer.runGame(gameDeck, player1, player2);
//		}
//		
//		turnsToWin = theDealer.getTurnNumber();
//		winner = theDealer.getGameWinner().getName();
//		
//		System.out.println("COMING FROM GGAAMMMEEE this is gameHasBeenWon: " +
//						theDealer.hasGameBeenWon());
//			
//		System.out.println("Congratulations " + winner + ", you have won the game!!");	
//	}
	

}
