package com.iggirex.war.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iggirex.war.Card;
import com.iggirex.war.Deck;
import com.iggirex.war.Player;
import com.iggirex.war.dao.TurnDAO;
import com.iggirex.war.entity.Game;
import com.iggirex.war.entity.Turn;

import static org.mockito.Mockito.*;

class DealerServiceImplTest {
	
	static Turn turn;
	DealerService dealerService;
	
	@Mock
	static TurnDAO turnDAO;

	@BeforeEach
	void testSetup() {
		MockitoAnnotations.initMocks(this);
		
		turnDAO = mock(TurnDAO.class);
		doNothing().when(turnDAO).saveTurn(turn);
		
		turn = new Turn();
		Game game = new Game();
		dealerService = new DealerServiceImpl();
		
		Turn tempTurn = dealerService.makeFirstTurn(turn, turnDAO, game);
		turn.setTurn(tempTurn);
	}
	
	public int squared(int x) {
		return x*x;
	}

	@Test
	void testPlayer1Wins() {
		Player player1 = turn.getPlayer1();
		Player player2 = turn.getPlayer2();
		
		Card player1Card = new Card("heart", "10");
		Card player2Card = new Card("heart", "9");
		
		player1.getDeck().addTopCard(player1Card);
		player2.getDeck().addTopCard(player2Card);
		
		dealerService.compareCards(turn, null);
		
		assertEquals(25, player1.getAmountOfCards());
		assertEquals(27, player1.getTotalAmountOfCards());
		assertEquals(2, player1.getAmountOfWinCards());

		assertEquals(25, player2.getAmountOfCards());
		assertEquals(25, player2.getTotalAmountOfCards());
		assertEquals(0, player2.getAmountOfWinCards());

	}
	
	@Test
	void testPlayer2Wins() {
		Player player1 = turn.getPlayer1();
		Player player2 = turn.getPlayer2();
		
		Card player1Card = new Card("heart", "j");
		Card player2Card = new Card("heart", "k");
		
		player1.getDeck().addTopCard(player1Card);
		player2.getDeck().addTopCard(player2Card);
		
		dealerService.compareCards(turn, null);
		
		assertEquals(25, player1.getAmountOfCards());
		assertEquals(25, player1.getTotalAmountOfCards());
		assertEquals(0, player1.getAmountOfWinCards());

		assertEquals(25, player2.getAmountOfCards());
		assertEquals(27, player2.getTotalAmountOfCards());
		assertEquals(2, player2.getAmountOfWinCards());
	}
	
	
	@Test
	void testTie() {
		Player player1 = turn.getPlayer1();
		Player player2 = turn.getPlayer2();
		
		Card secondPlayer1Card = new Card("heart", "2");
		Card secondPlayer2Card = new Card("heart", "q");
		player1.getDeck().addTopCard(secondPlayer1Card);
		player2.getDeck().addTopCard(secondPlayer2Card);
		
		Card player1Card = new Card("heart", "a");
		Card player2Card = new Card("heart", "a");
		player1.getDeck().addTopCard(player1Card);
		player2.getDeck().addTopCard(player2Card);
		
		System.out.println("THESE ARE PLAYER DECKS IN TIE::");
		System.out.println(player1.getDeck());
		System.out.println(player2.getDeck());
		
		dealerService.compareCards(turn, null);
		
		assertEquals(24, player1.getAmountOfCards());
		assertEquals(24, player1.getTotalAmountOfCards());
		assertEquals(0, player1.getAmountOfWinCards());

		assertEquals(24, player2.getAmountOfCards());
		assertEquals(28, player2.getTotalAmountOfCards());
		assertEquals(4, player2.getAmountOfWinCards());
	}

}
