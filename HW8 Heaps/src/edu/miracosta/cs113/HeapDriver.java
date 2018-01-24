package edu.miracosta.cs113;

// Homework No.8 Exercise No.1
// File Name:     HeapDriver.java
// @author:       Kostyantyn Shumishyn
// Date:          November 7, 2017
//
// Problem Statement: Tests Min and Max Heaps by offering a list of disordered
// values into each one, and then polling each list until it is empty
// to see what order they come back out in from the Priority Queue.
//
// Algorithm:
// 1. Adds to Min Heap out of order
// 2. Prints the Poll from the Min Heap, preferably in expected order
// 3. Repeat same for Max Heap

public class HeapDriver
{
	public static void main(String[] args)
	{
		// Instantiates Heaps
		MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
		MinHeap<Integer> minHeap = new MinHeap<Integer>();
		

		// Tests adding to the Min Heap
		System.out.println("Adding 0, 10, 30, 20, 10, 15 to Min Heap...\n");
		minHeap.offer(0);
		minHeap.offer(10);
		minHeap.offer(30);
		minHeap.offer(20);
		minHeap.offer(10);
		minHeap.offer(15);

		// Prints to User to demonstrate order
		System.out.println("Polling all items from the Min Heap to display to the user");
		while (!minHeap.isEmpty())
			System.out.println(minHeap.poll());
		
		
		// Tests adding to the Max Heap
		System.out.println("\nAdding 0, 10, 30, 20, 10, 15 to Max Heap...\n");
		maxHeap.offer(0);
		maxHeap.offer(10);
		maxHeap.offer(30);
		maxHeap.offer(20);
		maxHeap.offer(10);
		maxHeap.offer(15);

		// Prints to User to demonstrate order
		System.out.println("Polling all items from the Max Heap to display to the user");
		while (!maxHeap.isEmpty())
			System.out.println(maxHeap.poll());
		
	}

}
