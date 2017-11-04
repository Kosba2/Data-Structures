package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class AddPolynomials
{
	public static final String	FIRST_POLYNOMIAL	= "-3x^15 + 0x^4 + 0x^3 + 2x^2 + 3x + 7";
	public static final String	SECOND_POLYNOMIAL	= "0x^4 + 2x^3 + 0x^2 - 4x - 5";

	public static void main(String[] args)
	{
		/*
		 * Example Problem: 3x^4 + 0x^3 + 2x^2 + 3x + 7 + 0x^4 + 2x^3 + 0x^2 +
		 * 4x + 5 ------------------------------ 3x^4 + 2x^3 + 2x^2 + 7x + 12
		 */

		// Creates ArrayList for each Polynomial and one for the Result
		ArrayList<Term> firstPolyList;
		ArrayList<Term> secondPolyList;
		ArrayList<Term> summedPolyList;

		// Takes apart and stores value of Polynomials into lists
		firstPolyList = polynomialToArrayList(FIRST_POLYNOMIAL);
		secondPolyList = polynomialToArrayList(SECOND_POLYNOMIAL);

		// Uses the Implemented Comparable aspect of Term as a definition of how
		// to sort
		firstPolyList.sort(null);
		secondPolyList.sort(null);

		// Uses the Reverse Method to sort it from Greatest to Largest
		Collections.reverse(firstPolyList);
		Collections.reverse(secondPolyList);

		// Performs the Summation and stores into new List
		summedPolyList = addPolynomials(firstPolyList, secondPolyList);

		// Prints the outcome if there was a list to begin with
		if (summedPolyList.size() != 0)
		{
			// Looks at the first term for proper formatting
			String firstTerm = summedPolyList.get(0).toString();

			// Removes the first operator if it's a plus
			if ((firstTerm.charAt(0) + "").equals("+"))
			{
				System.out.print(firstTerm.substring(1, firstTerm.length()));
			}

			// If it's a minus, then keep it
			else
			{
				System.out.print(firstTerm.substring(0, firstTerm.length()));
			}

			// Prints the Polynomial back out correctly
			for (int i = 1; i < summedPolyList.size(); i++)
			{
				System.out.print(summedPolyList.get(i).toString());
			}
		}
	}

	// Polynomial String to Arraylist
	public static ArrayList<Term> polynomialToArrayList(String polynomial)
	{
		// Declares Arraylist to Store Terms into
		ArrayList<Term> termList = new ArrayList<>();

		// Variables
		String cleanPolynomial;

		// Cleans a provided Polynomial of White Spaces
		cleanPolynomial = polynomial.replaceAll("\\s+", "");
		Scanner scan = new Scanner(cleanPolynomial);

		while (scan.hasNext())
		{
			// Defaults to 1
			int coefficient = 0;
			int exponent = 0;

			// Sets Delimiter to X to just read the coefficient
			scan.useDelimiter("x");

			// Makes sure there is a number before the X
			if (scan.hasNextInt())
			{
				coefficient = scan.nextInt();
			}

			// Removes Delimiter until next run
			scan.useDelimiter("");

			// Checks for X
			if (scan.hasNext()
					&& Objects.equals(scan.next().toLowerCase(), "x"))
			{
				// Copies the cursor location of scanner for the sake of peaking
				// ahead
				// and advancing to do a check, and still being able to step
				// back to the
				// original location
				String replicaString = scan.nextLine();
				scan = new Scanner(replicaString);
				Scanner replicaScan = new Scanner(replicaString);

				scan.useDelimiter("");
				replicaScan.useDelimiter("");

				// Checks for Carrot
				if (Objects.equals(replicaScan.next(), "^"))
				{
					scan.next();
					scan.useDelimiter("\\+|-");
					exponent = scan.nextInt();
				}
				// If no carrot and just X, then Exponent is 1
				else
				{
					exponent = 1;
				}
				
				replicaScan.close();
			}
			// Creates terms of each cycle, then stores into Arraylist
			Term tempTerm = new Term(coefficient, exponent);
			termList.add(tempTerm);
		}
		scan.close();
		return termList;
	}

	public static ArrayList<Term> addPolynomials(ArrayList<Term> firstList,
			ArrayList<Term> secondList)
	{
		// Creates result List
		ArrayList<Term> resultList = new ArrayList<>();

		// Creates Two Iterators, one for each list
		Iterator<Term> firstIter = firstList.iterator();
		Iterator<Term> secondIter = secondList.iterator();

		while (firstIter.hasNext() && secondIter.hasNext())
		{
			// Variables
			Term firstTerm;
			Term secondTerm;

			// Fetches the Terms on each List
			firstTerm = firstIter.next();
			secondTerm = secondIter.next();

			// Stores Value of Exponents
			int firstExponent = firstTerm.getExponent();
			int secondExponent = secondTerm.getExponent();

			// Stores Value of Coefficients
			int firstCoefficient = firstTerm.getCoefficient();
			int secondCoefficient = secondTerm.getCoefficient();

			// Compares Exponents and advances through lists until they match
			while (firstExponent != secondExponent)
			{
				if (firstExponent > secondExponent)
				{
					resultList.add(firstTerm);
					firstTerm = firstIter.next();
					firstExponent = firstTerm.getExponent();
					firstCoefficient = firstTerm.getCoefficient();
				}
				else
				{
					resultList.add(secondTerm);
					secondTerm = secondIter.next();
					secondExponent = secondTerm.getExponent();
					secondCoefficient = secondTerm.getCoefficient();
				}
			}
			// When the exponents finally match, add them to list as an addition
			// of coefficients in new term
			resultList.add(new Term(firstCoefficient + secondCoefficient,
					firstExponent));
		}
		return resultList;
	}

}