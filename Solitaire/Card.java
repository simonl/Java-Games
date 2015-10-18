package Solitaire;

public final class Card {
	
	public static final CardMap<Color> colors = new CardMap<Color>(Color.RED, Color.RED, Color.BLACK, Color.BLACK);
	
	public final CardType type;
	public final int value;
	
	public Card(final CardType type, final int value) {
		if(value < 0 || value > 12) throw new RuntimeException("Card value out of bounds!");
		
		this.type = type;
		this.value = value;
	}
}