package edu.miracosta.cs113;

//Homework No.5 Exercise No.1
//File Name:     PalindromeDriver.java
//@author:       Kostyantyn Shumishyn
//Date:          October 3, 2017
//
//Problem Statement: Uses Stacks to Verify whether a String is a Palindrome
//
//Algorithm:
//1. Read in Input of a Phrase from user
//2. Clean the Phrase String to raw letter data
//3. Add entire phrase, char by char, to first Stack
//4. Pop half of the first stick (truncated to < 50%) by pushing into the second stack
//5. Check whether original phrase was Even or Odd, if Odd, pop last term on first stack
//6. Use Static Equals Method to Compare each Character in each Stack
//7. Use counter to keep track of how many chars match
//8. If counter matches size of one of the stacks, then every character has a match
//	 and is therefore a palindrome.

import java.util.Scanner;

public class PalindromeDriver
{
	public static void main(String[] args)
	{
		// Declaration and Initialization
		ArrayStack<Character> firstHalf = new ArrayStack<Character>();
		ArrayStack<Character> secondHalf = new ArrayStack<Character>();
		Scanner kb = new Scanner(System.in);
		String phrase;
		String cleanedPhrase;

		// Takes in an Input of a Phrase from user
		System.out.print("Please enter a phrase: ");
		phrase = kb.nextLine();
		kb.close();

		// Replaces all non-letter characters with empty strings to form
		// uninterrupted lowercase "cleaned" string
		cleanedPhrase = phrase.replaceAll("\\W", "").toLowerCase();

		// Tokenizes each letter into stack
		int cleanedPhraseSize = cleanedPhrase.length();
		for (int i = 0; i < cleanedPhraseSize; i++)
		{
			firstHalf.push(cleanedPhrase.charAt(i));
		}

		// Pops top half of first stack into second stack, reversing its order
		for (int j = 0; j < cleanedPhraseSize / 2; j++)
		{
			secondHalf.push(firstHalf.pop());
		}

		// Determines whether the phrase was even or odd in length
		// If odd because the Second Half took half the length integer truncated
		// then First Half will have the extra term in its list, so pops it off
		// the list because it isn't relevant to the comparison
		if ((cleanedPhraseSize % 2) == 1)
		{
			// Removes useless Character
			firstHalf.pop();
		}

		// Everything beyond this point comes out Even, suitable for Comparing
		// Stacks now

		// Calls the comparison Method which specializes in comparing equal sized stacks of characters 
		if (PalindromeDriver.isPalindrome(firstHalf, secondHalf))
		{
			System.out.println("\n\"" + phrase + " is a palindrome.");
		}
		else
		{
			System.out.println("\n\"" + phrase + "\" isn't a palindrome.");
		}
	}

	// Helper Method
	// Equals method utilizes Stack Pops to compare the characters of each one
	// Precondition: Stacks must be equal size
	public static boolean isPalindrome(ArrayStack<Character> stack1,
			ArrayStack<Character> stack2)
	{
		// Declaration
		char firstChar;
		char secondChar;


		// First Stack isn't empty relies on the precondition handled by the
		// driver where the Stacks are equal length, "Not my Job" essentially
		while (!stack1.empty())
		{
			// Stores the characters temporarily for Comparison
			firstChar = stack1.pop();
			secondChar = stack2.pop();

			// Wanted to do Bit-wise comparison to be cheeky, would this have
			// been okay?
			// counter += (firstChar == secondChar) ? 1 : 0;

			// Only increments when they are the same char
			if (firstChar != secondChar)
			{
				return false;
			}
		}
		return true;
	}

}