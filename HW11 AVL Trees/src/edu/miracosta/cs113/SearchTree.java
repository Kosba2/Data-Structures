package edu.miracosta.cs113;

// Homework No.11 Exercise No.1
// File Name:     SearchTree.java
// @author:       Kostyantyn Shumishyn
// Date:          November 27, 2017
//
// Problem Statement: Search Tree Interface

public interface SearchTree<E>
{

	/**
	 * Inserts an item where it belongs in the tree.
	 * 
	 * @param item
	 *            Item to be inserted in the tree.
	 * @return Returns true if the item was inserted, else returns false if the item already exists.
	 */
	public boolean add(E item);

	/**
	 * Returns true if the target is found in a tree, else returns false.
	 * 
	 * @param target
	 *            Target to be searched for.
	 * @return Returns true if target is found in tree, else returns false.
	 */
	public boolean contains(E target);

	/**
	 * Returns a reference to the data in the node that is equal to the target.
	 * 
	 * @param target
	 *            Target to be searched for and referenced.
	 * @return Returns a reference to the data in the node that is equal to the target, else returns
	 *         null.
	 */
	public E find(E target);

	/**
	 * Removes target from tree and returns it.
	 * 
	 * @param target
	 *            Target to remove from the tree.
	 * @return Returns the target if found and removed, else returns null.
	 */
	public E delete(E target);

	/**
	 * Removes the target from the tree.
	 * 
	 * @param target
	 *            Target to remove from the tree.
	 * @return Returns true if the target is found and removed, else returns false.
	 */
	public boolean remove(E target);

}
