package edu.miracosta.cs113;

// Homework No.7 Exercise No.1
// File Name:     MorseCodeDriver.java
// @author:       Kostyantyn Shumishyn
// Date:          October 30, 2017
//
// Problem Statement: Uses the Binary Tree Class to Encode and Decode
// messages to and from Morse Code.
//
// Algorithm:
// 1. Uses a predefined Library to fetch the pre-written Binary Tree from File
// 2. Runs Menu until the Exit option is selected.
// 3. Offers options to Translate between Morse or Text and to write in or to File
// 4. Uses Static Methods primarily for functionality regarding finding characters
// by traversing the Binary Tree generated specifically for Morse Code, as well as
// simply returning Formatted Strings related to the Morse Code Tree.

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;



public class MorseCodeDriver
{
    // Creates a Global Binary Tree
    private static BinaryTree<String> morseTree = new BinaryTree<>();

    public static void main(String[] args)
    {
        // Name of File to read Morse Data from
        String inFileName = "MorseDictionary.txt";

        // Name of File to write Morse Data to
        String outFileName;

        // Declaration
        boolean isRunning;
        int menuChoice;
        boolean promptFilename;

        // Initialization
        isRunning = true;
        menuChoice = 0;
        promptFilename = true;

        // File/IO Manipulation Variables
        Scanner keyboard = new Scanner(System.in);
        Scanner scan;
        FileInputStream inStream;
        FileOutputStream outStream;

        // Asks for the File Name
        while (promptFilename)
        {
            try
            {
                // Initializes Input Stream
                inStream = new FileInputStream(inFileName);
                scan = new Scanner(inStream);

                // Reads each Line
                while (scan.hasNext())
                    morseTree = BinaryTree.readBinaryTree(scan);

                // Closes Stream and Ends Prompt Loop
                scan.close();
                promptFilename = false;
            }
            catch (FileNotFoundException e)
            {
                System.out.println("File Not Found. Please Enter File Name.");
                inFileName = keyboard.nextLine();
            }
        }

        // Menu Running
        while (isRunning)
        {
            boolean isSelecting = true;
            System.out.println();
            System.out.println("|-------------------------------------|");
            System.out.println("| 1 - DISPLAY MORSE CODE              |");
            System.out.println("| 2 - TRANSLATE MORSE FILE TO TEXT    |");
            System.out.println("| 3 - TRANSLATE TEXT TO MORSE CODE    |");
            System.out.println("| 4 - TRANSLATE & WRITE MORSE TO FILE |");
            System.out.println("| 5 - EXIT                            |");
            System.out.println("|-------------------------------------|");
            System.out.print("Choice: ");

            while (isSelecting)
            {
                try
                {
                    menuChoice = keyboard.nextInt();
                    if (menuChoice > 5 || menuChoice < 1)
                    {
                        throw new InputMismatchException();
                    }
                    isSelecting = false;
                }
                catch (InputMismatchException e)
                {
                    System.out.print("\nInvalid entry.\nChoice: ");
                }
                finally
                {
                    keyboard.nextLine();
                }
            }

            // User Chooses "DISPLAY MORSE CODE"
            if (menuChoice == 1)
            {
                displayAsTable((treeAsTable(morseTree)));
                // Linebreak handled here to keep Table situationally flexible
                System.out.println();
            }

            // User Chooses "TRANSLATE MORSE FILE TO TEXT"
            else if (menuChoice == 2)
            {
                System.out.println("Please Enter File Name to be Translated");
                String morseFileName = keyboard.nextLine();
                try
                {
                    inStream = new FileInputStream(morseFileName);
                    scan = new Scanner(inStream);
                    while (scan.hasNext())
                    {
                        System.out.print(translateFromMorseToText(scan.next().toLowerCase(), morseTree));
                    }
                    scan.close();
                }
                catch (FileNotFoundException e)
                {
                    System.out.println("File not found. Returning to Menu.");
                }
            }

            // User Chooses "TRANSLATE TEXT TO MORSE CODE"
            else if (menuChoice == 3)
            {
                // Prompts for User Input of Text
                String userString;
                System.out.println("Enter Text to be Translated. Special characters will be removed automagically.");
                userString = keyboard.nextLine();

                // Prints the text, no spaces
                System.out.println(translateText(userString.toLowerCase()));
            }

            // User Chooses "TRANSLATE & WRITE MORSE TO FILE"
            else if (menuChoice == 4)
            {
                // Prompts for File Name
                System.out.println("What to call the text file? (w/o Extension)");
                outFileName = keyboard.nextLine();

                try
                {
                    // Initializes Output Stream
                    outStream = new FileOutputStream(outFileName + ".txt");

                    // Prompts for User Input of Text
                    String userString;
                    System.out.println("Enter Text to be Translated. Special characters will be removed automagically.");
                    userString = keyboard.nextLine();

                    // Turns into Bytes and Stores
                    byte[] translatedText = translateText(userString.toLowerCase()).getBytes();

                    // Writes to File
                    outStream.write(translatedText);
                    outStream.flush();

                    // Feedback
                    System.out.println("Morse Code file generated successfully.");
                }
                catch (Exception e)
                {
                    System.out.println("Something went wrong, returning to Menu.");
                }
            }

            // User Chooses "EXIT", Quits Program
            else
                isRunning = false;
        }

        // Closes Keyboard Impolitely
        keyboard.close();

        // Ponders Existence of this Code
        System.out.println("Thank you for using this Program, Stranded Person.");
    }

    // Generates String Tree of Binary Tree Data
    private static String treeAsTable(BinaryTree<String> tree)
    {
        // Base Case
        if (tree.isLeaf())
            return tree.getData() + "\n";

        // Recursive Cases
        else
        {
            // Handles Right Null - Not Exclusive of Left Null
            if (tree.getRightSubtree() == null)
                return tree.getData() + "\n" + treeAsTable(tree.getLeftSubtree());

            // Handles Left Null - Not Exclusive of Right Null
            if (tree.getLeftSubtree() == null)
                return tree.getData() + "\n" + treeAsTable(tree.getRightSubtree());

            // Primary Recursive Call
            return tree.getData() + "\n" + treeAsTable(tree.getLeftSubtree()) + treeAsTable(tree.getRightSubtree());
        }
    }

    // Displays an alphabetically sorted 6x5 table using the generated String
    // representation of the Binary Tree, but instead outputs the Table in
    // such a way that it is alphabetically ordered
    private static void displayAsTable(String data)
    {
        ArrayList<String> dataAsList = new ArrayList<>();
        Scanner scan = new Scanner(data);
        while (scan.hasNext())
        {
            String newData = scan.next();
            if (!newData.equalsIgnoreCase("0"))
            {
                newData = newData.substring(0, 1) + "  " + newData.substring(1, newData.length());
                dataAsList.add(newData);
            }
        }
        Collections.sort(dataAsList);
        for (int i = 0; i < dataAsList.size(); i++)
        {
            if (i % 5 == 0)
            {
                System.out.println("");
            }
            System.out.print(dataAsList.get(i) + "\t");
        }
        
        // Closes Scanner
        scan.close();
    }

    ////////////////////////////////////
    /// Translates A String to Morse ///
    ////////////////////////////////////

    // Starter Method to convert Text to Morse Code
    private static String translateText(String data)
    {
        return stringToMorse(data, 0);
    }

    // Converts text to Morse Code
    private static String stringToMorse(String data, int position)
    {
        // Base Case
        if (position == data.length())
            return "";

        // Recursive Case
        else
        {
            // Checks for Space first
            if (data.charAt(position) == ' ')
                return " " + stringToMorse(data, position + 1);

            // Primary Recursive Call
            else
                return searchBinaryTree(data.charAt(position), morseTree) + " " + stringToMorse(data, position + 1);
        }
    }

    ////////////////////////////////////
    /// Translates Morse to a String ///
    ////////////////////////////////////

    // Searches Binary Tree for a provided Character and returns the Morse
    // representation of it.
    private static String searchBinaryTree(char item, BinaryTree<String> tree)
    {
        // Checks if Current Root is null
        if (tree == null)
            return "";

        // If it isn't null, compares to item
        else if (tree.getData().toLowerCase().charAt(0) == item)
            return tree.getData().substring(1);

        // Recursively searches tree in PreOrder
        else
            return searchBinaryTree(item, tree.getLeftSubtree()) + searchBinaryTree(item, tree.getRightSubtree());
    }

    // Translates from morse to Alphabetical
    private static char translateFromMorseToText(String data, BinaryTree<String> tree)
    {
        // Base Case
        if (data.length() == 0)
            return tree.getData().charAt(0);

        // Recursive Case
        else
        {
            // Recurse through left Subtree
            if (data.charAt(0) == '*')
                return translateFromMorseToText(data.substring(1), tree.getLeftSubtree());

            // Recurse through right Subtree
            else
                return translateFromMorseToText(data.substring(1), tree.getRightSubtree());
        }
    }
}
