package com.iggirex.war.service;

import java.util.ArrayList;
import java.util.List;

import com.iggirex.war.Card;
import com.iggirex.war.Deck;
import com.iggirex.war.Player;
import com.iggirex.war.entity.Game;
import com.iggirex.war.entity.Turn;

public interface DealerService {
	
	public List<Turn> getTurns();
		
	public Turn runTurn(Turn turn);
	
	
	
	public Game initializeGame();
	
	void compareCards(Player player1, Player player2, Deck incomingWinPile);

	Deck makeInitialDeck();

	void deal(ArrayList<Card> theDeck, Player player1, Player player2);

	boolean setHasGameBeenWon(Player player1, Player player2);

	Turn makeFirstTurn(Player player1, Player player2);

	Turn runTurn(Turn lastTurn, Player player1, Player player2);
	
}