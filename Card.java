package perpetualMotion;

public class Card
{
	private Rank rank;
	private Suit suit;
	private Color color;
	
	/**
	 * constructor
	 * @param rank value for card's rank - must be valid from list of Rank enums
	 * @param suit value for card's suit - must be valid from list of Suit enums
	 * @param color value for card's color - must be valid from list of Color enums 
	 * @throws NullPointerException if rank, suit or color is null
	 */ 
	public Card(Rank rank, Suit suit, Color color)
	{
		if (rank==null)
		{
			throw new NullPointerException("Rank cannot be null.");
		}
		if (suit==null)
		{
			throw new NullPointerException("Suit cannot be null.");
		}
		if (color==null)
		{
			throw new NullPointerException("Color cannot be null.");
		}

		this.rank = rank;
			
		this.suit = suit;
		
		this.color = color;
	}
	
	public Rank getRank()
	{
		return rank;
	}
	
	public Suit getSuit()
	{
		return suit;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(rank.toString().toLowerCase());
		sb.append(" of ");
		sb.append(suit.toString().toLowerCase());
		
		return sb.toString();
	}
}
