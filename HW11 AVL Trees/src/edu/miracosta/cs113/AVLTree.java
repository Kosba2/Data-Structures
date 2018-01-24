package edu.miracosta.cs113;

// Homework No.11 Exercise No.1
// File Name:     AVLTree.java
// @author:       Kostyantyn Shumishyn
// Date:          November 27, 2017
//
// Problem Statement: AVL Tree child of Binary Search Tree with Rotation

@SuppressWarnings("serial")
public class AVLTree<E extends Comparable<E>> extends BSTWithRotate<E>
{
	// Instance Variables
	private boolean	increase;
	private boolean	decrease;

	/**
	 * Helper Method to kickstart the Recursive Add Method.
	 *
	 * @param item
	 *            The object to be inserted.
	 * @return Returns true if addition is successful, else false if item already exists in tree.
	 * 
	 */
	@Override
	public boolean add(E item)
	{
		increase = false;
		decrease = false;
		root = add((AVLNode<E>) root, item);
		return addReturn;
	}

	/**
	 * Recursive add method. Inserts the given object into the tree. Post-Condition: The value of
	 * addReturn is true if item has been inserted successfully, else false if it already exists.
	 * 
	 * @param localRoot
	 *            The local root of the subtree.
	 * @param item
	 *            The object to be inserted.
	 * @return Returns the new local root of the subtree with the item inserted.
	 * 
	 */
	private AVLNode<E> add(AVLNode<E> localRoot, E item)
	{
		// If the Local Root is null
		if (localRoot == null)
		{
			// Knows to increase
			addReturn = true;
			increase = true;
			return new AVLNode<E>(item);
		}

		// If the item is already in the tree
		if (item.compareTo(localRoot.data) == 0)
		{
			// Knows not to increase
			increase = false;
			addReturn = false;
			return localRoot;
		}

		// Item is less than the data
		else if (item.compareTo(localRoot.data) < 0)
		{
			// Stores the Local Root's Left
			localRoot.left = add((AVLNode<E>) localRoot.left, item);

			// Knows to handle the Increase
			if (increase)
			{
				// Decrements Balance Appropriately
				decrementBalance(localRoot);
				if (localRoot.balance < AVLNode.LEFT_HEAVY)
				{
					// Resets Increase
					increase = false;

					// Rebalances
					return rebalanceLeft(localRoot);
				}
			}
		}

		// Item is greater than the data
		else if (item.compareTo(localRoot.data) > 0)
		{
			// Stores the Local Root's Right
			localRoot.right = add((AVLNode<E>) localRoot.right, item);

			// Knows to handle the Increase
			if (increase)
			{
				// Increments the Balance Appropriately
				incrementBalance(localRoot);
				if (localRoot.balance > AVLNode.RIGHT_HEAVY)
				{
					// Resets Increase
					increase = false;

					// Rebalances
					return rebalanceRight(localRoot);
				}
			}
		}

		// Occurs if rebalance is not necessary
		return localRoot;
	}

	/**
	 * Helper Method that rebalances the tree to the left when it has become unbalanced.
	 * 
	 * @param localRoot
	 *            The root that will be rebalanced to the left.
	 * @return Returns the rebalanced tree.
	 */
	private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot)
	{
		// Stores the Local Root's Left
		AVLNode<E> nextLeft = (AVLNode<E>) localRoot.left;

		// If the next node is unbalanced right
		if (nextLeft.balance > AVLNode.BALANCED)
		{
			// Stores the next node's right
			AVLNode<E> nextLeftRight = (AVLNode<E>) localRoot.left.right;

			// If the next's right is unbalanced left
			if (nextLeftRight.balance < AVLNode.BALANCED)
			{
				incrementBalance(nextLeftRight);
				incrementBalance(localRoot);
			}
			
			// If the next's right is unbalanced right
			else if (nextLeftRight.balance > AVLNode.BALANCED)
			{
				decrementBalance(nextLeftRight);
				decrementBalance(nextLeft);
			}

			// Returns to finish Balance updates
			decrementBalance(nextLeft);
			
			// Return's the Left Rotation
			localRoot.left = rotateLeft(localRoot.left);
			incrementBalance(localRoot);
			incrementBalance(localRoot);

			// Returns the Right Rotation
			return (AVLTree<E>.AVLNode<E>) rotateRight(localRoot);
		}

		// If the next node is unbalanced left
		else
		{
			// Returns to finish Balance updates
			incrementBalance(nextLeft);
			incrementBalance(localRoot);
			incrementBalance(localRoot);

			// Returns the right rotation
			return (AVLTree<E>.AVLNode<E>) rotateRight(localRoot);
		}
	}

	/**
	 * Helper Method that rebalances the tree to the right when it has become unbalanced.
	 * 
	 * @param localRoot
	 *            The root that will be rebalanced to the right.
	 * @return Returns the rebalanced tree.
	 */
	private AVLNode<E> rebalanceRight(AVLNode<E> localRoot)
	{
		// Stores the Local Root's Right
		AVLNode<E> nextRight = (AVLNode<E>) localRoot.right;

		// If the next node is unbalanced right
		if (nextRight.balance > AVLNode.BALANCED)
		{
			// Returns to finish Balance Updates
			decrementBalance(nextRight);
			decrementBalance(localRoot);
			decrementBalance(localRoot);
			
			// Returns the left rotation
			return (AVLTree<E>.AVLNode<E>) rotateLeft(localRoot);
		}
		
		// If the next node is unbalanced left
		else
		{
			// Stors the next node's left
			AVLNode<E> nextRightLeft = (AVLNode<E>) localRoot.right.left;
			
			// Increments on Local Root's right
			incrementBalance((AVLNode<E>) localRoot.right);

			// If the next right's left is unbalanced left
			if (nextRightLeft.balance < AVLNode.BALANCED)
			{
				incrementBalance(nextRight);
				incrementBalance((AVLNode<E>) nextRight.left);

			}
			
			// If the next right's left is unbalanced right
			else if (nextRightLeft.balance > AVLNode.BALANCED)
			{
				decrementBalance(localRoot);
				decrementBalance((AVLNode<E>) nextRight.left);
			}

			// Returns the Right Rotation
			localRoot.right = rotateRight(localRoot.right);

			// Returns to finish Balance Updates
			decrementBalance(localRoot);
			decrementBalance(localRoot);

			// Returns the Left Rotation
			return (AVLTree<E>.AVLNode<E>) rotateLeft(localRoot);
		}
	}

	/**
	 * Helper Method that decrements the balance of a node.
	 * 
	 * @param node
	 *            The node whose balance will be decremented.
	 * 
	 */
	private void decrementBalance(AVLNode<E> node)
	{
		// Decrement the balance
		node.balance--;
		
		// If now balanced then the overall height has not increased
		if (node.balance == AVLNode.BALANCED)
			increase = false;
	}

	/**
	 * Helper Method that increments the balance of a node.
	 * 
	 * @param node
	 *            The node whose balance will be increments.
	 * 
	 */
	private void incrementBalance(AVLNode<E> node)
	{
		// Increments the balance
		node.balance++;
		
		// If now balanced then the overall height has not increased
		if (node.balance == AVLNode.BALANCED)
			increase = false;
	}

	/**
	 * Removes the target from the tree and returns it.
	 * 
	 * @param target
	 *            The target that will be removed from the tree.
	 * @return Returns the target after it is found and removed, else returns null.
	 */
	@Override
	public E delete(E target)
	{
		// Defaults to no changes
		increase = false;
		decrease = false;
		deleteReturn = null;
		root = delete((AVLNode<E>) root, target);
		return deleteReturn;
	}

	/**
	 * Helper Method that deletes the target node if it is in the tree.
	 * 
	 * @param localRoot
	 *            The current root being examined.
	 * @param target
	 *            The data that will be removed.
	 * @return Returns the reference to the node that contains the data to remove, else returns false. 
	 */
	private Node<E> delete(AVLNode<E> localRoot, E target)
	{
		// If the Local Root is null return null
		if (localRoot == null)
			deleteReturn = null;

		// IF the Local Root is not null
		else
		{
			// Stores the Target Comparison
			int compResult = target.compareTo(localRoot.data);
			
			// If the Target is the Local Root
			if (compResult == 0)
			{
				// Return the Local Root's Data
				deleteReturn = localRoot.data;
				
				// Decrease is guaranteed
				decrease = true;

				// If the Local Root's left and right are null return null
				if (localRoot.left == null && localRoot.right == null)
					return null;
				
				// If the Local Root's left is null return right
				else if (localRoot.left == null)
					return localRoot.right;

				// If the Local Root's right is null return left
				else if (localRoot.right == null)
					return localRoot.left;
				
				// If the Local Root's left's right is null -> Handle
				else if (localRoot.left.right == null)
				{
					localRoot.data = localRoot.left.data;
					localRoot.left = localRoot.left.left;
					incrementBalance(localRoot);

					if (localRoot.balance > AVLNode.RIGHT_HEAVY)
					{
						increase = false;
						return rebalanceRight(localRoot);
					}
					
					return localRoot;
				}
				
				// Handles the Right-most Node
				else
				{
					// Handles Right-most removal
					localRoot.data = rightmostDataRemove((AVLNode<E>) localRoot.left,
							localRoot);
					
					// If decrease has occurred
					if (decrease)
					{
						// Balance has increased
						incrementBalance(localRoot);
						
						// If unbalanced right
						if (localRoot.balance > AVLNode.RIGHT_HEAVY)
							return rebalanceLeft(localRoot);
					}
					
					// Returns the Local Root
					return localRoot;
				}
			}
			
			// If the target is less than the Local Host
			else if (compResult < 0)
			{
				// Handles simple deletion
				localRoot.left = delete((AVLNode<E>) localRoot.left, target);
				
				// If decrease has occurred
				if (decrease)
				{
					// Balance has increased
					incrementBalance(localRoot);
					
					// If unbalanced right
					if (localRoot.balance > AVLNode.RIGHT_HEAVY)
						return rebalanceRight(localRoot);
				}
			}
			
			// If the target is greater than the Local Root
			else
			{
				// Handles the simple deletion
				localRoot.right = delete((AVLNode<E>) localRoot.left, target);
				
				// If decrease has occurred
				if (decrease)
				{
					// Balance has increased
					decrementBalance(localRoot);
					
					// if unbalanced left
					if (localRoot.balance < AVLNode.LEFT_HEAVY)
						return rebalanceLeft(localRoot);
				}
			}
		}

		// Returns the Local Root
		return localRoot;
	}

	/**
	 * Helper method that finds the rightmost node and returns its data after removing that node.
	 * 
	 * @param localRoot
	 *            The current root being examined.
	 * @param parentRoot
	 *            The Local Root's parent.
	 * @return Returns the data of the rightmost root.
	 */
	private E rightmostDataRemove(AVLNode<E> localRoot, AVLNode<E> parentRoot)
	{
		// If the Local Root's right is null
		if (localRoot.right == null)
		{
			E temp = localRoot.data;
			localRoot = (AVLNode<E>) localRoot.left;
			decrease = false;
			
			// If the Parent Root's left is null
			if (parentRoot.left == null)
			{
				decrease = true;
				decrementBalance(parentRoot);
			}
			parentRoot.right = localRoot;
			return temp;
		}
		
		// Recursive Case
		else
			return rightmostDataRemove((AVLNode<E>) localRoot.right, localRoot);
	}

	@SuppressWarnings("hiding")
	private class AVLNode<E> extends BTree.Node<E>
	{
		// Instance Variables
		private static final int	LEFT_HEAVY	= -1;
		private static final int	RIGHT_HEAVY	= 1;
		private static final int	BALANCED	= 0;
		private int					balance;

		/**
		 * Constructor for an AVLNode.
		 * 
		 * @param data
		 *            The data to be inserted into a new AVLNode.
		 */
		public AVLNode(E data)
		{
			// Supers to Node with the addition of Balance
			super(data);
			balance = 0;
		}

		// Overriden ToString
		@Override
		public String toString()
		{
			return data.toString() + " (" + balance + ")";
		}
	}
}