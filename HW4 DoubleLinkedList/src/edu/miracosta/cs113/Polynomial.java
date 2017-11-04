package edu.miracosta.cs113;

//Homework No.4 Exercise No.1
//File Name:     Polynomial.java
//@author:       Kostyantyn Shumishyn
//Date:          September 26, 2017
//
//Problem Statement: Creates a Polynomial Class which uses DoublyLinkedLists to store Terms

import java.util.ListIterator;
import java.util.Objects;
import java.util.Scanner;

public class Polynomial
{
	// Instance Variable List
	private DoublyLinkedList<Term> myPolynomial = new DoublyLinkedList<Term>();

	// Default Constructor
	public Polynomial()
	{
		myPolynomial = new DoublyLinkedList<Term>();
	}

	// Full Constructor
	public Polynomial(DoublyLinkedList<Term> newPoly)
	{
		myPolynomial = newPoly;
	}

	// Adds a term to end of myPolynomial
	public boolean addTerm(Term newTerm)
	{
		return myPolynomial.add(newTerm);
	}

	public DoublyLinkedList<Term> getMyPolynomial()
	{
		return myPolynomial;
	}

	public void stringToPolynomial(String polynomial)
	{
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
			if (scan.hasNext() && Objects.equals(scan.next().toLowerCase(), "x"))
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
			myPolynomial.add(tempTerm);
		}
		scan.close();
	}

	public static Polynomial sumPolynomials(DoublyLinkedList<Term> firstList,
			DoublyLinkedList<Term> secondList)
	{
		// Creates result List
		DoublyLinkedList<Term> resultList = new DoublyLinkedList<Term>();

		// Creates Two Iterators, one for each list
		ListIterator<Term> firstIter = firstList.listIterator(0);
		ListIterator<Term> secondIter = secondList.listIterator(0);

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
			resultList.add(new Term(firstCoefficient + secondCoefficient, firstExponent));
		}

		// Creates new Polynomial to return
		Polynomial result = new Polynomial(resultList);

		return result;
	}

	// Suspect there is something wrong with Get that causes output to be incorrect because
	// debugging seems to imply that data does enter myPolynomial, so toString is simply failing
	// to access it maybe?
	public String toString()
	{
		String result = "";

		for (int index = 0; index < myPolynomial.size(); index++)
		{
			result += myPolynomial.get(index).toString();
			index++;
		}

		return result;
	}
}
