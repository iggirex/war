package com.iggirex.war.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iggirex.war.Card;
import com.iggirex.war.Deck;
import com.iggirex.war.Player;
import com.iggirex.war.dao.GameDAO;
import com.iggirex.war.dao.TurnDAO;
import com.iggirex.war.entity.Game;
import com.iggirex.war.entity.Turn;

@Service
public class DealerServiceImpl implements DealerService {
	
	// need to inject customer DAO
	@Autowired
	private TurnDAO turnDAO;
	
	@Autowired
	private GameDAO gameDAO;
	
	private boolean hasGameBeenWon;
	private Player gameWinner;

	// Use @Transactional because our service layer will define the
	// beginning and end of a transaction.
	// @Transactional saves you from having to .begin(), .commit(), .close()
	
	//	USE @Transactional here???? to store game obj??
	
	
	@Override
	@Transactional
	public Turn runTurn(Turn turn, Player player1, Player player2, Game game) {
		System.out.println("\nINSIDE RUN TRUNR this is turn handed in");
		System.out.println(game);
				
		Turn newForRealTurn = new Turn();
		newForRealTurn.setTurn(turn);
		newForRealTurn.setGameId(turn.getGameId());
		
		compareCards(newForRealTurn, null, game);
		newForRealTurn.setTurnNumber(turn.getTurnNumber() + 1);
		
		turn.setTurn(newForRealTurn);
		turnDAO.saveTurn(newForRealTurn);

		System.out.println("Inside run Turnr and this is turn being handed back:");
		System.out.println(newForRealTurn);
		
		return newForRealTurn;
	}
	
	@Override
	public Turn returnAllTurns(Turn turn, Game game) {
//		
////		Turn nextTurn = new Turn();
//		
//		while(game.getWinner() == null) {
//			System.out.println("\n+++++++++++++++++++++ INSIDE RETURN ALL TURNS +++++++++++++++++++++");
//			
//			System.out.println();
//			
//			turn.setTurn(runTurn(turn, turn.getPlayer1(), turn.getPlayer2(), game));
//			
////			model.addAttribute("turn", lastTurn);
////			model.addAttribute("game", game);
//			
//			System.out.println("\\n+++++++++++++++++++++ LEAVING RETURN ALL TURNS +++++++++++++++++++++");
//			System.out.println("\n\n");
//		}
		return null;
	}
	
	@Override
	public void compareCards(Turn turn, Deck incomingWinPile, Game game) {
		
		Player player1 = turn.getPlayer1();
		Player player2 = turn.getPlayer2();
		player1.turnWinDeckIntoPlayingDeck();
		player2.turnWinDeckIntoPlayingDeck();
		
		System.out.println("inside COMPARE cards, this is turn handed in:");
		System.out.println(turn);
		
		if (incomingWinPile == null) {
			clearPlayingCards(turn);
		}
		
		setHasGameBeenWon(turn.getPlayer1(), turn.getPlayer2(), game);
		
		// checking if game won up here because is less code for recursive call
		if (!hasGameBeenWon) {
		
			Deck winPile = incomingWinPile == null ? new Deck() : incomingWinPile;

			Card player1Card = player1.takeTopCard();
			Card player2Card = player2.takeTopCard();
			
			if(turn.getPlayer1Card() == null) {
				turn.setPlayer1Card(player1Card.getCardAsString());
			} else if (turn.getSecondPlayer1Card() == null) {
				turn.setSecondPlayer1Card(player1Card.getCardAsString());
			} else {
				turn.setThirdPlayer1Card(player1Card.getCardAsString());
			}
			
			if(turn.getPlayer2Card() == null) {
				turn.setPlayer2Card(player2Card.getCardAsString());
			} else if (turn.getSecondPlayer2Card() == null) {
				turn.setSecondPlayer2Card(player2Card.getCardAsString());
			} else {
				turn.setThirdPlayer2Card(player2Card.getCardAsString());
			}

//			System.out.println("\n=====================================");
//			System.out.println("COMPARING PLAYER1 CARD: " + player1Card + 
//								" TO PLAYER2 CARD: " + player2Card);
//			System.out.println("=====================================\n");

			winPile.addACard(player1Card);
			winPile.addACard(player2Card);
						
			if(player1Card.getValue() > player2Card.getValue()) {
				player1.addToWinDeck(winPile);
				
				turn.setPlayer1WinDeck(player1.getAmountOfWinCards());
				
			} else if(player2Card.getValue() > player1Card.getValue()) {
				player2.addToWinDeck(winPile);
				
				turn.setPlayer2WinDeck(player2.getAmountOfWinCards());
				
			} else {
				System.out.println("\nGOING INTO RECURSIVE CALL!!\n");
				
				turn.setPlayer1Score(player1.getTotalAmountOfCards());
				turn.setPlayer1GameDeck(player1.getAmountOfCards());
				turn.setPlayer1(player1);
				
				turn.setPlayer2Score(player2.getTotalAmountOfCards());
				turn.setPlayer2GameDeck(player2.getAmountOfCards());
				turn.setPlayer2(player2);
				
				compareCards(turn, winPile, game);	
			}
			
			turn.setPlayer1Score(player1.getTotalAmountOfCards());
			turn.setPlayer1GameDeck(player1.getAmountOfCards());
			turn.setPlayer1WinDeck(player1.getAmountOfWinCards());
			turn.setPlayer1(player1);
			
			turn.setPlayer2Score(player2.getTotalAmountOfCards());
			turn.setPlayer2GameDeck(player2.getAmountOfCards());
			turn.setPlayer2WinDeck(player2.getAmountOfWinCards());
			turn.setPlayer2(player2);
		}		
		
		System.out.println("At the end of compare cards turn is:");
		System.out.println(turn);
	}
	
	@Override
	public boolean setHasGameBeenWon(Player player1, Player player2, Game game) {
		
		int player1Score = player1.getTotalAmountOfCards();
		int player2Score = player2.getTotalAmountOfCards();
		
		if (player1Score == 0) {
			hasGameBeenWon = true;
			gameWinner = player2;
			game.setWinner("Player 2");
		} else if (player2Score == 0) {
			hasGameBeenWon = true;
			gameWinner = player1;
			game.setWinner("Player 1");
		}
		return hasGameBeenWon;
	}
	
	@Override
	@Transactional
	public Turn makeFirstTurn(Turn firstTurn, TurnDAO mockTurnDAO, Game game) {
		// FIND better solution for mockTurnDAO
		
		hasGameBeenWon = false;
		
		Deck initialDeck = makeInitialDeck();
		
		firstTurn.setPlayer1(new Player("player1"));
		firstTurn.setPlayer2(new Player("player2"));
		deal(initialDeck.getCards(), firstTurn.getPlayer1(), firstTurn.getPlayer2());
		
		firstTurn.setPlayer1GameDeck(firstTurn.getPlayer1().getDeck().getCards().size());
		firstTurn.setPlayer2GameDeck(firstTurn.getPlayer2().getDeck().getCards().size());
		firstTurn.setPlayer1WinDeck(0);
		firstTurn.setPlayer2WinDeck(0);
		firstTurn.setPlayer1Score(firstTurn.getPlayer1().getTotalAmountOfCards());
		firstTurn.setPlayer2Score(firstTurn.getPlayer2().getTotalAmountOfCards());
		
		firstTurn.setPlayer1(firstTurn.getPlayer1());
		firstTurn.setPlayer2(firstTurn.getPlayer2());
		
		firstTurn.setTurnNumber(1);
		
//		if (turnDAO != null) {
			firstTurn.setGameId(game);
			
			turnDAO.saveTurn(firstTurn);			
			gameDAO.saveGame(game);
//		}
		return firstTurn;
	}
	
	@Override
	@Transactional
	public List<Turn> getTurns() {
		return turnDAO.getTurns();
	}
	
	@Override
	public void deal(ArrayList<Card> theDeck, Player player1, Player player2) {
		
		Deck player1StartingDeck = new Deck();
		Deck player2StartingDeck = new Deck();
		
		player1.setDeck(player1StartingDeck);
		player2.setDeck(player2StartingDeck);
		
		for(int i=0; i<theDeck.size(); i++) {
			Card tempCard = theDeck.get(i);
			
			if(i % 2 == 0) {
				player1.addToDeck(tempCard);
			} else {
				player2.addToDeck(tempCard);
			}
		}
	}
	
	@Override
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
	
public void clearPlayingCards(Turn turn) {
		
		turn.setPlayer1Card(null);
		turn.setPlayer2Card(null);
		
		if( turn.getSecondPlayer1Card() != null) {
			turn.setSecondPlayer1Card(null);
		}
		if( turn.getSecondPlayer2Card() != null) {
			turn.setSecondPlayer2Card(null);
		}
		if( turn.getThirdPlayer1Card() != null) {
			turn.setThirdPlayer1Card(null);
		}
		if( turn.getThirdPlayer2Card() != null) {
			turn.setThirdPlayer2Card(null);
		}
	}
}








