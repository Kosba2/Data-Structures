package edu.miracosta.cs113;

public class Lab2Powers 
{
	public boolean isPowerTwo (int givenValue)
	{
		// Declaration
		boolean isPowerTwo;
		int tempNum;
		int exponent;
		
		// Initialization
		isPowerTwo = true;
		tempNum = givenValue;
		exponent = 0;
		
		// Testing Value
		//System.out.println("Your Given Value is " + givenValue);
		
		// Divides until it reaches 1, builds exponent value
		while (tempNum != 1)
		{
			tempNum /= 2;
			exponent++;
		}
		
		// Testing value
		//System.out.println("Your Exponent is " + exponent);
		
		// Retroactively multiplies number to check it against original
		for (int i = 0; i < exponent; i++)
		{
			tempNum *= 2;
			
			// Testing Value
			//System.out.println("TempNum is " + tempNum + " on loop " + i);
		}
		
		// Checks value back against original
		if (tempNum != givenValue)
		{
			isPowerTwo = false;
		}

		
		return isPowerTwo;
	}
}
