package com.iggirex.war.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/war")
public class WarController {
	
	@RequestMapping("/game")
	public String getWar(Model theModel) {
		
		return "war";
	}

}
