package edu.miracosta.cs113;

public class Lab1
{
	public static void main(String[] args)
	{
		// Loops from 1 to 100 printing out the number Fizz, Buzz or Fizzbuzz
		for (int counter = 1; counter <= 100; counter++)
		{
			if (counter % 3 == 0 && counter % 5 == 0)
			{
				System.out.println("Fizzbuzz");
			}
			else if (counter % 3 == 0)
			{
				System.out.println("Fizz");
			}
			else if (counter % 5 == 0)
			{
				System.out.println("Buzz");
			}
			else
			{
				System.out.println(counter);
			}
		}
	}
}
