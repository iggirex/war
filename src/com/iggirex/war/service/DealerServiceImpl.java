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
import com.iggirex.war.dao.TurnDAO;
import com.iggirex.war.entity.Game;
import com.iggirex.war.entity.Turn;

@Service
public class DealerServiceImpl implements DealerService {
	
	// need to inject customer DAO
	@Autowired
	private TurnDAO turnDAO;
	
	private boolean hasGameBeenWon;
	private Player gameWinner;

	// Use @Transactional because our service layer will define the
	// beginning and end of a transaction.
	// @Transactional saves you from having to .begin(), .commit(), .close()
	
	//	USE @Transactional here???? to store game obj??
	@Override
	@Transactional
	public Turn makeFirstTurn(Player player1, Player player2) {
		
		Turn firstTurn = new Turn(); 
		Deck initialDeck = makeInitialDeck();
		
		deal(initialDeck.getCards(), player1, player2);

		firstTurn.setPlayer1GameDeck(player1.getDeck().getCards().size());
		firstTurn.setPlayer2GameDeck(player2.getDeck().getCards().size());
		firstTurn.setPlayer1WinDeck(0);
		firstTurn.setPlayer2WinDeck(0);
		firstTurn.setPlayer1Score(player1.getTotalAmountOfCards());
		firstTurn.setPlayer2Score(player2.getTotalAmountOfCards());
		
		firstTurn.setPlayer1(player1);
		firstTurn.setPlayer2(player2);
		
		turnDAO.saveTurn(firstTurn);		
		
		System.out.println("\n$$$ IN MAKIE FIRST TUNR!!!");
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> tis iz player2 deck to Striung");
		
		System.out.println(player2);
		System.out.println(player2.getDeck());

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> VS lastTurn.getPlayer2() deck to Striung");
		
		System.out.println(firstTurn.getPlayer2().deckToString());
		
		return firstTurn;
	}
	
	@Override
	public Turn runTurn(Turn lastTurn, Player player1, Player player2) {
		
		System.out.println("INSIDE RUN TRUNR\n");
		
		
		System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>> tis iz player2 deck to Striung");
		
		System.out.println(player2);
		System.out.println(player2.getDeck());
		
		System.out.println("\n\n");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> VS lastTurn.getPlayer2() deck to Striung");
		
		System.out.println(lastTurn.getPlayer2().deckToString());
		
		
		Turn newTurn = new Turn(lastTurn.getPlayer1Score(), lastTurn.getPlayer1GameDeck(), lastTurn.getPlayer1WinDeck(), 
				lastTurn.getPlayer2Score(), lastTurn.getPlayer2GameDeck(), lastTurn.getPlayer2WinDeck(), player1, player2);
		
//		Turn newTurn = new Turn(lastTurn.getPlayer1Score(), lastTurn.getPlayer1GameDeck(), lastTurn.getPlayer1WinDeck(), 
//				lastTurn.getPlayer2Score(), lastTurn.getPlayer2GameDeck(), lastTurn.getPlayer2WinDeck());
		
		compareCards(player1, player2, null);
		
		turnDAO.saveTurn(newTurn);
		
		return newTurn;
	}
	
	@Override
	public Game initializeGame() {
		
		Player player1 = new Player("player1");
		Player player2 = new Player("player2");
		Game newGame = new Game(player1, player2);
		
		return newGame;
	}
	
	@Override
	public void compareCards(Player player1, Player player2, Deck incomingWinPile) {
		
		// checking if game won up here because is less code for recursive call
		
		System.out.println("\n\nmaking failing call in compare cards");
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> tis iz player2 AND  deck to Striung");
		
		System.out.println(player2);
		System.out.println(player2.deckToString());
		
		
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
	
	
	@Override
	@Transactional
	public List<Turn> getTurns() {
		return turnDAO.getTurns();
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
	
	@Override
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
	
	@Override
	public boolean setHasGameBeenWon(Player player1, Player player2) {
		
		System.out.println("\n\n INSIDE SET HAS GAME BEEN WON\nThis is player1's deck:");
		
		System.out.println(player1.deckToString());
		System.out.println(player1.getDeck());
		
		int player1Score = player1.getTotalAmountOfCards();
		int player2Score = player2.getTotalAmountOfCards();
		
		if (player1Score == 0) {
			hasGameBeenWon = true;
			gameWinner = player2;
		} else if (player2Score == 0) {
			hasGameBeenWon = true;
			gameWinner = player1;
		}
		return hasGameBeenWon;
	}

}
