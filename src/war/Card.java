package war;

public class Card {
	
	private String suit;
	private String rank;
	private int value;
	private String cardImage;
	
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public int getValue() {
		
		switch(rank) {
			case "j": value = 11;
				break;
			case "q": value = 12;
				break;
			case "k": value = 13;
				break;
			case "a": value = 14;
				break;
			default: value = Integer.parseInt(rank);
				break;
		}
		return value;
	}
	
	public String getCardImage() {
		
		String image = "";
		
		image = "__________" +
		        "|4       |" +
				"|        |" +
		        "|        |" +
				"|    $   |" +
		        "|        |" +
				"|        |" +
		        "|_______4|";
		
		return image;
	}

	@Override
	public String toString() {
		return "Card [suit=" + suit + ", rank=" + rank + "]";
	}

}
