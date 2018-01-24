package edu.miracosta.cs113;

// Homework No.8 Exercise No.1
// File Name:     Heap.java
// @author:       Kostyantyn Shumishyn
// Date:          November 7, 2017
//
// Problem Statement: Abstract Heap Class

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

@SuppressWarnings("serial")
public abstract class Heap<E> extends PriorityQueue<E> implements Comparator<E>
{
	// Instance Variables
	protected ArrayList<E>	theData;
	protected Comparator<E>	comparator	= null;

	// Default Constructor
	public Heap()
	{
		theData = new ArrayList<E>();
	}

	// Creates List for Heap and Instantiates a Comparator
	public Heap(Comparator<E> comp)
	{
		theData = new ArrayList<E>();
		comparator = comp;
	}

	// Implements Priority Queue Offer
	// Pre-Condition: Data is in Heap Order
	// Post-Condition: Data added, still in Heap Order
	@Override
	public boolean offer(E newData)
	{
		// Add the item to the heap.
		theData.add(newData);

		// Child is newData
		int child = theData.size() - 1;

		// Find's Parent's Child
		int parent = (child - 1) / 2;

		// Performs a ReHeap
		while (parent >= 0 && compare(theData.get(parent), theData.get(child)) > 0)
		{
			swap(parent, child);
			child = parent;
			parent = (child - 1) / 2;
		}
		// Returns true per Interface requirement
		return true;
	}

	// Implements Priority Queue Poll
	// Pre-Condition: Data is in Heap Order
	// Post-Condition: Smallest item has been removed, still in Heap Order
	@Override
	public E poll()
	{
		// If List is empty, return null
		if (isEmpty())
			return null;

		// Stores the top of the heap
		E result = theData.get(0);

		// Special case of only one item
		if (theData.size() == 1)
		{
			theData.remove(0);
			return result;
		}

		// Removes the last item
		theData.set(0, theData.remove(theData.size() - 1));

		// The parent is at the top
		int parent = 0;

		while (true)
		{
			// Stores the left child
			int leftChild = 2 * parent + 1;

			// Breaks out of heap, end case
			if (leftChild >= theData.size())
				break;

			// Stores right child
			int rightChild = leftChild + 1;

			// Assumes left child is smaller
			int minChild = leftChild;

			// Check whether right child is smaller
			if (rightChild < theData.size()
					&& compare(theData.get(leftChild), theData.get(rightChild)) > 0)
				minChild = rightChild;

			// minChild is the index of the smaller child now
			// Move smaller child up the heap
			if (compare(theData.get(parent), theData.get(minChild)) > 0)
			{
				swap(parent, minChild);
				parent = minChild;
			}

			// If there is nowhere else to go, you're done
			else
				break;
		}

		// Returns the Polled Data
		return result;
	}

	// Implements Priority Queue Peek
	@Override
	public E peek()
	{
		if (isEmpty())
			return null;

		else
			return theData.get(0);
	}

	// Implements Priority Queue Remove
	@Override
	public E remove()
	{
		// Throws Exception if removing from Empty List
		if (theData.isEmpty())
			throw new NoSuchElementException();

		// Calls Poll
		else
			return poll();
	}

	// Heap toString
	public String toString()
	{
		return theData.toString();
	}

	// Checks whether ArrayList/Data is empty
	@Override
	public boolean isEmpty()
	{
		return theData.isEmpty();
	}

	// Method to Swap a Parent with a Child
	private void swap(int parent, int child)
	{
		E temp = (E) theData.get(parent);
		theData.set(parent, theData.get(child));
		theData.set(child, temp);
	}
	
	// Abstract Compare method
	public abstract int compare(Object firstObject, Object secondObject);
}
