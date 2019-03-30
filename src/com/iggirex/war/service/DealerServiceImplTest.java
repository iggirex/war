package com.iggirex.war.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.iggirex.war.entity.Turn;

class DealerServiceImplTest {
	
	public int squared(int x) {
		return x*x;
	}
	
	
	
	

	@Test
	void test() {
		
		Turn turn = new Turn();
		DealerService dealerService = new DealerServiceImpl();
		
		

		
		dealerService.compareCards(turn, null);

		
		
		int output = squared(5);
		assertEquals(25, output);
	}

}
