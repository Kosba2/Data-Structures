package edu.miracosta.cs113;

// Homework No.11 Exercise No.1
// File Name:     BST.java
// @author:       Kostyantyn Shumishyn
// Date:          November 27, 2017
//
// Problem Statement: Binary Search Tree Parent

@SuppressWarnings("serial")
public class BST<E extends Comparable<E>> extends BTree<E>
		implements
			SearchTree<E>
{
	// Instance Variables
	protected boolean	addReturn;
	protected E			deleteReturn;

	/**
	 * Inserts the item where it belongs in the tree.
	 * 
	 * @param item
	 *            Item to be inserted in the tree.
	 * @return Returns true if the item was inserted, else returns false if it already exits.
	 */
	@Override
	public boolean add(E item)
	{
		root = add(root, item);
		return addReturn;
	}

	/**
	 * Recursive add method. Post-Condition: The data field addReturn is set true if the item is
	 * added successfully, or false otherwise.
	 * 
	 * @param localRoot
	 *            The local root of the subtree.
	 * @param item
	 *            The object to be inserted.
	 * @return The new local root that now contains the inserted item.
	 */
	private Node<E> add(Node<E> localRoot, E item)
	{
		// Item is not in the tree so insert it
		if (localRoot == null)
		{
			addReturn = true;
			return new Node<E>(item);
		}

		// Item is equal to the localRoot.data
		else if (item.compareTo(localRoot.data) == 0)
			addReturn = false;

		// Item is less than the localRoot.data
		else if (item.compareTo(localRoot.data) < 0)
			localRoot.left = add(localRoot.left, item);

		// Item is greater than the localRoot.data
		else
			localRoot.right = add(localRoot.right, item);

		// Returns the local root
		return localRoot;
	}

	/**
	 * Returns true if the target is found in the tree, else returns false.
	 * 
	 * @param target
	 *            Target to be searched for.
	 * @return Returns true if the target is found in the tree, else returns false.
	 */
	@Override
	public boolean contains(E target)
	{
		return (find(target) != null);
	}

	/**
	 * Returns a reference to the data in the node that is equal to the target.
	 * 
	 * @param target
	 *            The target to be searched for and referenced.
	 * @return Returns a reference to the data in the node that is equal to the target, else returns
	 *         null.
	 */
	@Override
	public E find(E target)
	{
		return find(root, target);
	}

	/**
	 * Recursive find method.
	 * 
	 * @param localRoot
	 *            The local subtees root.
	 * @param target
	 *            The object being searched for.
	 * @return Returns the the object if it was found, else returns null.
	 */
	private E find(Node<E> localRoot, E target)
	{
		// If the Local Root is null return null
		if (localRoot == null)
			return null;

		// Comparison Result
		int compResult = target.compareTo(localRoot.data);

		// If they are the same return it
		if (compResult == 0)
			return localRoot.data;

		// If target is less than the Local Root
		else if (compResult < 0)
			return find(localRoot.left, target);

		// If target is greater than the Local Root
		else
			return find(localRoot.right, target);
	}

	/**
	 * Removes the target from the tree and returns it.
	 * 
	 * @param target
	 *            Target to be removed from the tree.
	 * @return Returns the target found and removed, else returns null.
	 */
	@Override
	public E delete(E target)
	{
		root = delete(root, target);
		return deleteReturn;
	}

	/**
	 * Helper Method that deletes the target node if it's in the tree.
	 * 
	 * @param localRoot
	 *            Current root being examined.
	 * @param target
	 *            Data to be removed
	 * @return Returns the reference to the node that contains the data to be removed else returns
	 *         false.
	 */
	private Node<E> delete(Node<E> localRoot, E target)
	{
		// If the node doesn't exist return null
		if (localRoot == null)
			deleteReturn = null;

		// If the node isn't null
		else
		{
			// Comparison Result
			int compResult = target.compareTo(localRoot.data);

			// If they are the same
			if (compResult == 0)
			{
				// Stores the Local Root
				deleteReturn = localRoot.data;

				// If both children are null
				if (localRoot.left == null && localRoot.right == null)
					return null;

				// If just the left child is null, return the right child
				else if (localRoot.left == null)
					return localRoot.right;

				// If just the right child is null, return the left child
				else if (localRoot.right == null)
					return localRoot.left;

				// If neither child is null
				else
				{
					// If the Left Child's Right Child is null
					if (localRoot.left.right == null)
					{
						localRoot.left.right = localRoot.right;
						return localRoot.left;
					}

					// Finds the rightmost Node and returns it's data after removing it
					localRoot.data = rightmostNodeDataAndRemove(localRoot, localRoot.left);
				}
			}

			// If target is less than the Local Root
			else if (compResult < 0)
				localRoot.left = delete(localRoot.left, target);

			// If target is greater than the Local Root
			else
				localRoot.right = delete(localRoot.right, target);
		}
		
		// Returns the Local Root
		return localRoot;
	}

	/**
	 * Helper method that finds the rightmost node and returns its data after removing it.
	 * 
	 * @param localRoot
	 *            The current root being examined.
	 * @param parentRoot
	 *            The local root's parent.
	 * @return Returns the data of rightmost root.
	 */
	protected E rightmostNodeDataAndRemove(Node<E> localRoot, Node<E> parentRoot)
	{
		// If the right root is null
		if (localRoot.right == null)
		{
			parentRoot.right = localRoot.left;
			return localRoot.data;
		}

		// If it isn't null recursively call back
		else
			return rightmostNodeDataAndRemove(localRoot.right, localRoot);
	}

	/**
	 * Removes the target from the tree.
	 * 
	 * @param target
	 *            The target to remove from the tree.
	 * @return Returns true if the target is found and removed, else returns false.
	 */
	@Override
	public boolean remove(E target)
	{

		return (delete(target) != null);
	}

}
