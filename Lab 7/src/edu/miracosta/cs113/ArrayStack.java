package edu.miracosta.cs113;

//Lab No.7 Exercise No.1
//File Name:     ArrayStack.java
//@author:       Kostyantyn Shumishyn
//Date:          September 27, 2017
//
//Problem Statement: Creates an ArrayStack

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayStack<E> implements StackInterface<E>
{
	// Instance Variables
	private ArrayList<E> data;
	int topOfStack = -1;
	
	// Constructor
	public ArrayStack()
	{
		// Initializes a Default Constructor for ArrayList
		data = new ArrayList<E>();
	}
	
	// Pushes an Object onto the top of the stack
	public E push(E obj) 
	{
		data.add(obj);
		
		// Returns Object for feedback
		return obj;
	}

	// Pops an Object from the top of the stack
	// Throws EmptyStack Exception if Stack is empty (duh)
	public E pop() 
	{
		// Can't Pop if Stack is Empty
		if(this.empty())
		{
			throw new EmptyStackException();
		}
		
		// Removes Top of Stack
		return data.remove(data.size() - 1);
	}

	// Peeks at the top of the stack and returns the data
	// Throws EmptyStack Exception if Stack is empty (dur)
	public E peek() 
	{
		// Nothing to look at if Stack is Empty
		if(this.empty())
		{
			throw new EmptyStackException();
		}
		
		// Returns the Data at the Top of Stack
		return data.get(data.size() - 1);
	}

	// Uses ArrayList Empty Method to determine if Stack is Empty
	// True if it is, false otherwise
	public boolean empty() 
	{
		return data.isEmpty();
	}

}