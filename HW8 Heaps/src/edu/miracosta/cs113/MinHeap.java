package edu.miracosta.cs113;

// Homework No.8 Exercise No.1
// File Name:     MinHeap.java
// @author:       Kostyantyn Shumishyn
// Date:          November 7, 2017
//
// Problem Statement: Child of Abstract Heap, implements Min Heap Compare

@SuppressWarnings("serial")
public class MinHeap<E> extends Heap<E>
{
	// Compares two items using the Comperator Object's Compare Method OR
	// their natural ordering using CompareTo, depending on which one is
	// available.
	@SuppressWarnings("unchecked")
	@Override
	public int compare(Object firstObject, Object secondObject)
	{
		// Defines a Comparator
		if (comparator != null)
			return comparator.compare((E) firstObject, (E) secondObject);
		
		// Use Left's compareTo Method
		else
			return ((Comparable<E>) firstObject).compareTo((E) secondObject);
	}
}
