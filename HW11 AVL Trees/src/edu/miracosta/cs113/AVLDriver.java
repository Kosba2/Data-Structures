package edu.miracosta.cs113;

// Homework No.11 Exercise No.1
// File Name:     AVLDriver.java
// @author:       Kostyantyn Shumishyn
// Date:          November 27, 2017
//
// Problem Statement: Tests out Binary Search Tree and AVL Tree
//
// Algorithm:
// 1. Create Binary Search Tree Object
// 2. Create AVL Tree Object
// 3. Add from list to both trees
// 4. Print out both trees and compare balance

public class AVLDriver
{
	public static void main(String[] args)
	{
		// Creates a Binary Search Tree
		BST<Integer> binarySearchTree = new BST<Integer>();

		// Creates an AVL Tree
		AVLTree<Integer> avlTree = new AVLTree<Integer>();

		// Creates an Array of 20 Elements
		int[] data = new int[20];

		// Creates list of random numbers up to 20
		for (int i = 0; i < data.length; i++)
			data[i] = (int) Math.floor(Math.random() * 21);

		// Adds list to Binary Search TRee
		for (int i = 0; i < data.length; i++)
			binarySearchTree.add(data[i]);

		// Prints Binary Search Tree
		System.out.println(binarySearchTree.toString());

		// Adds list to AVL Tree
		for (int i = 0; i < data.length; i++)
			avlTree.add(data[i]);

		// Prints out AVL Tree
		System.out.println(avlTree.toString());
	}
}
