package com.iggirex.war.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iggirex.war.entity.Game;
import com.iggirex.war.entity.Turn;
import com.iggirex.war.service.DealerService;

@Controller
@SessionAttributes({"turn", "game"})
public class WarController {
	
	// Inject our dealer service with @Autowired
	@Autowired
	private DealerService dealerService;
	
	@GetMapping("/")
	public String getWar(Model theModel) {
		
		Turn firstTurn = new Turn();
		Game thisGame = new Game();
		Turn tempTurn = dealerService.makeFirstTurn(firstTurn, null, thisGame);
		firstTurn.setTurn(tempTurn);
		
		theModel.addAttribute("turn", firstTurn);
		theModel.addAttribute("game", thisGame);
		
		System.out.println("This is game being made:");
		System.out.println(thisGame.getId());
		
		return "war";
	}
	
	@GetMapping("/nextTurn")
	public String getNextTurn(Model model, @ModelAttribute("turn") Turn turn, @ModelAttribute("game") Game game) {
		
		System.out.println("\n==============================================");
//		System.out.println("IN controller and this is turn handed in:");
//		System.out.println(turn);
		
		Turn newTurn = dealerService.runTurn(turn, turn.getPlayer1(), turn.getPlayer2(), game);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>> inside /nextTurn <<<<<<<<<<<<<<<<<<<<");
		System.out.println("This is game being made:");
		System.out.println(game.getId());
		
//		System.out.println("\n");
//		System.out.println("IN controller and this is new turn JUST GOT MADEEE :");
//		System.out.println(newTurn);

		model.addAttribute("turn", newTurn);
		model.addAttribute("game", game);
		
		System.out.println("==============================================");
		System.out.println("\n\n");
		
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
//		return "turn-list";
//	}

}

