package com.iggirex.war.service;

import java.util.ArrayList;
import java.util.List;

import com.iggirex.war.Card;
import com.iggirex.war.Deck;
import com.iggirex.war.Player;
import com.iggirex.war.dao.TurnDAO;
import com.iggirex.war.entity.Game;
import com.iggirex.war.entity.Turn;

public interface DealerService {
	
	public List<Turn> getTurns();	
		
	Deck makeInitialDeck();

	void deal(ArrayList<Card> theDeck, Player player1, Player player2);

	Turn runTurn(Turn lastTurn, Player player1, Player player2, Game game);

	boolean setHasGameBeenWon(Player player1, Player player2, Game game);

	void compareCards(Turn turn, Deck incomingWinPile, Game game);

	public Turn returnAllTurns(Turn turn, Game game);

	Turn makeFirstTurn(Turn firstTurn, TurnDAO mockTurnDAO, Game game);

	List<Turn> getTurnsForGame(int gameId);
	
}
