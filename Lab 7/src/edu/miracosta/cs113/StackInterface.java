package edu.miracosta.cs113;

//Lab No.7 Exercise No.1
//File Name:     StackInterface.java
//@author:       Kostyantyn Shumishyn
//Date:          September 27, 2017
//
//Problem Statement: Creates an Interface for Stacks

public interface StackInterface<E> 
{
	// Stub for the Push method that returns what was added to the Top of Stack
	public E push(E obj);
	
	// Stub for the Pop method that returns what was removed from Top of Stack
	public E pop();
	
	// Stub for the Peek method that returns the Top of the Stack
	public E peek();
	
	// Stub for the Empty method that returns whether Stack is empty
	public boolean empty();

}