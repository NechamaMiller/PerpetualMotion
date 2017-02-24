package perpetualMotion;

import java.util.Stack;

public class PerpetualMotion
{
	private Deck theDeck;
	private Stack<Card>[] stacks;
	
	public PerpetualMotion()
	{
		theDeck = new Deck();
		theDeck.shuffle();
	
		stacks = (Stack<Card>[])new Stack[4];
		for (int i=0; i<stacks.length; i++)
		{
			stacks[i] = new Stack<Card>();
			stacks[i].push(theDeck.deal());
		}	
	}
	
	/**
	 * Acts like a toString()
	 */
	public String display()
	{
		StringBuilder sb = new StringBuilder();
		int remainingCards = theDeck.getRemainingCards();
		
		for (int i=0; i<stacks.length; i++)
		{
			if (!(stacks[i].isEmpty()))
			{
				sb.append("The " + stacks[i].peek() + " is on top of stack number " + (i+1) + ".\n");
				remainingCards += stacks[i].size();
			}
			else
			{
				sb.append("Stack number " + (i+1) + " is empty.\n");
			}
		}
			
		sb.append("There are " + theDeck.getRemainingCards() + " cards left in the deck and a total of " + remainingCards + " cards still to discard in order to win.");
		
		return sb.toString();
	}
	
	/**
	 * discards top card from both stacks indicated if their ranks match
	 * @param stackOneIdex index of first stack to discard from
	 * @param stackTwoIndex index of second stack to discard from
	 * @return true if cards were discarded or false otherwise. Will also return false if stack at one of stackIndexes was empty
	 * @throws IllegalArgumentException if index parameters are equal or either index parameter is less than 0 or more than 3
	 */
	public boolean discard(int stackOneIndex, int stackTwoIndex)
	{
		if (stackOneIndex == stackTwoIndex)
		{
			throw new IllegalArgumentException("Indexes cannot be equal.");
		}
		
		if (stackOneIndex<0 || stackOneIndex>(stacks.length-1) || stackTwoIndex<0 || stackTwoIndex>(stacks.length-1))
		{
			throw new IllegalArgumentException("Indexes must be between 0 and " + (stacks.length-1) + ".");
		}
		
		if (stacks[stackOneIndex].isEmpty() || stacks[stackTwoIndex].isEmpty())
		{
			return false;
		}
		
		if (stacks[stackOneIndex].peek().getRank().getVal() == (stacks[stackTwoIndex].peek().getRank().getVal()))
		{
			stacks[stackOneIndex].pop();
			stacks[stackTwoIndex].pop();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * discards top card from indicated stack if the top card of another stack has the same suit with a higher rank
	 * @param stackIndex index of stack to discard from
	 * @return true if card was discarded, false otherwise. Will also return false if stack at stackIndex was empty
	 * @throws IllegalArgumentException if index is less than 0 or more than 3
	 */
	public boolean discard(int stackIndex)
	{	
		if (stackIndex<0 || stackIndex>(stacks.length-1))
		{
			throw new IllegalArgumentException("Index cannot be less than 0 or more than " + (stacks.length-1) + ".");
		}
		if (stacks[stackIndex].isEmpty())
		{
			return false;
		}
		Card card1 = stacks[stackIndex].peek();
		
		for (int i=0; i<stacks.length; i++)
		{
			//it is possible for one stack to be empty when others aren't because there can be a different amount of cards in different stacks depending how many times discard() was called on that stack			
			if (!(stacks[i].isEmpty()))
			{
				Card card2 = stacks[i].peek();
				if (i != stackIndex && card1.getSuit().equals(card2.getSuit()) && card1.getRank().getVal() < card2.getRank().getVal())
				{
					stacks[stackIndex].pop();
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Game is won if deck and all stacks are empty
	 * @return true if won, false otherwise
	 */
	public boolean gameWon()
	{
		if (!(theDeck.isEmpty()))
		{
			return false;
		}
		
		for (Stack<Card> stack: stacks)
		{
			if (!(stack.isEmpty()))
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Deals a new card on top of each stack, unless the deck of cards is empty
	 * @return true if cards were dealt, false if deck was empty and no cards were dealt
	 */
	public boolean deal()
	{
		if (theDeck.isEmpty())
		{
			return false;
		}
		
		//I am checking if the deck is empty before every card even though I already checked in case the number of cards in the deck wouldn't be a multiple of 4 so I don't introduce nulls
		for (int i=0; i<stacks.length && (!(theDeck.isEmpty())); i++)
		{
			stacks[i].push(theDeck.deal());
		}		
		
		return true;//even if less than 4 cards were dealt, should still return true
	}
	
	//even though there is a display method, I put in a toString() because users often expect it
	public String toString()
	{
		return display();
	}
}
