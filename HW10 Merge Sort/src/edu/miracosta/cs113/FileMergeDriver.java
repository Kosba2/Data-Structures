package edu.miracosta.cs113;

//Homework No.10 Exercise No.1
//File Name:     FileMergeDriver.java
//@author:       Kostyantyn Shumishyn
//Date:          November 24, 2017
//
//Problem Statement: Offers a File Merge Sort Utility
//
//Algorithm:
// 1. Prompts user what file they wish to sort or a default test file
// 2. Asks whether they wish to overwrite that file or make new one
// 3. Performs Merge Sort of Files

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileMergeDriver
{
	// Global Variables
	private static final int	NUMBER_TO_READ	= 10;
	private static int			data[];
	private static Scanner		scannerMainFile	= null;
	private static Scanner		outputFileIn	= null;
	private static Scanner		outputA			= null;
	private static Scanner		outputB			= null;
	private static PrintWriter	writer			= null;

	public static void main(String[] args)
	{
		// Variables
		Scanner userIn = new Scanner(System.in);
		String response = "";
		boolean isEntering = true;

		String file = "";
		String outputFileA = "outputA.txt";
		String outputFileB = "outputB.txt";
		String destinationFile = "";

		System.out
				.println("Enter the path of the file of integers to be sorted or enter \"default\" "
						+ "\n(w/o quotes) to use the toBeSorted file in the project folder.");
		file = userIn.nextLine();
		System.out.println();

		// If the user chose Default, use default file
		if (file.toLowerCase().equals("default"))
			file = "toBeSorted.txt";

		// Tries to find Main File to Sort
		try
		{
			scannerMainFile = new Scanner(new FileInputStream(file));
		}

		// File Not Found Exception
		catch (FileNotFoundException e)
		{
			System.out.println("File not found. Program closing..." + e.getMessage());
			System.exit(0);
		}

		// Tries to call writer on the two output files
		try
		{
			writer = new PrintWriter(new FileOutputStream(outputFileA));
			writer = new PrintWriter(new FileOutputStream(outputFileB));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		// Closes File
		writer.close();

		// Goes through main file
		while (scannerMainFile.hasNext())
		{
			// Reads in Data
			data = readInData(NUMBER_TO_READ);

			// Sorts Data
			data = FileMergeSort.intArrayMergeSort(data);

			// Writes Data to First Output File
			writeFile("outputA.txt");

			if (scannerMainFile.hasNext())
			{
				data = readInData(NUMBER_TO_READ);
				data = FileMergeSort.intArrayMergeSort(data);
				writeFile("outputB.txt");

			}
		}

		// Finishes reading File
		scannerMainFile.close();

		// Error Checks the user's desired output preference.
		while (isEntering)
		{
			System.out.println("Enter \"Same\" to overwrite the datafile with the sorted data or "
					+ "\nEnter \"New\" to store data in new file titled \"destination.\"");

			response = userIn.nextLine();

			// If choice is "same"
			if (response.equalsIgnoreCase("same"))
			{
				destinationFile = file;
				isEntering = false;
			}

			// If choice is "new"
			else if (response.equalsIgnoreCase("new"))
			{
				destinationFile = "destination.txt";
				isEntering = false;
			}

			// Invalid choice
			else
			{
				System.out.println("Invalid entry. Try again.");
			}
		}

		// Closes reading in
		userIn.close();

		// Writes to Files w/ Error checking
		try
		{
			writer = new PrintWriter(new FileOutputStream(destinationFile));
			outputA = new Scanner(new FileInputStream(outputFileA));
			outputB = new Scanner(new FileInputStream(outputFileB));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found: " + e.getMessage());
			System.out.println("Program closing");
			System.exit(0);
		}

		// Merges the Files together and closes streams
		FileMergeSort.fileMergeSort(outputA, outputB, writer);
		writer.close();
		outputA.close();
		outputB.close();
		System.out.println("\nFile Merge-sort is complete.");
	}

	/**
	 * Helper Method that reads in data from the main file to be sorted.
	 * 
	 * @param reads
	 *            Number of integers to read from file.
	 * @return Returns an arraylist of the items read from file.
	 */
	private static int[] readInData(int reads)
	{
		ArrayList<Integer> newData = new ArrayList<Integer>();
		for (int i = 0; i < reads; i++)
		{
			newData.add(scannerMainFile.nextInt());
			if (!scannerMainFile.hasNext())
				i = reads;
		}
		return toArray(newData);
	}

	/**
	 * Helper method that converts ArrayList<Integer> to an integer array.
	 * 
	 * @param data
	 *            ArrayList to be converted to array
	 * @return returns int array version of data
	 */
	private static int[] toArray(ArrayList<Integer> data)
	{
		int[] newData = new int[data.size()];
		for (int i = 0; i < data.size(); i++)
			newData[i] = data.get(i);

		return newData;
	}

	/**
	 * Helper method that writes the data to file.
	 * 
	 * @param fileName
	 *            Name of the file to be written to.
	 */
	private static void writeFile(String fileName)
	{
		// Variables
		boolean fileFound = false;
		ArrayList<Integer> fileData = new ArrayList<Integer>();
		int[] newData;

		// Error handling to check if file exists
		try
		{
			outputFileIn = new Scanner(new FileInputStream(fileName));
			fileFound = true;
		}

		catch (FileNotFoundException e)
		{
			fileFound = false;
		}

		// If the file is found
		if (fileFound)
		{
			// Adds data into List
			while (outputFileIn.hasNext())
				fileData.add(new Integer(outputFileIn.next()));

			// Closes Stream
			outputFileIn.close();
		}

		// Puts data from list to array
		newData = toArray(fileData);

		// Merge sorts array data
		data = FileMergeSort.mergeSort(data, newData);

		// Error handles Printwriting
		try
		{
			writer = new PrintWriter(new FileOutputStream(fileName));
		}

		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		// Prints Data
		for (int i = 0; i < data.length; i++)
			writer.println("" + data[i]);

		// Closes Stream
		writer.close();
	}

}
