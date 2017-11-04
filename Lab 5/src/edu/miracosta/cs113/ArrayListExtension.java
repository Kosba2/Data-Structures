package edu.miracosta.cs113;

import java.util.ArrayList;

public class ArrayListExtension
{
	
	public static void main(String[] args)
	{
		System.out.println("crap");
	}
	
	public static void replace(ArrayList<String> aList, String oldItem,
			String newItem)
	{
		int index = aList.indexOf(oldItem);

		if (index == -1)
		{
			System.out.println("\nTarget is not in array list try again!");
		}
		else
		{
			aList.set(index, newItem);
		}
	}

	public static void delete(ArrayList<String> aList, String target)
	{
		int index = aList.indexOf(target);

		if (index == -1)
		{
			System.out.println("\nTarget is not in array list try again!");
		}
		else
		{
			aList.remove(index);
		}
	}
	
}