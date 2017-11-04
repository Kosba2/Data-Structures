package edu.miracosta.cs113;

public class Lab2Driver
{
	public static final int TEST_VALUE = 64;
	
	public static void main(String[] args)
	{
		Lab2Powers testCase = new Lab2Powers();
		
		System.out.println("Your number is " + testCase.isPowerTwo(TEST_VALUE));
	}
}
