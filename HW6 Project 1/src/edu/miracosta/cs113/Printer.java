package edu.miracosta.cs113;

//Homework No.6 Exercise No.1
//File Name:     Job.java
//@author:       Kostyantyn Shumishyn
//Date:          October 10, 2017
//
//Problem Statement: Printer class

import java.util.LinkedList;
import java.util.Queue;

public class Printer
{
	// Constants
	public static final int	PAGES_PER_MINUTE	= 10;
	public static final int	MIN_PAGES			= 1;
	public static final int	MAX_PAGES			= 50;

	// Instance Variables
	private int				minPages;
	private int				maxPages;
	private int				pagesPerMinute;
	private Queue<Job>		printerQueue;

	// Default Constructor
	public Printer()
	{
		this.setMaxPages(MAX_PAGES);
		this.setMinPages(MIN_PAGES);
		this.setPagesPerMinute(PAGES_PER_MINUTE);
		this.setPrinterQueue();
	}

	// Full Constructor
	public Printer(int minPages, int maxPages)
	{
		this.setMaxPages(maxPages);
		this.setMinPages(minPages);
		this.setPagesPerMinute(PAGES_PER_MINUTE);
		this.setPrinterQueue();
	}

	// Getters
	// Gets Max Pages
	public int getMaxPages()
	{
		return maxPages;
	}

	// Gets Min Pages
	public int getMinPages()
	{
		return minPages;
	}

	// Get Pages Per Minute
	public int getPagesPerMinute()
	{
		return pagesPerMinute;
	}

	// Gets Printer Queue
	public Queue<Job> getPrinterQueue()
	{
		return printerQueue;
	}
	// Setters
	// Sets Max Pages
	public void setMaxPages(int maxPages)
	{
		this.maxPages = maxPages;
	}

	// Sets Min Pages
	public void setMinPages(int minPages)
	{
		this.minPages = minPages;
	}

	// Sets Pages Per Minute
	public void setPagesPerMinute(int pagesPerMinute)
	{
		this.pagesPerMinute = pagesPerMinute;
	}

	// Sets Printer Queue
	public void setPrinterQueue()
	{
		this.printerQueue = new LinkedList<Job>();
	}

	// Utility Methods
	// Adds jobs to the Queue
	public void addToQueue(Job printJob)
	{
		this.printerQueue.add(printJob);
	}

	// To String Method for Convenient
	public String toString()
	{
		String queueString = "";

		while (printerQueue.size() != 0)
		{
			int counter = 0;

			if (counter == 0)
			{
				queueString = "" + printerQueue.poll();
			}
			else
			{
				queueString += ", " + printerQueue.poll();
			}

			counter++;
		}

		return "Printer has a range of " + this.minPages + "-" + this.maxPages + "."
				+ "\nCurrent Queue: " + queueString;
	}

	// Equals Method, trying out Autogenerate, Seems good!
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}

		// Should be safe to cast if it gets this far
		Printer otherPrinter = (Printer) obj;

		// Compares important aspects of a Printer
		if (maxPages != otherPrinter.maxPages)
		{
			return false;
		}

		if (minPages != otherPrinter.minPages)
		{
			return false;
		}

		return true;
	}

}