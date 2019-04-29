package com.iggirex.war.controller;

import java.util.List;

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
		System.out.println("\n========== INSIDE NEXT TURN ====================");
		
		Turn newTurn = dealerService.runTurn(turn, turn.getPlayer1(), turn.getPlayer2(), game);

		model.addAttribute("turn", newTurn);
		model.addAttribute("game", game);
		
		System.out.println("============= LEAVING NEXT TURN ================\n\n");		
		return "war";
	}
	
	@GetMapping("/allTurns")
	public String getAllTurns(Model model, @ModelAttribute("turn") Turn turn, @ModelAttribute("game") Game game) {
		
		Turn nextTurn = new Turn();
		
		model.addAttribute("allTurns", null);
		
		while(game.getWinner() == null) {
			System.out.println("\n$$$$$$$$$$$$ INSIDE ALL TURNS $$$$$$$$$$$$$$$");
			
			Turn lastTurn = dealerService.runTurn(turn, turn.getPlayer1(), turn.getPlayer2(), game);
			
			nextTurn.setTurn(lastTurn);
			
			
			
			System.out.println("\\n$$$$$$$$$$$$ LEAVING ALL TURNS $$$$$$$$$$$$$$$\n\n");
		}
		
		List<Turn> allTurns = dealerService.getTurnsForGame(game.getId());
		
		model.addAttribute("allTurns", allTurns);
		
		model.addAttribute("turn", nextTurn);
		model.addAttribute("game", game);
		
		return "win";
	}
	
	@GetMapping("/newGame")
	  public String newGame(Model theModel, @ModelAttribute("turn") Turn turn, @ModelAttribute("game") Game game) {
	    
	    Game newGame = new Game();
	    
	    Turn firstTurn = new Turn();
		Game thisGame = new Game();
		Turn tempTurn = dealerService.makeFirstTurn(firstTurn, null, thisGame);
		firstTurn.setTurn(tempTurn);
		
		theModel.addAttribute("turn", firstTurn);
		theModel.addAttribute("game", thisGame);

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

