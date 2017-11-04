package edu.miracosta.cs113;

public class Lab4 {

	public static void main(String[] args) 
	{
		// Variable Declarations
		int y1;
		int y2;
		int n;

		int[] y1List = new int[10];
		int[] y2List = new int[10];
		int[] nValue = new int[10];
		
		// Variable Initialization
		n = 0;
		
		// Assigns values to array for each formula
		for (int i = 0; i < 10; i++)
		{
			n = (i)*10;
			
			y1 = 100 * n + 10;
			y2 = 5 * n * n + 2;

			y1List[i] = y1;
			y2List[i] = y2;
			nValue[i] = n;
		}
		
		
		// Loops through Array of Values for Y1 and Y2 and lists the numbers
		for (int j = 0; j < 10; j++)
			System.out.printf("%-2d  %-4d  %-5d\n", nValue[j], y1List[j], y2List[j]);
		
		
		
	}

}
