package edu.miracosta.cs113;

//Homework No.10 Exercise No.1
//File Name:     FileMergeSort.java
//@author:       Kostyantyn Shumishyn
//Date:          November 24, 2017
//
//Problem Statement: Creates a File Merge API

import java.io.PrintWriter;
import java.util.Scanner;

public class FileMergeSort
{
	/**
	 * Precondition: Assume file1 and file2 are sorted and streams open. Performs merge-sort on the
	 * provided files.
	 * 
	 * @param file1
	 *            File to be merge-sorted with file 2.
	 * @param file2
	 *            File to be merge-sorted with file 1.
	 */
	public static void fileMergeSort(Scanner file1, Scanner file2, PrintWriter destination)
	{
		// Items from the file
		Integer file1Item = null;
		Integer file2Item = null;

		// While both files have not reached end
		while (file1.hasNext() && file2.hasNext())
		{
			// If the current file1 item is null store it
			if (file1Item == null)
				file1Item = file1.nextInt();

			// If the current file2 item is null store it
			if (file2Item == null)
				file2Item = file2.nextInt();

			// If file1Item is greater than file2Item
			if (file1Item < file2Item)
			{
				destination.println("" + file1Item);
				file1Item = null;
			}

			// If file2Item is greater than file1Item
			else
			{
				destination.println("" + file2Item);
				file2Item = null;
			}
		}

		// While File1 has a next
		while (file1.hasNext())
		{
			// Stores null
			if (file1Item == null)
				file1Item = file1.nextInt();

			// Prints the File
			destination.println("" + file1Item);
			file1Item = null;

		}

		// While File2 has a next
		while (file2.hasNext())
		{
			// Stores null
			if (file2Item == null)
				file2Item = file2.nextInt();

			// Prints the File
			destination.println("" + file2Item);
			file2Item = null;
		}
	}

	/**
	 * Performs a recursive merge-sort on an integer array assuming the array is not empty.
	 * 
	 * @param data
	 *            The array to be sorted.
	 * @return Returns the sorted array.
	 */
	public static int[] intArrayMergeSort(int[] data)
	{
		// Base Case
		if (data.length == 1)
			return data;

		// Recursive Case
		else
		{
			// Stores the halfway point
			int half = data.length / 2;
			int counter = 0;
			int[] leftHalf = new int[half];
			int[] rightHalf = new int[data.length - half];

			// Loops up to the half and stores into left data array
			for (int i = 0; i < half; i++)
				leftHalf[i] = data[i];

			// Loops from half to capacity and stores into right data array
			for (int i = half; i < data.length; i++)
			{
				rightHalf[counter] = data[i];

				// Increments counter
				counter++;
			}
			// Recursively breaks up each half into smaller halves
			leftHalf = intArrayMergeSort(leftHalf);
			rightHalf = intArrayMergeSort(rightHalf);

			// Merge Sorts both halfs
			return mergeSort(leftHalf, rightHalf);
		}
	}

	/**
	 * Performs the merge-sorting of arraysn assuming a and b are sorted
	 * 
	 * @param a
	 *            Array to be merged with b.
	 * @param b
	 *            Array to be merged with a.
	 * @return Returns merge-sorted array.
	 */
	public static int[] mergeSort(int[] a, int[] b)
	{
		// Variables
		int[] sorted = new int[a.length + b.length];
		boolean isSorting = true;
		int aCounter = 0;
		int bCounter = 0;
		int sortedCounter = 0;

		// If finished with array a, return rest of array b.
		if (a.length == 0)
			return b;

		// If finished with array b, return rest of array a.
		if (b.length == 0)
			return a;

		// Sorting Loop
		while (isSorting)
		{
			// Compares each term of A to B
			// b greater than a
			if (a[aCounter] < b[bCounter])
			{
				sorted[sortedCounter] = a[aCounter];
				aCounter++;
			}
			// a greater than b
			else
			{
				sorted[sortedCounter] = b[bCounter];
				bCounter++;
			}

			// Counter keeping track of sortings
			sortedCounter++;

			// Recognize finished with array a
			if (aCounter == a.length)
			{
				// Finish off array b
				while (bCounter < b.length)
				{
					sorted[sortedCounter] = b[bCounter];
					bCounter++;
					sortedCounter++;
				}
			}
			// Recognize finished with array b
			else if (bCounter == b.length)
			{
				// Finish off array a
				while (aCounter < a.length)
				{
					sorted[sortedCounter] = a[aCounter];
					aCounter++;
					sortedCounter++;
				}
			}
			// Endcase for Sorting Loop
			if (sortedCounter == sorted.length)
				isSorting = false;

		}
		// Returns the Sorted Sub-Array
		return sorted;
	}

}
