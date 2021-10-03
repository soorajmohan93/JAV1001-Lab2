/*
***************************************************************
      JAV-1001 App Development for Android
      JAV1001-Lab2 - Methods and Arrays
      Submitted by Sooraj Mohan (Student ID: A00247480)
***************************************************************
*/

import java.util.Scanner;                       //to use scanner class object to capture user inputs
import java.util.Random;                        //to generate Random integers
import java.util.InputMismatchException;        //to handle input invalid int user entry

public class Lab2
{
  public static void main(String[] args)
  {
  Scanner entry = new Scanner(System.in);        // Scanner object

  String userEntry;                              //to capture user's String inputs
  int userIntEntry;                              //to capture user's number inputs
  int[] randomNumber;                            //to hold array of random numbers
  char optRerun;                                 //variable for the option for user to rerun the application
  float avgValue;                                //variable to hold average value of the random number array

  // App welcome message
  System.out.println("-----------------------------------------------------------------------------");
  System.out.println("                             LAB 2:  Methods and Arrays");
  System.out.println("            Features: Caesar Cipher and playing with Random Numbers");
  System.out.println("-----------------------------------------------------------------------------");

  try                                                                         //try-catch to handle invalid integer entries
  {
    do{                                                                       //using do-while to loop back the processing if the user wishes so
      System.out.println(" ''''''''''''Caesar Cipher'''''''''''");
      System.out.print("Enter the text to be encrypted: ");
      userEntry = entry.nextLine();                                          //User enters the string to be encrypted
      System.out.print("Enter the value to encrypt the above text: ");
      userIntEntry = entry.nextInt();                                        //User enters the value to  encrypt the text
      entry.nextLine();                                                      //clearing scanner input buffer

      userEntry = caesarCipher(userEntry,userIntEntry);                      //calling method to use Caesar Cipher to encrypt text

      System.out.println("Encrypted text=> " + userEntry);                   //display of encrypted Text

      System.out.print("Decrypting " + userEntry + " with - " + userIntEntry + " => ");  //Decrypting the encrypted Text
      userEntry = caesarCipher(userEntry, (26 - (userIntEntry % 26)));                   //same method is called by passing - value of encryption
      System.out.println(userEntry);                                                     //Decrypted text is displayed here

      System.out.println(" ''''''''''''Random Number'''''''''''");
      System.out.print("Enter the number of Random Values to be generated => ");         //user can decide the number of random values to be generated
      userIntEntry = entry.nextInt();                                                    // variable userIntEntry is reused as the previous value is not needed anymore
      entry.nextLine();                                                                  //clearing scanner input buffer

      randomNumber = new int[userIntEntry];                                              //Instantiating random number array

      randomNumber = generateRandom(userIntEntry);                                       //calling method to generate the random integers

      System.out.print("Testing methods with => ");
      displayArray(randomNumber);                                                        //integer array display method

      avgValue = findingAnswers(randomNumber);                                           //Average is fetched from this method (overloading is used)
      System.out.println("Average is => " + avgValue);                                   //Average displayed

      System.out.print("Enter the number to search for => ");                            //user to enter the value to be searched
      userIntEntry = entry.nextInt();                                                    //again variable userIntEntry is reused as the previous value is not needed anymore
      entry.nextLine();
      boolean foundIt = findingAnswers(randomNumber, userIntEntry);                      //findingAnswers method is called using method overloading to search the value. This now returns a boolean.

      if (foundIt == true)                                                               //display of search result
      {
        System.out.println("The array contains " + userIntEntry + " :)");
      }
      else
      {
        System.out.println("The array doesn't contains " + userIntEntry + " :(");
      }

      randomNumber = reverseArray(randomNumber);                                         //reverse of array method is called here. randomNumber array is reused as the previous value is not needed anymore
      System.out.print("Array reversed is => ");
      displayArray(randomNumber);                                                        //integer array display method


      System.out.print("Do you want to run the application again? (Y/N): ");    //This is to take user's input if they want to rerun the application
      String userOpt = entry.next();
      userOpt = userOpt.toUpperCase();                                    //input from user is captured into String variable userOpt and set to upper case which helps in the if statements
      optRerun = userOpt.charAt(0);                                       //First character of the String is passed into char variable optRerun
      entry.nextLine();
      while (optRerun != 'Y' && optRerun != 'N')                          //Fallback for invalid entry - keeps looping until user enters Y or N.
      {
        System.out.print("Invalid Input - Kindly enter Y/N to proceed: ");
        userOpt = entry.next();
        userOpt = userOpt.toUpperCase();
        optRerun = userOpt.charAt(0);
        entry.nextLine();
      }

    }while(optRerun == 'Y');                                              //reRun the application if user chooses so
  }
  catch (InputMismatchException ex)                                       //exception handling for input mismatch
  {
    System.out.println("Invalid entry. Please rerun the application");
  }
  System.out.println("********Thank you. Application will close now.*********");        //Application closing message
 }




 public static String caesarCipher (String userEntry, int cipher)      //Caesar Cipher method
 {
   char strPos;                                                        //char to hold individual string character to encrypt/decrypt
   String encryptedText = new String();                                //char to hold encrypted/decrypted string
   for (int index = 0; index < userEntry.length(); index++)            //Loop structure accommodates multiple String
   {
     if (Character.isUpperCase(userEntry.charAt(index)))               //encryption/decryption for upper case
     {
       strPos = (char)(((int)userEntry.charAt(index) + cipher - 65) % 26 + 65);   //Caesar Cipher logic
     }
     else if ((Character.isLowerCase(userEntry.charAt(index))))       //encryption/decryption for lower case
     {
       strPos = (char)(((int)userEntry.charAt(index) + cipher - 97) % 26 + 97);   //Caesar Cipher logic
     }
     else
     {
       strPos = userEntry.charAt(index);                              //spaces and other characters are ignored - they are left as it is
     }
     encryptedText = encryptedText + strPos;                          //chars appended into string
   }
   return encryptedText;                                              //returns encrypted/decrypted string
 }

 public static int[] generateRandom (int numberValues)                //method to generate Random numbers
 {
   int[] randomNumber = new int[numberValues];                        //array to return
   Random genRandom = new Random();                                   //Random object
   for (int index = 0; index < randomNumber.length; index++)          //Loop structure accommodates multiple array
   {
    randomNumber[index] = genRandom.nextInt(101);                     //appending random numbers into array
   }
   return randomNumber;                                               //returns array of random numbers
 }

 public static float findingAnswers (int[] arrayVal)                  //method overloading - finding average
 {
   int sum = 0;                                                       //int variable to hold sum of all numbers in array
   for (int index = 0; index < arrayVal.length; index++)              //Loop structure accommodates multiple array
   {
     sum += arrayVal[index];                                          //sum of all integers
   }
   return (float)sum/arrayVal.length;                                        //returns average
 }

 public static boolean findingAnswers (int[] arrayVal, int findThisValue)   //method overloading - searching number
 {
   boolean foundIt = false;                                                 //boolean marked true value found
   for (int index = 0; index < arrayVal.length; index++)                    //Loop structure accommodates multiple array
   {
     if(arrayVal[index] == findThisValue)                                   //if value found, boolean is marked true and loop stopped
     {
       foundIt = true;
       break;
     }
   }
   return foundIt;                                                          //returns true/false
 }

 public static int[] reverseArray (int[] arrayVal)                      //method to reverse Array of random numbers
 {
   int[] revArray= new int[arrayVal.length];                            //declaring new int array for holding reversed array

   for (int i = 0 , j = arrayVal.length - 1; i < arrayVal.length ; i++ , j--)  //Loop structure accommodates multiple arrays
   {
     revArray[i] = arrayVal[j];                                         //array is reversed
   }
   return revArray;                                                     //return int array
 }

 public static void displayArray (int[] arrayVal)                       //method to display arrays - as display array is used in multiple places, a method is used
 {
   System.out.print("[");
   for (int index = 0; index < arrayVal.length; index++)                //Loop structure accommodates multiple arrays
   {
     System.out.print(" " + arrayVal[index]);                           //display array elements
   }
   System.out.println(" ]");
 }

}
