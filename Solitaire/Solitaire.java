package Solitaire;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Utils.Iterators;
import Functional.Iterator;

@SuppressWarnings("serial")
public class Solitaire {

	
	
	
	public static final Scanner keyboard = new Scanner(System.in);
	
	public static final void main(final String... args) {

		final PlayerEmpty player = initPlayer();
		
	}
		
	
	
	
	
		
	private static final List<Card> allCards =  new ArrayList<Card>(52) {
		{
			for(final CardType type : CardMap.identity.iterate())
				for(int value = 0; value < 13; value++)
					add(new Card(type, value));
		}
	};
	
	
	private static PlayerEmpty initPlayer() {

		java.util.Collections.shuffle(allCards);
		
		final java.util.Iterator<Card> it = allCards.iterator();
		
		State start = State.empty;
		for(int i = 0; i < 7; i++)
			for(int j = i; j < 7; j++)
				start = new State(start.deck, start.dealt, start.results, start.stacks.set(j, Iterators.cons(it.next(), start.stacks.get(j))));
		
		while(it.hasNext())
			start = new State(Iterators.cons(it.next(), start.deck), start.dealt, start.results, start.stacks);
		
		return new PlayerEmpty(start);
	}
	
	
	
	
	
	
	

	private static class PlayerWithCard {
		
		private final State state;
		private final Card inHand;
		private final LastCard putBack;
		
		public PlayerWithCard(final State state, final Card inHand, LastCard putBack) {
			this.state = state;
			this.inHand = inHand;
			this.putBack = putBack;
		}
		
		public PlayerEmpty putFinal() {
			final int next = state.results.get(inHand.type);
			
			if(inHand.value != next) return null;
			
			return new PlayerEmpty(
					new State(state.deck, state.dealt, state.results.set(inHand.type, next+1), state.stacks));
		}
		
		public PlayerEmpty putInto(final int index) {

			final Iterator<Card> stack = state.stacks.get(index);
			
			if(stack.more()) {
				final Card top = stack.current();
				
				if(Card.colors.get(inHand.type) == Card.colors.get(top.type)) return null;
				if(inHand.value != top.value - 1) return null;
			}
						
			return new PlayerEmpty(
					new State(state.deck, state.dealt, state.results, state.stacks.set(index, Iterators.cons(inHand, stack))));
		}
		
		public PlayerEmpty putBack() {
			return new PlayerEmpty(this.putBack.put(inHand, state));
		}
	}
	
	private static interface LastCard {
		public State put(final Card card, final State state);
	}
	
	private static class LastFinal implements LastCard {
		private final CardType type;
		
		public LastFinal(final CardType type) {
			this.type = type;
		}
		
		public State put(final Card card, final State state) {
			return new State(state.deck, state.dealt, state.results.set(type, state.results.get(type)+1), state.stacks);
		}
	}
	
	private static class LastStack implements LastCard {
		private final int stack;
		
		public LastStack(final int stack) {
			this.stack = stack;
		}
		
		public State put(final Card card, final State state) {
			return new State(state.deck, state.dealt, state.results, state.stacks.set(stack, Iterators.cons(card, state.stacks.get(stack))));	
		}
	}
	
	private static class LastDealt implements LastCard {
		
		public State put(final Card card, final State state) {
			return new State(state.deck, Iterators.cons(card, state.dealt), state.results, state.stacks);
		}
	}
	
	private static class PlayerEmpty {

		private final State state;
		public PlayerEmpty(final State state) {
			this.state = state;
		}
		
		public PlayerEmpty deal() {
			return new PlayerEmpty(state.deal());
		}
	
		public PlayerWithCard takeDealt() {
			if(!state.dealt.more()) return null;
			
			return new PlayerWithCard(
					new State(state.deck, state.dealt.rest(), state.results, state.stacks), 
					state.dealt.current(),
					new LastDealt());
		}

		public PlayerWithCard takeFinal(final CardType from) {
			final int next = state.results.get(from);
			
			if(next == 0) return null;
			
			return new PlayerWithCard(
					new State(state.deck, state.dealt, state.results.set(from, next-1), state.stacks),
					new Card(from, next-1),
					new LastFinal(from));
		}

		public PlayerWithCard takeFrom(final int index) {
			final Iterator<Card> stack = state.stacks.get(index);
			
			if(!stack.more()) return null;
			
			return new PlayerWithCard(
					new State(state.deck, state.dealt, state.results, state.stacks.set(index, stack.rest())),
					stack.current(),
					new LastStack(index));
		}
	}
}
