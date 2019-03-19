package com.iggirex.war.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.iggirex.war.Player;

@Entity
@Table(name="turn_tbl")
public class Turn {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
//	private Player player1;
//	private Player player2;
	
	public Turn() {
		
	}
	
	public Turn(int player1Score, int player1GameDeck, int player1WinDeck, int player2Score,
			int player2GameDeck, int player2WinDeck) {

		this.player1Score = player1Score;
		this.player1GameDeck = player1GameDeck;
		this.player1WinDeck = player1WinDeck;
		this.player2Score = player2Score;
		this.player2GameDeck = player2GameDeck;
		this.player2WinDeck = player2WinDeck;
		
//		this.player1 = player1;
//		this.player2 = player2;
	}

//	public Turn(int player1Score, int player1GameDeck, int player1WinDeck, int player2Score,
//			int player2GameDeck, int player2WinDeck, Player player1, Player player2) {
//
//		this.player1Score = player1Score;
//		this.player1GameDeck = player1GameDeck;
//		this.player1WinDeck = player1WinDeck;
//		this.player2Score = player2Score;
//		this.player2GameDeck = player2GameDeck;
//		this.player2WinDeck = player2WinDeck;
//		
//		this.player1 = player1;
//		this.player2 = player2;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayer1Score() {
		return player1Score;
	}

	public void setPlayer1Score(int player1Score) {
		this.player1Score = player1Score;
	}

	public int getPlayer1GameDeck() {
		return player1GameDeck;
	}

	public void setPlayer1GameDeck(int player1GameDeck) {
		this.player1GameDeck = player1GameDeck;
	}

	public int getPlayer1WinDeck() {
		return player1WinDeck;
	}

	public void setPlayer1WinDeck(int player1WinDeck) {
		this.player1WinDeck = player1WinDeck;
	}

	public int getPlayer2Score() {
		return player2Score;
	}

	public void setPlayer2Score(int player2Score) {
		this.player2Score = player2Score;
	}

	public int getPlayer2GameDeck() {
		return player2GameDeck;
	}

	public void setPlayer2GameDeck(int player2GameDeck) {
		this.player2GameDeck = player2GameDeck;
	}

	public int getPlayer2WinDeck() {
		return player2WinDeck;
	}

	public void setPlayer2WinDeck(int player2WinDeck) {
		this.player2WinDeck = player2WinDeck;
	}
	
	

//	public Player getPlayer1() {
//		return player1;
//	}
//
//	public void setPlayer1(Player player1) {
//		this.player1 = player1;
//	}
//
//	public Player getPlayer2() {
//		return player2;
//	}
//
//	public void setPlayer2(Player player2) {
//		this.player2 = player2;
//	}

	@Override
	public String toString() {
		return "Turn [id=" + id + ", player1Score=" + player1Score + ", player1GameDeck=" + player1GameDeck
				+ ", player1WinDeck=" + player1WinDeck + ", player2Score=" + player2Score + ", player2GameDeck="
				+ player2GameDeck + ", player2WinDeck=" + player2WinDeck + "]";
	}
	
	 

}
