package edu.miracosta.cs113;

// Homework No.6 Exercise No.2
// File Name:     CoinCombinationsDriver.java
// @author:       Kostyantyn Shumishyn
// Date:          October 10, 2017
// 
// Problem Statement: Uses a recursive algorithm to break down an amount of change into
// every single combination possible using basic US coin denominations.
//
// Algorithm:
// 1. Input a change amount
// 2. Call Kickstart/Wrapper Method to make prerequisite formatting and have a means
//    of calling the preliminary conditions of the main method
// 3. Starting with the largest denomination possible, print out the combination
// 4. Each recursive call, breaks and recombines coins to create all possible combinations
//    of coins, gradually breaking down the largest ones into smaller ones.
// 5. Base case is everything has been broken down into pennies.
// 6. At each recursive call 1 is also returned, thereby keeping a count of each time a
//    combination is printed, and finally on the base case 1 more is added and returned
//    to the Kickstart Method, to be printed back to user.

public class CoinCombinationsDriver
{

	public static void main(String[] args)
	{
		// Declaration
		int change;

		// Initialization
		change = 75;

		// Prints Combinations
		printCombinationOfCoins(change);
	}

	// Kickstart Method to handle Table Formatting that shouldn't be recursed
	public static void printCombinationOfCoins(int cents)
	{
		// Prints out top of Table
		System.out.printf("%30s", "Combinations\n");

		// Prints out Divider
		for (int i = 0; i < 48; i++)
		{
			System.out.print("-");
		}
		System.out.println();

		// Prints out the Coin Combinations
		System.out.println(
				"\nTotal Number of Combinations of Coins: " + coinCombinations(cents, 0, 0, 0, 0));
	}

	// Finds the number of combinations of change based on the number amount of change due
	public static int coinCombinations(int change, int quarters, int dimes, int nickels,
			int pennies)
	{
		// Base Case : The last combination where everything has become nothing but pennies
		// Return : Because you still have to account for the all pennies case
		if (change == pennies)
		{
			System.out.printf("%5d %12d %12d %12d\n", quarters, dimes, nickels, pennies);
			return 1;
		}
		// Other Layers
		else
		{
			// Original change variable for repeated use
			int oldChange = change;

			// Starts with the largest denomination and works its way down to pure pennies
			// First Time
			if (quarters == 0 && dimes == 0 && nickels == 0 && pennies == 0)
			{
				// Outputs the top of the Table
				System.out.printf("%5s %10s %14s %12s\n", "Quarters", "Dimes", "Nickels",
						"Pennies");

				// Sets number of quarters
				quarters = change / 25;
				change %= 25;

				// Sets number of dimes
				dimes = change / 10;
				change %= 10;

				// Sets number of nickels
				nickels = change / 5;
				change %= 5;

				// Sets number of pennies
				pennies = change;

				// Doesn't return 1 + recursive call because that's the job of the else condition
				return coinCombinations(oldChange, quarters, dimes, nickels, pennies);
			}

			// Other Times
			else
			{
				// Outputs previous combination
				System.out.printf("%5d %12d %12d %12d\n", quarters, dimes, nickels, pennies);

				// Tackle Nickels first because they only break down into Pennies
				if (nickels > 0)
				{
					// Each recursive call takes away a Nickel and adds 5 pennies and prints the
					// combinations above
					nickels -= 1;
					pennies += 5;

					// Returns 1 + the recursive call to account for this combination that was just
					// simplified
					return 1 + coinCombinations(oldChange, quarters, dimes, nickels, pennies);
				}
				// Tackles Dimes next and breaks down into Nickels AND Pennies to account for both
				else if (dimes > 0)
				{
					// Each recursive call takes away a Dime and adds 2 nickels and prints the
					// combination above
					dimes -= 1;
					nickels += 2;

					// Converts Pennies to Nickels to make sure all cases are handled
					nickels += (pennies / 5);
					pennies %= 5;

					// Returns 1 + the recursive call to account for these combinations that were
					// just simplified
					return 1 + coinCombinations(oldChange, quarters, dimes, nickels, pennies);
				}
				// Finally handles Quarters, because what else
				else if (quarters > 0)
				{
					// Each recursive call takes away a Quarter and adds 2 dimes and 1 nickel and
					// prints the combination above
					quarters -= 1;
					dimes += 2;
					nickels += 1;

					// Converts Pennies to Nickels to make sure all cases are handled
					nickels += (pennies / 5);
					pennies %= 5;

					// Converts Nickels into Dimes to make sure all cases are handled
					dimes += (nickels / 2);
					nickels %= 2;

					// Returns 1 + the recursive call to account for these combinations that were
					// just simplified twice
					return 1 + coinCombinations(oldChange, quarters, dimes, nickels, pennies);
				}
			}
		}
		// Pleases compiler
		return 0;

	}

}
