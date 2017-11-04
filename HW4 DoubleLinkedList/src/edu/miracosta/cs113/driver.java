package edu.miracosta.cs113;

//Homework No.4 Exercise No.1
//File Name:     driver.java
//@author:       Kostyantyn Shumishyn
//Date:          September 26, 2017
//
//Problem Statement: Used for testing the Polynomial/Term class' interactions with DoublyLinkedList

public class driver
{
	// Constant Strings for testing purposes
	public static final String	FIRST_POLYNOMIAL	= "-3x^15 + 6x^2 + 0x^4 + 0x^3 + 2x^2 + 3x + 7";
	public static final String	SECOND_POLYNOMIAL	= "0x^4 + 2x^3 + 0x^2 - 4x - 5";

	public static void main(String[] args)
	{

		/*
		 * Tests basic DoublyLinkedList Methods, successful
		 * 
		 * DoublyLinkedList<Term> termList = new DoublyLinkedList<Term>();
		 * 
		 * Term firstTerm = new Term(5, 7); Term secondTerm = new Term(-1, 7); Term thirdTerm = new
		 * Term(5,0);
		 * 
		 * System.out.println(firstTerm); System.out.println(secondTerm);
		 * System.out.println(thirdTerm); System.out.println();
		 * 
		 * termList.add(firstTerm); termList.addFirst(secondTerm);
		 * 
		 * System.out.println(termList.getFirst()); System.out.println(termList.getLast());
		 */

		// Polynomials for Summation's sake
		Polynomial firstPoly = new Polynomial();
		Polynomial secondPoly = new Polynomial();
		Polynomial thirdPoly = new Polynomial();

		// Converts String of Poly into actual Polynomial Class type
		firstPoly.stringToPolynomial(FIRST_POLYNOMIAL);
		secondPoly.stringToPolynomial(SECOND_POLYNOMIAL);

		// Sums above two Polynomials
		thirdPoly = Polynomial.sumPolynomials(firstPoly.getMyPolynomial(),
				secondPoly.getMyPolynomial());

		// Prints Strings of each Polynomial
		System.out.println(firstPoly.toString());
		System.out.println(secondPoly.toString());
		System.out.println(thirdPoly.toString());

	}
}
