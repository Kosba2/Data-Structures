package edu.miracosta.cs113;


/**
 * RandomClue.java : Random solver for the Clue problem
 * 
 * @author Nery Chapeton-Lamas (material from Kevin Lewis)
 * @version 1.0
 *
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RandomClue {

	/**
	 * Tester for random assistant theory checker
	 * 
	 * @param args
	 *            command line arguments (unused)
	 */
	public static void main(String[] args) {
		int answerSet, solution, murder, weapon, location;
		Scanner keyboard = new Scanner(System.in);
		Theory answer = null;
		AssistantJack jack;

		System.out.print("Which theory would like you like to test? (1, 2, 3[random]): ");
		answerSet = keyboard.nextInt();
		keyboard.close();

		jack = new AssistantJack(answerSet);

		Random random = new Random();

		// My Variables
		ArrayList<Integer> murdersGuessed = new ArrayList<>();
		ArrayList<Integer> locationsGuessed = new ArrayList<>();
		ArrayList<Integer> weaponsGuessed = new ArrayList<>();

		do {

			//guess
			murder = guess(murdersGuessed, random, 6);
			location = guess(locationsGuessed, random, 10);
			weapon = guess(weaponsGuessed, random, 6);

			solution = jack.checkAnswer(weapon, location, murder);

			//switch-case
			switch (solution)
			{
				case 1:
					weaponsGuessed.add(weapon);
					break;
				case 2:
					locationsGuessed.add(location);
					break;
				case 3:
					murdersGuessed.add(murder);
					break;
			}

		} while (solution != 0);


		answer = new Theory(weapon, location, murder);
		System.out.println("Total Checks = " + jack.getTimesAsked() + ", Solution = " + answer);

		if (jack.getTimesAsked() > 20) {
			System.out.println("FAILED!! You're a horrible Detective...");
		} else {
			System.out.println("WOW! You might as well be called Batman!");
		}

	}


	public static int guess(ArrayList<Integer> wrongGuesses, Random random, int bound)
	{
		int guess;
		boolean sameGuess;
		
		do
		{
			sameGuess = false;
			guess = random.nextInt(bound) + 1;

			for(Integer i : wrongGuesses)
				if (guess == i)
					sameGuess = true;

		} while (sameGuess);

		return guess;
	}

}