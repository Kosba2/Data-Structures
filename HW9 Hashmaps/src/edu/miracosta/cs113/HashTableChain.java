package edu.miracosta.cs113;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author Kostyantyn Shumishyn
 * 
 */

public class HashTableChain<K, V> extends AbstractMap<K, V> implements Map<K, V>
{
	// Instance Variables of HashTableChain
	private LinkedList<Entry<K, V>>[]	table;
	private static final int			START_CAPACITY	= 2;
	private static final double			LOAD_THRESHOLD	= 3.0;
	private int							numKeys			= 0;

	/**
	 * Default Constructor
	 */
	public HashTableChain()
	{
		table = new LinkedList[START_CAPACITY];
	}

	/**
	 * Gets the value associated with the provided key, from the table.
	 * 
	 * @param key
	 *            Key to be searched for.
	 * @return Returns the value associated with the key, else returns null.
	 */
	public V get(Object key)
	{
		// Sets default index
		int index = key.hashCode() % table.length;

		// Handles negative index
		if (index < 0)
			index += table.length;

		// Handles null at index, knows to end search
		if (table[index] == null)
			return null;

		// Searches table for the key
		for (Entry<K, V> item : table[index])
			if (item.key.equals(key))
				return item.value;

		// Satisfies compiler
		return null;
	}

	/**
	 * Adds key and associated value to table.
	 * 
	 * @param key
	 *            Key to pair with value.
	 * @param value
	 *            Value to pair with key.
	 * @return Returns the previous value associated with key, else null if none exists.
	 */
	public V put(K key, V value)
	{
		// Sets default index
		int index = key.hashCode() % table.length;

		// Handles negative index
		if (index < 0)
			index += table.length;

		// Handles null at index, valid location to add value
		if (table[index] == null)
			table[index] = new LinkedList<Entry<K, V>>();

		// Looks for the key and associates value where found
		for (Entry<K, V> item : table[index])
			if (item.equals(key))
			{
				V previousValue = item.value;
				item.setValue(value);
				return previousValue;
			}

		// Adds to Chain
		table[index].addFirst(new Entry<K, V>(key, value));
		numKeys++;

		// Rehashes if table passes the Load Threshold
		if (numKeys > (LOAD_THRESHOLD * table.length))
			rehash();

		// Satisfies compiler
		return null;
	}

	/**
	 * Removes the key from the table.
	 * 
	 * @param key
	 *            The key to be removed.
	 * @return Returns the value associated with the key, else returns nulls.
	 */
	@Override
	public V remove(Object key)
	{
		// Defaults to null
		V value = null;

		// Sets Default Index
		int index = key.hashCode() % table.length;

		// Handles negative index
		if (index < 0)
			index += table.length;

		// Handles null at index
		if (table[index] == null)
			return null;

		// Defaults location as 0
		int loc = 0;
		for (Entry<K, V> item : table[index])
		{
			// If Key is found
			if (item.getKey().equals(key))
			{
				// Successfully removes value
				numKeys--;
				value = table[index].remove(loc).getValue();

				// If removing made the size 0
				if (table[index].size() == 0)
					table[index] = null;
			}
			// Goes to next location
			loc++;
		}
		// Satisfies compiler
		return value;
	}

	/**
	 * Expands table size when loadFactor exceeds LOAD_THRESHOLD
	 * 
	 * @post The size of the table is doubled to be an odd integer. Each entry from previous table
	 *       is reinserted appropriately to the expanded table.
	 */
	private void rehash()
	{
		// Makes a new Expanded List of Odd size
		LinkedList<Entry<K, V>>[] newTable = new LinkedList[(table.length * 2) + 1];

		// Goes through each mapping in table
		for (int i = 0; i < table.length; i++)
		{
			for (Entry<K, V> item : table[i])
			{
				// Sets default index
				int index = item.key.hashCode() % newTable.length;

				// Handles negative index
				if (index < 0)
					index += table.length;

				// Creates new Chain if one didn't exist before
				if (newTable[index] == null)
					newTable[index] = new LinkedList<Entry<K, V>>();

				// Adds to the front of chain
				newTable[index].addFirst(item);

			}
		}
		// Overwrites old table
		table = newTable;
	}

	/** Returns the number of mappings. */
	@Override
	public int size()
	{
		return numKeys;
	}

	/** Returns the value associated with a Key. */
	@Override
	public V getObject(Object key)
	{
		return get(key);
	}

	/** Creates a Map. */
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet()
	{
		return null;
	}

	/**
	 * Provides string representation of data in the table.
	 * 
	 * @return Returns string representation of data in table.
	 */
	public String toString()
	{
		// Return String
		String tableAsString = "";

		for (int i = 0; i < table.length; i++)
		{
			// Cats current index
			tableAsString = tableAsString + "\n" + i + ") ";
			// Returns null if index is null
			if (table[i] == null)
				tableAsString = tableAsString + "null";

			// Else returns each part of entry tabbed
			else
				for (Entry<K, V> item : table[i])
					tableAsString = tableAsString + item.toString() + "\t";

		}
		// Returns Table String
		return tableAsString;
	}

	/** Inner class to implement the set view. */
	private class EntrySet extends java.util.AbstractSet<Entry<K, V>>
	{
		/** Return the size of the set. */
		public int size()
		{
			return numKeys;
		}

		/** Return an iterator over the set. */
		@Override
		public Iterator<Entry<K, V>> iterator()
		{
			return new SetIterator();
		}
	}

	/** Inner class to implement the set iterator. */
	private class SetIterator implements Iterator<Entry<K, V>>
	{
		// Instance Variables of SetIterator
		int						index				= 0;
		Iterator<Entry<K, V>>	localIterator		= null;
		Entry<K, V>				lastItemReturned	= null;

		// Creates an Entry Set
		public java.util.Set<Entry<K, V>> entrySet()
		{
			return new EntrySet();
		}

		/**
		 * Informs whether the set has more entries or not
		 * 
		 * @return returns true if there are more entries. otherwise, false
		 */
		@Override
		public boolean hasNext()
		{
			// Uses Local Iterator
			if (localIterator != null)
			{
				if (localIterator.hasNext())
					return true;

				else
				{
					localIterator = null;
					index++;
				}
			}
			// Traverses Table
			while (index < table.length && table[index] == null)
				index++;

			// If has gone through the whole table
			if (index == table.length)
				return false;

			// Sets Iterator at the index
			localIterator = table[index].iterator();

			// Returns whether the iterator has a Next
			return localIterator.hasNext();
		}

		/**
		 * Returns the next item in the set, assuming hasNext() is true.
		 * 
		 * @return Returns next item in the set.
		 */
		@Override
		public Entry<K, V> next()
		{
			lastItemReturned = localIterator.next();
			return lastItemReturned;
		}

		/**
		 * Removes the last item returned from the list, assuming Next was called once.
		 */
		@Override
		public void remove()
		{
			localIterator.remove();
			lastItemReturned = null;
		}
	}

	private class Entry<K, V>
	{
		// Instance Variables for an Entry
		private K	key;
		private V	value;

		/**
		 * Constructor for Entry. Creates Entry object for hashtable
		 * 
		 * @param key
		 *            Key to be paired with a value.
		 * @param value
		 *            Value to be paired with a key.
		 */
		public Entry(K key, V value)
		{
			this.key = key;
			this.value = value;
		}

		/**
		 * Retrieves the current Entry's key.
		 * 
		 * @return Returns the entry's key.
		 */
		public K getKey()
		{
			return key;
		}

		/**
		 * Retrieves the current Entry's value.
		 * 
		 * @return Returns the entry's value.
		 */
		public V getValue()
		{
			return value;
		}

		/**
		 * Sets the current Entry's value.
		 * 
		 * @return Returns the entry's new value.
		 */
		public V setValue(V value)
		{
			value = this.value;
			return value;
		}

		/**
		 * Return a String representation of the Entry.
		 * 
		 * @return Returns a String representation of the Entry in the form key = value.
		 */
		@Override
		public String toString()
		{
			return key.toString() + " = " + value.toString();
		}
	}
}
