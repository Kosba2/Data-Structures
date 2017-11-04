package edu.miracosta.cs113;

// Homework No.6 Exercise No.1
// File Name:     PrinterDriver.java
// @author:       Kostyantyn Shumishyn
// Date:          October 10, 2017
//
// Problem Statement: Using a Printer and a Job class, emulate a queue of printing jobs
// 
// 
// Algorithm:
// 1. Prompts for User's choice of a number of printers
// 2. Use Helper Method to choose appopriate method to handle print jobs
// 3. Add Jobs to Queue with variable number of pages
// 4. Remove Pages every minute (keeping track of pseudo-time)
// 5. Show feedback in console of what occurs every minute

import java.util.Scanner;
import java.util.Random;

public class PrinterDriver
{
	public static void main(String[] args)
	{
		// Declarations
		int numberPrinters;
		Scanner keyboard = new Scanner(System.in);

		// Prompts User for number of Printers
		System.out.print("Would you like to use 1, 2 or 3 printers?: ");
		numberPrinters = keyboard.nextInt();

		// Makes a Printer
		createPrinter(numberPrinters);

		// Closes Scanner
		keyboard.close();
	}

	// Helper Method chooses which Printer to use
	public static void createPrinter(int numPrinters)
	{
		// Depending on User's choice either chooses number of printers or gives feedback on invalid
		// choice
		if (numPrinters == 1)
		{
			onePrinterQueue();
		}
		else if (numPrinters == 2)
		{
			twoPrinterQueue();
		}
		else if (numPrinters == 3)
		{
			threePrinterQueue();
		}
		// If the user inputs anything but 1 2 or 3
		else
		{
			System.out.println("\nINVALID NUMBER OF PRINTERS, EXITING PROGRAM...");
		}
	}

	// Creates Printing Queues for 100 minutes then waits for them to finish for one printer
	public static void onePrinterQueue()
	{
		// Prints Label for this Printer Variation
		System.out.printf("\n%50s\n", "*** Printing with One Printer ***");

		// Creates Printer
		Printer firstPrinter = new Printer(1, 50);

		// Starts at 1 for formatting and readability
		int minute = 1;

		// Since printers are made from 1st to 100th minute, uses minute as Document # aswell
		String document = "Document #" + minute;

		// Create Print job and add to Queue
		Job printJob = new Job(PrinterDriver.randomPages(1, 50), document);
		firstPrinter.addToQueue(printJob);

		// Handles the Processing for this Printer
		while (firstPrinter.getPrinterQueue().peek() != null)
		{
			System.out.println("\nMinute #" + minute);

			if (minute <= 100)
			{
				System.out.println(" ~ " + printJob + " has been sent to printer #1.");
			}

			// Prints the amount of Pages it can do per minute (Default = 10)
			int firstPrinterPages = firstPrinter.getPrinterQueue().peek().getNumPages()
					- firstPrinter.getPagesPerMinute();

			// Doesn't care if it "Prints pages that aren't there", as it still complete the ones
			// that were
			// Removes Print Job when no pages are left
			if (firstPrinterPages <= 0)
			{
				System.out.println(" ! " + firstPrinter.getPrinterQueue().peek().getDocumentName()
						+ " has printed.");
				firstPrinter.getPrinterQueue().remove();
			}
			// Set Original Pages to current Unprinted Pages
			else
			{
				firstPrinter.getPrinterQueue().peek().setNumPages(firstPrinterPages);
			}

			// Runs the clock forward
			minute++;

			// Creates random new print jobs until the 100 minute mark then stops
			if (minute <= 100)
			{
				document = "Document #" + minute;
				printJob = new Job(PrinterDriver.randomPages(1, 50), document);
				firstPrinter.addToQueue(printJob);
			}
		}
		// Prints how long the entire process took
		printRunTime(minute);
	}

	// Creates Printing Queues for 100 minutes then waits for them to finish for two printers
	public static void twoPrinterQueue()
	{
		// Prints Label for this Printer Variation
		System.out.printf("\n%50s\n", "*** Printing with Two Printers ***");

		// Creates two Printers
		Printer firstPrinter = new Printer(1, 10);
		Printer secondPrinter = new Printer(11, 50);

		// Begins keeping track of time at 1 for formatting and readability
		int minute = 1;

		// Creates new Print Job with minutes for convenient formatting
		String document = "Document #" + minute;

		// Randomly generates a print job
		Job printJob = new Job(PrinterDriver.randomPages(1, 50), document);

		// Intelligently sorts jobs to correct printer
		if (printJob.getNumPages() <= 10)
		{
			firstPrinter.addToQueue(printJob);
		}
		else
		{
			secondPrinter.addToQueue(printJob);
		}

		// Handles the processing for both printers until both finish
		while (firstPrinter.getPrinterQueue().peek() != null
				|| secondPrinter.getPrinterQueue().peek() != null)
		{
			System.out.println("\nMinute #" + minute);

			// Creates Print Jobs until 100th minute
			if (minute <= 100)
			{
				if (printJob.getNumPages() <= 10)
				{
					System.out.println(" ~ " + printJob + " has been sent to printer #1.");
				}
				else
				{
					System.out.println(" ~ " + printJob + " has been sent to printer #2.");
				}
			}

			// Initializes Printer Pages
			int firstPrinterPages = 100;
			int secondPrinterPages = 100;

			// Prints number of pages (default is 10)
			if (firstPrinter.getPrinterQueue().peek() != null)
			{
				firstPrinterPages = firstPrinter.getPrinterQueue().peek().getNumPages()
						- firstPrinter.getPagesPerMinute();
			}

			// Prints number of pages (default is 10)
			if (secondPrinter.getPrinterQueue().peek() != null)
			{
				secondPrinterPages = secondPrinter.getPrinterQueue().peek().getNumPages()
						- secondPrinter.getPagesPerMinute();
			}

			// Checks whether first printer is done
			if (firstPrinterPages <= 0)
			{
				System.out.println(" ! " + firstPrinter.getPrinterQueue().peek().getDocumentName()
						+ " has printed on Printer #1.");
				firstPrinter.getPrinterQueue().remove();
			}
			// Set Original Pages to current Unprinted Pages
			else if (firstPrinterPages != 100)
			{
				firstPrinter.getPrinterQueue().peek().setNumPages(firstPrinterPages);
			}

			// Checks whether second printer is done
			if (secondPrinterPages <= 0)
			{
				System.out.println(" ! " + secondPrinter.getPrinterQueue().peek().getDocumentName()
						+ " has printed on Printer #2.");
				secondPrinter.getPrinterQueue().remove();
			}
			// Set Original Pages to current Unprinted Pages
			else if (secondPrinterPages != 100)
			{
				secondPrinter.getPrinterQueue().peek().setNumPages(secondPrinterPages);
			}

			// Runs the clock forward
			minute++;

			// Creates random new print jobs until the 100 minute mark then stops
			if (minute <= 100)
			{
				document = "Document #" + minute;
				printJob = new Job(PrinterDriver.randomPages(1, 50), document);

				if (printJob.getNumPages() <= 10)
				{
					firstPrinter.addToQueue(printJob);
				}
				else
				{
					secondPrinter.addToQueue(printJob);
				}
			}
		}
		// Prints how long the entire process took
		printRunTime(minute);
	}

	//
	public static void threePrinterQueue()
	{
		// Prints Label for this Printer Variation
		System.out.printf("\n%50s\n", "*** Printing with Three Printers ***");

		// Creates three Printers
		Printer firstPrinter = new Printer(1, 10);
		Printer secondPrinter = new Printer(11, 20);
		Printer thirdPrinter = new Printer(21, 50);

		// Begins keeping track of time at 1 for formatting and readability
		int minute = 1;

		// Creates new Print Job with minutes for convenient formatting
		String document = "Document #" + minute;

		// Randomly generates a print job
		Job printJob = new Job(PrinterDriver.randomPages(1, 50), document);

		// Intelligently sorts jobs to correct printer
		if (printJob.getNumPages() <= 10)
		{
			firstPrinter.addToQueue(printJob);
		}
		else if (printJob.getNumPages() >= 11 && printJob.getNumPages() <= 20)
		{
			secondPrinter.addToQueue(printJob);
		}
		else
		{
			thirdPrinter.addToQueue(printJob);
		}

		// Handles the processing for all three printers until they finish
		while (firstPrinter.getPrinterQueue().peek() != null
				|| secondPrinter.getPrinterQueue().peek() != null
				|| thirdPrinter.getPrinterQueue().peek() != null)
		{
			System.out.println("\nMinute #" + minute);

			// Creates Print Jobs until 100th minute
			if (minute <= 100)
			{
				if (printJob.getNumPages() <= 10)
				{
					System.out.println(" ~ " + printJob + " has been sent to printer #1.");
				}
				else if (printJob.getNumPages() >= 11 && printJob.getNumPages() <= 20)
				{
					System.out.println(" ~ " + printJob + " has been sent to printer #2.");
				}
				else
				{
					System.out.println(" ~ " + printJob + " has been sent to printer #3.");
				}
			}

			// Initializes Printer Pages
			int firstPrinterPages = 100;
			int secondPrinterPages = 100;
			int thirdPrinterPages = 100;

			// Prints number of pages (default is 10)
			if (firstPrinter.getPrinterQueue().peek() != null)
			{
				firstPrinterPages = firstPrinter.getPrinterQueue().peek().getNumPages()
						- firstPrinter.getPagesPerMinute();
			}

			// Prints number of pages (default is 10)
			if (secondPrinter.getPrinterQueue().peek() != null)
			{
				secondPrinterPages = secondPrinter.getPrinterQueue().peek().getNumPages()
						- secondPrinter.getPagesPerMinute();
			}

			// Prints number of pages (default is 10)
			if (thirdPrinter.getPrinterQueue().peek() != null)
			{
				thirdPrinterPages = thirdPrinter.getPrinterQueue().peek().getNumPages()
						- thirdPrinter.getPagesPerMinute();
			}

			// Checks whether first printer is done
			if (firstPrinterPages <= 0)
			{
				System.out.println(" ! " + firstPrinter.getPrinterQueue().peek().getDocumentName()
						+ " has printed on Printer #1.");
				firstPrinter.getPrinterQueue().remove();
			}

			// Set Original Pages to current Unprinted Pages
			else if (firstPrinterPages != 100)
			{
				firstPrinter.getPrinterQueue().peek().setNumPages(firstPrinterPages);
			}

			// Checks whether second printer is done
			if (secondPrinterPages <= 0)
			{
				// if print job is done, remove from queue
				System.out.println(" ! " + secondPrinter.getPrinterQueue().peek().getDocumentName()
						+ " has printed on Printer #2.");
				secondPrinter.getPrinterQueue().remove();
			}

			// Set Original Pages to current Unprinted Pages
			else if (secondPrinterPages != 100)
			{
				secondPrinter.getPrinterQueue().peek().setNumPages(secondPrinterPages);
			}

			// Checks whether third printer is done
			if (thirdPrinterPages <= 0)
			{
				System.out.println(" ! " + thirdPrinter.getPrinterQueue().peek().getDocumentName()
						+ " has printed on Printer #3.");
				thirdPrinter.getPrinterQueue().remove();
			}

			// Set Original Pages to current Unprinted Pages
			else if (thirdPrinterPages != 100)
			{
				thirdPrinter.getPrinterQueue().peek().setNumPages(thirdPrinterPages);
			}

			// Runs the clock forward
			minute++;

			// Creates random new print jobs until the 100 minute mark then stops
			if (minute <= 100)
			{
				document = "Document #" + minute;
				printJob = new Job(PrinterDriver.randomPages(1, 50), document);

				if (printJob.getNumPages() <= 10)
				{
					firstPrinter.addToQueue(printJob);
				}
				else if (printJob.getNumPages() >= 11 && printJob.getNumPages() <= 20)
				{
					secondPrinter.addToQueue(printJob);
				}
				else
				{
					thirdPrinter.addToQueue(printJob);
				}
			}
		}
		// Prints how long the entire process took
		printRunTime(minute);
	}

	// Method to print the run time, catered to offset convenient code in main methods
	public static void printRunTime(int minutes)
	{
		System.out.println("\nThis Printing took " + (minutes - 1) + " minutes.");
	}

	// Given a range, returns a value within that range inclusive
	public static int randomPages(int min, int max)
	{
		Random random = new Random();

		// Offsets to tailour for desired use
		int randomPages = random.nextInt((max - min) + 1) + min;

		return randomPages;
	}
}