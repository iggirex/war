package com.iggirex.war.dao;

import java.util.List;

import com.iggirex.war.entity.Game;
import com.iggirex.war.entity.Turn;

// Interface NOT strictly necessary but basic good practice
// Includes only a set of function declarations (implementations must follow)
// Contract that classes are forced to follow. Sets basic architecture. Your app, your rules.
// Can save time, if you have 50 classes, you can add new method here and create them in all classes

// Also allows polymorphism
//
// Let's say you want to group pests together, but very different things qualify = telemarketer & insect
// Both telemarketer and insect can implement pest interface
// Now you declare a pest array (pest is interface) and your classes that implement pest
// can all go in the same array though they are very different

public interface TurnDAO {
	
	public List<Turn> getTurns();
	
	public void saveTurn(Turn turn);

	List<Turn> getTurnsForGame(int gameId);
	
}
