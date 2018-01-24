package edu.miracosta.cs113;

/**
 * @author Kostyantyn Shumishyn
 *
 */

public interface Map<K, V>
{

	/**
	 * Returns the value associated with a specific key.
	 * 
	 * @param key
	 *            Key used to find associated value.
	 * @return Returns value associated with the provided key or null if key not found.
	 */
	public V getObject(Object key);

	/**
	 * Determines whether Map has any mappings.
	 * 
	 * @return Returns true if set contains at least 1 mapping, else returns false.
	 */
	public boolean isEmpty();

	/**
	 * Associates a Value with a Key.
	 * 
	 * @param key
	 *            Key to associate with a value.
	 * @param value
	 *            Value for which a key is associated with.
	 * @return Returns the previous value associated with the specified key, if there was none, then
	 *         null.
	 */
	public V put(K key, V value);

	/**
	 * Removes an associated mapping for a provided key.
	 * 
	 * @param key
	 *            Key to be unmapped.
	 * @return Returns the previous value associated with the specified key, if there was none, then
	 *         null.
	 */
	public V remove(Object key);

	/**
	 * Provides the number of key-value mappings in the map.
	 * 
	 * @return Returns the number of key-value mappings in the map.
	 */
	public int size();

}
