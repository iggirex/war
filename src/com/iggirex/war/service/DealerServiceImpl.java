package com.iggirex.war.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iggirex.war.Card;
import com.iggirex.war.Dealer;
import com.iggirex.war.Deck;
import com.iggirex.war.Game;
import com.iggirex.war.Player;
import com.iggirex.war.dao.TurnDAO;
import com.iggirex.war.entity.Turn;

@Service
public class DealerServiceImpl implements DealerService {
	
	// need to inject customer DAO
	@Autowired
	private TurnDAO turnDAO;
//	private boolean gameHasBeenWon;
//	private Player gameWinner;
//	public int turnNumber = 0;

	// Use @Transactional because our service layer will define the
	// beginning and end of a transaction.
	// @Transactional saves you from having to .begin(), .commit(), .close()
	
	@Override
	public Turn initializeGame() {
		
		Player player1 = new Player("player1");
		Player player2 = new Player("player2");
		
		// make initial turn
		Turn firstTurn = new Turn();
		
		// save initial turn
		turnDAO.saveTurn(firstTurn);
		
		
		// make initial deck / 
		Deck initialDeck = makeInitialDeck();
		
		
		// deal deck
		deal();
		
		
		// compare cards
		
		
		
		// return turn
		
		
		return firstTurn;
		
	}
	
	
	@Override
	@Transactional
	public List<Turn> getTurns() {
		
		return turnDAO.getTurns();
	}
	
	@Transactional
	private void saveTurn(Turn turn) {
		
		
		
	}
	
	@Override
	public Turn runTurn(Turn turn) {
		
		
		// totally empty turn comes in, add everything to turn
		
		// save turn
		turnDAO.saveTurn(turn);
		
		// compare cards
		
		// resolve winner

		return turn;
	}
	
	
	// start game
	
	public void startGame() {
		
		// make initial deck
		
		// deal cards
		
	}
	
	
	
	
	
	

	public Deck makeInitialDeck(){
		
		Deck initialDeck = new Deck();
		
		ArrayList<String> ranks = new ArrayList<>();
		ArrayList<String> suits = new ArrayList<>();
		
		for( int i=2; i<11; i++) {
			ranks.add(Integer.toString(i));
		}
		
		ranks.add("j");
		ranks.add("q");
		ranks.add("k");
		ranks.add("a");
		suits.add("heart");
		suits.add("diamond");
		suits.add("club");
		suits.add("spade");
		
		for( int i=0; i<ranks.size(); i++) {
			for( int x=0; x<suits.size(); x++) {
				
				String tempSuit = suits.get(x);
				String tempRank = ranks.get(i);
				Card tempCard = new Card(tempSuit, tempRank);
				
				initialDeck.addACard(tempCard);
			}	
		}
		
		ArrayList<Card> tempDeckCards = initialDeck.getCards();
		
		Collections.shuffle(tempDeckCards);
		initialDeck.setCards(tempDeckCards);
		
		return initialDeck;
	}
	
	public void deal(ArrayList<Card> theDeck, Player player1, Player player2) {
		
		for(int i=0; i<theDeck.size(); i++) {
			Card tempCard = theDeck.get(i);
			
			if(i % 2 == 0) {
				player1.addToDeck(tempCard);
			} else {
				player2.addToDeck(tempCard);
			}
		}
	}
	
	public void runGame(Deck gameDeck, Player player1, Player player2) {
//		
//		System.out.println("\n======================== Beginning of Turn " 
//							+ turnNumber + " =============================");
//		
//		Turn turn = new Turn();
//		gameHasBeenWon = turn.runTurns(gameDeck, player1, player2);
//		turnNumber++;
//		
//		System.out.println("=================================================="
//							+ "=========================\n");
//
//		gameWinner = turn.getWinner();
	}
	
//	public void play(Player player1, Player player2, Dealer theDealer, Deck gameDeck) {
//		
//		Game game = new Game();
//		
//		while (hasGameBeenWon()) {
//			runGame(gameDeck, player1, player2);
//		}
//		
//		game.setTurnsToWin(turnNumber);
//		game.setWinner(gameWinner.getName());
//		
//		System.out.println("COMING FROM GGAAMMMEEE this is gameHasBeenWon: " +
//						theDealer.hasGameBeenWon());
//			
//		System.out.println("Congratulations " + game.getWinner() + ", you have won the game!!");	
//	}
//
//	public boolean hasGameBeenWon() {
//		return gameHasBeenWon;
//	}
//
//	public Player getGameWinner() {
//		return gameWinner;
//	}
//
//	public int getTurnNumber() {
//		return turnNumber;
//	}

}
