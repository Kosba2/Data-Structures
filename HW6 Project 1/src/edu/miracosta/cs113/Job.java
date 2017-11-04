package edu.miracosta.cs113;

//Homework No.6 Exercise No.1
//File Name:     Job.java
//@author:       Kostyantyn Shumishyn
//Date:          October 10, 2017
//
//Problem Statement: Job class

public class Job
{
	// Constants
	public static final int		DEFAULT_NUM_PAGES	= 1;
	public static final String	DEFAULT_DOC_NAME	= "Print Job";

	// Instance Variables
	private int					numPages;
	private String				document;

	// Default Constructor
	public Job()
	{
		this.setNumPages(DEFAULT_NUM_PAGES);
		this.setDocumentName(DEFAULT_DOC_NAME);
	}

	// Full Constructor
	public Job(int numPages, String docName)
	{
		this.setNumPages(numPages);
		this.setDocumentName(docName);
	}

	// Getters
	// Gets number of pages
	public int getNumPages()
	{
		return numPages;
	}

	// Gets the document's name
	public String getDocumentName()
	{
		return document;
	}

	// Setters
	// Sets number of pages
	public void setNumPages(int numPages)
	{
		this.numPages = numPages;
	}

	// Sets the document's name
	public void setDocumentName(String docName)
	{
		this.document = docName;
	}

	// Utility Methods
	// Returns a Conveniently formatted String of a Job
	public String toString()
	{
		return this.document + " (" + this.numPages + " Pgs.)";
	}

	// Equals method, Testing out Eclipses' Auto-creation of it, makes sense!
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

		// Casts object, if it got this far, should be safe to cast
		Job otherJob = (Job) obj;

		// Checks Document Name
		if (document == null)
		{
			if (otherJob.document != null)
			{
				return false;
			}
		}
		else if (!document.equals(otherJob.document))
		{
			return false;
		}

		// Checks number of Pages
		if (numPages != otherJob.numPages)
		{
			return false;
		}

		return true;
	}

}