package com.iggirex.war.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iggirex.war.entity.Turn;
import com.iggirex.war.service.DealerService;

@Controller
@SessionAttributes("turn")
public class WarController {
	
	// Inject our customer service with @Autowired
	@Autowired
	private DealerService dealerService;
	
	
	@GetMapping("/")
	public String getWar(Model theModel) {
		
		Turn firstTurn = new Turn();
		
		Turn tempTurn = dealerService.makeFirstTurn(firstTurn, null);
		firstTurn.setTurn(tempTurn);
		theModel.addAttribute("turn", firstTurn);
		
		return "war";
	}
	
	@GetMapping("/nextTurn")
	public String getNextTurn(Model model, @ModelAttribute("turn") Turn turn) {
		
		Turn newTurn = dealerService.runTurn(turn, turn.getPlayer1(), turn.getPlayer2()); //
		
		System.out.println("IN controller and this is new turn being made:");
		System.out.println(newTurn);
		turn.setTurn(newTurn);
		model.addAttribute("turn", turn);
		
		return "war";
	}
	
	@Transactional
	@GetMapping("/newTurn")
	public String getNewTurn(Model model, @ModelAttribute("turn") Turn turn) {
		
		Turn newTurn = new Turn();
		turn.setTurn(newTurn);
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
//		return "turn-list";
//	}
	
	@ModelAttribute("turn")
	 public Turn turn() {
	  return new Turn();
	 }

}

