package edu.miracosta.cs113;

//Homework No.10 Exercise No.3
//File Name:     RadixDriver.java
//@author:       Kostyantyn Shumishyn
//Date:          November 24, 2017
//
//Problem Statement: Tests the Radix Sort Method
//
//Algorithm:
// 1. Creates an array of 50 random integers
// 2. Uses Radix Sort on them
// 3. Prints

public class RadixDriver
{
	public static void main(String[] args)
	{
		// Creates Array of 50 Integers
		int[] test = new int[50];

		// adds random Integers between 0 and 999
		for (int i = 0; i < 50; i++)
			test[i] = (int) (Math.random() * 1000);
		
		// Performs Radix Sort on random array
		test = RadixSort.radixSort(test);

		// Prints out all values in array
		for (int i = 0; i < test.length; i++)
			System.out.println((i + 1) + ") " + test[i]);
	}
}
