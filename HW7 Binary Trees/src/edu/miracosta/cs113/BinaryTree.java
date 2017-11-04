package edu.miracosta.cs113;

// Homework No.7 Exercise No.1
// File Name:     BinaryTree.java
// @author:       Kostyantyn Shumishyn
// Date:          October 28, 2017
//
// Problem Statement: Binary Tree Class
//
// Note: Read Binary Tree was used to Generate the Morse Dictionary

import java.io.Serializable;
import java.util.Scanner;

@SuppressWarnings("serial")
public class BinaryTree<E> implements Serializable
{
    // Static so Node can't access Tree stuff
    // Implements Serializable because we want to work with 0's and 1's,
    // allowing us to create binary files as opposed to text files
    protected static class Node<E> implements Serializable
    {
        // Node Instance Variables
        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        // Partial Node Constructor
        public Node(E data)
        {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public String toString()
        {
            return data.toString();
        }
    } // END OF NODE INNER CLASS

    // Binary Tree Instance Variables
    protected Node<E> root;

    // Default Constructor
    public BinaryTree()
    {
        root = null;
    }

    // Partial Binary Tree Constructor
    // Protected for Utility I don't entirely understand
    protected BinaryTree(Node<E> root)
    {
        this.root = root;
    }

    // Full Constructor for a Full Tree
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree)
    {
        // Adds Data to current Root
        root = new Node<E>(data);

        // If passed tree isn't null, make left the left tree
        if (leftTree != null)
            root.left = leftTree.root;
            // If passed tree is null, keep left null
        else
            root.left = null;

        // If passed tree isn't null, make right the right tree
        if (rightTree != null)
            root.right = rightTree.root;
            // If passed tree is null, keep right null
        else
            root.right = null;
    }

    // Accessors
    // Gets the Left Subtree
    public BinaryTree<E> getLeftSubtree()
    {
        // If neither root or it's left are null, return left
        if (root != null && root.left != null)
            return new BinaryTree<E>(root.left);
            // If one of them is null
        else
            return null;
    }

    // Gets the Right Subtree
    public BinaryTree<E> getRightSubtree()
    {
        // If neither root or it's right are null, return left
        if (root != null && root.right != null)
            return new BinaryTree<E>(root.right);
            // If one of them is null
        else
            return null;
    }

    // Gets the Root's Data
    public E getData()
    {
        if (root != null)
            return root.data;
        else
            return null;
    }

    // Checks whether the current root has any children
    public boolean isLeaf()
    {
        return (root.left == null && root.right == null);
    }

    // Binary Tree Specific ToString Method
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    // PreOrder Traverse Method
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb)
    {
        // Starts at one so the first line is non-tabbed
        // Otherwise tabs all following lines depending on depth
        for (int i = 1; i < depth; i++)
            sb.append("\t");

        // If the Tab is null, prints Null
        if (node == null)
            sb.append("null\n");

        // If the Tab is not null
        else
        {
            // Appends the Node
            sb.append(node.toString() + "\n");

            // And Recursively appends its left child
            preOrderTraverse(node.left, depth + 1, sb);

            // And Recursively appends its right child
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    // Constructs a binary tree by reading in data using Scanner
    // Used to Generate the MorseDictionary
    public static <E> BinaryTree<E> readBinaryTree(Scanner scan) {
        String data = scan.next();
        
        if (data.equals("null"))
            return null;
        else 
        {
            BinaryTree<E> leftTree = readBinaryTree(scan);
            BinaryTree<E> rightTree = readBinaryTree(scan);
            return new BinaryTree<E>((E) data, leftTree, rightTree);
        }
    }


}
