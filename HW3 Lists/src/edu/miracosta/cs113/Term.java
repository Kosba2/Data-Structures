package edu.miracosta.cs113;

public class Term implements Comparable<Object>
{
	// Constants
	public static final int	DEFAULT_COEFFICIENT	= 0;
	public static final int	DEFAULT_EXPONENT	= 0;

	// Instance Variables
	private int				coefficient;
	private int				exponent;

	// Default Constructor
	public Term()
	{
		this.setTerm(DEFAULT_COEFFICIENT, DEFAULT_EXPONENT);
	}

	// Full Constructor
	public Term(int termCoefficient, int termExponent)
	{
		this.setTerm(termCoefficient, termExponent);
	}

	// Copy Constructor
	public Term(Term otherTerm)
	{
		this(otherTerm.getCoefficient(), otherTerm.getExponent());
	}

	// Sets Exponent and Coefficient
	public void setTerm(int newCoefficient, int newExponent)
	{
		this.setCoefficient(newCoefficient);
		this.setExponent(newExponent);
	}

	// Gets Coefficient
	public int getCoefficient()
	{
		return this.coefficient;
	}

	// Sets Coefficient
	public void setCoefficient(int newCoefficient)
	{
		this.coefficient = newCoefficient;
	}

	// Gets Exponent
	public int getExponent()
	{
		return this.exponent;
	}

	// Sets Exponent
	public void setExponent(int newExponent)
	{
		this.exponent = newExponent;
	}

	// To String Method
	@Override
	public String toString()
	{
		// Formatting Variable
		String result = "";

		// Puts a positive sign for positive numbers
		if (this.coefficient > 0)
		{
			result = "+ ";
		}

		// Returns nothing if 0 coefficient
		else if (this.coefficient == 0)
		{
			return "";
		}

		// Adds coefficient for positive numbers != 1
		if (this.coefficient > 0 && this.coefficient != 1)
		{
			result += this.coefficient;
		}

		// If a negative number
		else
		{
			result += "- ";

			if (this.coefficient < -1)
			{
				result += Math.abs(this.coefficient);
			}
		}

		// Checks for Exponent being 1
		if (this.exponent == 1)
		{
			result += "x";
		}

		// Checks for exponent not being 0
		else if (this.exponent != 0)
		{
			result += "x^" + this.exponent;
		}

		// Separates the Terms
		result += " ";

		return result;

	}

	@Override
	public int compareTo(Object otherObject)
	{
		return Integer.compare(this.exponent, ((Term) otherObject).exponent);
	}

}