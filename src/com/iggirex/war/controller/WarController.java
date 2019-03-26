package com.iggirex.war.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.iggirex.war.Player;
import com.iggirex.war.entity.Game;
import com.iggirex.war.entity.Turn;
import com.iggirex.war.service.DealerService;

@Controller
public class WarController {
	
	// Inject our customer service with @Autowired
	@Autowired
	private DealerService dealerService;
	
	 // add constructor stuff 0s
		
	@GetMapping("/")
	public String getWar(Model theModel) {
		
		System.out.println("$$$$$$$$$$$$$$$$fdafsafadsfadsfasdfadsfadsfasd$$$$$$$$$$\n");
		System.out.println(theModel);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
		
		Player player1 = new Player("player1");
		Player player2 = new Player("player2");
		
		Game game = new Game(player1, player2);
		
		Turn firstTurn = dealerService.makeFirstTurn(player1, player2);
		
//		theModel.addAttribute("game", game);
		theModel.addAttribute("turn", firstTurn);
		

		System.out.println("THE MODEL IS NO TNULL!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
		System.out.println(theModel);
		
		return "war";
	}
	
	@PostMapping("/postWar")
	public String postWar(@ModelAttribute("turn") Turn thisTurn) {
		
		System.out.println("FUKCING HELLO");
		
		System.out.println("$$$$$$$$$$$ Turn coming into Post:::: $$$$$$$$$$$$\n");
		System.out.println(thisTurn);
		
		Player tempPlayer = thisTurn.getPlayer1();
		
		System.out.println("\n\n OK OK OK this is tempPLAYERRRRRROU");
		System.out.println(tempPlayer);
		
		System.out.println("\n EY! EY!!! This is player1 WinDeck: ");
		System.out.println(thisTurn.getPlayer2WinDeck());
		
		
		System.out.println("and these iz player1 coming into Post:");
		System.out.println(thisTurn.getPlayer1());
		System.out.println("\n");
		
		Turn newTurn = dealerService.runTurn(thisTurn, thisTurn.getPlayer1(), thisTurn.getPlayer2());
		
		System.out.println(" IN POST --- AFTER RUNNING TURN TURN IS:");
		System.out.println(this);
		
//			theModel.addAttribute("turn", firstTurn);
		System.out.println("Tin post --- HE MODEL IS NO TNULL!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
		System.out.println(newTurn);
		
		
		thisTurn.setPlayer1Score(thisTurn.getPlayer1Score() + 1);
		
		return "war";
	}
	
	
	@GetMapping("/turns")
	public String getTurns(Model theModel) {		
		
		// get stuff from !DAO REFACTOR get turns from SERVICE
		List<Turn> theList = dealerService.getTurns();
		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println(theList);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		
		// add stuff to the model
		theModel.addAttribute("turns", theList);
		
		return "turn-list";
	}

}

