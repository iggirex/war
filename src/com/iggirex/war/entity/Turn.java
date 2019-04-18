package com.iggirex.war.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.iggirex.war.Card;
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
	
	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="game_tbl_id")
//	@Column(name="game_id")
	private Game game;
	
	@Transient
	private Player player1;
	
	@Transient
	private Player player2;

	private String player1Card;
	private String player2Card;
	private String secondPlayer1Card;
	private String secondPlayer2Card;
	private String thirdPlayer1Card;
	private String thirdPlayer2Card;
	
	public Turn() {
		
	}
	
	public void setTurn(Turn newTurn) {
		
		this.player1Score = newTurn.getPlayer1Score();
		this.player1GameDeck = newTurn.getPlayer1GameDeck();
		this.player1WinDeck = newTurn.getPlayer1WinDeck();
		this.player2Score = newTurn.getPlayer2Score();
		this.player2GameDeck = newTurn.getPlayer2GameDeck();
		this.player2WinDeck = newTurn.getPlayer2WinDeck();
		
		this.player1 = newTurn.getPlayer1();
		this.player2 = newTurn.getPlayer2();
		
	}

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
	
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	


	public String getPlayer1Card() {
		return player1Card;
	}

	public void setPlayer1Card(String player1Card) {
		this.player1Card = player1Card;
	}

	public String getPlayer2Card() {
		return player2Card;
	}

	public void setPlayer2Card(String player2Card) {
		this.player2Card = player2Card;
	}

	public String getSecondPlayer1Card() {
		return secondPlayer1Card;
	}

	public void setSecondPlayer1Card(String secondPlayer1Card) {
		this.secondPlayer1Card = secondPlayer1Card;
	}

	public String getSecondPlayer2Card() {
		return secondPlayer2Card;
	}

	public void setSecondPlayer2Card(String secondPlayer2Card) {
		this.secondPlayer2Card = secondPlayer2Card;
	}

	public String getThirdPlayer1Card() {
		return thirdPlayer1Card;
	}

	public void setThirdPlayer1Card(String thirdPlayer1Card) {
		this.thirdPlayer1Card = thirdPlayer1Card;
	}

	public String getThirdPlayer2Card() {
		return thirdPlayer2Card;
	}

	public void setThirdPlayer2Card(String thirdPlayer2Card) {
		this.thirdPlayer2Card = thirdPlayer2Card;
	}

	public Game getGameId() {
		return game;
	}

	public void setGameId(Game game) {
		this.game = game;
	}

	@Override
	public String toString() {
		return "Turn [id=" + id + ", gameId=, player1Score=" + player1Score + ", player1GameDeck=" + player1GameDeck
				+ ", player1WinDeck=" + player1WinDeck + ", player2Score=" + player2Score + ", player2GameDeck="
				+ player2GameDeck + ", player2WinDeck=" + player2WinDeck + ", player1=" + player1 + ", player2="
				+ player2 + ", player1Card=" + player1Card + ", player2Card=" + player2Card + ", secondPlayer1Card="
				+ secondPlayer1Card + ", secondPlayer2Card=" + secondPlayer2Card + ", thirdPlayer1Card="
				+ thirdPlayer1Card + ", thirdPlayer2Card=" + thirdPlayer2Card + "]";
	}
	
	
	 

}
