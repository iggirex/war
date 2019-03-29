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
	public Turn makeFirstTurn(Turn firstTurn) {
		
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
		
		turnDAO.saveTurn(firstTurn);		

		return firstTurn;
	}
	
	@Override
	public Turn runTurn(Turn turn, Player player1, Player player2) {
		System.out.println("\nINSIDE RUN TRUNR");
//		System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>> tis iz player2 deck to Striung");
//		System.out.println(player2);
//		System.out.println(player2.getDeck());
//		System.out.println("\n\n");
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> VS lastTurn.getPlayer2() deck to Striung");
//		System.out.println(turn.getPlayer2().deckToString());
		
		System.out.println(turn);
		
//		compareCards(turn.getPlayer1(), turn.getPlayer2(), null);
		
		compareCards(turn, null);
		
 		System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>> did compareCards change turn obj????\n");
		System.out.println(turn);
		
		turnDAO.saveTurn(turn);
		
		return turn;
	}
	
	@Override
	public Game initializeGame() {
		
		Player player1 = new Player("player1");
		Player player2 = new Player("player2");
		Game newGame = new Game(player1, player2);
		
		return newGame;
	}

	
	
	@Override
	public void compareCards(Turn turn, Deck incomingWinPile) {
						
		setHasGameBeenWon(turn.getPlayer1(), turn.getPlayer2());
		
		// checking if game won up here because is less code for recursive call
		if (!hasGameBeenWon) {
						
			if(turn.getPlayer1().getTotalAmountOfCards() < 2 || turn.getPlayer2().getTotalAmountOfCards() < 2) {
				System.out.println("Game about to possibly end");
			}
			
			Player player1 = turn.getPlayer1();
			int test = player1.getAmountOfCards();
			
			Player player2 = turn.getPlayer1();
			
			player1.turnWinDeckIntoPlayingDeck();
			player2.turnWinDeckIntoPlayingDeck();
			
			Deck winPile = incomingWinPile == null ? new Deck() : incomingWinPile;
			
			
			Card player1Card = takeTopCard(player1);
			Card player2Card = takeTopCard(player2);
			turn.getPlayer1().setDeck(player1.getDeck());
			turn.getPlayer2().setDeck(player2.getDeck());
			
			
			System.out.println("\n******************* \nBout to set player 1 total Cards and iz::::");
			System.out.println(player1.getDeck());
			int amountOfCards = player1.getAmountOfCards(); 
			System.out.println(amountOfCards);
			System.out.println("******************* \nBout to set player 1 total Cards and iz::::\n");
			
			
			System.out.println(player1);
			int test2 = player1.getAmountOfCards();
			
			System.out.println(turn.getPlayer1());
			
			System.out.println("COMPARING PLAYER1 CARD: " + player1Card + 
								" TO PLAYER2 CARD: " + player2Card);
			
			winPile.addACard(player1Card);
			winPile.addACard(player2Card);
			
			System.out.println(turn);
			
			if(player1Card.getValue() > player2Card.getValue()) {
				player1.addToWinDeck(winPile);
				
				
				 
				System.out.println("\n******************* \nBout to set player 1 total Cards and iz::::");
				int amountOfCards2 = player1.getAmountOfCards(); 
				System.out.println(amountOfCards2);
				System.out.println("******************* Bout to set player 1 total Cards and iz::::\n");
				
				turn.setPlayer1Score(amountOfCards2);
				
				turn.setPlayer1(player1);
				
				
				
				System.out.println("Player 1 just won, their game deck has: " + 
									turn.getPlayer1().getWinDeck().getCards().size() +
									" and here is all their cards: " + turn.getPlayer2().getTotalAmountOfCards());
				System.out.println(turn);
			} else if(player2Card.getValue() > player1Card.getValue()) {
				player2.addToWinDeck(winPile);
				
				
				player2.addToWinDeck(winPile);
				turn.setPlayer1(player2);
				
				turn.setPlayer2Score(player2.getTotalAmountOfCards());
				
				
				System.out.println("Player 2 just won, their game deck has: " +
									turn.getPlayer2().getDeck().getCards().size() +
						" and here is all their cards: " + turn.getPlayer2().getTotalAmountOfCards());
				System.out.println(turn);
			} else {
				compareCards(turn, winPile);	
			}			
		}		
	}
	
	
public Card takeTopCard(Player player) {
	
	Deck playerDeck = player.getDeck();
	System.out.println("\n(())()()( hey this is playerDeck BEEEEFFFFOOOORRREEEE Take a Card!!!!!");
	System.out.println(playerDeck);
	System.out.println(player.getAmountOfCards());
	
	Card topCard  = playerDeck.takeACard();
	
	System.out.println(playerDeck);
	
	
	System.out.println("\n(())()()( hey this is playerDeck after Take a Card!!!!!");
	player.setDeck(playerDeck);
	
	return topCard;
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
	public boolean setHasGameBeenWon(Player player1, Player player2) {
		
		System.out.println("inside setHasGameBeenWon and player1 amount of cards:");
		System.out.println(player1);
		
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
