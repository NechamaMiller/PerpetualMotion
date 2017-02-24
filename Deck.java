package perpetualMotion;

import java.util.ArrayList;
import java.util.Random;

public class Deck
{
	private ArrayList<Card> cards;
	private final int STARTING_NUM_CARDS = 52;
	
	public Deck()
	{		
		cards = new ArrayList<Card>(STARTING_NUM_CARDS);
		
		for (int i=0; i<cards.size(); i++)
		{
			Rank rank;
			Suit suit;
			Color color;
			
			rank = Rank.values()[(i%13)];
			
			if(i<13)
			{
				suit = Suit.HEARTS;
				color = Color.RED;
			}
			else if(i<26)
			{
				suit = Suit.DIAMONDS;
				color = Color.RED;
			}
			else if (i<39)
			{
				suit = Suit.SPADES;
				color = Color.BLACK;
			}
			else
			{
				suit = Suit.CLUBS;
				color = Color.BLACK;
			}
			
			cards.add(i, new Card(rank,suit,color));
		}
	}
	
	public void shuffle()
	{
		Random rand = new Random(System.currentTimeMillis());
		
		for (int i=0; i<cards.size(); i++)
		{
			int swapIndex = rand.nextInt(cards.size());
			Card cardToSwap = cards.get(swapIndex);
			
			cards.set(swapIndex, cards.get(i));
			cards.set(i, cardToSwap);
		}
	}
	
	/**
	 * removes top card from deck and returns it
	 * @return top card of deck or null if deck is empty
	 */
	public Card deal()
	{
		if (!isEmpty())
		{
			return cards.remove(cards.size()-1);
		}
		else
		{
			return null;
		}
	}
	
	public boolean isEmpty()
	{
		return cards.isEmpty();
	}
	
	public int getRemainingCards()
	{
		return cards.size();
	}
	
	public String toString()
	{
		if (cards.size() == 0)
		{
			return "Deck is empty.";
		}
		
		StringBuilder sb = new StringBuilder();		
		int cardNumber = 1;
		
		for (Card card: cards)
		{
			sb.append("Card number " + cardNumber++ + ": " + card + "\n");
		}
		
		return sb.toString();
	}	
}
