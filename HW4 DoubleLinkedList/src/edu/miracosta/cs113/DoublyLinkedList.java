package edu.miracosta.cs113;

//Homework No.4 Exercise No.1
//File Name:     DoublyLinkedList.java
//@author:       Kostyantyn Shumishyn
//Date:          September 26, 2017
//
//Problem Statement: Creates a Doubly Linked List with inner Node and ListIterator

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>
{
	// Doubly Linked List Instance Variables
	private Node<E>	head	= null;
	private Node<E>	tail	= null;
	private int		size	= 0;

	public DoublyLinkedList()
	{
		// Only Size because Head and Tail default to Null
		size = 0;
	}

	// What does it mean I'm "Hiding the Type E"?
	// Node Inner Class
	private static class Node<E>
	{
		private E		element;
		private Node<E>	next	= null;
		private Node<E>	prev	= null;

		private Node(E dataItem)
		{
			element = dataItem;
		}
	}

	// ListIterator Inner Class
	private class DoubleListIterator implements ListIterator<E>
	{
		private Node<E>	nextItem;
		private Node<E>	lastItemReturned;
		private int		index	= 0;

		public DoubleListIterator(int i)
		{
			// Error Check Index
			if (i < 0 || i > size)
			{
				throw new IndexOutOfBoundsException("Invalid index " + i);
			}

			// No item returned yet
			lastItemReturned = null;

			// Special case of last item (why not let loop do this?)
			if (i == size)
			{
				index = size;
				nextItem = null;
			}

			// Start at the beginning
			else
			{
				nextItem = head;
				for (index = 0; index < i; index++)
				{
					nextItem = nextItem.next;
				}
			}
		}

		// Covers all the situations in which adding may occur
		@Override
		public void add(E e)
		{
			// adding to an empty list
			if (head == null)
			{
				head = new Node<E>(e);
				tail = head;
			}

			// adding to the head of the list
			if (nextItem == head)
			{
				Node<E> newNode = new Node<E>(e);
				newNode.next = nextItem;
				nextItem.prev = newNode;
				head = newNode;
			}
			// adding to the tail of the list
			else if (nextItem == null)
			{
				Node<E> newNode = new Node<E>(e);
				tail.next = newNode;
				newNode.prev = tail;
				tail = newNode;
			}
			// adding to the middle of the list
			else
			{
				Node<E> newNode = new Node<E>(e);
				newNode.prev = nextItem.prev;
				nextItem.prev.next = newNode;
				newNode.next = nextItem;
				nextItem.prev = newNode;
			}
			size++;
			index++;
			lastItemReturned = null;
		}

		// Checks if there is a next element
		@Override
		public boolean hasNext()
		{
			return nextItem != null;
		}

		// Checks if there is a previous element
		@Override
		public boolean hasPrevious()
		{
			return (nextItem == null && size != 0) || nextItem.prev != null;
		}

		// Steps forward if there is a forward
		@Override
		public E next()
		{
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}
			lastItemReturned = nextItem;
			nextItem = nextItem.next;
			index++;
			return lastItemReturned.element;
		}

		// Returns next index
		@Override
		public int nextIndex()
		{
			return index + 1;
		}

		// Steps backwards if there is a backward
		@Override
		public E previous()
		{
			if (!hasPrevious())
			{
				throw new NoSuchElementException();
			}
			if (nextItem == null)
			{ // Iterator past the last element
				nextItem = tail;
			}
			else
			{
				nextItem = nextItem.prev;
			}
			lastItemReturned = nextItem;
			index--;
			return lastItemReturned.element;
		}

		// Returns previous index
		@Override
		public int previousIndex()
		{
			return index - 1;
		}

		// Remove method makes sure last item looked at isn't null, then removes and relinks nodes
		@Override
		public void remove()
		{
			// If neither next or previous have been called recently
			if (lastItemReturned == null)
			{
				throw new IllegalStateException();
			}
			Node<E> prevNode = lastItemReturned.prev;
			Node<E> nextNode = lastItemReturned.next;

			prevNode.next = nextNode;
			nextNode.prev = prevNode;

			if (nextItem == lastItemReturned)
			{
				nextItem = prevNode;
			}
			else
			{
				index--;
			}

			lastItemReturned = null;
			size--;
		}

		// Set method checks whether last item looked at isn't null, then changes the data
		@Override
		public void set(E e)
		{
			if (lastItemReturned == null)
			{
				throw new IllegalStateException();
			}
			lastItemReturned.element = e;
		}

	}

	// Size Method returns Size
	public int size()
	{
		return size;
	}

	// Empty Method checks whether size is 0
	public boolean isEmpty()
	{
		return size == 0;
	}

	// Uses iterator's add Method to add at an Index
	@Override
	public void add(int index, E element)
	{
		listIterator(index).add(element);
	}

	// Removes first Node in List if list isn't empty and returns its Data
	public E removeFirst()
	{
		if (size == 0)
		{
			throw new NoSuchElementException();
		}
		Node<E> tmp = head;
		head = head.next;
		head.prev = null;
		size--;

		return tmp.element;
	}

	// Removes last Node in List if list isn't empty and returns its Data
	public E removeLast()
	{
		if (size == 0)
		{
			throw new NoSuchElementException();
		}
		Node<E> tmp = tail;
		tail = tail.prev;
		tail.next = null;
		size--;

		return tmp.element;
	}

	// Wasn't sure what purpose this method served with an addLast, so I just
	// had it do the same thing
	// but kept my old Code just in case
	@Override
	public boolean add(E element)
	{
		// Node<E> newNode = new Node<E>(element);
		//
		// // If list is empty
		// if (head == null)
		// {
		// head = newNode;
		// newNode.next = tail;
		// }
		//
		// // If list is not empty add to tail
		// else
		// {
		// newNode.prev = tail.prev;
		// tail.prev.next = newNode;
		// tail = newNode;
		// }
		//
		// size++;
		add(size, element);

		return true;
	}

	// Adds to front
	public void addFirst(E element)
	{
		add(0, element);
	}

	// Adds to end
	public void addLast(E element)
	{
		add(size, element);
	}

	// Gets head
	public E getFirst()
	{
		return head.element;
	}

	// Gets tail
	public E getLast()
	{
		return tail.element;
	}

	// Removes a node at a specified index, returns data
	@Override
	public E remove(int index)
	{
		Node<E> tempNode = new Node<E>(listIterator(index).next());

		listIterator(index).remove();

		return tempNode.element;
	}

	// Gets the element at the specified indice
	@Override
	public E get(int index)
	{
		return listIterator(index).next();
	}

	// Returns first valid index of specified object, -1 if none
	@Override
	public int indexOf(Object o)
	{
		int index = 0;
		Node<E> current = head;

		while (current != null)
		{
			if (current.equals(o))
			{
				return index;
			}
			index++;
			current = current.next;
		}

		return -1;
	}

	// Returns last valid index of specified object
	@Override
	public int lastIndexOf(Object o)
	{
		int index = 0;
		int lastIndex = -1;
		Node<E> current = head;

		while (listIterator(-1).hasNext())
		{
			if (current.equals(o))
			{
				lastIndex = index;
			}
			index++;
			current = current.next;
		}

		return lastIndex;
	}

	// Returns a List Iterator at an Index
	@Override
	public ListIterator<E> listIterator(int index)
	{
		return new DoubleListIterator(index);
	}

	// Replaces an element in list and returns old element
	@Override
	public E set(int index, E element)
	{
		Node<E> tempNode = new Node<E>(listIterator(index).next());

		listIterator(index).set(element);

		return tempNode.element;
	}

	// Checks whether the specified object is inside the List
	@Override
	public boolean contains(Object o)
	{
		boolean doesContain;

		// If it finds an index of the object
		if (this.indexOf(o) != -1)
		{
			doesContain = true;
		}
		else
		{
			doesContain = false;
		}
		return doesContain;
	}

	// Assuming rest is picked up by the heap
	@Override
	public void clear()
	{
		size = 0;
		head = null;
		tail = head;
	}

	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	///////////////////// STUBS THAT WILL NOT BE OVERRIDDEN /////////////////////
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean remove(Object o)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ListIterator<E> listIterator()
	{
		return new DoubleListIterator(0);
	}

	@Override
	public Iterator<E> iterator()
	{
		return new DoubleListIterator(0);
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
