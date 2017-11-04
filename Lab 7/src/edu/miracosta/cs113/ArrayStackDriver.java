package edu.miracosta.cs113;

import java.util.EmptyStackException;

//Lab No.7 Exercise No.2
//File Name:     ArrayStackDriver.java
//@author:       Kostyantyn Shumishyn
//Date:          September 29, 2017
//
//Problem Statement: Fiddles with Stacks and Queues
//
//Algorithm:
// 1. Create ArrayStack Object
// 2. Check the Empty Method
// 3. Check the Push Method
// 4. Check the Peek Method
// 5. Clear an ArrayStack by Popping it until it's empty
// 6. Give Feedback that ArrayStack is now empty
// 7. Intentionally trigger EmptyStackException in TryCatchBlock

public class ArrayStackDriver
{
	public static void main(String[] args)
	{
		// Initialization & Declaration
		ArrayStack<String> firstStack = new ArrayStack<String>();

		// Tests the Empty Method
		if (firstStack.empty())
		{
			System.out.println("Stack is Empty\n");
		}

		// Pushed an easily Identifiable List of Strings
		for (int i = 0; i < 10; i++)
		{
			System.out.println("Pushed " + firstStack.push("" + i*10));
		}

		// Tests Peek Method, should be 90
		System.out.println("\nTop of Stack should be 90, is it? " + firstStack.peek() + "\n");

		// Pops entire Stack, prints each term
		while (!firstStack.empty())
		{
			System.out.println("Popped " + firstStack.pop());
		}

		// Notifies that Stack was successfully Emptied
		if (firstStack.empty())
		{
			System.out.println("\nStack is Empty");
		}
		
		// Tests Exception
		try
		{
			System.out.println("\nTrying to peek at Empty Stack... ");
			
			// This will throw the Exception
			System.out.print(firstStack.peek());
		}
		catch (EmptyStackException e)
		{
			// Will print in place of the Peek if Exception is Thrown
			System.out.print("Empty Stack Exception Thrown!");
		}
	}

}