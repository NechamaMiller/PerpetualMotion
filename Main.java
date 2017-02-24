//this code was written and tested by Nechama Miller

package perpetualMotion;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		PerpetualMotion game = new PerpetualMotion();
		Scanner input = new Scanner(System.in);
		
		greeting();
		
		int menuChoice = 0;
		boolean gameOver = false;
		
		do
		{
			display(game);
			
			menuChoice = menu(input);
			
			System.out.println();
			
			switch (menuChoice)
			{
				case 1:
					if (discardTwoCards(game, input))
					{
						System.out.println("Your two cards were discarded.");
					}
					else //if the cards picked to be discarded was invalid, the method will return false
					{
						System.out.println("Sorry, the cards you picked were unable to be discarded.");
					}
					break;
				
				case 2:
					if (discardOneCard(game, input))
					{
						System.out.println("Your card was discarded.");
					}
					else
					{
						System.out.println("Sorry, the card you picked was unable to be discarded.");
					}
					break;	
				
				case 3://Presumably, if the user is trying to deal, he has no more moves to do, so he loses if the deck is empty
					if (!game.deal())
					{
						System.out.println("There are no more cards to deal from. You lose! Better luck next time...");
						gameOver = true;
					}
					break;
				
				default:
					gameOver = true;
			}
		}
		while (!game.gameWon() && !gameOver);
		
		if (game.gameWon())//this shouldn't print if user quits by choosing 0
		{
			System.out.println("\nYou finished all the cards! You win!");
		}
		
		System.out.println("Thanks for playing Idiot's Delight! Goodbye.");
	}
	
	public static void greeting()
	{
		System.out.println("Welcome to Perpetual Motion. Here are the rules of the game:\n"
				+ "You will be presented with four stacks of cards, each of which starts with one card.\n"
				+ "You can only see the top card in the pile at any point in the game.\n"
				+ "On each turn, you will have the choice to discard the top card of 2 stacks, discard the top card of 1 stack, or deal again.\n"
				+ "In order to discard the top card of 2 stacks, both cards must have the same rank.\n"
				+ "For example, one can be a 5 of diamonds and the other can be a 5 of clubs.\n"
				+ "In order to discard the top card of 1 stack, that card must have the same suit and a lower rank than another top card.\n"
				+ "For example, if there is a 3 of spades and a 4 of spades, you can choose to discard the 3 of spades.\n"
				+ "Keep in mind that in this game, ace equals 1 and jack, queen, and king all equal 10.\n"
				+ "This means that, for example if there is a jack on top of one pile and a ten on top of another, both cards can be discarded.\n"
				+ "If you have nothing else to do, you can choose to deal again, which will put a new card on the top of each stack.\n"
				+ "When you finish all the cards in the deck and in all the stacks, you win! But be careful! If you try to deal from an empty deck, you lose!\n"
				+ "Good luck!");
	}
	
	public static void display(PerpetualMotion game)
	{
		System.out.println("\nThis is what your game looks like right now:");
		System.out.println(game.display());
	}

	public static int menu(Scanner input)
	{
		System.out.print("\nWhat would you like to do?"
				+ "\n1. Discard 2 top cards with same the rank"
				+ "\n2. Discard a top card with the same suit and a lower rank than another card"
				+ "\n3. Deal again"
				+ "\n0. Exit game"
				+ "\nEnter choice here:");
		
		int menuChoice = input.nextInt();
		
		while (menuChoice < 0 || menuChoice > 3)
		{
			System.out.print("You did not enter a number between 0 and 3. Please try again: ");
			menuChoice = input.nextInt();
		}
		
		return menuChoice;
	}
	
	public static boolean discardTwoCards(PerpetualMotion game, Scanner input)
	{
		System.out.print("Enter number of first stack to discard from:");
		int index1 = input.nextInt();
		
		while(index1<1 || index1>4)
		{
			System.out.print("You did not enter a number between 1 and 4. Please try again:");
			index1 = input.nextInt();
		}
		
		System.out.print("Enter number of second stack to discard from:");
		int index2 = input.nextInt();
		
		while(index2<1 || index2>4 || index2==index1)
		{
			System.out.print("You did not enter a number between 1 and 4, or you entered the same number for both stacks. Please try again:");
			index2 = input.nextInt();
		}

		return game.discard(index1-1, index2-1);
	}
	
	public static boolean discardOneCard(PerpetualMotion game, Scanner input)
	{
		System.out.print("Enter number of stack to discard from:");
		int stackIndex = input.nextInt();	
		
		while(stackIndex<1 || stackIndex>4)
		{
			System.out.print("You did not enter a number between 1 and 4. Please try again:");
			stackIndex = input.nextInt();
		}
		
		return game.discard(stackIndex-1);
	}
}
