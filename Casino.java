/*****************************************************************
 * 
 * @author Ricardo Barbosa
 * @version 10/31/2019
 * Course:       CST338 Fall 2019
 * Description: Casino program that asks user to input a value 
 *              between 1 - 100 and returns 3 strings with 
 *              amount the user won. 
 * Usage:       A simple gambling game 
 * 
 *****************************************************************/

import java.util.*;
import java.lang.Math;

public class Casino
{
   //Scanner object created 
   private static Scanner keyboard = new Scanner(System.in);
   
   
   /********************************************************
    * Public methods
    ********************************************************/
   
   /**
    * getBet() retrieves a bet from the user 
    * 
    * @return The bet the user input (1-100)
    */
   public static int getBet()
   {
      int userBet = -1;
      
      //Prompts user until valid bet is entered 
      while (userBet < 0 || userBet >100)
      {
         System.out.println("How much would you like to bet? ($1 - $100) " +
                              "or quit: ");
         userBet = keyboard.nextInt();
      }
      return userBet;
   }
   
   /**
    * randString() generates the statistic for each string 
    * 
    * @return A statistically randomized string 
    */
   private static String randString()
   {
      double mathRand = Math.random();
      
      //space 50%
      if(mathRand <= 0.50)
         return "space";
   
      //cherries 25%
      if(mathRand <= 0.75)
         return "cherries";
      
      //BAR 12.5% & '7' 12.5%
      if(mathRand <= 0.875)
         return "BAR";
      return "7";
   }
   
   /**
    * pull() creates a new object of type ThreeString 
    * 
    * @return An instantiated object with 3 statistically randomized values
    */
   public static ThreeString pull()
   {
      //Instantiates a new ThreeString object
      ThreeString pullResults = new ThreeString();
      
      //Randomly populates 3 strings 
      pullResults.setString1(randString());
      pullResults.setString2(randString());
      pullResults.setString3(randString());
      
      return pullResults;
   }
   
   /**
    * getPayMultiplier() determines the amount awarded 
    *                    based on string combination
    * 
    * @return individual amount paid per pull
    * @param  An object of type ThreeString 
    */
   
   public static int getPayMultiplier(ThreeString thePull)
   {
      String pullString1 = thePull.getString1();
      String pullString2 = thePull.getString2();
      String pullString3 = thePull.getString3();
      
      if (pullString1.equals("cherries"))
      {
         if (pullString2.equals("cherries"))
         {
            if (pullString3.equals("cherries"))
            {
               //cherries cherries cherries
               return 30;
            }
            //cherries cherries [other]
            return 15;
         }
         else
         {
            //cherries [other] [other]
            return 5;
         }
         
      }
      else if (pullString1.equals("BAR"))
      {
         if (pullString1.equals(pullString2) &&
            pullString2.equals(pullString3))
         {
            //BAR BAR BAR
            return 50;
         }
      }
      else if (pullString1.equals("7"))
      {
         if (pullString1.equals(pullString2) &&
            pullString2.equals(pullString3))
         {
            //7 7 7
            return 100;
         }
      }
      
      //no winning combination
      return 0;      
   }
   
   /**
    * display() displays the pull result with winnings or other 
    * 
    * @param thePull
    * @param winnings
    */
   public static void display(ThreeString thePull, int winnings)
   {
      System.out.println("whirrrrr .... and your pull is ...");
      System.out.println(thePull);
      
      //See if above pull is a winner
      if (winnings > 0)
         System.out.println("Congrats, you won $" + winnings);
      else
         System.out.println("Sorry - you lost");
      
      System.out.println();
   }
   
   
   /************************************************************
    * Main Method
    * @param args
    ************************************************************/
   public static void main(String[] args)
   {
      int userBet = getBet();
      ThreeString newPull = new ThreeString();
      
      //game loop
      while (userBet > 0)
      {
         newPull = pull();
         int userWinnings = userBet * getPayMultiplier(newPull);
         
         //if the true is above, continue with the following 
         if (newPull.saveWinnings(userWinnings))
            display(newPull, userWinnings);
         else
            break;
         
         userBet = getBet();
      }
      
      //Summary & closing of scanner object
      System.out.println("Thanks for playing at the Casino");
      System.out.println(newPull.displayWinnings());
      keyboard.close();
   }
}

/*********************************************************
 *   ThreeString class 
 *********************************************************/

class ThreeString
{
   //Maximum length of any string 
   public static final int MAX_LEN = 20;
   public static final int MAX_PULLS = 40;
   
   //Array outcome of pulled winnings
   private static int[] pullWinnings = new int[MAX_PULLS];
   
   //Outcomes of pull
   private String string1;
   private String string2;
   private String string3;
   
   //Index of the pullWinnings array
   private static int numPulls = 0;
   
   //Default constructor
   ThreeString()
   {
      string1 = "";
      string2 = "";
      string3 = "";
   }
   
   //Accessors to the private String values 
   
   public String getString1()
   {
      return string1;
   }
   public String getString2()
   {
      return string2;
   }
   public String getString3()
   {
      return string3;
   }
   
   //Private helper method 
   private boolean validString(String str)
   {
      if(!str.equals("") && str.length() <= MAX_LEN)
         return true;
      return false;
   }
   
   //Mutators of string value
   public boolean setString1(String value)
   {
      //Validates legal string1
      if(validString(value))
      {
         string1 = value;
         return true;
      }
      return false;
   }

   public boolean setString2(String value)
   {
      //Validates legal string2
      if(validString(value))
      {
         string2 = value;
         return true;
      }
      return false;
   }
   
   public boolean setString3(String value)
   {
      //Validates legal string3
      if(validString(value)) {
         string3 = value;
         return true;
      }
      return false;
   }
   
   //Returns all strings as one string
   public String toString() {
      return string1 + " " + string2 + " " + string3;
   }
   
   //Saves winnings from round
   public boolean saveWinnings(int winnings)
   {
      //Boundary
      if(numPulls < MAX_PULLS)
      {
         pullWinnings[numPulls] = winnings;
         
         //Validates assignment in array
         if (pullWinnings[numPulls] == winnings)
         {
            ++numPulls;
            return true;
         }
      }
      return false;
   }
   
   //Uses loop to get values out of array
   //Displays total winnings 
   public String displayWinnings()
   {
      String message = "Your individual winnings were:\n";
      int totalWinnings = 0;
      
      //Individual winnings shown and total winnings calculated 
      for (int i = 0; i < numPulls; ++i)
      {
         message += " " + pullWinnings[i];
         totalWinnings += pullWinnings[i];
      }
      
      //total winnings displayed
      message += "\nYour total winnings were: $" + totalWinnings;
      
      return message;
   }
   
}
 
//this line was generated to commit a new pull request to the master for peer review 
   
