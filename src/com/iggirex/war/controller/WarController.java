package com.iggirex.war.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.iggirex.war.Player;
import com.iggirex.war.entity.Game;
import com.iggirex.war.entity.Turn;
import com.iggirex.war.service.DealerService;

@Controller
@SessionAttributes("turn")
public class WarController {
	
	// Inject our customer service with @Autowired
	@Autowired
	private DealerService dealerService;
	
	
	@GetMapping("/")
	public String getWar(Model theModel, @ModelAttribute Turn firstTurn) {
		
		Player player1 = new Player("player1");
		Player player2 = new Player("player2");
		Game game = new Game(player1, player2);
		
		Turn tempTurn = dealerService.makeFirstTurn(firstTurn);
		firstTurn.setTurn(tempTurn);
		theModel.addAttribute("turn", firstTurn);
		
		return "war";
	}
	
	@GetMapping("/nextTurn")
	public String getNextTurn(Model model, @ModelAttribute("turn") Turn turn) {
		
		Turn newTurn = dealerService.runTurn(turn, turn.getPlayer1(), turn.getPlayer2()); //
		
		turn.setTurn(newTurn);
		turn.setPlayer1Score(turn.getPlayer1Score());
		model.addAttribute("turn", turn);
		
		return "war";
	}
	
//	@GetMapping("/turns")
//	public String getTurns(Model theModel) {		
//		
//		// get stuff from !DAO REFACTOR get turns from SERVICE
//		List<Turn> theList = dealerService.getTurns();
//		
//		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$");
//		System.out.println(theList);
//		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$");
//		
//		
//		// add stuff to the model
//		theModel.addAttribute("turns", theList);
//		
//		return "turn-list";kk
//	}
	
	@ModelAttribute("turn")
	 public Turn turn() {
	  return new Turn();
	 }

}

