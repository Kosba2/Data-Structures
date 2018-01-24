package edu.miracosta.cs113;

// Homework No.11 Exercise No.1
// File Name:     BSTWithRotate.java
// @author:       Kostyantyn Shumishyn
// Date:          November 27, 2017
//
// Problem Statement: Binary Search Tree Child

@SuppressWarnings("serial")
public class BSTWithRotate<E extends Comparable<E>> extends BST<E>
{
	/**
	 * Rotates the tree to the left from the local root node.
	 */
	protected Node<E> rotateLeft(Node<E> localRoot)
	{
		// Stores the Right Root temporarily
		Node<E> temp = localRoot.right;

		// Moves Left root right
		localRoot.right = temp.left;

		// Moves Local Root left
		temp.left = localRoot;

		// Makes Right Root the Local Root
		localRoot = temp;

		// Returns the new Local Root
		return localRoot;
	}

	/**
	 * Rotates the tree to the right from the local root node.
	 */
	protected Node<E> rotateRight(Node<E> localRoot)
	{
		// Stores the Left Root temporarily
		Node<E> temp = localRoot.left;

		// Moves the Right root left
		localRoot.left = temp.right;

		// Moves the Local Root right
		temp.right = localRoot;

		// Makes the Left Root the Local Root
		localRoot = temp;

		// Returns the new Local Root
		return localRoot;
	}
}
