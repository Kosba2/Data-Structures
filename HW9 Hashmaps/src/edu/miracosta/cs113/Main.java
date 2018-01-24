package edu.miracosta.cs113;

/**
 * @author Kostyantyn Shumishyn
 *
 */

public class Main
{
	public static void main(String[] args)
	{
		// Creates new Hash Table Chain to Test
		HashTableChain<Integer, String> hashChain = new HashTableChain<Integer, String>();
		
		// Test Set 1
		hashChain.put(1, "one");
		hashChain.put(2, "two");
		hashChain.put(3, "three");
		System.out.println("HashChain should have size of 2 and have 3 entries");
		System.out.println(hashChain.toString());
		
		// Test Set 2
		hashChain.put(4, "four");
		hashChain.put(5, "five");
		hashChain.put(6, "six");
		hashChain.put(7, "seven");
		hashChain.put(8, "eight");
		hashChain.put(9, "nine");
		hashChain.put(10, "ten");
		System.out.println("\nHashChain should have rehashed to size of 5 and have 10 entries.");
		System.out.println(hashChain.toString());

		// Test Set 3
		hashChain.remove(5);
		hashChain.remove(10);
		hashChain.remove(7);
		System.out.println("\nHashChain should have 7 entries, 0 should be null.");
		System.out.println(hashChain.toString());

		// Test Set 4
		System.out.print("\nGetting 6 should return six: ");
		System.out.println(hashChain.get(6));

		// Test Set 5
		System.out.print("\nGetting 10 should return null: ");
		System.out.println(hashChain.get(10));

	}
}
