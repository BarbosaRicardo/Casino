/***********************************************************
 * 
 * @author Ricardo Barbosa
 * @version 10/30/2019
 * 
 ***********************************************************/

import java.util.*;
import java.lang.Math;

public class Casino
{
   
	private static Scanner keyboard = new Scanner(System.in);
	
	//Constructor for ThreeString class
	class ThreeString
	{
		//Maximum length of any string 
		public static final int MAX_LEN = 20;
		public static final int MAX_PULLS = 40;
		
		//Array of outcome of pull winnings
		private static int[] pullWinnings = new int[MAX_PULLS];
		
		//Outcomes of pull
		private String string1;
		private String string2;
		private String string3;
		
		//The current index of the pullWinnings
		private static int numPulls = 0;
		
		ThreeString()
		{
			string1 = "";
			string2 = "";
			string3 = "";
		}
		
		//Accessors to string values 
		
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
		
		//Mutators of string values
		
		public boolean setString1(String value)
		{
			//Validates the string 
			if(validString(value))
			{
				string1 = value;
				return true;
			}
			return false;
		}
		
		public boolean setString2(String value)
		{
			if(validString(value))
			{
				string2 = value;
				return true;
			}
			return false;
		}
		
		public boolean setString3(String value)
		{
			if(validString(value)) {
				string3 = value;
				return true;
			}
			return false;
		}
		
		public String toString() {
			return string1 + " " + string2 + " " + string3;
		}
		
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
			
			//total winnings 
			message += "\nYour total winnings were: $" + totalWinnings;
			
			return message;
		}
		
		//True only when not empty and less than max length of characters
		private boolean validString(String str)
		{
			if(!str.equals("") && str.length() <= MAX_LEN)
				return true;
			return false;
		}
	}
	/*
	 * @input valid bet
	 * @return bet amount as functional return 
	 */
	public static int getBet()
	{
		int userBet = -1;
		
		//Prompts until the bet is valid
		while (userBet < 0 || userBet >100)
		{
			System.out.println("How much would you like to bet? (1 - 100) " +
										"or quit: ");
			userBet = keyboard.nextInt();
		}
		
		return userBet;
	}
	
	//Instantiates and returns a ThreeString object to the user
	public static ThreeString pull()
	{
		ThreeString pullResults = new ThreeString();
		
		//Random results populated
		pullResults.setString1(randString());
		pullResults.setString2(randString());
		pullResults.setString3(randString());
		
		return pullResults;
	}
	
	//Returns random string based on probability
	private static String randString()
	{
		double mathRand = Math.random();
		
		//space 50%
		if(mathRand <= 0.50)
			return "space";
		
		//cherries 25%
		if(mathRand <= 0.25)
			return "cherries";
		
		//BAR 12.5% & '7' 12.5%
		if(mathRand <= 0.125)
			return "BAR";
		return "7";
	}
	
   //Pay multiplier is determined 
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
	
	//Outcome displayed to user 
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
		
		//Summary 
		System.out.println("Thanks for playing at the Casino");
		System.out.println(newPull.displayWinnings());

	}

}
