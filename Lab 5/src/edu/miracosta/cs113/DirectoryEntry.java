package edu.miracosta.cs113;

import java.util.ArrayList;

public class DirectoryEntry
{
	String name;
	String number;

	DirectoryEntry(String name, String number)
	{
		this.name = name;
		this.number = number;
	}
	public String getNumber()
	{
		return number;
	}
	public void setNumber(String number)
	{
		this.number = number;
	}
	public boolean equals(DirectoryEntry other)
	{
		if (name.equals(other.name))
		{
			return true;
		}
		return false;
	}

	private ArrayList<DirectoryEntry> theDirectory = new ArrayList<DirectoryEntry>();

	// Programming Exercise 1
	/**
	 * Add an entry to theDirectory or change an existing entry.
	 * 
	 * @param aName
	 *            The name of the person being added or changed
	 * @param newNumber
	 *            The new number to be assigned
	 * @return The old number , or if a new entry, null
	 */
	public String addOrChangeEntry(String aName, String newNumber)
	{
		for (DirectoryEntry d : theDirectory)
		{
			if (d.equals(new DirectoryEntry(aName, newNumber)))
			{
				String oldNumber = d.getNumber();
				d.setNumber(newNumber);
				return oldNumber;
			}
		}
		theDirectory.add(new DirectoryEntry(aName, newNumber));
		return null;
	}

	// Programming Exercise 2
	/**
	 * Remove an entry.
	 * 
	 * @param directoryName
	 *            The name of the person being removed
	 * @return The entry removed, or null if there is no entry for aName
	 */
	public DirectoryEntry removeEntry(String directoryName)
	{
		for (DirectoryEntry d : theDirectory)
		{
			if (d.equals(new DirectoryEntry(directoryName, "")))
			{
				theDirectory.remove(d);
				return d;
			}
		}
		return null;
	}
}