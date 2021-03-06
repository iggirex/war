package com.iggirex.war.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

import com.iggirex.war.Player;

@Entity
@Table(name="game_tbl")
public class Game {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
//	@Column(name="player1")
//	private Player player1;
//	
//	@Column(name="player2")
//	private Player player2;
	
	@Column(name="winner")
	private String winner;
	
	@Column(name="turnsToWin")
	private int turnsToWin;
	
	public int getId() {
		return this.id;
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

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public int getTurnsToWin() {
		return turnsToWin;
	}

	public void setTurnsToWin(int turnsToWin) {
		this.turnsToWin = turnsToWin;
	}

	@Override
	public String toString() {
		return "Game [id =" + id + "winner=" + winner + ", turnsToWin=" + turnsToWin
				+ "]";
	}
	
	public Game() {
	}
		
}
