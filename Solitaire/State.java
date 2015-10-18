package Solitaire;

import Utils.Iterators;
import Functional.Iterator;

public final class State {
	
	public static final State empty = empty();
	
	public static final State empty() {
		final Iterator<Card> nil = Iterators.nil();
		
		return new State(nil, nil,  new CardMap<Integer>(0, 0, 0, 0), 
				new Seven<Iterator<Card>>(nil, nil, nil, nil, nil, nil, nil));
	}
	
	public final Iterator<Card> deck;
	public final Iterator<Card> dealt;
	public final CardMap<Integer> results;
	public final Seven<Iterator<Card>> stacks;
	
	public State(
			final Iterator<Card> deck, 
			final Iterator<Card> dealt, 
			final CardMap<Integer> results, 
			final Seven<Iterator<Card>> stacks) {
		this.deck = deck;
		this.dealt = dealt;
		this.results = results;
		this.stacks = stacks;
	}
	
	public State deal() {
		if(deck.more()) {
			
			final State s1 = new State(deck.rest(), Iterators.cons(deck.current(), dealt), results, stacks);
			
			if(!s1.deck.more()) return s1;
			
			final State s2 = new State(s1.deck.rest(), Iterators.cons(s1.deck.current(), dealt), s1.results, s1.stacks);
						
			if(!s2.deck.more()) return s2;

			return new State(s2.deck.rest(), Iterators.cons(s2.deck.current(), dealt), s2.results, s2.stacks);
		
		} else {
			return new State(Iterators.reverse(dealt), Iterators.<Card>nil(), results, stacks);
		}
	}
}