package Solitaire;

public final class CardMap<T> {
	
	public static final CardMap<CardType> identity = 
		new CardMap<CardType>(CardType.HEART, CardType.DIAMOND, CardType.CLUB, CardType.SPADE);
	
	public static final CardMap<CardType> successor =
		new CardMap<CardType>(CardType.DIAMOND, CardType.CLUB, CardType.SPADE, null);
	
	private final T heart;
	private final T diamond;
	private final T club;
	private final T spade;
	
	public CardMap(final T heart, final T diamond, final T club, final T spade) {
		this.heart = heart;
		this.diamond = diamond;
		this.club = club;
		this.spade = spade;
	}
	
	public T get(final CardType type) {
		switch(type) {
			case HEART : return heart;
			case DIAMOND : return diamond;
			case CLUB : return club;
			case SPADE : return spade;
		}
		
		throw new RuntimeException("Non-exhaustive switch in : T CardMap<T>.get(CardType)");
	}
	
	public CardMap<T> set(final CardType type, final T value) {
		switch(type) {
			case HEART : return new CardMap<T>(value, diamond, club, spade);
			case DIAMOND : return new CardMap<T>(heart, value, club, spade);
			case CLUB : return new CardMap<T>(heart, diamond, value, spade);
			case SPADE : return new CardMap<T>(heart, diamond, club, value);
		}
		
		throw new RuntimeException("Non-exhaustive switch in : CardMap<T> CardMap<T>.set(CardType, T)");
	}
	
	public Iterable<T> iterate() {
		return new Iterable<T>() {
			public java.util.Iterator<T> iterator() {
				return new java.util.Iterator<T>() {
	
					private CardType type = CardType.HEART;
					
					public boolean hasNext() { return type != null; }
					
					public T next() {
						final CardType val = type;
						type = successor.get(val);
						return get(val);
					}
	
					public void remove() { }
				};
			}
		};
	}
}