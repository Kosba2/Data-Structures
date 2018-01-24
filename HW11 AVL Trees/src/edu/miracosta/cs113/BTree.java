package edu.miracosta.cs113;

// Homework No.11 Exercise No.1
// File Name:     BTree.java
// @author:       Kostyantyn Shumishyn
// Date:          November 27, 2017
//
// Problem Statement: Binary Tree Class

import java.io.Serializable;
import java.util.Scanner;

@SuppressWarnings("serial")
public class BTree<E> implements Serializable
{
	// Instance Variable Root Node
	protected Node<E> root;

	/**
	 * Default Constructor which instantiates a BinaryTree Object.
	 */
	public BTree()
	{
		root = null;
	}

	/**
	 * Partial Constructor with a root only.
	 * 
	 * @param root
	 *            Node to be used as the Tree's root.
	 */
	protected BTree(Node<E> root)
	{
		this.root = root;
	}

	/**
	 * Full Constructor which has a root and two children.
	 * 
	 * @param data
	 *            Data to be set as root node.
	 * @param leftTree
	 *            Binary tree to be used as the root node's left subtree.
	 * @param rightTree
	 *            Binary tree to be used as the root node's right subtree.
	 */
	public BTree(E data, BTree<E> leftTree, BTree<E> rightTree)
	{
		// Creates a new Node to call root
		root = new Node<E>(data);

		// If the left tree isn't null get the root
		if (leftTree != null)
			root.left = leftTree.root;

		// If the left tree is null get the root is null
		else
			root.left = null;

		// If the right tree isn't null get the root
		if (rightTree != null)
			root.right = rightTree.root;

		// If the right tree is null get the root is null
		else
			root.right = null;
	}

	/**
	 * Gets the tree's left subtree.
	 *
	 * @return Returns the tree's left subtree or null if the left subtree is empty.
	 */
	public BTree<E> getLeftSubtree()
	{
		// Returnss left subtree if it isn't null
		if (root != null && root.left != null)
			return new BTree<E>(root.left);

		// Returns null if it is
		else
			return null;
	}

	/**
	 * Gets the tree's right subtree.
	 *
	 * @return Returns the tree's right subtree or null if the right subtree is empty.
	 */
	public BTree<E> getRightSubtree()
	{
		// Returns right subtree if it isn't null
		if (root != null && root.right != null)
			return new BTree<E>(root.right);

		// Returns null if it is
		else
			return null;
	}

	/**
	 * Gets the data from the root node.
	 *
	 * @return Returns the root node's data or null if the node is null.
	 */
	public E getData()
	{
		// Returns root subtree if it isn't null
		if (root != null)
			return root.data;

		// Returns null if it is
		else
			return null;
	}

	/**
	 * Identifies whether the node is a leaf node.
	 * 
	 * @return Returns true if the node is a leaf node, else false.
	 */
	public boolean isLeaf()
	{
		return (root.left == null && root.right == null);
	}

	/**
	 * Provides a preorder traversal string representation of entire binary tree.
	 * 
	 * @return Returns a preorder traversal string representation of binary tree.
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}

	/**
	 * performs preorder traversal of tree recursively.
	 * 
	 * @param node
	 *            root of tree being traversed
	 * 
	 * @param depth
	 *            current level of tree
	 * 
	 * @param sb
	 *            string being appended to be returned as string representation of tree
	 * 
	 * @return returns sb as preordered traversal string representation of tree
	 */
	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb)
	{
		// Uses Tab to Indent tree
		for (int i = 1; i < depth; i++)
			sb.append("\t");

		// If Node is null print Null
		if (node == null)
			sb.append("null\n");

		// If Node isn't null print that node and traverse its children
		else
		{
			sb.append(node.toString() + "\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}

	/**
	 * Constructs Binary Tree by reading data from Scanner.
	 * 
	 * @param <T>
	 * 
	 * @param scan
	 *            The scanner being read from.
	 * @return Returns the Binary tree created from the scanner.
	 */
	public static <E> BTree<E> readBinaryTree(Scanner scan)
	{
		// Stores next String
		String data = scan.next();

		// Base Case: Next String is null, returns null
		if (data.equals("null"))
			return null;

		// If it isn't, Recursively read in left and right children
		else
		{
			BTree<E> leftTree = readBinaryTree(scan);
			BTree<E> rightTree = readBinaryTree(scan);

			// Return the each Sub-Tree back up
			return new BTree<E>((E) data, leftTree, rightTree);
		}
	}

	// Inner Class Node
	protected static class Node<E> implements Serializable
	{
		// Instance Variables
		protected E			data;
		protected Node<E>	left;
		protected Node<E>	right;

		/**
		 * Default Constructor which instantiates a Node Object.
		 */
		public Node(E data)
		{
			this.data = data;
			this.left = null;
			this.right = null;
		}

		/**
		 * Provides a String Representation of the Data in a node, assuming node is not null.
		 * 
		 * @return Returns a String Representation of th data in a node.
		 */
		public String toString()
		{
			return data.toString();
		}
	}
}
