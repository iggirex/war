package war;

public class Game {

	public static void main(String[] args) {
		
		Dealer theDealer = new Dealer();
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		
		Deck gameDeck = theDealer.makeInitialDeck();
		theDealer.deal(gameDeck.getCards(), player1, player2);
		
		while (!theDealer.hasGameBeenWon()) {
			theDealer.runTurn(gameDeck, player1, player2);
		}
		
		System.out.println("COMING FROM GGAAMMMEEE this is gameHasBeenWon: " +
							theDealer.hasGameBeenWon());
//		System.out.println("LOGIKKKL " +
//				";" + (!theDealer.hasGameBeenWon() || !theDealer.isGameHasBeenTied()));
				
		System.out.println("Congratulations " + theDealer.getGameWinner().getName() +
							", you have won the game!!");
	}
	
}
