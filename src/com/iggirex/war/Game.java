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

	public Game() {
	}

	public Game(Player player1, Player player2) {
		// TODO Auto-generated constructor stub
	}

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
	

}
