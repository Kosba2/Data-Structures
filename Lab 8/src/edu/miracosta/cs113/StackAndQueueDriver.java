package edu.miracosta.cs113;

//Lab No.8 Exercise No.1-3
//File Name:     StackAndQueueDriver.java
//@author:       Kostyantyn Shumishyn
//Date:          September 27, 2017
//
//Problem Statement: Fiddles with Stacks and Queues

import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class StackAndQueueDriver
{
	// Probably not best implementation of concept, but wanted to try it out
	public static final boolean testing = true;

	public static void main(String[] args)
	{
		// Declarations
		Stack<Integer> firstStack = new Stack<Integer>();
		Stack<Integer> secondStack = new Stack<Integer>();
		Queue<Integer> firstQueue = new LinkedList<Integer>();

		// Initialization of pushing integers to a stack
		firstStack.push(-1);
		firstStack.push(15);
		firstStack.push(23);
		firstStack.push(44);
		firstStack.push(4);
		firstStack.push(99);
		
		// Only runs if testing
		if (testing)
		{
			// Prints original stack for comparison
			System.out.println("Original first Stack: " + firstStack + "\n");
		}

		// Problem #1: Prints out top of stack
		System.out.println("Problem #1:\nObject at top of Stack: " + firstStack.peek() + "\n");

		// #2 Store first Stack into other Stack and Queue
		int firstStackSize = firstStack.size();
		for (int index = 0; index < firstStackSize; index++)
		{
			// Takes off first stack to push to other stack
			int tempInt = firstStack.pop();
			secondStack.push(tempInt);
			firstQueue.offer(tempInt);
		}

		// Only runs if testing
		if (testing)
		{			
			System.out.println("Problem #2:");
			
			// Checks to make sure first Stack is empty
			System.out.println("Is the first stack empty? " + firstStack);

			// Checks secondStack, should be reverse
			System.out.println("Second Stack should have come out reverse? " + secondStack);

			// Checks firstQueue, should be reverse
			System.out.println("First Queue should have come out reverse? " + firstQueue + "\n");
		}

		// Problem #3: Pop/Poll Print formatted
		System.out.println("Problem #3: ");
		System.out.printf("%15s %15s\n", "Stack", "Queue");
		System.out.printf("%15s %15s\n", "-----", "-----");
		
		// Both have same size, so this is merely a convenience
		int bothSizes = secondStack.size();
		for (int jndex = 0; jndex < bothSizes; jndex++)
		{
			System.out.printf("%14s %15s\n", secondStack.pop(), firstQueue.poll());
		}
	}
}
