package edu.miracosta.cs113;

//Homework No.10 Exercise No.3
//File Name:     RadixSort.java
//@author:       Kostyantyn Shumishyn
//Date:          November 24, 2017
//
//Problem Statement: Creates a Radix Sort API

public class RadixSort
{
	/**
	 * Precondition - Data is assumed to be >= 0 and sorts an array of positive integers.
	 * 
	 * @param data
	 *            The data to be sorted.
	 * @return Returns the sorted array from the provided data.
	 */
	public static int[] radixSort(int[] data)
	{
		// Variables
		int[] counter = new int[10];
		int[] transfer = new int[data.length];
		int maxValue = 0;
		int pass = 0;

		// Sorts
		for (int i = 0; i < data.length; i++)
			if (data[i] > maxValue)
				maxValue = data[i];

		return redistribute(data, counter, transfer, pass, maxValue);
	}

	/**
	 * Helper method to recursively progress through dataset and continue sorting using radix
	 * algorithm.
	 * 
	 * @param data
	 *            The data to be sorted.
	 * @param counter
	 *            Array that stores the number of times a digit appears in the set.
	 * @param transfer
	 *            Array to store the sorted data.
	 * @param pass
	 *            Keeps track of which pass the recursive method is on.
	 * @param maxVal
	 *            Largest value in the data to be used for ending recursive calls.
	 * @return Returns the sorted data.
	 */
	private static int[] redistribute(int[] data, int[] counter, int[] transfer, int pass,
			int maxVal)
	{
		// Calls Helper Methods
		resetCounter(counter);
		countDigits(data, counter, pass);

		// Keeps Counter
		for (int i = 0; i < counter.length - 1; i++)
			counter[i + 1] += counter[i];

		// Core of Redistribution
		for (int i = data.length - 1; i >= 0; i--)
		{
			int value = data[i];
			int modResults = (value / (int) Math.pow(10, pass)) % 10;
			int destination = counter[modResults] - 1;
			transfer[destination] = value;
			counter[modResults]--;
		}

		// Recursively redistributes and keeps track of passes
		if ((maxVal % (int) Math.pow(10, pass)) != maxVal)
		{
			pass++;
			return redistribute(transfer, counter, data, pass, maxVal);
		}

		// Base Case
		else
			return transfer;
	}

	/**
	 * Helper method to reset the counter array to 0.
	 * 
	 * @param data
	 *            The counter array to be reset.
	 */
	private static void resetCounter(int[] data)
	{
		for (int i = 0; i < data.length; i++)
			data[i] = 0;
	}

	/**
	 * Helper method that reads through the data and counts the occurance of the digits at the
	 * desired location.
	 * 
	 * @param data
	 *            Data to be searched through.
	 * @param counter
	 *            Array that keeps track of the occurances of the digit.
	 * @param pass
	 *            The number of sorting attempts conducted.
	 */
	private static void countDigits(int[] data, int[] counter, int pass)
	{
		for (int i = 0; i < data.length; i++)
		{
			int counterLocation = (data[i] / (int) Math.pow(10, pass)) % 10;
			counter[counterLocation]++;
		}
	}
}
