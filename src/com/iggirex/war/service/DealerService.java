package com.iggirex.war.service;

import java.util.List;

import com.iggirex.war.entity.Turn;

public interface DealerService {
	
	public List<Turn> getTurns();
		
	public Turn runTurn(Turn turn);
	
	public Turn initializeGame();
	
}
