package com.iggirex.war.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
		Turn firstTurn = dealerService.initializeGame();
		
//		if(!theModel.containsAttribute("turn")) {
		
//			Turn firstTurn = new Turn();
//			System.out.println("Adding furst turn");
//			theModel.addAttribute("turn", firstTurn);
//			
//			System.out.println(theModel.asMap());
//			
//		} else {

			System.out.println("THE MODEL IS NO TNULL!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
			
			System.out.println(theModel);
			
//		}
		
		return "war";
	}
	
	@PostMapping("/postWar")
	public String postWar(@ModelAttribute("turn") Turn thisTurn) {
	
		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
		
		System.out.println(thisTurn);
		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
		System.out.println("does model contain turns");

		
		Turn newTurn = dealerService.runTurn(thisTurn);
		System.out.println(" IN POST --- Adding furst turn");
		
//			theModel.addAttribute("turn", firstTurn);
		
					
		System.out.println("Tin post --- HE MODEL IS NO TNULL!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
		
		System.out.println(newTurn);
		
		
		
		return "war";

//		return "redirect:/turns";
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
	
//	@PostMapping("/runTurn")
//	public String runTurn(Model theModel) {
//		
//		Turn thisTurn = theModel.turns;
//		
//		return "war";
//	}

}

